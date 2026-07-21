package io.github.aspshijiu.avaritia26.gametest;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mojang.serialization.JsonOps;
import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.block.CompressedChestBlock;
import io.github.aspshijiu.avaritia26.block.ExtremeAnvilBlock;
import io.github.aspshijiu.avaritia26.block.EndlessCakeBlock;
import io.github.aspshijiu.avaritia26.block.InfinityChestBlock;
import io.github.aspshijiu.avaritia26.block.NeutronCollectorBlock;
import io.github.aspshijiu.avaritia26.block.NeutronCompressorBlock;
import io.github.aspshijiu.avaritia26.block.entity.CompressedChestBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.EndCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.ExtremeCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.InfinityChestBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.NetherCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.NeutronCollectorBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.NeutronCollectorTier;
import io.github.aspshijiu.avaritia26.block.entity.NeutronCompressorTier;
import io.github.aspshijiu.avaritia26.block.entity.NeutronCompressorBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.SculkCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.crafting.EternalSingularityRecipe;
import io.github.aspshijiu.avaritia26.crafting.ExtremeSmithingInput;
import io.github.aspshijiu.avaritia26.crafting.ExtremeSmithingRecipe;
import io.github.aspshijiu.avaritia26.crafting.FullMatterClusterRecipe;
import io.github.aspshijiu.avaritia26.crafting.InfinityCatalystRecipe;
import io.github.aspshijiu.avaritia26.crafting.ModRecipes;
import io.github.aspshijiu.avaritia26.component.InfinityChestContents;
import io.github.aspshijiu.avaritia26.component.InfinityBucketContents;
import io.github.aspshijiu.avaritia26.component.ClockAccelerationData;
import io.github.aspshijiu.avaritia26.component.SideConfiguration;
import io.github.aspshijiu.avaritia26.crafting.NoConsumeCatalystShapedRecipe;
import io.github.aspshijiu.avaritia26.entity.EndestPearlEntity;
import io.github.aspshijiu.avaritia26.entity.BladeSlashEntity;
import io.github.aspshijiu.avaritia26.entity.BlazeFireballEntity;
import io.github.aspshijiu.avaritia26.entity.BurningArrowEntity;
import io.github.aspshijiu.avaritia26.entity.BurningBallEntity;
import io.github.aspshijiu.avaritia26.entity.GapingVoidEntity;
import io.github.aspshijiu.avaritia26.entity.HeavenArrowEntity;
import io.github.aspshijiu.avaritia26.entity.HeavenSubArrowEntity;
import io.github.aspshijiu.avaritia26.entity.InfinityThrownTridentEntity;
import io.github.aspshijiu.avaritia26.entity.NeutronArrowEntity;
import io.github.aspshijiu.avaritia26.entity.UmbrellaProjectileEntity;
import io.github.aspshijiu.avaritia26.event.ModArmorEvents;
import io.github.aspshijiu.avaritia26.event.ModCombatEvents;
import io.github.aspshijiu.avaritia26.event.ModClockEvents;
import io.github.aspshijiu.avaritia26.event.ModToolEvents;
import io.github.aspshijiu.avaritia26.inventory.CompressedChestMenu;
import io.github.aspshijiu.avaritia26.inventory.EndCraftingMenu;
import io.github.aspshijiu.avaritia26.inventory.ExtremeCraftingMenu;
import io.github.aspshijiu.avaritia26.inventory.ExtremeAnvilMenu;
import io.github.aspshijiu.avaritia26.inventory.ExtremeSmithingMenu;
import io.github.aspshijiu.avaritia26.inventory.InfinityChestMenu;
import io.github.aspshijiu.avaritia26.inventory.InfinityClockMenu;
import io.github.aspshijiu.avaritia26.inventory.NetherCraftingMenu;
import io.github.aspshijiu.avaritia26.inventory.NeutronCollectorMenu;
import io.github.aspshijiu.avaritia26.inventory.NeutronCompressorMenu;
import io.github.aspshijiu.avaritia26.inventory.NeutronRingMenu;
import io.github.aspshijiu.avaritia26.inventory.SculkCraftingMenu;
import io.github.aspshijiu.avaritia26.item.BlazeAxeItem;
import io.github.aspshijiu.avaritia26.item.BlazeBowItem;
import io.github.aspshijiu.avaritia26.item.BlazeHoeItem;
import io.github.aspshijiu.avaritia26.item.BlazePickaxeItem;
import io.github.aspshijiu.avaritia26.item.BlazeShovelItem;
import io.github.aspshijiu.avaritia26.item.BlazeSwordItem;
import io.github.aspshijiu.avaritia26.item.CrystalBowItem;
import io.github.aspshijiu.avaritia26.item.CrystalSwordItem;
import io.github.aspshijiu.avaritia26.item.MatterClusterItem;
import io.github.aspshijiu.avaritia26.item.NeutronRingItem;
import io.github.aspshijiu.avaritia26.item.InfinityArmorItem;
import io.github.aspshijiu.avaritia26.item.InfinityBucketItem;
import io.github.aspshijiu.avaritia26.item.InfinityClockItem;
import io.github.aspshijiu.avaritia26.item.InfinityCrossbowItem;
import io.github.aspshijiu.avaritia26.item.InfinityElytraItem;
import io.github.aspshijiu.avaritia26.item.InfinityMaceItem;
import io.github.aspshijiu.avaritia26.item.InfinityTridentItem;
import io.github.aspshijiu.avaritia26.item.InfinityUmbrellaItem;
import io.github.aspshijiu.avaritia26.item.SingularityItem;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import io.github.aspshijiu.avaritia26.registry.ModMobEffects;
import io.github.aspshijiu.avaritia26.network.SetTimePayload;
import io.github.aspshijiu.avaritia26.registry.ModArmorMaterials;
import io.github.aspshijiu.avaritia26.singularity.SingularityDefinition;
import io.github.aspshijiu.avaritia26.singularity.SingularityManager;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.gametest.v1.CustomTestMethodInvoker;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.projectile.arrow.SpectralArrow;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEgg;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.UseCooldown;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.enchantment.Repairable;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
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
	public void neutronGearRegistersAndCrafts(GameTestHelper helper) {
		ItemStack gear = new ItemStack(ModItems.NEUTRON_GEAR);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.NEUTRON_GEAR_KEY) == ModItems.NEUTRON_GEAR,
				"中子齿轮没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(gear.getRarity() == Rarity.RARE, "中子齿轮稀有度错误");
		CraftingInput input = CraftingInput.of(3, 3, List.of(
				ItemStack.EMPTY, new ItemStack(ModItems.NEUTRON_INGOT), ItemStack.EMPTY,
				new ItemStack(ModItems.NEUTRON_INGOT), new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT), new ItemStack(ModItems.NEUTRON_INGOT),
				ItemStack.EMPTY, new ItemStack(ModItems.NEUTRON_INGOT), ItemStack.EMPTY
		));
		assertCraftingRecipe(helper, "neutron_gear", input, ModItems.NEUTRON_GEAR, 1);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(4, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("neutron_gear"));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(RecipeType.CRAFTING, CraftingInput.of(3, 3, wrongStacks), helper.getLevel())
						.filter(recipe -> recipe.id().equals(recipeKey))
						.isPresent(),
				"中子齿轮中心材料错误时不应匹配配方"
		);
		helper.succeed();
	}

	@GameTest
	public void compressedChestCraftsStoresAndPreservesContents(GameTestHelper helper) {
		helper.assertTrue(BuiltInRegistries.BLOCK.getValue(ModBlocks.COMPRESSED_CHEST_KEY) == ModBlocks.COMPRESSED_CHEST, "压缩箱子方块注册错误");
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModBlocks.COMPRESSED_CHEST_ITEM_KEY) == ModBlocks.COMPRESSED_CHEST_ITEM, "压缩箱子物品注册错误");
		CraftingInput input = CraftingInput.of(3, 3, List.of(
				new ItemStack(Items.CHEST), new ItemStack(Items.CHEST), new ItemStack(Items.CHEST),
				new ItemStack(Items.CHEST), new ItemStack(ModItems.NEUTRON_GEAR), new ItemStack(Items.CHEST),
				new ItemStack(Items.CHEST), new ItemStack(Items.CHEST), new ItemStack(Items.CHEST)
		));
		assertCraftingRecipe(helper, "compressed_chest", input, ModBlocks.COMPRESSED_CHEST_ITEM, 1);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(4, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("compressed_chest"));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(RecipeType.CRAFTING, CraftingInput.of(3, 3, wrongStacks), helper.getLevel())
						.filter(recipe -> recipe.id().equals(recipeKey)).isPresent(),
				"压缩箱子中心材料错误时不应匹配配方"
		);

		BlockPos pos = new BlockPos(5, 0, 5);
		helper.setBlock(pos, ModBlocks.COMPRESSED_CHEST);
		CompressedChestBlockEntity chest = helper.getBlockEntity(pos, CompressedChestBlockEntity.class);
		helper.assertTrue(chest.getContainerSize() == 243, "压缩箱子容量应为 243 槽");
		chest.setItem(0, new ItemStack(Items.DIAMOND, 32));
		chest.setItem(242, new ItemStack(Items.NETHERITE_INGOT, 7));
		BlockEntity loaded = BlockEntity.loadStatic(
				helper.absolutePos(pos), helper.getBlockState(pos), chest.saveWithFullMetadata(helper.getLevel().registryAccess()), helper.getLevel().registryAccess()
		);
		helper.assertTrue(loaded instanceof CompressedChestBlockEntity, "压缩箱子无法从存档恢复");
		CompressedChestBlockEntity loadedChest = (CompressedChestBlockEntity) loaded;
		helper.assertTrue(loadedChest.getItem(0).getCount() == 32 && loadedChest.getItem(242).getCount() == 7, "压缩箱子首尾槽位存档错误");

		ItemStack clone = ((CompressedChestBlock) ModBlocks.COMPRESSED_CHEST).getCloneItemStack(
				helper.getLevel(), helper.absolutePos(pos), helper.getBlockState(pos), true
		);
		CompressedChestBlockEntity restored = new CompressedChestBlockEntity(helper.absolutePos(pos), helper.getBlockState(pos));
		restored.applyComponentsFromItemStack(clone);
		helper.assertTrue(restored.getItem(0).getCount() == 32 && restored.getItem(242).getCount() == 7, "压缩箱子物品没有保留全部内容");

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		CompressedChestMenu menu = new CompressedChestMenu(1, player.getInventory(), chest);
		helper.assertTrue(menu.slots.size() == 279, "压缩箱子菜单应包含 243 个箱子槽和 36 个玩家槽");
		menu.removed(player);
		helper.succeed();
	}

	@GameTest
	public void infinityChestSmithsStoresHugeStacksAndPreservesContents(GameTestHelper helper) {
		helper.assertTrue(BuiltInRegistries.BLOCK.getValue(ModBlocks.INFINITY_CHEST_KEY) == ModBlocks.INFINITY_CHEST, "无尽箱方块注册错误");
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModBlocks.INFINITY_CHEST_ITEM_KEY) == ModBlocks.INFINITY_CHEST_ITEM, "无尽箱物品注册错误");
		helper.assertTrue(
				BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(Avaritia26.id("infinity_chest")) == ModBlockEntities.INFINITY_CHEST,
				"无尽箱方块实体注册错误"
		);
		helper.assertTrue(
				BuiltInRegistries.DATA_COMPONENT_TYPE.getValue(Avaritia26.id("infinity_chest_contents"))
						== ModDataComponents.INFINITY_CHEST_CONTENTS,
				"无尽箱内容组件注册错误"
		);

		ItemStack compressedBase = new ItemStack(ModBlocks.COMPRESSED_CHEST_ITEM);
		compressedBase.set(
				DataComponents.CONTAINER,
				ItemContainerContents.fromItems(List.of(new ItemStack(Items.DIAMOND, 32)))
		);
		ExtremeSmithingInput input = new ExtremeSmithingInput(
				new ItemStack(ModItems.UPGRADE_SMITHING_TEMPLATE),
				compressedBase,
				new ItemStack(ModItems.ENHANCEMENT_CORE),
				new ItemStack(ModBlocks.INFINITY_ITEM),
				new ItemStack(ModBlocks.NEUTRON_ITEM)
		);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_chest"));
		RecipeHolder<ExtremeSmithingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_SMITHING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("未匹配无尽箱强化锻造配方"));
		helper.assertTrue(recipe.id().equals(recipeKey), "无尽箱匹配到了错误强化锻造配方");
		ItemStack result = recipe.value().assemble(input);
		helper.assertTrue(result.is(ModBlocks.INFINITY_CHEST_ITEM), "无尽箱强化锻造输出错误");
		InfinityChestBlockEntity craftedChest = new InfinityChestBlockEntity(
				helper.absolutePos(new BlockPos(8, 0, 8)),
				ModBlocks.INFINITY_CHEST.defaultBlockState()
		);
		craftedChest.applyComponentsFromItemStack(result);
		helper.assertTrue(craftedChest.getItem(0).is(Items.DIAMOND) && craftedChest.getItem(0).getCount() == 32, "无尽箱锻造丢失压缩箱内容");
		ExtremeSmithingInput duplicates = new ExtremeSmithingInput(
				input.template(),
				input.base(),
				new ItemStack(ModBlocks.NEUTRON_ITEM),
				new ItemStack(ModBlocks.NEUTRON_ITEM),
				new ItemStack(ModBlocks.NEUTRON_ITEM)
		);
		helper.assertFalse(recipe.value().matches(duplicates, helper.getLevel()), "无尽箱配方不应接受重复追加材料");

		BlockPos pos = new BlockPos(5, 0, 5);
		helper.setBlock(pos, ModBlocks.INFINITY_CHEST);
		InfinityChestBlockEntity chest = helper.getBlockEntity(pos, InfinityChestBlockEntity.class);
		helper.assertTrue(chest.getContainerSize() == 300, "无尽箱容量应为 300 槽");
		helper.assertTrue(chest.getMaxStackSize() == Integer.MAX_VALUE, "无尽箱每槽上限应为 int 最大值");
		chest.setItem(0, new ItemStack(Items.DIAMOND, 1_000_000));
		chest.setItem(299, new ItemStack(Items.NETHERITE_INGOT, 2_000_000));
		BlockEntity loaded = BlockEntity.loadStatic(
				helper.absolutePos(pos),
				helper.getBlockState(pos),
				chest.saveWithFullMetadata(helper.getLevel().registryAccess()),
				helper.getLevel().registryAccess()
		);
		helper.assertTrue(loaded instanceof InfinityChestBlockEntity, "无尽箱无法从存档恢复");
		InfinityChestBlockEntity loadedChest = (InfinityChestBlockEntity) loaded;
		helper.assertTrue(
				loadedChest.getItem(0).getCount() == 1_000_000 && loadedChest.getItem(299).getCount() == 2_000_000,
				"无尽箱首尾槽巨量堆叠存档错误"
		);

		ItemStack clone = ((InfinityChestBlock) ModBlocks.INFINITY_CHEST)
				.getCloneItemStack(helper.getLevel(), helper.absolutePos(pos), helper.getBlockState(pos), true);
		InfinityChestBlockEntity restored = new InfinityChestBlockEntity(helper.absolutePos(pos), helper.getBlockState(pos));
		restored.applyComponentsFromItemStack(clone);
		helper.assertTrue(
				restored.getItem(0).getCount() == 1_000_000 && restored.getItem(299).getCount() == 2_000_000,
				"无尽箱方块物品没有保留 300 槽巨量内容"
		);

		var ops = helper.getLevel().registryAccess().createSerializationContext(JsonOps.INSTANCE);
		var encoded = ItemStack.CODEC.encodeStart(ops, clone).getOrThrow();
		ItemStack decodedClone = ItemStack.CODEC.parse(ops, encoded).getOrThrow();
		helper.assertTrue(
				clone.get(ModDataComponents.INFINITY_CHEST_CONTENTS)
						.equals(decodedClone.get(ModDataComponents.INFINITY_CHEST_CONTENTS)),
				"无尽箱物品存档往返后内容组件不相等"
		);
		InfinityChestBlockEntity decodedChest = new InfinityChestBlockEntity(helper.absolutePos(pos), helper.getBlockState(pos));
		decodedChest.applyComponentsFromItemStack(decodedClone);
		helper.assertTrue(decodedChest.getItem(299).getCount() == 2_000_000, "无尽箱物品存档编解码丢失巨量内容");
		InfinityChestContents contents = clone.get(ModDataComponents.INFINITY_CHEST_CONTENTS);
		helper.assertTrue(contents != null, "无尽箱方块物品缺少内容组件");
		RegistryFriendlyByteBuf buffer = new RegistryFriendlyByteBuf(Unpooled.buffer(), helper.getLevel().registryAccess());
		try {
			InfinityChestContents.STREAM_CODEC.encode(buffer, contents);
			InfinityChestContents decoded = InfinityChestContents.STREAM_CODEC.decode(buffer);
			helper.assertTrue(contents.equals(decoded) && contents.hashCode() == decoded.hashCode(), "无尽箱内容组件网络往返后不相等");
			NonNullList<ItemStack> networkItems = NonNullList.withSize(300, ItemStack.EMPTY);
			decoded.copyInto(networkItems);
			helper.assertTrue(networkItems.get(0).getCount() == 1_000_000 && networkItems.get(299).getCount() == 2_000_000, "无尽箱网络同步往返错误");
			ItemStack.STREAM_CODEC.encode(buffer, clone);
			ItemStack networkClone = ItemStack.STREAM_CODEC.decode(buffer);
			InfinityChestBlockEntity networkChest = new InfinityChestBlockEntity(helper.absolutePos(pos), helper.getBlockState(pos));
			networkChest.applyComponentsFromItemStack(networkClone);
			helper.assertTrue(networkChest.getItem(299).getCount() == 2_000_000, "无尽箱方块物品网络同步丢失巨量内容");
		} finally {
			buffer.release();
		}

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		InfinityChestMenu menu = new InfinityChestMenu(1, player.getInventory(), chest);
		helper.assertTrue(menu.slots.size() == 336, "无尽箱菜单应包含 300 个箱子槽和 36 个玩家槽");
		helper.assertTrue(menu.getSlot(299).getMaxStackSize(new ItemStack(Items.DIAMOND)) == Integer.MAX_VALUE, "无尽箱最后一个槽位不可访问巨量堆叠");
		player.getInventory().setItem(0, new ItemStack(Items.DIAMOND, 64));
		menu.quickMoveStack(player, InfinityChestMenu.PLAYER_SLOT_START + 27);
		helper.assertTrue(chest.getItem(0).getCount() == 1_000_064, "玩家物品没有合并进无尽箱巨量堆叠");
		menu.quickMoveStack(player, 0);
		helper.assertTrue(chest.getItem(0).getCount() == 1_000_000, "从无尽箱快捷取出没有按普通物品堆叠上限取出");
		helper.assertTrue(player.getInventory().countItem(Items.DIAMOND) == 64, "无尽箱快捷取出数量错误");
		menu.removed(player);
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
		helper.assertTrue(recipe.value() instanceof InfinityCatalystRecipe, "无尽催化剂没有使用动态配方类型");
		RegistryFriendlyByteBuf recipeBuffer = new RegistryFriendlyByteBuf(
				Unpooled.buffer(),
				helper.getLevel().registryAccess()
		);
		try {
			InfinityCatalystRecipe.STREAM_CODEC.encode(recipeBuffer, (InfinityCatalystRecipe) recipe.value());
			InfinityCatalystRecipe decoded = InfinityCatalystRecipe.STREAM_CODEC.decode(recipeBuffer);
			helper.assertTrue(decoded.matches(input, helper.getLevel()), "无尽催化剂配方网络同步往返后不再匹配");
			helper.assertTrue(
					decoded.assemble(input).is(ModItems.INFINITY_CATALYST),
					"无尽催化剂配方网络同步丢失输出"
			);
		} finally {
			recipeBuffer.release();
		}

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
	public void infinityNuggetCraftsAndRecombines(GameTestHelper helper) {
		ItemStack nugget = new ItemStack(ModItems.INFINITY_NUGGET);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_NUGGET_KEY) == ModItems.INFINITY_NUGGET,
				"无尽粒没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(nugget.getMaxStackSize() == 64, "无尽粒应当堆叠 64 个");
		helper.assertTrue(nugget.getRarity() == Rarity.EPIC, "无尽粒应当是 EPIC 稀有度");
		helper.assertTrue(nugget.has(DataComponents.DAMAGE_RESISTANT), "无尽粒应当具有防火伤害抗性组件");
		List<Component> tooltip = new java.util.ArrayList<>();
		ModItems.INFINITY_NUGGET.appendHoverText(
				nugget,
				Item.TooltipContext.of(helper.getLevel()),
				TooltipDisplay.DEFAULT,
				tooltip::add,
				TooltipFlag.NORMAL
		);
		helper.assertTrue(tooltip.size() == 1 && tooltip.getFirst().getStyle().isItalic(), "无尽粒缺少经典斜体说明");

		CraftingInput unpackInput = CraftingInput.of(1, 1, List.of(new ItemStack(ModItems.INFINITY_INGOT)));
		assertCraftingRecipe(helper, "infinity_nugget_from_ingot", unpackInput, ModItems.INFINITY_NUGGET, 9);
		CraftingInput packInput = filledCraftingInput(ModItems.INFINITY_NUGGET);
		assertCraftingRecipe(helper, "infinity_ingot_from_nuggets", packInput, ModItems.INFINITY_INGOT, 1);
		List<ItemStack> wrongStacks = copyStacks(packInput.items());
		wrongStacks.set(4, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("infinity_ingot_from_nuggets")
		);
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(RecipeType.CRAFTING, CraftingInput.of(3, 3, wrongStacks), helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey)).isPresent(),
				"无尽粒回压配方不应接受错误材料"
		);
		helper.succeed();
	}

	@GameTest
	public void enhancementCoreCraftsWithExactPattern(GameTestHelper helper) {
		ItemStack core = new ItemStack(ModItems.ENHANCEMENT_CORE);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.ENHANCEMENT_CORE_KEY) == ModItems.ENHANCEMENT_CORE,
				"强化核心没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(core.getMaxStackSize() == 64, "强化核心应当堆叠 64 个");
		helper.assertTrue(core.getRarity() == Rarity.EPIC, "强化核心应当使用原版最高 EPIC 稀有度");
		helper.assertTrue(core.has(DataComponents.DAMAGE_RESISTANT), "强化核心应当具有防火伤害抗性组件");
		List<Component> tooltip = new java.util.ArrayList<>();
		ModItems.ENHANCEMENT_CORE.appendHoverText(
				core,
				Item.TooltipContext.of(helper.getLevel()),
				TooltipDisplay.DEFAULT,
				tooltip::add,
				TooltipFlag.NORMAL
		);
		helper.assertTrue(tooltip.size() == 1 && tooltip.getFirst().getStyle().isItalic(), "强化核心缺少经典斜体说明");

		CraftingInput input = enhancementCoreInput();
		assertExtremeRecipe(helper, "enhancement_core", input, ModItems.ENHANCEMENT_CORE);
		assertWrongExtremeRecipe(helper, "enhancement_core", input, "强化核心不应接受错误材料");
		helper.succeed();
	}

	@GameTest
	public void upgradeSmithingTemplateCraftsAndDuplicates(GameTestHelper helper) {
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.UPGRADE_SMITHING_TEMPLATE_KEY)
						== ModItems.UPGRADE_SMITHING_TEMPLATE,
				"强化锻造模板没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				ModItems.UPGRADE_SMITHING_TEMPLATE instanceof SmithingTemplateItem template
						&& template.getBaseSlotEmptyIcons().size() == 5
						&& template.getBaseSlotEmptyIcons().contains(Identifier.withDefaultNamespace("container/slot/sword"))
						&& template.getAdditionalSlotEmptyIcons().equals(
								List.of(Identifier.withDefaultNamespace("container/slot/ingot"))
						),
				"强化锻造模板的工具与方块槽位提示错误"
		);

		CraftingInput craftingInput = upgradeSmithingTemplateInput();
		assertExtremeRecipe(
				helper,
				"upgrade_smithing_template",
				craftingInput,
				ModItems.UPGRADE_SMITHING_TEMPLATE
		);
		assertWrongExtremeRecipe(
				helper,
				"upgrade_smithing_template",
				craftingInput,
				"强化锻造模板初次配方不应接受错误材料"
		);

		CraftingInput duplicateInput = upgradeSmithingTemplateDuplicateInput();
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("upgrade_smithing_template_too")
		);
		RecipeHolder<Recipe<CraftingInput>> duplicateRecipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, duplicateInput, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("未匹配强化锻造模板复制配方"));
		helper.assertTrue(duplicateRecipe.id().equals(recipeKey), "强化锻造模板复制时匹配到了错误配方");
		ItemStack result = duplicateRecipe.value().assemble(duplicateInput);
		helper.assertTrue(result.is(ModItems.UPGRADE_SMITHING_TEMPLATE) && result.getCount() == 2, "强化锻造模板复制输出错误");
		helper.assertTrue(duplicateRecipe.value() instanceof NoConsumeCatalystShapedRecipe, "复制配方没有使用催化剂保留类型");
		NoConsumeCatalystShapedRecipe recipe = (NoConsumeCatalystShapedRecipe) duplicateRecipe.value();
		NonNullList<ItemStack> remaining = recipe.getRemainingItems(duplicateInput);
		for (int slot = 0; slot < duplicateInput.size(); slot++) {
			if (duplicateInput.getItem(slot).is(ModItems.INFINITY_CATALYST)) {
				helper.assertTrue(remaining.get(slot).is(ModItems.INFINITY_CATALYST), "复制配方消耗了无尽催化剂");
			} else {
				helper.assertTrue(remaining.get(slot).isEmpty(), "复制配方错误保留了其他材料");
			}
		}
		helper.assertTrue(remaining.stream().filter(stack -> !stack.isEmpty()).count() == 8, "复制配方应当保留 8 个无尽催化剂");

		BlockPos tablePos = new BlockPos(5, 0, 5);
		helper.setBlock(tablePos, ModBlocks.EXTREME_CRAFTING_TABLE);
		ExtremeCraftingTableBlockEntity table = helper.getBlockEntity(tablePos, ExtremeCraftingTableBlockEntity.class);
		for (int row = 0; row < duplicateInput.height(); row++) {
			for (int column = 0; column < duplicateInput.width(); column++) {
				table.setItem(column + row * 9, duplicateInput.getItem(column, row).copy());
			}
		}
		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		ExtremeCraftingMenu menu = (ExtremeCraftingMenu) table.createMenu(1, player.getInventory(), player);
		menu.slotsChanged(table);
		ItemStack menuResult = menu.getSlot(ExtremeCraftingMenu.RESULT_SLOT).getItem();
		helper.assertTrue(
				menuResult.is(ModItems.UPGRADE_SMITHING_TEMPLATE) && menuResult.getCount() == 2,
				"终极工作台没有显示两个复制模板，实际结果：" + menuResult
		);
		ItemStack taken = menu.getSlot(ExtremeCraftingMenu.RESULT_SLOT).remove(menuResult.getCount());
		menu.getSlot(ExtremeCraftingMenu.RESULT_SLOT).onTake(player, taken);
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				ItemStack stored = menu.getSlot(column + row * 9 + ExtremeCraftingMenu.INPUT_SLOT_START).getItem();
				ItemStack source = row < duplicateInput.height() && column < duplicateInput.width()
						? duplicateInput.getItem(column, row) : ItemStack.EMPTY;
				if (source.is(ModItems.INFINITY_CATALYST)) {
					helper.assertTrue(stored.is(ModItems.INFINITY_CATALYST) && stored.getCount() == 1, "终极工作台实际消耗了无尽催化剂");
				} else {
					helper.assertTrue(stored.isEmpty(), "终极工作台实际复制后仍残留普通材料");
				}
			}
		}
		menu.removed(player);
		assertWrongExtremeRecipe(
				helper,
				"upgrade_smithing_template_too",
				duplicateInput,
				"强化锻造模板复制配方不应接受错误材料"
		);
		helper.succeed();
	}

	@GameTest
	public void extremeSmithingTableCraftsAndConsumesFiveInputs(GameTestHelper helper) {
		helper.assertTrue(
				BuiltInRegistries.BLOCK.getValue(ModBlocks.EXTREME_SMITHING_TABLE_KEY)
						== ModBlocks.EXTREME_SMITHING_TABLE,
				"终焉锻造台没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModBlocks.EXTREME_SMITHING_TABLE_ITEM_KEY)
						== ModBlocks.EXTREME_SMITHING_TABLE_ITEM,
				"终焉锻造台物品没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				BuiltInRegistries.RECIPE_TYPE.getValue(Avaritia26.id("extreme_smithing")) == ModRecipes.EXTREME_SMITHING,
				"强化锻造配方类型没有注册"
		);
		helper.assertTrue(
				BuiltInRegistries.RECIPE_SERIALIZER.getValue(Avaritia26.id("extreme_smithing"))
						== ModRecipes.EXTREME_SMITHING_SERIALIZER,
				"强化锻造配方序列化器没有注册"
		);

		CraftingInput tableInput = extremeSmithingTableInput();
		assertExtremeRecipe(helper, "extreme_smithing_table", tableInput, ModBlocks.EXTREME_SMITHING_TABLE_ITEM);
		assertWrongExtremeRecipe(
				helper,
				"extreme_smithing_table",
				tableInput,
				"终焉锻造台配方不应接受错误材料"
		);

		ItemStack namedBase = new ItemStack(Items.IRON_SWORD);
		namedBase.set(DataComponents.CUSTOM_NAME, Component.literal("保留名称"));
		ExtremeSmithingInput smithingInput = new ExtremeSmithingInput(
				new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
				namedBase,
				new ItemStack(Items.NETHERITE_INGOT),
				new ItemStack(Items.DIAMOND),
				new ItemStack(Items.GOLD_INGOT)
		);
		RecipeHolder<ExtremeSmithingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_SMITHING, smithingInput, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("未匹配 GameTest 强化锻造配方"));
		helper.assertTrue(
				recipe.id().identifier().equals(Identifier.fromNamespaceAndPath("avaritia26-gametest", "extreme_smithing_test")),
				"强化锻造匹配到了错误配方"
		);
		ItemStack assembled = recipe.value().assemble(smithingInput);
		helper.assertTrue(assembled.is(Items.NETHERITE_SWORD), "强化锻造输出类型错误");
		helper.assertTrue(
				Component.literal("保留名称").equals(assembled.get(DataComponents.CUSTOM_NAME)),
				"强化锻造没有继承基底组件"
		);
		RegistryFriendlyByteBuf recipeBuffer = new RegistryFriendlyByteBuf(
				Unpooled.buffer(),
				helper.getLevel().registryAccess()
		);
		try {
			ExtremeSmithingRecipe.STREAM_CODEC.encode(recipeBuffer, recipe.value());
			ExtremeSmithingRecipe decoded = ExtremeSmithingRecipe.STREAM_CODEC.decode(recipeBuffer);
			helper.assertTrue(decoded.matches(smithingInput, helper.getLevel()), "强化锻造配方网络同步往返后不再匹配");
			helper.assertTrue(decoded.assemble(smithingInput).is(Items.NETHERITE_SWORD), "强化锻造配方网络同步丢失输出");
		} finally {
			recipeBuffer.release();
		}
		ExtremeSmithingInput duplicateAdditions = new ExtremeSmithingInput(
				smithingInput.template(),
				smithingInput.base(),
				new ItemStack(Items.DIAMOND),
				new ItemStack(Items.DIAMOND),
				new ItemStack(Items.DIAMOND)
		);
		helper.assertFalse(recipe.value().matches(duplicateAdditions, helper.getLevel()), "三份重复追加材料不应通过强化锻造");

		BlockPos tablePos = new BlockPos(5, 0, 5);
		helper.setBlock(tablePos, ModBlocks.EXTREME_SMITHING_TABLE);
		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		ExtremeSmithingMenu menu = new ExtremeSmithingMenu(1, player.getInventory(), helper.absolutePos(tablePos));
		menu.getSlot(ExtremeSmithingMenu.TEMPLATE_SLOT).set(new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE));
		menu.getSlot(ExtremeSmithingMenu.BASE_SLOT).set(namedBase.copy());
		menu.getSlot(ExtremeSmithingMenu.ADDITION_SLOT_START).set(new ItemStack(Items.GOLD_INGOT));
		menu.getSlot(ExtremeSmithingMenu.ADDITION_SLOT_START + 1).set(new ItemStack(Items.NETHERITE_INGOT));
		menu.getSlot(ExtremeSmithingMenu.ADDITION_SLOT_START + 2).set(new ItemStack(Items.DIAMOND));
		menu.slotsChanged(menu.getSlot(ExtremeSmithingMenu.TEMPLATE_SLOT).container);
		ItemStack menuResult = menu.getSlot(ExtremeSmithingMenu.RESULT_SLOT).getItem();
		helper.assertTrue(menuResult.is(Items.NETHERITE_SWORD), "终焉锻造台没有显示预期结果");
		ItemStack taken = menu.getSlot(ExtremeSmithingMenu.RESULT_SLOT).remove(1);
		menu.getSlot(ExtremeSmithingMenu.RESULT_SLOT).onTake(player, taken);
		for (int slot = ExtremeSmithingMenu.TEMPLATE_SLOT; slot < ExtremeSmithingMenu.ADDITION_SLOT_END; slot++) {
			helper.assertTrue(menu.getSlot(slot).getItem().isEmpty(), "终焉锻造台没有消耗第 " + slot + " 个输入");
		}
		helper.assertTrue(menu.getSlot(ExtremeSmithingMenu.RESULT_SLOT).getItem().isEmpty(), "取出后仍残留锻造结果");
		menu.removed(player);
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
	public void diamondLatticeBlockResourceChainWorks(GameTestHelper helper) {
		helper.assertTrue(
				BuiltInRegistries.BLOCK.getValue(ModBlocks.DIAMOND_LATTICE_BLOCK_KEY)
						== ModBlocks.DIAMOND_LATTICE_BLOCK,
				"钻石晶格块没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModBlocks.DIAMOND_LATTICE_BLOCK_ITEM_KEY)
						== ModBlocks.DIAMOND_LATTICE_BLOCK_ITEM,
				"钻石晶格块物品没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				Block.byItem(ModBlocks.DIAMOND_LATTICE_BLOCK_ITEM) == ModBlocks.DIAMOND_LATTICE_BLOCK,
				"钻石晶格块物品没有关联到方块"
		);
		ItemStack blockStack = new ItemStack(ModBlocks.DIAMOND_LATTICE_BLOCK_ITEM);
		helper.assertTrue(blockStack.getRarity() == Rarity.UNCOMMON, "钻石晶格块应当是 UNCOMMON 稀有度");
		helper.assertTrue(
				ModBlocks.DIAMOND_LATTICE_BLOCK.getExplosionResistance() == 100.0F,
				"钻石晶格块爆炸抗性应当是 100"
		);

		BlockPos relativePos = new BlockPos(20, 0, 0);
		helper.setBlock(relativePos, ModBlocks.DIAMOND_LATTICE_BLOCK);
		helper.assertBlockPresent(ModBlocks.DIAMOND_LATTICE_BLOCK, relativePos);
		helper.assertTrue(
				helper.getBlockState(relativePos).getDestroySpeed(helper.getLevel(), helper.absolutePos(relativePos))
						== 100.0F,
				"钻石晶格块硬度应当是 100"
		);
		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos),
				helper.getLevel(),
				helper.absolutePos(relativePos),
				null
		);
		helper.assertTrue(
				drops.size() == 1 && drops.getFirst().is(ModBlocks.DIAMOND_LATTICE_BLOCK_ITEM),
				"钻石晶格块应当掉落自身"
		);

		CraftingInput packInput = filledCraftingInput(ModItems.DIAMOND_LATTICE);
		assertCraftingRecipe(
				helper,
				"diamond_lattice_block",
				packInput,
				ModBlocks.DIAMOND_LATTICE_BLOCK_ITEM,
				1
		);
		assertCraftingRecipe(
				helper,
				"diamond_lattice_from_block",
				CraftingInput.of(1, 1, List.of(blockStack)),
				ModItems.DIAMOND_LATTICE,
				9
		);
		List<ItemStack> wrongStacks = copyStacks(packInput.items());
		wrongStacks.set(4, new ItemStack(Items.DIAMOND));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE,
				Avaritia26.id("diamond_lattice_block")
		);
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(RecipeType.CRAFTING, CraftingInput.of(3, 3, wrongStacks), helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"钻石晶格块配方不应接受错误材料"
		);
		helper.succeed();
	}

	@GameTest
	public void starFuelResourceChainBurnsIndefinitely(GameTestHelper helper) {
		ItemStack fuel = new ItemStack(ModItems.STAR_FUEL);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.STAR_FUEL_KEY) == ModItems.STAR_FUEL,
				"星辰燃料没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(fuel.getMaxStackSize() == 16, "星辰燃料应当堆叠 16 个");
		helper.assertTrue(fuel.getRarity() == Rarity.RARE, "星辰燃料应当是 RARE 稀有度");
		helper.assertTrue(
				helper.getLevel().fuelValues().burnDuration(fuel) == Integer.MAX_VALUE,
				"星辰燃料燃烧时间应当是 int 最大值"
		);
		List<Component> tooltip = new java.util.ArrayList<>();
		ModItems.STAR_FUEL.appendHoverText(
				fuel,
				Item.TooltipContext.of(helper.getLevel()),
				TooltipDisplay.DEFAULT,
				tooltip::add,
				TooltipFlag.NORMAL
		);
		helper.assertTrue(tooltip.size() == 1 && tooltip.getFirst().getStyle().isItalic(), "星辰燃料缺少经典斜体说明");

		CraftingInput fuelInput = starFuelInput();
		ResourceKey<Recipe<?>> fuelRecipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("star_fuel"));
		RecipeHolder<Recipe<CraftingInput>> fuelRecipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, fuelInput, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("正确材料未匹配星辰燃料配方"));
		helper.assertTrue(fuelRecipe.id().equals(fuelRecipeKey), "星辰燃料材料匹配到了错误配方");
		helper.assertTrue(fuelRecipe.value().assemble(fuelInput).is(ModItems.STAR_FUEL), "星辰燃料配方输出错误");
		NonNullList<ItemStack> remainders = CraftingRecipe.defaultCraftingReminder(fuelInput);
		long bucketCount = remainders.stream().filter(stack -> stack.is(Items.BUCKET)).count();
		helper.assertTrue(
				bucketCount == 4,
				"星辰燃料配方应当返还四个空桶，实际为 " + bucketCount + "，余留物：" + remainders
		);
		assertWrongExtremeRecipe(helper, "star_fuel", fuelInput, "星辰燃料配方不应接受错误材料");

		BlockPos tablePos = new BlockPos(22, 0, 0);
		helper.setBlock(tablePos, ModBlocks.EXTREME_CRAFTING_TABLE);
		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		ExtremeCraftingMenu menu = new ExtremeCraftingMenu(4, player.getInventory(), helper.absolutePos(tablePos));
		for (int row = 0; row < fuelInput.height(); row++) {
			for (int column = 0; column < fuelInput.width(); column++) {
				menu.getSlot(ExtremeCraftingMenu.INPUT_SLOT_START + column + row * 9)
						.set(fuelInput.getItem(column, row).copy());
			}
		}
		menu.slotsChanged(menu.getSlot(ExtremeCraftingMenu.INPUT_SLOT_START).container);
		ItemStack menuResult = menu.getSlot(ExtremeCraftingMenu.RESULT_SLOT).getItem();
		helper.assertTrue(menuResult.is(ModItems.STAR_FUEL), "终极工作台没有显示星辰燃料结果");
		ItemStack taken = menu.getSlot(ExtremeCraftingMenu.RESULT_SLOT).remove(1);
		menu.getSlot(ExtremeCraftingMenu.RESULT_SLOT).onTake(player, taken);
		int menuBuckets = 0;
		for (int slot = ExtremeCraftingMenu.INPUT_SLOT_START; slot < ExtremeCraftingMenu.INPUT_SLOT_END; slot++) {
			ItemStack remaining = menu.getSlot(slot).getItem();
			if (remaining.is(Items.BUCKET)) {
				menuBuckets += remaining.getCount();
			} else {
				helper.assertTrue(remaining.isEmpty(), "星辰燃料合成后残留了非空桶材料");
			}
		}
		helper.assertTrue(menuBuckets == 4, "终极工作台实际合成后没有保留四个空桶");
		menu.removed(player);

		helper.assertTrue(
				BuiltInRegistries.BLOCK.getValue(ModBlocks.STAR_FUEL_BLOCK_KEY) == ModBlocks.STAR_FUEL_BLOCK,
				"星辰燃料块没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModBlocks.STAR_FUEL_BLOCK_ITEM_KEY) == ModBlocks.STAR_FUEL_BLOCK_ITEM,
				"星辰燃料块物品没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(Block.byItem(ModBlocks.STAR_FUEL_BLOCK_ITEM) == ModBlocks.STAR_FUEL_BLOCK, "星辰燃料块物品未关联方块");
		ItemStack blockStack = new ItemStack(ModBlocks.STAR_FUEL_BLOCK_ITEM);
		helper.assertTrue(blockStack.getRarity() == Rarity.RARE, "星辰燃料块应当是 RARE 稀有度");
		helper.assertTrue(
				helper.getLevel().fuelValues().burnDuration(blockStack) == Integer.MAX_VALUE,
				"星辰燃料块燃烧时间应当是 int 最大值"
		);
		helper.assertTrue(ModBlocks.STAR_FUEL_BLOCK.getExplosionResistance() == 200.0F, "星辰燃料块爆炸抗性应当是 200");

		BlockPos relativePos = new BlockPos(21, 0, 0);
		helper.setBlock(relativePos, ModBlocks.STAR_FUEL_BLOCK);
		helper.assertBlockPresent(ModBlocks.STAR_FUEL_BLOCK, relativePos);
		helper.assertTrue(
				helper.getBlockState(relativePos).getDestroySpeed(helper.getLevel(), helper.absolutePos(relativePos))
						== 100.0F,
				"星辰燃料块硬度应当是 100"
		);
		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos), helper.getLevel(), helper.absolutePos(relativePos), null
		);
		helper.assertTrue(drops.size() == 1 && drops.getFirst().is(ModBlocks.STAR_FUEL_BLOCK_ITEM), "星辰燃料块应当掉落自身");
		assertCraftingRecipe(
				helper,
				"star_fuel_block",
				filledCraftingInput(ModItems.STAR_FUEL),
				ModBlocks.STAR_FUEL_BLOCK_ITEM,
				1
		);
		assertCraftingRecipe(
				helper,
				"star_fuel_from_block",
				CraftingInput.of(1, 1, List.of(blockStack)),
				ModItems.STAR_FUEL,
				9
		);
		helper.succeed();
	}

	@GameTest
	public void refinedCoalResourceChainPowersAlternateStarFuel(GameTestHelper helper) {
		ItemStack coal = new ItemStack(ModItems.REFINED_COAL);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.REFINED_COAL_KEY) == ModItems.REFINED_COAL, "精炼煤炭注册错误");
		helper.assertTrue(coal.getMaxStackSize() == 32, "精炼煤炭应当堆叠 32 个");
		helper.assertTrue(coal.getRarity() == Rarity.UNCOMMON, "精炼煤炭应当是 UNCOMMON 稀有度");
		helper.assertTrue(helper.getLevel().fuelValues().burnDuration(coal) == 160_000, "精炼煤炭燃烧时间错误");
		List<Component> tooltip = new java.util.ArrayList<>();
		ModItems.REFINED_COAL.appendHoverText(
				coal, Item.TooltipContext.of(helper.getLevel()), TooltipDisplay.DEFAULT, tooltip::add, TooltipFlag.NORMAL
		);
		helper.assertTrue(tooltip.size() == 1 && tooltip.getFirst().getStyle().isItalic(), "精炼煤炭缺少经典斜体说明");

		CraftingInput coalInput = filledCraftingInput(Items.COAL);
		List<ItemStack> coalStacks = copyStacks(coalInput.items());
		coalStacks.set(4, new ItemStack(ModItems.NEUTRON_NUGGET));
		coalInput = CraftingInput.of(3, 3, coalStacks);
		assertExtremeRecipe(helper, "refined_coal", coalInput, ModItems.REFINED_COAL);
		assertWrongExtremeRecipe(helper, "refined_coal", coalInput, "精炼煤炭配方不应接受错误材料");

		helper.assertTrue(
				BuiltInRegistries.BLOCK.getValue(ModBlocks.REFINED_COAL_BLOCK_KEY) == ModBlocks.REFINED_COAL_BLOCK,
				"精炼煤炭块注册错误"
		);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModBlocks.REFINED_COAL_BLOCK_ITEM_KEY) == ModBlocks.REFINED_COAL_BLOCK_ITEM,
				"精炼煤炭块物品注册错误"
		);
		ItemStack blockStack = new ItemStack(ModBlocks.REFINED_COAL_BLOCK_ITEM);
		helper.assertTrue(Block.byItem(blockStack.getItem()) == ModBlocks.REFINED_COAL_BLOCK, "精炼煤炭块物品未关联方块");
		helper.assertTrue(blockStack.getRarity() == Rarity.UNCOMMON, "精炼煤炭块稀有度错误");
		helper.assertTrue(helper.getLevel().fuelValues().burnDuration(blockStack) == 1_440_000, "精炼煤炭块燃烧时间错误");
		helper.assertTrue(ModBlocks.REFINED_COAL_BLOCK.getExplosionResistance() == 50.0F, "精炼煤炭块爆炸抗性错误");
		BlockPos relativePos = new BlockPos(23, 0, 0);
		helper.setBlock(relativePos, ModBlocks.REFINED_COAL_BLOCK);
		helper.assertBlockPresent(ModBlocks.REFINED_COAL_BLOCK, relativePos);
		helper.assertTrue(
				helper.getBlockState(relativePos).getDestroySpeed(helper.getLevel(), helper.absolutePos(relativePos)) == 50.0F,
				"精炼煤炭块硬度错误"
		);
		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos), helper.getLevel(), helper.absolutePos(relativePos), null
		);
		helper.assertTrue(drops.size() == 1 && drops.getFirst().is(ModBlocks.REFINED_COAL_BLOCK_ITEM), "精炼煤炭块应当掉落自身");
		assertCraftingRecipe(
				helper, "refined_coal_block", filledCraftingInput(ModItems.REFINED_COAL), ModBlocks.REFINED_COAL_BLOCK_ITEM, 1
		);
		assertCraftingRecipe(
				helper, "refined_coal_from_block", CraftingInput.of(1, 1, List.of(blockStack)), ModItems.REFINED_COAL, 9
		);

		List<ItemStack> alternateStacks = new java.util.ArrayList<>(9);
		for (int index = 0; index < 9; index++) {
			alternateStacks.add(new ItemStack(index == 4 ? ModItems.ETERNAL_SINGULARITY : ModBlocks.REFINED_COAL_BLOCK_ITEM));
		}
		CraftingInput alternateInput = CraftingInput.of(3, 3, alternateStacks);
		assertExtremeRecipe(helper, "star_fuel_alternate", alternateInput, ModItems.STAR_FUEL);
		assertWrongExtremeRecipe(helper, "star_fuel_alternate", alternateInput, "星辰燃料替代配方不应接受错误材料");
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
	public void infinityPickaxeCraftsSwitchesAndBreaksClassicVolume(GameTestHelper helper) {
		ItemStack pickaxe = new ItemStack(ModItems.INFINITY_PICKAXE);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_PICKAXE_KEY) == ModItems.INFINITY_PICKAXE,
				"无尽镐没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(pickaxe.getMaxStackSize() == 1 && !pickaxe.isDamageableItem(), "无尽镐应当不可堆叠且永不损耗");
		helper.assertTrue(pickaxe.getRarity() == Rarity.EPIC && pickaxe.is(ItemTags.PICKAXES), "无尽镐稀有度或工具标签错误");
		helper.assertTrue(ModItems.INFINITY_PICKAXE.getDestroySpeed(pickaxe, Blocks.STONE.defaultBlockState()) == 9999.0F, "无尽镐普通模式速度错误");

		CraftingInput input = infinityPickaxeInput();
		assertExtremeRecipe(helper, "infinity_pickaxe", input, ModItems.INFINITY_PICKAXE);
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("未匹配无尽镐配方"));
		pickaxe = recipe.value().assemble(input);
		var fortune = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(fortune, pickaxe) == 10, "无尽镐配方产物缺少时运 X");
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(40, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_pickaxe"));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(9, 9, wrongStacks), helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"无尽镐中心材料错误时不应匹配配方"
		);

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, pickaxe);
		player.setShiftKeyDown(true);
		ModItems.INFINITY_PICKAXE.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(pickaxe.getOrDefault(ModDataComponents.INFINITY_PICKAXE_HAMMER, false), "无尽镐没有切换到锤模式");
		helper.assertTrue(ModItems.INFINITY_PICKAXE.getDestroySpeed(pickaxe, Blocks.STONE.defaultBlockState()) == 5.0F, "锤模式速度错误");
		var pig = helper.spawnWithNoFreeWill(EntityTypes.PIG, new BlockPos(2, 1, 2));
		pickaxe.postHurtEnemy(pig, player);
		helper.assertTrue(pig.getDeltaMovement().y >= 2.0, "无尽镐锤模式没有施加强击退");

		BlockPos origin = new BlockPos(16, 10, 16);
		BlockPos farStone = origin.offset(7, 7, 7);
		BlockPos farDirt = origin.offset(-8, -8, -8);
		BlockPos wood = origin.east();
		BlockPos bedrock = origin.east(2);
		BlockPos glass = origin.east(3);
		helper.setBlock(origin, Blocks.STONE);
		helper.setBlock(farStone, Blocks.STONE);
		helper.setBlock(farDirt, Blocks.DIRT);
		helper.setBlock(wood, Blocks.OAK_LOG);
		helper.setBlock(bedrock, Blocks.BEDROCK);
		helper.setBlock(glass, Blocks.GLASS);
		BlockPos absoluteOrigin = helper.absolutePos(origin);
		player.setPos(absoluteOrigin.getX() + 0.5, absoluteOrigin.getY(), absoluteOrigin.getZ() + 0.5);
		var result = AttackBlockCallback.EVENT.invoker().interact(
				player,
				helper.getLevel(),
				InteractionHand.MAIN_HAND,
				absoluteOrigin,
				Direction.UP
		);
		helper.assertTrue(result.consumesAction(), "无尽镐锤模式没有接管方块攻击");
		helper.assertBlockPresent(Blocks.AIR, origin);
		helper.assertBlockPresent(Blocks.AIR, farStone);
		helper.assertBlockPresent(Blocks.AIR, farDirt);
		helper.assertBlockPresent(Blocks.AIR, glass);
		helper.assertBlockPresent(Blocks.OAK_LOG, wood);
		helper.assertBlockPresent(Blocks.BEDROCK, bedrock);
		List<ItemEntity> clusters = helper.getLevel().getEntitiesOfClass(
				ItemEntity.class,
				new AABB(absoluteOrigin).inflate(2.0),
				entity -> entity.getItem().is(ModItems.MATTER_CLUSTER)
		);
		helper.assertTrue(clusters.size() == 1 && MatterClusterItem.getSize(clusters.getFirst().getItem()) == 3, "无尽镐没有把范围掉落压入物质团");
		helper.assertTrue(pickaxe.getDamageValue() == 0, "无尽镐范围挖掘后不应产生耐久损耗");
		helper.succeed();
	}

	@GameTest
	public void infinityShovelCraftsFlattensAndDestroysClassicVolume(GameTestHelper helper) {
		ItemStack shovel = new ItemStack(ModItems.INFINITY_SHOVEL);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_SHOVEL_KEY) == ModItems.INFINITY_SHOVEL,
				"无尽铲没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(shovel.getMaxStackSize() == 1 && !shovel.isDamageableItem(), "无尽铲应当不可堆叠且永不损耗");
		helper.assertTrue(shovel.getRarity() == Rarity.EPIC && shovel.is(ItemTags.SHOVELS), "无尽铲稀有度或工具标签错误");
		helper.assertTrue(ModItems.INFINITY_SHOVEL.getDestroySpeed(shovel, Blocks.DIRT.defaultBlockState()) == 9999.0F, "无尽铲普通模式速度错误");

		CraftingInput input = infinityShovelInput();
		assertExtremeRecipe(helper, "infinity_shovel", input, ModItems.INFINITY_SHOVEL);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(16, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_shovel"));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(9, 9, wrongStacks), helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"无尽铲中心材料错误时不应匹配配方"
		);

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, shovel);
		BlockPos pathPos = new BlockPos(3, 1, 3);
		helper.setBlock(pathPos, Blocks.GRASS_BLOCK);
		helper.setBlock(pathPos.above(), Blocks.AIR);
		BlockPos absolutePathPos = helper.absolutePos(pathPos);
		var flattenResult = ModItems.INFINITY_SHOVEL.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absolutePathPos), Direction.UP, absolutePathPos, false)
		));
		helper.assertTrue(flattenResult.consumesAction(), "无尽铲没有接管压径交互");
		helper.assertBlockPresent(Blocks.DIRT_PATH, pathPos);

		player.setShiftKeyDown(true);
		ModItems.INFINITY_SHOVEL.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(shovel.getOrDefault(ModDataComponents.INFINITY_SHOVEL_DESTROYER, false), "无尽铲没有切换到毁灭者模式");
		helper.assertTrue(ModItems.INFINITY_SHOVEL.getDestroySpeed(shovel, Blocks.DIRT.defaultBlockState()) == 5.0F, "毁灭者模式速度错误");

		BlockPos origin = new BlockPos(16, 10, 16);
		BlockPos farDirt = origin.offset(-8, -8, -8);
		BlockPos glass = origin.east();
		BlockPos wood = origin.east(2);
		BlockPos bedrock = origin.east(3);
		helper.setBlock(origin, Blocks.STONE);
		helper.setBlock(farDirt, Blocks.DIRT);
		helper.setBlock(glass, Blocks.GLASS);
		helper.setBlock(wood, Blocks.OAK_LOG);
		helper.setBlock(bedrock, Blocks.BEDROCK);
		BlockPos absoluteOrigin = helper.absolutePos(origin);
		player.setPos(absoluteOrigin.getX() + 0.5, absoluteOrigin.getY(), absoluteOrigin.getZ() + 0.5);
		var destroyResult = AttackBlockCallback.EVENT.invoker().interact(
				player,
				helper.getLevel(),
				InteractionHand.MAIN_HAND,
				absoluteOrigin,
				Direction.UP
		);
		helper.assertTrue(destroyResult.consumesAction(), "无尽铲毁灭者模式没有接管方块攻击");
		helper.assertBlockPresent(Blocks.AIR, origin);
		helper.assertBlockPresent(Blocks.AIR, farDirt);
		helper.assertBlockPresent(Blocks.AIR, glass);
		helper.assertBlockPresent(Blocks.OAK_LOG, wood);
		helper.assertBlockPresent(Blocks.BEDROCK, bedrock);
		helper.assertTrue(shovel.getDamageValue() == 0, "无尽铲范围挖掘后不应产生耐久损耗");
		List<ItemEntity> clusters = helper.getLevel().getEntitiesOfClass(
				ItemEntity.class,
				new AABB(absoluteOrigin).inflate(2.0),
				entity -> entity.getItem().is(ModItems.MATTER_CLUSTER)
		);
		helper.assertTrue(
				clusters.size() == 1 && MatterClusterItem.getSize(clusters.getFirst().getItem()) == 2,
				"无尽铲没有把范围掉落压入物质团"
		);
		helper.succeed();
	}

	@GameTest
	public void infinityAxeCraftsChainsTreesAndClearsClassicArea(GameTestHelper helper) {
		ItemStack axe = new ItemStack(ModItems.INFINITY_AXE);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_AXE_KEY) == ModItems.INFINITY_AXE,
				"自然荒芜之斧没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(axe.getMaxStackSize() == 1 && !axe.isDamageableItem(), "自然荒芜之斧应当不可堆叠且永不损耗");
		helper.assertTrue(axe.getRarity() == Rarity.EPIC && axe.is(ItemTags.AXES), "自然荒芜之斧稀有度或工具标签错误");
		helper.assertTrue(ModItems.INFINITY_AXE.getDestroySpeed(axe, Blocks.OAK_LOG.defaultBlockState()) == 9999.0F, "自然荒芜之斧木材速度错误");
		helper.assertTrue(ModItems.INFINITY_AXE.getDestroySpeed(axe, Blocks.STONE.defaultBlockState()) == 6.0F, "自然荒芜之斧非木材保底速度错误");
		var modifiers = axe.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(
				modifiers != null && modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 40.0,
				"自然荒芜之斧主手攻击伤害应当是 40"
		);
		helper.assertTrue(
				modifiers != null && Math.abs(modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) - 1.0) < 0.0001,
				"自然荒芜之斧主手攻击速度应当是 1.0"
		);

		CraftingInput input = infinityAxeInput();
		assertExtremeRecipe(helper, "infinity_axe", input, ModItems.INFINITY_AXE);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(1, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_axe"));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(input.width(), input.height(), wrongStacks), helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"自然荒芜之斧刃部材料错误时不应匹配配方"
		);

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, axe);
		BlockPos treeOrigin = new BlockPos(10, 10, 10);
		for (int distance = 0; distance <= 33; distance++) {
			helper.setBlock(treeOrigin.above(distance), Blocks.OAK_LOG);
		}
		for (int distance = 1; distance <= 5; distance++) {
			helper.setBlock(treeOrigin.east(distance), Blocks.OAK_LEAVES);
		}
		BlockPos absoluteTreeOrigin = helper.absolutePos(treeOrigin);
		player.setPos(absoluteTreeOrigin.getX() + 0.5, absoluteTreeOrigin.getY(), absoluteTreeOrigin.getZ() + 0.5);
		var chainResult = AttackBlockCallback.EVENT.invoker().interact(
				player,
				helper.getLevel(),
				InteractionHand.MAIN_HAND,
				absoluteTreeOrigin,
				Direction.UP
		);
		helper.assertTrue(chainResult.consumesAction(), "自然荒芜之斧没有接管连锁砍树");
		helper.assertBlockPresent(Blocks.AIR, treeOrigin.above(32));
		helper.assertBlockPresent(Blocks.OAK_LOG, treeOrigin.above(33));
		helper.assertBlockPresent(Blocks.AIR, treeOrigin.east(4));
		helper.assertBlockPresent(Blocks.OAK_LEAVES, treeOrigin.east(5));

		BlockPos areaOrigin = new BlockPos(50, 10, 50);
		BlockPos minCorner = areaOrigin.offset(-13, -3, -13);
		BlockPos maxXBoundary = areaOrigin.east(13);
		BlockPos maxYBoundary = areaOrigin.above(23);
		BlockPos leaves = areaOrigin.east();
		BlockPos vine = areaOrigin.west();
		BlockPos coral = areaOrigin.north();
		BlockPos flower = areaOrigin.south();
		BlockPos stone = areaOrigin.south(2);
		helper.setBlock(minCorner, Blocks.OAK_PLANKS);
		helper.setBlock(maxXBoundary, Blocks.OAK_PLANKS);
		helper.setBlock(maxYBoundary, Blocks.OAK_PLANKS);
		helper.setBlock(leaves, Blocks.OAK_LEAVES);
		helper.setBlock(vine, Blocks.VINE);
		helper.setBlock(coral, Blocks.TUBE_CORAL_BLOCK);
		helper.setBlock(flower, Blocks.DANDELION);
		helper.setBlock(stone, Blocks.STONE);
		BlockPos absoluteAreaOrigin = helper.absolutePos(areaOrigin);
		player.setPos(absoluteAreaOrigin.getX() + 0.5, absoluteAreaOrigin.getY(), absoluteAreaOrigin.getZ() + 0.5);
		player.setShiftKeyDown(true);
		List<ItemStack> clusters = ModToolEvents.destroyClassicAxeArea(helper.getLevel(), player, axe);
		var areaResult = ModItems.INFINITY_AXE.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(areaResult.consumesAction(), "自然荒芜之斧没有接管潜行范围清理");
		helper.assertBlockPresent(Blocks.AIR, minCorner);
		helper.assertBlockPresent(Blocks.OAK_PLANKS, maxXBoundary);
		helper.assertBlockPresent(Blocks.OAK_PLANKS, maxYBoundary);
		helper.assertBlockPresent(Blocks.AIR, leaves);
		helper.assertBlockPresent(Blocks.AIR, vine);
		helper.assertBlockPresent(Blocks.AIR, coral);
		helper.assertBlockPresent(Blocks.AIR, flower);
		helper.assertBlockPresent(Blocks.STONE, stone);
		helper.assertTrue(axe.getDamageValue() == 0, "自然荒芜之斧使用后不应产生耐久损耗");
		helper.assertTrue(
				clusters.size() == 1 && MatterClusterItem.getSize(clusters.getFirst()) >= 2,
				"自然荒芜之斧没有把范围掉落压入物质团"
		);
		helper.succeed();
	}

	@GameTest
	public void infinityBowCraftsAutoFiresAndCreatesHeavenBarrage(GameTestHelper helper) {
		ItemStack bow = new ItemStack(ModItems.INFINITY_BOW);
		helper.assertTrue(
				BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_BOW_KEY) == ModItems.INFINITY_BOW,
				"天堂陨落长弓没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(
				BuiltInRegistries.ENTITY_TYPE.getValue(ModEntityTypes.HEAVEN_ARROW_KEY) == ModEntityTypes.HEAVEN_ARROW
						&& BuiltInRegistries.ENTITY_TYPE.getValue(ModEntityTypes.HEAVEN_SUB_ARROW_KEY) == ModEntityTypes.HEAVEN_SUB_ARROW,
				"天堂箭实体没有注册到预期的 ResourceKey"
		);
		helper.assertTrue(bow.getMaxStackSize() == 1 && !bow.isDamageableItem(), "天堂陨落长弓应当不可堆叠且永不损耗");
		helper.assertTrue(bow.getRarity() == Rarity.EPIC && bow.is(ItemTags.BOW_ENCHANTABLE), "天堂陨落长弓稀有度或弓附魔标签错误");
		helper.assertTrue(ModItems.INFINITY_BOW.getUseDuration(bow, null) == 13, "天堂陨落长弓蓄力时长应当是 13 tick");
		helper.assertTrue(ModItems.INFINITY_BOW.getUseAnimation(bow) == net.minecraft.world.item.ItemUseAnimation.BOW, "天堂陨落长弓缺少拉弓动作");

		CraftingInput input = infinityBowInput();
		assertExtremeRecipe(helper, "infinity_bow", input, ModItems.INFINITY_BOW);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(20, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_bow"));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(input.width(), input.height(), wrongStacks), helper.getLevel())
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				"天堂陨落长弓弓身材料错误时不应匹配配方"
		);

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		BlockPos bowOrigin = helper.absolutePos(new BlockPos(5, 10, 5));
		player.setPos(bowOrigin.getX() + 0.5, bowOrigin.getY(), bowOrigin.getZ() + 0.5);
		player.setXRot(0.0F);
		player.setYRot(0.0F);
		player.setItemInHand(InteractionHand.MAIN_HAND, bow);
		List<UUID> existingPrimaryArrowIds = helper.getLevel().getEntitiesOfClass(
				HeavenArrowEntity.class,
				player.getBoundingBox().inflate(8.0)
		).stream().map(HeavenArrowEntity::getUUID).toList();
		helper.assertTrue(player.getInventory().countItem(Items.ARROW) == 0, "天堂陨落长弓测试不应预置弹药");
		var useResult = ModItems.INFINITY_BOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(useResult.consumesAction() && player.isUsingItem(), "天堂陨落长弓没有在无弹药时开始蓄力");
		helper.assertFalse(ModItems.INFINITY_BOW.releaseUsing(bow, helper.getLevel(), player, 7), "提前松开天堂陨落长弓不应发射");
		helper.assertTrue(
				helper.getLevel().getEntitiesOfClass(HeavenArrowEntity.class, player.getBoundingBox().inflate(8.0)).stream()
						.noneMatch(arrow -> !existingPrimaryArrowIds.contains(arrow.getUUID())),
				"提前松弦不应生成天堂箭"
		);

		ModItems.INFINITY_BOW.onUseTick(helper.getLevel(), player, bow, 1);
		helper.runAfterDelay(1, () -> {
			List<HeavenArrowEntity> primaryArrows = helper.getLevel().getEntitiesOfClass(
					HeavenArrowEntity.class,
					player.getBoundingBox().inflate(8.0)
			).stream().filter(arrow -> !existingPrimaryArrowIds.contains(arrow.getUUID())).toList();
			helper.assertTrue(primaryArrows.size() == 1, "满蓄力没有生成且仅生成一支天堂箭");
			HeavenArrowEntity primaryArrow = primaryArrows.getFirst();
			helper.assertTrue(primaryArrow.getOwner() == player, "天堂箭没有保留射手");
			helper.assertTrue(primaryArrow.pickup == AbstractArrow.Pickup.CREATIVE_ONLY && primaryArrow.isCritArrow(), "天堂箭回收或暴击状态错误");
			helper.assertTrue(primaryArrow.getDeltaMovement().length() > 2.9, "天堂箭初速度不足");
			helper.assertTrue(player.getInventory().countItem(Items.ARROW) == 0 && bow.getDamageValue() == 0, "天堂陨落长弓不应消耗箭或耐久");

			BlockPos impactPos = helper.absolutePos(new BlockPos(12, 5, 12));
			List<UUID> existingSubArrowIds = helper.getLevel().getEntitiesOfClass(
					HeavenSubArrowEntity.class,
					new AABB(impactPos).inflate(8.0, 30.0, 8.0)
			).stream().map(HeavenSubArrowEntity::getUUID).toList();
			primaryArrow.createBarrage(helper.getLevel(), impactPos);
			helper.runAfterDelay(1, () -> {
				List<HeavenSubArrowEntity> subArrows = helper.getLevel().getEntitiesOfClass(
						HeavenSubArrowEntity.class,
						new AABB(impactPos).inflate(8.0, 30.0, 8.0)
				).stream().filter(arrow -> !existingSubArrowIds.contains(arrow.getUUID())).toList();
				helper.assertTrue(subArrows.size() == 10, "天堂箭落地后应当生成 10 支天降子箭");
				helper.assertTrue(
						subArrows.stream().allMatch(arrow -> arrow.getOwner() == player
								&& arrow.pickup == AbstractArrow.Pickup.CREATIVE_ONLY
								&& arrow.isCritArrow()
								&& arrow.getDeltaMovement().y < -0.15),
						"天降子箭的射手、回收、暴击或下落速度错误"
				);

				var target = helper.spawnWithNoFreeWill(EntityTypes.PIG, new BlockPos(30, 5, 30));
				primaryArrow.setCritArrow(false);
				primaryArrow.setPos(target.getX() - 1.0, target.getY() + target.getBbHeight() * 0.5, target.getZ());
				primaryArrow.setDeltaMovement(3.0, 0.0, 0.0);
				primaryArrow.tick();
				helper.assertTrue(target.isDeadOrDying(), "天堂箭 20 点基础伤害没有击杀普通目标");
				helper.succeed();
			});
		});
	}

	@GameTest
	public void infinityArmorPiecesCraftEquipAndProvideClassicAbilities(GameTestHelper helper) {
		assertInfinityArmorPiece(
				helper,
				ModItems.INFINITY_HELMET_KEY,
				ModItems.INFINITY_HELMET,
				ArmorType.HELMET,
				ItemTags.HEAD_ARMOR,
				ItemTags.HEAD_ARMOR_ENCHANTABLE,
				6
		);
		assertInfinityArmorPiece(
				helper,
				ModItems.INFINITY_CHESTPLATE_KEY,
				ModItems.INFINITY_CHESTPLATE,
				ArmorType.CHESTPLATE,
				ItemTags.CHEST_ARMOR,
				ItemTags.CHEST_ARMOR_ENCHANTABLE,
				16
		);
		assertInfinityArmorPiece(
				helper,
				ModItems.INFINITY_PANTS_KEY,
				ModItems.INFINITY_PANTS,
				ArmorType.LEGGINGS,
				ItemTags.LEG_ARMOR,
				ItemTags.LEG_ARMOR_ENCHANTABLE,
				12
		);
		assertInfinityArmorPiece(
				helper,
				ModItems.INFINITY_BOOTS_KEY,
				ModItems.INFINITY_BOOTS,
				ArmorType.BOOTS,
				ItemTags.FOOT_ARMOR,
				ItemTags.FOOT_ARMOR_ENCHANTABLE,
				6
		);

		CraftingInput helmetInput = infinityHelmetInput();
		assertExtremeRecipe(helper, "infinity_helmet", helmetInput, ModItems.INFINITY_HELMET);
		assertWrongExtremeRecipe(helper, "infinity_helmet", helmetInput, "无尽头盔材料错误时不应匹配配方");
		CraftingInput chestplateInput = infinityChestplateInput();
		assertExtremeRecipe(helper, "infinity_chestplate", chestplateInput, ModItems.INFINITY_CHESTPLATE);
		assertWrongExtremeRecipe(helper, "infinity_chestplate", chestplateInput, "无尽胸甲材料错误时不应匹配配方");
		CraftingInput pantsInput = infinityPantsInput();
		assertExtremeRecipe(helper, "infinity_pants", pantsInput, ModItems.INFINITY_PANTS);
		assertWrongExtremeRecipe(helper, "infinity_pants", pantsInput, "无尽护腿材料错误时不应匹配配方");
		CraftingInput bootsInput = infinityBootsInput();
		assertExtremeRecipe(helper, "infinity_boots", bootsInput, ModItems.INFINITY_BOOTS);
		assertWrongExtremeRecipe(helper, "infinity_boots", bootsInput, "无尽之靴材料错误时不应匹配配方");

		ItemAttributeModifiers bootsAttributes = new ItemStack(ModItems.INFINITY_BOOTS).get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(bootsAttributes != null, "无尽之靴缺少原生属性组件");
		assertClose(helper, bootsAttributes.compute(Attributes.MOVEMENT_SPEED, 0.1, EquipmentSlot.FEET), 0.25, "无尽之靴移动速度错误");
		assertClose(helper, bootsAttributes.compute(Attributes.JUMP_STRENGTH, 0.42, EquipmentSlot.FEET), 0.82, "无尽之靴跳跃能力错误");
		assertClose(helper, bootsAttributes.compute(Attributes.STEP_HEIGHT, 0.6, EquipmentSlot.FEET), 1.0625, "无尽之靴跨步高度错误");

		ServerPlayer player = (ServerPlayer) helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.INFINITY_HELMET));
		player.setAirSupply(1);
		player.getFoodData().setFoodLevel(1);
		player.getFoodData().setSaturation(0.0F);
		ModArmorEvents.tickPlayer(player);
		helper.assertTrue(
				player.getAirSupply() == 300
						&& player.getFoodData().getFoodLevel() == 20
						&& player.getFoodData().getSaturationLevel() == 20.0F
						&& player.hasEffect(MobEffects.NIGHT_VISION),
				"无尽头盔没有补满呼吸、饥饿、饱和度或夜视"
		);

		player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.INFINITY_CHESTPLATE));
		player.getActiveEffectsMap().put(MobEffects.POISON, new MobEffectInstance(MobEffects.POISON, 200));
		ModArmorEvents.tickPlayer(player);
		helper.assertTrue(player.getAbilities().mayfly, "无尽胸甲没有授予飞行权限");
		helper.assertFalse(player.hasEffect(MobEffects.POISON), "无尽胸甲没有清除负面效果");
		player.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);
		ModArmorEvents.tickPlayer(player);
		helper.assertFalse(player.getAbilities().mayfly || player.getAbilities().flying, "脱下无尽胸甲后仍保留其飞行权限");

		player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(ModItems.INFINITY_PANTS));
		player.setRemainingFireTicks(100);
		ModArmorEvents.tickPlayer(player);
		helper.assertTrue(player.getRemainingFireTicks() == 0, "无尽护腿没有熄灭穿戴者身上的火焰");

		player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.INFINITY_CHESTPLATE));
		player.setItemSlot(EquipmentSlot.FEET, new ItemStack(ModItems.INFINITY_BOOTS));
		helper.assertTrue(ModArmorEvents.hasFullSet(player), "四件无尽护甲没有识别为完整套装");
		var damageSource = helper.getLevel().damageSources().generic();
		helper.assertFalse(
				ServerLivingEntityEvents.ALLOW_DAMAGE.invoker().allowDamage(player, damageSource, 20.0F),
				"完整无尽套装没有阻止普通伤害"
		);
		player.setHealth(1.0F);
		helper.assertFalse(
				ServerLivingEntityEvents.ALLOW_DEATH.invoker().allowDeath(player, damageSource, 20.0F),
				"完整无尽套装没有阻止死亡"
		);
		helper.assertTrue(player.getHealth() == player.getMaxHealth(), "无尽套装阻止死亡后没有恢复生命");
		player.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
		helper.assertTrue(
				ServerLivingEntityEvents.ALLOW_DAMAGE.invoker().allowDamage(player, damageSource, 20.0F),
				"不完整的无尽套装不应阻止伤害"
		);
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
		helper.assertTrue(recipe.value() instanceof EternalSingularityRecipe, "永恒奇点没有使用动态配方类型");
		RegistryFriendlyByteBuf recipeBuffer = new RegistryFriendlyByteBuf(
				Unpooled.buffer(),
				helper.getLevel().registryAccess()
		);
		try {
			EternalSingularityRecipe.STREAM_CODEC.encode(recipeBuffer, (EternalSingularityRecipe) recipe.value());
			EternalSingularityRecipe decoded = EternalSingularityRecipe.STREAM_CODEC.decode(recipeBuffer);
			helper.assertTrue(decoded.matches(input, helper.getLevel()), "永恒奇点配方网络同步往返后不再匹配");
			helper.assertTrue(
					decoded.assemble(input).is(ModItems.ETERNAL_SINGULARITY),
					"永恒奇点配方网络同步丢失输出"
			);
		} finally {
			recipeBuffer.release();
		}

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
	public void tieredNeutronCollectorsCraftAndProduceTheirExactMaterials(GameTestHelper helper) {
		SingularityDefinition gold = SingularityManager.get(Avaritia26.id("gold"));
		SingularityDefinition redstone = SingularityManager.get(Avaritia26.id("redstone"));
		SingularityDefinition copper = SingularityManager.get(Avaritia26.id("copper"));
		helper.assertTrue(gold != null && redstone != null && copper != null, "高阶收集器配方所需奇点未加载");

		CraftingInput denseInput = extremeInput(List.of(
				"AAC   CAA", "AB     BA", "C DEEED C", "  EGGGE  ", "  EGFGE  ",
				"  EGGGE  ", "C DEEED C", "AB     BA", "AAC   CAA"
		), Map.of(
				'A', new ItemStack(Items.ENDER_PEARL),
				'B', new ItemStack(Items.NETHER_STAR),
				'C', new ItemStack(ModItems.DIAMOND_LATTICE),
				'D', new ItemStack(ModItems.NEUTRON_INGOT),
				'E', new ItemStack(Items.EMERALD_BLOCK),
				'F', new ItemStack(ModItems.ENDEST_PEARL),
				'G', new ItemStack(ModBlocks.NEUTRON_COLLECTOR_ITEM)
		));
		assertExtremeRecipe(helper, "dense_neutron_collector", denseInput, ModBlocks.DENSE_NEUTRON_COLLECTOR_ITEM);

		CraftingInput denserInput = extremeInput(List.of(
				"ABB F BBA", "BCC   CCB", "BCDEEEDCB", "  EGGGE  ", "F EGEGE F",
				"  EGGGE  ", "BCDEEEDCB", "BCC   CCB", "ABB F BBA"
		), Map.of(
				'A', new ItemStack(ModItems.NEUTRON_GEAR),
				'B', new ItemStack(ModItems.NEUTRON_PILE),
				'C', new ItemStack(ModItems.BLAZE_CUBE),
				'D', SingularityItem.createStack(gold),
				'E', new ItemStack(ModBlocks.BLAZE_CUBE_BLOCK_ITEM),
				'F', new ItemStack(Items.GOLD_BLOCK),
				'G', new ItemStack(ModBlocks.DENSE_NEUTRON_COLLECTOR_ITEM)
		));
		assertExtremeRecipe(helper, "denser_neutron_collector", denserInput, ModBlocks.DENSER_NEUTRON_COLLECTOR_ITEM);
		List<ItemStack> wrongGold = copyStacks(denserInput.items());
		wrongGold.set(20, SingularityItem.createStack(copper));
		assertExtremeRecipeDoesNotMatch(helper, "denser_neutron_collector", CraftingInput.of(9, 9, wrongGold));

		CraftingInput densestInput = extremeInput(List.of(
				"CC     CC", "C  BBB  C", "  AAAAA  ", " BAXXXAB ", " BAXYXAB ",
				" BAXXXAB ", "  AAAAA  ", "C  BBB  C", "CC     CC"
		), Map.of(
				'A', new ItemStack(Items.REDSTONE_BLOCK),
				'B', new ItemStack(ModItems.NEUTRON_INGOT),
				'C', new ItemStack(ModItems.NEUTRON_GEAR),
				'X', new ItemStack(ModBlocks.DENSER_NEUTRON_COLLECTOR_ITEM),
				'Y', SingularityItem.createStack(redstone)
		));
		assertExtremeRecipe(helper, "densest_neutron_collector", densestInput, ModBlocks.DENSEST_NEUTRON_COLLECTOR_ITEM);
		List<ItemStack> wrongRedstone = copyStacks(densestInput.items());
		wrongRedstone.set(40, SingularityItem.createStack(gold));
		assertExtremeRecipeDoesNotMatch(helper, "densest_neutron_collector", CraftingInput.of(9, 9, wrongRedstone));

		assertCollectorTier(helper, new BlockPos(2, 0, 0), ModBlocks.DENSE_NEUTRON_COLLECTOR,
				ModBlocks.DENSE_NEUTRON_COLLECTOR_ITEM, NeutronCollectorTier.DENSE,
				ModItems.NEUTRON_NUGGET, 3600, "致密中子收集器");
		assertCollectorTier(helper, new BlockPos(4, 0, 0), ModBlocks.DENSER_NEUTRON_COLLECTOR,
				ModBlocks.DENSER_NEUTRON_COLLECTOR_ITEM, NeutronCollectorTier.DENSER,
				ModItems.NEUTRON_INGOT, 3600, "更致密中子收集器");
		assertCollectorTier(helper, new BlockPos(6, 0, 0), ModBlocks.DENSEST_NEUTRON_COLLECTOR,
				ModBlocks.DENSEST_NEUTRON_COLLECTOR_ITEM, NeutronCollectorTier.DENSEST,
				ModBlocks.NEUTRON_ITEM, 200, "最致密中子收集器");
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
	public void tieredNeutronCompressorsCraftAndApplyEveryMultiplier(GameTestHelper helper) {
		SingularityDefinition gold = SingularityManager.get(Avaritia26.id("gold"));
		SingularityDefinition redstone = SingularityManager.get(Avaritia26.id("redstone"));
		SingularityDefinition copper = SingularityManager.get(Avaritia26.id("copper"));
		helper.assertTrue(gold != null && redstone != null && copper != null, "高阶压缩机配方所需奇点未加载");
		helper.assertTrue(NeutronCompressorTier.DENSER.scaleInput(1001) == 751, "更致密压缩机输入倍率没有向上取整");
		helper.assertTrue(NeutronCompressorTier.DENSER.scaleTime(241) == 61, "更致密压缩机时间倍率没有向上取整");
		helper.assertTrue(NeutronCompressorTier.DENSEST.scaleInput(1001) == 501, "最致密压缩机输入倍率没有向上取整");
		helper.assertTrue(NeutronCompressorTier.DENSEST.scaleTime(241) == 31, "最致密压缩机时间倍率没有向上取整");

		CraftingInput denseInput = extremeInput(List.of(
				"AAC   CAA", "AB     BA", "C DEEED C", "  EGGGE  ", "  EGFGE  ",
				"  EGGGE  ", "C DEEED C", "AB     BA", "AAC   CAA"
		), Map.of(
				'A', new ItemStack(Items.ENDER_PEARL), 'B', new ItemStack(Items.NETHER_STAR),
				'C', new ItemStack(ModItems.DIAMOND_LATTICE), 'D', new ItemStack(ModItems.NEUTRON_INGOT),
				'E', new ItemStack(Items.EMERALD_BLOCK), 'F', new ItemStack(ModItems.ENDEST_PEARL),
				'G', new ItemStack(ModBlocks.NEUTRON_COMPRESSOR_ITEM)
		));
		assertExtremeRecipe(helper, "dense_neutron_compressor", denseInput, ModBlocks.DENSE_NEUTRON_COMPRESSOR_ITEM);

		CraftingInput denserInput = extremeInput(List.of(
				"ABB F BBA", "BCC   CCB", "BCDEEEDCB", "  EGGGE  ", "F EGEGE F",
				"  EGGGE  ", "BCDEEEDCB", "BCC   CCB", "ABB F BBA"
		), Map.of(
				'A', new ItemStack(ModItems.NEUTRON_GEAR), 'B', new ItemStack(ModItems.NEUTRON_PILE),
				'C', new ItemStack(ModItems.BLAZE_CUBE), 'D', SingularityItem.createStack(gold),
				'E', new ItemStack(ModBlocks.BLAZE_CUBE_BLOCK_ITEM), 'F', new ItemStack(Items.GOLD_BLOCK),
				'G', new ItemStack(ModBlocks.DENSE_NEUTRON_COMPRESSOR_ITEM)
		));
		assertExtremeRecipe(helper, "denser_neutron_compressor", denserInput, ModBlocks.DENSER_NEUTRON_COMPRESSOR_ITEM);
		List<ItemStack> wrongGold = copyStacks(denserInput.items());
		wrongGold.set(20, SingularityItem.createStack(copper));
		assertExtremeRecipeDoesNotMatch(helper, "denser_neutron_compressor", CraftingInput.of(9, 9, wrongGold));

		CraftingInput densestInput = extremeInput(List.of(
				"CC     CC", "C  BBB  C", "  AAAAA  ", " BAXXXAB ", " BAXYXAB ",
				" BAXXXAB ", "  AAAAA  ", "C  BBB  C", "CC     CC"
		), Map.of(
				'A', new ItemStack(Items.REDSTONE_BLOCK), 'B', new ItemStack(ModItems.NEUTRON_INGOT),
				'C', new ItemStack(ModItems.NEUTRON_GEAR), 'X', new ItemStack(ModBlocks.DENSER_NEUTRON_COMPRESSOR_ITEM),
				'Y', SingularityItem.createStack(redstone)
		));
		assertExtremeRecipe(helper, "densest_neutron_compressor", densestInput, ModBlocks.DENSEST_NEUTRON_COMPRESSOR_ITEM);
		List<ItemStack> wrongRedstone = copyStacks(densestInput.items());
		wrongRedstone.set(40, SingularityItem.createStack(gold));
		assertExtremeRecipeDoesNotMatch(helper, "densest_neutron_compressor", CraftingInput.of(9, 9, wrongRedstone));

		assertCompressorTier(helper, new BlockPos(2, 0, 0), ModBlocks.DENSE_NEUTRON_COMPRESSOR,
				ModBlocks.DENSE_NEUTRON_COMPRESSOR_ITEM, NeutronCompressorTier.DENSE,
				1000, 120, 1, "致密中子压缩机");
		assertCompressorTier(helper, new BlockPos(4, 0, 0), ModBlocks.DENSER_NEUTRON_COMPRESSOR,
				ModBlocks.DENSER_NEUTRON_COMPRESSOR_ITEM, NeutronCompressorTier.DENSER,
				750, 60, 1, "更致密中子压缩机");
		assertCompressorTier(helper, new BlockPos(6, 0, 0), ModBlocks.DENSEST_NEUTRON_COMPRESSOR,
				ModBlocks.DENSEST_NEUTRON_COMPRESSOR_ITEM, NeutronCompressorTier.DENSEST,
				500, 30, 2, "最致密中子压缩机");
		helper.succeed();
	}

	@GameTest
	public void infinityUpgradeCraftsRepairsAndPreservesMachines(GameTestHelper helper) {
		ItemStack upgrade = new ItemStack(ModItems.INFINITY_UPGRADE);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_UPGRADE_KEY) == ModItems.INFINITY_UPGRADE,
				"无尽升级组件未注册");
		helper.assertTrue(upgrade.getRarity() == Rarity.EPIC, "无尽升级组件稀有度错误");
		helper.assertTrue(upgrade.getMaxStackSize() == 1 && upgrade.getMaxDamage() == 16, "无尽升级组件耐久错误");
		helper.assertTrue(upgrade.has(DataComponents.DAMAGE_RESISTANT), "无尽升级组件应当防火");
		helper.assertFalse(upgrade.hasFoil(), "无尽升级组件不应自带附魔光效");
		Repairable repairable = upgrade.get(DataComponents.REPAIRABLE);
		helper.assertTrue(repairable != null && repairable.isValidRepairItem(new ItemStack(ModItems.STAR_FUEL)),
				"无尽升级组件不能用星辰燃料修复");
		helper.assertFalse(repairable != null && repairable.isValidRepairItem(new ItemStack(ModItems.REFINED_COAL)),
				"无尽升级组件错误接受精炼煤炭修复");
		List<Component> tooltip = new java.util.ArrayList<>();
		ModItems.INFINITY_UPGRADE.appendHoverText(
				upgrade, Item.TooltipContext.of(helper.getLevel()), TooltipDisplay.DEFAULT, tooltip::add, TooltipFlag.NORMAL
		);
		helper.assertTrue(tooltip.size() == 1 && tooltip.getFirst().getStyle().isItalic(), "无尽升级组件缺少斜体说明");

		CraftingInput recipeInput = infinityUpgradeInput();
		assertExtremeRecipe(helper, "infinity_upgrade", recipeInput, ModItems.INFINITY_UPGRADE);
		ItemStack recipeResult = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, recipeInput, helper.getLevel())
				.orElseThrow(() -> helper.assertionException("无尽升级组件配方未加载"))
				.value().assemble(recipeInput);
		helper.assertTrue(recipeResult.getCount() == 1, "无尽升级组件配方应当产出一个耐久物品");
		List<ItemStack> wrongRecipe = copyStacks(recipeInput.items());
		wrongRecipe.set(40, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "infinity_upgrade", CraftingInput.of(9, 9, wrongRecipe));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, upgrade);
		BlockPos collectorPos = new BlockPos(2, 0, 0);
		helper.setBlock(
				collectorPos,
				ModBlocks.NEUTRON_COLLECTOR.defaultBlockState().setValue(NeutronCollectorBlock.FACING, Direction.EAST)
		);
		NeutronCollectorBlockEntity collector = helper.getBlockEntity(collectorPos, NeutronCollectorBlockEntity.class);
		for (int tick = 0; tick < 100; tick++) {
			NeutronCollectorBlockEntity.serverTick(
					helper.getLevel(), helper.absolutePos(collectorPos), helper.getBlockState(collectorPos), collector
			);
		}
		collector.setItem(0, new ItemStack(ModItems.NEUTRON_PILE, 3));
		player.setShiftKeyDown(false);
		helper.assertFalse(useUpgrade(helper, player, collectorPos).consumesAction(), "未潜行时无尽升级组件不应生效");
		helper.assertBlockPresent(ModBlocks.NEUTRON_COLLECTOR, collectorPos);
		helper.assertTrue(upgrade.getDamageValue() == 0, "未潜行使用错误消耗耐久");

		player.setShiftKeyDown(true);
		helper.assertTrue(useUpgrade(helper, player, collectorPos).consumesAction(), "无尽升级组件没有升级中子收集器");
		helper.assertBlockPresent(ModBlocks.DENSE_NEUTRON_COLLECTOR, collectorPos);
		NeutronCollectorBlockEntity upgradedCollector = helper.getBlockEntity(collectorPos, NeutronCollectorBlockEntity.class);
		helper.assertTrue(upgradedCollector == collector, "升级中子收集器时错误替换了方块实体");
		helper.assertTrue(upgradedCollector.getProgress() == 100, "升级中子收集器丢失进度");
		helper.assertTrue(upgradedCollector.getItem(0).is(ModItems.NEUTRON_PILE)
				&& upgradedCollector.getItem(0).getCount() == 3, "升级中子收集器丢失产物");
		helper.assertTrue(helper.getBlockState(collectorPos).getValue(NeutronCollectorBlock.FACING) == Direction.EAST,
				"升级中子收集器丢失朝向");
		upgradedCollector.setItem(0, ItemStack.EMPTY);
		useUpgrade(helper, player, collectorPos);
		useUpgrade(helper, player, collectorPos);
		helper.assertBlockPresent(ModBlocks.DENSEST_NEUTRON_COLLECTOR, collectorPos);
		helper.assertTrue(helper.getBlockEntity(collectorPos, NeutronCollectorBlockEntity.class) == collector,
				"连续升级中子收集器没有保留方块实体");
		helper.assertTrue(upgrade.getDamageValue() == 3, "收集器三级升级耐久消耗错误");
		for (int tick = 100; tick < 200; tick++) {
			NeutronCollectorBlockEntity.serverTick(
					helper.getLevel(), helper.absolutePos(collectorPos), helper.getBlockState(collectorPos), collector
			);
		}
		helper.assertTrue(collector.getItem(0).is(ModBlocks.NEUTRON_ITEM), "最致密收集器没有继承进度并产出中子块");
		helper.assertFalse(useUpgrade(helper, player, collectorPos).consumesAction(), "最致密收集器不应继续升级");
		helper.assertTrue(upgrade.getDamageValue() == 3, "对最高级收集器使用错误消耗耐久");

		BlockPos compressorPos = new BlockPos(5, 0, 0);
		helper.setBlock(
				compressorPos,
				ModBlocks.NEUTRON_COMPRESSOR.defaultBlockState().setValue(NeutronCompressorBlock.FACING, Direction.WEST)
		);
		NeutronCompressorBlockEntity compressor = helper.getBlockEntity(compressorPos, NeutronCompressorBlockEntity.class);
		feedCompressor(helper, compressorPos, compressor, Items.OBSIDIAN, 128);
		compressor.setItem(NeutronCompressorBlockEntity.OUTPUT_SLOT, new ItemStack(Items.DIRT, 7));
		useUpgrade(helper, player, compressorPos);
		useUpgrade(helper, player, compressorPos);
		useUpgrade(helper, player, compressorPos);
		helper.assertBlockPresent(ModBlocks.DENSEST_NEUTRON_COMPRESSOR, compressorPos);
		NeutronCompressorBlockEntity upgradedCompressor = helper.getBlockEntity(
				compressorPos, NeutronCompressorBlockEntity.class
		);
		helper.assertTrue(upgradedCompressor == compressor, "升级中子压缩机时错误替换了方块实体");
		helper.assertTrue(compressor.getMaterialCount() == 128, "升级中子压缩机丢失缓存材料");
		helper.assertTrue(compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT).is(Items.DIRT)
				&& compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT).getCount() == 7,
				"升级中子压缩机丢失输出槽物品");
		helper.assertTrue(helper.getBlockState(compressorPos).getValue(NeutronCompressorBlock.FACING) == Direction.WEST,
				"升级中子压缩机丢失朝向");
		helper.assertTrue(upgrade.getDamageValue() == 6, "两类机器共六次升级耐久消耗错误");
		compressor.setItem(NeutronCompressorBlockEntity.OUTPUT_SLOT, ItemStack.EMPTY);
		feedCompressor(helper, compressorPos, compressor, Items.OBSIDIAN, 372);
		tickUntilOutput(helper, compressorPos, compressor, 29);
		helper.assertTrue(compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT).getCount() == 2,
				"最致密压缩机没有继承缓存并双倍产出");
		List<ItemEntity> upgradeDrops = helper.getLevel().getEntitiesOfClass(
				ItemEntity.class,
				new AABB(helper.absolutePos(compressorPos)).inflate(2.0),
				entity -> entity.getItem().is(Items.OBSIDIAN) || entity.getItem().is(Items.DIRT)
		);
		helper.assertTrue(upgradeDrops.isEmpty(), "升级机器时错误掉落了方块实体内容");

		BlockPos breakingPos = new BlockPos(8, 0, 0);
		helper.setBlock(breakingPos, ModBlocks.NEUTRON_COLLECTOR);
		upgrade.setDamageValue(15);
		useUpgrade(helper, player, breakingPos);
		helper.assertTrue(upgrade.isEmpty(), "无尽升级组件第十六次使用后没有损坏");
		helper.assertBlockPresent(ModBlocks.DENSE_NEUTRON_COLLECTOR, breakingPos);
		helper.succeed();
	}

	@GameTest
	public void forgeEnergyRemainsInternalPlaceholder(GameTestHelper helper) {
		ItemStack energy = new ItemStack(ModItems.FORGE_ENERGY, 64);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.FORGE_ENERGY_KEY) == ModItems.FORGE_ENERGY,
				"锻造能量占位物品未注册");
		helper.assertTrue(ModItems.FORGE_ENERGY.getClass() == Item.class, "锻造能量不应附带自定义行为");
		helper.assertTrue(energy.getMaxStackSize() == 64 && energy.getRarity() == Rarity.COMMON,
				"锻造能量默认物品属性错误");
		helper.assertFalse(energy.has(DataComponents.DAMAGE_RESISTANT), "锻造能量不应额外防火");
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("forge_energy"));
		helper.assertTrue(helper.getLevel().getServer().getRecipeManager().byKey(recipeKey).isEmpty(),
				"锻造能量不应拥有玩家配方");

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, energy);
		InteractionResult result = ModItems.FORGE_ENERGY.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertFalse(result.consumesAction(), "锻造能量占位物品不应响应使用");
		helper.assertTrue(player.getMainHandItem().is(ModItems.FORGE_ENERGY)
				&& player.getMainHandItem().getCount() == 64, "使用锻造能量错误消耗了物品");
		helper.succeed();
	}

	@GameTest
	public void soulFarmlandSupportsAndAcceleratesPlants(GameTestHelper helper) {
		helper.assertTrue(BuiltInRegistries.BLOCK.getValue(ModBlocks.SOUL_FARMLAND_KEY) == ModBlocks.SOUL_FARMLAND,
				"灵魂耕地方块未注册");
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModBlocks.SOUL_FARMLAND_ITEM_KEY)
				== ModBlocks.SOUL_FARMLAND_ITEM, "灵魂耕地物品未注册");
		helper.assertTrue(new ItemStack(ModBlocks.SOUL_FARMLAND_ITEM).getRarity() == Rarity.RARE,
				"灵魂耕地物品稀有度错误");

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		BlockPos supportPos = new BlockPos(6, 0, 2);
		helper.setBlock(supportPos, Blocks.STONE);
		player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModBlocks.SOUL_FARMLAND_ITEM));
		BlockPos absoluteSupportPos = helper.absolutePos(supportPos);
		InteractionResult placeResult = ModBlocks.SOUL_FARMLAND_ITEM.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteSupportPos), Direction.UP, absoluteSupportPos, false)
		));
		helper.assertTrue(placeResult.consumesAction(), "灵魂耕地物品不能放置");
		helper.assertBlockPresent(ModBlocks.SOUL_FARMLAND, supportPos.above());

		BlockPos farmlandPos = new BlockPos(2, 0, 2);
		helper.setBlock(farmlandPos, Blocks.SOUL_SOIL);
		player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.INFINITY_HOE));
		BlockPos absoluteFarmlandPos = helper.absolutePos(farmlandPos);
		InteractionResult tillResult = ModItems.INFINITY_HOE.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteFarmlandPos), Direction.UP, absoluteFarmlandPos, false)
		));
		helper.assertTrue(tillResult.consumesAction(), "无尽锄不能把灵魂土转为灵魂耕地");
		helper.assertBlockPresent(ModBlocks.SOUL_FARMLAND, farmlandPos);
		BlockPos soulSandPos = new BlockPos(4, 0, 2);
		helper.setBlock(soulSandPos, Blocks.SOUL_SAND);
		BlockPos absoluteSoulSandPos = helper.absolutePos(soulSandPos);
		ModItems.INFINITY_HOE.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteSoulSandPos), Direction.UP, absoluteSoulSandPos, false)
		));
		helper.assertBlockPresent(ModBlocks.SOUL_FARMLAND, soulSandPos);

		BlockState farmland = helper.getBlockState(farmlandPos);
		helper.assertTrue(farmland.is(BlockTags.MINEABLE_WITH_SHOVEL), "灵魂耕地没有铲类挖掘标签");
		helper.assertTrue(farmland.is(BlockTags.SUPPORTS_CROPS) && farmland.is(BlockTags.GROWS_CROPS),
				"灵魂耕地不能承载并滋养普通作物");
		helper.assertTrue(farmland.is(BlockTags.SUPPORTS_NETHER_WART), "灵魂耕地不能承载地狱疣");
		helper.assertTrue(farmland.is(BlockTags.SUPPORTS_SUGAR_CANE), "灵魂耕地不能承载甘蔗");
		helper.assertTrue(farmland.is(BlockTags.SUPPORTS_BAMBOO) && farmland.is(BlockTags.SUPPORTS_CACTUS),
				"灵魂耕地缺少扩展植物支持标签");
		helper.assertTrue(Math.abs(farmland.getShape(helper.getLevel(), absoluteFarmlandPos).bounds().maxY - 0.9375) < 0.0001,
				"灵魂耕地碰撞外形高度错误");

		BlockPos plantPos = farmlandPos.above();
		helper.setBlock(plantPos, Blocks.WHEAT.defaultBlockState().setValue(CropBlock.AGE, 0));
		helper.assertTrue(helper.getBlockState(plantPos).canSurvive(helper.getLevel(), helper.absolutePos(plantPos)),
				"小麦不能在灵魂耕地上存活");
		RandomSource random = RandomSource.create(26L);
		for (int tick = 0; tick < 20 && helper.getBlockState(plantPos).getValue(CropBlock.AGE) == 0; tick++) {
			farmland.randomTick(helper.getLevel(), absoluteFarmlandPos, random);
		}
		helper.assertTrue(helper.getBlockState(plantPos).getValue(CropBlock.AGE) > 0,
				"灵魂耕地没有加速小麦生长");

		helper.setBlock(plantPos, Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, 0));
		helper.assertTrue(helper.getBlockState(plantPos).canSurvive(helper.getLevel(), helper.absolutePos(plantPos)),
				"地狱疣不能在灵魂耕地上存活");
		for (int tick = 0; tick < 20 && helper.getBlockState(plantPos).getValue(NetherWartBlock.AGE) == 0; tick++) {
			farmland.randomTick(helper.getLevel(), absoluteFarmlandPos, random);
		}
		helper.assertTrue(helper.getBlockState(plantPos).getValue(NetherWartBlock.AGE) == 1,
				"灵魂耕地没有加速地狱疣生长");

		helper.setBlock(farmlandPos.east(), Blocks.WATER);
		helper.setBlock(plantPos, Blocks.SUGAR_CANE.defaultBlockState().setValue(SugarCaneBlock.AGE, 1));
		helper.assertTrue(helper.getBlockState(plantPos).canSurvive(helper.getLevel(), helper.absolutePos(plantPos)),
				"甘蔗不能在有水的灵魂耕地上存活");
		for (int tick = 0; tick < 20 && helper.getBlockState(plantPos).getValue(SugarCaneBlock.AGE) == 1; tick++) {
			farmland.randomTick(helper.getLevel(), absoluteFarmlandPos, random);
		}
		helper.assertTrue(helper.getBlockState(plantPos).getValue(SugarCaneBlock.AGE) == 6,
				"灵魂耕地没有把甘蔗年龄加速五级");

		helper.setBlock(plantPos, Blocks.AIR);
		helper.setBlock(farmlandPos.east(), Blocks.AIR);
		for (int tick = 0; tick < 20; tick++) {
			farmland.randomTick(helper.getLevel(), absoluteFarmlandPos, random);
		}
		helper.assertBlockPresent(ModBlocks.SOUL_FARMLAND, farmlandPos);
		helper.succeed();
	}

	@GameTest
	public void sculkCraftingTableCraftsPersistsAndUsesTierRecipes(GameTestHelper helper) {
		helper.assertTrue(BuiltInRegistries.BLOCK.getValue(ModBlocks.SCULK_CRAFTING_TABLE_KEY)
				== ModBlocks.SCULK_CRAFTING_TABLE, "幽匿工作台方块未注册");
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModBlocks.SCULK_CRAFTING_TABLE_ITEM_KEY)
				== ModBlocks.SCULK_CRAFTING_TABLE_ITEM, "幽匿工作台物品未注册");
		helper.assertTrue(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(Avaritia26.id("sculk_crafting_table"))
				== ModBlockEntities.SCULK_CRAFTING_TABLE, "幽匿工作台方块实体未注册");
		helper.assertTrue(BuiltInRegistries.MENU.getValue(Avaritia26.id("sculk_crafting"))
				== ModMenus.SCULK_CRAFTING, "幽匿工作台菜单未注册");

		CraftingInput recipeInput = CraftingInput.of(3, 3, List.of(
				new ItemStack(Items.ECHO_SHARD), new ItemStack(Blocks.SCULK_SHRIEKER), new ItemStack(Items.ECHO_SHARD),
				new ItemStack(Blocks.SCULK), new ItemStack(ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM), new ItemStack(Blocks.SCULK),
				new ItemStack(Items.ECHO_SHARD), new ItemStack(Blocks.SCULK_CATALYST), new ItemStack(Items.ECHO_SHARD)
		));
		assertCraftingRecipe(helper, "sculk_crafting_table", recipeInput, ModBlocks.SCULK_CRAFTING_TABLE_ITEM, 1);
		List<ItemStack> wrongRecipe = copyStacks(recipeInput.items());
		wrongRecipe.set(4, new ItemStack(Blocks.CRAFTING_TABLE));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("sculk_crafting_table"));
		helper.assertFalse(helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(RecipeType.CRAFTING, CraftingInput.of(3, 3, wrongRecipe), helper.getLevel())
				.filter(recipe -> recipe.id().equals(recipeKey)).isPresent(), "幽匿工作台配方错误接受普通工作台");

		BlockPos tablePos = new BlockPos(5, 0, 5);
		helper.setBlock(tablePos, ModBlocks.SCULK_CRAFTING_TABLE);
		SculkCraftingTableBlockEntity table = helper.getBlockEntity(tablePos, SculkCraftingTableBlockEntity.class);
		helper.assertTrue(table.getContainerSize() == 9, "幽匿工作台应持久保存 3×3 输入");
		BlockState tableState = helper.getBlockState(tablePos);
		helper.assertTrue(tableState.getLightEmission() == 15, "幽匿工作台亮度错误");
		helper.assertTrue(tableState.is(BlockTags.MINEABLE_WITH_PICKAXE), "幽匿工作台缺少镐类挖掘标签");

		for (int slot = 0; slot < 9; slot++) {
			table.setItem(slot, new ItemStack(slot == 4 ? Items.NETHERITE_SCRAP : Items.DIAMOND));
		}
		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		BlockPos absoluteTablePos = helper.absolutePos(tablePos);
		player.setPos(Vec3.atCenterOf(absoluteTablePos));
		SculkCraftingMenu menu = new SculkCraftingMenu(
				1,
				player.getInventory(),
				table,
				net.minecraft.world.inventory.ContainerLevelAccess.create(helper.getLevel(), absoluteTablePos)
		);
		helper.assertTrue(menu.slots.size() == 46, "幽匿工作台菜单槽位数量错误");
		helper.assertTrue(menu.slots.getFirst().getItem().is(ModItems.DIAMOND_LATTICE),
				"幽匿工作台没有匹配 3×3 分级配方");
		ItemStack crafted = menu.quickMoveStack(player, SculkCraftingMenu.RESULT_SLOT);
		helper.assertTrue(crafted.is(ModItems.DIAMOND_LATTICE), "幽匿工作台快速合成输出错误");
		helper.assertTrue(table.isEmpty(), "幽匿工作台合成后没有正确消耗九格输入");
		helper.assertTrue(player.getInventory().getNonEquipmentItems().stream()
				.anyMatch(stack -> stack.is(ModItems.DIAMOND_LATTICE)), "幽匿工作台快速合成没有把产物移入背包");

		table.setItem(0, new ItemStack(Items.ECHO_SHARD, 2));
		table.setItem(8, new ItemStack(Blocks.SCULK_CATALYST));
		BlockEntity loaded = BlockEntity.loadStatic(
				absoluteTablePos,
				tableState,
				table.saveWithFullMetadata(helper.getLevel().registryAccess()),
				helper.getLevel().registryAccess()
		);
		helper.assertTrue(loaded instanceof SculkCraftingTableBlockEntity, "幽匿工作台无法从存档恢复");
		SculkCraftingTableBlockEntity loadedTable = (SculkCraftingTableBlockEntity) loaded;
		helper.assertTrue(loadedTable.getItem(0).getCount() == 2
				&& loadedTable.getItem(8).is(Blocks.SCULK_CATALYST.asItem()),
				"幽匿工作台首尾槽位存档错误");

		helper.destroyBlock(tablePos);
		helper.assertBlockPresent(Blocks.AIR, tablePos);
		helper.runAfterDelay(1, () -> {
			List<ItemEntity> drops = helper.getLevel().getEntitiesOfClass(
					ItemEntity.class,
					new AABB(absoluteTablePos).inflate(2.0),
					entity -> entity.getItem().is(Items.ECHO_SHARD)
							|| entity.getItem().is(Blocks.SCULK_CATALYST.asItem())
			);
			helper.assertTrue(drops.stream().mapToInt(entity -> entity.getItem().getCount()).sum() == 3,
					"破坏幽匿工作台时没有返还持久输入");
			helper.succeed();
		});
	}

	@GameTest
	public void netherCraftingTableCraftsPersistsAndUsesFiveByFiveRecipes(GameTestHelper helper) {
		helper.assertTrue(BuiltInRegistries.BLOCK.getValue(ModBlocks.NETHER_CRAFTING_TABLE_KEY)
				== ModBlocks.NETHER_CRAFTING_TABLE, "炼狱工作台方块未注册");
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModBlocks.NETHER_CRAFTING_TABLE_ITEM_KEY)
				== ModBlocks.NETHER_CRAFTING_TABLE_ITEM, "炼狱工作台物品未注册");
		helper.assertTrue(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(Avaritia26.id("nether_crafting_table"))
				== ModBlockEntities.NETHER_CRAFTING_TABLE, "炼狱工作台方块实体未注册");
		helper.assertTrue(BuiltInRegistries.MENU.getValue(Avaritia26.id("nether_crafting"))
				== ModMenus.NETHER_CRAFTING, "炼狱工作台菜单未注册");

		CraftingInput recipeInput = CraftingInput.of(3, 3, List.of(
				new ItemStack(Blocks.WITHER_SKELETON_SKULL), new ItemStack(Blocks.RESPAWN_ANCHOR), new ItemStack(Blocks.WITHER_SKELETON_SKULL),
				new ItemStack(Blocks.NETHERRACK), new ItemStack(ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM), new ItemStack(Blocks.NETHERRACK),
				new ItemStack(Items.NETHERITE_INGOT), new ItemStack(Items.NETHER_STAR), new ItemStack(Items.NETHERITE_INGOT)
		));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("nether_crafting_table"));
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, recipeInput, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.orElseThrow(() -> helper.assertionException("正确材料未匹配炼狱工作台配方"));
		helper.assertTrue(recipe.value().assemble(recipeInput).is(ModBlocks.NETHER_CRAFTING_TABLE_ITEM),
				"炼狱工作台配方输出错误");
		List<ItemStack> wrongRecipe = copyStacks(recipeInput.items());
		wrongRecipe.set(4, new ItemStack(Blocks.CRAFTING_TABLE));
		helper.assertFalse(helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(3, 3, wrongRecipe), helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey)).isPresent(), "炼狱工作台配方错误接受普通工作台");

		BlockPos tablePos = new BlockPos(5, 0, 5);
		helper.setBlock(tablePos, ModBlocks.NETHER_CRAFTING_TABLE);
		NetherCraftingTableBlockEntity table = helper.getBlockEntity(tablePos, NetherCraftingTableBlockEntity.class);
		helper.assertTrue(table.getContainerSize() == 25, "炼狱工作台应持久保存 5×5 输入");
		BlockState tableState = helper.getBlockState(tablePos);
		helper.assertTrue(tableState.getLightEmission() == 15, "炼狱工作台亮度错误");
		helper.assertTrue(tableState.is(BlockTags.MINEABLE_WITH_PICKAXE), "炼狱工作台缺少镐类挖掘标签");
		helper.assertTrue(tableState.getDestroySpeed(helper.getLevel(), helper.absolutePos(tablePos)) == 50.0F,
				"炼狱工作台硬度错误");
		helper.assertTrue(ModBlocks.NETHER_CRAFTING_TABLE.getExplosionResistance() == 1000.0F,
				"炼狱工作台爆炸抗性错误");
		helper.assertTrue(new ItemStack(ModBlocks.NETHER_CRAFTING_TABLE_ITEM).getRarity() == Rarity.UNCOMMON,
				"炼狱工作台稀有度错误");

		CraftingInput fiveByFive = blazeCubeInput();
		for (int slot = 0; slot < 25; slot++) {
			table.setItem(slot, fiveByFive.items().get(slot).copy());
		}
		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		BlockPos absoluteTablePos = helper.absolutePos(tablePos);
		player.setPos(Vec3.atCenterOf(absoluteTablePos));
		NetherCraftingMenu menu = new NetherCraftingMenu(
				1,
				player.getInventory(),
				table,
				net.minecraft.world.inventory.ContainerLevelAccess.create(helper.getLevel(), absoluteTablePos)
		);
		helper.assertTrue(menu.slots.size() == 62, "炼狱工作台菜单槽位数量错误");
		helper.assertTrue(menu.slots.getFirst().getItem().is(ModItems.BLAZE_CUBE)
				&& menu.slots.getFirst().getItem().getCount() == 2, "炼狱工作台没有匹配 5×5 分级配方");
		ItemStack crafted = menu.quickMoveStack(player, SculkCraftingMenu.RESULT_SLOT);
		helper.assertTrue(crafted.is(ModItems.BLAZE_CUBE) && crafted.getCount() == 2,
				"炼狱工作台快速合成输出错误");
		helper.assertTrue(table.isEmpty(), "炼狱工作台合成后没有正确消耗 5×5 输入");
		helper.assertTrue(player.getInventory().getNonEquipmentItems().stream()
				.anyMatch(stack -> stack.is(ModItems.BLAZE_CUBE) && stack.getCount() == 2),
				"炼狱工作台快速合成没有把产物移入背包");

		table.setItem(0, new ItemStack(Items.NETHER_STAR, 2));
		table.setItem(24, new ItemStack(Items.NETHERITE_INGOT));
		BlockEntity loaded = BlockEntity.loadStatic(
				absoluteTablePos,
				tableState,
				table.saveWithFullMetadata(helper.getLevel().registryAccess()),
				helper.getLevel().registryAccess()
		);
		helper.assertTrue(loaded instanceof NetherCraftingTableBlockEntity, "炼狱工作台无法从存档恢复");
		NetherCraftingTableBlockEntity loadedTable = (NetherCraftingTableBlockEntity) loaded;
		helper.assertTrue(loadedTable.getItem(0).getCount() == 2
				&& loadedTable.getItem(24).is(Items.NETHERITE_INGOT), "炼狱工作台首尾槽位存档错误");

		helper.destroyBlock(tablePos);
		helper.runAfterDelay(1, () -> {
			List<ItemEntity> drops = helper.getLevel().getEntitiesOfClass(
					ItemEntity.class,
					new AABB(absoluteTablePos).inflate(2.0),
					entity -> entity.getItem().is(Items.NETHER_STAR) || entity.getItem().is(Items.NETHERITE_INGOT)
			);
			helper.assertTrue(drops.stream().mapToInt(entity -> entity.getItem().getCount()).sum() == 3,
					"破坏炼狱工作台时没有返还持久输入");
			helper.succeed();
		});
	}

	@GameTest
	public void endCraftingTableCraftsPersistsAndUsesSevenBySevenRecipes(GameTestHelper helper) {
		helper.assertTrue(BuiltInRegistries.BLOCK.getValue(ModBlocks.END_CRAFTING_TABLE_KEY)
				== ModBlocks.END_CRAFTING_TABLE, "末地工作台方块未注册");
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModBlocks.END_CRAFTING_TABLE_ITEM_KEY)
				== ModBlocks.END_CRAFTING_TABLE_ITEM, "末地工作台物品未注册");
		helper.assertTrue(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(Avaritia26.id("end_crafting_table"))
				== ModBlockEntities.END_CRAFTING_TABLE, "末地工作台方块实体未注册");
		helper.assertTrue(BuiltInRegistries.MENU.getValue(Avaritia26.id("end_crafting"))
				== ModMenus.END_CRAFTING, "末地工作台菜单未注册");

		CraftingInput recipeInput = CraftingInput.of(5, 5, List.of(
				new ItemStack(Items.END_CRYSTAL), new ItemStack(Blocks.END_PORTAL_FRAME), new ItemStack(Blocks.END_PORTAL_FRAME), new ItemStack(Blocks.END_PORTAL_FRAME), new ItemStack(Items.END_CRYSTAL),
				new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.PURPUR_PILLAR), new ItemStack(Items.ENDER_EYE), new ItemStack(Blocks.PURPUR_PILLAR), new ItemStack(Blocks.OBSIDIAN),
				new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.END_STONE_BRICKS), new ItemStack(ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM), new ItemStack(Blocks.END_STONE_BRICKS), new ItemStack(Blocks.OBSIDIAN),
				new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.ENDER_CHEST), new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.OBSIDIAN),
				new ItemStack(Items.END_CRYSTAL), new ItemStack(Items.DRAGON_BREATH), new ItemStack(Items.DRAGON_BREATH), new ItemStack(Items.DRAGON_BREATH), new ItemStack(Items.END_CRYSTAL)
		));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("end_crafting_table"));
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, recipeInput, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.orElseThrow(() -> helper.assertionException("正确材料未匹配末地工作台配方"));
		ItemStack recipeResult = recipe.value().assemble(recipeInput);
		helper.assertTrue(recipeResult.is(ModBlocks.END_CRAFTING_TABLE_ITEM) && recipeResult.getCount() == 2,
				"末地工作台配方应当产出两个工作台");
		List<ItemStack> wrongRecipe = copyStacks(recipeInput.items());
		wrongRecipe.set(12, new ItemStack(Blocks.CRAFTING_TABLE));
		helper.assertFalse(helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(5, 5, wrongRecipe), helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey)).isPresent(), "末地工作台配方错误接受普通工作台");

		BlockPos tablePos = new BlockPos(5, 0, 5);
		helper.setBlock(tablePos, ModBlocks.END_CRAFTING_TABLE);
		EndCraftingTableBlockEntity table = helper.getBlockEntity(tablePos, EndCraftingTableBlockEntity.class);
		helper.assertTrue(table.getContainerSize() == 49, "末地工作台应持久保存 7×7 输入");
		BlockState tableState = helper.getBlockState(tablePos);
		helper.assertTrue(tableState.getLightEmission() == 15, "末地工作台亮度错误");
		helper.assertTrue(tableState.is(BlockTags.MINEABLE_WITH_PICKAXE), "末地工作台缺少镐类挖掘标签");
		helper.assertTrue(tableState.getDestroySpeed(helper.getLevel(), helper.absolutePos(tablePos)) == 75.0F,
				"末地工作台硬度错误");
		helper.assertTrue(ModBlocks.END_CRAFTING_TABLE.getExplosionResistance() == 1500.0F,
				"末地工作台爆炸抗性错误");
		helper.assertTrue(tableState.getSoundType().getBreakSound() == net.minecraft.sounds.SoundEvents.END_PORTAL_FRAME_FILL,
				"末地工作台没有使用末地传送门音效");
		helper.assertTrue(new ItemStack(ModBlocks.END_CRAFTING_TABLE_ITEM).getRarity() == Rarity.RARE,
				"末地工作台稀有度错误");

		CraftingInput sevenBySeven = extremeCraftingTableInput();
		for (int slot = 0; slot < 49; slot++) {
			table.setItem(slot, sevenBySeven.items().get(slot).copy());
		}
		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		BlockPos absoluteTablePos = helper.absolutePos(tablePos);
		player.setPos(Vec3.atCenterOf(absoluteTablePos));
		EndCraftingMenu menu = new EndCraftingMenu(
				1,
				player.getInventory(),
				table,
				net.minecraft.world.inventory.ContainerLevelAccess.create(helper.getLevel(), absoluteTablePos)
		);
		helper.assertTrue(menu.slots.size() == 86, "末地工作台菜单槽位数量错误");
		helper.assertTrue(menu.slots.getFirst().getItem().is(ModBlocks.EXTREME_CRAFTING_TABLE_ITEM),
				"末地工作台没有匹配 7×7 分级配方");
		ItemStack crafted = menu.quickMoveStack(player, SculkCraftingMenu.RESULT_SLOT);
		helper.assertTrue(crafted.is(ModBlocks.EXTREME_CRAFTING_TABLE_ITEM), "末地工作台快速合成输出错误");
		helper.assertTrue(table.isEmpty(), "末地工作台合成后没有正确消耗 7×7 输入");
		helper.assertTrue(player.getInventory().getNonEquipmentItems().stream()
				.anyMatch(stack -> stack.is(ModBlocks.EXTREME_CRAFTING_TABLE_ITEM)),
				"末地工作台快速合成没有把产物移入背包");

		table.setItem(0, new ItemStack(Items.DRAGON_BREATH, 2));
		table.setItem(48, new ItemStack(Items.ENDER_EYE));
		BlockEntity loaded = BlockEntity.loadStatic(
				absoluteTablePos,
				tableState,
				table.saveWithFullMetadata(helper.getLevel().registryAccess()),
				helper.getLevel().registryAccess()
		);
		helper.assertTrue(loaded instanceof EndCraftingTableBlockEntity, "末地工作台无法从存档恢复");
		EndCraftingTableBlockEntity loadedTable = (EndCraftingTableBlockEntity) loaded;
		helper.assertTrue(loadedTable.getItem(0).getCount() == 2 && loadedTable.getItem(48).is(Items.ENDER_EYE),
				"末地工作台首尾槽位存档错误");

		helper.destroyBlock(tablePos);
		helper.runAfterDelay(1, () -> {
			List<ItemEntity> drops = helper.getLevel().getEntitiesOfClass(
					ItemEntity.class,
					new AABB(absoluteTablePos).inflate(2.0),
					entity -> entity.getItem().is(Items.DRAGON_BREATH) || entity.getItem().is(Items.ENDER_EYE)
			);
			helper.assertTrue(drops.stream().mapToInt(entity -> entity.getItem().getCount()).sum() == 3,
					"破坏末地工作台时没有返还持久输入");
			helper.succeed();
		});
	}

	@GameTest
	public void neutronRingCraftsStoresItemsAndRejectsNesting(GameTestHelper helper) {
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.NEUTRON_RING_KEY) == ModItems.NEUTRON_RING,
				"中子指环物品未注册");
		helper.assertTrue(BuiltInRegistries.DATA_COMPONENT_TYPE.getValue(Avaritia26.id("neutron_ring_inventory"))
				== ModDataComponents.NEUTRON_RING_INVENTORY, "中子指环存储组件未注册");
		helper.assertTrue(BuiltInRegistries.MENU.getValue(Avaritia26.id("neutron_ring")) == ModMenus.NEUTRON_RING,
				"中子指环菜单未注册");

		ItemStack ring = new ItemStack(ModItems.NEUTRON_RING);
		helper.assertTrue(ring.getMaxStackSize() == 1, "中子指环必须只能单件堆叠");
		helper.assertTrue(ring.getRarity() == Rarity.EPIC, "中子指环稀有度错误");
		helper.assertTrue(ring.has(DataComponents.DAMAGE_RESISTANT), "中子指环缺少防火属性");

		CraftingInput recipeInput = neutronRingInput();
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("neutron_ring"));
		RecipeHolder<Recipe<CraftingInput>> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, recipeInput, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.orElseThrow(() -> helper.assertionException("正确材料未匹配中子指环配方"));
		helper.assertTrue(recipe.value().assemble(recipeInput).is(ModItems.NEUTRON_RING), "中子指环配方输出错误");
		List<ItemStack> wrongRecipe = copyStacks(recipeInput.items());
		wrongRecipe.set(24, new ItemStack(Items.NETHER_STAR));
		helper.assertFalse(helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(7, 7, wrongRecipe), helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey)).isPresent(), "中子指环配方错误接受其他中心材料");

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.getInventory().setSelectedSlot(0);
		player.getInventory().setItem(0, ring);
		player.getInventory().setItem(9, new ItemStack(Items.DIAMOND, 32));
		helper.assertTrue(NeutronRingItem.findRingSlot(player) == 0, "按键打开逻辑没有找到背包中的中子指环");
		NeutronRingMenu menu = new NeutronRingMenu(1, player.getInventory(), 0);
		helper.assertTrue(menu.slots.size() == 117, "中子指环菜单应包含 81 个存储槽与 36 个背包槽");
		helper.assertFalse(menu.slots.getFirst().mayPlace(ring.copy()), "中子指环不应允许嵌套存储自身");
		helper.assertFalse(menu.slots.get(108).mayPickup(player), "打开菜单时不应允许取走正在使用的中子指环");

		ItemStack moved = menu.quickMoveStack(player, 81);
		helper.assertTrue(moved.is(Items.DIAMOND) && moved.getCount() == 32, "中子指环快速存入输出错误");
		helper.assertTrue(player.getInventory().getItem(9).isEmpty(), "快速存入后背包材料没有清空");
		ItemContainerContents stored = ring.get(ModDataComponents.NEUTRON_RING_INVENTORY);
		helper.assertTrue(stored != null && stored.nonEmptyItemCopyStream()
				.anyMatch(stack -> stack.is(Items.DIAMOND) && stack.getCount() == 32), "中子指环没有持久保存物品");

		NeutronRingMenu reopened = new NeutronRingMenu(2, player.getInventory(), 0);
		helper.assertTrue(reopened.slots.getFirst().getItem().is(Items.DIAMOND)
				&& reopened.slots.getFirst().getItem().getCount() == 32, "重新打开中子指环后存储内容丢失");
		ItemStack extracted = reopened.quickMoveStack(player, 0);
		helper.assertTrue(extracted.is(Items.DIAMOND) && extracted.getCount() == 32, "中子指环快速取出错误");
		helper.assertTrue(ring.getOrDefault(ModDataComponents.NEUTRON_RING_INVENTORY, ItemContainerContents.EMPTY)
				.nonEmptyItemCopyStream().findAny().isEmpty(), "中子指环取出后组件内容未清空");
		helper.succeed();
	}

	@GameTest
	public void infinityTotemSmithsPreventsDeathAndWearsDown(GameTestHelper helper) {
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_TOTEM_KEY) == ModItems.INFINITY_TOTEM,
				"无尽图腾物品未注册");
		ItemStack totem = new ItemStack(ModItems.INFINITY_TOTEM);
		helper.assertTrue(totem.getMaxStackSize() == 1 && totem.getMaxDamage() == 999,
				"无尽图腾堆叠数或耐久错误");
		helper.assertTrue(totem.getRarity() == Rarity.EPIC && totem.has(DataComponents.DAMAGE_RESISTANT),
				"无尽图腾稀有度或防火属性错误");

		ExtremeSmithingInput smithingInput = new ExtremeSmithingInput(
				new ItemStack(ModItems.UPGRADE_SMITHING_TEMPLATE),
				new ItemStack(Items.TOTEM_OF_UNDYING),
				new ItemStack(Items.EXPERIENCE_BOTTLE),
				new ItemStack(ModItems.ENHANCEMENT_CORE),
				new ItemStack(Items.BEACON)
		);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_totem"));
		RecipeHolder<ExtremeSmithingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_SMITHING, smithingInput, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.orElseThrow(() -> helper.assertionException("正确材料未匹配无尽图腾锻造配方"));
		helper.assertTrue(recipe.value().assemble(smithingInput).is(ModItems.INFINITY_TOTEM),
				"无尽图腾锻造配方输出错误");
		ExtremeSmithingInput duplicateAdditions = new ExtremeSmithingInput(
				smithingInput.template(),
				smithingInput.base(),
				new ItemStack(Items.BEACON),
				new ItemStack(Items.BEACON),
				new ItemStack(Items.BEACON)
		);
		helper.assertFalse(recipe.value().matches(duplicateAdditions, helper.getLevel()),
				"无尽图腾配方不应接受重复追加材料");

		ServerPlayer player = (ServerPlayer) helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setPos(Vec3.atCenterOf(helper.absolutePos(new BlockPos(5, 1, 5))));
		player.getInventory().setItem(9, totem);
		player.getActiveEffectsMap().put(MobEffects.POISON, new MobEffectInstance(MobEffects.POISON, 200));
		player.setHealth(1.0F);
		var zombie = helper.spawnWithNoFreeWill(EntityTypes.ZOMBIE, new BlockPos(6, 1, 5));
		helper.assertTrue(ModCombatEvents.findInfinityTotem(player) == totem,
				"死亡保护没有找到背包中的无尽图腾");
		boolean allowDeath = ServerLivingEntityEvents.ALLOW_DEATH.invoker().allowDeath(
				player,
				helper.getLevel().damageSources().generic(),
				20.0F
		);
		helper.assertFalse(allowDeath, "无尽图腾没有阻止玩家死亡");
		helper.assertTrue(player.getHealth() == player.getMaxHealth(), "无尽图腾没有回满生命");
		helper.assertFalse(player.hasEffect(MobEffects.POISON), "无尽图腾没有清除原有状态效果");
		helper.assertTrue(player.getEffect(MobEffects.REGENERATION).getDuration() == 2600
				&& player.getEffect(MobEffects.REGENERATION).getAmplifier() == 4,
				"无尽图腾生命恢复效果错误");
		helper.assertTrue(player.getEffect(MobEffects.RESISTANCE).getDuration() == 400
				&& player.getEffect(MobEffects.RESISTANCE).getAmplifier() == 1,
				"无尽图腾抗性提升效果错误");
		helper.assertTrue(player.getEffect(MobEffects.ABSORPTION).getDuration() == 700
				&& player.getEffect(MobEffects.FIRE_RESISTANCE).getDuration() == 1100,
				"无尽图腾吸收或防火效果错误");
		helper.assertTrue(player.hasEffect(MobEffects.JUMP_BOOST) && player.hasEffect(MobEffects.SPEED),
				"无尽图腾第十次强化效果未触发");
		helper.assertFalse(zombie.isAlive(), "无尽图腾强化触发没有消灭附近敌对生物");
		helper.assertTrue(totem.getDamageValue() == 1, "无尽图腾触发后没有损失一点耐久");

		totem.setDamageValue(998);
		player.setHealth(1.0F);
		helper.assertFalse(ServerLivingEntityEvents.ALLOW_DEATH.invoker().allowDeath(
				player,
				helper.getLevel().damageSources().generic(),
				20.0F
		), "最后一点耐久的无尽图腾没有阻止死亡");
		helper.assertTrue(totem.isEmpty(), "无尽图腾耗尽 999 点耐久后没有损坏");
		helper.succeed();
	}

	@GameTest
	public void infinityRingPreservesUpstreamWipBehavior(GameTestHelper helper) {
		ItemStack ring = new ItemStack(ModItems.INFINITY_RING);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_RING_KEY) == ModItems.INFINITY_RING,
				"无尽指环物品未注册");
		helper.assertTrue(ring.getMaxStackSize() == 1 && ring.getRarity() == Rarity.EPIC,
				"无尽指环堆叠数或稀有度错误");
		helper.assertTrue(ring.has(DataComponents.DAMAGE_RESISTANT), "无尽指环缺少防火属性");

		List<Component> tooltip = new java.util.ArrayList<>();
		ModItems.INFINITY_RING.appendHoverText(
				ring,
				Item.TooltipContext.of(helper.getLevel()),
				TooltipDisplay.DEFAULT,
				tooltip::add,
				TooltipFlag.NORMAL
		);
		helper.assertTrue(tooltip.size() == 1 && tooltip.getFirst().getStyle().isItalic(),
				"无尽指环缺少上游 WIP 说明");

		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_ring"));
		helper.assertTrue(helper.getLevel().getServer().getRecipeManager().byKey(recipeKey).isEmpty(),
				"上游 WIP 无尽指环不应拥有配方");
		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, ring);
		InteractionResult result = ModItems.INFINITY_RING.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertFalse(result.consumesAction(), "上游 WIP 无尽指环不应响应使用");
		helper.assertTrue(player.getMainHandItem() == ring && ring.getCount() == 1,
				"使用上游 WIP 无尽指环不应改变物品");
		helper.succeed();
	}

	@GameTest
	public void infinityUmbrellaCraftsCyclesWeatherAndProjectiles(GameTestHelper helper) {
		ItemStack umbrella = new ItemStack(ModItems.INFINITY_UMBRELLA);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_UMBRELLA_KEY) == ModItems.INFINITY_UMBRELLA,
				"无尽雨伞物品未注册");
		helper.assertTrue(umbrella.getMaxStackSize() == 1 && umbrella.getRarity() == Rarity.EPIC
				&& umbrella.has(DataComponents.DAMAGE_RESISTANT), "无尽雨伞物品属性错误");
		helper.assertTrue(BuiltInRegistries.ENTITY_TYPE.getValue(ModEntityTypes.UMBRELLA_SUN_PROJECTILE_KEY)
				== ModEntityTypes.UMBRELLA_SUN_PROJECTILE, "无尽雨伞晴天弹未注册");
		helper.assertTrue(BuiltInRegistries.ENTITY_TYPE.getValue(ModEntityTypes.UMBRELLA_RAIN_PROJECTILE_KEY)
				== ModEntityTypes.UMBRELLA_RAIN_PROJECTILE, "无尽雨伞雨天弹未注册");
		helper.assertTrue(BuiltInRegistries.ENTITY_TYPE.getValue(ModEntityTypes.UMBRELLA_STORM_PROJECTILE_KEY)
				== ModEntityTypes.UMBRELLA_STORM_PROJECTILE, "无尽雨伞雷暴弹未注册");

		CraftingInput input = infinityUmbrellaInput();
		assertExtremeRecipe(helper, "infinity_umbrella", input, ModItems.INFINITY_UMBRELLA);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(13, new ItemStack(Items.IRON_INGOT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_umbrella"));
		helper.assertFalse(helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, CraftingInput.of(9, 9, wrongStacks), helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey)).isPresent(),
				"无尽雨伞配方错误接受了替代材料");

		ServerPlayer player = (ServerPlayer) helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setPos(Vec3.atCenterOf(helper.absolutePos(new BlockPos(5, 2, 5))));
		player.setItemInHand(InteractionHand.MAIN_HAND, umbrella);
		player.setShiftKeyDown(true);
		helper.assertTrue(ModItems.INFINITY_UMBRELLA.use(helper.getLevel(), player, InteractionHand.MAIN_HAND)
				.consumesAction(), "潜行使用无尽雨伞没有切换模式");
		helper.assertTrue(InfinityUmbrellaItem.getMode(umbrella) == InfinityUmbrellaItem.SUN,
				"无尽雨伞没有从普通模式切换到晴天模式");
		player.setShiftKeyDown(false);
		player.setXRot(-90.0F);

		InfinityUmbrellaItem.setMode(umbrella, InfinityUmbrellaItem.RAIN);
		ModItems.INFINITY_UMBRELLA.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(helper.getLevel().getWeatherData().isRaining()
				&& !helper.getLevel().getWeatherData().isThundering(), "雨天模式没有开启降雨");
		helper.assertTrue(helper.getLevel().getWeatherData().getRainTime() >= 12000
				&& helper.getLevel().getWeatherData().getRainTime() <= 24000, "雨天持续时间不在上游范围内");

		InfinityUmbrellaItem.setMode(umbrella, InfinityUmbrellaItem.STORM);
		ModItems.INFINITY_UMBRELLA.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(helper.getLevel().getWeatherData().isRaining()
				&& helper.getLevel().getWeatherData().isThundering(), "雷暴模式没有开启雷雨");
		InfinityUmbrellaItem.setMode(umbrella, InfinityUmbrellaItem.SUN);
		ModItems.INFINITY_UMBRELLA.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertFalse(helper.getLevel().getWeatherData().isRaining()
				|| helper.getLevel().getWeatherData().isThundering(), "晴天模式没有清除恶劣天气");

		player.setXRot(0.0F);
		BlockPos lavaPos = helper.absolutePos(new BlockPos(10, 2, 5));
		InfinityUmbrellaItem.setMode(umbrella, InfinityUmbrellaItem.SUN);
		ModItems.INFINITY_UMBRELLA.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		UmbrellaProjectileEntity sunProjectile = helper.getLevel().getEntitiesOfClass(
				UmbrellaProjectileEntity.class,
				player.getBoundingBox().inflate(4.0),
				entity -> entity.getType() == ModEntityTypes.UMBRELLA_SUN_PROJECTILE
		).getFirst();
		sunProjectile.applyImpact(helper.getLevel(), lavaPos);
		helper.assertBlockPresent(Blocks.LAVA, new BlockPos(10, 2, 5));

		BlockPos waterPos = helper.absolutePos(new BlockPos(11, 2, 5));
		UmbrellaProjectileEntity rainProjectile = new UmbrellaProjectileEntity(
				helper.getLevel(), player, UmbrellaProjectileEntity.Mode.RAIN);
		rainProjectile.applyImpact(helper.getLevel(), waterPos);
		helper.assertBlockPresent(Blocks.WATER, new BlockPos(11, 2, 5));

		BlockPos lightningPos = helper.absolutePos(new BlockPos(12, 2, 5));
		UmbrellaProjectileEntity stormProjectile = new UmbrellaProjectileEntity(
				helper.getLevel(), player, UmbrellaProjectileEntity.Mode.STORM);
		stormProjectile.applyImpact(helper.getLevel(), lightningPos);
		helper.assertTrue(!helper.getLevel().getEntitiesOfClass(
				LightningBolt.class,
				new AABB(lightningPos).inflate(1.0)
		).isEmpty(), "雷暴弹没有生成闪电");

		ModItems.INFINITY_UMBRELLA.inventoryTick(umbrella, helper.getLevel(), player, EquipmentSlot.MAINHAND);
		helper.assertTrue(player.hasEffect(MobEffects.SLOW_FALLING), "手持无尽雨伞没有获得缓降");
		player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
		player.getInventory().setItem(9, umbrella);
		ModItems.INFINITY_UMBRELLA.inventoryTick(umbrella, helper.getLevel(), player, null);
		helper.assertFalse(player.hasEffect(MobEffects.SLOW_FALLING), "收起无尽雨伞后缓降没有移除");
		helper.succeed();
	}

	@GameTest
	public void infinityClockSmithsSetsTimeAndAcceleratesBlocks(GameTestHelper helper) {
		ItemStack clock = new ItemStack(ModItems.INFINITY_CLOCK);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_CLOCK_KEY) == ModItems.INFINITY_CLOCK,
				"无尽时钟物品未注册");
		helper.assertTrue(BuiltInRegistries.DATA_COMPONENT_TYPE.getValue(Avaritia26.id("infinity_clock_overclock"))
				== ModDataComponents.INFINITY_CLOCK_OVERCLOCK, "无尽时钟模式组件未注册");
		helper.assertTrue(BuiltInRegistries.DATA_COMPONENT_TYPE.getValue(Avaritia26.id("infinity_clock_multiplier"))
				== ModDataComponents.INFINITY_CLOCK_MULTIPLIER, "无尽时钟倍率组件未注册");
		helper.assertTrue(BuiltInRegistries.MENU.getValue(Avaritia26.id("infinity_clock")) == ModMenus.INFINITY_CLOCK,
				"无尽时钟菜单未注册");
		helper.assertTrue(clock.getMaxStackSize() == 1 && clock.getRarity() == Rarity.EPIC
				&& clock.has(DataComponents.DAMAGE_RESISTANT), "无尽时钟物品属性错误");

		ExtremeSmithingInput smithingInput = new ExtremeSmithingInput(
				new ItemStack(ModItems.UPGRADE_SMITHING_TEMPLATE),
				new ItemStack(Items.CLOCK),
				new ItemStack(Items.ENCHANTED_GOLDEN_APPLE),
				new ItemStack(ModItems.ENHANCEMENT_CORE),
				new ItemStack(ModItems.ETERNAL_SINGULARITY)
		);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_clock"));
		RecipeHolder<ExtremeSmithingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_SMITHING, smithingInput, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.orElseThrow(() -> helper.assertionException("正确材料未匹配无尽时钟锻造配方"));
		helper.assertTrue(recipe.value().assemble(smithingInput).is(ModItems.INFINITY_CLOCK),
				"无尽时钟锻造配方输出错误");

		ServerPlayer player = (ServerPlayer) helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.getInventory().setSelectedSlot(0);
		player.setItemInHand(InteractionHand.MAIN_HAND, clock);
		player.setShiftKeyDown(true);
		helper.assertTrue(ModItems.INFINITY_CLOCK.use(helper.getLevel(), player, InteractionHand.MAIN_HAND)
				.consumesAction(), "无尽时钟没有切换到超频模式");
		helper.assertTrue(InfinityClockItem.isOverclock(clock)
				&& clock.get(DataComponents.CUSTOM_MODEL_DATA).getFloat(0) == 1.0F,
				"无尽时钟超频状态或模型状态没有持久保存");
		player.setShiftKeyDown(false);
		for (int expected : List.of(4, 16, 64, 256, 512, 1)) {
			ModItems.INFINITY_CLOCK.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
			helper.assertTrue(InfinityClockItem.getMultiplier(clock) == expected,
					"无尽时钟倍率循环错误，预期 " + expected + "x");
		}

		InfinityClockMenu menu = new InfinityClockMenu(1, player.getInventory(), 0);
		helper.assertTrue(menu.slots.size() == 36, "无尽时钟界面没有映射完整玩家背包");
		helper.assertFalse(menu.slots.get(27).mayPickup(player), "无尽时钟界面没有锁定正在使用的时钟");

		var worldClock = helper.getLevel().dimensionType().defaultClock()
				.orElseThrow(() -> helper.assertionException("测试维度没有默认世界时钟"));
		long originalTime = helper.getLevel().clockManager().getTotalTicks(worldClock);
		SetTimePayload.setTime(helper.getLevel().getServer(), 6000);
		helper.assertTrue(Math.floorMod(helper.getLevel().clockManager().getTotalTicks(worldClock), 24000L) == 6000L,
				"无尽时钟没有设置到正午");
		helper.getLevel().clockManager().setTotalTicks(worldClock, originalTime);

		BlockPos collectorPos = new BlockPos(14, 1, 5);
		helper.setBlock(collectorPos, ModBlocks.NEUTRON_COLLECTOR);
		NeutronCollectorBlockEntity collector = helper.getBlockEntity(collectorPos, NeutronCollectorBlockEntity.class);
		InfinityClockItem.setOverclock(clock, true);
		clock.set(ModDataComponents.INFINITY_CLOCK_MULTIPLIER, 4);
		BlockPos absoluteCollectorPos = helper.absolutePos(collectorPos);
		player.setPos(Vec3.atCenterOf(absoluteCollectorPos));
		InteractionResult accelerationResult = ModItems.INFINITY_CLOCK.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteCollectorPos), Direction.UP, absoluteCollectorPos, false)
		));
		helper.assertTrue(accelerationResult.consumesAction(), "无尽时钟没有接管方块超频交互");
		ClockAccelerationData data = helper.getLevel().getDataStorage().computeIfAbsent(ClockAccelerationData.TYPE);
		helper.assertTrue(data.get(absoluteCollectorPos) == 4, "无尽时钟没有持久保存 4x 超频位置");
		int previousProgress = collector.getProgress();
		ModClockEvents.tickAcceleratedBlocks(helper.getLevel());
		helper.assertTrue(collector.getProgress() >= previousProgress + 4,
				"无尽时钟没有额外驱动方块实体 tick");

		clock.set(ModDataComponents.INFINITY_CLOCK_MULTIPLIER, 1);
		ModItems.INFINITY_CLOCK.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteCollectorPos), Direction.UP, absoluteCollectorPos, false)
		));
		helper.assertTrue(data.get(absoluteCollectorPos) == 1, "无尽时钟 1x 模式没有解除方块超频");
		helper.succeed();
	}

	@GameTest
	public void sideConfigCardCraftsCopiesPersistsAndClearsConfiguration(GameTestHelper helper) {
		ItemStack card = new ItemStack(ModItems.SIDE_CONFIG_CARD);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.SIDE_CONFIG_CARD_KEY) == ModItems.SIDE_CONFIG_CARD,
				"侧面配置卡物品未注册");
		helper.assertTrue(BuiltInRegistries.DATA_COMPONENT_TYPE.getValue(Avaritia26.id("side_configuration"))
				== ModDataComponents.SIDE_CONFIGURATION, "侧面配置组件未注册");
		helper.assertTrue(card.getMaxStackSize() == 1 && card.getRarity() == Rarity.RARE,
				"侧面配置卡物品属性错误");

		CraftingInput recipeInput = CraftingInput.of(3, 3, List.of(
				new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT),
				new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.DIAMOND_LATTICE), new ItemStack(Items.IRON_INGOT),
				ItemStack.EMPTY, new ItemStack(Items.GOLD_INGOT), ItemStack.EMPTY
		));
		assertCraftingRecipe(helper, "side_config_card", recipeInput, ModItems.SIDE_CONFIG_CARD, 1);
		List<ItemStack> wrongRecipe = copyStacks(recipeInput.items());
		wrongRecipe.set(4, new ItemStack(Items.DIAMOND));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("side_config_card"));
		helper.assertFalse(helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(RecipeType.CRAFTING, CraftingInput.of(3, 3, wrongRecipe), helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey)).isPresent(),
				"侧面配置卡中心材料错误时仍匹配配方");

		BlockPos collectorPos = new BlockPos(9, 1, 9);
		BlockPos compressorPos = new BlockPos(11, 1, 9);
		BlockPos clearPos = new BlockPos(13, 1, 9);
		helper.setBlock(collectorPos, ModBlocks.NEUTRON_COLLECTOR);
		helper.setBlock(compressorPos, ModBlocks.NEUTRON_COMPRESSOR.defaultBlockState()
				.setValue(NeutronCompressorBlock.FACING, Direction.EAST));
		helper.setBlock(clearPos, Blocks.STONE);
		NeutronCollectorBlockEntity collector = helper.getBlockEntity(collectorPos, NeutronCollectorBlockEntity.class);
		NeutronCompressorBlockEntity compressor = helper.getBlockEntity(compressorPos, NeutronCompressorBlockEntity.class);
		ServerPlayer player = (ServerPlayer) helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.getInventory().setSelectedSlot(0);
		player.setItemInHand(InteractionHand.MAIN_HAND, card);

		player.setShiftKeyDown(true);
		InteractionResult readResult = ModItems.SIDE_CONFIG_CARD.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(helper.absolutePos(collectorPos)), Direction.UP,
						helper.absolutePos(collectorPos), false)
		));
		helper.assertTrue(readResult.consumesAction(), "侧面配置卡没有读取机器配置");
		SideConfiguration stored = card.get(ModDataComponents.SIDE_CONFIGURATION);
		helper.assertTrue(stored != null && stored.equals(collector.getSideConfiguration()) && card.hasFoil(),
				"侧面配置卡没有保存配置或显示附魔光效");

		RegistryFriendlyByteBuf buffer = new RegistryFriendlyByteBuf(Unpooled.buffer(), helper.getLevel().registryAccess());
		try {
			ItemStack.STREAM_CODEC.encode(buffer, card);
			ItemStack decoded = ItemStack.STREAM_CODEC.decode(buffer);
			helper.assertTrue(stored.equals(decoded.get(ModDataComponents.SIDE_CONFIGURATION)),
					"侧面配置卡网络同步丢失配置");
		} finally {
			buffer.release();
		}

		player.setShiftKeyDown(false);
		InteractionResult applyResult = ModItems.SIDE_CONFIG_CARD.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(helper.absolutePos(compressorPos)), Direction.UP,
						helper.absolutePos(compressorPos), false)
		));
		helper.assertTrue(applyResult.consumesAction() && compressor.getSideConfiguration().equals(stored),
				"侧面配置卡没有将保存配置应用到机器");

		SideConfiguration directional = new SideConfiguration(
				SideConfiguration.Mode.PASSIVE_INPUT,
				SideConfiguration.Mode.PASSIVE_OUTPUT,
				SideConfiguration.Mode.OFF,
				SideConfiguration.Mode.OFF,
				SideConfiguration.Mode.OFF,
				SideConfiguration.Mode.OFF
		);
		compressor.setSideConfiguration(directional);
		helper.assertTrue(compressor.getSlotsForFace(Direction.EAST)[0] == NeutronCompressorBlockEntity.INPUT_SLOT,
				"侧面配置没有按机器朝向映射前侧输入");
		helper.assertTrue(compressor.getSlotsForFace(Direction.WEST)[0] == NeutronCompressorBlockEntity.OUTPUT_SLOT,
				"侧面配置没有按机器朝向映射后侧输出");
		BlockEntity loaded = BlockEntity.loadStatic(
				helper.absolutePos(compressorPos),
				helper.getBlockState(compressorPos),
				compressor.saveWithFullMetadata(helper.getLevel().registryAccess()),
				helper.getLevel().registryAccess()
		);
		helper.assertTrue(loaded instanceof NeutronCompressorBlockEntity loadedCompressor
				&& loadedCompressor.getSideConfiguration().equals(directional),
				"机器重载后丢失侧面配置");

		player.setShiftKeyDown(true);
		InteractionResult clearResult = ModItems.SIDE_CONFIG_CARD.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(helper.absolutePos(clearPos)), Direction.UP,
						helper.absolutePos(clearPos), false)
		));
		helper.assertTrue(clearResult.consumesAction() && !card.has(ModDataComponents.SIDE_CONFIGURATION)
				&& !card.hasFoil(), "侧面配置卡没有清除保存配置或附魔光效");
		helper.succeed();
	}

	@GameTest
	public void infinityBucketSmithsTransfersCyclesPicksUpAndPlacesFluids(GameTestHelper helper) {
		ItemStack bucket = new ItemStack(ModItems.INFINITY_BUCKET);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_BUCKET_KEY) == ModItems.INFINITY_BUCKET,
				"无尽桶物品未注册");
		helper.assertTrue(BuiltInRegistries.DATA_COMPONENT_TYPE.getValue(Avaritia26.id("infinity_bucket_contents"))
				== ModDataComponents.INFINITY_BUCKET_CONTENTS, "无尽桶流体组件未注册");
		helper.assertTrue(bucket.getMaxStackSize() == 1 && bucket.getRarity() == Rarity.EPIC
				&& bucket.has(DataComponents.DAMAGE_RESISTANT), "无尽桶物品属性错误");

		ExtremeSmithingInput smithingInput = new ExtremeSmithingInput(
				new ItemStack(ModItems.UPGRADE_SMITHING_TEMPLATE),
				new ItemStack(Items.BUCKET),
				new ItemStack(Items.LAVA_BUCKET),
				new ItemStack(ModItems.ENHANCEMENT_CORE),
				new ItemStack(Items.POWDER_SNOW_BUCKET)
		);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("infinity_bucket"));
		RecipeHolder<ExtremeSmithingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_SMITHING, smithingInput, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.orElseThrow(() -> helper.assertionException("正确材料未匹配无尽桶锻造配方"));
		helper.assertTrue(recipe.value().assemble(smithingInput).is(ModItems.INFINITY_BUCKET),
				"无尽桶锻造配方输出错误");

		ServerPlayer player = (ServerPlayer) helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.getInventory().setSelectedSlot(0);
		player.setItemInHand(InteractionHand.MAIN_HAND, bucket);
		ContainerItemContext context = ContainerItemContext.ofPlayerHand(player, InteractionHand.MAIN_HAND);
		Storage<FluidVariant> storage = FluidStorage.ITEM.find(bucket, context);
		helper.assertTrue(storage != null, "无尽桶没有注册 Fabric 流体存储接口");
		try (Transaction transaction = Transaction.openOuter()) {
			helper.assertTrue(storage.insert(FluidVariant.of(Fluids.WATER), FluidConstants.BUCKET, transaction)
					== FluidConstants.BUCKET, "无尽桶流体接口没有接受一桶水");
		}
		helper.assertTrue(InfinityBucketContents.EMPTY.equals(bucket.getOrDefault(
				ModDataComponents.INFINITY_BUCKET_CONTENTS, InfinityBucketContents.EMPTY)),
				"未提交的流体事务仍修改了无尽桶");
		try (Transaction transaction = Transaction.openOuter()) {
			helper.assertTrue(storage.insert(FluidVariant.of(Fluids.WATER), FluidConstants.BUCKET, transaction)
					== FluidConstants.BUCKET, "无尽桶流体接口提交前插入失败");
			transaction.commit();
		}
		try (Transaction transaction = Transaction.openOuter()) {
			helper.assertTrue(storage.insert(FluidVariant.of(Fluids.LAVA), FluidConstants.BUCKET * 2, transaction)
					== FluidConstants.BUCKET * 2, "无尽桶没有保存第二种流体");
			transaction.commit();
		}
		InfinityBucketContents transferred = bucket.get(ModDataComponents.INFINITY_BUCKET_CONTENTS);
		helper.assertTrue(transferred != null && transferred.entries().size() == 2
				&& transferred.selected().fluid().isOf(Fluids.LAVA), "无尽桶没有把最近灌入的流体设为当前流体");
		try (Transaction transaction = Transaction.openOuter()) {
			helper.assertTrue(storage.extract(FluidVariant.of(Fluids.WATER), FluidConstants.BUCKET, transaction) == 0,
					"无尽桶流体接口不应越过当前流体抽取");
			helper.assertTrue(storage.extract(FluidVariant.of(Fluids.LAVA), FluidConstants.BUCKET, transaction)
					== FluidConstants.BUCKET, "无尽桶流体接口没有抽取当前流体");
			transaction.commit();
		}
		helper.assertTrue(bucket.get(ModDataComponents.INFINITY_BUCKET_CONTENTS).selected().amount()
				== FluidConstants.BUCKET, "无尽桶流体接口抽取数量错误");
		ItemStack capacityBucket = new ItemStack(ModItems.INFINITY_BUCKET);
		helper.assertTrue(InfinityBucketItem.fill(
				capacityBucket,
				FluidVariant.of(Fluids.WATER),
				Long.MAX_VALUE
		) == InfinityBucketContents.CAPACITY_PER_FLUID, "无尽桶单种流体容量上限错误");
		helper.assertTrue(InfinityBucketItem.fill(
				capacityBucket,
				FluidVariant.of(Fluids.WATER),
				FluidConstants.BUCKET
		) == 0, "无尽桶超过单种流体容量后仍继续灌入");

		RegistryFriendlyByteBuf buffer = new RegistryFriendlyByteBuf(Unpooled.buffer(), helper.getLevel().registryAccess());
		try {
			ItemStack.STREAM_CODEC.encode(buffer, bucket);
			ItemStack decoded = ItemStack.STREAM_CODEC.decode(buffer);
			helper.assertTrue(bucket.get(ModDataComponents.INFINITY_BUCKET_CONTENTS)
					.equals(decoded.get(ModDataComponents.INFINITY_BUCKET_CONTENTS)),
					"无尽桶多流体数据网络往返后不相等");
		} finally {
			buffer.release();
		}

		ItemStack worldBucket = new ItemStack(ModItems.INFINITY_BUCKET);
		player.setItemInHand(InteractionHand.MAIN_HAND, worldBucket);
		BlockPos waterPos = new BlockPos(8, 1, 12);
		BlockPos lavaPos = new BlockPos(11, 1, 12);
		BlockPos targetPos = new BlockPos(14, 1, 12);
		helper.setBlock(waterPos, Blocks.WATER);
		helper.setBlock(lavaPos, Blocks.LAVA);
		helper.setBlock(targetPos, Blocks.STONE);
		player.setPos(Vec3.atCenterOf(helper.absolutePos(waterPos.north(3))));
		player.setYRot(0.0F);
		player.setXRot(25.0F);
		helper.assertTrue(ModItems.INFINITY_BUCKET.use(helper.getLevel(), player, InteractionHand.MAIN_HAND)
				.consumesAction(), "无尽桶没有吸取水源");
		helper.assertTrue(helper.getLevel().getFluidState(helper.absolutePos(waterPos)).isEmpty()
				&& worldBucket.get(ModDataComponents.INFINITY_BUCKET_CONTENTS).selected().fluid().isOf(Fluids.WATER),
				"无尽桶吸取水源后的世界或内容错误");

		player.setPos(Vec3.atCenterOf(helper.absolutePos(lavaPos.north(3))));
		player.setYRot(0.0F);
		player.setXRot(25.0F);
		ModItems.INFINITY_BUCKET.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(helper.getLevel().getFluidState(helper.absolutePos(lavaPos)).isEmpty()
				&& worldBucket.get(ModDataComponents.INFINITY_BUCKET_CONTENTS).selected().fluid().isOf(Fluids.LAVA),
				"无尽桶没有吸取并选中新流体");

		player.setShiftKeyDown(true);
		ModItems.INFINITY_BUCKET.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(worldBucket.get(ModDataComponents.INFINITY_BUCKET_CONTENTS).selected().fluid().isOf(Fluids.WATER),
				"无尽桶潜行右键没有轮换当前流体");

		player.setPos(Vec3.atCenterOf(helper.absolutePos(targetPos.north(3))));
		player.setYRot(0.0F);
		player.setXRot(25.0F);
		helper.assertTrue(ModItems.INFINITY_BUCKET.use(helper.getLevel(), player, InteractionHand.MAIN_HAND)
				.consumesAction(), "无尽桶没有放置当前流体");
		helper.assertTrue(helper.getLevel().getFluidState(helper.absolutePos(targetPos.north())).is(Fluids.WATER)
				&& worldBucket.get(ModDataComponents.INFINITY_BUCKET_CONTENTS).selected().fluid().isOf(Fluids.LAVA),
				"无尽桶放置水后没有扣除一桶或切换到下一种流体");
		helper.succeed();
	}

	@GameTest
	public void infinityCrossbowCraftsChargesAndFiresEveryAmmoType(GameTestHelper helper) {
		ItemStack crossbow = new ItemStack(ModItems.INFINITY_CROSSBOW);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_CROSSBOW_KEY)
				== ModItems.INFINITY_CROSSBOW, "无尽弩物品未注册");
		helper.assertTrue(BuiltInRegistries.DATA_COMPONENT_TYPE.getValue(Avaritia26.id("infinity_crossbow_multi"))
				== ModDataComponents.INFINITY_CROSSBOW_MULTI, "无尽弩多重射击组件未注册");
		helper.assertTrue(crossbow.getMaxStackSize() == 1 && crossbow.getRarity() == Rarity.EPIC
				&& crossbow.has(DataComponents.DAMAGE_RESISTANT), "无尽弩物品属性错误");
		helper.assertFalse(InfinityCrossbowItem.isMulti(crossbow), "无尽弩默认不应启用多重射击");

		CraftingInput input = infinityCrossbowInput();
		assertExtremeRecipe(helper, "infinity_crossbow", input, ModItems.INFINITY_CROSSBOW);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(4, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "infinity_crossbow", CraftingInput.of(9, 9, wrongStacks));

		List<Component> tooltip = new java.util.ArrayList<>();
		ModItems.INFINITY_CROSSBOW.appendHoverText(
				crossbow, Item.TooltipContext.of(helper.getLevel()), TooltipDisplay.DEFAULT,
				tooltip::add, TooltipFlag.NORMAL
		);
		helper.assertTrue(tooltip.size() == 2, "无尽弩缺少无限等级或射击模式说明");

		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		player.getInventory().setSelectedSlot(0);
		player.setItemInHand(InteractionHand.MAIN_HAND, crossbow);
		player.setPos(Vec3.atCenterOf(helper.absolutePos(new BlockPos(10, 2, 10))));
		assertCrossbowProjectile(helper, player, crossbow, Items.ARROW, Arrow.class, "普通箭");
		assertCrossbowProjectile(helper, player, crossbow, Items.TIPPED_ARROW, Arrow.class, "药箭");
		assertCrossbowProjectile(helper, player, crossbow, Items.SPECTRAL_ARROW, SpectralArrow.class, "光灵箭");
		assertCrossbowProjectile(helper, player, crossbow, Items.ENDER_PEARL, ThrownEnderpearl.class, "末影珍珠");
		assertCrossbowProjectile(helper, player, crossbow, Items.FIRE_CHARGE, SmallFireball.class, "火焰弹");
		assertCrossbowProjectile(helper, player, crossbow, Items.FIREWORK_ROCKET, FireworkRocketEntity.class, "烟花火箭");
		assertCrossbowProjectile(helper, player, crossbow, Items.TRIDENT, ThrownTrident.class, "三叉戟");
		assertCrossbowProjectile(helper, player, crossbow, Items.SNOWBALL, Snowball.class, "雪球");
		assertCrossbowProjectile(helper, player, crossbow, Items.EGG, ThrownEgg.class, "鸡蛋");
		assertCrossbowProjectile(helper, player, crossbow, ModItems.ENDEST_PEARL, EndestPearlEntity.class, "终望珍珠");
		assertCrossbowProjectile(helper, player, crossbow, Items.WIND_CHARGE, WindCharge.class, "风弹");
		assertCrossbowProjectile(helper, player, crossbow, Items.TNT, HeavenArrowEntity.class, "TNT");

		ItemStack enderPearls = new ItemStack(Items.ENDER_PEARL, 16);
		player.setItemInHand(InteractionHand.OFF_HAND, enderPearls);
		helper.assertTrue(ModItems.INFINITY_CROSSBOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND)
				.consumesAction() && player.isUsingItem(), "无尽弩没有开始十刻蓄力");
		ModItems.INFINITY_CROSSBOW.onUseTick(helper.getLevel(), player, crossbow, 1);
		ChargedProjectiles charged = crossbow.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
		helper.assertTrue(!charged.isEmpty() && charged.itemCopies().getFirst().is(Items.ENDER_PEARL),
				"无尽弩没有保存实际装填的末影珍珠");
		helper.assertTrue(enderPearls.getCount() == 16, "无尽弩蓄力不应消耗副手弹药");

		player.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.SNOWBALL, 16));
		ModItems.INFINITY_CROSSBOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertFalse(crossbow.has(DataComponents.CHARGED_PROJECTILES), "无尽弩射击后没有清除装填状态");
		helper.assertTrue(player.getCooldowns().isOnCooldown(crossbow), "无尽弩单发后没有进入冷却");

		player.setShiftKeyDown(true);
		ModItems.INFINITY_CROSSBOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(InfinityCrossbowItem.isMulti(crossbow), "无尽弩潜行右键没有启用多重射击");
		ModItems.INFINITY_CROSSBOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		ModItems.INFINITY_CROSSBOW.onUseTick(helper.getLevel(), player, crossbow, 1);
		List<Projectile> multiProjectiles = InfinityCrossbowItem.createProjectiles(
				helper.getLevel(), player, new ItemStack(Items.SNOWBALL), crossbow);
		helper.assertTrue(multiProjectiles.size() == 5
				&& multiProjectiles.stream().allMatch(Snowball.class::isInstance),
				"无尽弩多重射击应创建五枚不同角度的雪球");
		multiProjectiles.forEach(Projectile::discard);
		ModItems.INFINITY_CROSSBOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(player.getOffhandItem().getCount() == 16, "无尽弩五连发不应消耗副手弹药");

		player.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.DIRT));
		player.setShiftKeyDown(false);
		crossbow.set(ModDataComponents.INFINITY_CROSSBOW_MULTI, false);
		ModItems.INFINITY_CROSSBOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		ModItems.INFINITY_CROSSBOW.onUseTick(helper.getLevel(), player, crossbow, 1);
		ChargedProjectiles defaultCharge = crossbow.getOrDefault(
				DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
		helper.assertTrue(!defaultCharge.isEmpty()
				&& defaultCharge.itemCopies().getFirst().is(ModItems.INFINITY_CATALYST),
				"副手没有支持弹药时无尽弩没有装填天堂箭标记");
		List<Projectile> defaultProjectiles = InfinityCrossbowItem.createProjectiles(
				helper.getLevel(), player, defaultCharge.itemCopies().getFirst(), crossbow);
		helper.assertTrue(defaultProjectiles.size() == 1
				&& defaultProjectiles.getFirst() instanceof HeavenArrowEntity,
				"副手没有支持弹药时无尽弩没有创建天堂箭");
		defaultProjectiles.forEach(Projectile::discard);
		ModItems.INFINITY_CROSSBOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(player.getOffhandItem().is(Items.DIRT), "无尽弩默认天堂箭不应修改副手物品");
		helper.succeed();
	}

	@GameTest
	public void infinityShieldCraftsBlocksForeverAndCannotBeDisabled(GameTestHelper helper) {
		ItemStack shield = new ItemStack(ModItems.INFINITY_SHIELD);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_SHIELD_KEY)
				== ModItems.INFINITY_SHIELD, "无尽盾物品未注册");
		helper.assertTrue(ModItems.INFINITY_SHIELD instanceof ShieldItem, "无尽盾没有使用原版盾牌行为");
		helper.assertTrue(shield.getMaxStackSize() == 1 && shield.getRarity() == Rarity.EPIC
				&& shield.has(DataComponents.DAMAGE_RESISTANT), "无尽盾物品属性错误");
		helper.assertFalse(shield.isDamageableItem(), "无尽盾不应存在可损耗耐久");
		BlocksAttacks blocking = shield.get(DataComponents.BLOCKS_ATTACKS);
		helper.assertTrue(blocking != null, "无尽盾缺少 26.2 格挡组件");
		helper.assertTrue(blocking.blockDelayTicks() == 5, "无尽盾应保留原版五刻起防延迟");
		helper.assertTrue(blocking.disableCooldownScale() == 0.0F, "无尽盾仍可被斧类禁用");

		CraftingInput input = infinityShieldInput();
		assertExtremeRecipe(helper, "infinity_shield", input, ModItems.INFINITY_SHIELD);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(31, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(
				helper,
				"infinity_shield",
				CraftingInput.of(input.width(), input.height(), wrongStacks)
		);

		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.OFF_HAND, shield);
		helper.assertTrue(ModItems.INFINITY_SHIELD.use(helper.getLevel(), player, InteractionHand.OFF_HAND)
				.consumesAction() && player.isUsingItem(), "无尽盾右键没有进入格挡状态");
		blocking.disable(helper.getLevel(), player, 5.0F, shield);
		helper.assertTrue(player.isUsingItem() && !player.getCooldowns().isOnCooldown(shield),
				"斧类禁用仍中断了无尽盾格挡或添加冷却");
		blocking.hurtBlockingItem(helper.getLevel(), shield, player, InteractionHand.OFF_HAND, 1000.0F);
		helper.assertTrue(!shield.isDamageableItem() && shield.getDamageValue() == 0,
				"无尽盾承受高额格挡伤害后产生了耐久损耗");
		helper.succeed();
	}

	@GameTest
	public void infinityTridentCraftsSwitchesModesStrikesAndReturns(GameTestHelper helper) {
		ItemStack trident = new ItemStack(ModItems.INFINITY_TRIDENT);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_TRIDENT_KEY)
				== ModItems.INFINITY_TRIDENT, "无尽三叉戟物品未注册");
		helper.assertTrue(BuiltInRegistries.ENTITY_TYPE.getValue(Avaritia26.id("infinity_thrown_trident"))
				== ModEntityTypes.INFINITY_THROWN_TRIDENT, "无尽三叉戟投掷实体未注册");
		helper.assertTrue(trident.getMaxStackSize() == 1 && trident.getRarity() == Rarity.EPIC
				&& trident.has(DataComponents.DAMAGE_RESISTANT) && !trident.isDamageableItem(),
				"无尽三叉戟物品属性错误");
		var modifiers = trident.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 101.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 104.0,
				"无尽三叉戟没有保留上游百点攻击与攻速增幅");
		List<Component> tooltip = new java.util.ArrayList<>();
		ModItems.INFINITY_TRIDENT.appendHoverText(
				trident, Item.TooltipContext.of(helper.getLevel()), TooltipDisplay.DEFAULT,
				tooltip::add, TooltipFlag.NORMAL
		);
		helper.assertTrue(tooltip.size() == 1, "无尽三叉戟缺少当前模式说明");

		CraftingInput input = infinityTridentInput();
		assertExtremeRecipe(helper, "infinity_trident", input, ModItems.INFINITY_TRIDENT);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(31, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "infinity_trident",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		player.getInventory().setSelectedSlot(0);
		player.setItemInHand(InteractionHand.MAIN_HAND, trident);
		player.setPos(Vec3.atCenterOf(helper.absolutePos(new BlockPos(10, 2, 10))));
		player.setShiftKeyDown(true);
		ModItems.INFINITY_TRIDENT.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(InfinityTridentItem.getMode(trident) == InfinityTridentItem.RIPTIDE,
				"无尽三叉戟没有切换到激流模式");
		ModItems.INFINITY_TRIDENT.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		ModItems.INFINITY_TRIDENT.releaseUsing(trident, helper.getLevel(), player,
				ModItems.INFINITY_TRIDENT.getUseDuration(trident, player) - 10);
		helper.assertTrue(player.getDeltaMovement().length() >= 4.4 && player.getMainHandItem() == trident,
				"无尽三叉戟激流五级推进或物品保留错误");

		player.setShiftKeyDown(true);
		ModItems.INFINITY_TRIDENT.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(InfinityTridentItem.getMode(trident) == InfinityTridentItem.LOYALTY,
				"无尽三叉戟没有切回忠诚模式");
		InfinityThrownTridentEntity projectile = InfinityTridentItem.throwTrident(
				helper.getLevel(), player, trident);
		helper.assertTrue(projectile.getOwner() == player && !projectile.isRemoved()
				&& player.getMainHandItem().isEmpty(),
				"无尽三叉戟忠诚模式没有投出实体或移除手持物品");

		var warden = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(14, 2, 10));
		int lightningBefore = helper.getLevel().getEntitiesOfClass(
				LightningBolt.class, warden.getBoundingBox().inflate(8.0)).size();
		projectile.applyEntityImpact(warden);
		int lightningAfter = helper.getLevel().getEntitiesOfClass(
				LightningBolt.class, warden.getBoundingBox().inflate(8.0)).size();
		helper.assertTrue(warden.isDeadOrDying(), "无尽三叉戟投射物没有造成无限伤害");
		helper.assertTrue(lightningAfter - lightningBefore == 2, "无尽三叉戟命中没有召唤两道闪电");

		projectile.setPos(player.getEyePosition());
		projectile.tick();
		helper.assertTrue(projectile.isRemoved()
				&& player.getInventory().contains(new ItemStack(ModItems.INFINITY_TRIDENT)),
				"无尽三叉戟命中后没有忠诚返还给投掷者");
		helper.succeed();
	}

	@GameTest
	public void infinityMaceCraftsBlastsAndAmplifiesSmashDamage(GameTestHelper helper) {
		ItemStack mace = new ItemStack(ModItems.INFINITY_MACE);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_MACE_KEY)
				== ModItems.INFINITY_MACE, "无尽重锤物品未注册");
		helper.assertTrue(mace.getMaxStackSize() == 1 && mace.getRarity() == Rarity.EPIC
				&& mace.has(DataComponents.DAMAGE_RESISTANT) && !mace.isDamageableItem(),
				"无尽重锤物品属性错误");
		helper.assertFalse(ModItems.INFINITY_MACE.isFoil(mace), "无尽重锤不应显示附魔光效");
		var modifiers = mace.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 101.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 104.0
				&& modifiers.compute(Attributes.ENTITY_INTERACTION_RANGE, 3.0, EquipmentSlot.MAINHAND) == 8.0,
				"无尽重锤百点攻速伤害或五格交互距离错误");
		var enchantments = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.WIND_BURST), mace) == 5, "无尽重锤风爆等级错误");
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.BREACH), mace) == 10, "无尽重锤破甲等级错误");

		CraftingInput input = infinityMaceInput();
		assertExtremeRecipe(helper, "infinity_mace", input, ModItems.INFINITY_MACE);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(14, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "infinity_mace",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, mace);
		player.setPos(Vec3.atCenterOf(helper.absolutePos(new BlockPos(10, 2, 10))));
		List<WindCharge> charges = InfinityMaceItem.createWindCharges(helper.getLevel(), player);
		helper.assertTrue(charges.size() == 3
				&& charges.stream().allMatch(charge -> charge.getDeltaMovement().length() > 1.4),
				"无尽重锤没有创建三枚高速风弹");
		charges.forEach(Projectile::discard);
		ModItems.INFINITY_MACE.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(player.getCooldowns().isOnCooldown(mace), "无尽重锤右键没有进入一秒冷却");

		var warden = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(14, 2, 10));
		player.fallDistance = 5.0F;
		helper.assertTrue(net.minecraft.world.item.MaceItem.canSmashAttack(player), "重锤粉碎测试没有满足坠落条件");
		var source = helper.getLevel().damageSources().playerAttack(player);
		float vanillaBonus = Items.MACE.getAttackDamageBonus(warden, 0.0F, source);
		float infinityBonus = ModItems.INFINITY_MACE.getAttackDamageBonus(warden, 0.0F, source);
		helper.assertTrue(Math.abs(infinityBonus - vanillaBonus - warden.getHealth() * 0.25F) < 0.001F,
				"无尽重锤粉碎攻击没有追加目标当前生命值 25% 的伤害");
		helper.succeed();
	}

	@GameTest
	public void crystalSwordCraftsSwitchesModeAndFiresPiercingSlash(GameTestHelper helper) {
		ItemStack sword = new ItemStack(ModItems.CRYSTAL_SWORD);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.CRYSTAL_SWORD_KEY)
				== ModItems.CRYSTAL_SWORD, "晶态矩阵剑物品未注册");
		helper.assertTrue(BuiltInRegistries.ENTITY_TYPE.getValue(Avaritia26.id("blade_slash"))
				== ModEntityTypes.BLADE_SLASH, "晶态矩阵剑剑气实体未注册");
		helper.assertTrue(sword.getMaxStackSize() == 1 && sword.getRarity() == Rarity.EPIC
				&& sword.has(DataComponents.DAMAGE_RESISTANT) && sword.getMaxDamage() == 8888,
				"晶态矩阵剑物品属性或耐久错误");
		helper.assertTrue(sword.is(ItemTags.SWORDS), "晶态矩阵剑缺少原版剑标签");
		helper.assertFalse(ModItems.CRYSTAL_SWORD.isFoil(sword), "晶态矩阵剑不应显示附魔光效");
		var modifiers = sword.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 51.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 29.0,
				"晶态矩阵剑没有保留上游五十点伤害与二十五点攻速增幅");
		Repairable repairable = sword.get(DataComponents.REPAIRABLE);
		helper.assertTrue(repairable != null
				&& repairable.isValidRepairItem(new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT)),
				"晶态矩阵剑不能用晶态矩阵锭修复");

		CraftingInput input = crystalSwordInput();
		assertExtremeRecipe(helper, "crystal_sword", input, ModItems.CRYSTAL_SWORD);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(36, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "crystal_sword",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setPos(Vec3.atCenterOf(helper.absolutePos(new BlockPos(10, 2, 10))));
		player.setItemInHand(InteractionHand.MAIN_HAND, sword);
		player.setShiftKeyDown(true);
		ModItems.CRYSTAL_SWORD.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(CrystalSwordItem.isBladeSlashEnabled(sword), "晶态矩阵剑没有开启剑气模式");
		ModItems.CRYSTAL_SWORD.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		List<BladeSlashEntity> slashes = helper.getLevel().getEntitiesOfClass(
				BladeSlashEntity.class, player.getBoundingBox().inflate(8.0));
		helper.assertTrue(slashes.size() == 1
				&& Math.abs(slashes.getFirst().getDeltaMovement().length() - BladeSlashEntity.SPEED) < 0.2,
				"晶态矩阵剑没有以预期速度发射剑气");

		var warden = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(14, 2, 10));
		float healthBefore = warden.getHealth();
		slashes.getFirst().applyImpact(helper.getLevel(), warden);
		helper.assertTrue(Math.abs(warden.getHealth() - (healthBefore - BladeSlashEntity.DAMAGE)) < 0.001F,
				"晶态矩阵剑剑气没有造成两百点伤害");
		warden.setInvulnerable(true);
		InteractionResult attackResult = AttackEntityCallback.EVENT.invoker().interact(
				player, helper.getLevel(), InteractionHand.MAIN_HAND, warden, new EntityHitResult(warden));
		helper.assertFalse(attackResult.consumesAction(), "晶态矩阵剑近战攻击不应接管原版伤害流程");
		helper.assertFalse(warden.isInvulnerable(), "晶态矩阵剑近战没有解除目标无敌状态");

		player.setShiftKeyDown(true);
		ModItems.CRYSTAL_SWORD.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertFalse(CrystalSwordItem.isBladeSlashEnabled(sword), "晶态矩阵剑没有关闭剑气模式");
		helper.succeed();
	}

	@GameTest
	public void crystalHoeCraftsTillsThreeByThreeAndGrowsCrops(GameTestHelper helper) {
		ItemStack hoe = new ItemStack(ModItems.CRYSTAL_HOE);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.CRYSTAL_HOE_KEY)
				== ModItems.CRYSTAL_HOE, "晶态矩阵锄物品未注册");
		helper.assertTrue(hoe.getMaxStackSize() == 1 && hoe.getRarity() == Rarity.EPIC
				&& hoe.has(DataComponents.DAMAGE_RESISTANT) && hoe.getMaxDamage() == 8888,
				"晶态矩阵锄物品属性或耐久错误");
		helper.assertTrue(hoe.is(ItemTags.HOES), "晶态矩阵锄缺少原版锄标签");
		helper.assertFalse(ModItems.CRYSTAL_HOE.isFoil(hoe), "晶态矩阵锄不应显示附魔光效");
		var modifiers = hoe.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 51.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 29.0,
				"晶态矩阵锄没有保留上游五十点伤害与二十五点攻速增幅");
		helper.assertTrue(ModItems.CRYSTAL_HOE.getDestroySpeed(hoe, Blocks.HAY_BLOCK.defaultBlockState()) == 50.0F,
				"晶态矩阵锄挖掘速度错误");
		Repairable repairable = hoe.get(DataComponents.REPAIRABLE);
		helper.assertTrue(repairable != null
				&& repairable.isValidRepairItem(new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT)),
				"晶态矩阵锄不能用晶态矩阵锭修复");

		CraftingInput input = crystalHoeInput();
		assertExtremeRecipe(helper, "crystal_hoe", input, ModItems.CRYSTAL_HOE);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(24, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "crystal_hoe",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, hoe);
		BlockPos center = new BlockPos(5, 1, 5);
		for (BlockPos pos : BlockPos.betweenClosed(center.offset(-1, 0, -1), center.offset(1, 0, 1))) {
			helper.setBlock(pos, Blocks.DIRT);
		}
		InteractionResult tillResult = ModItems.CRYSTAL_HOE.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(helper.absolutePos(center)), Direction.UP,
						helper.absolutePos(center), false)
		));
		helper.assertTrue(tillResult.consumesAction(), "晶态矩阵锄没有响应耕地使用");
		for (BlockPos pos : BlockPos.betweenClosed(center.offset(-1, 0, -1), center.offset(1, 0, 1))) {
			BlockState farmland = helper.getBlockState(pos);
			helper.assertTrue(farmland.is(Blocks.FARMLAND)
					&& farmland.getValue(FarmlandBlock.MOISTURE) == FarmlandBlock.MAX_MOISTURE,
					"晶态矩阵锄没有生成 3×3 满湿度耕地");
		}

		BlockPos cropPos = new BlockPos(10, 2, 5);
		helper.setBlock(cropPos.below(), Blocks.FARMLAND);
		helper.setBlock(cropPos, Blocks.WHEAT);
		int ageBefore = helper.getBlockState(cropPos).getValue(CropBlock.AGE);
		InteractionResult growResult = ModItems.CRYSTAL_HOE.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(helper.absolutePos(cropPos)), Direction.UP,
						helper.absolutePos(cropPos), false)
		));
		int ageAfter = helper.getBlockState(cropPos).getValue(CropBlock.AGE);
		helper.assertTrue(growResult.consumesAction() && ageAfter > ageBefore,
				"晶态矩阵锄没有直接催熟可骨粉作物");
		helper.assertTrue(hoe.getDamageValue() == 0, "晶态矩阵锄范围耕作或催熟不应额外损耗耐久");
		helper.succeed();
	}

	@GameTest
	public void crystalPickaxeCraftsTogglesSilkAndBreaksUnbreakableBlocks(GameTestHelper helper) {
		ItemStack pickaxe = new ItemStack(ModItems.CRYSTAL_PICKAXE);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.CRYSTAL_PICKAXE_KEY)
				== ModItems.CRYSTAL_PICKAXE, "晶态矩阵镐物品未注册");
		helper.assertTrue(pickaxe.getMaxStackSize() == 1 && pickaxe.getRarity() == Rarity.EPIC
				&& pickaxe.has(DataComponents.DAMAGE_RESISTANT) && pickaxe.getMaxDamage() == 8888,
				"晶态矩阵镐物品属性或耐久错误");
		helper.assertTrue(pickaxe.is(ItemTags.PICKAXES), "晶态矩阵镐缺少原版镐标签");
		helper.assertFalse(ModItems.CRYSTAL_PICKAXE.isFoil(pickaxe), "晶态矩阵镐不应显示附魔光效");
		helper.assertTrue(ModItems.CRYSTAL_PICKAXE.getDestroySpeed(
				pickaxe, Blocks.OBSIDIAN.defaultBlockState()) == 100.0F, "晶态矩阵镐挖掘速度错误");
		var modifiers = pickaxe.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 51.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 29.0,
				"晶态矩阵镐没有保留上游五十点伤害与二十五点攻速增幅");

		var enchantments = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		var fortune = enchantments.getOrThrow(Enchantments.FORTUNE);
		var silkTouch = enchantments.getOrThrow(Enchantments.SILK_TOUCH);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(fortune, pickaxe) == 3
				&& EnchantmentHelper.getItemEnchantmentLevel(silkTouch, pickaxe) == 0,
				"晶态矩阵镐默认时运或精准采集状态错误");

		CraftingInput input = crystalPickaxeInput();
		assertExtremeRecipe(helper, "crystal_pickaxe", input, ModItems.CRYSTAL_PICKAXE);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(6, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "crystal_pickaxe",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, pickaxe);
		player.setShiftKeyDown(true);
		ModItems.CRYSTAL_PICKAXE.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(silkTouch, pickaxe) == 1
				&& EnchantmentHelper.getItemEnchantmentLevel(fortune, pickaxe) == 3,
				"晶态矩阵镐没有开启精准采集或错误移除时运");
		ModItems.CRYSTAL_PICKAXE.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(silkTouch, pickaxe) == 0
				&& EnchantmentHelper.getItemEnchantmentLevel(fortune, pickaxe) == 3,
				"晶态矩阵镐没有关闭精准采集或错误移除时运");

		BlockPos bedrockPos = new BlockPos(6, 2, 6);
		helper.setBlock(bedrockPos, Blocks.BEDROCK);
		BlockPos absoluteBedrock = helper.absolutePos(bedrockPos);
		InteractionResult breakResult = AttackBlockCallback.EVENT.invoker().interact(
				player, helper.getLevel(), InteractionHand.MAIN_HAND, absoluteBedrock, Direction.UP);
		helper.assertTrue(breakResult.consumesAction(), "晶态矩阵镐没有接管基岩破坏");
		helper.assertBlockPresent(Blocks.AIR, bedrockPos);
		helper.assertTrue(pickaxe.getDamageValue() == 1, "晶态矩阵镐破坏不可破坏方块没有损耗一点耐久");
		helper.runAfterDelay(1, () -> {
			int bedrockDrops = helper.getLevel().getEntitiesOfClass(
					ItemEntity.class, new AABB(absoluteBedrock).inflate(2.0),
					entity -> entity.getItem().is(Items.BEDROCK)
			).stream().mapToInt(entity -> entity.getItem().getCount()).sum();
			helper.assertTrue(bedrockDrops == 1, "晶态矩阵镐破坏基岩没有掉落基岩物品");
			helper.succeed();
		});
	}

	@GameTest
	public void crystalShovelCraftsFlattensAndGrantsSpeed(GameTestHelper helper) {
		ItemStack shovel = new ItemStack(ModItems.CRYSTAL_SHOVEL);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.CRYSTAL_SHOVEL_KEY)
				== ModItems.CRYSTAL_SHOVEL, "晶态矩阵铲物品未注册");
		helper.assertTrue(shovel.getMaxStackSize() == 1 && shovel.getRarity() == Rarity.EPIC
				&& shovel.has(DataComponents.DAMAGE_RESISTANT) && shovel.getMaxDamage() == 8888,
				"晶态矩阵铲物品属性或耐久错误");
		helper.assertTrue(shovel.is(ItemTags.SHOVELS), "晶态矩阵铲缺少原版铲标签");
		helper.assertFalse(ModItems.CRYSTAL_SHOVEL.isFoil(shovel), "晶态矩阵铲不应显示附魔光效");
		var modifiers = shovel.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 51.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 29.0,
				"晶态矩阵铲没有保留上游五十点伤害与二十五点攻速增幅");

		CraftingInput input = crystalShovelInput();
		assertExtremeRecipe(helper, "crystal_shovel", input, ModItems.CRYSTAL_SHOVEL);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(0, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "crystal_shovel",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, shovel);
		player.getActiveEffectsMap().put(MobEffects.SLOWNESS,
				new MobEffectInstance(MobEffects.SLOWNESS, 200, 1));
		player.getActiveEffectsMap().put(MobEffects.MINING_FATIGUE,
				new MobEffectInstance(MobEffects.MINING_FATIGUE, 200, 1));
		ModItems.CRYSTAL_SHOVEL.inventoryTick(shovel, helper.getLevel(), player, EquipmentSlot.MAINHAND);
		helper.assertTrue(player.getEffect(MobEffects.HASTE) != null
				&& player.getEffect(MobEffects.HASTE).getAmplifier() == 2
				&& player.getEffect(MobEffects.SPEED) != null
				&& player.getEffect(MobEffects.SPEED).getAmplifier() == 2,
				"晶态矩阵铲主手时没有提供急迫 III 与速度 III");
		helper.assertFalse(player.hasEffect(MobEffects.SLOWNESS)
				|| player.hasEffect(MobEffects.MINING_FATIGUE),
				"晶态矩阵铲没有清除缓慢或挖掘疲劳");

		BlockPos pathPos = new BlockPos(6, 2, 6);
		helper.setBlock(pathPos, Blocks.GRASS_BLOCK);
		InteractionResult flattenResult = ModItems.CRYSTAL_SHOVEL.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(helper.absolutePos(pathPos)), Direction.UP,
						helper.absolutePos(pathPos), false)
		));
		helper.assertTrue(flattenResult.consumesAction(), "晶态矩阵铲没有接管铲平操作");
		helper.assertBlockPresent(Blocks.DIRT_PATH, pathPos);
		helper.assertTrue(shovel.getDamageValue() == 1, "晶态矩阵铲铲平道路没有损耗一点耐久");
		helper.succeed();
	}

	@GameTest
	public void crystalAxeCraftsStripsShieldsAndDealsJumpDamage(GameTestHelper helper) {
		ItemStack axe = new ItemStack(ModItems.CRYSTAL_AXE);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.CRYSTAL_AXE_KEY)
				== ModItems.CRYSTAL_AXE, "晶态矩阵斧物品未注册");
		helper.assertTrue(axe.getMaxStackSize() == 1 && axe.getRarity() == Rarity.EPIC
				&& axe.has(DataComponents.DAMAGE_RESISTANT) && axe.getMaxDamage() == 8888,
				"晶态矩阵斧物品属性或耐久错误");
		helper.assertTrue(axe.is(ItemTags.AXES), "晶态矩阵斧缺少原版斧标签");
		helper.assertFalse(ModItems.CRYSTAL_AXE.isFoil(axe), "晶态矩阵斧不应显示附魔光效");
		var modifiers = axe.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 51.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 29.0,
				"晶态矩阵斧没有保留上游五十点伤害与二十五点攻速增幅");

		CraftingInput input = crystalAxeInput();
		assertExtremeRecipe(helper, "crystal_axe", input, ModItems.CRYSTAL_AXE);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(12, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "crystal_axe",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player attacker = helper.makeMockServerPlayer(GameType.SURVIVAL);
		attacker.setItemInHand(InteractionHand.MAIN_HAND, axe);
		Player defender = helper.makeMockServerPlayer(GameType.SURVIVAL);
		ItemStack shield = new ItemStack(Items.SHIELD);
		defender.setItemInHand(InteractionHand.OFF_HAND, shield);
		defender.startUsingItem(InteractionHand.OFF_HAND);
		AttackEntityCallback.EVENT.invoker().interact(
				attacker, helper.getLevel(), InteractionHand.MAIN_HAND, defender, new EntityHitResult(defender));
		helper.assertFalse(defender.isUsingItem(), "晶态矩阵斧没有停止盾牌格挡");
		helper.assertTrue(shield.getDamageValue() >= shield.getMaxDamage() / 2,
				"晶态矩阵斧没有削减盾牌至少一半耐久");

		var warden = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(7, 2, 7));
		attacker.setOnGround(false);
		attacker.setDeltaMovement(0.0, -0.2, 0.0);
		float healthBeforeJump = warden.getHealth();
		ServerLivingEntityEvents.AFTER_DAMAGE.invoker().afterDamage(
				warden, attacker.damageSources().playerAttack(attacker), 1.0F, 1.0F, false);
		helper.assertTrue(warden.getHealth() <= healthBeforeJump - 54.0F,
				"晶态矩阵斧下落跳劈没有追加五十四点虚空伤害");
		attacker.setOnGround(true);
		float healthBeforeGrounded = warden.getHealth();
		ServerLivingEntityEvents.AFTER_DAMAGE.invoker().afterDamage(
				warden, attacker.damageSources().playerAttack(attacker), 1.0F, 1.0F, false);
		helper.assertTrue(warden.getHealth() == healthBeforeGrounded,
				"晶态矩阵斧在地面攻击时错误追加跳劈伤害");

		BlockPos logPos = new BlockPos(10, 2, 7);
		helper.setBlock(logPos, Blocks.OAK_LOG);
		InteractionResult stripResult = ModItems.CRYSTAL_AXE.useOn(new UseOnContext(
				attacker,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(helper.absolutePos(logPos)), Direction.UP,
						helper.absolutePos(logPos), false)
		));
		helper.assertTrue(stripResult.consumesAction(), "晶态矩阵斧没有接管原木去皮操作");
		helper.assertBlockPresent(Blocks.STRIPPED_OAK_LOG, logPos);
		helper.assertTrue(axe.getDamageValue() == 1, "晶态矩阵斧原木去皮没有损耗一点耐久");
		helper.succeed();
	}

	@GameTest
	public void crystalBowCraftsSwitchesModesAndFiresBothProjectiles(GameTestHelper helper) {
		ItemStack bow = new ItemStack(ModItems.CRYSTAL_BOW);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.CRYSTAL_BOW_KEY)
				== ModItems.CRYSTAL_BOW, "晶态矩阵弓物品未注册");
		helper.assertTrue(bow.getMaxStackSize() == 1 && bow.getRarity() == Rarity.EPIC
				&& bow.has(DataComponents.DAMAGE_RESISTANT) && bow.getMaxDamage() == 8888,
				"晶态矩阵弓物品属性或耐久错误");
		helper.assertTrue(bow.is(ItemTags.BOW_ENCHANTABLE), "晶态矩阵弓缺少弓附魔标签");
		helper.assertFalse(ModItems.CRYSTAL_BOW.isFoil(bow), "晶态矩阵弓不应显示附魔光效");
		helper.assertTrue(ModItems.CRYSTAL_BOW.getUseAnimation(bow) == ItemUseAnimation.BOW,
				"晶态矩阵弓没有使用拉弓动画");

		var enchantments = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		var infinity = enchantments.getOrThrow(Enchantments.INFINITY);
		var multishot = enchantments.getOrThrow(Enchantments.MULTISHOT);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(infinity, bow) == 1
				&& EnchantmentHelper.getItemEnchantmentLevel(multishot, bow) == 1,
				"晶态矩阵弓没有自带无限 I 与多重射击 I");

		CraftingInput input = crystalBowInput();
		assertExtremeRecipe(helper, "crystal_bow", input, ModItems.CRYSTAL_BOW);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(2, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "crystal_bow",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, bow);
		helper.assertFalse(CrystalBowItem.isBladeSlashEnabled(bow), "晶态矩阵弓默认弹道不应是剑气");
		NeutronArrowEntity neutronArrow = (NeutronArrowEntity) ModItems.CRYSTAL_BOW.createProjectile(
				helper.getLevel(), player, bow, 1.0F);
		helper.assertTrue(neutronArrow.getOwner() == player
				&& Math.abs(neutronArrow.getDeltaMovement().length() - 3.0) < 0.001,
				"晶态矩阵弓中子箭的归属或速度错误");
		var warden = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(7, 2, 7));
		float healthBeforeArrow = warden.getHealth();
		neutronArrow.applyImpact(helper.getLevel(), warden);
		helper.assertTrue(neutronArrow.isRemoved() && warden.getHealth() <= healthBeforeArrow - 32.0F,
				"晶态矩阵弓中子箭没有造成三十二点虚空伤害或命中后移除");

		player.setShiftKeyDown(true);
		ModItems.CRYSTAL_BOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(CrystalBowItem.isBladeSlashEnabled(bow), "晶态矩阵弓没有切换到剑气模式");
		BladeSlashEntity slash = (BladeSlashEntity) ModItems.CRYSTAL_BOW.createProjectile(
				helper.getLevel(), player, bow, 1.0F);
		helper.assertTrue(Math.abs(slash.getDeltaMovement().length() - 6.0) < 0.001
				&& slash.getDamage() == 205.0F && slash.getLifetime() == 30,
				"晶态矩阵弓满蓄力剑气参数错误：速度=" + slash.getDeltaMovement().length()
						+ "，伤害=" + slash.getDamage() + "，寿命=" + slash.getLifetime());
		var slashTarget = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(10, 2, 7));
		float healthBeforeSlash = slashTarget.getHealth();
		slash.applyImpact(helper.getLevel(), slashTarget);
		helper.assertTrue(slashTarget.getHealth() <= healthBeforeSlash - 205.0F,
				"晶态矩阵弓剑气没有造成蓄力增强伤害");

		ModItems.CRYSTAL_BOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		int useDuration = ModItems.CRYSTAL_BOW.getUseDuration(bow, player);
		helper.assertTrue(ModItems.CRYSTAL_BOW.releaseUsing(bow, helper.getLevel(), player, useDuration - 20),
				"晶态矩阵弓满蓄力没有成功发射");
		helper.assertTrue(bow.getDamageValue() == 1, "晶态矩阵弓发射没有损耗一点耐久");
		helper.succeed();
	}

	@GameTest
	public void blazeSwordCraftsSwitchesModeAndTransformsWithFireballs(GameTestHelper helper) {
		ItemStack sword = new ItemStack(ModItems.BLAZE_SWORD);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.BLAZE_SWORD_KEY)
				== ModItems.BLAZE_SWORD, "烈焰剑物品未注册");
		helper.assertTrue(sword.getMaxStackSize() == 1 && sword.getRarity() == Rarity.EPIC
				&& sword.has(DataComponents.DAMAGE_RESISTANT) && sword.getMaxDamage() == 7777,
				"烈焰剑物品属性或耐久错误");
		helper.assertTrue(sword.is(ItemTags.SWORDS), "烈焰剑缺少原版剑标签");
		helper.assertFalse(ModItems.BLAZE_SWORD.isFoil(sword), "烈焰剑不应显示附魔光效");
		var modifiers = sword.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 26.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 29.0,
				"烈焰剑没有保留上游二十五点伤害与攻速增幅");
		var enchantments = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		var fireAspect = enchantments.getOrThrow(Enchantments.FIRE_ASPECT);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(fireAspect, sword) == 10,
				"烈焰剑没有自带火焰附加 X");

		CraftingInput input = blazeSwordInput();
		assertExtremeRecipe(helper, "blaze_sword", input, ModItems.BLAZE_SWORD);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(4, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "blaze_sword",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, sword);
		player.setShiftKeyDown(true);
		ModItems.BLAZE_SWORD.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(BlazeSwordItem.isFireballEnabled(sword), "烈焰剑没有开启火球模式");

		BlazeFireballEntity entityFireball = ModItems.BLAZE_SWORD.createFireball(helper.getLevel(), player);
		var ravager = helper.spawnWithNoFreeWill(EntityTypes.RAVAGER, new BlockPos(7, 2, 7));
		float healthBefore = ravager.getHealth();
		entityFireball.applyEntityImpact(helper.getLevel(), ravager);
		helper.assertTrue(BlazeFireballEntity.DAMAGE == 50.0F
				&& ravager.getRemainingFireTicks() >= 100 && ravager.getHealth() < healthBefore,
				"烈焰剑火球命中状态错误：原始伤害=" + BlazeFireballEntity.DAMAGE
						+ "，燃烧刻=" + ravager.getRemainingFireTicks()
						+ "，生命=" + healthBefore + "→" + ravager.getHealth());
		helper.assertTrue(entityFireball.ignoreExplosion(null), "烈焰剑火球没有忽略爆炸");

		ravager.setInvulnerable(true);
		InteractionResult attackResult = AttackEntityCallback.EVENT.invoker().interact(
				player, helper.getLevel(), InteractionHand.MAIN_HAND, ravager, new EntityHitResult(ravager));
		helper.assertFalse(attackResult.consumesAction(), "烈焰剑近战不应接管原版攻击流程");
		helper.assertFalse(ravager.isInvulnerable(), "烈焰剑近战没有解除目标无敌状态");

		BlockPos skeletonPos = new BlockPos(7, 2, 12);
		var skeleton = helper.spawnWithNoFreeWill(EntityTypes.SKELETON, skeletonPos);
		skeleton.hurtServer(helper.getLevel(), player.damageSources().playerAttack(player), 100.0F);
		List<ItemEntity> skulls = skullDropsNear(helper, skeletonPos);
		helper.assertTrue(skulls.size() == 1 && skulls.getFirst().getItem().is(Items.WITHER_SKELETON_SKULL),
				"烈焰剑击杀骷髅没有保证掉落凋灵骷髅头");

		BlockPos obsidianPos = helper.absolutePos(new BlockPos(10, 2, 7));
		helper.getLevel().setBlockAndUpdate(obsidianPos, Blocks.OBSIDIAN.defaultBlockState());
		ModItems.BLAZE_SWORD.createFireball(helper.getLevel(), player)
				.applyBlockImpact(helper.getLevel(), obsidianPos);
		helper.assertTrue(helper.getLevel().getBlockState(obsidianPos).is(Blocks.LAVA),
				"烈焰剑火球没有把黑曜石熔成熔岩");

		BlockPos sandPos = helper.absolutePos(new BlockPos(13, 2, 7));
		helper.getLevel().setBlockAndUpdate(sandPos, Blocks.SAND.defaultBlockState());
		helper.getLevel().setBlockAndUpdate(sandPos.east(), Blocks.SAND.defaultBlockState());
		ModItems.BLAZE_SWORD.createFireball(helper.getLevel(), player)
				.applyBlockImpact(helper.getLevel(), sandPos);
		helper.assertTrue(helper.getLevel().getBlockState(sandPos).is(Blocks.GLASS)
				&& helper.getLevel().getBlockState(sandPos.east()).is(Blocks.GLASS),
				"烈焰剑火球没有把范围沙子烧成玻璃");
		helper.succeed();
	}

	@GameTest
	public void blazeHoeCraftsTillsSoulSoilAndSmeltsDrops(GameTestHelper helper) {
		ItemStack hoe = new ItemStack(ModItems.BLAZE_HOE);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.BLAZE_HOE_KEY)
				== ModItems.BLAZE_HOE, "烈焰锄物品未注册");
		helper.assertTrue(hoe.getMaxStackSize() == 1 && hoe.getRarity() == Rarity.EPIC
				&& hoe.has(DataComponents.DAMAGE_RESISTANT) && hoe.getMaxDamage() == 7777,
				"烈焰锄物品属性或耐久错误");
		helper.assertTrue(hoe.is(ItemTags.HOES), "烈焰锄缺少原版锄标签");
		helper.assertFalse(ModItems.BLAZE_HOE.isFoil(hoe), "烈焰锄不应显示附魔光效");
		var modifiers = hoe.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 26.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 29.0,
				"烈焰锄没有保留上游二十五点伤害与攻速增幅");
		var enchantments = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.FIRE_ASPECT), hoe) == 10,
				"烈焰锄没有自带火焰附加 X");

		CraftingInput input = blazeHoeInput();
		assertExtremeRecipe(helper, "blaze_hoe", input, ModItems.BLAZE_HOE);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(2, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "blaze_hoe",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, hoe);
		BlockPos dirtPos = new BlockPos(7, 2, 7);
		helper.setBlock(dirtPos, Blocks.DIRT);
		BlockPos absoluteDirtPos = helper.absolutePos(dirtPos);
		InteractionResult tillResult = ModItems.BLAZE_HOE.useOn(new UseOnContext(
				player, InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteDirtPos), Direction.UP, absoluteDirtPos, false)));
		helper.assertTrue(tillResult.consumesAction() && helper.getBlockState(dirtPos).is(Blocks.FARMLAND),
				"烈焰锄关闭熔炼模式时没有保留原版耕作能力");

		player.setShiftKeyDown(true);
		ModItems.BLAZE_HOE.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(BlazeHoeItem.isSmeltingEnabled(hoe), "烈焰锄没有开启熔炼模式");
		BlockPos soulPos = new BlockPos(10, 2, 7);
		helper.setBlock(soulPos, Blocks.SOUL_SAND);
		BlockPos absoluteSoulPos = helper.absolutePos(soulPos);
		UseOnContext soulContext = new UseOnContext(player, InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteSoulPos), Direction.UP, absoluteSoulPos, false));
		ModItems.BLAZE_HOE.useOn(soulContext);
		helper.assertTrue(helper.getBlockState(soulPos).is(Blocks.SOUL_SOIL),
				"烈焰锄没有把灵魂沙转化为灵魂土");
		ModItems.BLAZE_HOE.useOn(soulContext);
		helper.assertTrue(helper.getBlockState(soulPos).is(ModBlocks.SOUL_FARMLAND),
				"烈焰锄没有把灵魂土耕成灵魂耕地");

		BlockPos orePos = new BlockPos(13, 2, 7);
		helper.setBlock(orePos, Blocks.IRON_ORE);
		List<ItemStack> oreDrops = Block.getDrops(helper.getBlockState(orePos), helper.getLevel(),
				helper.absolutePos(orePos), null, player, hoe);
		helper.assertTrue(oreDrops.size() == 1 && oreDrops.getFirst().is(Items.IRON_INGOT),
				"烈焰锄熔炼模式没有把铁矿掉落物自动熔炼成铁锭：" + oreDrops);

		BlockPos cropPos = new BlockPos(16, 2, 7);
		helper.setBlock(cropPos, Blocks.POTATOES.defaultBlockState().setValue(CropBlock.AGE, CropBlock.MAX_AGE));
		List<ItemStack> cropDrops = Block.getDrops(helper.getBlockState(cropPos), helper.getLevel(),
				helper.absolutePos(cropPos), null, player, hoe);
		helper.assertFalse(cropDrops.stream().anyMatch(stack -> stack.is(Items.BAKED_POTATO)),
				"烈焰锄不应自动熔炼农作物掉落");
		helper.succeed();
	}

	@GameTest
	public void blazePickaxeCraftsMinesAndSmeltsOreDrops(GameTestHelper helper) {
		ItemStack pickaxe = new ItemStack(ModItems.BLAZE_PICKAXE);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.BLAZE_PICKAXE_KEY)
				== ModItems.BLAZE_PICKAXE, "烈焰镐物品未注册");
		helper.assertTrue(pickaxe.getMaxStackSize() == 1 && pickaxe.getRarity() == Rarity.EPIC
				&& pickaxe.has(DataComponents.DAMAGE_RESISTANT) && pickaxe.getMaxDamage() == 7777,
				"烈焰镐物品属性或耐久错误");
		helper.assertTrue(pickaxe.is(ItemTags.PICKAXES), "烈焰镐缺少原版镐标签");
		helper.assertTrue(pickaxe.isCorrectToolForDrops(Blocks.DIAMOND_ORE.defaultBlockState()),
				"烈焰镐不能正确开采钻石矿");
		helper.assertFalse(ModItems.BLAZE_PICKAXE.isFoil(pickaxe), "烈焰镐不应显示附魔光效");
		var modifiers = pickaxe.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 26.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 29.0,
				"烈焰镐没有保留上游二十五点伤害与攻速增幅");
		var enchantments = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.FIRE_ASPECT), pickaxe) == 10,
				"烈焰镐没有自带火焰附加 X");

		CraftingInput input = blazePickaxeInput();
		assertExtremeRecipe(helper, "blaze_pickaxe", input, ModItems.BLAZE_PICKAXE);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(1, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "blaze_pickaxe",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, pickaxe);
		BlockPos orePos = new BlockPos(7, 2, 7);
		helper.setBlock(orePos, Blocks.GOLD_ORE);
		List<ItemStack> normalDrops = Block.getDrops(helper.getBlockState(orePos), helper.getLevel(),
				helper.absolutePos(orePos), null, player, pickaxe);
		helper.assertTrue(normalDrops.stream().anyMatch(stack -> stack.is(Items.RAW_GOLD))
				&& normalDrops.stream().noneMatch(stack -> stack.is(Items.GOLD_INGOT)),
				"烈焰镐关闭熔炼模式时不应改变原矿掉落：" + normalDrops);

		player.setShiftKeyDown(true);
		ModItems.BLAZE_PICKAXE.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(BlazePickaxeItem.isSmeltingEnabled(pickaxe), "烈焰镐没有开启熔炼模式");
		List<ItemStack> smeltedDrops = Block.getDrops(helper.getBlockState(orePos), helper.getLevel(),
				helper.absolutePos(orePos), null, player, pickaxe);
		helper.assertTrue(smeltedDrops.size() == 1 && smeltedDrops.getFirst().is(Items.GOLD_INGOT),
				"烈焰镐熔炼模式没有把金矿掉落物自动熔炼成金锭：" + smeltedDrops);
		helper.succeed();
	}

	@GameTest
	public void blazeShovelCraftsFlattensAndTransformsEveryMappedBlock(GameTestHelper helper) {
		ItemStack shovel = new ItemStack(ModItems.BLAZE_SHOVEL);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.BLAZE_SHOVEL_KEY)
				== ModItems.BLAZE_SHOVEL, "烈焰铲物品未注册");
		helper.assertTrue(shovel.getMaxStackSize() == 1 && shovel.getRarity() == Rarity.EPIC
				&& shovel.has(DataComponents.DAMAGE_RESISTANT) && shovel.getMaxDamage() == 7777,
				"烈焰铲物品属性或耐久错误");
		helper.assertTrue(shovel.is(ItemTags.SHOVELS), "烈焰铲缺少原版铲标签");
		helper.assertFalse(ModItems.BLAZE_SHOVEL.isFoil(shovel), "烈焰铲不应显示附魔光效");
		var modifiers = shovel.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 26.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 29.0,
				"烈焰铲没有保留上游二十五点伤害与攻速增幅");
		var enchantments = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.FIRE_ASPECT), shovel) == 10,
				"烈焰铲没有自带火焰附加 X");

		CraftingInput input = blazeShovelInput();
		assertExtremeRecipe(helper, "blaze_shovel", input, ModItems.BLAZE_SHOVEL);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(4, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "blaze_shovel",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, shovel);
		BlockPos dirtPos = new BlockPos(4, 2, 4);
		helper.setBlock(dirtPos, Blocks.DIRT);
		BlockPos absoluteDirtPos = helper.absolutePos(dirtPos);
		InteractionResult flattenResult = ModItems.BLAZE_SHOVEL.useOn(new UseOnContext(
				player, InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteDirtPos), Direction.UP, absoluteDirtPos, false)));
		helper.assertTrue(flattenResult.consumesAction() && helper.getBlockState(dirtPos).is(Blocks.DIRT_PATH),
				"烈焰铲关闭转化模式时没有保留原版铲平能力");

		player.setShiftKeyDown(true);
		ModItems.BLAZE_SHOVEL.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(BlazeShovelItem.isTransformationEnabled(shovel), "烈焰铲没有开启转化模式");
		helper.assertTrue(BlazeShovelItem.TRANSFORMATIONS.size() == 17, "烈焰铲转化表条目数量错误");
		int index = 0;
		for (var entry : BlazeShovelItem.TRANSFORMATIONS.entrySet()) {
			BlockPos pos = new BlockPos(5 + index, 2, 8);
			Block support = entry.getValue() == Blocks.CRIMSON_FUNGUS
					? Blocks.CRIMSON_NYLIUM
					: entry.getValue() == Blocks.WARPED_FUNGUS ? Blocks.WARPED_NYLIUM : Blocks.STONE;
			helper.setBlock(pos.below(), support);
			helper.setBlock(pos, entry.getKey());
			BlockPos absolutePos = helper.absolutePos(pos);
			InteractionResult result = ModItems.BLAZE_SHOVEL.useOn(new UseOnContext(
					player, InteractionHand.MAIN_HAND,
					new BlockHitResult(Vec3.atCenterOf(absolutePos), Direction.UP, absolutePos, false)));
			helper.assertTrue(result.consumesAction() && helper.getBlockState(pos).is(entry.getValue()),
					"烈焰铲转化失败：" + entry.getKey() + "→" + entry.getValue());
			index++;
		}
		helper.succeed();
	}

	@GameTest
	public void blazeAxeCraftsStripsAndSmeltsLogsIntoRefinedCoal(GameTestHelper helper) {
		ItemStack axe = new ItemStack(ModItems.BLAZE_AXE);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.BLAZE_AXE_KEY)
				== ModItems.BLAZE_AXE, "烈焰斧物品未注册");
		helper.assertTrue(axe.getMaxStackSize() == 1 && axe.getRarity() == Rarity.EPIC
				&& axe.has(DataComponents.DAMAGE_RESISTANT) && axe.getMaxDamage() == 7777,
				"烈焰斧物品属性或耐久错误");
		helper.assertTrue(axe.is(ItemTags.AXES), "烈焰斧缺少原版斧标签");
		helper.assertFalse(ModItems.BLAZE_AXE.isFoil(axe), "烈焰斧不应显示附魔光效");
		var modifiers = axe.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ATTACK_DAMAGE, 1.0, EquipmentSlot.MAINHAND) == 26.0
				&& modifiers.compute(Attributes.ATTACK_SPEED, 4.0, EquipmentSlot.MAINHAND) == 29.0,
				"烈焰斧没有保留上游二十五点伤害与攻速增幅");
		var enchantments = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.FIRE_ASPECT), axe) == 10,
				"烈焰斧没有自带火焰附加 X");

		CraftingInput input = blazeAxeInput();
		assertExtremeRecipe(helper, "blaze_axe", input, ModItems.BLAZE_AXE);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(7, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "blaze_axe",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockServerPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, axe);
		BlockPos stripPos = new BlockPos(7, 2, 7);
		helper.setBlock(stripPos, Blocks.OAK_LOG);
		BlockPos absoluteStripPos = helper.absolutePos(stripPos);
		InteractionResult stripResult = ModItems.BLAZE_AXE.useOn(new UseOnContext(
				player, InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absoluteStripPos), Direction.UP, absoluteStripPos, false)));
		helper.assertTrue(stripResult.consumesAction() && helper.getBlockState(stripPos).is(Blocks.STRIPPED_OAK_LOG),
				"烈焰斧关闭熔炼模式时没有保留原版去皮能力");

		player.setShiftKeyDown(true);
		ModItems.BLAZE_AXE.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(BlazeAxeItem.isSmeltingEnabled(axe), "烈焰斧没有开启熔炼模式");
		BlockPos logPos = new BlockPos(10, 2, 7);
		helper.setBlock(logPos, Blocks.OAK_LOG);
		List<ItemStack> logDrops = Block.getDrops(helper.getBlockState(logPos), helper.getLevel(),
				helper.absolutePos(logPos), null, player, axe);
		helper.assertTrue(logDrops.stream().anyMatch(stack -> stack.is(Items.CHARCOAL))
				&& logDrops.stream().anyMatch(stack -> stack.is(ModItems.REFINED_COAL)),
				"烈焰斧没有把原木熔成木炭并追加精炼煤炭：" + logDrops);
		helper.succeed();
	}

	@GameTest
	public void blazeBowCraftsSwitchesModesAndBurnsTargets(GameTestHelper helper) {
		ItemStack bow = new ItemStack(ModItems.BLAZE_BOW);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.BLAZE_BOW_KEY)
				== ModItems.BLAZE_BOW, "烈焰弓物品未注册");
		helper.assertTrue(bow.getMaxStackSize() == 1 && bow.getRarity() == Rarity.EPIC
				&& bow.has(DataComponents.DAMAGE_RESISTANT) && bow.getMaxDamage() == 0,
				"烈焰弓物品属性错误");
		helper.assertTrue(bow.is(ItemTags.BOW_ENCHANTABLE), "烈焰弓缺少弓附魔标签");
		helper.assertFalse(ModItems.BLAZE_BOW.isFoil(bow), "烈焰弓不应显示附魔光效");
		helper.assertTrue(ModItems.BLAZE_BOW.getUseAnimation(bow) == ItemUseAnimation.BOW,
				"烈焰弓没有使用拉弓动画");

		CraftingInput input = blazeBowInput();
		assertExtremeRecipe(helper, "blaze_bow", input, ModItems.BLAZE_BOW);
		List<ItemStack> wrongStacks = copyStacks(input.items());
		wrongStacks.set(1, new ItemStack(Items.DIRT));
		assertExtremeRecipeDoesNotMatch(helper, "blaze_bow",
				CraftingInput.of(input.width(), input.height(), wrongStacks));

		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		player.setItemInHand(InteractionHand.MAIN_HAND, bow);
		helper.assertFalse(BlazeBowItem.isBurningMode(bow), "烈焰弓默认不应处于灼烧火球模式");
		BurningArrowEntity arrow = (BurningArrowEntity) ModItems.BLAZE_BOW.createProjectile(
				helper.getLevel(), player, bow, 1.0F);
		helper.assertTrue(arrow.getOwner() == player && arrow.isCritArrow()
				&& Math.abs(arrow.getConfiguredBaseDamage() - 4.0) < 0.001
				&& Math.abs(arrow.getDeltaMovement().length() - 3.6) < 0.1,
				"烈焰弓满蓄力灼烧箭的归属、暴击、伤害或速度错误");
		var arrowTarget = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(7, 2, 7));
		arrow.applyImpact(helper.getLevel(), arrowTarget);
		MobEffectInstance arrowBurning = arrowTarget.getEffect(ModMobEffects.BURNING);
		helper.assertTrue(arrow.isRemoved() && arrowBurning != null
				&& arrowBurning.getDuration() == BurningArrowEntity.BURNING_DURATION
				&& arrowBurning.getAmplifier() == 0,
				"烈焰弓灼烧箭没有施加六十秒灼烧或命中后移除");

		player.setShiftKeyDown(true);
		ModItems.BLAZE_BOW.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
		player.setShiftKeyDown(false);
		helper.assertTrue(BlazeBowItem.isBurningMode(bow), "烈焰弓没有切换到灼烧火球模式");
		BurningBallEntity ball = (BurningBallEntity) ModItems.BLAZE_BOW.createProjectile(
				helper.getLevel(), player, bow, 1.0F);
		helper.assertTrue(ball.getOwner() == player
				&& Math.abs(ball.getDeltaMovement().length() - 3.6) < 0.1,
				"烈焰弓灼烧火球的归属或速度错误");
		var areaTarget = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(10, 2, 7));
		var farTarget = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(22, 2, 7));
		ball.applyImpact(helper.getLevel(), areaTarget.position());
		MobEffectInstance areaBurning = areaTarget.getEffect(ModMobEffects.BURNING);
		helper.assertTrue(areaBurning != null
				&& areaBurning.getDuration() == BurningBallEntity.BURNING_DURATION
				&& areaBurning.getAmplifier() == BurningBallEntity.BURNING_AMPLIFIER,
				"烈焰弓灼烧火球没有在范围内施加三十秒灼烧 IV");
		helper.assertFalse(player.hasEffect(ModMobEffects.BURNING), "灼烧火球不应伤害发射者");
		helper.assertFalse(farTarget.hasEffect(ModMobEffects.BURNING), "灼烧火球影响了范围外目标");

		var tickTarget = helper.spawnWithNoFreeWill(EntityTypes.RAVAGER, new BlockPos(13, 2, 7));
		float healthBefore = tickTarget.getHealth();
		ModMobEffects.BURNING.value().applyEffectTick(helper.getLevel(), tickTarget, 0);
		helper.assertTrue(Math.abs(tickTarget.getHealth() - (healthBefore - tickTarget.getMaxHealth() * 0.05F)) < 0.001
				&& tickTarget.invulnerableTime == 10,
				"灼烧效果没有按最大生命值的百分之五造成火焰伤害");
		helper.assertTrue(ModMobEffects.BURNING.value().shouldApplyEffectTickThisTick(20, 0)
				&& !ModMobEffects.BURNING.value().shouldApplyEffectTickThisTick(19, 0)
				&& ModMobEffects.BURNING.value().shouldApplyEffectTickThisTick(2, 3),
				"灼烧效果的伤害间隔没有随等级缩短");

		int useDuration = ModItems.BLAZE_BOW.getUseDuration(bow, player);
		helper.assertTrue(ModItems.BLAZE_BOW.releaseUsing(bow, helper.getLevel(), player, useDuration - 20)
				&& player.getCooldowns().isOnCooldown(bow),
				"烈焰弓灼烧火球没有成功发射或添加一秒冷却");
		helper.assertTrue(bow.getDamageValue() == 0, "上游烈焰弓不应消耗耐久");
		helper.succeed();
	}

	@GameTest
	public void neutronHorseArmorSmithsEquipsAndProtectsHorse(GameTestHelper helper) {
		ItemStack armor = new ItemStack(ModItems.NEUTRON_HORSE_ARMOR);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.NEUTRON_HORSE_ARMOR_KEY)
				== ModItems.NEUTRON_HORSE_ARMOR, "中子马铠物品未注册");
		helper.assertTrue(armor.getMaxStackSize() == 1 && armor.get(DataComponents.RARITY) == Rarity.RARE
				&& armor.has(DataComponents.DAMAGE_RESISTANT) && !armor.isDamageableItem(),
				"中子马铠物品属性错误：堆叠=" + armor.getMaxStackSize()
						+ "，基础稀有度=" + armor.get(DataComponents.RARITY)
						+ "，防火=" + armor.has(DataComponents.DAMAGE_RESISTANT)
						+ "，最大耐久=" + armor.getMaxDamage());
		helper.assertFalse(ModItems.NEUTRON_HORSE_ARMOR.isFoil(armor), "中子马铠不应显示附魔光效");

		var enchantments = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.FROST_WALKER), armor) == 10,
				"中子马铠没有自带冰霜行者 X");
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.PROTECTION), armor) == 10,
				"中子马铠没有自带保护 X");
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.FEATHER_FALLING), armor) == 4,
				"中子马铠没有自带摔落缓冲 IV");

		Equippable equippable = armor.get(DataComponents.EQUIPPABLE);
		helper.assertTrue(equippable != null && equippable.slot() == EquipmentSlot.BODY
				&& equippable.canBeEquippedBy(EntityTypes.HORSE.builtInRegistryHolder())
				&& !equippable.canBeEquippedBy(EntityTypes.PLAYER.builtInRegistryHolder())
				&& !equippable.damageOnHurt(),
				"中子马铠没有限制在战马身体槽或仍会受击损耗");
		var modifiers = armor.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(modifiers != null
				&& modifiers.compute(Attributes.ARMOR, 0.0, EquipmentSlot.BODY) == 50.0
				&& modifiers.compute(Attributes.ARMOR_TOUGHNESS, 0.0, EquipmentSlot.BODY) == 1.0
				&& modifiers.compute(Attributes.KNOCKBACK_RESISTANCE, 0.0, EquipmentSlot.BODY) == 1.0,
				"中子马铠物品没有声明五十护甲、一点韧性和完全击退抗性");

		ExtremeSmithingInput input = new ExtremeSmithingInput(
				new ItemStack(ModItems.UPGRADE_SMITHING_TEMPLATE),
				new ItemStack(Items.DIAMOND_HORSE_ARMOR),
				PotionContents.createItemStack(Items.POTION, Potions.SWIFTNESS),
				new ItemStack(ModItems.ENHANCEMENT_CORE),
				new ItemStack(Items.BLUE_ICE)
		);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE, Avaritia26.id("neutron_horse_armor"));
		RecipeHolder<ExtremeSmithingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_SMITHING, input, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.orElseThrow(() -> helper.assertionException("正确材料未匹配中子马铠强化锻造配方"));
		helper.assertTrue(recipe.value().assemble(input).is(ModItems.NEUTRON_HORSE_ARMOR),
				"中子马铠强化锻造输出错误");
		ExtremeSmithingInput wrongPotion = new ExtremeSmithingInput(
				input.template(), input.base(), PotionContents.createItemStack(Items.POTION, Potions.STRENGTH),
				input.addition2(), input.addition3());
		helper.assertFalse(recipe.value().matches(wrongPotion, helper.getLevel()),
				"中子马铠配方不应接受非迅捷药水");

		var horse = helper.spawnWithNoFreeWill(EntityTypes.HORSE, new BlockPos(8, 2, 8));
		horse.setItemSlot(EquipmentSlot.BODY, armor.copy());
		helper.assertTrue(horse.getItemBySlot(EquipmentSlot.BODY).is(ModItems.NEUTRON_HORSE_ARMOR),
				"中子马铠没有装备到战马身体槽");
		helper.runAfterDelay(1, () -> {
			helper.assertTrue(horse.getAttributeValue(Attributes.ARMOR) == 30.0
					&& horse.getAttributeValue(Attributes.ARMOR_TOUGHNESS) == 1.0
					&& horse.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) == 1.0,
					"中子马铠属性错误：护甲=" + horse.getAttributeValue(Attributes.ARMOR)
							+ "，韧性=" + horse.getAttributeValue(Attributes.ARMOR_TOUGHNESS)
							+ "，击退抗性=" + horse.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
			helper.succeed();
		});
	}

	@GameTest
	public void infinityElytraSmithsGlidesAndStrikesNearbyTargets(GameTestHelper helper) {
		ItemStack elytra = new ItemStack(ModItems.INFINITY_ELYTRA);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(ModItems.INFINITY_ELYTRA_KEY)
				== ModItems.INFINITY_ELYTRA, "无尽鞘翅物品未注册");
		helper.assertTrue(elytra.getMaxStackSize() == 1 && elytra.getRarity() == Rarity.EPIC
				&& elytra.has(DataComponents.DAMAGE_RESISTANT) && !elytra.isDamageableItem()
				&& elytra.has(DataComponents.GLIDER),
				"无尽鞘翅的堆叠、稀有度、防火、无限耐久或滑翔组件错误");
		Equippable equippable = elytra.get(DataComponents.EQUIPPABLE);
		helper.assertTrue(equippable != null && equippable.slot() == EquipmentSlot.CHEST
				&& equippable.assetId().filter(ModArmorMaterials.INFINITY_ELYTRA_ASSET::equals).isPresent()
				&& !equippable.damageOnHurt(),
				"无尽鞘翅没有使用胸甲槽、自定义翅膀资源或仍会受击损耗");

		ExtremeSmithingInput input = new ExtremeSmithingInput(
				new ItemStack(ModItems.UPGRADE_SMITHING_TEMPLATE),
				new ItemStack(Items.ELYTRA),
				new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM),
				new ItemStack(ModItems.ENHANCEMENT_CORE),
				new ItemStack(ModBlocks.NEUTRON_ITEM)
		);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
				Registries.RECIPE, Avaritia26.id("infinity_elytra"));
		RecipeHolder<ExtremeSmithingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_SMITHING, input, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.orElseThrow(() -> helper.assertionException("正确材料未匹配无尽鞘翅强化锻造配方"));
		helper.assertTrue(recipe.value().assemble(input).is(ModItems.INFINITY_ELYTRA),
				"无尽鞘翅强化锻造输出错误");
		ExtremeSmithingInput wrongAddition = new ExtremeSmithingInput(
				input.template(), input.base(), input.addition1(), input.addition2(), new ItemStack(Items.DIRT));
		helper.assertFalse(recipe.value().matches(wrongAddition, helper.getLevel()),
				"无尽鞘翅配方不应接受错误的第三附加材料");

		Player wearer = helper.makeMockPlayer(GameType.SURVIVAL);
		wearer.setPos(Vec3.atCenterOf(helper.absolutePos(new BlockPos(7, 4, 7))));
		wearer.setItemSlot(EquipmentSlot.CHEST, elytra.copy());
		wearer.setOnGround(false);
		helper.assertTrue(wearer.tryToStartFallFlying() && wearer.isFallFlying(),
				"无尽鞘翅的原生滑翔组件无法启动飞行");

		var collisionTarget = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(8, 4, 7));
		var farTarget = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(14, 4, 7));
		Player nearbyPlayer = helper.makeMockPlayer(GameType.SURVIVAL);
		nearbyPlayer.setPos(Vec3.atCenterOf(helper.absolutePos(new BlockPos(7, 4, 8))));
		float playerHealth = nearbyPlayer.getHealth();
		int struck = InfinityElytraItem.applyFlightTick(helper.getLevel(), wearer);
		helper.assertTrue(struck >= 1 && collisionTarget.getHealth() < collisionTarget.getMaxHealth()
				&& collisionTarget.getDeltaMovement().lengthSqr() > 0.0,
				"无尽鞘翅没有对三格内非玩家目标造成百点撞击伤害与击退");
		helper.assertTrue(farTarget.getHealth() == farTarget.getMaxHealth(),
				"无尽鞘翅伤害了范围外目标");
		helper.assertTrue(nearbyPlayer.getHealth() == playerHealth,
				"无尽鞘翅飞行撞击不应伤害玩家");
		helper.assertTrue(Math.abs(wearer.getDeltaMovement().length() - InfinityElytraItem.FLIGHT_SPEED) < 0.001,
				"无尽鞘翅没有把飞行速度维持在每刻一点五格");

		collisionTarget.discard();
		farTarget.discard();
		nearbyPlayer.setPos(Vec3.atCenterOf(helper.absolutePos(new BlockPos(14, 4, 14))));
		var landingTarget = helper.spawnWithNoFreeWill(EntityTypes.WARDEN, new BlockPos(8, 4, 7));
		float landingHealth = landingTarget.getHealth();
		helper.assertTrue(InfinityElytraItem.applyLandingImpact(helper.getLevel(), wearer) >= 1
				&& Math.abs(landingTarget.getHealth() - (landingHealth - InfinityElytraItem.LANDING_DAMAGE)) < 0.001,
				"无尽鞘翅落地没有对两点五格内目标造成六点虚空伤害");
		helper.succeed();
	}

	@GameTest
	public void extremeAnvilSmithsRepairsRenamesAndFallsThroughBlocks(GameTestHelper helper) {
		ItemStack anvilStack = new ItemStack(ModBlocks.EXTREME_ANVIL_ITEM);
		helper.assertTrue(BuiltInRegistries.BLOCK.getValue(ModBlocks.EXTREME_ANVIL_KEY)
				== ModBlocks.EXTREME_ANVIL
				&& BuiltInRegistries.ITEM.getValue(ModBlocks.EXTREME_ANVIL_ITEM_KEY)
				== ModBlocks.EXTREME_ANVIL_ITEM,
				"终焉之砧方块或物品未注册");
		helper.assertTrue(anvilStack.getMaxStackSize() == 64 && anvilStack.getRarity() == Rarity.EPIC
				&& anvilStack.has(DataComponents.DAMAGE_RESISTANT),
				"终焉之砧物品属性错误");
		helper.assertTrue(ModBlocks.EXTREME_ANVIL.defaultBlockState().is(BlockTags.ANVIL)
				&& ModBlocks.EXTREME_ANVIL.defaultBlockState().is(ExtremeAnvilBlock.UNBREAKABLE_SUPPORT),
				"终焉之砧没有加入原版铁砧或不可破坏支撑标签");

		ExtremeSmithingInput smithingInput = new ExtremeSmithingInput(
				new ItemStack(ModItems.UPGRADE_SMITHING_TEMPLATE),
				new ItemStack(Items.ANVIL),
				new ItemStack(ModItems.FULL_MATTER_CLUSTER),
				new ItemStack(ModItems.ENHANCEMENT_CORE),
				new ItemStack(ModBlocks.NEUTRON_ITEM)
		);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("extreme_anvil"));
		RecipeHolder<ExtremeSmithingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_SMITHING, smithingInput, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.orElseThrow(() -> helper.assertionException("正确材料未匹配终焉之砧强化锻造配方"));
		helper.assertTrue(recipe.value().assemble(smithingInput).is(ModBlocks.EXTREME_ANVIL_ITEM),
				"终焉之砧强化锻造输出错误");
		ExtremeSmithingInput wrongInput = new ExtremeSmithingInput(
				smithingInput.template(), smithingInput.base(), smithingInput.addition1(),
				smithingInput.addition2(), new ItemStack(Items.DIRT));
		helper.assertFalse(recipe.value().matches(wrongInput, helper.getLevel()),
				"终焉之砧配方不应接受错误的第三附加材料");

		BlockPos anvilPos = new BlockPos(5, 2, 5);
		helper.setBlock(anvilPos, ModBlocks.EXTREME_ANVIL);
		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		ExtremeAnvilMenu menu = new ExtremeAnvilMenu(1, player.getInventory(), helper.absolutePos(anvilPos));
		ItemStack damagedPickaxe = new ItemStack(Items.DIAMOND_PICKAXE);
		damagedPickaxe.setDamageValue(1000);
		menu.getSlot(0).set(damagedPickaxe);
		menu.getSlot(1).set(new ItemStack(Items.DIAMOND, 4));
		menu.createResult();
		ItemStack repaired = menu.getSlot(2).getItem();
		helper.assertTrue(!repaired.isEmpty() && repaired.getDamageValue() < damagedPickaxe.getDamageValue()
				&& menu.getSlot(2).mayPickup(player),
				"终焉之砧没有为零经验的生存玩家修复物品");
		ItemStack taken = menu.getSlot(2).remove(1);
		menu.getSlot(2).onTake(player, taken);
		helper.assertTrue(menu.getSlot(0).getItem().isEmpty() && menu.getSlot(1).getItem().getCount() < 4,
				"取走终焉之砧结果后没有正确消耗输入");

		ItemStack firstSword = new ItemStack(Items.DIAMOND_SWORD);
		ItemStack secondSword = new ItemStack(Items.DIAMOND_SWORD);
		var enchantmentRegistry = helper.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		var sharpness = enchantmentRegistry.getOrThrow(Enchantments.SHARPNESS);
		ItemEnchantments.Mutable swordEnchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
		swordEnchantments.set(sharpness, 5);
		EnchantmentHelper.setEnchantments(firstSword, swordEnchantments.toImmutable());
		EnchantmentHelper.setEnchantments(secondSword, swordEnchantments.toImmutable());
		menu.getSlot(0).set(firstSword);
		menu.getSlot(1).set(secondSword);
		menu.createResult();
		helper.assertTrue(EnchantmentHelper.getItemEnchantmentLevel(sharpness, menu.getSlot(2).getItem()) == 6,
				"终焉之砧没有突破原版等级上限合并锋利 V 为锋利 VI");
		helper.assertTrue(menu.setItemName("终焉测试剑")
				&& menu.getSlot(2).getItem().getHoverName().getString().equals("终焉测试剑")
				&& !menu.setItemName("x".repeat(51)),
				"终焉之砧重命名或五十字符服务端边界错误");
		menu.removed(player);

		BlockPos breakableSupport = new BlockPos(10, 4, 10);
		BlockPos fallingAnvil = breakableSupport.above();
		BlockPos safeSupport = new BlockPos(13, 4, 10);
		BlockPos safeAnvil = safeSupport.above();
		helper.setBlock(breakableSupport, Blocks.STONE);
		helper.setBlock(fallingAnvil, ModBlocks.EXTREME_ANVIL);
		helper.setBlock(safeSupport, Blocks.BEDROCK);
		helper.setBlock(safeAnvil, ModBlocks.EXTREME_ANVIL);
		helper.runAfterDelay(3, () -> {
			helper.assertFalse(helper.getBlockState(breakableSupport).is(Blocks.STONE),
					"终焉之砧下落时没有摧毁普通支撑方块");
			helper.assertBlockPresent(Blocks.BEDROCK, safeSupport);
			helper.assertBlockPresent(ModBlocks.EXTREME_ANVIL, safeAnvil);
			helper.succeed();
		});
	}

	@GameTest
	public void endlessCakeSmithsFeedsBuffsAndNeverRunsOut(GameTestHelper helper) {
		ItemStack cakeStack = new ItemStack(ModBlocks.ENDLESS_CAKE_ITEM);
		helper.assertTrue(BuiltInRegistries.BLOCK.getValue(ModBlocks.ENDLESS_CAKE_KEY)
				== ModBlocks.ENDLESS_CAKE
				&& BuiltInRegistries.ITEM.getValue(ModBlocks.ENDLESS_CAKE_ITEM_KEY)
				== ModBlocks.ENDLESS_CAKE_ITEM,
				"贪婪蛋糕方块或物品未注册");
		helper.assertTrue(cakeStack.getMaxStackSize() == 64 && cakeStack.getRarity() == Rarity.UNCOMMON,
				"贪婪蛋糕物品的堆叠或稀有度错误");
		List<Component> tooltip = new java.util.ArrayList<>();
		ModBlocks.ENDLESS_CAKE_ITEM.appendHoverText(
				cakeStack, Item.TooltipContext.of(helper.getLevel()), TooltipDisplay.DEFAULT,
				tooltip::add, TooltipFlag.NORMAL);
		helper.assertTrue(tooltip.stream().anyMatch(line -> line.getContents() instanceof TranslatableContents contents
				&& contents.getKey().equals("tooltip.avaritia26.endless_cake.desc")),
				"贪婪蛋糕缺少上游说明文本");
		BlockState cakeState = ModBlocks.ENDLESS_CAKE.defaultBlockState();
		helper.assertTrue(cakeState.is(BlockTags.MINEABLE_WITH_SHOVEL)
				&& cakeState.is(BlockTags.BEACON_BASE_BLOCKS)
				&& cakeState.is(ExtremeAnvilBlock.UNBREAKABLE_SUPPORT),
				"贪婪蛋糕没有加入铲挖掘、信标基座或终焉之砧支撑标签");

		ExtremeSmithingInput input = new ExtremeSmithingInput(
				new ItemStack(ModItems.UPGRADE_SMITHING_TEMPLATE),
				new ItemStack(Items.CAKE),
				new ItemStack(Items.GOLDEN_CARROT),
				new ItemStack(ModItems.ENHANCEMENT_CORE),
				new ItemStack(Items.DRAGON_EGG)
		);
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id("endless_cake"));
		RecipeHolder<ExtremeSmithingRecipe> recipe = helper.getLevel().getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_SMITHING, input, helper.getLevel())
				.filter(candidate -> candidate.id().equals(recipeKey))
				.orElseThrow(() -> helper.assertionException("正确材料未匹配贪婪蛋糕强化锻造配方"));
		helper.assertTrue(recipe.value().assemble(input).is(ModBlocks.ENDLESS_CAKE_ITEM),
				"贪婪蛋糕强化锻造输出错误");
		ExtremeSmithingInput wrongInput = new ExtremeSmithingInput(
				input.template(), input.base(), new ItemStack(Items.CARROT), input.addition2(), input.addition3());
		helper.assertFalse(recipe.value().matches(wrongInput, helper.getLevel()),
				"贪婪蛋糕配方不应接受普通胡萝卜");

		BlockPos cakePos = new BlockPos(7, 2, 7);
		helper.setBlock(cakePos.below(), Blocks.STONE);
		helper.setBlock(cakePos, ModBlocks.ENDLESS_CAKE);
		Player player = helper.makeMockPlayer(GameType.SURVIVAL);
		player.getFoodData().setFoodLevel(18);
		player.getFoodData().setSaturation(0.0F);
		player.getActiveEffectsMap().put(
				MobEffects.POISON, new MobEffectInstance(MobEffects.POISON, 200, 0));
		InteractionResult eaten = EndlessCakeBlock.tryEat(helper.getLevel(), helper.absolutePos(cakePos), player);
		helper.assertTrue(eaten.consumesAction() && player.getFoodData().getFoodLevel() == 20
				&& player.getFoodData().getSaturationLevel() > 0.0F,
				"贪婪蛋糕没有恢复两点饥饿值与饱和度");
		helper.assertFalse(player.hasEffect(MobEffects.POISON), "贪婪蛋糕没有移除中毒");
		MobEffectInstance regeneration = player.getEffect(MobEffects.REGENERATION);
		MobEffectInstance resistance = player.getEffect(MobEffects.RESISTANCE);
		MobEffectInstance fireResistance = player.getEffect(MobEffects.FIRE_RESISTANCE);
		MobEffectInstance absorption = player.getEffect(MobEffects.ABSORPTION);
		helper.assertTrue(regeneration != null && regeneration.getDuration() == 400
				&& regeneration.getAmplifier() == 1
				&& resistance != null && resistance.getDuration() == 6000
				&& fireResistance != null && fireResistance.getDuration() == 6000
				&& absorption != null && absorption.getDuration() == 2400 && absorption.getAmplifier() == 3,
				"贪婪蛋糕的生命恢复 II、抗性、抗火或伤害吸收 IV 参数错误");
		helper.assertBlockPresent(ModBlocks.ENDLESS_CAKE, cakePos);
		helper.assertTrue(EndlessCakeBlock.tryEat(helper.getLevel(), helper.absolutePos(cakePos), player)
				.consumesAction(), "满饥饿值时贪婪蛋糕不再允许重复食用");
		helper.assertBlockPresent(ModBlocks.ENDLESS_CAKE, cakePos);
		helper.assertTrue(cakeState.hasAnalogOutputSignal()
				&& cakeState.getAnalogOutputSignal(helper.getLevel(), helper.absolutePos(cakePos), Direction.UP) == 14,
				"贪婪蛋糕没有持续输出十四格比较器信号");

		helper.setBlock(cakePos.below(), Blocks.AIR);
		helper.runAfterDelay(1, () -> {
			helper.assertBlockNotPresent(ModBlocks.ENDLESS_CAKE, cakePos);
			helper.succeed();
		});
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
		helper.assertTrue(ModItems.MATTER_CLUSTER.getBarColor(packed.getFirst()) == 0x7F3FFF, "物质团容量条颜色错误");
		List<Component> tooltip = new java.util.ArrayList<>();
		ModItems.MATTER_CLUSTER.appendHoverText(
				packed.getFirst(),
				Item.TooltipContext.of(helper.getLevel()),
				TooltipDisplay.DEFAULT,
				tooltip::add,
				TooltipFlag.NORMAL
		);
		helper.assertTrue(tooltip.size() == 7, "满物质团提示应当包含容量、五组预览和剩余组数");

		var ops = helper.getLevel().registryAccess().createSerializationContext(JsonOps.INSTANCE);
		var encoded = ItemStack.CODEC.encodeStart(ops, packed.getFirst()).getOrThrow();
		ItemStack restored = ItemStack.CODEC.parse(ops, encoded).getOrThrow();
		helper.assertTrue(MatterClusterItem.getSize(restored) == MatterClusterItem.CAPACITY, "物质团存档往返丢失内容");
		RegistryFriendlyByteBuf buffer = new RegistryFriendlyByteBuf(
				Unpooled.buffer(),
				helper.getLevel().registryAccess()
		);
		try {
			ItemStack.STREAM_CODEC.encode(buffer, packed.getFirst());
			ItemStack networkRestored = ItemStack.STREAM_CODEC.decode(buffer);
			helper.assertTrue(
					MatterClusterItem.getSize(networkRestored) == MatterClusterItem.CAPACITY,
					"物质团网络同步往返丢失内容"
			);
		} finally {
			buffer.release();
		}

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
		helper.assertTrue(
				BuiltInRegistries.RECIPE_SERIALIZER.getValue(Avaritia26.id("full_matter_cluster"))
						== ModRecipes.FULL_MATTER_CLUSTER_SERIALIZER,
				"满物质团配方序列化器没有注册"
		);

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
		helper.assertTrue(recipe.value() instanceof FullMatterClusterRecipe, "满物质团配方没有使用专用配方类型");
		RegistryFriendlyByteBuf recipeBuffer = new RegistryFriendlyByteBuf(
				Unpooled.buffer(),
				helper.getLevel().registryAccess()
		);
		try {
			FullMatterClusterRecipe.STREAM_CODEC.encode(recipeBuffer, (FullMatterClusterRecipe) recipe.value());
			FullMatterClusterRecipe decoded = FullMatterClusterRecipe.STREAM_CODEC.decode(recipeBuffer);
			helper.assertTrue(decoded.matches(input, helper.getLevel()), "满物质团配方网络同步往返后不再匹配");
			helper.assertTrue(
					decoded.assemble(input).is(ModItems.FULL_MATTER_CLUSTER),
					"满物质团配方网络同步丢失输出"
			);
		} finally {
			recipeBuffer.release();
		}

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
		helper.assertFalse(
				recipe.value().matches(CraftingInput.of(1, 1, List.of(new ItemStack(Items.COBBLESTONE))), helper.getLevel()),
				"普通物品不应匹配满物质团配方"
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

	private static void assertExtremeRecipeDoesNotMatch(GameTestHelper helper, String path, CraftingInput input) {
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id(path));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(ModRecipes.EXTREME_CRAFTING, input, helper.getLevel())
						.filter(recipe -> recipe.id().equals(recipeKey))
						.isPresent(),
				path + " 错误奇点不应匹配配方"
		);
	}

	private static CraftingInput extremeInput(List<String> pattern, Map<Character, ItemStack> key) {
		List<ItemStack> stacks = new java.util.ArrayList<>(pattern.size() * pattern.getFirst().length());
		for (String row : pattern) {
			for (char symbol : row.toCharArray()) {
				stacks.add(symbol == ' ' ? ItemStack.EMPTY : key.get(symbol).copy());
			}
		}
		return CraftingInput.of(pattern.getFirst().length(), pattern.size(), stacks);
	}

	private static void assertCollectorTier(
			GameTestHelper helper,
			BlockPos relativePos,
			Block block,
			Item blockItem,
			NeutronCollectorTier expectedTier,
			Item output,
			int productionTicks,
			String name
	) {
		ItemStack machineStack = new ItemStack(blockItem);
		helper.assertTrue(machineStack.getRarity() == Rarity.EPIC, name + "稀有度错误");
		helper.assertTrue(machineStack.has(DataComponents.DAMAGE_RESISTANT), name + "应当防火");
		helper.setBlock(relativePos, block);
		helper.assertTrue(helper.getBlockState(relativePos).is(BlockTags.MINEABLE_WITH_PICKAXE), name + "应当可用镐挖掘");
		helper.assertTrue(helper.getBlockState(relativePos).is(BlockTags.NEEDS_DIAMOND_TOOL), name + "应当需要钻石级工具");
		NeutronCollectorBlockEntity collector = helper.getBlockEntity(relativePos, NeutronCollectorBlockEntity.class);
		helper.assertTrue(collector.getTier() == expectedTier, name + "层级识别错误");
		helper.assertTrue(collector.productionTicks() == productionTicks, name + "生产耗时错误");
		Player menuPlayer = helper.makeMockServerPlayer(GameType.SURVIVAL);
		menuPlayer.setPos(Vec3.atCenterOf(helper.absolutePos(relativePos)));
		NeutronCollectorMenu menu = (NeutronCollectorMenu) collector.createMenu(
				4,
				menuPlayer.getInventory(),
				menuPlayer
		);
		helper.assertTrue(menu.timeRequired() == productionTicks, name + "菜单生产耗时同步错误");
		helper.assertTrue(menu.stillValid(menuPlayer), name + "菜单错误判定为不可用");
		for (int tick = 0; tick < productionTicks; tick++) {
			NeutronCollectorBlockEntity.serverTick(
					helper.getLevel(), helper.absolutePos(relativePos), helper.getBlockState(relativePos), collector
			);
		}
		helper.assertTrue(collector.getItem(0).is(output) && collector.getItem(0).getCount() == 1, name + "产物错误");
		helper.assertTrue(collector.canTakeItemThroughFace(0, collector.getItem(0), Direction.DOWN), name + "产物无法自动抽取");
		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos), helper.getLevel(), helper.absolutePos(relativePos), collector
		);
		helper.assertTrue(drops.size() == 1 && drops.getFirst().is(blockItem), name + "没有掉落自身");
	}

	private static void assertCompressorTier(
			GameTestHelper helper,
			BlockPos relativePos,
			Block block,
			Item blockItem,
			NeutronCompressorTier expectedTier,
			int materialsRequired,
			int timeRequired,
			int outputCount,
			String name
	) {
		ItemStack machineStack = new ItemStack(blockItem);
		helper.assertTrue(machineStack.getRarity() == Rarity.EPIC, name + "稀有度错误");
		helper.assertTrue(machineStack.has(DataComponents.DAMAGE_RESISTANT), name + "应当防火");
		helper.setBlock(relativePos, block);
		helper.assertTrue(helper.getBlockState(relativePos).is(BlockTags.MINEABLE_WITH_PICKAXE), name + "应当可用镐挖掘");
		helper.assertTrue(helper.getBlockState(relativePos).is(BlockTags.NEEDS_DIAMOND_TOOL), name + "应当需要钻石级工具");
		NeutronCompressorBlockEntity compressor = helper.getBlockEntity(relativePos, NeutronCompressorBlockEntity.class);
		helper.assertTrue(compressor.getTier() == expectedTier, name + "层级识别错误");
		feedCompressor(helper, relativePos, compressor, Items.OBSIDIAN, materialsRequired);
		helper.assertTrue(compressor.getMaterialCount() == materialsRequired, name + "折算输入数量错误");
		helper.assertTrue(compressor.getMaterialsRequired() == materialsRequired, name + "菜单所需材料数量错误");
		helper.assertTrue(compressor.getTimeRequired() == timeRequired, name + "折算处理时间错误");
		helper.assertTrue(compressor.getProgress() == 1, name + "装满材料后没有开始处理");

		Player menuPlayer = helper.makeMockServerPlayer(GameType.SURVIVAL);
		menuPlayer.setPos(Vec3.atCenterOf(helper.absolutePos(relativePos)));
		NeutronCompressorMenu menu = (NeutronCompressorMenu) compressor.createMenu(
				5, menuPlayer.getInventory(), menuPlayer
		);
		helper.assertTrue(menu.materialsRequired() == materialsRequired, name + "材料数量未同步到菜单");
		helper.assertTrue(menu.timeRequired() == timeRequired, name + "处理时间未同步到菜单");
		helper.assertTrue(menu.stillValid(menuPlayer), name + "菜单错误判定为不可用");
		if (outputCount == 2) {
			ItemStack blockedOutput = SingularityItem.createStack(SingularityManager.get(Avaritia26.id("obsidian")));
			blockedOutput.setCount(63);
			compressor.setItem(NeutronCompressorBlockEntity.OUTPUT_SLOT, blockedOutput);
			NeutronCompressorBlockEntity.serverTick(
					helper.getLevel(), helper.absolutePos(relativePos), helper.getBlockState(relativePos), compressor
			);
			helper.assertTrue(compressor.getProgress() == 1, name + "输出空间不足时仍推进进度");
			compressor.setItem(NeutronCompressorBlockEntity.OUTPUT_SLOT, ItemStack.EMPTY);
		}

		for (int tick = 1; tick < timeRequired - 1; tick++) {
			NeutronCompressorBlockEntity.serverTick(
					helper.getLevel(), helper.absolutePos(relativePos), helper.getBlockState(relativePos), compressor
			);
		}
		helper.assertTrue(compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT).isEmpty(), name + "提前产出");
		NeutronCompressorBlockEntity.serverTick(
				helper.getLevel(), helper.absolutePos(relativePos), helper.getBlockState(relativePos), compressor
		);
		ItemStack output = compressor.getItem(NeutronCompressorBlockEntity.OUTPUT_SLOT);
		SingularityDefinition definition = output.get(ModDataComponents.SINGULARITY);
		helper.assertTrue(definition != null && definition.name().equals(Avaritia26.id("obsidian")), name + "产物类型错误");
		helper.assertTrue(output.getCount() == outputCount, name + "产出倍率错误");
		List<ItemStack> drops = Block.getDrops(
				helper.getBlockState(relativePos), helper.getLevel(), helper.absolutePos(relativePos), compressor
		);
		helper.assertTrue(drops.size() == 1 && drops.getFirst().is(blockItem), name + "没有掉落自身");
	}

	private static void assertInfinityArmorPiece(
			GameTestHelper helper,
			ResourceKey<Item> key,
			Item item,
			ArmorType type,
			TagKey<Item> armorTag,
			TagKey<Item> enchantmentTag,
			int defense
	) {
		ItemStack stack = new ItemStack(item);
		helper.assertTrue(BuiltInRegistries.ITEM.getValue(key) == item, item + " 没有注册到预期的 ResourceKey");
		helper.assertTrue(item instanceof InfinityArmorItem armor && armor.armorType() == type, item + " 护甲类型错误");
		helper.assertTrue(stack.getMaxStackSize() == 1 && !stack.isDamageableItem(), item + " 应当不可堆叠且永不损耗");
		helper.assertTrue(stack.getRarity() == Rarity.EPIC, item + " 稀有度错误");
		helper.assertTrue(stack.is(armorTag) && stack.is(enchantmentTag), item + " 护甲或附魔标签错误");
		Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
		helper.assertTrue(
				equippable != null
						&& equippable.slot() == type.getSlot()
						&& !equippable.damageOnHurt()
						&& equippable.assetId().filter(ModArmorMaterials.INFINITY_ASSET::equals).isPresent(),
				item + " 装备槽、受击损耗或渲染资产错误"
		);
		ItemAttributeModifiers attributes = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
		helper.assertTrue(attributes != null, item + " 缺少护甲属性组件");
		assertClose(helper, attributes.compute(Attributes.ARMOR, 0.0, type.getSlot()), defense, item + " 护甲值错误");
		assertClose(helper, attributes.compute(Attributes.ARMOR_TOUGHNESS, 0.0, type.getSlot()), 1.0, item + " 韧性错误");
	}

	private static void assertWrongExtremeRecipe(GameTestHelper helper, String path, CraftingInput input, String message) {
		List<ItemStack> wrongStacks = copyStacks(input.items());
		int firstFilled = 0;
		while (wrongStacks.get(firstFilled).isEmpty()) {
			firstFilled++;
		}
		wrongStacks.set(firstFilled, new ItemStack(Items.DIRT));
		ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, Avaritia26.id(path));
		helper.assertFalse(
				helper.getLevel().getServer().getRecipeManager()
						.getRecipeFor(
								ModRecipes.EXTREME_CRAFTING,
								CraftingInput.of(input.width(), input.height(), wrongStacks),
								helper.getLevel()
						)
						.filter(candidate -> candidate.id().equals(recipeKey))
						.isPresent(),
				message
		);
	}

	private static void assertClose(GameTestHelper helper, double actual, double expected, String message) {
		helper.assertTrue(Math.abs(actual - expected) < 0.00001, message + "，实际值：" + actual);
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

	private static InteractionResult useUpgrade(GameTestHelper helper, Player player, BlockPos relativePos) {
		BlockPos absolutePos = helper.absolutePos(relativePos);
		player.setPos(Vec3.atCenterOf(absolutePos));
		return ModItems.INFINITY_UPGRADE.useOn(new UseOnContext(
				player,
				InteractionHand.MAIN_HAND,
				new BlockHitResult(Vec3.atCenterOf(absolutePos), Direction.UP, absolutePos, false)
		));
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

	private static CraftingInput starFuelInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"         ",
				"  aaaaa  ",
				" abbcbba ",
				" abaaaba ",
				" acadaca ",
				" abaaaba ",
				" abbcbba ",
				"  aaaaa  ",
				"         "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'a' -> new ItemStack(Items.COAL_BLOCK);
					case 'b' -> new ItemStack(Items.MAGMA_BLOCK);
					case 'c' -> new ItemStack(Items.LAVA_BUCKET);
					case 'd' -> new ItemStack(ModItems.ETERNAL_SINGULARITY);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知星辰燃料配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput infinityUpgradeInput() {
		return extremeInput(List.of(
				" aaadaaa ",
				"baeegeea ",
				"baegggea ",
				"bdiegeid ",
				"bdfehefdc",
				" diegeidc",
				" aedgdeac",
				" aeegeeac",
				" aaadaaa "
		), Map.of(
				'a', new ItemStack(ModBlocks.NEUTRON_ITEM),
				'b', new ItemStack(ModBlocks.BLAZE_CUBE_BLOCK_ITEM),
				'c', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT),
				'd', new ItemStack(ModItems.NEUTRON_INGOT),
				'e', new ItemStack(ModItems.NEUTRON_GEAR),
				'f', new ItemStack(ModItems.INFINITY_CATALYST),
				'g', new ItemStack(ModItems.STAR_FUEL),
				'h', new ItemStack(ModItems.ETERNAL_SINGULARITY),
				'i', new ItemStack(ModItems.NEUTRON_NUGGET)
		));
	}

	private static CraftingInput enhancementCoreInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"   PPP   ",
				" NPCCCPN ",
				" PABBBAP ",
				"PCBBXBBCP",
				"PCBXEXBCP",
				"PCBBXBBCP",
				" PABBBAP ",
				" NPCCCPN ",
				"   PPP   "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'A' -> new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM);
					case 'B' -> new ItemStack(ModItems.INFINITY_NUGGET);
					case 'C' -> new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
					case 'E' -> new ItemStack(ModItems.ENDEST_PEARL);
					case 'N' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'P' -> new ItemStack(ModItems.NEUTRON_PILE);
					case 'X' -> new ItemStack(ModItems.INFINITY_CATALYST);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知强化核心配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput upgradeSmithingTemplateInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				" aaabaaa ",
				" accecca ",
				" acefeca ",
				"dijkelmnd",
				"dogphqgrd",
				"dstufvwxd",
				" acdfdca ",
				" accecca ",
				" aaabaaa "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'a' -> new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
					case 'b' -> new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM);
					case 'c' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'd' -> new ItemStack(ModItems.NEUTRON_PILE);
					case 'e' -> new ItemStack(ModItems.INFINITY_NUGGET);
					case 'f' -> new ItemStack(ModItems.INFINITY_INGOT);
					case 'g' -> new ItemStack(ModItems.INFINITY_CATALYST);
					case 'h' -> new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
					case 'i' -> new ItemStack(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'j' -> new ItemStack(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'k' -> new ItemStack(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'l' -> new ItemStack(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'm' -> new ItemStack(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'n' -> new ItemStack(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'o' -> new ItemStack(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'p' -> new ItemStack(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'q' -> new ItemStack(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'r' -> new ItemStack(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 's' -> new ItemStack(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 't' -> new ItemStack(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'u' -> new ItemStack(Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'v' -> new ItemStack(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'w' -> new ItemStack(Items.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE);
					case 'x' -> new ItemStack(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知强化锻造模板配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput extremeSmithingTableInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"aaaaaaaaa",
				"bccfgfccb",
				"bcdhhhdcb",
				"lfhijihfl",
				"eghjkjhge",
				"lfhijihfl",
				"bcdhhhdcb",
				"bccfgfccb",
				"bleeeeelb"
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'a' -> new ItemStack(ModBlocks.NEUTRON_ITEM);
					case 'b' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'c' -> new ItemStack(ModItems.DIAMOND_LATTICE);
					case 'd' -> new ItemStack(ModItems.BLAZE_CUBE);
					case 'e' -> new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM);
					case 'f' -> new ItemStack(ModItems.INFINITY_NUGGET);
					case 'g' -> new ItemStack(ModItems.INFINITY_INGOT);
					case 'h' -> new ItemStack(ModItems.NEUTRON_GEAR);
					case 'i' -> new ItemStack(ModItems.INFINITY_CATALYST);
					case 'j' -> new ItemStack(Items.SMITHING_TABLE);
					case 'k' -> new ItemStack(ModBlocks.EXTREME_CRAFTING_TABLE_ITEM);
					case 'l' -> new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
					default -> throw new IllegalArgumentException("未知终焉锻造台配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput upgradeSmithingTemplateDuplicateInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"         ",
				" abbbbba ",
				" bdcccdb ",
				" bceeecb ",
				" bcefecb ",
				" bceeecb ",
				" bdcccdb ",
				" abbbbba ",
				"         "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'a' -> new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM);
					case 'b' -> new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
					case 'c' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'd' -> new ItemStack(ModItems.NEUTRON_PILE);
					case 'e' -> new ItemStack(ModItems.INFINITY_CATALYST);
					case 'f' -> new ItemStack(ModItems.UPGRADE_SMITHING_TEMPLATE);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知强化锻造模板复制配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
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

	private static CraftingInput infinityPickaxeInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				" IIIIIII ",
				"IIIICIIII",
				"II  N  II",
				"    N    ",
				"    N    ",
				"    N    ",
				"    N    ",
				"    N    ",
				"    N    "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'C' -> new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM);
					case 'I' -> new ItemStack(ModItems.INFINITY_INGOT);
					case 'N' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知无尽镐配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput infinityUmbrellaInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				" IBNND  C",
				" INENNNA ",
				" XINNNBN ",
				"  INNFNND",
				"   INNNNN",
				"   NINNGN",
				"  N  IINB",
				" B    XII",
				"ACC      "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'A' -> new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM);
					case 'B' -> new ItemStack(ModBlocks.NEUTRON_ITEM);
					case 'C' -> new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
					case 'D' -> new ItemStack(ModItems.NEUTRON_NUGGET);
					case 'E' -> new ItemStack(Items.FLINT_AND_STEEL);
					case 'F' -> new ItemStack(Items.WATER_BUCKET);
					case 'G' -> new ItemStack(Items.TRIDENT);
					case 'I' -> new ItemStack(ModItems.INFINITY_INGOT);
					case 'N' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'X' -> new ItemStack(ModItems.INFINITY_NUGGET);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知无尽雨伞配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput infinityShovelInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"      III",
				"     IIXI",
				"      III",
				"     N I ",
				"    N    ",
				"   N     ",
				"  N      ",
				" N       ",
				"N        "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'I' -> new ItemStack(ModItems.INFINITY_INGOT);
					case 'N' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'X' -> new ItemStack(ModBlocks.INFINITY_ITEM);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知无尽铲配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput infinityAxeInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"   I     ",
				"  IIIII  ",
				"   IIII  ",
				"     IN  ",
				"      N  ",
				"      N  ",
				"      N  ",
				"      N  ",
				"      N  "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'I' -> new ItemStack(ModItems.INFINITY_INGOT);
					case 'N' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知自然荒芜之斧配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput infinityBowInput() {
		List<ItemStack> stacks = new java.util.ArrayList<>(81);
		for (String row : List.of(
				"   II    ",
				"  I W    ",
				" I  W    ",
				"I   W    ",
				"X   W    ",
				"I   W    ",
				" I  W    ",
				"  I W    ",
				"   II    "
		)) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'I' -> new ItemStack(ModItems.INFINITY_INGOT);
					case 'W' -> new ItemStack(Items.WOOL.white());
					case 'X' -> new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知天堂陨落长弓配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, 9, stacks);
	}

	private static CraftingInput infinityCrossbowInput() {
		return extremeInput(
				List.of(
						"   IIIIIP",
						" AC N  C ",
						" CXN  C  ",
						"I NIPC   ",
						"IN PCN   ",
						"I  CNIN  ",
						"I C  NNA ",
						"IC    AAN",
						"P      NN"
				),
				Map.of(
						'A', new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM),
						'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT),
						'I', new ItemStack(ModItems.INFINITY_INGOT),
						'N', new ItemStack(ModItems.NEUTRON_INGOT),
						'P', new ItemStack(ModItems.NEUTRON_PILE),
						'X', new ItemStack(ModItems.INFINITY_CATALYST)
				)
		);
	}

	private static CraftingInput infinityShieldInput() {
		return extremeInput(
				List.of(
						" MNNCNNM ",
						" NCCDCCN ",
						" NSIIISN ",
						" NIAAAIN ",
						" NIAXAIN ",
						" NIAAAIN ",
						" NSIIISN ",
						" NCCDCCN ",
						" MNNCNNM "
				),
				Map.of(
						'A', new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM),
						'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT),
						'D', new ItemStack(ModItems.DIAMOND_LATTICE),
						'I', new ItemStack(ModItems.INFINITY_INGOT),
						'M', new ItemStack(ModBlocks.NEUTRON_ITEM),
						'N', new ItemStack(ModItems.NEUTRON_INGOT),
						'S', new ItemStack(Items.SHIELD),
						'X', new ItemStack(ModItems.INFINITY_CATALYST)
				)
		);
	}

	private static CraftingInput infinityTridentInput() {
		return extremeInput(
				List.of(
						"     I  I",
						"    I  I ",
						"   CAAI  ",
						"    AXA I",
						"    PAAI ",
						"   N  C  ",
						"  N      ",
						" C       ",
						"A        "
				),
				Map.of(
						'A', new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM),
						'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT),
						'I', new ItemStack(ModItems.INFINITY_INGOT),
						'N', new ItemStack(ModItems.NEUTRON_INGOT),
						'P', new ItemStack(Items.HEART_OF_THE_SEA),
						'X', new ItemStack(ModItems.INFINITY_CATALYST)
				)
		);
	}

	private static CraftingInput infinityMaceInput() {
		return extremeInput(
				List.of(
						"     CC C",
						"    CIIC ",
						"    CIMIC",
						"     CIIC",
						"    N CC ",
						"   D     ",
						"  N      ",
						" N       ",
						"C        "
				),
				Map.of(
						'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT),
						'D', new ItemStack(Items.BREEZE_ROD),
						'I', new ItemStack(ModItems.INFINITY_NUGGET),
						'M', new ItemStack(Items.HEAVY_CORE),
						'N', new ItemStack(ModItems.NEUTRON_INGOT)
				)
		);
	}

	private static CraftingInput crystalSwordInput() {
		return extremeInput(
				List.of(
						"     CA",
						"    CAC",
						" C CAC ",
						" CCAC  ",
						"CAAC   ",
						" BACC  ",
						"A C    "
				),
				Map.of(
						'A', new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM),
						'B', new ItemStack(ModBlocks.NEUTRON_ITEM),
						'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT)
				)
		);
	}

	private static CraftingInput crystalHoeInput() {
		return extremeInput(
				List.of(
						"CAAAAA ",
						" CCCA A",
						"     AA",
						"   B CA",
						"  B   C",
						" B     ",
						"A      "
				),
				Map.of(
						'A', new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM),
						'B', new ItemStack(ModItems.NEUTRON_INGOT),
						'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT)
				)
		);
	}

	private static CraftingInput crystalPickaxeInput() {
		return extremeInput(
				List.of(
						"CAAAA D",
						" CCCC  ",
						"    CCA",
						"   B CA",
						"  B  CA",
						" B   CA",
						"A     C"
				),
				Map.of(
						'A', new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM),
						'B', new ItemStack(ModItems.NEUTRON_INGOT),
						'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT),
						'D', new ItemStack(ModBlocks.NEUTRON_ITEM)
				)
		);
	}

	private static CraftingInput crystalShovelInput() {
		return extremeInput(
				List.of(
						"    CCC",
						"   CCCC",
						"    CCC",
						"   B C ",
						"  B    ",
						" B     ",
						"A      "
				),
				Map.of(
						'A', new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM),
						'B', new ItemStack(ModItems.NEUTRON_INGOT),
						'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT)
				)
		);
	}

	private static CraftingInput crystalAxeInput() {
		return extremeInput(
				List.of(
						"  CCC  ",
						" CAA D ",
						" CAA   ",
						" C  AC ",
						"  B CC ",
						" B     ",
						"A      "
				),
				Map.of(
						'A', new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM),
						'B', new ItemStack(ModItems.NEUTRON_INGOT),
						'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT),
						'D', new ItemStack(ModBlocks.NEUTRON_ITEM)
				)
		);
	}

	private static CraftingInput crystalBowInput() {
		return extremeInput(
				List.of(
						"  CDAAC",
						"   CCCD",
						"C A  B ",
						"DC  B  ",
						"AC B   ",
						"ACB    ",
						"CD     "
				),
				Map.of(
						'A', new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM),
						'B', new ItemStack(ModItems.NEUTRON_INGOT),
						'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT),
						'D', new ItemStack(ModItems.DIAMOND_LATTICE)
				)
		);
	}

	private static CraftingInput blazeSwordInput() {
		return extremeInput(
				List.of("   DC", "A DCD", "ADCD ", " ED  ", "B AA "),
				Map.of(
						'A', new ItemStack(Items.BONE_BLOCK),
						'B', new ItemStack(ModItems.DIAMOND_LATTICE),
						'C', new ItemStack(ModItems.BLAZE_CUBE),
						'D', new ItemStack(Items.BLAZE_POWDER),
						'E', new ItemStack(Items.SOUL_SOIL)
				)
		);
	}

	private static CraftingInput blazeHoeInput() {
		return extremeInput(
				List.of("DDCCA", " DDAC", "  A D", " E   ", "B    "),
				Map.of(
						'A', new ItemStack(Items.BONE_BLOCK),
						'B', new ItemStack(ModItems.DIAMOND_LATTICE),
						'C', new ItemStack(ModItems.BLAZE_CUBE),
						'D', new ItemStack(Items.BLAZE_POWDER),
						'E', new ItemStack(Items.SOUL_SOIL)
				)
		);
	}

	private static CraftingInput blazePickaxeInput() {
		return extremeInput(
				List.of("DCCCA", " DDAC", "  ADC", " E DC", "B   D"),
				Map.of(
						'A', new ItemStack(Items.BONE_BLOCK),
						'B', new ItemStack(ModItems.DIAMOND_LATTICE),
						'C', new ItemStack(ModItems.BLAZE_CUBE),
						'D', new ItemStack(Items.BLAZE_POWDER),
						'E', new ItemStack(Items.SOUL_SOIL)
				)
		);
	}

	private static CraftingInput blazeShovelInput() {
		return extremeInput(
				List.of("   DC", "  DCD", "  AD ", " E   ", "B    "),
				Map.of(
						'A', new ItemStack(Items.BONE_BLOCK),
						'B', new ItemStack(ModItems.DIAMOND_LATTICE),
						'C', new ItemStack(ModItems.BLAZE_CUBE),
						'D', new ItemStack(Items.BLAZE_POWDER),
						'E', new ItemStack(Items.SOUL_SOIL)
				)
		);
	}

	private static CraftingInput blazeAxeInput() {
		return extremeInput(
				List.of("  DDA", " DCA ", " DACD", " E DD", "B    "),
				Map.of(
						'A', new ItemStack(Items.BONE_BLOCK),
						'B', new ItemStack(ModItems.DIAMOND_LATTICE),
						'C', new ItemStack(ModItems.BLAZE_CUBE),
						'D', new ItemStack(Items.BLAZE_POWDER),
						'E', new ItemStack(Items.SOUL_SOIL)
				)
		);
	}

	private static CraftingInput blazeBowInput() {
		return extremeInput(
				List.of(" ABBA", "ACDDC", "BD E ", "BDE  ", "AC   "),
				Map.of(
						'A', new ItemStack(Items.BONE_BLOCK),
						'B', new ItemStack(ModItems.BLAZE_CUBE),
						'C', new ItemStack(ModItems.DIAMOND_LATTICE),
						'D', new ItemStack(Items.BLAZE_POWDER),
						'E', new ItemStack(Items.SOUL_SOIL)
				)
		);
	}

	private static void assertCrossbowProjectile(
			GameTestHelper helper,
			Player player,
			ItemStack crossbow,
			Item ammo,
			Class<? extends Projectile> expectedType,
			String name
	) {
		Projectile projectile = InfinityCrossbowItem.createProjectile(
				helper.getLevel(), player, new ItemStack(ammo), crossbow
		);
		helper.assertTrue(expectedType.isInstance(projectile), "无尽弩没有为" + name + "创建正确投射物");
		helper.assertTrue(InfinityCrossbowItem.isAmmo(new ItemStack(ammo)), "无尽弩没有识别" + name + "弹药");
		projectile.discard();
	}

	private static CraftingInput infinityHelmetInput() {
		return infinityArmorInput(List.of(
				"  NNNNN  ",
				" NIIIIIN ",
				" N XIX N ",
				" NIIIIIN ",
				" NIIIIIN ",
				" NI I IN "
		));
	}

	private static CraftingInput infinityChestplateInput() {
		return infinityArmorInput(List.of(
				" NN   NN ",
				"NNN   NNN",
				"NNN   NNN",
				" NIIIIIN ",
				" NIICIIN ",
				" NIIIIIN ",
				" NIIIIIN ",
				" NIIIIIN ",
				"  NNNNN  "
		));
	}

	private static CraftingInput infinityPantsInput() {
		return infinityArmorInput(List.of(
				"NNNNNNNNN",
				"NIIIXIIIN",
				"NINNXNNIN",
				"NIN   NIN",
				"NCN   NCN",
				"NIN   NIN",
				"NIN   NIN",
				"NIN   NIN",
				"NNN   NNN"
		));
	}

	private static CraftingInput infinityBootsInput() {
		return infinityArmorInput(List.of(
				" NNN NNN ",
				" NIN NIN ",
				" NIN NIN ",
				"NNIN NINN",
				"NIIN NIIN",
				"NNNN NNNN"
		));
	}

	private static CraftingInput infinityArmorInput(List<String> pattern) {
		List<ItemStack> stacks = new java.util.ArrayList<>(pattern.size() * 9);
		for (String row : pattern) {
			for (char symbol : row.toCharArray()) {
				ItemStack stack = switch (symbol) {
					case 'C' -> new ItemStack(ModBlocks.CRYSTAL_MATRIX_ITEM);
					case 'I' -> new ItemStack(ModItems.INFINITY_INGOT);
					case 'N' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'X' -> new ItemStack(ModItems.INFINITY_CATALYST);
					case ' ' -> ItemStack.EMPTY;
					default -> throw new IllegalArgumentException("未知无尽护甲配方符号: " + symbol);
				};
				stacks.add(stack);
			}
		}
		return CraftingInput.of(9, pattern.size(), stacks);
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

	private static CraftingInput neutronRingInput() {
		String[] pattern = {
				"  aaa  ",
				" cbbbc ",
				"ab d ba",
				"abdedba",
				"ab d ba",
				" cbbbc ",
				"  aaa  "
		};
		List<ItemStack> stacks = new java.util.ArrayList<>(49);
		for (String row : pattern) {
			for (char symbol : row.toCharArray()) {
				stacks.add(switch (symbol) {
					case 'a' -> new ItemStack(ModItems.NEUTRON_INGOT);
					case 'b' -> new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT);
					case 'c' -> new ItemStack(ModBlocks.DIAMOND_LATTICE_BLOCK_ITEM);
					case 'd' -> new ItemStack(ModItems.ENDEST_PEARL);
					case 'e' -> new ItemStack(ModItems.INFINITY_CATALYST);
					default -> ItemStack.EMPTY;
				});
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
