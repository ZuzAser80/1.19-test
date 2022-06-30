package net.fabricmc.example.item;

import net.fabricmc.example.entity.basic.BulletEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;


public class GunItem extends Item {
    public int magCap;
    public int dmg;
    public String type;
    int totalCount = 0;
    Item bul;
    int shootCooldown;
    int reloadCooldown;
    PlayerEntity pl;
    int tick;

    //TODO: Rethink the models and shit
    //TODO: FUCKING ANIMATIONS LEARN HOW TO DO IT ALREADY U LAZY FUCK ILL SWEAR AS MUCH AS I WANT BECAUSE I FUCKING CAN! DONT FORGET TO RECORD FOR BIG BREAK FOR REAL NOW
    public GunItem(Settings settings, Item bulletItem, int dmg, int magCap, String gunType, int c1, int c2) {
        super(settings);
        bul = bulletItem;
        this.dmg = dmg;
        type = gunType;
        this.magCap = magCap;
        shootCooldown = c1;
        reloadCooldown = c2;
    }

    public Item getBulletItem() {
        return bul;
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        this.tick++;
        int magCount;
        pl = (PlayerEntity) entity;
        PlayerInventory i = pl.getInventory();
        totalCount = i.count(this.bul);
        NbtCompound nbt = stack.getOrCreateNbt();
        if (selected && MinecraftClient.getInstance().mouse.wasLeftButtonClicked() && pl.isHolding(this) && tick > shootCooldown) {
            magCount = nbt.getInt("magCount");
            if (!world.isClient && !pl.getItemCooldownManager().isCoolingDown(this)) {
                if (!pl.isCreative()) {
                    magCount--;
                } else
                {
                    magCount = magCap;
                }
                stack.getOrCreateNbt().putInt("magCount", magCount);
                if (magCount > 0) {
                    if ("shotgun".equals(type)) {
                        Random rnd = new Random();
                        int n = rnd.nextInt(3, 6);
                        for (int r = 0; r <= n; r++) {
                            //spawn bullets
                            int r1 = rnd.nextInt(2);
                            int r2 = rnd.nextInt(2);
                            if (r2 == 1) {
                                r2 = -r2;
                            }
                            if (r1 == 1) {
                                r1 = -r1;
                            }
                            BulletEntity sGBE = new BulletEntity(world, this);
                            sGBE.setPos(pl.getX(), pl.getY() + 0.75, pl.getZ());
                            sGBE.setVelocity(pl, pl.getPitch() + r1, pl.getYaw() + r2, 0.0F, 3.0F, 1.0F);
                            sGBE.setNoGravity(true);
                            world.spawnEntity(sGBE);
                        }
                    } else {
                        BulletEntity dBE = new BulletEntity(world, this);
                        dBE.setPos(pl.getX(), pl.getY() + 0.75, pl.getZ());
                        dBE.setVelocity(pl, pl.getPitch(), pl.getYaw(), 0.0F, 3.0F, 1.0F);
                        dBE.setNoGravity(true);
                        world.spawnEntity(dBE);
                    }
                    tick = 0;
                    //pl.getItemCooldownManager().set(this, shootCooldown);
                } else if(magCount < 0)
                {
                    magCount = 0;
                    stack.getOrCreateNbt().putInt("magCount", magCount);
                }
                if (i.contains(this.bul.getDefaultStack()) && !pl.getItemCooldownManager().isCoolingDown(this)) {
                    ItemStack bulletStack = i.getStack(i.getSlotWithStack(this.bul.getDefaultStack()));
                    if ((totalCount > 0) && stack.getNbt().getInt("magCount") == 0 && tick > reloadCooldown) {
                        reload(bulletStack, pl, stack);
                        tick = 0;
                    } else
                    {
                        System.out.println("cannot reload, :" + tick);
                    }
                }
            }
        }
    }

    public int getTC() {
        return this.totalCount;
    }

    public void reload(ItemStack bS, PlayerEntity i, ItemStack gunStack) {
        gunStack.getOrCreateNbt().putInt("magCount", Math.min(bS.getCount(), magCap));
        bS.decrement(gunStack.getNbt().getInt("magCount"));
    }
}