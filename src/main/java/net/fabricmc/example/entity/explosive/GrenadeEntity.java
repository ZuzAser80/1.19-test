package net.fabricmc.example.entity.explosive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class GrenadeEntity extends PersistentProjectileEntity {

    ItemStack grenadeStack;
    int entityDamageOnHit;
    float power;
    boolean explode;
    boolean smoke;
    boolean fire;

    public GrenadeEntity(World world, Item item, int damage, float explosionPower, boolean explode, boolean smoke, boolean fire)
    {
        super(GrenadeRegistry.GrenadeType, world);
        grenadeStack = new ItemStack(item);
        entityDamageOnHit = damage;
        power = explosionPower;
        this.explode = explode;
        this.smoke = smoke;
        this.fire = fire;
    }

    public void tick()
    {
        super.tick();
        this.setRotation(this.getYaw() + random.nextFloat(), this.getPitch() + random.nextFloat());
        if (world instanceof ServerWorld) {
            ((ServerWorld)world).spawnParticles(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0.0D);
        }
    }
    protected void onCollision(HitResult hitResult) {
        if(hitResult.getType() == HitResult.Type.BLOCK) {
            Vec3d pos = hitResult.getPos();
            BlockPos blockPos = new BlockPos(pos);
            if(this.explode)
            {
                world.createExplosion(this.getOwner(), pos.x, pos.y, pos.z, power, Explosion.DestructionType.DESTROY);
            } else if(this.fire)
            {
                world.createExplosion(this.getOwner(), pos.x, pos.y, pos.z, power, true, Explosion.DestructionType.DESTROY);
            } else if(this.smoke)
            {
                if (!world.isClient()) {
                    summonSmoke(blockPos);
                }
            }
            this.discard();
        }
        else if(hitResult.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHitResult = (EntityHitResult)hitResult;
            if(entityHitResult.getEntity() instanceof LivingEntity entity) {
                entity.damage(DamageSource.explosion((LivingEntity)this.getOwner()), entityDamageOnHit);
                this.discard();
            }
        }
    }
    @Override
    protected ItemStack asItemStack() {
        return grenadeStack;
    }

    public void summonSmoke(BlockPos blockPos)
    {
        spawnBitSmoke(blockPos);

        spawnBitSmoke(blockPos.north(1));
        spawnBitSmoke(blockPos.west(1));
        spawnBitSmoke(blockPos.south(1));
        spawnBitSmoke(blockPos.east(1));
        spawnBitSmoke(blockPos.north(1).west(1));
        spawnBitSmoke(blockPos.south(1).west(1));
        spawnBitSmoke(blockPos.north(1).east(1));
        spawnBitSmoke(blockPos.south(1).east(1));

        spawnBitSmoke(blockPos.up(1));
        spawnBitSmoke(blockPos.up(1).north(1));
        spawnBitSmoke(blockPos.up(1).west(1));
        spawnBitSmoke(blockPos.up(1).south(1));
        spawnBitSmoke(blockPos.up(1).east(1));
        spawnBitSmoke(blockPos.up(1).north(1).west(1));
        spawnBitSmoke(blockPos.up(1).south(1).west(1));
        spawnBitSmoke(blockPos.up(1).north(1).east(1));
        spawnBitSmoke(blockPos.up(1).south(1).east(1));

        spawnBitSmoke(blockPos.up(2));
        spawnBitSmoke(blockPos.up(2).north(1));
        spawnBitSmoke(blockPos.up(2).west(1));
        spawnBitSmoke(blockPos.up(2).south(1));
        spawnBitSmoke(blockPos.up(2).east(1));
        spawnBitSmoke(blockPos.up(2).north(1).west(1));
        spawnBitSmoke(blockPos.up(2).south(1).west(1));
        spawnBitSmoke(blockPos.up(2).north(1).east(1));
        spawnBitSmoke(blockPos.up(2).south(1).east(1));

    }

    public void spawnBitSmoke(BlockPos pos)
    {
        ((ServerWorld) world).spawnParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, pos.getX(), pos.getY(), pos.getZ(), 10, 0.125, 0.125D, 0.125, 0.00125D);
    }
}
