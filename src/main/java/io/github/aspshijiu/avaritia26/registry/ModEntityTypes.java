package io.github.aspshijiu.avaritia26.registry;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.entity.BladeSlashEntity;
import io.github.aspshijiu.avaritia26.entity.EndestPearlEntity;
import io.github.aspshijiu.avaritia26.entity.GapingVoidEntity;
import io.github.aspshijiu.avaritia26.entity.HeavenArrowEntity;
import io.github.aspshijiu.avaritia26.entity.HeavenSubArrowEntity;
import io.github.aspshijiu.avaritia26.entity.InfinityThrownTridentEntity;
import io.github.aspshijiu.avaritia26.entity.NeutronArrowEntity;
import io.github.aspshijiu.avaritia26.entity.UmbrellaProjectileEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntityTypes {
	public static final ResourceKey<EntityType<?>> BLADE_SLASH_KEY = key("blade_slash");
	public static final EntityType<BladeSlashEntity> BLADE_SLASH = Registry.register(
			BuiltInRegistries.ENTITY_TYPE,
			BLADE_SLASH_KEY,
			EntityType.Builder.<BladeSlashEntity>of(BladeSlashEntity::new, MobCategory.MISC)
					.sized(0.5F, 0.5F)
					.clientTrackingRange(4)
					.updateInterval(1)
					.build(BLADE_SLASH_KEY)
	);
	public static final ResourceKey<EntityType<?>> HEAVEN_ARROW_KEY = key("heaven_arrow");
	public static final EntityType<HeavenArrowEntity> HEAVEN_ARROW = Registry.register(
			BuiltInRegistries.ENTITY_TYPE,
			HEAVEN_ARROW_KEY,
			EntityType.Builder.<HeavenArrowEntity>of(HeavenArrowEntity::new, MobCategory.MISC)
					.sized(0.5F, 0.5F)
					.clientTrackingRange(4)
					.updateInterval(1)
					.build(HEAVEN_ARROW_KEY)
	);
	public static final ResourceKey<EntityType<?>> HEAVEN_SUB_ARROW_KEY = key("heaven_sub_arrow");
	public static final EntityType<HeavenSubArrowEntity> HEAVEN_SUB_ARROW = Registry.register(
			BuiltInRegistries.ENTITY_TYPE,
			HEAVEN_SUB_ARROW_KEY,
			EntityType.Builder.<HeavenSubArrowEntity>of(HeavenSubArrowEntity::new, MobCategory.MISC)
					.sized(0.5F, 0.5F)
					.clientTrackingRange(4)
					.updateInterval(2)
					.build(HEAVEN_SUB_ARROW_KEY)
	);
	public static final ResourceKey<EntityType<?>> NEUTRON_ARROW_KEY = key("neutron_arrow");
	public static final EntityType<NeutronArrowEntity> NEUTRON_ARROW = Registry.register(
			BuiltInRegistries.ENTITY_TYPE,
			NEUTRON_ARROW_KEY,
			EntityType.Builder.<NeutronArrowEntity>of(NeutronArrowEntity::new, MobCategory.MISC)
					.sized(0.5F, 0.5F)
					.clientTrackingRange(4)
					.updateInterval(1)
					.build(NEUTRON_ARROW_KEY)
	);
	public static final ResourceKey<EntityType<?>> ENDEST_PEARL_KEY = key("endest_pearl");
	public static final EntityType<EndestPearlEntity> ENDEST_PEARL = Registry.register(
			BuiltInRegistries.ENTITY_TYPE,
			ENDEST_PEARL_KEY,
			EntityType.Builder.<EndestPearlEntity>of(EndestPearlEntity::new, MobCategory.MISC)
					.sized(0.25F, 0.25F)
					.clientTrackingRange(4)
					.updateInterval(10)
					.build(ENDEST_PEARL_KEY)
	);
	public static final ResourceKey<EntityType<?>> INFINITY_THROWN_TRIDENT_KEY = key("infinity_thrown_trident");
	public static final EntityType<InfinityThrownTridentEntity> INFINITY_THROWN_TRIDENT = Registry.register(
			BuiltInRegistries.ENTITY_TYPE,
			INFINITY_THROWN_TRIDENT_KEY,
			EntityType.Builder.<InfinityThrownTridentEntity>of(InfinityThrownTridentEntity::new, MobCategory.MISC)
					.sized(0.5F, 0.5F)
					.clientTrackingRange(4)
					.updateInterval(20)
					.build(INFINITY_THROWN_TRIDENT_KEY)
	);
	public static final ResourceKey<EntityType<?>> GAPING_VOID_KEY = key("gaping_void");
	public static final EntityType<GapingVoidEntity> GAPING_VOID = Registry.register(
			BuiltInRegistries.ENTITY_TYPE,
			GAPING_VOID_KEY,
			EntityType.Builder.<GapingVoidEntity>of(GapingVoidEntity::new, MobCategory.MISC)
					.sized(0.25F, 0.25F)
					.clientTrackingRange(10)
					.updateInterval(3)
					.build(GAPING_VOID_KEY)
	);
	public static final ResourceKey<EntityType<?>> UMBRELLA_SUN_PROJECTILE_KEY = key("umbrella_sun_projectile");
	public static final EntityType<UmbrellaProjectileEntity> UMBRELLA_SUN_PROJECTILE = umbrellaProjectile(
			UMBRELLA_SUN_PROJECTILE_KEY,
			UmbrellaProjectileEntity.Mode.SUN
	);
	public static final ResourceKey<EntityType<?>> UMBRELLA_RAIN_PROJECTILE_KEY = key("umbrella_rain_projectile");
	public static final EntityType<UmbrellaProjectileEntity> UMBRELLA_RAIN_PROJECTILE = umbrellaProjectile(
			UMBRELLA_RAIN_PROJECTILE_KEY,
			UmbrellaProjectileEntity.Mode.RAIN
	);
	public static final ResourceKey<EntityType<?>> UMBRELLA_STORM_PROJECTILE_KEY = key("umbrella_storm_projectile");
	public static final EntityType<UmbrellaProjectileEntity> UMBRELLA_STORM_PROJECTILE = umbrellaProjectile(
			UMBRELLA_STORM_PROJECTILE_KEY,
			UmbrellaProjectileEntity.Mode.STORM
	);

	private ModEntityTypes() {
	}

	public static void initialize() {
	}

	private static ResourceKey<EntityType<?>> key(String path) {
		return ResourceKey.create(Registries.ENTITY_TYPE, Avaritia26.id(path));
	}

	private static EntityType<UmbrellaProjectileEntity> umbrellaProjectile(
			ResourceKey<EntityType<?>> key,
			UmbrellaProjectileEntity.Mode mode
	) {
		return Registry.register(
				BuiltInRegistries.ENTITY_TYPE,
				key,
				EntityType.Builder.<UmbrellaProjectileEntity>of(
						(type, level) -> new UmbrellaProjectileEntity(type, level, mode),
						MobCategory.MISC
				)
						.sized(0.25F, 0.25F)
						.clientTrackingRange(4)
						.updateInterval(10)
						.build(key)
		);
	}
}
