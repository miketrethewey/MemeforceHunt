package memeforce;

public enum Palette {
	GREEN ((byte) 0x08),
	BLUE ((byte) 0x08),
	RED ((byte) 0x08);

	public final byte b;

	private Palette(byte b) {
		this.b = b;
	}
}