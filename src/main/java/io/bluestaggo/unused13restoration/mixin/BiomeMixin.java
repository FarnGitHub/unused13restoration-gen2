package io.bluestaggo.unused13restoration.mixin;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.RainforestBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Biome.class)
public class BiomeMixin {
	@Inject(
		method = "<clinit>",
		at = @At("TAIL")
	)
	private static void registerCustomBiomes(CallbackInfo ci) {
		Biome rainforest = new RainforestBiome(21);
		rainforest.baseColor = 5470985;
		rainforest.name = "Rainforest";
		rainforest.mutatedColor = 5470985;
		rainforest.temperature = 1.2f;
		rainforest.downfall = 0.9f;
		rainforest.baseHeight = 0.2f;
		rainforest.heightVariation = 0.4f;

		Biome rainforestHills = new RainforestBiome(22);
		rainforestHills.baseColor = 2900485;
		rainforestHills.name = "RainforestHills";
		rainforestHills.mutatedColor = 5470985;
		rainforestHills.temperature = 1.2f;
		rainforestHills.downfall = 0.9f;
		rainforestHills.baseHeight = 1.8f;
		rainforestHills.heightVariation = 0.5f;
	}
}
