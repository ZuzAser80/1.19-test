package net.fabricmc.example.armor.model.kevlar;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class KevlarChestplateModel extends BipedEntityModel<LivingEntity> {
    private final ModelPart main;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    public KevlarChestplateModel(ModelPart root) {
        super(root, RenderLayer::getArmorCutoutNoCull);
        this.main = root.getChild("body");
        this.cube_r1 = main.getChild("cube_r1");
        this.cube_r2 = main.getChild("cube_r2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("body", ModelPartBuilder.create().uv(39, 48).cuboid(-5.0F, -10.0F, -4.0F, 10.0F, 10.0F, 1.0F)
                .uv(41, 48).cuboid(-5.0F, -10.0F, 3.0F, 10.0F, 10.0F, 1.0F)
                .uv(24, 59).cuboid(-4.5F, -14.0F, -2.0F, 2.0F, 1.0F, 4.0F)
                .uv(24, 59).cuboid(2.5F, -14.0F, -2.0F, 2.0F, 1.0F, 4.0F), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 58).cuboid(-10.4F, -5.1F, -1.6F, 3.0F, 5.0F, 1.0F)
                .uv(0, 58).cuboid(-3.6F, -5.1F, -1.6F, 3.0F, 5.0F, 1.0F), ModelTransform.of(5.5F, -10.0F, 4.5F, 0.4363F, 0.0F, 0.0F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(56, 0).cuboid(-1.7F, -4.7F, -0.4F, 3.0F, 5.0F, 1.0F)
                .uv(56, 0).cuboid(-8.4F, -4.7F, -0.4F, 3.0F, 5.0F, 1.0F), ModelTransform.of(3.5F, -10.0F, -3.5F, -0.4363F, 0.0F, 0.0F));
        modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }
}
