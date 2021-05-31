package me.bigvirusboi.grates.block;

import me.bigvirusboi.grates.tileentity.MetalGrateTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.GameType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class GrateBlock extends ContainerBlock {
    private final GrateType type;
    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(0, 13, 0, 16, 16, 2),
            Block.makeCuboidShape(0, 13, 14, 16, 16, 16),
            Block.makeCuboidShape(0, 13, 2, 2, 16, 14),
            Block.makeCuboidShape(14, 13, 2, 16, 16, 14),
            Block.makeCuboidShape(1, 13.5, 1, 15, 15.5, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public GrateBlock(GrateType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MetalGrateTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult result) {
        if (type != GrateType.IRON) {
            if (worldIn.isRemote) return ActionResultType.SUCCESS;
            if (((ServerPlayerEntity) player).interactionManager.getGameType() == GameType.ADVENTURE) return ActionResultType.SUCCESS;

            INamedContainerProvider tile = this.getContainer(state, worldIn, pos);
            if (tile != null) {
                NetworkHooks.openGui((ServerPlayerEntity) player, tile, pos);
            }

            return ActionResultType.CONSUME;
        } else return ActionResultType.PASS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new MetalGrateTileEntity();
    }
}
