package net.dimensionred.fouls.advancement;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.item.FoulsItems;
import net.dimensionred.fouls.potion.FoulsStatusEffects;
import net.dimensionred.fouls.world.biome.FoulsBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class FoulsAdvancements extends FabricAdvancementProvider{


    public FoulsAdvancements(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }



    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {


//        Map<String, AdvancementCriterion<?>> criteria = Map.of(
//                "has_dirt", Criteria.INVENTORY_CHANGED.create(InventoryChangedCriterion.Conditions.items(FoulsItems.PALE_FLOWER).conditions())
//
//        );
        RegistryEntry<Biome> orchardBiome = wrapperLookup.getOrThrow(RegistryKeys.BIOME)
                .getOrThrow(FoulsBiomes.PALE_ORCHARD_BIOME);


        AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(
                        FoulsItems.PALE_FLOWER,
                        Text.translatable("advancement.fouls.root.name"),
                        Text.translatable("advancement.fouls.root.desc"),
                        Identifier.of("minecraft","textures/gui/advancements/backgrounds/adventure.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )

                .criterion("got_dirt", InventoryChangedCriterion.Conditions.items(Items.DIRT))
                .build(consumer, Fouls.MOD_ID + "/root");


        AdvancementEntry orchardAdvancement = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        FoulsItems.FLOWERING_PALE_OAK_LEAVES,
                        Text.translatable("advancement.fouls.orchard.name"),
                        Text.translatable("advancement.fouls.orchard.desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(10))
                .criterion("found_orchard", TickCriterion.Conditions.createLocation(LocationPredicate.Builder.createBiome(orchardBiome)))
                .build(consumer, Fouls.MOD_ID + "/found_orchard");

        AdvancementEntry thornsAdvancement = Advancement.Builder.create().parent(orchardAdvancement)
                .display(
                        FoulsItems.THORNS,
                        Text.translatable("advancement.fouls.thorns.name"),
                        Text.translatable("advancement.fouls.thorns.desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(10))
                .criterion("hit_by_thorns", EnterBlockCriterion.Conditions.block(FoulsItems.THORNS))
                .build(consumer, Fouls.MOD_ID + "/hit_by_thorns");

        AdvancementEntry greenFruitAdvancement = Advancement.Builder.create().parent(orchardAdvancement)
                .display(
                        FoulsItems.GREEN_FRUIT,
                        Text.translatable("advancement.fouls.green_fruit.name"),
                        Text.translatable("advancement.fouls.green_fruit.desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("got_green_fruit", InventoryChangedCriterion.Conditions.items(FoulsItems.GREEN_FRUIT))
                .build(consumer, Fouls.MOD_ID + "/got_green_fruit");
        AdvancementEntry sweetFruitAdvancement = Advancement.Builder.create().parent(orchardAdvancement)
                .display(
                        FoulsItems.SWEET_FRUIT,
                        Text.translatable("advancement.fouls.sweet_fruit.name"),
                        Text.translatable("advancement.fouls.sweet_fruit.desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("got_sweet_fruit", InventoryChangedCriterion.Conditions.items(FoulsItems.SWEET_FRUIT))
                .build(consumer, Fouls.MOD_ID + "/got_sweet_fruit");

        AdvancementEntry oaklingAdvancement = Advancement.Builder.create().parent(orchardAdvancement)
                .display(
                        FoulsItems.PALE_OAK_BRANCH,
                        Text.translatable("advancement.fouls.oakling.name"),
                        Text.translatable("advancement.fouls.oakling.desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR)
                .criterion("got_oakling", EffectsChangedCriterion.Conditions.create(EntityEffectPredicate.Builder.create().addEffect(FoulsStatusEffects.OAKLING)))
                .criterion("got_long_oakling", EffectsChangedCriterion.Conditions.create(EntityEffectPredicate.Builder.create().addEffect(FoulsStatusEffects.LONG_OAKLING)))
                .build(consumer, Fouls.MOD_ID + "/got_oakling");

    }




}

