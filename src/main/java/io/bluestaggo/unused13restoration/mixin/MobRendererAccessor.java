package io.bluestaggo.unused13restoration.mixin;

import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.model.Model;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobRenderer.class)
public interface MobRendererAccessor {
	@Accessor
	Model getModel();
}
