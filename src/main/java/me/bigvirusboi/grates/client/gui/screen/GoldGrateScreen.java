package me.bigvirusboi.grates.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import me.bigvirusboi.grates.Grates;
import me.bigvirusboi.grates.inventory.container.GoldGrateContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldGrateScreen extends ContainerScreen<GoldGrateContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Grates.MOD_ID, "textures/gui/container/gold_grate.png");

    public GoldGrateScreen(GoldGrateContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 176;
        this.ySize = 133;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int x, int y) {
        this.font.drawString(stack, this.title.getString(), 8.0f, 6.0f, 4210752);
        this.font.drawString(stack, this.playerInventory.getDisplayName().getString(), 8.0f, 39.0f, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int x2 = (this.width - this.xSize) / 2;
        int y2 = (this.height - this.ySize) / 2;
        this.blit(stack, x2, y2, 0, 0, this.xSize, this.ySize);

        Slot slot = this.container.getSlot(0);
        if (!slot.getHasStack()) blit(stack, this.guiLeft + slot.xPos, this.guiTop + slot.yPos, this.xSize, 0, 16, 16);
    }
}
