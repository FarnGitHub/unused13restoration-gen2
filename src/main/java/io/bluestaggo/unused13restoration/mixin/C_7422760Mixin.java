package io.bluestaggo.unused13restoration.mixin;

import net.minecraft.unmapped.C_7422760;
import net.minecraft.world.storage.AnvilWorldStorageSource;
import net.minecraft.world.storage.WorldStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(C_7422760.class)
public abstract class C_7422760Mixin {
	@Shadow private WorldStorageSource f_0378177;
	@Shadow private File f_9903274;

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void useAnvilStorage(CallbackInfo ci) {
		this.f_0378177 = new AnvilWorldStorageSource(this.f_9903274);
	}
}
