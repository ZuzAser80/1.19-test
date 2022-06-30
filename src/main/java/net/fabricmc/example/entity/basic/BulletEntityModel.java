package net.fabricmc.example.entity.basic;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

public class BulletEntityModel extends Model {
    private final ModelPart base;

    public BulletEntityModel(ModelPart part) {
        super(RenderLayer::getEntitySolid);
        this.base = part.getChild("base");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("base",
                ModelPartBuilder.create().uv(6, 11).cuboid(-0.5F, -6.0F, -2.0F, 1.0F, 1.0F, 4.0F),
                ModelTransform.pivot(0.0F, 5.0F, 0.0F)
        );
        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        //light = 156728;
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-180));
        this.base.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
