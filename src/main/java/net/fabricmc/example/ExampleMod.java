package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.entity.basic.BasicRegistry;
import net.fabricmc.example.entity.bullet_hole.BulletHoleRegistry;
import net.fabricmc.example.entity.bullet_hole.BulletHoleRenderer;
import net.fabricmc.example.entity.explosive.GrenadeRegistry;
import net.fabricmc.example.item.ItemRegistry;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
		ItemRegistry.registry();
		BasicRegistry.registry();
		GrenadeRegistry.registry();
		BulletHoleRegistry.registry();
	}
}
