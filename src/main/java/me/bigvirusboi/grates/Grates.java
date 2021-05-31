package me.bigvirusboi.grates;

import me.bigvirusboi.grates.client.ClientEventBus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Grates.MOD_ID)
@Mod.EventBusSubscriber
public class Grates {
    public static final String MOD_ID = "grates";
    public static final Logger LOGGER = LogManager.getLogger();

    public Grates() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        //bus.addListener(this::commonSetup);

        Registries.ITEMS.register(bus);
        Registries.BLOCKS.register(bus);
        Registries.CONTAINERS.register(bus);
        Registries.TILE_ENTITIES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(ClientEventBus::clientSetup);
    }
}
