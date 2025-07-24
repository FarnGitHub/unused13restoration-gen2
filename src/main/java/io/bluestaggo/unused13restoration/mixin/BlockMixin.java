package io.bluestaggo.unused13restoration.mixin;

import io.bluestaggo.unused13restoration.InfiniteLiquidSourceBlock;
import io.bluestaggo.unused13restoration.Unused13Restoration;
import net.minecraft.block.Block;
import net.minecraft.item.CreativeModeTab;
import net.minecraft.unmapped.C_7674817;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {
	@Inject(
		method = "<clinit>",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/block/Block;JUNGLE_STAIRS:Lnet/minecraft/block/Block;",
			ordinal = 0
		)
	)
	private static void registerCustomBlocks(CallbackInfo ci) {
		Block coral = Unused13Restoration.coral = new C_7674817(137, 26);
		coral.setKey("coral");
		coral.sound = Block.STONE_SOUND;
		coral.setCreativeModeTab(CreativeModeTab.DECORATIONS);

		Block infiniteWaterSource = Unused13Restoration.infiniteWaterSource = new InfiniteLiquidSourceBlock(138, Block.WATER.id);
		infiniteWaterSource.setKey("infiniteWaterSource");
		infiniteWaterSource.sound = Block.STONE_SOUND;
		infiniteWaterSource.setCreativeModeTab(CreativeModeTab.DECORATIONS);

		Block infiniteLavaSource = Unused13Restoration.infiniteLavaSource = new InfiniteLiquidSourceBlock(139, Block.LAVA.id);
		infiniteLavaSource.setKey("infiniteLavaSource");
		infiniteLavaSource.sound = Block.STONE_SOUND;
		infiniteLavaSource.setCreativeModeTab(CreativeModeTab.DECORATIONS);
	}
}
