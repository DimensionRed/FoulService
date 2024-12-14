package net.dimensionred.fouls.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ExampleMixin {


//    @Unique
//    private static final DyeColor PALE = createNewColor(16, "pale", 16701501, MapColor.YELLOW, 14602026, 16776960);
//
//    @Inject(method = "<clinit>", at = @At("TAIL"))
//    private static void addCustomDyeColor(CallbackInfo ci) {
//        try {
//            Field valuesField = DyeColor.class.getDeclaredField("$VALUES");
//            valuesField.setAccessible(true);
//
//            DyeColor[] currentValues = (DyeColor[]) valuesField.get(null);
//            DyeColor[] newValues = Arrays.copyOf(currentValues, currentValues.length + 1);
//            newValues[newValues.length - 1] = PALE;
//
//            valuesField.set(null, newValues);
//        } catch (ReflectiveOperationException e) {
//            throw new RuntimeException("Failed to add custom DyeColor", e);
//        }
//    }
//
//    private static DyeColor createNewColor(int id, String name, int entityColor, MapColor mapColor, int fireworkColor, int signColor) {
//        try {
//            Constructor<DyeColor> constructor = DyeColor.class.getDeclaredConstructor(int.class, String.class, int.class, MapColor.class, int.class, int.class);
//            constructor.setAccessible(true);
//            return constructor.newInstance(id, name, entityColor, mapColor, fireworkColor, signColor);
//        } catch (ReflectiveOperationException e) {
//            throw new RuntimeException("Failed to create new DyeColor constant", e);
//        }
//    }

}