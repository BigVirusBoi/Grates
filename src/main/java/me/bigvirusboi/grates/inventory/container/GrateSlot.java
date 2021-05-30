package me.bigvirusboi.grates.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class GrateSlot extends Slot {
    public GrateSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public void onSlotChange(ItemStack oldStackIn, ItemStack newStackIn) {

    }

    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getStack() {
        return super.getStack();
    }

    @Override
    public boolean getHasStack() {
        return super.getHasStack();
    }

    @Override
    public void putStack(ItemStack stack) {
        this.onSlotChanged();
    }

    @Override
    public void onSlotChanged() {
        this.inventory.markDirty();
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public ItemStack decrStackSize(int amount) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canTakeStack(PlayerEntity playerIn) {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int getSlotIndex() {
        return 1;
    }
}
