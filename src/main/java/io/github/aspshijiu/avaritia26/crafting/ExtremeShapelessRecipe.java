package io.github.aspshijiu.avaritia26.crafting;

import java.util.List;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public final class ExtremeShapelessRecipe implements Recipe<CraftingInput> {
	public static final MapCodec<ExtremeShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
			instance.group(
					Ingredient.CODEC.listOf(1, 81).fieldOf("ingredients").forGetter(recipe -> recipe.ingredients),
					ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
			).apply(instance, ExtremeShapelessRecipe::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, ExtremeShapelessRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(81)),
			recipe -> recipe.ingredients,
			ItemStackTemplate.STREAM_CODEC,
			recipe -> recipe.result,
			ExtremeShapelessRecipe::new
	);

	private final List<Ingredient> ingredients;
	private final ItemStackTemplate result;

	public ExtremeShapelessRecipe(List<Ingredient> ingredients, ItemStackTemplate result) {
		this.ingredients = ingredients;
		this.result = result;
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		if (input.ingredientCount() != ingredients.size()) {
			return false;
		}
		if (input.size() == 1 && ingredients.size() == 1) {
			return ingredients.getFirst().test(input.getItem(0));
		}
		return input.stackedContents().canCraft(this, null);
	}

	@Override
	public ItemStack assemble(CraftingInput input) {
		return result.create();
	}

	@Override
	public boolean showNotification() {
		return true;
	}

	@Override
	public String group() {
		return "";
	}

	@Override
	public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
		return ModRecipes.EXTREME_SHAPELESS_SERIALIZER;
	}

	@Override
	public RecipeType<? extends Recipe<CraftingInput>> getType() {
		return ModRecipes.EXTREME_CRAFTING;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.create(ingredients);
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeBookCategories.CRAFTING_MISC;
	}
}
