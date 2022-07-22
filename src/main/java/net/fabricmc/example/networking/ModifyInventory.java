package net.fabricmc.example.networking;

import io.netty.buffer.Unpooled;
import net.fabricmc.example.item.AttachmentModeIndex;
import net.fabricmc.example.item.GunItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.function.Predicate;

public class ModifyInventory {

    public static KeyBinding attachmentMode;
    public static KeyBinding scopeKey;
    public static KeyBinding barrelKey;

    public static void registryClient()
    {
        attachmentMode = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fbg.attachment_mode",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                "category.fbg.main"
        ));
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(attachmentMode.wasPressed()) {
                client.getNetworkHandler().sendPacket(createAttachmentModePacket());
            }
        });
    }
    public static void registerUpKey()
    {
        scopeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fbg.scope",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UP,
                "category.fbg.main"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(scopeKey.wasPressed()) {
                client.getNetworkHandler().sendPacket(createScopePacket());
            }
        });
    }

    public static void registerLeftKey()
    {
        barrelKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fbg.barrel",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT,
                "category.fbg.main"
        ));
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(barrelKey.wasPressed()) {
                client.getNetworkHandler().sendPacket(createBarrelPacket());
            }
        });
    }

    static TagKey<Item> scopes = TagKey.of(Registry.ITEM_KEY, new Identifier("fbg", "scope"));
    static TagKey<Item> barrel = TagKey.of(Registry.ITEM_KEY, new Identifier("fbg", "barrel"));

    public static void onAttachmentModePacket(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender) {
        server.execute(() -> {
            PlayerInventory i = player.getInventory();
            if(i.getMainHandStack().getItem() instanceof GunItem test)
            {
                if(test.attachmentModeIndex.equals(AttachmentModeIndex.IN_PLACE)) {
                    test.attachmentModeIndex = AttachmentModeIndex.GOING_DOWN;
                }
                if(!test.inAttachmentMode) {
                    test.attachmentModeIndex = AttachmentModeIndex.GOING_UP;
                } else {
                    test.attachmentModeIndex = AttachmentModeIndex.GOING_DOWN;

                    if(!player.isCreative()) {
                        i.removeOne(nbtDataThing);
                        i.insertStack(previousScopeStack);
                        previousScopeStack = nbtDataThing;

                        i.removeOne(barrelStack);
                        i.insertStack(previousBarrelStack);
                        previousBarrelStack = barrelStack;
                    }
                }
                test.inAttachmentMode = !test.inAttachmentMode;
            }
        });
    }

    static int scopeListIndex = 0;
    static List<ItemStack> scopeList = null;
    static ItemStack nbtDataThing = new ItemStack(Items.AIR);
    static ItemStack previousScopeStack = new ItemStack(Items.AIR);

    public static void onScopePacket(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender)
    {
        server.execute(() -> {
            PlayerInventory i = player.getInventory();
            if(i.getMainHandStack().getItem() instanceof GunItem GunItem) {
                if (i.contains(scopes)) {
                    scopeList = i.main.stream().filter(stack -> stack.isIn(scopes)).toList();
                }
                if (GunItem.inAttachmentMode) {
                    if (scopeList != null) {
                        if (scopeListIndex < scopeList.size()) {
                            nbtDataThing = scopeList.get(scopeListIndex);
                            if(!i.getMainHandStack().getOrCreateNbt().getString("scope").equals("") && previousScopeStack.isOf(Items.AIR)) {
                                System.out.println("we had: " + i.getMainHandStack().getOrCreateNbt().getString("scope").equals(""));
                                previousScopeStack = new ItemStack(Registry.ITEM.get(new Identifier("fbg", i.getMainHandStack().getOrCreateNbt().getString("scope").replaceFirst("item.fbg.", ""))));
                            }
                            i.getMainHandStack().getOrCreateNbt().putString("scope", nbtDataThing.getItem().getTranslationKey());
                            scopeListIndex++;
                        } else {
                            if (i.getMainHandStack().getItem() instanceof GunItem) {
                                i.getMainHandStack().getOrCreateNbt().putString("scope", "");
                            } else {
                                i.getMainHandStack().getOrCreateNbt().putString("scope", nbtDataThing.getTranslationKey());
                            }
                            scopeListIndex = 0;
                        }
                    }
                    if (!i.getMainHandStack().getOrCreateNbt().getString("scope").equals("") && (scopeList == null)) {
                        nbtDataThing = new ItemStack(Items.AIR);
                        previousScopeStack = new ItemStack(Registry.ITEM.get(new Identifier("fbg", i.getMainHandStack().getOrCreateNbt().getString("scope").replaceFirst("item.fbg.", ""))));
                        i.getMainHandStack().getOrCreateNbt().putString("scope", "");
                    }
                }
            }
        });
    }

    static int barrelListIndex = 0;
    static List<ItemStack> barrelList = null;
    static ItemStack barrelStack = new ItemStack(Items.AIR);
    static ItemStack previousBarrelStack = new ItemStack(Items.AIR);

    public static void onBarrelPacket(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender) {
        server.execute(() -> {
            PlayerInventory i = player.getInventory();
            if(i.getMainHandStack().getItem() instanceof GunItem GunItem) {
                if (i.contains(barrel)) {
                    barrelList = i.main.stream().filter(stack -> stack.isIn(barrel)).toList();
                }
                if (GunItem.inAttachmentMode) {
                    if (barrelList != null) {
                        if (barrelListIndex < barrelList.size()) {
                            barrelStack = barrelList.get(barrelListIndex);
                            i.getMainHandStack().getOrCreateNbt().putString("barrel", barrelStack.getItem().getTranslationKey());
                            barrelListIndex++;
                        } else {
                            if (i.getMainHandStack().getItem() instanceof GunItem) {
                                i.getMainHandStack().getOrCreateNbt().putString("barrel", "");
                            } else {
                                i.getMainHandStack().getOrCreateNbt().putString("barrel", barrelStack.getItem().getTranslationKey());
                            }
                            barrelListIndex = 0;
                        }
                    }
                    if (!i.getMainHandStack().getOrCreateNbt().getString("barrel").equals("") && (barrelList == null || barrelList.isEmpty())) {
                        previousBarrelStack = new ItemStack(Registry.ITEM.get(new Identifier("fbg", i.getMainHandStack().getOrCreateNbt().getString("barrel").replaceFirst("item.fbg.", ""))));
                        barrelStack = Items.AIR.getDefaultStack();
                        i.getMainHandStack().getOrCreateNbt().putString("barrel", "");
                    }
                }
            }
        });
    }

    public static Packet<?> createAttachmentModePacket() {
        PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());
        return ClientPlayNetworking.createC2SPacket(new Identifier("fbg", "inventory_server_packet"), buffer);
    }
    public static Packet<?> createScopePacket() {
        PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());
        return ClientPlayNetworking.createC2SPacket(new Identifier("fbg", "scope_server_packet"), buffer);
    }
    public static Packet<?> createBarrelPacket() {
        PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());
        return ClientPlayNetworking.createC2SPacket(new Identifier("fbg", "barrel_server_packet"), buffer);
    }
}
