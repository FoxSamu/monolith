package net.shadew.monolith.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class MonolithFeatures {
    public static final Feature<MonolithConfig> MONOLITH = new MonolithFeature("monolith");

    public static final ConfiguredFeature<?, ?> MONOLITH_NETHER = register(
        "monolith_nether",
        MONOLITH.configure(new MonolithConfig(
            new WeightedBlockStateProvider()
                .addState(Blocks.GOLD_BLOCK.getDefaultState(), 1)
                .addState(Blocks.QUARTZ_BLOCK.getDefaultState(), 4),
            2, 6
        ))
                .decorate(Placement.RANGE.configure(new TopSolidRangeConfig(10, 20, 128)).spreadHorizontally())
                .applyChance(30 * 30)
    );

    public static final ConfiguredFeature<?, ?> MONOLITH_MESA = register(
        "monolith_mesa",
        MONOLITH.configure(new MonolithConfig(
            new WeightedBlockStateProvider()
                .addState(Blocks.GOLD_BLOCK.getDefaultState(), 1)
                .addState(Blocks.IRON_BLOCK.getDefaultState(), 1),
            2, 6
        ))
                .applyChance(20 * 20)
                .decorate(Features.Placements.HEIGHTMAP_WORLD_SURFACE.spreadHorizontally())
    );

    public static final ConfiguredFeature<?, ?> MONOLITH_UNRARE = register(
        "monolith_unrare",
        MONOLITH.configure(new MonolithConfig(new SimpleBlockStateProvider(Blocks.IRON_BLOCK.getDefaultState()), 2, 6))
                .applyChance(20 * 20)
                .decorate(Features.Placements.HEIGHTMAP_WORLD_SURFACE.spreadHorizontally())
    );

    public static final ConfiguredFeature<?, ?> MONOLITH_STANDARD = register(
        "monolith_standard",
        MONOLITH.configure(new MonolithConfig(new SimpleBlockStateProvider(Blocks.IRON_BLOCK.getDefaultState()), 2, 6))
                .applyChance(30 * 30)
                .decorate(Features.Placements.HEIGHTMAP_WORLD_SURFACE.spreadHorizontally())
    );

    public static final ConfiguredFeature<?, ?> MONOLITH_OCEAN = register(
        "monolith_ocean",
        MONOLITH.configure(new MonolithConfig(new SimpleBlockStateProvider(Blocks.PRISMARINE.getDefaultState()), 2, 6))
                .decorate(Features.Placements.TOP_SOLID_HEIGHTMAP.spreadHorizontally())
                .applyChance(36 * 36)
    );

    public static final ConfiguredFeature<?, ?> MONOLITH_END = register(
        "monolith_end",
        MONOLITH.configure(new MonolithConfig(
            new WeightedBlockStateProvider()
                .addState(Blocks.OBSIDIAN.getDefaultState(), 10)
                .addState(Blocks.BEDROCK.getDefaultState(), 1),
            2, 6
        ))
                .decorate(Features.Placements.HEIGHTMAP_WORLD_SURFACE.spreadHorizontally())
                .applyChance(35 * 35)
    );

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "monolith:" + id, feature);
    }
}
