package net.fabricmc.example.item.model.general;


import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class AttachmentModel extends Model {

    public AttachmentModel(ModelPart root)
    {
        super(RenderLayer::getEntityCutoutNoCull);
    }

    public ModelPart getMain()
    {
        return null;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

    }
}