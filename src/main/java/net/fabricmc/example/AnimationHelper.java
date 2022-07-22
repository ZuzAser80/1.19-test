package net.fabricmc.example;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

import java.util.HashSet;
import java.util.Set;

public class AnimationHelper {

    private final static Set<AnimationHelper> animations = new HashSet<>();

    private boolean isPlaying;
    private int animationLength;
    private int ticks;
    private final float offsetPerTick;
    private float offset;

    private AnimationHelper(int animationTicks, float offset, float offPerT) {
        this.isPlaying = true;
        this.animationLength = animationTicks;
        this.offsetPerTick = offPerT;
        this.offset = offset;
    }

    public static AnimationHelper begin(int animationTicks, float offPerT, float offset)
    {
        AnimationHelper animation = new AnimationHelper(animationTicks, offset, offPerT);
        animations.add(animation);
        return animation;
    }

    @Environment(EnvType.CLIENT)
    public static void eventClient()
    {
        ClientTickEvents.END_CLIENT_TICK.register((client) ->
        {
            Set<AnimationHelper> toRemove = new HashSet<>();
            animations.forEach((animation) -> {
                if(!animation.isPlaying && animation.ticks != 0)
                {
                    animation.ticks = 0;
                    toRemove.add(animation);
                }
                if(animation.isPlaying) {
                    if (animation.ticks <= animation.animationLength) {
                        animation.offset += animation.offsetPerTick;
                    } else {
                        animation.isPlaying = false;
                    }
                    animation.ticks++;
                }
            });
            if(!toRemove.isEmpty()) {
                animations.removeAll(toRemove);
            }
        });
    }

    public float getOffset()
    {
        return this.offset;
    }
    public boolean getIsPlaying()
    {
        return this.isPlaying;
    }

    public Quaternion getQuaternion() {
        return new Quaternion(Vec3f.POSITIVE_Z.getDegreesQuaternion(offset));
    }

    public void doTransform(MatrixStack matrices, float offsetX, float offsetY, float offsetZ) {
        matrices.translate(offsetX, offsetY, offsetZ);
    }
}
