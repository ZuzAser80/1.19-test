package net.fabricmc.example.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.item.BulletProofShieldItem;
import net.fabricmc.example.item.GunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin <T extends LivingEntity> extends AnimalModel<T> implements ModelWithArms, ModelWithHead {

    //LOOK INTO THE CROSSBOW HOLDING CODE HOLY FUCK
    //STFU
    @Final @Shadow public ModelPart rightArm;
    @Final @Shadow public ModelPart leftArm;

    @Shadow @Final public ModelPart head;
    @Shadow @Final public ModelPart body;
    @Shadow public BipedEntityModel.ArmPose rightArmPose;
    public boolean isSprinting = false;

    @Inject(method = "setAngles*", at = @At(value = "TAIL"))
    public void handRotation(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci)
    {
        if(!livingEntity.isSwimming() && !livingEntity.hasVehicle())
        {
            isSprinting = livingEntity.isSprinting();
            ItemStack s = livingEntity.getMainHandStack();
            int t = 0;
            if(s.getItem() instanceof GunItem)
            {
                if(MinecraftClient.getInstance().mouse.wasLeftButtonClicked())
                {
                    this.rightArm.pivotZ -= 0.5 * j;
                    this.rightArm.pitch = -1.570796f;
                }
                t = 1;

            } else if(livingEntity.getOffHandStack().getItem() instanceof GunItem)
            {
                t = -1;
            } else if (s.getItem() instanceof GunItem && livingEntity.getOffHandStack().getItem() instanceof GunItem)
            {
                t = 2;
            }
            if(t == 1)
            {
                //rightArm.yaw -= 0.79f;
                leftArm.pitch = -0.392699f * 3;
                leftArm.yaw += 0.79f;
                if (isSprinting)
                {
                    rightArm.pitch += 0.5;
                }
                if (livingEntity.isSneaking())
                {
                    rightArm.pitch -= 0.5;
                    leftArm.pitch -= 0.5;
                }
            } else if(t == -1)
            {
                rightArm.pitch = -0.392699f * 3;
                rightArm.yaw += 0.79f;
                if (isSprinting)
                {
                    leftArm.pitch += 0.5;
                }
                if (livingEntity.isSneaking())
                {
                    rightArm.pitch -= 0.5;
                    leftArm.pitch -= 0.5;
                }
            }
        }
    }
}
