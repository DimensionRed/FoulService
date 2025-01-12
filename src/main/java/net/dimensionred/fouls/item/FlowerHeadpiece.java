package net.dimensionred.fouls.item;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.client.render.FlowerHeadpieceRenderer;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FlowerHeadpiece extends ArmorItem implements GeoItem {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public FlowerHeadpiece(ArmorMaterial material, EquipmentType type, Settings settings) {
        super(material, type, settings);

    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public <E extends LivingEntity, S extends BipedEntityRenderState> @NotNull BipedEntityModel<?> getGeoArmorRenderer(@Nullable E livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, EquipmentModel.LayerType type, BipedEntityModel<S> original) {
                //return GeoRenderProvider.super.getGeoArmorRenderer(livingEntity, itemStack, equipmentSlot, type, original);
                if(this.renderer == null)
                    this.renderer = new FlowerHeadpieceRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, 20, state -> {
            state.getController().setAnimation(DefaultAnimations.IDLE);
            Entity entity = state.getData(DataTickets.ENTITY);

            if (entity instanceof ArmorStandEntity)
                return PlayState.CONTINUE;

            Set<Item> wornArmor = new ObjectOpenHashSet<>();


            if(entity instanceof LivingEntity)
                {
                    for (ItemStack stack : ((LivingEntity) entity).getArmorItems()){
                    if (stack.isEmpty())
                        return PlayState.STOP;

                    wornArmor.add(stack.getItem());
                }

                boolean isFullSet = wornArmor.containsAll(ObjectArrayList.of(
                        FoulsItems.FLOWER_HEADPIECE
                ));

                return isFullSet ? PlayState.CONTINUE : PlayState.STOP;
                }
            return PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


}
