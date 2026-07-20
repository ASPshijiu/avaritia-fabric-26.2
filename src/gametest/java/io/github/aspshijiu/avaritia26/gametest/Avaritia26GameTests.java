package io.github.aspshijiu.avaritia26.gametest;

import java.lang.reflect.Method;

import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.gametest.v1.CustomTestMethodInvoker;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.ItemStack;
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

	@Override
	public void invokeTestMethod(GameTestHelper helper, Method method) throws ReflectiveOperationException {
		helper.setBlock(0, 0, 0, Blocks.AIR);
		method.invoke(this, helper);
	}
}
