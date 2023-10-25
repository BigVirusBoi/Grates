package me.bigvirusboi.grates.data.loot;

import me.bigvirusboi.grates.init.BlockInit;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.Collections;

public class ModBlockLoot extends BlockLootSubProvider {
    public ModBlockLoot() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(BlockInit.IRON_GRATE.get());
        this.dropSelf(BlockInit.GOLD_GRATE.get());
    }

    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}