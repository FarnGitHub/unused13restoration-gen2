package io.bluestaggo.unused13restoration.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.locale.LanguageManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Properties;

@Mixin(LanguageManager.class)
public abstract class LanguageManagerMixin {
	@Inject(
		method = "setLanguage",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/locale/LanguageManager;loadTranslations(Ljava/util/Properties;Ljava/lang/String;)V",
			ordinal = 0
		)
	)
	private void addCustomLanguageEntries(String language, CallbackInfo ci, @Local Properties translations) {
		translations.put("item.delayEntitySpawnerWand.name", "Delay Entity Spawner Wand");
		translations.put("tile.coral.name", "Coral Block");
		translations.put("tile.infiniteLavaSource.name", "Infinite Lava Source");
		translations.put("tile.infiniteWaterSource.name", "Infinite Water Source");
	}
}
