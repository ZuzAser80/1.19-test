package net.fabricmc.example.entity.basic;

import net.fabricmc.example.item.GunItem;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BulletEntity extends PersistentProjectileEntity {

    //TRY MAKING THE SOURCE A DIFF CLASS MIXIN ACCESS NEEDED
    protected Item bullet;
    protected GunItem gunItem;
    public DamageSource bulletDamageSource = new ProjectileDamageSource("bulletSource", this, this.getOwner());

    public BulletEntity(World world, GunItem gun) {
        super(BasicRegistry.PistolBullet, world);
        gunItem = gun;
        bullet = gunItem.getBulletItem();
    }

    public void tick()
    {
        super.tick();
        if (world instanceof ServerWorld) {
            ((ServerWorld)world).spawnParticles(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0.0D);
        }
    }
    protected void onCollision(HitResult hitResult) {
        if(hitResult.getType() == HitResult.Type.BLOCK)
        {
            this.discard();
        } else if(hitResult.getType() == HitResult.Type.ENTITY)
        {
            //DamageSource b_s = new DamageSource().setProjectile().name;
            EntityHitResult entityHitResult = (EntityHitResult)hitResult;
            if(entityHitResult.getEntity() instanceof LivingEntity)
            {
                Entity entity = entityHitResult.getEntity();

                //System.out.println(gunItem.dmg);
                entity.damage(bulletDamageSource, gunItem.dmg);
                this.discard();
            }
        }
    }
    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(bullet);
    }
}
