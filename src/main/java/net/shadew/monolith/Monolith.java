package net.shadew.monolith;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("monolith")
public class Monolith {
    public Monolith() {
        FMLJavaModLoadingContext.get().getModEventBus().register(new MonolithRegistryHandler());
        MinecraftForge.EVENT_BUS.register(new MonolithEventHandler());
    }
}
