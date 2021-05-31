package me.bigvirusboi.grates.util;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class GrateUtils {
    // TODO: for when i add phantom slots :)
    public static Item readFilter(CompoundNBT nbt) {
        return Registry.ITEM.getOrDefault(new ResourceLocation(nbt.getString("FilterItem")));
    }

    public static void writeFilter(CompoundNBT nbt, Item item) {
        nbt.putString("FilterItem", item.getRegistryName().toString());
    }

    public static CompoundNBT getFilterNBT(CompoundNBT nbt, Item item) {
        nbt.putString("FilterItem", item.getRegistryName().toString());
        return nbt;
    }
}
