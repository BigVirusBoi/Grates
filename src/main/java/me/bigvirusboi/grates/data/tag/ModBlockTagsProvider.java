package me.bigvirusboi.grates.data.tag;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.init.BlockInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper fileHelper) {
        super(output, provider, Grates.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.GRATES).add(BlockInit.IRON_GRATE.get(), BlockInit.GOLD_GRATE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.GRATES);
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(BlockInit.IRON_GRATE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(BlockInit.GOLD_GRATE.get());
    }
}