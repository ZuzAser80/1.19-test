package net.fabricmc.example.entity.bullet_hole;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BulletHoleEntity extends PersistentProjectileEntity {
    public int ageD;
    public BulletHoleEntity(World world, int ageInTicks) {
        super(BulletHoleRegistry.HoleType, world);
        ageD = ageInTicks;
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }
}
