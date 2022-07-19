package net.fabricmc.example.entity.basic;

import net.fabricmc.example.entity.bullet_hole.BulletHoleEntity;
import net.fabricmc.example.item.GunItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.registry.Registry;
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
            BlockHitResult blockHitResult = (BlockHitResult)hitResult;
            if(world.getBlockState(blockHitResult.getBlockPos()).isIn(TagKey.of(Registry.BLOCK_KEY, new Identifier("fbg", "bullet_destructible")))) {
               world.breakBlock(blockHitResult.getBlockPos(), false);
            } else
            {
                if (!world.isClient) {
                    BulletHoleEntity holeEntity = new BulletHoleEntity(world, 72000);
                    holeEntity.setPos(this.getX(), this.getY(), this.getZ());
                    holeEntity.setVelocity(blockHitResult.getPos());
                    holeEntity.setNoGravity(true);
                    world.spawnEntity(holeEntity);
                }
            }
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
