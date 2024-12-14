package net.dimensionred.fouls;

import net.dimensionred.fouls.client.particle.FoulsParticleTypes;
import net.dimensionred.fouls.item.FoulsCreativeTabs;
import net.dimensionred.fouls.item.FoulsItems;
import net.dimensionred.fouls.misc.FoulsComposting;
import net.dimensionred.fouls.potion.FoulsPotions;
import net.dimensionred.fouls.potion.FoulsStatusEffects;
import net.dimensionred.fouls.world.gen.FoulsWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fouls implements ModInitializer {
	public static final String MOD_ID = "fouls";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("- This is a logger for Foul Service -");
		FoulsStatusEffects.setup();
		FoulsItems.setup();
		FoulsCreativeTabs.setup();
		FoulsEvents.registerEvents();
		FoulsComposting.setup();
		FoulsParticleTypes.setup();
		FoulsPotions.setup();

		FoulsWorldGeneration.genWorldgen();


		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.WATER, FoulsItems.PALE_FLOWER, FoulsPotions.FRAGRANT);
			builder.registerPotionRecipe(FoulsPotions.FRAGRANT, FoulsItems.GREEN_FRUIT, FoulsPotions.NAUSEA);
			builder.registerPotionRecipe(FoulsPotions.NAUSEA, Items.REDSTONE, FoulsPotions.LONG_NAUSEA);
			builder.registerPotionRecipe(FoulsPotions.FRAGRANT, FoulsItems.PALE_OAK_BRANCH, FoulsPotions.OAKLING);
			builder.registerPotionRecipe(FoulsPotions.OAKLING, Items.REDSTONE, FoulsPotions.LONG_OAKLING);

		});

		FlammableBlockRegistry.getDefaultInstance().add(FoulsItems.FLOWERING_PALE_OAK_LEAVES, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(FoulsItems.GREEN_FRUIT_LEAVES, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(FoulsItems.SWEET_FRUIT_LEAVES, 30, 60);


	}


}