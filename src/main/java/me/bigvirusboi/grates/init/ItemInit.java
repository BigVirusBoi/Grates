package me.bigvirusboi.grates.init;

import me.bigvirusboi.grates.Grates;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Grates.MOD_ID);

    public static final RegistryObject<Item> IRON_GRATE_ITEM = ITEMS.register("iron_grate", () ->
            new BlockItem(BlockInit.IRON_GRATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLD_GRATE_ITEM = ITEMS.register("gold_grate", () ->
            new BlockItem(BlockInit.GOLD_GRATE.get(), new Item.Properties()));
}
