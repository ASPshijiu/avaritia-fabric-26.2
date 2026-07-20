package io.github.aspshijiu.avaritia26.crafting;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import io.github.aspshijiu.avaritia26.singularity.SingularityDefinition;
import io.github.aspshijiu.avaritia26.singularity.SingularityManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
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

public final class InfinityCatalystRecipe implements Recipe<CraftingInput> {
	public static final MapCodec<InfinityCatalystRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC.listOf(1, 64).fieldOf("ingredients").forGetter(recipe -> recipe.fixedIngredients),
			Codec.intRange(1, 64).optionalFieldOf("count", 1).forGetter(recipe -> recipe.count)
	).apply(instance, InfinityCatalystRecipe::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, InfinityCatalystRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(64)),
			recipe -> recipe.fixedIngredients,
			ByteBufCodecs.VAR_INT,
			recipe -> recipe.count,
			InfinityCatalystRecipe::new
	);

	private final List<Ingredient> fixedIngredients;
	private final int count;

	public InfinityCatalystRecipe(List<Ingredient> fixedIngredients, int count) {
		this.fixedIngredients = List.copyOf(fixedIngredients);
		if (this.fixedIngredients.isEmpty() || this.fixedIngredients.size() > 64) {
			throw new IllegalArgumentException("无尽催化剂固定配料数量必须在 1 到 64 之间");
		}
		if (this.fixedIngredients.stream().anyMatch(Ingredient::isEmpty)) {
			throw new IllegalArgumentException("无尽催化剂固定配料不能为空");
		}
		if (count < 1 || count > 64) {
			throw new IllegalArgumentException("无尽催化剂输出数量必须在 1 到 64 之间");
		}
		this.count = count;
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		List<SingularityDefinition> requiredSingularities = SingularityManager.values().stream()
				.filter(SingularityDefinition::recipeEnabled)
				.toList();
		if (requiredSingularities.isEmpty()
				|| input.ingredientCount() != fixedIngredients.size() + requiredSingularities.size()) {
			return false;
		}

		boolean[] fixedMatched = new boolean[fixedIngredients.size()];
		Set<Identifier> singularitiesMatched = new HashSet<>();
		for (ItemStack stack : input.items()) {
			if (stack.isEmpty()) {
				continue;
			}
			SingularityDefinition stackDefinition = stack.get(ModDataComponents.SINGULARITY);
			if (stack.is(ModItems.SINGULARITY) && stackDefinition != null) {
				SingularityDefinition required = SingularityManager.get(stackDefinition.name());
				if (required == null || !required.enabled() || !required.recipeEnabled()
						|| !required.equals(stackDefinition) || !singularitiesMatched.add(required.name())) {
					return false;
				}
				continue;
			}
			boolean matched = false;
			for (int index = 0; index < fixedIngredients.size(); index++) {
				if (!fixedMatched[index] && fixedIngredients.get(index).test(stack)) {
					fixedMatched[index] = true;
					matched = true;
					break;
				}
			}
			if (!matched) {
				return false;
			}
		}
		return singularitiesMatched.size() == requiredSingularities.size();
	}

	@Override
	public ItemStack assemble(CraftingInput input) {
		return new ItemStack(ModItems.INFINITY_CATALYST, count);
	}

	@Override
	public boolean showNotification() {
		return true;
	}

	@Override
	public String group() {
		return "infinity_catalyst";
	}

	@Override
	public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
		return ModRecipes.INFINITY_CATALYST_SERIALIZER;
	}

	@Override
	public RecipeType<? extends Recipe<CraftingInput>> getType() {
		return ModRecipes.EXTREME_CRAFTING;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.NOT_PLACEABLE;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeBookCategories.CRAFTING_MISC;
	}
}
