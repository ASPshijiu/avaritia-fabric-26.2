package io.github.aspshijiu.avaritia26.gametest;

import java.lang.reflect.Method;
import java.util.List;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.gametest.v1.CustomTestMethodInvoker;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public final class Avaritia26GameTests implements CustomTestMethodInvoker {
	@GameTest
	public void modStartsOnServer(GameTestHelper helper) {
		helper.assertBlockPresent(Blocks.AIR, 0, 0, 0);
		helper.succeed();
	}

	@GameTest
	public void diamondLatticeIsRegistered(GameTestHelper helper) {
		ItemStack stack = new ItemStack(ModItems.DIAMOND_LATTICE);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.DIAMOND_LATTICE_KEY) == ModItems.DIAMOND_LATTICE,
				"钻石晶格没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				stack.is(ModItems.DIAMOND_LATTICE),
				"钻石晶格 ItemStack 未指向注册物品"
		);
		helper.assertTrue(
				ModItems.DIAMOND_LATTICE.getDefaultMaxStackSize() == 64,
				"钻石晶格应当可以堆叠 64 个"
		);
		helper.assertTrue(stack.getRarity() == Rarity.UNCOMMON, "钻石晶格应当是 UNCOMMON 稀有度");
		helper.succeed();
	}

	@GameTest
	public void crystalMatrixIngotIsRegistered(GameTestHelper helper) {
		ItemStack stack = new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.CRYSTAL_MATRIX_INGOT_KEY) == ModItems.CRYSTAL_MATRIX_INGOT,
				"晶态矩阵锭没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				stack.is(ModItems.CRYSTAL_MATRIX_INGOT),
				"晶态矩阵锭 ItemStack 未指向注册物品"
		);
		helper.assertTrue(
				ModItems.CRYSTAL_MATRIX_INGOT.getDefaultMaxStackSize() == 64,
				"晶态矩阵锭应当可以堆叠 64 个"
		);
		helper.assertTrue(stack.getRarity() == Rarity.RARE, "晶态矩阵锭应当是 RARE 稀有度");
		helper.assertTrue(
				stack.has(DataComponents.DAMAGE_RESISTANT),
				"晶态矩阵锭应当具有防火伤害抗性组件"
		);
		helper.succeed();
	}

	@GameTest
	public void neutronMaterialsAreRegistered(GameTestHelper helper) {
		assertRegisteredMaterial(helper, ModItems.NEUTRON_PILE_KEY, ModItems.NEUTRON_PILE, Rarity.UNCOMMON, "中子堆");
		assertRegisteredMaterial(helper, ModItems.NEUTRON_NUGGET_KEY, ModItems.NEUTRON_NUGGET, Rarity.RARE, "中子粒");
		assertRegisteredMaterial(helper, ModItems.NEUTRON_INGOT_KEY, ModItems.NEUTRON_INGOT, Rarity.EPIC, "中子锭");
		helper.succeed();
	}

	@GameTest
	public void crystalMatrixBlockWorks(GameTestHelper helper) {
		helper.assertTrue(
				BuiltInRegistries.BLOCK.getValue(ModBlocks.CRYSTAL_MATRIX_KEY) == ModBlocks.CRYSTAL_MATRIX,
				"晶态矩阵块没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModBlocks.CRYSTAL_MATRIX_ITEM_KEY) == ModBlocks.CRYSTAL_MATRIX_ITEM,
				"晶态矩阵块物品没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				Block.byItem(ModBlocks.CRYSTAL_MATRIX_ITEM) == ModBlocks.CRYSTAL_MATRIX,
				"晶态矩阵块物品没有关联到方块"
		);
		ItemStack blockStack = new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM);
		helper.assertTrue(blockStack.getRarity() == Rarity.RARE, "晶态矩阵块物品应当是 RARE 稀有度");
		helper.assertTrue(
				blockStack.has(DataComponents.DAMAGE_RESISTANT),
				"晶态矩阵块物品应当具有防火伤害抗性组件"
		);

		BlockPos relativePos = new BlockPos(1, 0, 0);
		helper.setBlock(relativePos, ModBlocks.CRYSTAL_MATRIX);
		helper.assertBlockPresent(ModBlocks.CRYSTAL_MATRIX, relativePos);
		helper.assertTrue(
				helper.getBlockState(relativePos).getLightEmission() == 11,
				"晶态矩阵块光照等级应当是 11"
		);
		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos),
				helper.getLevel(),
				helper.absolutePos(relativePos),
				null
		);
		helper.assertTrue(
				drops.size() == 1 && drops.getFirst().is(ModBlocks.CRYSTAL_MATRIX_ITEM),
				"晶态矩阵块应当掉落自身"
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

	@GameTest
	public void neutronMaterialConversionRecipesWork(GameTestHelper helper) {
		assertCraftingRecipe(
				helper,
				"neutron_pile_from_nugget",
				CraftingInput.of(1, 1, List.of(new ItemStack(ModItems.NEUTRON_NUGGET))),
				ModItems.NEUTRON_PILE,
				9
		);
		assertCraftingRecipe(
				helper,
				"neutron_nugget_from_piles",
				filledCraftingInput(ModItems.NEUTRON_PILE),
				ModItems.NEUTRON_NUGGET,
				1
		);
		assertCraftingRecipe(
				helper,
				"neutron_nugget_from_ingot",
				CraftingInput.of(1, 1, List.of(new ItemStack(ModItems.NEUTRON_INGOT))),
				ModItems.NEUTRON_NUGGET,
				9
		);
		assertCraftingRecipe(
				helper,
				"neutron_ingot_from_nuggets",
				filledCraftingInput(ModItems.NEUTRON_NUGGET),
				ModItems.NEUTRON_INGOT,
				1
		);
		helper.succeed();
	}

	@Override
	public void invokeTestMethod(GameTestHelper helper, Method method) throws ReflectiveOperationException {
		helper.setBlock(0, 0, 0, Blocks.AIR);
		method.invoke(this, helper);
	}

	private static void assertRegisteredMaterial(
			GameTestHelper helper,
			ResourceKey<Item> key,
			Item item,
			Rarity rarity,
			String name
	) {
		ItemStack stack = new ItemStack(item);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(key) == item, name + "没有注册到预期的 ResourceKey");
		helper.assertTrue(stack.is(item), name + " ItemStack 未指向注册物品");
		helper.assertTrue(item.getDefaultMaxStackSize() == 64, name + "应当可以堆叠 64 个");
		helper.assertTrue(stack.getRarity() == rarity, name + "稀有度错误");
	}

	private static CraftingInput filledCraftingInput(Item item) {
		return CraftingInput.of(3, 3, List.of(
				new ItemStack(item), new ItemStack(item), new ItemStack(item),
				new ItemStack(item), new ItemStack(item), new ItemStack(item),
				new ItemStack(item), new ItemStack(item), new ItemStack(item)
		));
	}

	private static void assertCraftingRecipe(
			GameTestHelper helper,
			String path,
			CraftingInput input,
			Item expectedItem,
			int expectedCount
	) {
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id(path));
		RecipeHolder<CraftingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(RecipeType.CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("未匹配配方 " + path));
		helper.assertTrue(recipe.id().equals(recipeKey), "输入匹配到了错误配方 " + path);
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(expectedItem), path + " 输出了错误物品");
		helper.assertTrue(result.getCount() == expectedCount, path + " 输出数量错误");
	}
}
