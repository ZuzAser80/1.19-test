package net.fabricmc.example.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class SpecnazArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] {8, 12, 10, 6};
    private static final int[] PROTECTION_VALUES = new int[] {3, 8, 5, 3};
    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 10;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return null;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return "specnaz_bullet";
    }

    @Override
    public float getToughness() {
        return 8;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
