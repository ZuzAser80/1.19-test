package net.fabricmc.example;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class FBGServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {

    }
    public void summonServerEntity(Entity entity, World world)
    {
        world.spawnEntity(entity);
    }
}
