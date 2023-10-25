package me.bigvirusboi.grates.client;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.init.BlockInit;
import me.bigvirusboi.grates.init.MenuInit;
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
            ItemBlockRenderTypes.setRenderLayer(BlockInit.IRON_GRATE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockInit.GOLD_GRATE.get(), RenderType.translucent());

            // Screens
            MenuScreens.register(MenuInit.GOLD_GRATE.get(), GoldGrateScreen::new);
        });
    }
}
