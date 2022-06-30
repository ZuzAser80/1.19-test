package net.fabricmc.example.entity.explosive.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class HEGrenadeModel extends Model {
    private final ModelPart bb_main;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    public HEGrenadeModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
        this.bb_main = root.getChild("bb_main");
        cube_r1 = bb_main.getChild("cube_r1");
        cube_r2 = bb_main.getChild("cube_r2");
        cube_r3 = bb_main.getChild("cube_r3");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 10).cuboid(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F)
                .uv(46, 0).cuboid(4.0F, -9.0F, -4.0F, 1.0F, 8.0F, 8.0F)
                .uv(46, 0).cuboid(-5.0F, -9.0F, -4.0F, 1.0F, 8.0F, 8.0F)
                .uv(0, 55).cuboid(-4.0F, -9.0F, 4.0F, 8.0F, 8.0F, 1.0F)
                .uv(0, 55).cuboid(-4.0F, -9.0F, -5.0F, 8.0F, 8.0F, 1.0F)
                .uv(0, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 1.0F, 8.0F)
                .uv(40, 25).cuboid(-3.0F, -11.0F, -3.0F, 6.0F, 1.0F, 6.0F)
                .uv(30, 57).cuboid(-1.0F, -16.0F, -1.0F, 2.0F, 5.0F, 2.0F), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(26, 29).cuboid(-1.5F, -1.5F, -0.475F, 3.0F, 3.0F, 0.0F), ModelTransform.of(-1.5F, -13.5F, 1.5F, 0.0F, 0.0F, 0.2618F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(58, 41).cuboid(-0.3139F, -1.0564F, -1.175F, 1.0F, 5.0F, 2.0F), ModelTransform.of(-4.6313F, -13.1547F, 0.125F, 0.0F, 0.0F, 0.3054F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(52, 61).cuboid(-3.8F, -0.4F, -1.025F, 4.0F, 1.0F, 2.0F), ModelTransform.of(-1.0F, -15.5F, 0.0F, 0.0F, 0.0F, -0.4363F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        bb_main.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
