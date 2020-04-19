package io.github.alttpj.memeforcehunt.common.value;

public enum ItemPalette {
	/**
	 * Gree palette id.
	 */
	GREEN((byte) 0x04, (byte) 0x08),
	BLUE((byte) 0x02, (byte) 0x04),
	RED((byte) 0x01, (byte) 0x02);

	/**
	 * If item is in chest, this palette ID is used.
	 */
	public final byte paletteIdChest;

	/**
	 * If item is lying around in the overworld, this palette ID is used.
	 */
	public final byte paletteIdOverworld;

	ItemPalette(final byte paletteIdChest, final byte paletteIdOverworld) {
		this.paletteIdChest = paletteIdChest;
		this.paletteIdOverworld = paletteIdOverworld;
	}
}
