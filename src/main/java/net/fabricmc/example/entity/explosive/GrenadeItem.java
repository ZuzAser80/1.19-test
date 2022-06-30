package net.fabricmc.example.entity.explosive;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class GrenadeItem extends Item {

    public String type;
    public GrenadeItem(Settings settings, String type) {
        super(settings);
        this.type = type;
    }
    public UseAction getUseAction(ItemStack stack)
    {
        return UseAction.SPEAR;
    }
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!user.getAbilities().creativeMode && !user.getMainHandStack().isOf(this)) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if(user instanceof PlayerEntity player) {
            boolean spawned = false;
		/*
		user.getItemCooldownManager().set(this, 5);
		Optionally, you can add a cooldown to your item's right-click use, similar to Ender Pearls.
		*/
            if (!world.isClient) {
                GrenadeEntity snowballEntity = switch (type) {
                    case "Smoke" -> new GrenadeEntity(world, this, 0, 0, false, true, false);
                    case "Explosive" -> new GrenadeEntity(world, this, 10, 5, true, false, false);
                    case "Fire" -> new GrenadeEntity(world, this, 10, 0, false, false, true);
                    case "Flash" -> new GrenadeEntity(world, this, 10, 0, false, false, false);
                    default -> null;
                };
                if(snowballEntity != null) {
                    snowballEntity.setPos(player.getX(), player.getY() + 0.5, player.getZ());
                    snowballEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 0F);
                    world.spawnEntity(snowballEntity); // spawns entity
                    spawned = true;
                }
            }

            player.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!player.getAbilities().creativeMode && spawned) {
                stack.decrement(1); // decrements itemStack if user is not in creative mode
            }
        }
    }
}
