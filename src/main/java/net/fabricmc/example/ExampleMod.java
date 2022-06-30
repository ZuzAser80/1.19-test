package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.entity.basic.BasicRegistry;
import net.fabricmc.example.entity.explosive.GrenadeRegistry;
import net.fabricmc.example.item.ItemRegistry;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static ItemGroup FbgArmorGroup = FabricItemGroupBuilder.create(
			new Identifier("fbg", "fbg_armor_group"))
			.icon(() -> new ItemStack(Items.STONE))
			.build();
	public static final Logger LOGGER = LogManager.getLogger("FBG");
	@Override
	public void onInitialize() {


		//GO DO THE KEVLAR PLATES YOU DUMB FUCK
		///summon minecraft:zombie ~ ~ ~ {CanPickUpLoot:1b,Glowing:1b,NoAI:1b,Silent:1b}
		//Item FirstAidPack = new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(0).alwaysEdible().snack().statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 3, 1), 100).build()));
		//Registry.register(Registry.ITEM, new Identifier("fbg", "firstaidpack"), FirstAidPack);
		ItemRegistry.registry();
		BasicRegistry.registry();
		GrenadeRegistry.registry();


		//LOGGER.info("Hello Fabric world!");
	}
}
