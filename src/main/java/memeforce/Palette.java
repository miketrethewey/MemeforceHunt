package memeforce;

public enum Palette {
	GREEN ((byte) 0x04),
	BLUE ((byte) 0x02),
	RED ((byte) 0x01);

	public final byte b;

	private Palette(byte b) {
		this.b = b;
	}
}