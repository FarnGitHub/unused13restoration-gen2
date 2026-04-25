package io.bluestaggo.unused13restoration.mixin;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import net.minecraft.world.gen.feature.HouseFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(FlatChunkGenerator.class)
public class FlatChunkGeneratorMixin {
	@Shadow
	private World world;

	@Shadow
	private Random random;

	@Inject(
		method = "populateChunk",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/gen/structure/VillageStructure;place(Lnet/minecraft/world/World;Ljava/util/Random;II)Z"
		)
	)
	private void placeDevVillageHouses(ChunkSource source, int chunkX, int chunkZ, CallbackInfo ci) {
		Random random = this.world.setRandomSeed(chunkX >> 2, chunkZ >> 2, "NoTch VILaergesres".hashCode());
		if (random.nextInt(10) != 0) {
			return;
		}

		int x = chunkX * 16 + 8 + this.random.nextInt(16);
		int z = chunkZ * 16 + 8 + this.random.nextInt(16);
		new HouseFeature().place(this.world, this.random, x, 255, z);
	}
}
