package net.shadew.monolith.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class MonolithConfig implements IFeatureConfig {
    public static final Codec<MonolithConfig> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            BlockStateProvider.TYPE_CODEC.fieldOf("material").forGetter(config -> config.material),
            Codec.INT.fieldOf("min_length").forGetter(config -> config.minLength),
            Codec.INT.fieldOf("max_length").forGetter(config -> config.maxLength)
        ).apply(
            instance,
            MonolithConfig::new
        )
    );

    public final BlockStateProvider material;
    public final int minLength;
    public final int maxLength;

    public MonolithConfig(BlockStateProvider material, int minLength, int maxLength) {
        this.material = material;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
}
