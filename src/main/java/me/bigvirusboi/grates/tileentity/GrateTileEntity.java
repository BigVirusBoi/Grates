package me.bigvirusboi.grates.tileentity;

import me.bigvirusboi.grates.Registries;
import me.bigvirusboi.grates.inventory.container.GoldGrateContainer;
import me.bigvirusboi.grates.util.GrateUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class GrateTileEntity extends LockableLootTileEntity implements ITickableTileEntity {
    private Item filter = Items.AIR;

    public GrateTileEntity() {
        this(Registries.GRATE_TILE.get());
    }

    public GrateTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.filter = GrateUtils.readFilter(nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        GrateUtils.writeFilter(compound, filter);
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
        return 1;
    }

    public Item getFilterItem() {
        return filter;
    }

    @Override
    public void tick() {
        if (world != null) {
            List<ItemEntity> entities = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(pos).offset(0, 0.75, 0));
            for (ItemEntity entity : entities) {
                if (getFilterItem() == Items.AIR || entity.getItem().getItem() == getFilterItem().getItem()) {
                    entity.setPosition(pos.getX() + .5, entity.getPosY() - 0.5, pos.getZ() + .5);
                    entity.setVelocity(0, 0, 0);
                }
            }
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return NonNullList.withSize(1, filter.getDefaultInstance());
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.filter = itemsIn.get(0).getItem();
    }
}
