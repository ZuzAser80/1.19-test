package net.fabricmc.example.entity.bulletHole;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class BulletHoleEntityModel extends Model {
    private final ModelPart bb_main;
    private final ModelPart cube_r1;
    public BulletHoleEntityModel(ModelPart root) {
        super(RenderLayer::getEntityCutoutNoCull);
        this.bb_main = root.getChild("bb_main");
        this.cube_r1 = bb_main.getChild("cube_r1");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.scale(0.25f, 0.25f, 0.25f);
        bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
