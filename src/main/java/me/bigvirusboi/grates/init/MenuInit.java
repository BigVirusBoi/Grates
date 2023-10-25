package me.bigvirusboi.grates.init;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.inventory.GoldGrateMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// TODO tags for block break etc.

public class MenuInit {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Grates.MOD_ID);

    public static final RegistryObject<MenuType<GoldGrateMenu>> GOLD_GRATE_CONTAINER = MENU_TYPES.register("gold_grate", () ->
            IForgeMenuType.create((windowId, inv, data) -> new GoldGrateMenu(windowId, data.readBlockPos(), inv)));
}
