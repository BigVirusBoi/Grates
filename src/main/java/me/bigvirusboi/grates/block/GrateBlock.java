package me.bigvirusboi.grates.block;

import me.bigvirusboi.grates.init.MenuInit;
import me.bigvirusboi.grates.block.entity.GrateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class GrateBlock extends BaseEntityBlock {
    private final GrateType type;
    private static final VoxelShape SHAPE = Stream.of(
            Block.box(0, 13, 0, 16, 16, 2),
            Block.box(0, 13, 14, 16, 16, 16),
            Block.box(0, 13, 2, 2, 16, 14),
            Block.box(14, 13, 2, 16, 16, 14),
            Block.box(1, 13.5, 1, 15, 15.5, 15)
    ).reduce(Shapes::or).get();

    public GrateBlock(GrateType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GrateBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, MenuInit.GRATE_BLOCK_ENTITY.get(), GrateBlockEntity::tick);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (type != GrateType.IRON) {
            if (level.isClientSide) return InteractionResult.SUCCESS;
            if (!player.getAbilities().mayBuild) return InteractionResult.SUCCESS; // TODO adventure mode + check

            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof MenuProvider provider) {
                NetworkHooks.openScreen((ServerPlayer) player, provider, pos);
            }

            return InteractionResult.CONSUME;
        } else return InteractionResult.PASS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
