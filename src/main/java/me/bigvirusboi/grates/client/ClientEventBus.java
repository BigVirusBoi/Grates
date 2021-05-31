package me.bigvirusboi.grates.client;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.Registries;
import me.bigvirusboi.grates.client.gui.screen.GoldGrateScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Grates.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBus {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        ScreenManager.registerFactory(Registries.GOLD_GRATE_CONTAINER.get(), GoldGrateScreen::new);

        RenderTypeLookup.setRenderLayer(Registries.IRON_GRATE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Registries.GOLD_GRATE.get(), RenderType.getCutout());
    }
}
