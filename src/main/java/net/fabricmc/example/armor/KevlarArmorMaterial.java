package net.fabricmc.example.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class KevlarArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] {6, 11, 8, 5};
    private static final int[] PROTECTION_VALUES = new int[] {2, 7, 4, 2};

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 9;
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
        //TODO: ADD KEVLAR PLAsTINES LATER AND MAKE 'EM HERE
        return null;
    }

    @Override
    public String getName() {
        return "kevlar_bullet";
    }

    @Override
    public float getToughness() {
        return 7.5f;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
