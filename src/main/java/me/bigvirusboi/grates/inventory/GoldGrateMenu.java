package me.bigvirusboi.grates.inventory;

import me.bigvirusboi.grates.block.entity.GrateBlockEntity;
import me.bigvirusboi.grates.init.MenuInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GoldGrateMenu extends AbstractContainerMenu {
    public final GrateBlockEntity blockEntity;

    public GoldGrateMenu(int windowId, BlockPos pos, Inventory inventory) {
        this(windowId, inventory, new GrateBlockEntity(pos, inventory.player.level().getBlockState(pos)));
    }

    public GoldGrateMenu(final int id, final Inventory inventory, final GrateBlockEntity blockEntity) {
        super(MenuInit.GOLD_GRATE_CONTAINER.get(), id);

        checkContainerSize(blockEntity, 1);

        this.blockEntity = blockEntity;

        // Filter Slot
        int startX = 8;
        int slotSizeP2 = 18;

        this.addSlot(new Slot(blockEntity, 0, 80, 20) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });

        // Main Player Inventory
        int startPlayerInvY = 51;

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(inventory, 9 + (row * 9) + column, startX + (column * slotSizeP2), startPlayerInvY + (row * slotSizeP2)));
            }
        }

        // Player Hotbar
        int hotbarY = 109;

        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(inventory, column, startX + (column * slotSizeP2), hotbarY));
        }
    }

    @NotNull
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY; // TODO
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockEntity.stillValid(player);
    }

    public void removed(Player player) {
        super.removed(player);
        this.blockEntity.stopOpen(player);
    }
}