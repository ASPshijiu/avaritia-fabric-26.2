package io.github.aspshijiu.avaritia26.crafting;

import io.github.aspshijiu.avaritia26.Avaritia26;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public final class ModRecipes {
	public static final RecipeType<Recipe<CraftingInput>> EXTREME_CRAFTING = Registry.register(
			BuiltInRegistries.RECIPE_TYPE,
			Avaritia26.id("extreme_crafting"),
			new RecipeType<>() {
				@Override
				public String toString() {
					return Avaritia26.id("extreme_crafting").toString();
				}
			}
	);
	public static final RecipeSerializer<ExtremeShapedRecipe> EXTREME_SHAPED_SERIALIZER = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER,
			Avaritia26.id("extreme_shaped"),
			new RecipeSerializer<>(ExtremeShapedRecipe.CODEC, ExtremeShapedRecipe.STREAM_CODEC)
	);
	public static final RecipeSerializer<ExtremeShapelessRecipe> EXTREME_SHAPELESS_SERIALIZER = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER,
			Avaritia26.id("extreme_shapeless"),
			new RecipeSerializer<>(ExtremeShapelessRecipe.CODEC, ExtremeShapelessRecipe.STREAM_CODEC)
	);
	public static final RecipeSerializer<FullMatterClusterRecipe> FULL_MATTER_CLUSTER_SERIALIZER = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER,
			Avaritia26.id("full_matter_cluster"),
			new RecipeSerializer<>(FullMatterClusterRecipe.CODEC, FullMatterClusterRecipe.STREAM_CODEC)
	);

	private ModRecipes() {
	}

	public static void initialize() {
	}
}
