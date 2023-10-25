package me.bigvirusboi.grates;

import me.bigvirusboi.grates.client.ClientEventBus;
import me.bigvirusboi.grates.init.BlockInit;
import me.bigvirusboi.grates.init.ItemInit;
import me.bigvirusboi.grates.init.MenuInit;
import me.bigvirusboi.grates.init.TileEntityInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* TODO
 - make a "hopping golden grate" that is a hopper that has a filter yes
 - fix grates they dont fully work
 - put all in Redstone Blocks
 - make an "invert" function button thing and a redstone powered augment of sorts
 - make it like this: simple/iron grate with no extra features, advanced/gold grate with 1 filter slot and invert button
 - filter item? that can hold many items to filter??? (like create)
 - phantom slots?
 - write a wiki for this (on a big virus boi productions page or something)
 */

@Mod(Grates.MOD_ID)
public class Grates {
    public static final String MOD_ID = "grates";
    public static final Logger LOGGER = LogManager.getLogger();

    public Grates() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::buildContents);

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        MenuInit.MENU_TYPES.register(bus);
        TileEntityInit.TILE_ENTITY_TYPES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(ClientEventBus::clientSetup);
    }

    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(BlockInit.IRON_GRATE);
            event.accept(BlockInit.GOLD_GRATE);
        }
    }

    public static ResourceLocation getResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
