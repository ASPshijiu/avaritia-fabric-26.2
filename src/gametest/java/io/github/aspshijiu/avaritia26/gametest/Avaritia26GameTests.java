package io.github.aspshijiu.avaritia26.gametest;

import java.lang.reflect.Method;
import java.util.List;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.block.entity.ExtremeCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.crafting.ModRecipes;
import io.github.aspshijiu.avaritia26.inventory.ExtremeCraftingMenu;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.gametest.v1.CustomTestMethodInvoker;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;

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
	public void recordFragmentRecipeWorks(GameTestHelper helper) {
		assertRegisteredMaterial(
				helper,
				ModItems.RECORD_FRAGMENT_KEY,
				ModItems.RECORD_FRAGMENT,
				Rarity.RARE,
				"唱片碎片"
		);
		CraftingInput input = CraftingInput.of(3, 3, List.of(
				ItemStack.EMPTY, new ItemStack(ModItems.NEUTRON_PILE), ItemStack.EMPTY,
				new ItemStack(ModItems.NEUTRON_PILE), new ItemStack(Items.MUSIC_DISC_13),
				new ItemStack(ModItems.NEUTRON_PILE),
				ItemStack.EMPTY, new ItemStack(ModItems.NEUTRON_PILE), ItemStack.EMPTY
		));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("record_fragment"));
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("正确唱片材料未匹配唱片碎片配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "唱片碎片材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(
				result.is(ModItems.RECORD_FRAGMENT) && result.getCount() == 4,
				"唱片碎片配方应当输出 4 个"
		);

		List<ItemStack> wrongStacks = new java.util.ArrayList<>(input.items());
		wrongStacks.set(4, new ItemStack(Items.DIRT));
		CraftingInput wrongInput = CraftingInput.of(3, 3, wrongStacks);
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, wrongInput, helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"非唱片物品不应匹配唱片碎片配方"
		);
		helper.succeed();
	}

	@GameTest
	public void ultimateStewRecipeAndEffectsWork(GameTestHelper helper) {
		assertRegisteredMaterial(
				helper,
				ModItems.ULTIMATE_STEW_KEY,
				ModItems.ULTIMATE_STEW,
				Rarity.EPIC,
				"终极炖菜"
		);
		ItemStack stack = new ItemStack(ModItems.ULTIMATE_STEW);
		FoodProperties food = stack.get(DataComponents.FOOD);
		Consumable consumable = stack.get(DataComponents.CONSUMABLE);
		helper.assertTrue(food != null, "终极炖菜缺少食物组件");
		helper.assertTrue(consumable != null, "终极炖菜缺少可食用组件");
		helper.assertTrue(food.nutrition() == 20, "终极炖菜应当恢复 20 点饥饿值");
		helper.assertTrue(food.saturation() == 800.0F, "终极炖菜饱和度应当保持上游的 20 倍修正值");
		helper.assertTrue(food.canAlwaysEat(), "终极炖菜应当允许满饥饿值食用");
		helper.assertTrue(consumable.consumeSeconds() == 0.8F, "终极炖菜食用时间应当是 0.8 秒");
		helper.assertTrue(consumable.onConsumeEffects().size() == 1, "终极炖菜应当配置一组状态效果");

		CraftingInput input = ultimateStewInput();
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("ultimate_stew"));
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("27 种正确材料未匹配终极炖菜配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "终极炖菜材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModItems.ULTIMATE_STEW) && result.getCount() == 1, "终极炖菜配方输出错误");

		List<ItemStack> wrongStacks = new java.util.ArrayList<>(input.items());
		wrongStacks.set(0, new ItemStack(Items.DIRT));
		CraftingInput wrongInput = CraftingInput.of(9, 3, wrongStacks);
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, wrongInput, helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"终极炖菜配方不应接受错误材料"
		);

		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		ItemStack consumed = consumable.onConsume(helper.getLevel(), player, stack.copy());
		helper.assertTrue(consumed.isEmpty(), "食用一份终极炖菜后物品应当被消耗");
		assertEffect(helper, player, MobEffects.STRENGTH, 5 * 60 * 20, 4, "力量");
		assertEffect(helper, player, MobEffects.HASTE, 3 * 60 * 20, 2, "急迫");
		assertEffect(helper, player, MobEffects.SPEED, 3 * 60 * 20, 2, "速度");
		assertEffect(helper, player, MobEffects.JUMP_BOOST, 3 * 60 * 20, 2, "跳跃提升");
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
	public void crystalMatrixStorageRecipesWork(GameTestHelper helper) {
		assertCraftingRecipe(
				helper,
				"crystal_matrix",
				filledCraftingInput(ModItems.CRYSTAL_MATRIX_INGOT),
				ModBlocks.CRYSTAL_MATRIX_ITEM,
				1
		);
		assertCraftingRecipe(
				helper,
				"crystal_matrix_ingot_from_block",
				CraftingInput.of(1, 1, List.of(new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM))),
				ModItems.CRYSTAL_MATRIX_INGOT,
				9
		);
		helper.succeed();
	}

	@GameTest
	public void neutronBlockWorks(GameTestHelper helper) {
		helper.assertTrue(
				BuiltInRegistries.BLOCK.getValue(ModBlocks.NEUTRON_KEY) == ModBlocks.NEUTRON,
				"中子块没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModBlocks.NEUTRON_ITEM_KEY) == ModBlocks.NEUTRON_ITEM,
				"中子块物品没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(Block.byItem(ModBlocks.NEUTRON_ITEM) == ModBlocks.NEUTRON, "中子块物品没有关联到方块");
		ItemStack blockStack = new ItemStack(ModBlocks.NEUTRON_ITEM);
		helper.assertTrue(blockStack.getRarity() == Rarity.EPIC, "中子块物品应当是 EPIC 稀有度");
		helper.assertTrue(blockStack.has(DataComponents.DAMAGE_RESISTANT), "中子块物品应当具有防火伤害抗性组件");

		BlockPos relativePos = new BlockPos(2, 0, 0);
		helper.setBlock(relativePos, ModBlocks.NEUTRON);
		helper.assertBlockPresent(ModBlocks.NEUTRON, relativePos);
		helper.assertTrue(helper.getBlockState(relativePos).getLightEmission() == 13, "中子块光照等级应当是 13");
		helper.assertTrue(ModBlocks.NEUTRON.getExplosionResistance() == 8888.0F, "中子块爆炸抗性应当是 8888");
		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos),
				helper.getLevel(),
				helper.absolutePos(relativePos),
				null
		);
		helper.assertTrue(
				drops.size() == 1 && drops.getFirst().is(ModBlocks.NEUTRON_ITEM),
				"中子块应当掉落自身"
		);
		helper.succeed();
	}

	@GameTest
	public void neutronStorageRecipesWork(GameTestHelper helper) {
		assertCraftingRecipe(
				helper,
				"neutron",
				filledCraftingInput(ModItems.NEUTRON_INGOT),
				ModBlocks.NEUTRON_ITEM,
				1
		);
		assertCraftingRecipe(
				helper,
				"neutron_ingot_from_block",
				CraftingInput.of(1, 1, List.of(new ItemStack(ModBlocks.NEUTRON_ITEM))),
				ModItems.NEUTRON_INGOT,
				9
		);
		helper.succeed();
	}

	@GameTest
	public void compressedCraftingTablesWork(GameTestHelper helper) {
		assertCraftingTableBlock(
				helper,
				new BlockPos(3, 0, 0),
				ModBlocks.COMPRESSED_CRAFTING_TABLE_KEY,
				ModBlocks.COMPRESSED_CRAFTING_TABLE,
				ModBlocks.COMPRESSED_CRAFTING_TABLE_ITEM_KEY,
				ModBlocks.COMPRESSED_CRAFTING_TABLE_ITEM,
				100.0F,
				"压缩工作台"
		);
		assertCraftingTableBlock(
				helper,
				new BlockPos(4, 0, 0),
				ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_KEY,
				ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE,
				ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM_KEY,
				ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM,
				500.0F,
				"二重压缩工作台"
		);
		helper.succeed();
	}

	@GameTest
	public void compressedCraftingTableRecipesWork(GameTestHelper helper) {
		assertCraftingRecipe(
				helper,
				"compressed_crafting_table",
				filledCraftingInput(Items.CRAFTING_TABLE),
				ModBlocks.COMPRESSED_CRAFTING_TABLE_ITEM,
				1
		);
		assertCraftingRecipe(
				helper,
				"crafting_table_from_compressed",
				CraftingInput.of(1, 1, List.of(new ItemStack(ModBlocks.COMPRESSED_CRAFTING_TABLE_ITEM))),
				Items.CRAFTING_TABLE,
				9
		);
		assertCraftingRecipe(
				helper,
				"double_compressed_crafting_table",
				filledCraftingInput(ModBlocks.COMPRESSED_CRAFTING_TABLE_ITEM),
				ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM,
				1
		);
		assertCraftingRecipe(
				helper,
				"compressed_crafting_table_from_double",
				CraftingInput.of(1, 1, List.of(new ItemStack(ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM))),
				ModBlocks.COMPRESSED_CRAFTING_TABLE_ITEM,
				9
		);
		helper.succeed();
	}

	@GameTest
	public void extremeShapedRecipeSupportsNineByNine(GameTestHelper helper) {
		List<ItemStack> stacks = new java.util.ArrayList<>(java.util.Collections.nCopies(81, ItemStack.EMPTY));
		stacks.set(0, new ItemStack(Items.DIAMOND));
		stacks.set(8, new ItemStack(Items.DIAMOND));
		stacks.set(40, new ItemStack(Items.NETHERITE_SCRAP));
		stacks.set(72, new ItemStack(Items.DIAMOND));
		stacks.set(80, new ItemStack(Items.DIAMOND));
		CraftingInput input = CraftingInput.of(9, 9, stacks);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Identifier.fromNamespaceAndPath("avaritia26-gametest", "extreme_shaped_test")
		);
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("9x9 有序测试配方未匹配"));
		helper.assertTrue(recipe.id().equals(recipeKey), "9x9 输入匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModItems.DIAMOND_LATTICE) && result.getCount() == 1, "9x9 配方输出错误");

		stacks.set(1, new ItemStack(Items.DIAMOND));
		CraftingInput extraInput = CraftingInput.of(9, 9, stacks);
		boolean extraMatches = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, extraInput, helper.getLevel())
				.isPresent();
		helper.assertFalse(extraMatches, "9x9 有序配方不应接受额外材料");
		helper.succeed();
	}

	@GameTest
	public void extremeShapelessRecipeSupportsMoreThanNineIngredients(GameTestHelper helper) {
		CraftingInput input = CraftingInput.of(5, 2, List.of(
				new ItemStack(Items.COCOA_BEANS), new ItemStack(Items.KELP), new ItemStack(Items.SUGAR_CANE),
				new ItemStack(Items.BAMBOO), new ItemStack(Items.CACTUS),
				new ItemStack(Items.WHEAT), new ItemStack(Items.BEETROOT), new ItemStack(Items.POTATO),
				new ItemStack(Items.CARROT), new ItemStack(Items.APPLE)
		));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Identifier.fromNamespaceAndPath("avaritia26-gametest", "extreme_shapeless_test")
		);
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("10 种材料未匹配终极无序配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "终极无序材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModItems.DIAMOND_LATTICE), "终极无序配方输出错误");

		List<ItemStack> extraStacks = new java.util.ArrayList<>(input.items());
		extraStacks.add(new ItemStack(Items.DIRT));
		extraStacks.add(ItemStack.EMPTY);
		CraftingInput extraInput = CraftingInput.of(4, 3, extraStacks);
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, extraInput, helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"终极无序配方不应接受额外材料"
		);
		helper.succeed();
	}

	@GameTest
	public void extremeCraftingTableRecipeWorks(GameTestHelper helper) {
		CraftingInput input = extremeCraftingTableInput();
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("extreme_crafting_table")
		);
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("正确材料未匹配终极工作台配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "终极工作台材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(
				result.is(ModBlocks.EXTREME_CRAFTING_TABLE_ITEM) && result.getCount() == 1,
				"终极工作台配方输出错误"
		);

		List<ItemStack> padded = new java.util.ArrayList<>(java.util.Collections.nCopies(81, ItemStack.EMPTY));
		for (int row = 0; row < 7; row++) {
			for (int column = 0; column < 7; column++) {
				padded.set(column + 1 + (row + 1) * 9, input.getItem(column, row));
			}
		}
		CraftingInput positioned = CraftingInput.ofPositioned(9, 9, padded).input();
		helper.assertTrue(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, positioned, helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"终极工作台配方放在 9x9 网格中央时未匹配"
		);

		padded.set(0, new ItemStack(Items.DIRT));
		CraftingInput withExtraItem = CraftingInput.ofPositioned(9, 9, padded).input();
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, withExtraItem, helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"终极工作台配方不应接受网格外的额外材料"
		);
		helper.succeed();
	}

	@GameTest
	public void extremeCraftingTableStoresAndDropsGrid(GameTestHelper helper) {
		helper.assertTrue(
				BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(Avaritia26.id("extreme_crafting_table"))
						== ModBlockEntities.EXTREME_CRAFTING_TABLE,
				"终极工作台方块实体未正确注册"
		);
		BlockPos relativePos = new BlockPos(5, 0, 0);
		helper.setBlock(relativePos, ModBlocks.EXTREME_CRAFTING_TABLE);
		helper.assertBlockPresent(ModBlocks.EXTREME_CRAFTING_TABLE, relativePos);
		ExtremeCraftingTableBlockEntity blockEntity = helper.getBlockEntity(
				relativePos,
				ExtremeCraftingTableBlockEntity.class
		);
		helper.assertTrue(blockEntity.getContainerSize() == 81, "终极工作台必须保存 81 个输入槽");
		blockEntity.setItem(0, new ItemStack(Items.DIAMOND, 5));
		blockEntity.setItem(80, new ItemStack(Items.NETHERITE_SCRAP, 2));

		BlockEntity loaded = BlockEntity.loadStatic(
				helper.absolutePos(relativePos),
				helper.getBlockState(relativePos),
				blockEntity.saveWithFullMetadata(helper.getLevel().registryAccess()),
				helper.getLevel().registryAccess()
		);
		helper.assertTrue(loaded instanceof ExtremeCraftingTableBlockEntity, "终极工作台方块实体无法从存档恢复");
		ExtremeCraftingTableBlockEntity loadedTable = (ExtremeCraftingTableBlockEntity) loaded;
		helper.assertTrue(
				loadedTable.getItem(0).is(Items.DIAMOND) && loadedTable.getItem(0).getCount() == 5,
				"终极工作台第一个槽位存档错误"
		);
		helper.assertTrue(
				loadedTable.getItem(80).is(Items.NETHERITE_SCRAP) && loadedTable.getItem(80).getCount() == 2,
				"终极工作台最后一个槽位存档错误"
		);

		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos),
				helper.getLevel(),
				helper.absolutePos(relativePos),
				blockEntity
		);
		helper.assertTrue(
				drops.size() == 1 && drops.getFirst().is(ModBlocks.EXTREME_CRAFTING_TABLE_ITEM),
				"终极工作台应当掉落自身"
		);
		helper.destroyBlock(relativePos);
		helper.assertItemEntityPresent(Items.DIAMOND, relativePos, 2.0);
		helper.assertItemEntityPresent(Items.NETHERITE_SCRAP, relativePos, 2.0);
		helper.succeed();
	}

	@GameTest
	public void extremeCraftingMenuCraftsAndConsumesOnce(GameTestHelper helper) {
		BlockPos relativePos = new BlockPos(6, 0, 0);
		helper.setBlock(relativePos, ModBlocks.EXTREME_CRAFTING_TABLE);
		ExtremeCraftingTableBlockEntity blockEntity = helper.getBlockEntity(
				relativePos,
				ExtremeCraftingTableBlockEntity.class
		);
		blockEntity.setItem(0, new ItemStack(Items.DIAMOND));
		blockEntity.setItem(8, new ItemStack(Items.DIAMOND));
		blockEntity.setItem(40, new ItemStack(Items.NETHERITE_SCRAP));
		blockEntity.setItem(72, new ItemStack(Items.DIAMOND));
		blockEntity.setItem(80, new ItemStack(Items.DIAMOND));
		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		ExtremeCraftingMenu menu = (ExtremeCraftingMenu) blockEntity.createMenu(
				1,
				player.getInventory(),
				player
		);
		helper.assertTrue(menu.slots.size() == 118, "终极工作台菜单槽位总数错误");
		ItemStack result = menu.getSlot(ExtremeCraftingMenu.RESULT_SLOT).getItem();
		helper.assertTrue(result.is(ModItems.DIAMOND_LATTICE) && result.getCount() == 1, "菜单未显示 9x9 配方产物");

		ItemStack taken = menu.getSlot(ExtremeCraftingMenu.RESULT_SLOT).remove(result.getCount());
		menu.getSlot(ExtremeCraftingMenu.RESULT_SLOT).onTake(player, taken);
		helper.assertTrue(taken.is(ModItems.DIAMOND_LATTICE) && taken.getCount() == 1, "取出菜单产物错误");
		for (int slot : List.of(0, 8, 40, 72, 80)) {
			helper.assertTrue(blockEntity.getItem(slot).isEmpty(), "终极工作台没有准确消耗一次材料");
		}
		helper.assertTrue(menu.getSlot(ExtremeCraftingMenu.RESULT_SLOT).getItem().isEmpty(), "材料耗尽后产物槽未清空");

		for (int slot : List.of(0, 8, 72, 80)) {
			menu.getSlot(slot + ExtremeCraftingMenu.INPUT_SLOT_START).set(new ItemStack(Items.DIAMOND));
		}
		menu.getSlot(40 + ExtremeCraftingMenu.INPUT_SLOT_START).set(new ItemStack(Items.NETHERITE_SCRAP));
		ExtremeCraftingMenu secondMenu = (ExtremeCraftingMenu) blockEntity.createMenu(
				2,
				player.getInventory(),
				player
		);
		secondMenu.getSlot(1 + ExtremeCraftingMenu.INPUT_SLOT_START).set(new ItemStack(Items.DIAMOND));
		helper.assertTrue(
				menu.quickMoveStack(player, ExtremeCraftingMenu.RESULT_SLOT).isEmpty(),
				"另一个菜单修改输入后不应允许取出过期产物"
		);
		helper.assertFalse(blockEntity.isEmpty(), "拒绝过期产物时不应消耗任何材料");
		secondMenu.getSlot(1 + ExtremeCraftingMenu.INPUT_SLOT_START).set(ItemStack.EMPTY);
		ItemStack shifted = menu.quickMoveStack(player, ExtremeCraftingMenu.RESULT_SLOT);
		helper.assertTrue(shifted.is(ModItems.DIAMOND_LATTICE), "shift-click 返回了错误的终极合成产物");
		helper.assertTrue(
				player.getInventory().contains(new ItemStack(ModItems.DIAMOND_LATTICE)),
				"shift-click 未把终极合成产物移入玩家背包"
		);
		helper.assertTrue(blockEntity.isEmpty(), "shift-click 没有准确消耗一次材料");
		secondMenu.removed(player);
		menu.removed(player);
		helper.assertTrue(blockEntity.isEmpty(), "关闭终极工作台菜单后输入状态异常");
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

	private static CraftingInput extremeCraftingTableInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(49);
		for (String row : List.of(
				"bccfccb",
				"cddgddc",
				"cdihidc",
				"cdiaidc",
				"cdjkjdc",
				"cdddddc",
				"beeeeeb"
		)) {
			for (char symbol : row.toCharArray()) {
				Item item = switch (symbol) {
					case 'a' -> ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM;
					case 'b' -> Items.LODESTONE;
					case 'c' -> ModItems.DIAMOND_LATTICE;
					case 'd' -> ModItems.CRYSTAL_MATRIX_INGOT;
					case 'e' -> ModBlocks.CRYSTAL_MATRIX_ITEM;
					case 'f' -> Items.RECOVERY_COMPASS;
					case 'g' -> Items.DRAGON_EGG;
					case 'h' -> Items.BEACON;
					case 'i' -> Items.REINFORCED_DEEPSLATE;
					case 'j' -> Items.NETHERITE_BLOCK;
					case 'k' -> Items.HEART_OF_THE_SEA;
					default -> throw new IllegalArgumentException("未知终极工作台配方符号: " + symbol);
				};
				stacks.add(new ItemStack(item));
			}
		}
		return CraftingInput.of(7, 7, stacks);
	}

	private static CraftingInput ultimateStewInput() {
		return CraftingInput.of(9, 3, List.of(
				new ItemStack(Items.APPLE), new ItemStack(Items.GOLDEN_APPLE), new ItemStack(Items.MELON_SLICE),
				new ItemStack(Items.GLISTERING_MELON_SLICE), new ItemStack(Items.SWEET_BERRIES),
				new ItemStack(Items.CHORUS_FRUIT), new ItemStack(Items.CARROT), new ItemStack(Items.GOLDEN_CARROT),
				new ItemStack(Items.POTATO), new ItemStack(Items.POISONOUS_POTATO), new ItemStack(Items.BEETROOT),
				new ItemStack(Items.KELP), new ItemStack(Items.NETHER_WART), new ItemStack(Items.COCOA_BEANS),
				new ItemStack(Items.PITCHER_POD), new ItemStack(Items.HONEY_BOTTLE), new ItemStack(Items.CACTUS),
				new ItemStack(Items.BAMBOO), new ItemStack(Items.SUGAR_CANE), new ItemStack(Items.SEA_PICKLE),
				new ItemStack(Items.BROWN_MUSHROOM), new ItemStack(Items.RED_MUSHROOM),
				new ItemStack(Items.CRIMSON_FUNGUS), new ItemStack(Items.WARPED_FUNGUS), new ItemStack(Items.WHEAT),
				new ItemStack(Items.PUMPKIN), new ItemStack(ModItems.NEUTRON_NUGGET)
		));
	}

	private static void assertEffect(
			GameTestHelper helper,
			Player player,
			net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> effect,
			int duration,
			int amplifier,
			String name
	) {
		MobEffectInstance instance = player.getEffect(effect);
		helper.assertTrue(instance != null, "食用终极炖菜后缺少" + name + "效果");
		helper.assertTrue(instance.getDuration() == duration, name + "效果持续时间错误");
		helper.assertTrue(instance.getAmplifier() == amplifier, name + "效果等级错误");
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

	private static void assertCraftingTableBlock(
			GameTestHelper helper,
			BlockPos relativePos,
			ResourceKey<Block> blockKey,
			Block block,
			ResourceKey<Item> itemKey,
			Item item,
			float explosionResistance,
			String name
	) {
		helper.assertTrue(BuiltInRegistries.BLOCK.getValue(blockKey) == block, name + "方块注册错误");
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(itemKey) == item, name + "物品注册错误");
		helper.assertTrue(Block.byItem(item) == block, name + "物品没有关联到方块");
		helper.assertTrue(block instanceof CraftingTableBlock, name + "不是原版 3x3 合成台类型");
		helper.assertTrue(new ItemStack(item).getRarity() == Rarity.UNCOMMON, name + "稀有度错误");
		helper.assertTrue(block.getExplosionResistance() == explosionResistance, name + "爆炸抗性错误");

		helper.setBlock(relativePos, block);
		helper.assertBlockPresent(block, relativePos);
		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos),
				helper.getLevel(),
				helper.absolutePos(relativePos),
				null
		);
		helper.assertTrue(drops.size() == 1 && drops.getFirst().is(item), name + "应当掉落自身");

		MenuProvider provider = helper.getBlockState(relativePos).getMenuProvider(
				helper.getLevel(),
				helper.absolutePos(relativePos)
		);
		helper.assertTrue(provider != null, name + "没有菜单提供器");
		Player player = helper.makeMockPlayer(GameType.CREATIVE);
		AbstractContainerMenu menu = provider.createMenu(1, player.getInventory(), player);
		helper.assertTrue(menu instanceof CraftingMenu, name + "没有创建 3x3 合成界面");
	}
}
