package memeforce;

public enum Palette {
	GREEN ((byte) 0x08),
	BLUE ((byte) 0x04),
	RED ((byte) 0x02);

	public final byte b;

	private Palette(byte b) {
		this.b = b;
	}
}