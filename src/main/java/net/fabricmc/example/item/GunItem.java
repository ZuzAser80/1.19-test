package net.fabricmc.example.item;

import net.fabricmc.example.entity.basic.BulletEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

import java.util.Random;

public class GunItem extends Item {
    public int magCap;
    public int dmg;
    public String type;
    int totalCount = 0;
    Item bul;
    int reloadCooldown;
    PlayerEntity pl;
    boolean shoot;
    boolean reload;
    public boolean inAttachmentMode;
    boolean spawnMultipleBulletsPerShot;
    public DefaultParticleType particle;
    public AttachmentModeIndex attachmentModeIndex = AttachmentModeIndex.OTHER;

    //TODO: Rethink the models and shit
    public GunItem(Settings settings, Item bulletItem, int dmg, int magCap, String gunType, boolean spawnMultipleBulletsPerShot, DefaultParticleType particleType) {
        super(settings);
        bul = bulletItem;
        this.dmg = dmg;
        type = gunType;
        particle = particleType;
        this.magCap = magCap;
        reloadCooldown = 200;
        this.spawnMultipleBulletsPerShot = spawnMultipleBulletsPerShot;
    }

    public Item getBulletItem() {
        return bul;
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        int magCount;
        pl = (PlayerEntity) entity;
        PlayerInventory i = pl.getInventory();
        totalCount = i.count(this.bul);
        NbtCompound nbt = stack.getOrCreateNbt();
        if (selected && MinecraftClient.getInstance().mouse.wasLeftButtonClicked() && shoot) {
            magCount = nbt.getInt("magCount");
            if (!world.isClient) {
                if (!pl.isCreative()) {
                    magCount--;
                } else
                {
                    magCount = magCap;
                }
                stack.getOrCreateNbt().putInt("magCount", magCount);
                if (magCount > 0) {
                    if (spawnMultipleBulletsPerShot) {
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
                            BulletEntity sGBE = new BulletEntity(world, this, pl);
                            sGBE.setPos(pl.getX(), pl.getY() + 0.75, pl.getZ());
                            sGBE.setVelocity(pl, pl.getPitch() + r1, pl.getYaw() + r2, 0.0F, 3.0F, 1.0F);
                            sGBE.setNoGravity(true);
                            world.spawnEntity(sGBE);
                        }
                    } else {
                        BulletEntity dBE = new BulletEntity(world, this, pl);
                        dBE.setPos(pl.getX(), pl.getY() + 0.75, pl.getZ());
                        dBE.setVelocity(pl, pl.getPitch(), pl.getYaw(), 0.0F, 3.0F, 1.0F);
                        dBE.setNoGravity(true);
                        world.spawnEntity(dBE);
                    }
                    //pl.getItemCooldownManager().set(this, shootCooldown);
                } else if(magCount < 0)
                {
                    magCount = 0;
                    stack.getOrCreateNbt().putInt("magCount", magCount);
                }
                shoot = false;
            }
        }
        if (i.contains(this.bul.getDefaultStack())) {
            ItemStack bulletStack = i.getStack(i.getSlotWithStack(this.bul.getDefaultStack()));
            if (reload) {
                reload(bulletStack, stack);
            }
        }
    }

    public void shoot()
    {
        shoot = true;
    }
    public void reload()
    {
        reload = true;
    }

    public int getTC() {
        return this.totalCount;
    }

    public void reload(ItemStack bS, ItemStack gunStack) {
        gunStack.getOrCreateNbt().putInt("magCount", Math.min(bS.getCount(), magCap));
        bS.decrement(gunStack.getNbt().getInt("magCount"));
        reload = false;
    }
}