package net.shadew.monolith;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.shadew.monolith.feature.MonolithFeatures;

public class MonolithRegistryHandler {
    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().register(MonolithFeatures.MONOLITH);
    }
}
