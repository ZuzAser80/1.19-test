package net.fabricmc.example.entity.bulletHole;

import net.fabricmc.example.entity.explosive.GrenadeEntity;
import net.fabricmc.example.entity.explosive.model.HEGrenadeModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class BulletHoleRenderer extends EntityRenderer<BulletHoleEntity> {

    Identifier texture;
    BulletHoleEntityModel model = new BulletHoleEntityModel(BulletHoleEntityModel.getTexturedModelData().createModel());

    public BulletHoleRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(BulletHoleEntity tridentEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(tridentEntity)), false, false);

        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, tridentEntity.prevYaw, tridentEntity.getYaw()) - 90.0F));
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, tridentEntity.prevPitch, tridentEntity.getPitch()) + 90.0F));
        //matrixStack.scale(0.5f, 0.5f, 0.5f);
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        super.render(tridentEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(BulletHoleEntity entity) {
        return new Identifier("fbg", "textures/entity/bullet_hole.png");
    }
}
