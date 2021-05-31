package me.bigvirusboi.grates.tileentity;

import me.bigvirusboi.grates.Registries;
import me.bigvirusboi.grates.inventory.container.GoldGrateContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class MetalGrateTileEntity extends LockableLootTileEntity implements INamedContainerProvider, ITickableTileEntity {//TileEntity implements INamedContainerProvider, ITickableTileEntity, IItemHandler {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

    public MetalGrateTileEntity() {
        this(Registries.METAL_GRATE_TILE.get());
    }

    public MetalGrateTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.inventory = NonNullList.withSize(1, ItemStack.EMPTY);
        if (!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.inventory);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.inventory, true);
        }
        return compound;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return this.getBlockState().getBlock().getTranslatedName();
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new GoldGrateContainer(id, player, this);
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    public ItemStack getFilterItem() {
        return this.inventory.get(0);
    }

    @Override
    public void tick() {
        if (world != null) {
            List<ItemEntity> entities = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(pos).offset(0, 0.75, 0));
            for (ItemEntity entity : entities) {
                if (getFilterItem() == ItemStack.EMPTY || entity.getItem().getItem() == getFilterItem().getItem()) {
                    entity.setPosition(pos.getX() + .5, entity.getPosY() - 0.5, pos.getZ() + .5);
                    entity.setVelocity(0, 0, 0);
                }
            }
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.inventory = itemsIn;
    }
}
