package io.github.aspshijiu.avaritia26.gametest;

import java.lang.reflect.Method;
import java.util.List;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.gametest.v1.CustomTestMethodInvoker;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;

public final class Avaritia26GameTests implements CustomTestMethodInvoker {
	@GameTest
	public void modStartsOnServer(GameTestHelper helper) {
		helper.assertBlockPresent(Blocks.AIR, 0, 0, 0);
		helper.succeed();
	}

	@GameTest
	public void diamondLatticeIsRegistered(GameTestHelper helper) {
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.DIAMOND_LATTICE_KEY) == ModItems.DIAMOND_LATTICE,
				"钻石晶格没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				new ItemStack(ModItems.DIAMOND_LATTICE).is(ModItems.DIAMOND_LATTICE),
				"钻石晶格 ItemStack 未指向注册物品"
		);
		helper.assertTrue(
				ModItems.DIAMOND_LATTICE.getDefaultMaxStackSize() == 64,
				"钻石晶格应当可以堆叠 64 个"
		);
		helper.succeed();
	}

	@GameTest
	public void crystalMatrixIngotIsRegistered(GameTestHelper helper) {
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.CRYSTAL_MATRIX_INGOT_KEY) == ModItems.CRYSTAL_MATRIX_INGOT,
				"晶态矩阵锭没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT).is(ModItems.CRYSTAL_MATRIX_INGOT),
				"晶态矩阵锭 ItemStack 未指向注册物品"
		);
		helper.assertTrue(
				ModItems.CRYSTAL_MATRIX_INGOT.getDefaultMaxStackSize() == 64,
				"晶态矩阵锭应当可以堆叠 64 个"
		);
		helper.succeed();
	}

	@GameTest
	public void diamondLatticeNormalRecipeWorks(GameTestHelper helper) {
		CraftingInput input = CraftingInput.of(3, 3, List.of(
				new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND),
				new ItemStack(Items.DIAMOND), new ItemStack(Items.NETHERITE_SCRAP), new ItemStack(Items.DIAMOND),
				new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND)
		));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("diamond_lattice_normal")
		);
		RecipeHolder<CraftingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(RecipeType.CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("正确材料未匹配钻石晶格配方"));

		helper.assertTrue(recipe.id().equals(recipeKey), "正确材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModItems.DIAMOND_LATTICE), "钻石晶格配方输出了错误物品");
		helper.assertTrue(result.getCount() == 1, "钻石晶格配方应当只输出 1 个");

		CraftingInput wrongInput = CraftingInput.of(3, 3, List.of(
				new ItemStack(Items.NETHERITE_SCRAP), new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND),
				new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND),
				new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND)
		));
		boolean wrongInputMatches = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(RecipeType.CRAFTING, wrongInput, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.isPresent();
		helper.assertFalse(wrongInputMatches, "下界合金碎片位置错误时不应匹配钻石晶格配方");
		helper.succeed();
	}

	@GameTest
	public void crystalMatrixIngotNormalRecipeWorks(GameTestHelper helper) {
		CraftingInput input = CraftingInput.of(3, 3, List.of(
				new ItemStack(ModItems.DIAMOND_LATTICE), new ItemStack(Items.NETHER_STAR),
				new ItemStack(ModItems.DIAMOND_LATTICE),
				new ItemStack(ModItems.DIAMOND_LATTICE), new ItemStack(Items.NETHER_STAR),
				new ItemStack(ModItems.DIAMOND_LATTICE),
				new ItemStack(ModItems.DIAMOND_LATTICE), new ItemStack(Items.NETHER_STAR),
				new ItemStack(ModItems.DIAMOND_LATTICE)
		));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("crystal_matrix_ingot_normal")
		);
		RecipeHolder<CraftingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(RecipeType.CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("正确材料未匹配晶态矩阵锭配方"));

		helper.assertTrue(recipe.id().equals(recipeKey), "正确材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModItems.CRYSTAL_MATRIX_INGOT), "晶态矩阵锭配方输出了错误物品");
		helper.assertTrue(result.getCount() == 1, "晶态矩阵锭配方应当只输出 1 个");

		CraftingInput wrongInput = CraftingInput.of(3, 3, List.of(
				new ItemStack(Items.NETHER_STAR), new ItemStack(ModItems.DIAMOND_LATTICE),
				new ItemStack(ModItems.DIAMOND_LATTICE),
				new ItemStack(ModItems.DIAMOND_LATTICE), new ItemStack(Items.NETHER_STAR),
				new ItemStack(ModItems.DIAMOND_LATTICE),
				new ItemStack(ModItems.DIAMOND_LATTICE), new ItemStack(Items.NETHER_STAR),
				new ItemStack(ModItems.DIAMOND_LATTICE)
		));
		boolean wrongInputMatches = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(RecipeType.CRAFTING, wrongInput, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.isPresent();
		helper.assertFalse(wrongInputMatches, "材料错位时不应匹配晶态矩阵锭配方");
		helper.succeed();
	}

	@Override
	public void invokeTestMethod(GameTestHelper helper, Method method) throws ReflectiveOperationException {
		helper.setBlock(0, 0, 0, Blocks.AIR);
		method.invoke(this, helper);
	}
}
