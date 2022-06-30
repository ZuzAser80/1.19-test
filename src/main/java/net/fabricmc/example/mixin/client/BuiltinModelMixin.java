package net.fabricmc.example.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.item.BulletProofShieldItem;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Environment(EnvType.CLIENT)
@Mixin(HeldItemFeatureRenderer.class)
public class BuiltinModelMixin {
    @Inject(method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;Lnet/minecraft/util/Arm;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "HEAD"))
    protected void renderItemMixin(LivingEntity entity, ItemStack stack, ModelTransformation.Mode transformationMode, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                                   CallbackInfo info) {
        if (stack.getItem() instanceof BulletProofShieldItem && entity.isBlocking()) {
            int t;
            boolean bl = arm == Arm.RIGHT;
            t = bl ? 1 : -1;
            matrices.multiply(new Quaternion(Vec3f.POSITIVE_Z.getDegreesQuaternion(-40f * t)));
            matrices.multiply(new Quaternion(Vec3f.POSITIVE_Y.getDegreesQuaternion(-60f * t)));
            //matrices.multiply(new Quaternion(Vec3f.POSITIVE_X.getDegreesQuaternion(45f)));
            matrices.translate(-0.3f, 0, 0.4);
        }
    }
}
