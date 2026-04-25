package io.bluestaggo.unused13restoration.mixin;

import net.minecraft.unmapped.GeneratorProfiler;
import net.minecraft.world.storage.AnvilWorldStorageSource;
import net.minecraft.world.storage.WorldStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(GeneratorProfiler.class)
public abstract class GeneratorProfilerMixin {
	@Shadow private WorldStorageSource storageSource;
	@Shadow private File workingDirectory;

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void useAnvilStorage(CallbackInfo ci) {
		this.storageSource = new AnvilWorldStorageSource(this.workingDirectory);
	}
}
