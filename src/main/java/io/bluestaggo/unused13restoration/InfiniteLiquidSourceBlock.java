package io.bluestaggo.unused13restoration;

import net.minecraft.block.LegacyLiquidSourceBlock;

public class InfiniteLiquidSourceBlock extends LegacyLiquidSourceBlock {
	public InfiniteLiquidSourceBlock(int i, int j) {
		super(i, j);
		this.setTicksRandomly(true);
	}
}
