package io.bluestaggo.unused13restoration.mixin;

import io.bluestaggo.unused13restoration.FileSprite;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Shadow public TextureManager textureManager;

	@Inject(
		method = "init",
		at = @At("TAIL")
	)
	private void registerCustomSprites(CallbackInfo ci) {
		this.textureManager.addSprite(new FileSprite(26, 0,
			this.textureManager.getColors("/assets/unused13restoration/textures/block/coral.png")));
	}
}
