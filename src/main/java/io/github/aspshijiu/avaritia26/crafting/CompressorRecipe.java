package io.github.aspshijiu.avaritia26.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

public final class CompressorRecipe implements Recipe<SingleRecipeInput> {
	public static final MapCodec<CompressorRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
			ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
			Codec.intRange(1, 1_000_000).optionalFieldOf("inputCount", 1000).forGetter(recipe -> recipe.inputCount),
			Codec.intRange(1, 72_000).optionalFieldOf("timeCost", 240).forGetter(recipe -> recipe.timeCost)
	).apply(instance, CompressorRecipe::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, CompressorRecipe> STREAM_CODEC =
			StreamCodec.of(CompressorRecipe::write, CompressorRecipe::read);

	private final Ingredient ingredient;
	private final ItemStackTemplate result;
	private final int inputCount;
	private final int timeCost;

	public CompressorRecipe(Ingredient ingredient, ItemStackTemplate result, int inputCount, int timeCost) {
		if (ingredient.isEmpty()) {
			throw new IllegalArgumentException("压缩配方的输入和输出不能为空");
		}
		if (inputCount < 1 || inputCount > 1_000_000 || timeCost < 1 || timeCost > 72_000) {
			throw new IllegalArgumentException("压缩配方参数超出允许范围");
		}
		this.ingredient = ingredient;
		this.result = result;
		this.inputCount = inputCount;
		this.timeCost = timeCost;
	}

	public Ingredient ingredient() {
		return ingredient;
	}

	public int inputCount() {
		return inputCount;
	}

	public int timeCost() {
		return timeCost;
	}

	@Override
	public boolean matches(SingleRecipeInput input, Level level) {
		return ingredient.test(input.item());
	}

	@Override
	public ItemStack assemble(SingleRecipeInput input) {
		return result.create();
	}

	@Override
	public boolean showNotification() {
		return false;
	}

	@Override
	public String group() {
		return "compressor";
	}

	@Override
	public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
		return ModRecipes.COMPRESSOR_SERIALIZER;
	}

	@Override
	public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
		return ModRecipes.COMPRESSOR;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.create(ingredient);
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeBookCategories.CRAFTING_MISC;
	}

	private static void write(RegistryFriendlyByteBuf buffer, CompressorRecipe recipe) {
		Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient);
		ItemStackTemplate.STREAM_CODEC.encode(buffer, recipe.result);
		buffer.writeVarInt(recipe.inputCount);
		buffer.writeVarInt(recipe.timeCost);
	}

	private static CompressorRecipe read(RegistryFriendlyByteBuf buffer) {
		return new CompressorRecipe(
				Ingredient.CONTENTS_STREAM_CODEC.decode(buffer),
				ItemStackTemplate.STREAM_CODEC.decode(buffer),
				buffer.readVarInt(),
				buffer.readVarInt()
		);
	}
}
