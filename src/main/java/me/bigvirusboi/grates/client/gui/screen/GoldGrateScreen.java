package me.bigvirusboi.grates.client.gui.screen;

import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.inventory.GoldGrateMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldGrateScreen extends AbstractContainerScreen<GoldGrateMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Grates.MOD_ID, "textures/gui/container/gold_grate.png");

    public GoldGrateScreen(GoldGrateMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        this.imageWidth = 176;
        this.imageHeight = 133;

        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        int x2 = (this.width - this.imageWidth) / 2;
        int y2 = (this.height - this.imageHeight) / 2;

        graphics.blit(BACKGROUND_TEXTURE, x2, y2, 0, 0, this.imageWidth, this.imageHeight);

        Slot slot = this.menu.getSlot(0);
        if (!slot.hasItem()) graphics.blit(BACKGROUND_TEXTURE, this.getGuiLeft() + slot.x, this.getGuiTop() + slot.y, this.getXSize(), 0, 16, 16);
    }
}
