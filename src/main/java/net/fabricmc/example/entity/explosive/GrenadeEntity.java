package net.fabricmc.example.entity.explosive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class GrenadeEntity extends PersistentProjectileEntity {

    ItemStack grenadeStack;
    GrenadeType type;
    int ageInTicks;

    public GrenadeEntity(World world, Item item, GrenadeType type, int ageToDissolve)
    {
        super(GrenadeRegistry.GrenadeType, world);
        grenadeStack = new ItemStack(item);
        this.type = type;
        ageInTicks = ageToDissolve;
    }

    public void tick()
    {
        super.tick();
        if(ageInTicks > 0)
        {
            ageInTicks--;
        }
        this.setRotation(this.getYaw() + random.nextFloat(), this.getPitch() + random.nextFloat());
        if (world instanceof ServerWorld) {
            ((ServerWorld)world).spawnParticles(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0.0D);
        }
    }
    protected void onCollision(HitResult hitResult) {
        Vec3d pos = hitResult.getPos();
        BlockPos blockPos = new BlockPos(pos);
        if(hitResult.getType() == HitResult.Type.BLOCK && world.getBlockState(blockPos).getBlock() != Blocks.AIR && world.getBlockState(blockPos).getBlock() != Blocks.WATER && world.getBlockState(blockPos).getBlock() != Blocks.LAVA) {
            this.setVelocity(-this.getVelocity().getX() * 0.8, -this.getVelocity().getY() * 0.75, -this.getVelocity().getZ() * 0.8);
        }
        if(!world.isClient() && ageInTicks > 0) {
            switch (type) {
                case SMOKE -> {
                    summonSmoke(blockPos);
                }
                case EXPLOSIVE -> {
                    world.createExplosion(this.getOwner(), pos.x, pos.y, pos.z, 5, Explosion.DestructionType.DESTROY);
                }
                case INCENDIARY -> {
                    world.createExplosion(this.getOwner(), pos.x, pos.y, pos.z, 0, true, Explosion.DestructionType.NONE);
                }
            }
        }
        this.discard();
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
