package net.fabricmc.example.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.item.GunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public abstract class HandRenderer {
    @Final @Shadow private EntityRenderDispatcher entityRenderDispatcher;

    int animationProgress = 5;
    int tick = 0;

    @Shadow public void renderItem(LivingEntity entity, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {}

    @Inject(method = "renderFirstPersonItem*", at = @At("HEAD"), cancellable = true)
    public void renderHands(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci)
    {
        tick++;
        //Preventing the double-rendering models
        if (hand == Hand.OFF_HAND) {
            return;
        }
        Item mainHand = player.getMainHandStack().getItem();
        if(mainHand instanceof GunItem gun)
        {
            if(gun.type.equals("pistol")) {
                matrices.translate(0, -1, -1f);
                matrices.scale(3f, 3f, 3f);
                matrices.push();
                matrices.scale(0.5f, 0.5f, 0.5f);
                matrices.multiply(new Quaternion(Vec3f.POSITIVE_X.getDegreesQuaternion(-90)));
                matrices.translate(0.75, 0.0, -0.0);
                drawHand(matrices, vertexConsumers, light, Arm.RIGHT, player);
                matrices.translate(-0.5, 0.0, 0.125);
                matrices.multiply(new Quaternion(Vec3f.POSITIVE_X.getDegreesQuaternion(90)));
                matrices.scale(0.75f, 0.75f, 0.75f);
                this.renderItem(player, player.getMainHandStack(), ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND, false, matrices, vertexConsumers, light);
            } else if (gun.type.equals("shotgun"))
            {
                matrices.translate(-0.15, -0.65f, -0.25f);
                matrices.scale(3f, 3f, 3f);

                //matrices.push();
                matrices.scale(0.5f, 0.5f, 0.5f);
                matrices.multiply(new Quaternion(Vec3f.POSITIVE_X.getDegreesQuaternion(-90)));
                matrices.translate(0.75, -0.35, 0);
                //drawHand(matrices, vertexConsumers, light, Arm.RIGHT, player);

                matrices.push();
                matrices.translate(-0.8, 0.5, -0.3);
                matrices.multiply(new Quaternion(Vec3f.POSITIVE_Z.getDegreesQuaternion(-20f)));
                matrices.multiply(new Quaternion(Vec3f.POSITIVE_Y.getDegreesQuaternion(-45f)));
                drawHand(matrices, vertexConsumers, light, Arm.LEFT, player);

                matrices.multiply(new Quaternion(Vec3f.POSITIVE_Y.getDegreesQuaternion(45f)));
                matrices.multiply(new Quaternion(Vec3f.POSITIVE_Z.getDegreesQuaternion(20f)));
                matrices.translate(0.5, 0.25, 0.3);
                matrices.multiply(new Quaternion(Vec3f.POSITIVE_X.getDegreesQuaternion(90)));
                this.renderItem(player, player.getMainHandStack(), ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND, false, matrices, vertexConsumers, light);
            }
            lAT = MinecraftClient.getInstance().mouse.wasRightButtonClicked();
            lCT = MinecraftClient.getInstance().mouse.wasLeftButtonClicked();
            ci.cancel();
        }
        //organise the code: do the switch thing, to the instanceof thing, make tags for different weapons,
        //try to work on animations and preventing some stuff that vanilla does like the hand wiggling when hitting.
        //Ya know, it better to do it later, but just... don't forget about it, alr?
        //Do the off-hand check to avoid having player with 4 hands, this is important
        //DO THE BULLET HOLE THING DON'T FORGET
    }
    public boolean lAT = false;
    public boolean lCT = false;
    public boolean isAiming = false;
    public void gunFeatures(MatrixStack matrices, AbstractClientPlayerEntity player)
    {
        if(MinecraftClient.getInstance().mouse.wasRightButtonClicked() && !lAT)
        {
            isAiming = !isAiming;
        }
        if(isAiming)
        {
            matrices.translate(-0.9, 0.7, 0.4);
        }
        if(player.isSprinting() && !isAiming) {
            matrices.multiply(new Quaternion(Vec3f.POSITIVE_X.getDegreesQuaternion(-10f)));
            matrices.translate(0, 0.25, 0);
            matrices.multiply(new Quaternion(Vec3f.POSITIVE_Y.getDegreesQuaternion(15)));
        }
        if(player.isSneaking() && !isAiming) {
            matrices.multiply(new Quaternion(Vec3f.POSITIVE_X.getDegreesQuaternion(15)));
            matrices.translate(0, 0, 0.5);
        }
    }
    public void drawHand(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Arm arm, AbstractClientPlayerEntity abstractClientPlayerEntity)
    {
        RenderSystem.setShaderTexture(0, abstractClientPlayerEntity.getSkinTexture());
        PlayerEntityRenderer playerEntityRenderer = (PlayerEntityRenderer)this.entityRenderDispatcher.getRenderer(abstractClientPlayerEntity);
        if(arm == Arm.RIGHT) {
            playerEntityRenderer.renderRightArm(matrices, vertexConsumers, light, abstractClientPlayerEntity);
        } else {
            playerEntityRenderer.renderLeftArm(matrices, vertexConsumers, light, abstractClientPlayerEntity);
        }
    }
}
