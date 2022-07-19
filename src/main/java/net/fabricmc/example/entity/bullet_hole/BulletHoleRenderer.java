package net.fabricmc.example.entity.bullet_hole;

import net.fabricmc.example.entity.basic.BulletEntity;
import net.fabricmc.example.entity.basic.BulletEntityModel;
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

    BulletHoleModel model = new BulletHoleModel(BulletHoleModel.getTexturedModelData().createModel());

    public BulletHoleRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(BulletHoleEntity entity) {
        return new Identifier("fbg", "textures/entity/bullet_hole.png");
    }

    @Override
    public void render(BulletHoleEntity tridentEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(tridentEntity)), false, false);

        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90));
        matrixStack.translate(0.05, -1.5, 0);
        //matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, tridentEntity.prevPitch, tridentEntity.getPitch())));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStack.pop();
        super.render(tridentEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
