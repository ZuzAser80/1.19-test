package net.fabricmc.example.mixin.client;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.armor.KevlarArmorMaterial;
import net.fabricmc.example.entity.basic.BulletEntity;
import net.fabricmc.example.item.BulletProofShieldItem;
import net.fabricmc.example.item.GunItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
@Environment(EnvType.CLIENT)
public abstract class LivingEntitySwingHandMixin {
    @Shadow public abstract ItemStack getMainHandStack();

    @Shadow public abstract ItemStack getOffHandStack();

    @Inject(at = @At("HEAD"), method = "swingHand(Lnet/minecraft/util/Hand;)V", cancellable = true)
    public void swingHandInject(Hand hand, CallbackInfo ci)
    {
        LivingEntity entity = ((LivingEntity)(Object)this);
        if(entity.getMainHandStack().getItem() instanceof GunItem)
        {
            ci.cancel();
        }
    }
    @Inject(at = @At("HEAD"), method = "applyArmorToDamage", cancellable = true)
    public void lowerDamageIntakeWArmor(DamageSource source, float amount, CallbackInfoReturnable<Float> cir)
    {
        if(source.name.equals("bulletSource"))
        {
            LivingEntity entity = ((LivingEntity)(Object)this);
            if(!entity.isInvulnerableTo(source))
            {
                Item headItem = entity.getEquippedStack(EquipmentSlot.HEAD).getItem();
                Item chestItem = entity.getEquippedStack(EquipmentSlot.CHEST).getItem();
                Item legsItem = entity.getEquippedStack(EquipmentSlot.LEGS).getItem();
                Item bootsItem = entity.getEquippedStack(EquipmentSlot.FEET).getItem();
                //MAKE MY OWN ARMOR ITEMS AND VANILLA ONES WON't STOP BULLETZ
                if(headItem instanceof ArmorItem && ((ArmorItem)headItem).getMaterial().getName().contains("bullet"))
                {
                    amount -= ((ArmorItem)entity.getEquippedStack(EquipmentSlot.HEAD).getItem()).getProtection() * 2;
                }
                if (chestItem instanceof ArmorItem && ((ArmorItem)chestItem).getMaterial().getName().contains("bullet"))
                {
                    amount -= ((ArmorItem)entity.getEquippedStack(EquipmentSlot.CHEST).getItem()).getProtection() * 6;
                }
                if (legsItem instanceof ArmorItem && ((ArmorItem)legsItem).getMaterial().getName().contains("bullet"))
                {
                    amount -= ((ArmorItem)entity.getEquippedStack(EquipmentSlot.LEGS).getItem()).getProtection() * 4;
                }
                if (bootsItem instanceof ArmorItem && ((ArmorItem)bootsItem).getMaterial().getName().contains("bullet"))
                {
                    amount -= ((ArmorItem)entity.getEquippedStack(EquipmentSlot.FEET).getItem()).getProtection() * 2;
                }
            }
        }
        cir.setReturnValue(amount);
    }
    @Inject(at = @At("HEAD"), method = "blockedByShield", cancellable = true)
    public void blockBulletDamage(DamageSource source, CallbackInfoReturnable<Boolean> cir)
    {
        Entity entity = source.getSource();
        LivingEntity livingEntity = ((LivingEntity)(Object)this);
        boolean bl = entity instanceof BulletEntity && livingEntity.isBlocking() && (this.getMainHandStack().getItem() instanceof BulletProofShieldItem || this.getOffHandStack().getItem() instanceof BulletProofShieldItem);
        cir.setReturnValue(bl);
    }
}
