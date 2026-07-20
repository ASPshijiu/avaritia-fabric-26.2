package io.github.aspshijiu.avaritia26.gametest;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.mojang.serialization.JsonOps;
import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.block.NeutronCollectorBlock;
import io.github.aspshijiu.avaritia26.block.entity.ExtremeCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.NeutronCollectorBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.NeutronCompressorBlockEntity;
import io.github.aspshijiu.avaritia26.crafting.ModRecipes;
import io.github.aspshijiu.avaritia26.entity.EndestPearlEntity;
import io.github.aspshijiu.avaritia26.entity.GapingVoidEntity;
import io.github.aspshijiu.avaritia26.inventory.ExtremeCraftingMenu;
import io.github.aspshijiu.avaritia26.inventory.NeutronCollectorMenu;
import io.github.aspshijiu.avaritia26.inventory.NeutronCompressorMenu;
import io.github.aspshijiu.avaritia26.item.MatterClusterItem;
import io.github.aspshijiu.avaritia26.item.SingularityItem;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import io.github.aspshijiu.avaritia26.singularity.SingularityDefinition;
import io.github.aspshijiu.avaritia26.singularity.SingularityManager;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.gametest.v1.CustomTestMethodInvoker;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.UseCooldown;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public final class Avaritia26GameTests implements CustomTestMethodInvoker {
	@GameTest
	public void modStartsOnServer(GameTestHelper helper) {
		helper.assertBlockPresent(Blocks.AIR, 0, 0, 0);
		helper.succeed();
	}

	@GameTest
	public void builtInSingularitiesLoadAndRoundTrip(GameTestHelper helper) {
		Map<String, ExpectedSingularity> expected = Map.ofEntries(
				Map.entry("obsidian", new ExpectedSingularity(Items.OBSIDIAN, 3_876_692, 1_051_676)),
				Map.entry("blue_ice", new ExpectedSingularity(Items.BLUE_ICE, 9_353_470, 7_052_795)),
				Map.entry("coal", new ExpectedSingularity(Items.COAL, 3_553_081, 2_498_084)),
				Map.entry("copper", new ExpectedSingularity(Items.COPPER_INGOT, 16_422_780, 12_342_320)),
				Map.entry("iron", new ExpectedSingularity(Items.IRON_INGOT, 14_803_425, 7_105_644)),
				Map.entry("lapis_lazuli", new ExpectedSingularity(Items.LAPIS_LAZULI, 6_786_538, 1_790_887)),
				Map.entry("redstone", new ExpectedSingularity(Items.REDSTONE, 16_711_680, 9_046_273)),
				Map.entry("glowstone", new ExpectedSingularity(Items.GLOWSTONE_DUST, 16_765_839, 10_510_645)),
				Map.entry("gold", new ExpectedSingularity(Items.GOLD_INGOT, 16_643_423, 14_257_668)),
				Map.entry("diamond", new ExpectedSingularity(Items.DIAMOND, 10_943_721, 1_748_136)),
				Map.entry("emerald", new ExpectedSingularity(Items.EMERALD, 8_255_660, 36_378)),
				Map.entry("quartz", new ExpectedSingularity(Items.QUARTZ, 15_394_270, 11_969_678)),
				Map.entry("amethyst_shard", new ExpectedSingularity(Items.AMETHYST_SHARD, 13_607_155, 9_267_916)),
				Map.entry("netherite", new ExpectedSingularity(Items.NETHERITE_INGOT, 4_471_355, 1_709_590))
		);

		helper.assertTrue(SingularityManager.values().size() == expected.size(), "内置奇点定义数量错误");
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.SINGULARITY_KEY) == ModItems.SINGULARITY,
				"奇点共用物品没有注册"
		);
		helper.assertTrue(ModItems.SINGULARITY.getDefaultMaxStackSize() == 64, "奇点应当可以堆叠 64 个");

		expected.forEach((path, values) -> assertSingularity(helper, path, values));
		helper.succeed();
	}

	@GameTest
	public void infinityCatalystDynamicallyRequiresEverySingularity(GameTestHelper helper) {
		ItemStack catalyst = new ItemStack(ModItems.INFINITY_CATALYST);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_CATALYST_KEY) == ModItems.INFINITY_CATALYST,
				"无尽催化剂没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(ModItems.INFINITY_CATALYST.getDefaultMaxStackSize() == 64, "无尽催化剂应当堆叠 64 个");
		helper.assertTrue(catalyst.getRarity() == Rarity.EPIC, "无尽催化剂应当是 EPIC 稀有度");
		helper.assertTrue(catalyst.has(DataComponents.DAMAGE_RESISTANT), "无尽催化剂应当具有防火伤害抗性组件");
		helper.assertTrue(
				BuiltInRegistries.RECIPE_SERIALIZER.getValue(Avaritia26.id("infinity_catalyst"))
						== ModRecipes.INFINITY_CATALYST_SERIALIZER,
				"无尽催化剂动态配方序列化器未注册"
		);

		List<ItemStack> ingredients = new java.util.ArrayList<>(List.of(
				new ItemStack(Items.BEDROCK),
				new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT),
				new ItemStack(ModItems.NEUTRON_INGOT),
				new ItemStack(ModItems.COSMIC_MEATBALLS),
				new ItemStack(ModItems.ULTIMATE_STEW),
				new ItemStack(ModItems.ENDEST_PEARL),
				new ItemStack(ModItems.RECORD_FRAGMENT)
		));
		List<SingularityDefinition> definitions = SingularityManager.values().stream()
				.filter(SingularityDefinition::recipeEnabled)
				.toList();
		helper.assertTrue(definitions.size() == 14, "无尽催化剂测试应当动态读取 14 个内置奇点");
		definitions.stream().map(SingularityItem::createStack).forEach(ingredients::add);

		CraftingInput input = CraftingInput.of(7, 3, ingredients);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("infinity_catalyst")
		);
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("七种固定材料和全部奇点未匹配无尽催化剂配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "无尽催化剂材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModItems.INFINITY_CATALYST) && result.getCount() == 1, "无尽催化剂输出错误");

		assertInfinityCatalystRejected(helper, recipeKey, ingredients.subList(0, ingredients.size() - 1), "缺少奇点");

		List<ItemStack> duplicateSingularity = copyStacks(ingredients);
		duplicateSingularity.set(duplicateSingularity.size() - 1, duplicateSingularity.get(7).copy());
		assertInfinityCatalystRejected(helper, recipeKey, duplicateSingularity, "重复奇点");

		List<ItemStack> duplicateFixed = copyStacks(ingredients);
		duplicateFixed.set(1, duplicateFixed.getFirst().copy());
		assertInfinityCatalystRejected(helper, recipeKey, duplicateFixed, "重复固定材料");

		List<ItemStack> staleSingularity = copyStacks(ingredients);
		SingularityDefinition original = definitions.getFirst();
		SingularityDefinition tampered = new SingularityDefinition(
				original.name(),
				original.displayName() + ".tampered",
				original.overlayColor(),
				original.underlayColor(),
				original.count(),
				original.timeCost(),
				original.ingredient(),
				original.enabled(),
				original.recipeEnabled()
		);
		staleSingularity.set(7, SingularityItem.createStack(tampered));
		assertInfinityCatalystRejected(helper, recipeKey, staleSingularity, "失效奇点快照");

		List<ItemStack> extraIngredient = copyStacks(ingredients);
		extraIngredient.add(new ItemStack(Items.DIRT));
		assertInfinityCatalystRejected(helper, recipeKey, extraIngredient, "额外材料");
		helper.succeed();
	}

	@GameTest
	public void infinityIngotAndBlockResourceChainWorks(GameTestHelper helper) {
		ItemStack ingot = new ItemStack(ModItems.INFINITY_INGOT);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_INGOT_KEY) == ModItems.INFINITY_INGOT,
				"无尽锭没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(ModItems.INFINITY_INGOT.getDefaultMaxStackSize() == 64, "无尽锭应当堆叠 64 个");
		helper.assertTrue(ingot.getRarity() == Rarity.EPIC, "无尽锭应当是 EPIC 稀有度");
		helper.assertTrue(ingot.has(DataComponents.DAMAGE_RESISTANT), "无尽锭应当具有防火伤害抗性组件");

		CraftingInput ingotInput = infinityIngotInput();
		assertExtremeRecipe(helper, "infinity_ingot", ingotInput, ModItems.INFINITY_INGOT);
		List<ItemStack> wrongIngotInput = copyStacks(ingotInput.items());
		wrongIngotInput.set(2, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> ingotRecipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("infinity_ingot")
		);
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(
								ModRecipes.EXTREME_CRAFTING,
								CraftingInput.of(9, 5, wrongIngotInput),
								helper.getLevel()
						)
						.filter(candidate -> candidate.id().equals(ingotRecipeKey))
						.isPresent(),
				"无尽锭配方不应接受错误边框材料"
		);

		helper.assertTrue(
				BuiltInRegistries.BLOCK.getValue(ModBlocks.INFINITY_KEY) == ModBlocks.INFINITY,
				"无尽块没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModBlocks.INFINITY_ITEM_KEY) == ModBlocks.INFINITY_ITEM,
				"无尽块物品没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(Block.byItem(ModBlocks.INFINITY_ITEM) == ModBlocks.INFINITY, "无尽块物品没有关联到方块");
		ItemStack blockStack = new ItemStack(ModBlocks.INFINITY_ITEM);
		helper.assertTrue(blockStack.getRarity() == Rarity.EPIC, "无尽块物品应当是 EPIC 稀有度");
		helper.assertTrue(blockStack.has(DataComponents.DAMAGE_RESISTANT), "无尽块物品应当具有防火伤害抗性组件");

		BlockPos relativePos = new BlockPos(15, 0, 0);
		helper.setBlock(relativePos, ModBlocks.INFINITY);
		helper.assertBlockPresent(ModBlocks.INFINITY, relativePos);
		helper.assertTrue(helper.getBlockState(relativePos).getLightEmission() == 15, "无尽块光照等级应当是 15");
		helper.assertTrue(ModBlocks.INFINITY.getExplosionResistance() == 9999.0F, "无尽块爆炸抗性应当是 9999");
		helper.assertTrue(
				helper.getBlockState(relativePos).getDestroySpeed(helper.getLevel(), helper.absolutePos(relativePos))
						== 9999.0F,
				"无尽块硬度应当是 9999"
		);
		helper.assertTrue(helper.getBlockState(relativePos).is(BlockTags.MINEABLE_WITH_PICKAXE), "无尽块应当可用镐挖掘");
		helper.assertTrue(helper.getBlockState(relativePos).is(BlockTags.BEACON_BASE_BLOCKS), "无尽块应当可作为信标基座");
		helper.assertTrue(helper.getBlockState(relativePos).is(BlockTags.PORTALS), "无尽块应当保留上游传送门方块标签");
		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos),
				helper.getLevel(),
				helper.absolutePos(relativePos),
				null
		);
		helper.assertTrue(drops.size() == 1 && drops.getFirst().is(ModBlocks.INFINITY_ITEM), "无尽块应当掉落自身");

		assertCraftingRecipe(
				helper,
				"infinity",
				filledCraftingInput(ModItems.INFINITY_INGOT),
				ModBlocks.INFINITY_ITEM,
				1
		);
		assertCraftingRecipe(
				helper,
				"infinity_ingot_from_block",
				CraftingInput.of(1, 1, List.of(new ItemStack(ModBlocks.INFINITY_ITEM))),
				ModItems.INFINITY_INGOT,
				9
		);
		helper.succeed();
	}

	@GameTest
	public void blazeCubeResourceChainWorks(GameTestHelper helper) {
		assertRegisteredMaterial(helper, ModItems.BLAZE_CUBE_KEY, ModItems.BLAZE_CUBE, Rarity.UNCOMMON, "炽骨立方");
		ItemStack cubeStack = new ItemStack(ModItems.BLAZE_CUBE);
		helper.assertTrue(cubeStack.has(DataComponents.DAMAGE_RESISTANT), "炽骨立方应当具有防火伤害抗性组件");

		CraftingInput cubeInput = blazeCubeInput();
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("blaze_cube"));
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, cubeInput, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("正确 5x5 材料未匹配炽骨立方配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "炽骨立方材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(cubeInput);
		helper.assertTrue(result.is(ModItems.BLAZE_CUBE) && result.getCount() == 2, "炽骨立方应当产出 2 个");
		List<ItemStack> wrongStacks = copyStacks(cubeInput.items());
		wrongStacks.set(12, new ItemStack(Items.DIRT));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(5, 5, wrongStacks), helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"炽骨立方中心材料错误时不应匹配配方"
		);

		helper.assertTrue(
				BuiltInRegistries.BLOCK.getValue(ModBlocks.BLAZE_CUBE_BLOCK_KEY) == ModBlocks.BLAZE_CUBE_BLOCK,
				"炽骨立方块没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModBlocks.BLAZE_CUBE_BLOCK_ITEM_KEY) == ModBlocks.BLAZE_CUBE_BLOCK_ITEM,
				"炽骨立方块物品没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(Block.byItem(ModBlocks.BLAZE_CUBE_BLOCK_ITEM) == ModBlocks.BLAZE_CUBE_BLOCK, "炽骨立方块物品没有关联到方块");
		helper.assertTrue(ModBlocks.BLAZE_CUBE_BLOCK instanceof MagmaBlock, "炽骨立方块应当使用原版岩浆块灼烧语义");
		ItemStack blockStack = new ItemStack(ModBlocks.BLAZE_CUBE_BLOCK_ITEM);
		helper.assertTrue(blockStack.getRarity() == Rarity.RARE, "炽骨立方块物品应当是 RARE 稀有度");
		helper.assertTrue(blockStack.has(DataComponents.DAMAGE_RESISTANT), "炽骨立方块物品应当具有防火伤害抗性组件");

		BlockPos relativePos = new BlockPos(5, 0, 0);
		helper.setBlock(relativePos, ModBlocks.BLAZE_CUBE_BLOCK);
		helper.assertBlockPresent(ModBlocks.BLAZE_CUBE_BLOCK, relativePos);
		helper.assertTrue(helper.getBlockState(relativePos).getLightEmission() == 9, "炽骨立方块光照等级应当是 9");
		helper.assertTrue(ModBlocks.BLAZE_CUBE_BLOCK.getExplosionResistance() == 50.0F, "炽骨立方块爆炸抗性应当是 50");
		helper.assertTrue(
				helper.getBlockState(relativePos).getDestroySpeed(helper.getLevel(), helper.absolutePos(relativePos)) == 1000.0F,
				"炽骨立方块硬度应当是 1000"
		);
		helper.assertTrue(helper.getBlockState(relativePos).is(BlockTags.MINEABLE_WITH_PICKAXE), "炽骨立方块应当可用镐挖掘");
		helper.assertTrue(
				helper.getBlockState(relativePos).is(BlockTags.ENABLES_BUBBLE_COLUMN_DRAG_DOWN),
				"炽骨立方块应当生成向下气泡柱"
		);

		var pig = helper.spawn(EntityTypes.PIG, relativePos.above());
		float initialHealth = pig.getHealth();
		ModBlocks.BLAZE_CUBE_BLOCK.stepOn(
				helper.getLevel(),
				helper.absolutePos(relativePos),
				helper.getBlockState(relativePos),
				pig
		);
		helper.assertTrue(pig.getHealth() == initialHealth - 1.0F, "炽骨立方块应当灼伤未潜行生物 1 点生命");
		pig.setHealth(initialHealth);
		pig.invulnerableTime = 0;
		pig.setShiftKeyDown(true);
		ModBlocks.BLAZE_CUBE_BLOCK.stepOn(
				helper.getLevel(),
				helper.absolutePos(relativePos),
				helper.getBlockState(relativePos),
				pig
		);
		helper.assertTrue(pig.getHealth() == initialHealth, "潜行生物不应受到炽骨立方块灼伤");

		helper.setBlock(relativePos.above(), Blocks.WATER);
		BubbleColumnBlock.updateColumn(
				Blocks.BUBBLE_COLUMN,
				helper.getLevel(),
				helper.absolutePos(relativePos.above()),
				helper.getBlockState(relativePos)
		);
		helper.assertBlockPresent(Blocks.BUBBLE_COLUMN, relativePos.above());
		helper.assertTrue(
				helper.getBlockState(relativePos.above()).getValue(BubbleColumnBlock.DRAG_DOWN),
				"炽骨立方块生成的气泡柱方向应当向下"
		);
		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos),
				helper.getLevel(),
				helper.absolutePos(relativePos),
				null
		);
		helper.assertTrue(drops.size() == 1 && drops.getFirst().is(ModBlocks.BLAZE_CUBE_BLOCK_ITEM), "炽骨立方块应当掉落自身");

		assertCraftingRecipe(helper, "blaze_cube_block", filledCraftingInput(ModItems.BLAZE_CUBE), ModBlocks.BLAZE_CUBE_BLOCK_ITEM, 1);
		assertCraftingRecipe(
				helper,
				"blaze_cube_from_block",
				CraftingInput.of(1, 1, List.of(new ItemStack(ModBlocks.BLAZE_CUBE_BLOCK_ITEM))),
				ModItems.BLAZE_CUBE,
				9
		);
		helper.succeed();
	}

	@GameTest
	public void skullFireSwordCraftsAndBeheadsSkeletons(GameTestHelper helper) {
		ItemStack sword = new ItemStack(ModItems.SKULL_FIRE_SWORD);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.SKULL_FIRE_SWORD_KEY) == ModItems.SKULL_FIRE_SWORD,
				"炽焰之啄颅剑没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(sword.getCount() == 1 && sword.getMaxStackSize() == 1, "炽焰之啄颅剑应当不可堆叠");
		helper.assertTrue(sword.getRarity() == Rarity.EPIC, "炽焰之啄颅剑应当是 EPIC 稀有度");
		helper.assertTrue(sword.getMaxDamage() == 1561, "炽焰之啄颅剑应当保留经典钻石剑耐久");
		helper.assertTrue(sword.is(ItemTags.SWORDS), "炽焰之啄颅剑缺少原版剑标签");
		var modifiers = sword.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null, "炽焰之啄颅剑缺少武器属性组件");
		helper.assertTrue(
				modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 7.0,
				"炽焰之啄颅剑主手攻击伤害应当与经典钻石剑一致"
		);
		helper.assertTrue(
				Math.abs(modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) - 1.6) < 0.0001,
				"炽焰之啄颅剑主手攻击速度应当是 1.6"
		);
		List<Component> tooltip = new java.util.ArrayList<>();
		ModItems.SKULL_FIRE_SWORD.appendHoverText(
				sword,
				Item.TooltipContext.of(helper.getLevel()),
				TooltipDisplay.DEFAULT,
				tooltip::add,
				TooltipFlag.NORMAL
		);
		helper.assertTrue(tooltip.size() == 1, "炽焰之啄颅剑缺少经典功能说明");
		helper.assertTrue(tooltip.getFirst().getStyle().isItalic(), "炽焰之啄颅剑功能说明应当使用斜体");

		CraftingInput input = skullFireSwordInput();
		assertExtremeRecipe(helper, "skull_fire_sword", input, ModItems.SKULL_FIRE_SWORD);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(40, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("skull_fire_sword"));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(9, 9, wrongStacks), helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"炽焰之啄颅剑中心材料错误时不应匹配配方"
		);

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		BlockPos playerPos = helper.absolutePos(new BlockPos(10, 1, 0));
		player.setPos(playerPos.getX() + 0.5, playerPos.getY(), playerPos.getZ() + 0.5);
		player.setItemInHand(InteractionHand.MAIN_HAND, sword);
		BlockPos guaranteedDropPos = new BlockPos(7, 1, 0);
		var skeleton = helper.spawnWithNoFreeWill(EntityTypes.SKELETON, guaranteedDropPos);
		helper.assertTrue(
				skeleton.hurtServer(helper.getLevel(), helper.getLevel().damageSources().playerAttack(player), 100.0F),
				"炽焰之啄颅剑击杀测试没有造成伤害"
		);
		List<ItemEntity> guaranteedSkulls = skullDropsNear(helper, guaranteedDropPos);
		helper.assertTrue(
				guaranteedSkulls.size() == 1 && guaranteedSkulls.getFirst().getItem().is(Items.WITHER_SKELETON_SKULL),
				"炽焰之啄颅剑击杀普通骷髅应当保证掉落一个凋灵骷髅头"
		);

		BlockPos convertedDropPos = new BlockPos(12, 1, 0);
		ItemEntity ordinarySkull = helper.spawnItem(Items.SKELETON_SKULL, convertedDropPos);
		var secondSkeleton = helper.spawnWithNoFreeWill(EntityTypes.SKELETON, convertedDropPos);
		secondSkeleton.hurtServer(helper.getLevel(), helper.getLevel().damageSources().playerAttack(player), 100.0F);
		List<ItemEntity> convertedSkulls = skullDropsNear(helper, convertedDropPos);
		helper.assertTrue(convertedSkulls.size() == 1, "已有普通骷髅头时不应额外生成第二个头颅");
		helper.assertTrue(
				ordinarySkull.getItem().is(Items.WITHER_SKELETON_SKULL),
				"炽焰之啄颅剑没有把普通骷髅头烧成凋灵骷髅头"
		);

		BlockPos normalWeaponPos = new BlockPos(17, 1, 0);
		player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.DIAMOND_SWORD));
		var thirdSkeleton = helper.spawnWithNoFreeWill(EntityTypes.SKELETON, normalWeaponPos);
		thirdSkeleton.hurtServer(helper.getLevel(), helper.getLevel().damageSources().playerAttack(player), 100.0F);
		helper.assertTrue(skullDropsNear(helper, normalWeaponPos).isEmpty(), "普通钻石剑不应触发啄颅掉落");
		helper.succeed();
	}

	@GameTest
	public void infinitySwordCraftsKillsAndNeverWears(GameTestHelper helper) {
		ItemStack sword = new ItemStack(ModItems.INFINITY_SWORD);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_SWORD_KEY) == ModItems.INFINITY_SWORD,
				"寰宇支配之剑没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(sword.getMaxStackSize() == 1, "寰宇支配之剑应当不可堆叠");
		helper.assertTrue(sword.getRarity() == Rarity.EPIC, "寰宇支配之剑应当是 EPIC 稀有度");
		helper.assertFalse(sword.isDamageableItem(), "寰宇支配之剑不应存在可损耗耐久");
		helper.assertTrue(sword.is(ItemTags.SWORDS), "寰宇支配之剑缺少原版剑标签");
		var weapon = sword.get(DataComponents.WEAPON);
		helper.assertTrue(weapon != null && weapon.itemDamagePerAttack() == 0, "寰宇支配之剑攻击不应损耗物品");
		var modifiers = sword.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null, "寰宇支配之剑缺少武器属性组件");
		helper.assertTrue(
				modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 100.0,
				"寰宇支配之剑主手面板攻击伤害应当是 100"
		);
		helper.assertTrue(
				Math.abs(modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) - 1.6) < 0.0001,
				"寰宇支配之剑主手攻击速度应当是 1.6"
		);

		CraftingInput input = infinitySwordInput();
		assertExtremeRecipe(helper, "infinity_sword", input, ModItems.INFINITY_SWORD);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(40, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_sword"));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(9, 9, wrongStacks), helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"寰宇支配之剑材料位置错误时不应匹配配方"
		);

		Player attacker = helper.makeMockServerPlayer(GameType.SURVIVAL);
		attacker.setItemInHand(InteractionHand.MAIN_HAND, sword);
		var warden = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(7, 1, 0));
		helper.assertTrue(warden.getHealth() >= 500.0F, "终结测试目标应当具有高生命值");
		var attackResult = AttackEntityCallback.EVENT.invoker().interact(
				attacker,
				helper.getLevel(),
				InteractionHand.MAIN_HAND,
				warden,
				new EntityHitResult(warden)
		);
		helper.assertTrue(attackResult.consumesAction(), "寰宇支配之剑服务端攻击没有接管命中");
		helper.assertTrue(warden.isDeadOrDying(), "寰宇支配之剑没有终结高生命值目标");
		helper.assertTrue(sword.getDamageValue() == 0, "寰宇支配之剑攻击后不应产生耐久损耗");

		helper.succeed();
	}

	@GameTest
	public void infinityHoeCraftsAndTillsNineByNine(GameTestHelper helper) {
		ItemStack hoe = new ItemStack(ModItems.INFINITY_HOE);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_HOE_KEY) == ModItems.INFINITY_HOE,
				"无尽锄没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(hoe.getMaxStackSize() == 1 && !hoe.isDamageableItem(), "无尽锄应当不可堆叠且永不损耗");
		helper.assertTrue(hoe.getRarity() == Rarity.EPIC, "无尽锄应当是 EPIC 稀有度");
		helper.assertTrue(hoe.is(ItemTags.HOES), "无尽锄缺少原版锄标签");
		helper.assertTrue(ModItems.INFINITY_HOE.getDestroySpeed(hoe, Blocks.HAY_BLOCK.defaultBlockState()) == 9999.0F, "无尽锄缺少终局挖掘速度");

		CraftingInput input = infinityHoeInput();
		assertExtremeRecipe(helper, "infinity_hoe", input, ModItems.INFINITY_HOE);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(5, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_hoe"));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(
								ModRecipes.EXTREME_CRAFTING,
								CraftingInput.of(input.width(), input.height(), wrongStacks),
								helper.getLevel()
						)
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"无尽锄材料位置错误时不应匹配配方"
		);

		BlockPos origin = new BlockPos(10, 1, 10);
		for (int x = -4; x <= 4; x++) {
			for (int z = -4; z <= 4; z++) {
				helper.setBlock(origin.offset(x, 0, z), Blocks.DIRT);
				helper.setBlock(origin.offset(x, 1, z), Blocks.AIR);
			}
		}
		BlockPos hole = origin.offset(4, 0, 4);
		helper.setBlock(hole, Blocks.AIR);
		helper.setBlock(hole.below(), Blocks.STONE);
		BlockPos covered = origin.offset(-4, 0, -4);
		helper.setBlock(covered.above(), Blocks.DIRT);
		helper.setBlock(origin.offset(1, 0, 0), Blocks.COARSE_DIRT);

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, hoe);
		player.getInventory().add(new ItemStack(Items.DIRT));
		BlockPos absoluteOrigin = helper.absolutePos(origin);
		var result = ModItems.INFINITY_HOE.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteOrigin), Direction.UP, absoluteOrigin, false)
		));
		helper.assertTrue(result.consumesAction(), "无尽锄没有接管合法耕作交互");
		helper.assertBlockPresent(Blocks.FARMLAND, origin);
		helper.assertBlockPresent(Blocks.FARMLAND, hole);
		helper.assertBlockPresent(Blocks.FARMLAND, covered);
		helper.assertBlockNotPresent(Blocks.DIRT, covered.above());
		helper.assertBlockPresent(Blocks.DIRT, origin.offset(1, 0, 0));
		helper.assertFalse(player.getInventory().contains(new ItemStack(Items.DIRT)), "无尽锄补土后应消耗一块泥土");
		helper.assertTrue(hoe.getDamageValue() == 0, "无尽锄范围耕作后不应产生耐久损耗");

		BlockPos sneakOrigin = new BlockPos(21, 1, 10);
		helper.setBlock(sneakOrigin, Blocks.DIRT);
		helper.setBlock(sneakOrigin.above(), Blocks.AIR);
		helper.setBlock(sneakOrigin.east(), Blocks.DIRT);
		helper.setBlock(sneakOrigin.east().above(), Blocks.AIR);
		player.setShiftKeyDown(true);
		BlockPos absoluteSneakOrigin = helper.absolutePos(sneakOrigin);
		ModItems.INFINITY_HOE.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteSneakOrigin), Direction.UP, absoluteSneakOrigin, false)
		));
		helper.assertBlockPresent(Blocks.FARMLAND, sneakOrigin);
		helper.assertBlockPresent(Blocks.DIRT, sneakOrigin.east());
		helper.succeed();
	}

	@GameTest
	public void eternalSingularityDynamicallyCombinesEverySingularity(GameTestHelper helper) {
		ItemStack eternal = new ItemStack(ModItems.ETERNAL_SINGULARITY);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.ETERNAL_SINGULARITY_KEY) == ModItems.ETERNAL_SINGULARITY,
				"永恒奇点没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(ModItems.ETERNAL_SINGULARITY.getDefaultMaxStackSize() == 8, "永恒奇点应当堆叠 8 个");
		helper.assertTrue(eternal.getRarity() == Rarity.RARE, "永恒奇点应当是 RARE 稀有度");
		helper.assertTrue(eternal.has(DataComponents.DAMAGE_RESISTANT), "永恒奇点应当具有防火伤害抗性组件");
		helper.assertTrue(ModItems.ETERNAL_SINGULARITY.isFoil(eternal), "永恒奇点应当始终显示附魔光效");
		helper.assertTrue(
				BuiltInRegistries.RECIPE_SERIALIZER.getValue(Avaritia26.id("eternal_singularity"))
						== ModRecipes.ETERNAL_SINGULARITY_SERIALIZER,
				"永恒奇点动态配方序列化器未注册"
		);

		List<SingularityDefinition> definitions = SingularityManager.values().stream()
				.filter(SingularityDefinition::recipeEnabled)
				.toList();
		helper.assertTrue(definitions.size() == 14, "永恒奇点测试应当动态读取 14 个内置奇点");
		List<ItemStack> singularities = definitions.stream()
				.map(SingularityItem::createStack)
				.collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));
		CraftingInput input = CraftingInput.of(7, 2, singularities);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("eternal_singularity")
		);
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("全部奇点未匹配永恒奇点配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "永恒奇点材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModItems.ETERNAL_SINGULARITY) && result.getCount() == 1, "永恒奇点输出错误");

		List<ItemStack> missing = copyStacks(singularities);
		missing.removeLast();
		missing.add(ItemStack.EMPTY);
		helper.assertFalse(
				recipe.value().matches(CraftingInput.of(7, 2, missing), helper.getLevel()),
				"缺少奇点不应匹配永恒奇点配方"
		);

		List<ItemStack> duplicate = copyStacks(singularities);
		duplicate.set(duplicate.size() - 1, duplicate.getFirst().copy());
		helper.assertFalse(
				recipe.value().matches(CraftingInput.of(7, 2, duplicate), helper.getLevel()),
				"重复奇点不应匹配永恒奇点配方"
		);

		List<ItemStack> tamperedStacks = copyStacks(singularities);
		SingularityDefinition original = definitions.getFirst();
		SingularityDefinition tampered = new SingularityDefinition(
				original.name(),
				original.displayName(),
				original.overlayColor() ^ 0x00FFFFFF,
				original.underlayColor(),
				original.count(),
				original.timeCost(),
				original.ingredient(),
				original.enabled(),
				original.recipeEnabled()
		);
		tamperedStacks.set(0, SingularityItem.createStack(tampered));
		helper.assertFalse(
				recipe.value().matches(CraftingInput.of(7, 2, tamperedStacks), helper.getLevel()),
				"失效奇点快照不应匹配永恒奇点配方"
		);

		List<ItemStack> extra = copyStacks(singularities);
		extra.add(new ItemStack(Items.DIRT));
		helper.assertFalse(
				recipe.value().matches(CraftingInput.of(5, 3, extra), helper.getLevel()),
				"额外材料不应匹配永恒奇点配方"
		);
		helper.succeed();
	}

	@GameTest
	public void neutronCollectorCraftsPersistsAndProduces(GameTestHelper helper) {
		CraftingInput collectorInput = neutronCollectorInput();
		assertExtremeRecipe(
				helper,
				"neutron_collector",
				collectorInput,
				ModBlocks.NEUTRON_COLLECTOR_ITEM
		);
		List<ItemStack> wrongRecipeInput = copyStacks(collectorInput.items());
		wrongRecipeInput.set(40, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> collectorRecipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("neutron_collector")
		);
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(
								ModRecipes.EXTREME_CRAFTING,
								CraftingInput.of(9, 9, wrongRecipeInput),
								helper.getLevel()
						)
						.filter(candidate -> candidate.id().equals(collectorRecipeKey))
						.isPresent(),
				"中子收集器中心材料错误时不应匹配配方"
		);
		helper.assertTrue(
				BuiltInRegistries.BLOCK.getValue(ModBlocks.NEUTRON_COLLECTOR_KEY) == ModBlocks.NEUTRON_COLLECTOR,
				"中子收集器方块未注册"
		);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModBlocks.NEUTRON_COLLECTOR_ITEM_KEY)
						== ModBlocks.NEUTRON_COLLECTOR_ITEM,
				"中子收集器方块物品未注册"
		);
		helper.assertTrue(
				BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(Avaritia26.id("neutron_collector"))
						== ModBlockEntities.NEUTRON_COLLECTOR,
				"中子收集器方块实体未注册"
		);
		ItemStack collectorItem = new ItemStack(ModBlocks.NEUTRON_COLLECTOR_ITEM);
		helper.assertTrue(collectorItem.getRarity() == Rarity.RARE, "中子收集器应当是 RARE 稀有度");
		helper.assertTrue(collectorItem.has(DataComponents.DAMAGE_RESISTANT), "中子收集器物品应当防火");

		BlockPos relativePos = new BlockPos(16, 0, 0);
		helper.setBlock(relativePos, ModBlocks.NEUTRON_COLLECTOR);
		helper.assertBlockPresent(ModBlocks.NEUTRON_COLLECTOR, relativePos);
		helper.assertTrue(
				helper.getBlockState(relativePos).getValue(NeutronCollectorBlock.FACING) == Direction.NORTH,
				"中子收集器默认朝向错误"
		);
		helper.assertTrue(helper.getBlockState(relativePos).is(BlockTags.MINEABLE_WITH_PICKAXE), "中子收集器应当可用镐挖掘");
		helper.assertTrue(helper.getBlockState(relativePos).is(BlockTags.NEEDS_DIAMOND_TOOL), "中子收集器应当需要钻石级工具");
		NeutronCollectorBlockEntity collector = helper.getBlockEntity(
				relativePos,
				NeutronCollectorBlockEntity.class
		);
		helper.assertTrue(collector.getContainerSize() == 1, "中子收集器应当只有一个输出槽位");
		helper.assertFalse(collector.canPlaceItem(0, new ItemStack(Items.DIRT)), "中子收集器不应接受外部输入");

		for (int tick = 0; tick < 100; tick++) {
			NeutronCollectorBlockEntity.serverTick(
					helper.getLevel(),
					helper.absolutePos(relativePos),
					helper.getBlockState(relativePos),
					collector
			);
		}
		BlockEntity loaded = BlockEntity.loadStatic(
				helper.absolutePos(relativePos),
				helper.getBlockState(relativePos),
				collector.saveWithFullMetadata(helper.getLevel().registryAccess()),
				helper.getLevel().registryAccess()
		);
		helper.assertTrue(loaded instanceof NeutronCollectorBlockEntity, "中子收集器无法从存档恢复");
		helper.assertTrue(
				((NeutronCollectorBlockEntity) loaded).getProgress() == 100,
				"中子收集器生产进度存档错误"
		);

		for (int tick = 100; tick < NeutronCollectorBlockEntity.PRODUCTION_TICKS - 1; tick++) {
			NeutronCollectorBlockEntity.serverTick(
					helper.getLevel(),
					helper.absolutePos(relativePos),
					helper.getBlockState(relativePos),
					collector
			);
		}
		helper.assertTrue(collector.getItem(0).isEmpty(), "中子收集器提前产出了中子堆");
		NeutronCollectorBlockEntity.serverTick(
				helper.getLevel(),
				helper.absolutePos(relativePos),
				helper.getBlockState(relativePos),
				collector
		);
		helper.assertTrue(
				collector.getItem(0).is(ModItems.NEUTRON_PILE) && collector.getItem(0).getCount() == 1,
				"中子收集器未在 3600 tick 产出一个中子堆"
		);
		helper.assertTrue(collector.getProgress() == 0, "中子收集器产出后没有重置进度");
		helper.assertTrue(collector.getSlotsForFace(Direction.UP).length == 1, "中子收集器未暴露自动化输出槽");
		helper.assertTrue(
				collector.canTakeItemThroughFace(0, collector.getItem(0), Direction.UP),
				"漏斗无法从中子收集器抽取产物"
		);

		collector.setItem(0, new ItemStack(ModItems.NEUTRON_PILE, 64));
		for (int tick = 0; tick < 100; tick++) {
			NeutronCollectorBlockEntity.serverTick(
					helper.getLevel(),
					helper.absolutePos(relativePos),
					helper.getBlockState(relativePos),
					collector
			);
		}
		helper.assertTrue(collector.getItem(0).getCount() == 64, "输出满时中子收集器溢出了物品");
		helper.assertTrue(collector.getProgress() == 0, "输出满时中子收集器仍在推进进度");

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		NeutronCollectorMenu menu = (NeutronCollectorMenu) collector.createMenu(3, player.getInventory(), player);
		helper.assertTrue(menu.slots.size() == 37, "中子收集器菜单槽位数量错误");
		helper.assertFalse(menu.slots.getFirst().mayPlace(new ItemStack(Items.DIRT)), "中子收集器输出槽错误接受物品");

		List<ItemStack> machineDrops = Block.getDrops(
				helper.getBlockState(relativePos),
				helper.getLevel(),
				helper.absolutePos(relativePos),
				collector
		);
		helper.assertTrue(
				machineDrops.size() == 1 && machineDrops.getFirst().is(ModBlocks.NEUTRON_COLLECTOR_ITEM),
				"中子收集器 loot 未掉落自身"
		);
		collector.setItem(0, new ItemStack(ModItems.NEUTRON_PILE, 3));
		helper.destroyBlock(relativePos);
		int droppedPiles = helper.getLevel().getEntitiesOfClass(
				ItemEntity.class,
				new AABB(helper.absolutePos(relativePos)).inflate(2.0),
				entity -> entity.getItem().is(ModItems.NEUTRON_PILE)
		).stream().mapToInt(entity -> entity.getItem().getCount()).sum();
		helper.assertTrue(droppedPiles == 3, "破坏中子收集器没有完整返还输出物品");
		helper.succeed();
	}

	@GameTest
	public void neutronCompressorCraftsAndProcessesAllTargets(GameTestHelper helper) {
		assertExtremeRecipe(
				helper,
				"neutron_compressor",
				neutronCompressorInput(),
				ModBlocks.NEUTRON_COMPRESSOR_ITEM
		);
		helper.assertTrue(
				BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(Avaritia26.id("neutron_compressor"))
						== ModBlockEntities.NEUTRON_COMPRESSOR,
				"中子压缩机方块实体未注册"
		);
		var matterRecipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(
						ModRecipes.COMPRESSOR,
						new SingleRecipeInput(new ItemStack(ModItems.NEUTRON_INGOT)),
						helper.getLevel()
				)
				.orElseThrow(() -> helper.assertionException("中子锭未匹配满物质团压缩配方"));
		helper.assertTrue(matterRecipe.value().inputCount() == 4096, "满物质团压缩数量错误");
		helper.assertTrue(matterRecipe.value().timeCost() == 240, "满物质团压缩时间错误");
		helper.assertTrue(
				matterRecipe.value().assemble(new SingleRecipeInput(new ItemStack(ModItems.NEUTRON_INGOT)))
						.is(ModItems.FULL_MATTER_CLUSTER),
				"满物质团压缩输出错误"
		);

		BlockPos relativePos = new BlockPos(12, 0, 0);
		helper.setBlock(relativePos, ModBlocks.NEUTRON_COMPRESSOR);
		NeutronCompressorBlockEntity compressor = helper.getBlockEntity(
				relativePos,
				NeutronCompressorBlockEntity.class
		);
		helper.assertTrue(compressor.getContainerSize() == 2, "中子压缩机应当有输入和输出两个槽位");
		helper.assertTrue(compressor.canAccept(new ItemStack(Items.OBSIDIAN)), "中子压缩机不接受奇点材料");
		helper.assertFalse(compressor.canAccept(new ItemStack(Items.DIRT)), "中子压缩机错误接受无配方材料");

		feedCompressor(helper, relativePos, compressor, Items.OBSIDIAN, 1000);
		helper.assertTrue(compressor.getMaterialCount() == 1000, "黑曜石没有完整进入压缩缓存");
		for (int tick = 0; tick < 100; tick++) {
			NeutronCompressorBlockEntity.serverTick(
					helper.getLevel(),
					helper.absolutePos(relativePos),
					helper.getBlockState(relativePos),
					compressor
			);
		}
		BlockEntity loaded = BlockEntity.loadStatic(
				helper.absolutePos(relativePos),
				helper.getBlockState(relativePos),
				compressor.saveWithFullMetadata(helper.getLevel().registryAccess()),
				helper.getLevel().registryAccess()
		);
		helper.assertTrue(loaded instanceof NeutronCompressorBlockEntity, "中子压缩机无法从存档恢复");
		NeutronCompressorBlockEntity restored = (NeutronCompressorBlockEntity) loaded;
		helper.assertTrue(restored.getMaterialCount() == 1000, "中子压缩机缓存材料存档错误");
		helper.assertTrue(restored.getProgress() == compressor.getProgress(), "中子压缩机进度存档错误");

		tickUntilOutput(helper, relativePos, compressor, 200);
		SingularityDefinition obsidian = compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT)
				.get(ModDataComponents.SINGULARITY);
		helper.assertTrue(
				obsidian != null && obsidian.name().equals(Avaritia26.id("obsidian")),
				"中子压缩机未产出黑曜石奇点"
		);
		compressor.setItem(NeutronCompressorBlockEntity.OUTPUT_SLOT, ItemStack.EMPTY);

		feedCompressor(helper, relativePos, compressor, Items.COAL, 1000);
		compressor.setItem(NeutronCompressorBlockEntity.OUTPUT_SLOT, new ItemStack(Items.DIRT));
		int blockedProgress = compressor.getProgress();
		for (int tick = 0; tick < 300; tick++) {
			NeutronCompressorBlockEntity.serverTick(
					helper.getLevel(), helper.absolutePos(relativePos), helper.getBlockState(relativePos), compressor
			);
		}
		helper.assertTrue(compressor.getProgress() == blockedProgress, "输出堵塞时压缩机仍在推进进度");
		helper.assertTrue(compressor.getMaterialCount() == 1000, "输出堵塞时压缩机丢失了缓存材料");
		compressor.setItem(NeutronCompressorBlockEntity.OUTPUT_SLOT, ItemStack.EMPTY);
		tickUntilOutput(helper, relativePos, compressor, 240);
		SingularityDefinition coal = compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT)
				.get(ModDataComponents.SINGULARITY);
		helper.assertTrue(coal != null && coal.name().equals(Avaritia26.id("coal")), "解除堵塞后未产出煤炭奇点");
		compressor.setItem(NeutronCompressorBlockEntity.OUTPUT_SLOT, ItemStack.EMPTY);

		feedCompressor(helper, relativePos, compressor, ModItems.NEUTRON_INGOT, 4096);
		tickUntilOutput(helper, relativePos, compressor, 240);
		helper.assertTrue(
				compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT).is(ModItems.FULL_MATTER_CLUSTER),
				"中子压缩机未完成满物质团压缩配方"
		);
		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		NeutronCompressorMenu menu = (NeutronCompressorMenu) compressor.createMenu(2, player.getInventory(), player);
		helper.assertTrue(menu.slots.size() == 38, "中子压缩机菜单槽位数量错误");
		compressor.setItem(NeutronCompressorBlockEntity.OUTPUT_SLOT, ItemStack.EMPTY);
		feedCompressor(helper, relativePos, compressor, Items.OBSIDIAN, 128);
		compressor.ejectBufferedMaterial(player);
		helper.assertTrue(player.getInventory().countItem(Items.OBSIDIAN) == 128, "潜行弹出没有返还缓存材料");
		helper.assertTrue(compressor.getMaterialCount() == 0 && compressor.getProgress() == 0, "弹出后缓存未清空");

		feedCompressor(helper, relativePos, compressor, Items.OBSIDIAN, 192);
		List<ItemStack> machineDrops = Block.getDrops(
				helper.getBlockState(relativePos),
				helper.getLevel(),
				helper.absolutePos(relativePos),
				compressor
		);
		helper.assertTrue(
				machineDrops.size() == 1 && machineDrops.getFirst().is(ModBlocks.NEUTRON_COMPRESSOR_ITEM),
				"中子压缩机 loot 未掉落自身"
		);
		helper.destroyBlock(relativePos);
		int droppedObsidian = helper.getLevel().getEntitiesOfClass(
				ItemEntity.class,
				new AABB(helper.absolutePos(relativePos)).inflate(2.0),
				entity -> entity.getItem().is(Items.OBSIDIAN)
		).stream().mapToInt(entity -> entity.getItem().getCount()).sum();
		helper.assertTrue(droppedObsidian == 192, "破坏压缩机没有完整返还缓存材料");
		helper.succeed();
	}

	@GameTest
	public void everyBuiltInSingularityCompresses(GameTestHelper helper) {
		BlockPos relativePos = new BlockPos(13, 0, 0);
		helper.setBlock(relativePos, ModBlocks.NEUTRON_COMPRESSOR);
		NeutronCompressorBlockEntity compressor = helper.getBlockEntity(
				relativePos,
				NeutronCompressorBlockEntity.class
		);
		for (SingularityDefinition definition : SingularityManager.values()) {
			Item ingredient = definition.ingredient().items()
					.findFirst()
					.orElseThrow(() -> helper.assertionException(definition.name() + " 没有可压缩输入"))
					.value();
			feedCompressor(helper, relativePos, compressor, ingredient, definition.count());
			tickUntilOutput(helper, relativePos, compressor, definition.timeCost());
			SingularityDefinition output = compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT)
					.get(ModDataComponents.SINGULARITY);
			helper.assertTrue(
					output != null && output.name().equals(definition.name()),
					definition.name() + " 压缩产出了错误奇点"
			);
			compressor.setItem(NeutronCompressorBlockEntity.OUTPUT_SLOT, ItemStack.EMPTY);
		}
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
	public void cosmicMeatballsRecipeAndEffectsWork(GameTestHelper helper) {
		assertRegisteredMaterial(
				helper,
				ModItems.COSMIC_MEATBALLS_KEY,
				ModItems.COSMIC_MEATBALLS,
				Rarity.EPIC,
				"寰宇肉丸"
		);
		ItemStack stack = new ItemStack(ModItems.COSMIC_MEATBALLS);
		FoodProperties food = stack.get(DataComponents.FOOD);
		Consumable consumable = stack.get(DataComponents.CONSUMABLE);
		helper.assertTrue(food != null, "寰宇肉丸缺少食物组件");
		helper.assertTrue(consumable != null, "寰宇肉丸缺少可食用组件");
		helper.assertTrue(food.nutrition() == 20, "寰宇肉丸应当恢复 20 点饥饿值");
		helper.assertTrue(food.saturation() == 800.0F, "寰宇肉丸饱和度应当保持上游的 20 倍修正值");
		helper.assertTrue(food.canAlwaysEat(), "寰宇肉丸应当允许满饥饿值食用");
		helper.assertTrue(consumable.consumeSeconds() == 1.6F, "寰宇肉丸应当使用默认的 1.6 秒食用时间");
		helper.assertTrue(consumable.onConsumeEffects().size() == 1, "寰宇肉丸应当配置一组状态效果");

		CraftingInput input = cosmicMeatballsInput();
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("cosmic_meatballs")
		);
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("13 种正确材料未匹配寰宇肉丸配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "寰宇肉丸材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModItems.COSMIC_MEATBALLS) && result.getCount() == 1, "寰宇肉丸配方输出错误");

		List<ItemStack> wrongStacks = new java.util.ArrayList<>(input.items());
		wrongStacks.set(11, new ItemStack(Items.DIRT));
		CraftingInput wrongInput = CraftingInput.of(9, 2, wrongStacks);
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, wrongInput, helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"寰宇肉丸配方不应接受非蛋物品"
		);

		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		ItemStack consumed = consumable.onConsume(helper.getLevel(), player, stack.copy());
		helper.assertTrue(consumed.isEmpty(), "食用一份寰宇肉丸后物品应当被消耗");
		assertEffect(helper, player, MobEffects.FIRE_RESISTANCE, 5 * 60 * 20, 0, "抗火");
		assertEffect(helper, player, MobEffects.RESISTANCE, 1 * 60 * 20, 1, "抗性提升");
		assertEffect(helper, player, MobEffects.ABSORPTION, 3 * 60 * 20, 2, "伤害吸收");
		assertEffect(helper, player, MobEffects.NIGHT_VISION, 3 * 60 * 20, 0, "夜视");
		assertEffect(helper, player, MobEffects.WATER_BREATHING, 2 * 60 * 20, 2, "水下呼吸");
		assertEffect(helper, player, MobEffects.REGENERATION, 5 * 60 * 20, 4, "生命恢复");
		helper.succeed();
	}

	@GameTest
	public void endestPearlRecipeAndThrowWork(GameTestHelper helper) {
		ItemStack stack = new ItemStack(ModItems.ENDEST_PEARL, 2);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.ENDEST_PEARL_KEY) == ModItems.ENDEST_PEARL,
				"终望珍珠没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(ModItems.ENDEST_PEARL.getDefaultMaxStackSize() == 16, "终望珍珠应当堆叠 16 个");
		helper.assertTrue(stack.getRarity() == Rarity.EPIC, "终望珍珠应当是 EPIC 稀有度");
		UseCooldown cooldown = stack.get(DataComponents.USE_COOLDOWN);
		helper.assertTrue(cooldown != null && cooldown.ticks() == 30, "终望珍珠冷却时间应当是 30 tick");
		helper.assertTrue(
				BuiltInRegistries.ENTITY_TYPE.getValue(ModEntityTypes.ENDEST_PEARL_KEY) == ModEntityTypes.ENDEST_PEARL,
				"终望珍珠投掷物实体未正确注册"
		);
		helper.assertTrue(
				BuiltInRegistries.ENTITY_TYPE.getValue(ModEntityTypes.GAPING_VOID_KEY) == ModEntityTypes.GAPING_VOID,
				"虚空漩涡实体未正确注册"
		);

		CraftingInput input = endestPearlInput();
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("endest_pearl"));
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("正确 9x9 材料未匹配终望珍珠配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "终望珍珠材料匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModItems.ENDEST_PEARL) && result.getCount() == 1, "终望珍珠配方输出错误");
		List<ItemStack> wrongStacks = new java.util.ArrayList<>(input.items());
		wrongStacks.set(40, new ItemStack(Items.DIRT));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(9, 9, wrongStacks), helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"终望珍珠中心材料错误时不应匹配配方"
		);

		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		BlockPos throwOrigin = helper.absolutePos(new BlockPos(8, 2, 0));
		player.setPos(throwOrigin.getX() + 0.5, throwOrigin.getY(), throwOrigin.getZ() + 0.5);
		player.setItemInHand(InteractionHand.MAIN_HAND, stack);
		ModItems.ENDEST_PEARL.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(stack.getCount() == 1, "生存模式投掷终望珍珠应当消耗 1 个");
		helper.assertTrue(player.getCooldowns().isOnCooldown(stack), "投掷终望珍珠后没有进入冷却");
		AABB testArea = new AABB(player.blockPosition()).inflate(64.0);
		List<EndestPearlEntity> projectiles = helper.getLevel().getEntitiesOfClass(
				EndestPearlEntity.class,
				testArea
		);
		helper.assertTrue(projectiles.size() == 1, "投掷终望珍珠没有生成唯一投掷物");
		EndestPearlEntity projectile = projectiles.getFirst();
		helper.assertTrue(projectile.getOwner() == player, "终望珍珠投掷物没有保存发射者");
		helper.assertTrue(
				Math.abs(projectile.getDeltaMovement().length() - 1.5) < 0.05,
				"终望珍珠初速度应当是 1.5"
		);

		BlockPos collisionPos = new BlockPos(10, 1, 2);
		helper.setBlock(collisionPos, Blocks.STONE);
		BlockPos projectileStart = helper.absolutePos(new BlockPos(10, 1, 0));
		projectile.setPos(projectileStart.getX() + 0.5, projectileStart.getY() + 0.5, projectileStart.getZ() + 0.5);
		projectile.setDeltaMovement(0.0, 0.0, 1.5);
		projectile.tick();
		projectile.tick();
		List<GapingVoidEntity> voids = helper.getLevel().getEntitiesOfClass(
				GapingVoidEntity.class,
				new AABB(helper.absolutePos(collisionPos)).inflate(3.0)
		);
		helper.assertTrue(voids.size() == 1, "终望珍珠命中方块后没有生成虚空漩涡");
		voids.getFirst().discard();
		helper.succeed();
	}

	@GameTest
	public void gapingVoidPullsAndBreaksPermittedBlocks(GameTestHelper helper) {
		BlockPos center = new BlockPos(14, 2, 0);
		BlockPos dirtPos = center.offset(1, 0, 0);
		BlockPos obsidianPos = center.offset(0, 1, 0);
		helper.setBlock(dirtPos, Blocks.DIRT);
		helper.setBlock(obsidianPos, Blocks.OBSIDIAN);
		Player user = helper.makeMockPlayer(GameType.SURVIVAL);
		BlockPos absoluteCenter = helper.absolutePos(center);
		user.setPos(absoluteCenter.getX() + 30.0, absoluteCenter.getY(), absoluteCenter.getZ());
		GapingVoidEntity gapingVoid = new GapingVoidEntity(helper.getLevel(), user);
		gapingVoid.setPos(
				absoluteCenter.getX() + 0.5,
				absoluteCenter.getY() + 0.5,
				absoluteCenter.getZ() + 0.5
		);
		helper.getLevel().addFreshEntity(gapingVoid);
		ItemEntity pulledItem = new ItemEntity(
				helper.getLevel(),
				gapingVoid.getX() + 5.0,
				gapingVoid.getY(),
				gapingVoid.getZ(),
				new ItemStack(Items.COBBLESTONE)
		);
		helper.getLevel().addFreshEntity(pulledItem);

		for (int tick = 0; tick < 100; tick++) {
			gapingVoid.tick();
		}
		helper.assertTrue(gapingVoid.getAge() == 100, "虚空漩涡生命周期没有按 tick 推进");
		helper.assertTrue(pulledItem.getDeltaMovement().x < 0.0, "虚空漩涡没有把物品拉向中心");
		helper.assertBlockPresent(Blocks.AIR, dirtPos);
		helper.assertBlockPresent(Blocks.OBSIDIAN, obsidianPos);
		helper.assertTrue(GapingVoidEntity.getVoidScale(90) > 1.0, "虚空漩涡没有随生命周期扩张");

		for (int tick = 100; tick <= GapingVoidEntity.MAX_LIFETIME; tick++) {
			gapingVoid.tick();
		}
		helper.assertTrue(gapingVoid.isRemoved(), "虚空漩涡没有在 186 tick 后坍缩移除");
		helper.assertTrue(GapingVoidEntity.getVoidScale(GapingVoidEntity.MAX_LIFETIME) == 0.0, "坍缩末尾缩放应当归零");
		helper.succeed();
	}

	@GameTest
	public void matterClusterStoresMergesAndReleasesItems(GameTestHelper helper) {
		ItemStack emptyCluster = new ItemStack(ModItems.MATTER_CLUSTER);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.MATTER_CLUSTER_KEY) == ModItems.MATTER_CLUSTER,
				"物质团没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(ModItems.MATTER_CLUSTER.getDefaultMaxStackSize() == 1, "物质团应当不可堆叠");
		helper.assertTrue(emptyCluster.getRarity() == Rarity.RARE, "物质团应当是 RARE 稀有度");
		helper.assertTrue(ModDataComponents.MATTER_CLUSTER_CONTENTS.codec() != null, "物质团组件缺少持久化编码器");
		helper.assertTrue(ModDataComponents.MATTER_CLUSTER_CONTENTS.streamCodec() != null, "物质团组件缺少网络编码器");

		List<ItemStack> packed = MatterClusterItem.createClusters(List.of(
				new ItemStack(Items.COBBLESTONE, 5000),
				new ItemStack(Items.DIRT, 100)
		));
		helper.assertTrue(packed.size() == 2, "5100 个物品应当拆成两个物质团");
		helper.assertTrue(MatterClusterItem.getSize(packed.get(0)) == MatterClusterItem.CAPACITY, "第一个物质团应当装满 4096 个");
		helper.assertTrue(MatterClusterItem.getSize(packed.get(1)) == 1004, "第二个物质团剩余数量错误");
		helper.assertTrue(
				packed.getFirst().has(ModDataComponents.MATTER_CLUSTER_CONTENTS),
				"物质团没有保存容器数据组件"
		);
		helper.assertTrue(
				MatterClusterItem.getSize(packed.getFirst().copy()) == MatterClusterItem.CAPACITY,
				"复制物质团时容器数据丢失"
		);
		helper.assertTrue(ModItems.MATTER_CLUSTER.isBarVisible(packed.getFirst()), "非空物质团应当显示容量条");
		helper.assertTrue(ModItems.MATTER_CLUSTER.getBarWidth(packed.getFirst()) == 13, "满物质团容量条应当填满");

		ItemStack mergeTarget = MatterClusterItem.createClusters(List.of(new ItemStack(Items.STONE, 3000))).getFirst();
		ItemStack mergeSource = MatterClusterItem.createClusters(List.of(new ItemStack(Items.DIRT, 1500))).getFirst();
		helper.assertTrue(MatterClusterItem.merge(mergeSource, mergeTarget), "两个物质团未能合并");
		helper.assertTrue(MatterClusterItem.getSize(mergeTarget) == MatterClusterItem.CAPACITY, "合并目标没有填满");
		helper.assertTrue(MatterClusterItem.getSize(mergeSource) == 404, "合并来源剩余数量错误");

		ItemStack releasable = MatterClusterItem.createClusters(List.of(
				new ItemStack(Items.DIAMOND, 10),
				new ItemStack(Items.DIRT, 5)
		)).getFirst();
		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		BlockPos releasePos = helper.absolutePos(new BlockPos(18, 2, 0));
		player.setPos(releasePos.getX() + 0.5, releasePos.getY(), releasePos.getZ() + 0.5);
		player.setItemInHand(InteractionHand.MAIN_HAND, releasable);
		ModItems.MATTER_CLUSTER.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty(), "释放内容后物质团没有被消耗");
		List<ItemEntity> drops = helper.getLevel().getEntitiesOfClass(
				ItemEntity.class,
				new AABB(releasePos).inflate(3.0)
		);
		int diamonds = drops.stream()
				.filter(entity -> entity.getItem().is(Items.DIAMOND))
				.mapToInt(entity -> entity.getItem().getCount())
				.sum();
		int dirt = drops.stream()
				.filter(entity -> entity.getItem().is(Items.DIRT))
				.mapToInt(entity -> entity.getItem().getCount())
				.sum();
		helper.assertTrue(diamonds == 10 && dirt == 5, "物质团右键没有完整释放保存的物品");
		helper.succeed();
	}

	@GameTest
	public void fullMatterClusterRequiresFullCapacity(GameTestHelper helper) {
		ItemStack fullMatterCluster = new ItemStack(ModItems.FULL_MATTER_CLUSTER);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.FULL_MATTER_CLUSTER_KEY) == ModItems.FULL_MATTER_CLUSTER,
				"满物质团没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(ModItems.FULL_MATTER_CLUSTER.getDefaultMaxStackSize() == 1, "满物质团应当不可堆叠");
		helper.assertTrue(fullMatterCluster.getRarity() == Rarity.RARE, "满物质团应当是 RARE 稀有度");

		ItemStack fullInput = MatterClusterItem.createClusters(
				List.of(new ItemStack(Items.COBBLESTONE, MatterClusterItem.CAPACITY))
		).getFirst();
		CraftingInput input = CraftingInput.of(1, 1, List.of(fullInput));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("full_matter_cluster")
		);
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("装满 4096 个物品的物质团未匹配满物质团配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "满物质团输入匹配到了错误配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModItems.FULL_MATTER_CLUSTER) && result.getCount() == 1, "满物质团配方输出错误");

		ItemStack underfilled = MatterClusterItem.createClusters(
				List.of(new ItemStack(Items.COBBLESTONE, MatterClusterItem.CAPACITY - 1))
		).getFirst();
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(
								ModRecipes.EXTREME_CRAFTING,
								CraftingInput.of(1, 1, List.of(underfilled)),
								helper.getLevel()
						)
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"少于 4096 个物品的物质团不应匹配满物质团配方"
		);
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

	private static void assertSingularity(GameTestHelper helper, String path, ExpectedSingularity values) {
		SingularityDefinition definition = SingularityManager.get(Avaritia26.id(path));
		helper.assertTrue(definition != null, "缺少奇点定义 " + path);
		helper.assertTrue(definition.enabled() && definition.recipeEnabled(), path + " 奇点应当启用");
		helper.assertTrue(
				definition.count() == 1000 && definition.timeCost() == 240,
				path + " 奇点默认压缩参数错误"
		);
		helper.assertTrue(definition.overlayColor() == values.overlayColor(), path + " 奇点覆盖色错误");
		helper.assertTrue(definition.underlayColor() == values.underlayColor(), path + " 奇点底色错误");
		helper.assertTrue(definition.ingredient().test(new ItemStack(values.ingredient())), path + " 奇点输入错误");

		ItemStack stack = SingularityItem.createStack(definition);
		helper.assertTrue(stack.is(ModItems.SINGULARITY), path + " 奇点没有使用共用物品");
		helper.assertTrue(stack.getRarity() == Rarity.UNCOMMON, path + " 奇点稀有度错误");
		helper.assertTrue(
				definition.equals(stack.get(ModDataComponents.SINGULARITY)),
				path + " 奇点组件写入失败"
		);
		CustomModelData colors = stack.get(DataComponents.CUSTOM_MODEL_DATA);
		helper.assertTrue(colors != null, path + " 奇点缺少模型颜色组件");
		helper.assertTrue(colors.getColor(0) == definition.underlayColor(), path + " 奇点模型底色错误");
		helper.assertTrue(colors.getColor(1) == definition.overlayColor(), path + " 奇点模型覆盖色错误");
		helper.assertTrue(
				definition.equals(stack.copy().get(ModDataComponents.SINGULARITY)),
				path + " 奇点复制丢失组件"
		);
		var ops = helper.getLevel().registryAccess().createSerializationContext(JsonOps.INSTANCE);
		var encoded = ItemStack.CODEC.encodeStart(ops, stack).getOrThrow();
		ItemStack restored = ItemStack.CODEC.parse(ops, encoded).getOrThrow();
		helper.assertTrue(
				definition.equals(restored.get(ModDataComponents.SINGULARITY)),
				path + " 奇点存档往返丢失组件"
		);

		RegistryFriendlyByteBuf buffer = new RegistryFriendlyByteBuf(
				Unpooled.buffer(),
				helper.getLevel().registryAccess()
		);
		try {
			SingularityDefinition.STREAM_CODEC.encode(buffer, definition);
			SingularityDefinition decoded = SingularityDefinition.STREAM_CODEC.decode(buffer);
			helper.assertTrue(definition.equals(decoded), path + " 奇点网络同步往返不一致");
		} finally {
			buffer.release();
		}
	}

	private record ExpectedSingularity(Item ingredient, int overlayColor, int underlayColor) {
	}

	private static void assertExtremeRecipe(
			GameTestHelper helper,
			String path,
			CraftingInput input,
			Item expectedItem
	) {
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id(path));
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("未匹配终极配方 " + path));
		helper.assertTrue(recipe.id().equals(recipeKey), path + " 匹配到了错误配方");
		helper.assertTrue(recipe.value().assemble(input).is(expectedItem), path + " 输出了错误物品");
	}

	private static void assertInfinityCatalystRejected(
			GameTestHelper helper,
			ResourceKey<Recipe<?>> recipeKey,
			List<ItemStack> ingredients,
			String scenario
	) {
		List<ItemStack> padded = copyStacks(ingredients);
		int height = (padded.size() + 8) / 9;
		while (padded.size() < 9 * height) {
			padded.add(ItemStack.EMPTY);
		}
		CraftingInput input = CraftingInput.of(9, height, padded);
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				scenario + "不应匹配无尽催化剂配方"
		);
	}

	private static List<ItemStack> copyStacks(List<ItemStack> stacks) {
		List<ItemStack> copies = new java.util.ArrayList<>(stacks.size());
		stacks.forEach(stack -> copies.add(stack.copy()));
		return copies;
	}

	private static void feedCompressor(
			GameTestHelper helper,
			BlockPos relativePos,
			NeutronCompressorBlockEntity compressor,
			Item item,
			int count
	) {
		int remaining = count;
		while (remaining > 0) {
			int batch = Math.min(item.getDefaultMaxStackSize(), remaining);
			compressor.setItem(NeutronCompressorBlockEntity.INPUT_SLOT, new ItemStack(item, batch));
			NeutronCompressorBlockEntity.serverTick(
					helper.getLevel(),
					helper.absolutePos(relativePos),
					helper.getBlockState(relativePos),
					compressor
			);
			remaining -= batch;
		}
	}

	private static void tickUntilOutput(
			GameTestHelper helper,
			BlockPos relativePos,
			NeutronCompressorBlockEntity compressor,
			int maximumTicks
	) {
		for (int tick = 0; tick < maximumTicks && compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT).isEmpty(); tick++) {
			NeutronCompressorBlockEntity.serverTick(
					helper.getLevel(),
					helper.absolutePos(relativePos),
					helper.getBlockState(relativePos),
					compressor
			);
		}
		helper.assertFalse(
				compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT).isEmpty(),
				"中子压缩机没有在预期时间内产出物品"
		);
	}

	private static CraftingInput filledCraftingInput(Item item) {
		return CraftingInput.of(3, 3, List.of(
				new ItemStack(item), new ItemStack(item), new ItemStack(item),
				new ItemStack(item), new ItemStack(item), new ItemStack(item),
				new ItemStack(item), new ItemStack(item), new ItemStack(item)
		));
	}

	private static CraftingInput blazeCubeInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(25);
		for (String row : List.of(" bcb ", "byxyb", "cxaxc", "byxyb", " bcb ")) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'a' -> new ItemStack(Items.ANCIENT_DEBRIS);
					case 'b' -> new ItemStack(Items.BLAZE_POWDER);
					case 'c' -> new ItemStack(Items.FIRE_CHARGE);
					case 'x' -> new ItemStack(Items.BLAZE_ROD);
					case 'y' -> new ItemStack(Items.BONE);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知炽骨立方配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(5, 5, stacks);
	}

	private static CraftingInput skullFireSwordInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"       IX",
				"      IXI",
				"     IXI ",
				"    IXI  ",
				" B IXI   ",
				"  BXI    ",
				"  WB     ",
				" W  B    ",
				"D        "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'B' -> new ItemStack(Items.BONE);
					case 'D' -> new ItemStack(Items.NETHER_STAR);
					case 'I' -> new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
					case 'W' -> new ItemStack(Items.OAK_LOG);
					case 'X' -> new ItemStack(Items.BLAZE_POWDER);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知炽焰之啄颅剑配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput infinitySwordInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"       II",
				"      III",
				"     III ",
				"    III  ",
				" C III   ",
				"  CII    ",
				"  NC     ",
				" N  C    ",
				"X        "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'C' -> new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
					case 'I' -> new ItemStack(ModItems.INFINITY_INGOT);
					case 'N' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'X' -> new ItemStack(ModItems.INFINITY_CATALYST);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知寰宇支配之剑配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput infinityHoeInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"     N   ",
				" IIIIII  ",
				"IIIIIII  ",
				"I    II  ",
				"     N   ",
				"     N   ",
				"     N   ",
				"     N   ",
				"     N   "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'I' -> new ItemStack(ModItems.INFINITY_INGOT);
					case 'N' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知无尽锄配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static List<ItemEntity> skullDropsNear(GameTestHelper helper, BlockPos relativePos) {
		return helper.getLevel().getEntitiesOfClass(
				ItemEntity.class,
				new AABB(helper.absolutePos(relativePos)).inflate(2.0),
				itemEntity -> itemEntity.getItem().is(Items.SKELETON_SKULL)
						|| itemEntity.getItem().is(Items.WITHER_SKELETON_SKULL)
		);
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

	private static CraftingInput endestPearlInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"   EEE   ",
				" EEPPPEE ",
				" EPPPPPE ",
				"EPPPNPPPE",
				"EPPNSNPPE",
				"EPPPNPPPE",
				" EPPPPPE ",
				" EEPPPEE ",
				"   EEE   "
		)) {
			for (char symbol : row.toCharArray()) {
				stacks.add(switch (symbol) {
					case 'E' -> new ItemStack(Items.END_STONE);
					case 'N' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'P' -> new ItemStack(Items.ENDER_PEARL);
					case 'S' -> new ItemStack(Items.NETHER_STAR);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知终望珍珠配方符号: " + symbol);
				});
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput neutronCompressorInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"IIIHHHIII",
				"C N   N C",
				"I N   N I",
				"C N   N C",
				"RNN O NNR",
				"C N   N C",
				"I N   N I",
				"C N   N C",
				"IIICICIII"
		)) {
			for (char symbol : row.toCharArray()) {
				stacks.add(switch (symbol) {
					case 'C' -> new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
					case 'H' -> new ItemStack(Items.HOPPER);
					case 'I' -> new ItemStack(Items.IRON_BLOCK);
					case 'N' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'O' -> new ItemStack(ModBlocks.NEUTRON_ITEM);
					case 'R' -> new ItemStack(Items.REDSTONE_BLOCK);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知中子压缩机配方符号: " + symbol);
				});
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput neutronCollectorInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"IIQQQQQII",
				"I QQQQQ I",
				"I  RRR  I",
				"C RRRRR C",
				"I RRCRR I",
				"C RRRRR C",
				"I  RRR  I",
				"I       I",
				"IIICICIII"
		)) {
			for (char symbol : row.toCharArray()) {
				stacks.add(switch (symbol) {
					case 'C' -> new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
					case 'I' -> new ItemStack(Items.IRON_BLOCK);
					case 'Q' -> new ItemStack(Items.QUARTZ_BLOCK);
					case 'R' -> new ItemStack(Items.REDSTONE_BLOCK);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知中子收集器配方符号: " + symbol);
				});
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput infinityIngotInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(45);
		for (String row : List.of(
				"NNNNNNNNN",
				"NCXXCXXCN",
				"NXCCXCCXN",
				"NCXXCXXCN",
				"NNNNNNNNN"
		)) {
			for (char symbol : row.toCharArray()) {
				stacks.add(new ItemStack(switch (symbol) {
					case 'C' -> ModItems.CRYSTAL_MATRIX_INGOT;
					case 'N' -> ModItems.NEUTRON_INGOT;
					case 'X' -> ModItems.INFINITY_CATALYST;
					default -> throw new IllegalArgumentException("未知无尽锭配方符号: " + symbol);
				}));
			}
		}
		return CraftingInput.of(9, 5, stacks);
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

	private static CraftingInput cosmicMeatballsInput() {
		return CraftingInput.of(9, 2, List.of(
				new ItemStack(Items.PORKCHOP), new ItemStack(Items.BEEF), new ItemStack(Items.MUTTON),
				new ItemStack(Items.COD), new ItemStack(Items.SALMON), new ItemStack(Items.TROPICAL_FISH),
				new ItemStack(Items.PUFFERFISH), new ItemStack(Items.RABBIT), new ItemStack(Items.CHICKEN),
				new ItemStack(Items.ROTTEN_FLESH), new ItemStack(Items.SPIDER_EYE), new ItemStack(Items.EGG),
				new ItemStack(ModItems.NEUTRON_NUGGET), ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
				ItemStack.EMPTY, ItemStack.EMPTY
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
