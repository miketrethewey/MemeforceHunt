package com.alttp.sprite.memeforcehunt.lib;

public enum Palette {
	GREEN ((byte) 0x04, (byte) 0x08),
	BLUE ((byte) 0x02, (byte) 0x04),
	RED ((byte) 0x01, (byte) 0x02);

	public final byte b;
	public final byte bOW;

	private Palette(byte b, byte bOW) {
		this.b = b;
		this.bOW = bOW;
	}
}
