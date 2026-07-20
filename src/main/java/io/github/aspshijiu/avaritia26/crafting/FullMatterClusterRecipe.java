package io.github.aspshijiu.avaritia26.crafting;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.aspshijiu.avaritia26.item.MatterClusterItem;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public final class FullMatterClusterRecipe implements Recipe<CraftingInput> {
	public static final MapCodec<FullMatterClusterRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
			instance.group(
					Codec.STRING.optionalFieldOf("group", "matter_cluster").forGetter(recipe -> recipe.group),
					Ingredient.CODEC.listOf(1, 81).fieldOf("ingredients").forGetter(recipe -> recipe.ingredients)
			).apply(instance, FullMatterClusterRecipe::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, FullMatterClusterRecipe> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.STRING_UTF8,
			recipe -> recipe.group,
			Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(81)),
			recipe -> recipe.ingredients,
			FullMatterClusterRecipe::new
	);

	private final String group;
	private final List<Ingredient> ingredients;

	public FullMatterClusterRecipe(String group, List<Ingredient> ingredients) {
		this.group = group;
		this.ingredients = ingredients;
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		if (input.ingredientCount() != ingredients.size()) {
			return false;
		}
		boolean ingredientsMatch = input.size() == 1 && ingredients.size() == 1
				? ingredients.getFirst().test(input.getItem(0))
				: input.stackedContents().canCraft(this, null);
		if (!ingredientsMatch) {
			return false;
		}
		for (ItemStack stack : input.items()) {
			if (stack.is(ModItems.MATTER_CLUSTER) && MatterClusterItem.getSize(stack) >= MatterClusterItem.CAPACITY) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack assemble(CraftingInput input) {
		return new ItemStack(ModItems.FULL_MATTER_CLUSTER);
	}

	@Override
	public boolean showNotification() {
		return true;
	}

	@Override
	public String group() {
		return group;
	}

	@Override
	public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
		return ModRecipes.FULL_MATTER_CLUSTER_SERIALIZER;
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
