package me.bigvirusboi.grates.inventory.container;

import me.bigvirusboi.grates.Registries;
import me.bigvirusboi.grates.tileentity.MetalGrateTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;
import java.util.Objects;

public class GoldGrateContainer extends Container {
    public final MetalGrateTileEntity tileEntity;
    private final IInventory inventory;

    public GoldGrateContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    public GoldGrateContainer(final int id, final PlayerInventory playerInventory, final MetalGrateTileEntity tileEntity) {
        super(Registries.GOLD_GRATE_CONTAINER.get(), id);

        assertInventorySize(tileEntity, 1);

        this.tileEntity = tileEntity;
        this.inventory = playerInventory;

        // Filter Slot
        int startX = 8;
        int slotSizeP2 = 18;

        this.addSlot(new Slot(tileEntity, 0, 80, 20) {
            @Override
            public int getSlotStackLimit() {
                return 1;
            }
        });

        // Main Player Inventory
        int startPlayerInvY = 51;

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * slotSizeP2), startPlayerInvY + (row * slotSizeP2)));
            }
        }

        // Player Hotbar
        int hotbarY = 109;

        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, startX + (column * slotSizeP2), hotbarY));
        }
    }

    private static MetalGrateTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof MetalGrateTileEntity) {
            return (MetalGrateTileEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(@Nonnull PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
}