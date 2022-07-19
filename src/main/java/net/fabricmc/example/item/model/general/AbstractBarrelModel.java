package net.fabricmc.example.item.model.general;

import net.minecraft.client.model.ModelPart;

public class AbstractBarrelModel extends AttachmentModel {

    public AbstractBarrelModel(ModelPart root) {
        super(root);
    }

    public ModelPart getMuzzlePivot()
    {
        return null;
    }
}
