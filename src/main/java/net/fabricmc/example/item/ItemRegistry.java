package net.fabricmc.example.item;

import jdk.jfr.Frequency;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.armor.KevlarArmorMaterial;
import net.fabricmc.example.armor.SpecnazArmorMaterial;
import net.fabricmc.example.entity.explosive.GrenadeItem;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    public static GunItem USP;
    public static ArmorItem KevlarHelm;
    public static ArmorItem KevlarChest;
    public static ArmorItem SpecnazHelm;
    public static ArmorItem SpecnazChest;
    public static Item HeGrenade;

    public static void registry()
    {
        //Armor Material(s)
        ArmorMaterial KevlarMaterial = new KevlarArmorMaterial();
        ArmorMaterial SpecnazMaterial = new SpecnazArmorMaterial();

        USP = Registry.register(Registry.ITEM, new Identifier("fbg", "usp"), new GunItem(new Item.Settings().maxCount(1), Items.STONE, 20, 7, "pistol", 20, 400));
        Registry.register(Registry.ITEM, new Identifier("fbg", "shotgun"), new GunItem(new Item.Settings().maxCount(1), Items.IRON_INGOT, 80, 8, "shotgun", 40, 600));
        //TODO: 3D KEVLAR PLASTINES MODELS?
        //Registry(s)
        //LOOK @ THE SHIELD WIKI DONT FORGOR
        HeGrenade = Registry.register(Registry.ITEM, new Identifier("fbg", "he_grenade"), new GrenadeItem(new Item.Settings().group(ExampleMod.FbgArmorGroup).maxCount(1).rarity(Rarity.UNCOMMON), "Smoke"));
        Registry.register(Registry.ITEM, new Identifier("fbg", "kevlar_big_plate"), new Item(new Item.Settings().group(ExampleMod.FbgArmorGroup)));
        Registry.register(Registry.ITEM, new Identifier("fbg", "swat_shield"), new BulletProofShieldItem(new Item.Settings()));
        KevlarHelm = Registry.register(Registry.ITEM, new Identifier("fbg", "kevlar_helmet"), new ArmorItem(KevlarMaterial, EquipmentSlot.HEAD, new Item.Settings().group(ExampleMod.FbgArmorGroup)));
        KevlarChest = Registry.register(Registry.ITEM, new Identifier("fbg", "kevlar_chest"), new ArmorItem(KevlarMaterial, EquipmentSlot.CHEST, new Item.Settings().group(ExampleMod.FbgArmorGroup)));
        SpecnazHelm = Registry.register(Registry.ITEM, new Identifier("fbg", "specnaz_helmet"), new ArmorItem(SpecnazMaterial, EquipmentSlot.HEAD, new Item.Settings().group(ExampleMod.FbgArmorGroup)));
        SpecnazChest = Registry.register(Registry.ITEM, new Identifier("fbg", "specnaz_chest"), new ArmorItem(SpecnazMaterial, EquipmentSlot.CHEST, new Item.Settings().group(ExampleMod.FbgArmorGroup)));
    }
}
