package net.fabricmc.example.networking;

import net.fabricmc.example.item.Test01;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class KeybindRegistry {

    public static KeyBinding attachmentMode;
    public static KeyBinding scopeKey;
    public static KeyBinding barrelKey;
    static TagKey<Item> scopes = TagKey.of(Registry.ITEM_KEY, new Identifier("fbg", "scope"));
    static TagKey<Item> barrel = TagKey.of(Registry.ITEM_KEY, new Identifier("fbg", "barrel"));


    public static void registryClient()
    {
        attachmentMode = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fbg.attachment_mode",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                "category.fbg.main"
        ));
        //keyBinding.wasPressed()
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(attachmentMode.wasPressed() && (client.player.getMainHandStack().getItem() instanceof net.fabricmc.example.item.Test01 test /*client.player.getMainHandStack().getItem() instanceof GunItem*/)) {
                //Putting the gun down
                if(test.attachmentModeIndex != null && test.attachmentModeIndex.equals("inPlace"))
                {
                    test.attachmentModeIndex = "goingDown";
                }
                if(!test.inAttachmentMode)
                {
                    test.attachmentModeIndex = "goingUp";
                } else
                {
                    test.attachmentModeIndex = "goingDown";

                    client.player.getInventory().removeOne(nbtDataThing);
                    client.player.getInventory().insertStack(previousScopeStack);

                    client.player.getInventory().removeOne(barrelStack);
                    client.player.getInventory().insertStack(previousBarrelStack);

                }
                //switching
                test.inAttachmentMode = !test.inAttachmentMode;
            }
        });
    }
    static int listIndex = 0;
    static List<ItemStack> list = null;
    static ItemStack nbtDataThing = new ItemStack(Items.AIR);
    static ItemStack previousScopeStack = new ItemStack(Items.AIR);
    public static void registerUpKey()
    {
        scopeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fbg.scope",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UP,
                "category.fbg.main"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.player != null && client.player.getMainHandStack().getItem() instanceof Test01 test01)
            {
                PlayerInventory i = client.player.getInventory();
                if(i.contains(scopes)) {
                    list = client.player.getInventory().main.stream().filter(stack -> stack.isIn(scopes)).toList();
                }
                if(test01.inAttachmentMode && scopeKey.wasPressed() ) {
                    if (list != null) {
                        if (listIndex < list.size()) {
                            nbtDataThing = list.get(listIndex);
                            client.player.getMainHandStack().getOrCreateNbt().putString("scope", nbtDataThing.getItem().getTranslationKey());
                            listIndex++;
                        } else {
                            client.player.getMainHandStack().getOrCreateNbt().putString("scope", "");
                            previousScopeStack = nbtDataThing;
                            listIndex = 0;
                        }
                    } else if (!client.player.getMainHandStack().getOrCreateNbt().getString("scope").equals("") && list == null)
                    {
                        previousScopeStack = new ItemStack(Registry.ITEM.get(new Identifier("fbg", client.player.getMainHandStack().getOrCreateNbt().getString("scope").replaceFirst("item.fbg.", ""))));
                    }
                }
            }
        });
    }
    static int barrelListIndex = 0;
    static List<ItemStack> barrelList = null;
    static ItemStack barrelStack = new ItemStack(Items.AIR);
    static ItemStack previousBarrelStack = new ItemStack(Items.AIR);
    public static void registerLeftKey()
    {
        barrelKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fbg.barrel",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT,
                "category.fbg.main"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.player != null && client.player.getMainHandStack().getItem() instanceof Test01 test01)
            {
                PlayerInventory i = client.player.getInventory();
                if(i.contains(barrel))
                {
                    barrelList = i.main.stream().filter(stack -> stack.isIn(barrel)).toList();
                }
                if(test01.inAttachmentMode && barrelKey.wasPressed()) {
                    if (barrelList != null) {
                        if (barrelListIndex < barrelList.size()) {
                            barrelStack = barrelList.get(barrelListIndex);
                            client.player.getMainHandStack().getOrCreateNbt().putString("barrel", barrelStack.getItem().getTranslationKey());
                            barrelListIndex++;

                        } else {
                            //for default state
                            client.player.getMainHandStack().getOrCreateNbt().putString("barrel", "");
                            previousBarrelStack = barrelStack;
                            barrelListIndex = 0;
                        }
                    }/* else if (!client.player.getMainHandStack().getOrCreateNbt().getString("barrel").equals("") && list == null) {
                        previousBarrelStack = new ItemStack(Registry.ITEM.get(new Identifier("fbg", client.player.getMainHandStack().getOrCreateNbt().getString("barrel").replaceFirst("item.fbg.", ""))));
                        client.player.getMainHandStack().getOrCreateNbt().putString("barrel", "");
                    }*/
                }
            }
        });
    }
}
