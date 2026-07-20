package io.github.aspshijiu.avaritia26.crafting;

import java.util.ArrayList;
import java.util.List;

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
import net.minecraft.world.level.Level;

public final class ExtremeSmithingRecipe implements Recipe<ExtremeSmithingInput> {
	public static final MapCodec<ExtremeSmithingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC.fieldOf("template").forGetter(recipe -> recipe.template),
			Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
			Ingredient.CODEC.listOf().fieldOf("addition").forGetter(recipe -> recipe.additions),
			ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
	).apply(instance, ExtremeSmithingRecipe::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, ExtremeSmithingRecipe> STREAM_CODEC =
			StreamCodec.of(ExtremeSmithingRecipe::write, ExtremeSmithingRecipe::read);

	private final Ingredient template;
	private final Ingredient base;
	private final List<Ingredient> additions;
	private final ItemStackTemplate result;

	public ExtremeSmithingRecipe(
			Ingredient template,
			Ingredient base,
			List<Ingredient> additions,
			ItemStackTemplate result
	) {
		if (template.isEmpty() || base.isEmpty() || additions.size() != 3 || additions.stream().anyMatch(Ingredient::isEmpty)) {
			throw new IllegalArgumentException("强化锻造配方必须包含模板、基底和三种追加材料");
		}
		this.template = template;
		this.base = base;
		this.additions = List.copyOf(additions);
		this.result = result;
	}

	@Override
	public boolean matches(ExtremeSmithingInput input, Level level) {
		return template.test(input.template())
				&& base.test(input.base())
				&& additionsMatch(input);
	}

	private boolean additionsMatch(ExtremeSmithingInput input) {
		List<ItemStack> remaining = new ArrayList<>(List.of(input.addition1(), input.addition2(), input.addition3()));
		for (Ingredient ingredient : additions) {
			int match = -1;
			for (int index = 0; index < remaining.size(); index++) {
				if (ingredient.test(remaining.get(index))) {
					match = index;
					break;
				}
			}
			if (match < 0) {
				return false;
			}
			remaining.remove(match);
		}
		return remaining.isEmpty();
	}

	@Override
	public ItemStack assemble(ExtremeSmithingInput input) {
		ItemStack stack = input.base().transmuteCopy(result.item().value(), result.count());
		stack.applyComponents(result.components());
		return stack;
	}

	public boolean acceptsTemplate(ItemStack stack) {
		return template.test(stack);
	}

	public boolean acceptsBase(ItemStack stack) {
		return base.test(stack);
	}

	public boolean acceptsAddition(ItemStack stack) {
		return additions.stream().anyMatch(ingredient -> ingredient.test(stack));
	}

	@Override
	public boolean showNotification() {
		return true;
	}

	@Override
	public String group() {
		return "extreme_smithing";
	}

	@Override
	public RecipeSerializer<? extends Recipe<ExtremeSmithingInput>> getSerializer() {
		return ModRecipes.EXTREME_SMITHING_SERIALIZER;
	}

	@Override
	public RecipeType<? extends Recipe<ExtremeSmithingInput>> getType() {
		return ModRecipes.EXTREME_SMITHING;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.create(List.of(template, base, additions.get(0), additions.get(1), additions.get(2)));
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeBookCategories.CRAFTING_MISC;
	}

	private static void write(RegistryFriendlyByteBuf buffer, ExtremeSmithingRecipe recipe) {
		Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.template);
		Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
		for (Ingredient addition : recipe.additions) {
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, addition);
		}
		ItemStackTemplate.STREAM_CODEC.encode(buffer, recipe.result);
	}

	private static ExtremeSmithingRecipe read(RegistryFriendlyByteBuf buffer) {
		return new ExtremeSmithingRecipe(
				Ingredient.CONTENTS_STREAM_CODEC.decode(buffer),
				Ingredient.CONTENTS_STREAM_CODEC.decode(buffer),
				List.of(
						Ingredient.CONTENTS_STREAM_CODEC.decode(buffer),
						Ingredient.CONTENTS_STREAM_CODEC.decode(buffer),
						Ingredient.CONTENTS_STREAM_CODEC.decode(buffer)
				),
				ItemStackTemplate.STREAM_CODEC.decode(buffer)
		);
	}
}
