package io.bluestaggo.unused13restoration.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.io.File;

@Mixin(Minecraft.class)
public interface MinecraftAccessor {
	@Invoker("takePanorama") String invokeTakePanorama(File file, int width, int height);
}
