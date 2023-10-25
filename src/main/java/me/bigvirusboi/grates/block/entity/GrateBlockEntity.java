package me.bigvirusboi.grates.block.entity;

import me.bigvirusboi.grates.init.BlockEntityInit;
import me.bigvirusboi.grates.inventory.GoldGrateMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class GrateBlockEntity extends RandomizableContainerBlockEntity implements Container {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

    public GrateBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.GRATE_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GrateBlockEntity blockEntity) {
        if (level != null) {
            List<ItemEntity> entities = level.getEntitiesOfClass(ItemEntity.class, new AABB(pos).move(0, 0.75, 0));
            for (ItemEntity entity : entities) {
                if (blockEntity.getFilterItem() == ItemStack.EMPTY || entity.getItem().getItem() == blockEntity.getFilterItem().getItem()) {
                    entity.setPos(pos.getX() + .5, entity.getBlockY() - 0.5, pos.getZ() + .5);
                    entity.setDeltaMovement(0, 0, 0);
                }
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.inventory = NonNullList.withSize(1, ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag) && tag.contains("Items", 9)) {
            ContainerHelper.loadAllItems(tag, this.inventory);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.inventory, false);
        }
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.grates.gold_grate");
    }

    @Override
    protected AbstractContainerMenu createMenu(int windowId, Inventory inventory) {
        return new GoldGrateMenu(windowId, inventory, this);
    }

    @Override
    public int getContainerSize() {
        return this.inventory.size();
    }

    public ItemStack getFilterItem() {
        return this.inventory.get(0);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.inventory = items;
    }
}
