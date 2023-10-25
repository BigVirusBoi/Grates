package me.bigvirusboi.grates.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class GrateUtils {
    // TODO: for when i add phantom slots :)
    public static Item readFilter(CompoundTag nbt) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(nbt.getString("FilterItem")));
    }

    public static void writeFilter(CompoundTag nbt, Item item) {
        nbt.putString("FilterItem", item.getDescriptionId());
    }

    public static CompoundTag getFilterNBT(CompoundTag nbt, Item item) {
        nbt.putString("FilterItem", item.getDescriptionId());
        return nbt;
    }
}
