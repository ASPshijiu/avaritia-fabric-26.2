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
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public final class EternalSingularityRecipe implements Recipe<CraftingInput> {
	public static final MapCodec<EternalSingularityRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Codec.intRange(1, 8).optionalFieldOf("count", 1).forGetter(recipe -> recipe.count)
	).apply(instance, EternalSingularityRecipe::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, EternalSingularityRecipe> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.VAR_INT,
			recipe -> recipe.count,
			EternalSingularityRecipe::new
	);

	private final int count;

	public EternalSingularityRecipe(int count) {
		if (count < 1 || count > 8) {
			throw new IllegalArgumentException("永恒奇点输出数量必须在 1 到 8 之间");
		}
		this.count = count;
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		List<SingularityDefinition> required = SingularityManager.values().stream()
				.filter(SingularityDefinition::recipeEnabled)
				.toList();
		if (required.isEmpty() || input.ingredientCount() != required.size()) {
			return false;
		}

		Set<Identifier> matched = new HashSet<>();
		for (ItemStack stack : input.items()) {
			if (stack.isEmpty()) {
				continue;
			}
			SingularityDefinition snapshot = stack.get(ModDataComponents.SINGULARITY);
			if (!stack.is(ModItems.SINGULARITY) || snapshot == null) {
				return false;
			}
			SingularityDefinition current = SingularityManager.get(snapshot.name());
			if (current == null || !current.enabled() || !current.recipeEnabled()
					|| !current.equals(snapshot) || !matched.add(current.name())) {
				return false;
			}
		}
		return matched.size() == required.size();
	}

	@Override
	public ItemStack assemble(CraftingInput input) {
		return new ItemStack(ModItems.ETERNAL_SINGULARITY, count);
	}

	@Override
	public boolean showNotification() {
		return true;
	}

	@Override
	public String group() {
		return "eternal_singularity";
	}

	@Override
	public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
		return ModRecipes.ETERNAL_SINGULARITY_SERIALIZER;
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
