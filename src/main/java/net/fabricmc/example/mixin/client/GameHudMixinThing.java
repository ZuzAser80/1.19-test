package net.fabricmc.example.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.item.GunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(InGameHud.class)
@Environment(EnvType.CLIENT)
public abstract class GameHudMixinThing {
    @Shadow @Final private MinecraftClient client;

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Inject(at = @At("TAIL"), method = "render")
    public void renderAmmoOverlay(MatrixStack matrices, float tickDelta, CallbackInfo ci)
    {
        if(this.client.player.getMainHandStack().getItem() instanceof GunItem gun && !this.client.options.hudHidden)
        {
            //k this shit works
            ItemStack s = this.client.player.getMainHandStack();
            int sW = scaledWidth;
            int sH = scaledHeight;
            int aB = (sW - 50) - (sW - 5);
            if(s.getNbt()!=null) {
                DrawableHelper.fill(matrices, sW - 50, sH - 45, sW - 7, sH - 5, new Color(0, 0, 0, 150).getRGB());
                if (s.getNbt().getInt("magCount") > 0) {
                    DrawableHelper.fill(matrices, sW - 50, sH - 50, (sW - 50) - ((aB / gun.magCap) * s.getNbt().getInt("magCount")), sH - 45, new Color(0, 255, 0, 150).getRGB());
                }
                this.client.textRenderer.draw(matrices, s.getNbt().getInt("magCount") + " / " + gun.getTC(), sW - 45, sH - 30, new Color(255, 255, 255).getRGB());
            }
        }
    }
}
