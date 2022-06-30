package net.fabricmc.example.armor.model.specnaz;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class SpecnazHelmetModel extends BipedEntityModel<LivingEntity> {
    private final ModelPart bb_main;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;

    public SpecnazHelmetModel(ModelPart root) {
        super(root, RenderLayer::getArmorCutoutNoCull);
        this.bb_main = root.getChild("head");
        this.cube_r1 = bb_main.getChild("cube_r1");
        this.cube_r2 = bb_main.getChild("cube_r2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("head", ModelPartBuilder.create().uv(24, 10).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 1.0F, 10.0F)
                .uv(0, 0).cuboid(-5.0F, -9.0F, 5.0F, 10.0F, 8.0F, 1.0F)
                .uv(0, 31).cuboid(5.0F, -9.0F, 0.0F, 1.0F, 7.0F, 5.0F)
                .uv(0, 31).cuboid(-6.0F, -9.0F, 0.0F, 1.0F, 7.0F, 5.0F)
                .uv(52, 42).cuboid(-6.0F, -9.0F, -5.0F, 1.0F, 4.0F, 5.0F)
                .uv(52, 42).cuboid(5.0F, -9.0F, -5.0F, 1.0F, 4.0F, 5.0F)
                .uv(21, 31).cuboid(-6.5F, -7.0F, -2.0F, 2.0F, 4.0F, 4.0F)
                .uv(21, 31).cuboid(4.5F, -7.0F, -2.0F, 2.0F, 4.0F, 4.0F)
                .uv(42, 54).cuboid(-5.0F, -9.0F, -7.0F, 10.0F, 9.0F, 1.0F)
                .uv(24, 43).cuboid(-5.0F, -9.0F, -6.0F, 10.0F, 4.0F, 1.0F), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(42, 0).cuboid(-4.5F, -0.5F, -0.2F, 10.0F, 1.0F, 1.0F), ModelTransform.of(-0.5F, -0.5F, -5.5F, 0.3491F, 0.0F, 0.0F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 56).cuboid(-11.4F, -0.2856F, -6.2821F, 1.0F, 1.0F, 7.0F)
                .uv(0, 56).cuboid(-0.6F, -0.2856F, -6.2821F, 1.0F, 1.0F, 7.0F), ModelTransform.of(5.5F, -4.5F, -0.5F, 0.6981F, 0.0F, 0.0F));

        modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);

        return TexturedModelData.of(modelData, 64, 64);
    }
}
