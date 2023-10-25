package me.bigvirusboi.grates.client;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.registry.Registries;
import me.bigvirusboi.grates.client.gui.screen.GoldGrateScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Grates.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBus {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            // Render Layers
            ItemBlockRenderTypes.setRenderLayer(Registries.IRON_GRATE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(Registries.GOLD_GRATE.get(), RenderType.translucent());

            // Screens
            MenuScreens.register(Registries.GOLD_GRATE_CONTAINER.get(), GoldGrateScreen::new);
        });
    }
}
