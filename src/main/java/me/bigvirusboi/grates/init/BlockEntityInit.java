package me.bigvirusboi.grates.init;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.block.entity.GrateBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Grates.MOD_ID);

    public static final RegistryObject<BlockEntityType<GrateBlockEntity>> GRATE = TILE_ENTITY_TYPES.register("metal_grate", () ->
            BlockEntityType.Builder.of(GrateBlockEntity::new,
                    BlockInit.IRON_GRATE.get(),
                    BlockInit.GOLD_GRATE.get()
            ).build(null));
}
