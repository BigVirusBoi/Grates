package me.bigvirusboi.grates.init;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.block.GrateBlock;
import me.bigvirusboi.grates.block.GrateType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Grates.MOD_ID);

    public static final RegistryObject<GrateBlock> IRON_GRATE = BLOCKS.register("iron_grate", () ->
            new GrateBlock(GrateType.IRON, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).isValidSpawn((state, level, pos, entityType) -> false)));
    public static final RegistryObject<GrateBlock> GOLD_GRATE = BLOCKS.register("gold_grate", () ->
            new GrateBlock(GrateType.GOLD, BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).isValidSpawn((state, level, pos, entityType) -> false)));
}
