package me.bigvirusboi.grates.data.tag;

import me.bigvirusboi.grates.Grates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public class Blocks {
        public static final TagKey<Block> GRATES = mod("grates");

        private static TagKey<Block> mod(String path) {
            return BlockTags.create(modId(path));
        }
    }

    public class Items {
        public static final TagKey<Item> GRATES = mod("grates");

        private static TagKey<Item> mod(String path) {
            return ItemTags.create(modId(path));
        }
    }

    private static ResourceLocation modId(String path) {
        return Grates.getResource(path);
    }
}
