package net.fabricmc.example.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Test01 extends Item {
    public boolean inAttachmentMode;
    public String attachmentModeIndex;
    public int animationLength;
    public Test01(Settings settings) {
        super(settings);
    }
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(selected && MinecraftClient.getInstance().mouse.wasRightButtonClicked())
        {
            stack.getOrCreateNbt().putString("barrel", "silencer");
        }
    }
    public int getAnimationLength()
    {
        return this.animationLength;
    }
}
