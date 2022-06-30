package net.fabricmc.example.armor.model.specnaz;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class SpecnazChestplateModel extends BipedEntityModel<LivingEntity> {
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart bb_main;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    public SpecnazChestplateModel(ModelPart root) {
        super(root, RenderLayer::getArmorCutoutNoCull);
        this.bb_main = root.getChild("body");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        cube_r1 = bb_main.getChild("cube_r1");
        cube_r2 = bb_main.getChild("cube_r2");
        cube_r3 = bb_main.getChild("cube_r3");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData rightArm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(20, 31).mirrored().cuboid(-3.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F).mirrored(false), ModelTransform.pivot(-8.0F, 12.0F, 0.0F));

        ModelPartData leftArm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(20, 31).cuboid(-1.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F), ModelTransform.pivot(8.0F, 12.0F, 0.0F));

        ModelPartData bb_main = modelPartData.addChild("body", ModelPartBuilder.create().uv(9, 54).cuboid(-5.0F, -9.0F, -4.0F, 10.0F, 9.0F, 1.0F)
                .uv(10, 43).cuboid(-5.0F, -9.0F, 3.0F, 10.0F, 9.0F, 1.0F)
                .uv(0, 56).cuboid(-5.0F, -12.0F, 3.0F, 3.0F, 3.0F, 1.0F)
                .uv(0, 30).cuboid(2.0F, -12.0F, 3.0F, 3.0F, 3.0F, 1.0F)
                .uv(17, 16).cuboid(-5.0F, -12.0F, -4.0F, 3.0F, 3.0F, 1.0F)
                .uv(0, 60).cuboid(2.0F, -12.0F, -4.0F, 3.0F, 3.0F, 1.0F)
                .uv(48, 45).cuboid(-4.8F, -14.75F, -2.4F, 3.0F, 1.0F, 5.0F)
                .uv(48, 58).cuboid(1.8F, -14.75F, -2.4F, 3.0F, 1.0F, 5.0F)
                .uv(44, 0).cuboid(-4.5F, -7.25F, -4.75F, 3.0F, 6.0F, 1.0F)
                .uv(0, 0).cuboid(-0.5F, -6.25F, -4.75F, 2.0F, 5.0F, 1.0F)
                .uv(23, 0).cuboid(2.0F, -5.25F, -4.75F, 3.0F, 4.0F, 1.0F)
                .uv(40, 33).cuboid(-4.5F, -8.5F, 3.5F, 9.0F, 6.0F, 1.0F), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 42).cuboid(-1.6F, -1.5F, -0.5F, 2.0F, 4.0F, 1.0F), ModelTransform.of(4.0F, -9.5F, -4.5F, 0.0F, 0.0F, 0.3491F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(30, 17).cuboid(-5.4F, -2.8059F, -0.5778F, 3.0F, 3.0F, 1.0F)
                .uv(56, 8).cuboid(1.4F, -2.8059F, -0.5778F, 3.0F, 3.0F, 1.0F), ModelTransform.of(0.5F, -12.0F, 3.5F, 0.3491F, 0.0F, 0.0F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(56, 0).cuboid(-10.4F, -2.8F, -0.4F, 3.0F, 3.0F, 1.0F)
                .uv(31, 12).cuboid(-3.6F, -2.8F, -0.4F, 3.0F, 3.0F, 1.0F), ModelTransform.of(5.5F, -12.0F, -3.5F, -0.3491F, 0.0F, 0.0F));

        modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }
}
