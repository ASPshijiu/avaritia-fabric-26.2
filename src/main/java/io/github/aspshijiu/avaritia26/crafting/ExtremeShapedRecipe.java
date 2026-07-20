package io.github.aspshijiu.avaritia26.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.Level;

public final class ExtremeShapedRecipe implements Recipe<CraftingInput> {
	public static final MapCodec<ExtremeShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
			instance.group(
					ExtremeShapedPatternCodec.MAP_CODEC.forGetter(recipe -> recipe.pattern),
					ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
			).apply(instance, ExtremeShapedRecipe::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, ExtremeShapedRecipe> STREAM_CODEC = StreamCodec.composite(
			ShapedRecipePattern.STREAM_CODEC,
			recipe -> recipe.pattern,
			ItemStackTemplate.STREAM_CODEC,
			recipe -> recipe.result,
			ExtremeShapedRecipe::new
	);

	private final ShapedRecipePattern pattern;
	private final ItemStackTemplate result;

	public ExtremeShapedRecipe(ShapedRecipePattern pattern, ItemStackTemplate result) {
		this.pattern = pattern;
		this.result = result;
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		return pattern.matches(input);
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
		return ModRecipes.EXTREME_SHAPED_SERIALIZER;
	}

	@Override
	public RecipeType<? extends Recipe<CraftingInput>> getType() {
		return ModRecipes.EXTREME_CRAFTING;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.createFromOptionals(pattern.ingredients());
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeBookCategories.CRAFTING_MISC;
	}
}
