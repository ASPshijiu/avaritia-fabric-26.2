package io.github.aspshijiu.avaritia26.singularity;

import java.util.Objects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;

public record SingularityDefinition(
		Identifier name,
		String displayName,
		int overlayColor,
		int underlayColor,
		int count,
		int timeCost,
		Ingredient ingredient,
		boolean enabled,
		boolean recipeEnabled
) {
	public static final int DEFAULT_COLOR = 0x3B2754;
	public static final Codec<SingularityDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Identifier.CODEC.fieldOf("name").forGetter(SingularityDefinition::name),
			Codec.STRING.fieldOf("displayName").forGetter(SingularityDefinition::displayName),
			Codec.INT.optionalFieldOf("overlayColor", DEFAULT_COLOR).forGetter(SingularityDefinition::overlayColor),
			Codec.INT.optionalFieldOf("underlayColor", DEFAULT_COLOR).forGetter(SingularityDefinition::underlayColor),
			Codec.intRange(1, 1_000_000).optionalFieldOf("count", 1000).forGetter(SingularityDefinition::count),
			Codec.intRange(1, 72_000).optionalFieldOf("timeCost", 240).forGetter(SingularityDefinition::timeCost),
			Ingredient.CODEC.fieldOf("ingredient").forGetter(SingularityDefinition::ingredient),
			Codec.BOOL.optionalFieldOf("enabled", true).forGetter(SingularityDefinition::enabled),
			Codec.BOOL.optionalFieldOf("recipeEnabled", true).forGetter(SingularityDefinition::recipeEnabled)
	).apply(instance, SingularityDefinition::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, SingularityDefinition> STREAM_CODEC =
			StreamCodec.of(SingularityDefinition::write, SingularityDefinition::read);

	public SingularityDefinition {
		Objects.requireNonNull(name, "奇点名称不能为空");
		Objects.requireNonNull(displayName, "奇点显示名键不能为空");
		Objects.requireNonNull(ingredient, "奇点输入不能为空");
		if (displayName.length() > 128) {
			throw new IllegalArgumentException("奇点显示名键不能超过 128 个字符");
		}
		if (count < 1 || count > 1_000_000) {
			throw new IllegalArgumentException("奇点输入数量必须在 1 到 1000000 之间");
		}
		if (timeCost < 1 || timeCost > 72_000) {
			throw new IllegalArgumentException("奇点压缩时间必须在 1 到 72000 tick 之间");
		}
		try {
			if (ingredient.isEmpty()) {
				throw new IllegalArgumentException("奇点输入不能是空配料");
			}
		} catch (IllegalStateException ignored) {
			// Tag ingredients are decoded before tags are bound during data-pack reload.
		}
	}

	private static void write(RegistryFriendlyByteBuf buffer, SingularityDefinition definition) {
		buffer.writeIdentifier(definition.name);
		buffer.writeUtf(definition.displayName, 128);
		buffer.writeInt(definition.overlayColor);
		buffer.writeInt(definition.underlayColor);
		buffer.writeVarInt(definition.count);
		buffer.writeVarInt(definition.timeCost);
		Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, definition.ingredient);
		buffer.writeBoolean(definition.enabled);
		buffer.writeBoolean(definition.recipeEnabled);
	}

	private static SingularityDefinition read(RegistryFriendlyByteBuf buffer) {
		return new SingularityDefinition(
				buffer.readIdentifier(),
				buffer.readUtf(128),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readVarInt(),
				buffer.readVarInt(),
				Ingredient.CONTENTS_STREAM_CODEC.decode(buffer),
				buffer.readBoolean(),
				buffer.readBoolean()
		);
	}
}
