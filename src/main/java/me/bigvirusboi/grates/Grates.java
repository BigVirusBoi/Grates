package me.bigvirusboi.grates;

import me.bigvirusboi.grates.client.ClientEventBus;
import me.bigvirusboi.grates.registry.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/* TODO
 - make a "hopping golden grate" that is a hopper that has a filter yes
 - fix grates they dont fully work
 - move registries to their own Init classes
 - put all in Redstone Blocks
 - make an "invert" function
 - make it like this: simple/iron grate with no extra features, advanced/gold grate with 1 filter slot and invert button
 - filter item? that can hold many items to filter??? (like create)
 - phantom slots?
 - data gen
 */

@Mod(Grates.MOD_ID)
@Mod.EventBusSubscriber(modid = Grates.MOD_ID)
public class Grates {
    public static final String MOD_ID = "grates";

    public Grates() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Registries.ITEMS.register(bus);
        Registries.BLOCKS.register(bus);
        Registries.MENU_TYPES.register(bus);
        Registries.BLOCK_ENTITY_TYPES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(ClientEventBus::clientSetup);
    }

    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(Registries.IRON_GRATE);
            event.accept(Registries.GOLD_GRATE);
        }
    }
}
