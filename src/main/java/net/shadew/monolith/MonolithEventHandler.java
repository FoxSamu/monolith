package net.shadew.monolith;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import net.shadew.monolith.feature.MonolithFeatures;

public class MonolithEventHandler {
    @SubscribeEvent
    public void onModifyBiomes(BiomeLoadingEvent event) {
        ResourceLocation id = event.getName();
        Biome.Category c = event.getCategory();

        if (isMonolithBiome(id)) {
            if (c == Biome.Category.NETHER) {
                event.getGeneration().feature(GenerationStage.Decoration.SURFACE_STRUCTURES, MonolithFeatures.MONOLITH_NETHER);
            } else if (c == Biome.Category.THEEND) {
                event.getGeneration().feature(GenerationStage.Decoration.SURFACE_STRUCTURES, MonolithFeatures.MONOLITH_END);
            } else if (c == Biome.Category.MESA) {
                event.getGeneration().feature(GenerationStage.Decoration.SURFACE_STRUCTURES, MonolithFeatures.MONOLITH_MESA);
            } else if (c == Biome.Category.DESERT) {
                event.getGeneration().feature(GenerationStage.Decoration.SURFACE_STRUCTURES, MonolithFeatures.MONOLITH_UNRARE);
            } else if (c == Biome.Category.OCEAN) {
                event.getGeneration().feature(GenerationStage.Decoration.SURFACE_STRUCTURES, MonolithFeatures.MONOLITH_OCEAN);
            } else {
                event.getGeneration().feature(GenerationStage.Decoration.SURFACE_STRUCTURES, MonolithFeatures.MONOLITH_STANDARD);
            }
        }
    }

    private static boolean isMonolithBiome(ResourceLocation id) {
        return
            isMonolithBiome(BiomeManager.getBiomes(BiomeManager.BiomeType.DESERT), id) ||
                isMonolithBiome(BiomeManager.getBiomes(BiomeManager.BiomeType.DESERT_LEGACY), id) ||
                isMonolithBiome(BiomeManager.getBiomes(BiomeManager.BiomeType.WARM), id) ||
                isMonolithBiome(BiomeManager.getBiomes(BiomeManager.BiomeType.COOL), id) ||
                isMonolithBiome(BiomeManager.getBiomes(BiomeManager.BiomeType.ICY), id) ||
                id.getNamespace().equals("minecraft") ||
                id.getNamespace().equals("biomesoplenty");
    }

    private static boolean isMonolithBiome(List<BiomeManager.BiomeEntry> biomes, ResourceLocation id) {
        for (BiomeManager.BiomeEntry e : biomes) {
            if (e.getKey().getValue().equals(id)) return true;
        }
        return false;
    }
}
