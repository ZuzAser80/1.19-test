package net.fabricmc.example.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class AttachmentItem extends Item {

    String[] tooltip;

    public AttachmentItem(Settings settings, String[] tooltip) {
        super(settings);
        this.tooltip = tooltip;
    }

    //Ability to make as much tooltips as you want... at the palm of your hand.
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        for (String s : this.tooltip) {
            tooltip.add(Text.translatable(String.valueOf(s)));
        }
    }

}
