package me.bigvirusboi.grates.registry;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.block.GrateBlock;
import me.bigvirusboi.grates.block.GrateType;
import me.bigvirusboi.grates.inventory.GoldGrateMenu;
import me.bigvirusboi.grates.block.entity.GrateBlockEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// TODO tags for block break etc.

public class Registries {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Grates.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Grates.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Grates.MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Grates.MOD_ID);

    public static final RegistryObject<Item> IRON_GRATE_ITEM = ITEMS.register("iron_grate", () ->
            new BlockItem(Registries.IRON_GRATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLD_GRATE_ITEM = ITEMS.register("gold_grate", () ->
            new BlockItem(Registries.GOLD_GRATE.get(), new Item.Properties()));

    public static final RegistryObject<Block> IRON_GRATE = BLOCKS.register("iron_grate", () ->
            new GrateBlock(GrateType.IRON, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).isValidSpawn((state, level, pos, entityType) -> false)));
    public static final RegistryObject<Block> GOLD_GRATE = BLOCKS.register("gold_grate", () ->
            new GrateBlock(GrateType.GOLD, BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).isValidSpawn((state, level, pos, entityType) -> false)));

    public static final RegistryObject<BlockEntityType<GrateBlockEntity>> GRATE_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("metal_grate", () ->
            BlockEntityType.Builder.of(GrateBlockEntity::new,
                    Registries.IRON_GRATE.get(),
                    Registries.GOLD_GRATE.get()
            ).build(null));

    public static final RegistryObject<MenuType<GoldGrateMenu>> GOLD_GRATE_CONTAINER = MENU_TYPES.register("gold_grate", () ->
            IForgeMenuType.create((windowId, inv, data) -> new GoldGrateMenu(windowId, data.readBlockPos(), inv)));
}
