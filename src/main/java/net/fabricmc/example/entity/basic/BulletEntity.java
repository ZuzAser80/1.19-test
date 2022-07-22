package net.fabricmc.example.entity.basic;

import net.fabricmc.example.entity.bulletHole.BulletHoleEntity;
import net.fabricmc.example.entity.bulletHole.BulletHoleRegistry;
import net.fabricmc.example.item.GunItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;
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
    protected PlayerEntity player;
    protected boolean spawnedHole;
    protected DefaultParticleType particle;
    public DamageSource bulletDamageSource = new ProjectileDamageSource("bulletSource", this, this.getOwner());

    public BulletEntity(World world, GunItem gun, PlayerEntity pl) {
        super(BasicRegistry.PistolBullet, world);
        gunItem = gun;
        bullet = gunItem.getBulletItem();
        player = pl;
        this.particle = gun.particle;
    }

    public void tick()
    {
        super.tick();
        if (world instanceof ServerWorld) {
            ((ServerWorld)world).spawnParticles(particle, this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0.0D);
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
                BulletHoleEntity arrow = new BulletHoleEntity(BulletHoleRegistry.bulletHoleEntityType, world);
                arrow.setPos(this.getX(), this.getY(), this.getZ());
                arrow.setPitch(this.getPitch());
                //arrow.setYaw(player.getYaw());
                arrow.setVelocity(this, this.getPitch(), this.getYaw(), 0, 0, 0);
                world.spawnEntity(arrow);
            }
            this.discard();
        } else if(hitResult.getType() == HitResult.Type.ENTITY)
        {
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
