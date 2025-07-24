package io.bluestaggo.unused13restoration.mixin;

import io.bluestaggo.unused13restoration.DelayEntitySpawnerWandItem;
import io.bluestaggo.unused13restoration.Unused13Restoration;
import net.minecraft.item.CreativeModeTab;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin {
	@Inject(
		method = "<clinit>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/stat/Stats;initItemStats()V"
		)
	)
	private static void registerCustomItems(CallbackInfo ci) {
		Unused13Restoration.delayEntitySpawnerWand = new DelayEntitySpawnerWandItem(133)
			.setSprite(12, 6)
			.setKey("delayEntitySpawnerWand")
			.setCreativeModeTab(CreativeModeTab.TOOLS);
	}
}
