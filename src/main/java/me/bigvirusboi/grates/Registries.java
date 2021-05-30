package me.bigvirusboi.grates;

import me.bigvirusboi.grates.block.GrateBlock;
import me.bigvirusboi.grates.block.GrateType;
import me.bigvirusboi.grates.inventory.container.GoldGrateContainer;
import me.bigvirusboi.grates.tileentity.GrateTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registries {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Grates.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Grates.MOD_ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Grates.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Grates.MOD_ID);

    public static final RegistryObject<Item> IRON_GRATE_ITEM = ITEMS.register("iron_grate", () ->
            new BlockItem(Registries.IRON_GRATE.get(), new Item.Properties().group(ItemGroup.REDSTONE)));
    public static final RegistryObject<Item> GOLD_GRATE_ITEM = ITEMS.register("gold_grate", () ->
            new BlockItem(Registries.GOLD_GRATE.get(), new Item.Properties().group(ItemGroup.REDSTONE)));

    public static final RegistryObject<Block> IRON_GRATE = BLOCKS.register("iron_grate", () ->
            new GrateBlock(GrateType.IRON, AbstractBlock.Properties.create(Material.IRON, MaterialColor.IRON).setRequiresTool()
                    .hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid()
                    .harvestTool(ToolType.PICKAXE).harvestLevel(1).setAllowsSpawn(Registries::neverAllowSpawn)));
    public static final RegistryObject<Block> GOLD_GRATE = BLOCKS.register("gold_grate", () ->
            new GrateBlock(GrateType.GOLD, AbstractBlock.Properties.create(Material.IRON, MaterialColor.GOLD).setRequiresTool()
                    .hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL).notSolid()
                    .harvestTool(ToolType.PICKAXE).harvestLevel(2).setAllowsSpawn(Registries::neverAllowSpawn)));

    public static final RegistryObject<ContainerType<GoldGrateContainer>> GOLD_GRATE_CONTAINER = CONTAINERS.register("gold_grate", () ->
            IForgeContainerType.create(GoldGrateContainer::new));

    public static final RegistryObject<TileEntityType<GrateTileEntity>> GRATE_TILE = TILE_ENTITIES.register("grate", () ->
            TileEntityType.Builder.create(GrateTileEntity::new,
                    Registries.GOLD_GRATE.get()
            ).build(null));



    private static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }
}
