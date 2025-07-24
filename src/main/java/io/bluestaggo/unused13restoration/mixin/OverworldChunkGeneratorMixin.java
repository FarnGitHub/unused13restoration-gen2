package io.bluestaggo.unused13restoration.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.unmapped.C_0041715;
import net.minecraft.unmapped.C_4926342;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.gen.Generator;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(OverworldChunkGenerator.class)
public class OverworldChunkGeneratorMixin {
	@Shadow
	private World world;

	@Shadow
	private Random random;

	@Shadow
	private Generator cave;

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void useLegacyCaves(World world, long seed, boolean structures, CallbackInfo ci) {
		this.cave = new C_4926342();
	}

	@Inject(
		method = "populateChunk",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/gen/structure/VillageStructure;place(Lnet/minecraft/world/World;Ljava/util/Random;II)Z"
		)
	)
	private void placeDevVillageHouses(ChunkSource source, int chunkX, int chunkZ, CallbackInfo ci, @Local Biome biome) {
		if (biome == Biome.OCEAN || biome == Biome.FROZEN_OCEAN || biome == Biome.RIVER || biome == Biome.FROZEN_RIVER || biome == Biome.BEACH) {
			return;
		}

		Random random = this.world.setRandomSeed(chunkX >> 2, chunkZ >> 2, "NoTch VILaergesres".hashCode());
		if (random.nextInt(10) != 0) {
			return;
		}

		int x = chunkX * 16 + 8 + random.nextInt(16);
		int z = chunkZ * 16 + 8 + random.nextInt(16);
		new C_0041715().place(this.world, this.random, x, 255, z);
	}
}
