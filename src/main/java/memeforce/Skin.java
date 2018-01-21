package memeforce;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import static memeforce.Palette.*;

public enum Skin {
	BENANA ("benana", GREEN),
	THINKING ("thinking", GREEN),
	ONE_UP ("1up", GREEN),
	BIRB ("birb", GREEN),
	BOB ("bob", GREEN),
	BOOTS ("boots", RED),
	BROCCOLI ("broccoli", GREEN),
	CHEST ("chest", GREEN),
	CUCCO ("cucco", RED),
	DWARF ("dwarf", BLUE),
	FORK ("fork", GREEN),
	GLACEON ("glaceon", BLUE),
	ICE_ROD ("icerod", BLUE),
	LINK_FACE ("linkface", BLUE),
	QUACK ("quack", GREEN),
	SKULLTULA ("skulltula", GREEN),
	WOODFELLA ("woodfella", BLUE),
	Z1_LINK ("z1link", GREEN);

	private final String name;
	private final Palette p;
	private final byte[] data;
	private final BufferedImage preview;
	private final ImageIcon ico;

	private Skin(String s, Palette p) {
		name = s;
		this.p = p;
		InputStream temp;
		ArrayList<Byte> btemp = new ArrayList<Byte>();
		temp = Skin.class.getResourceAsStream(
				String.format("/gfx/%s.bin", s));
		int r = 0;
		do {
			try {
				r = temp.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (r == -1) { break; }
			btemp.add((byte) r);
		} while (r != -1);

		int l = btemp.size();
		int i = 0;
		data = new byte[l];
		for (Byte b : btemp) {
			data[i++] = b;
		}

		BufferedImage itemp;
		try {
			itemp = ImageIO.read(Skin.class.getResourceAsStream(
					String.format("/previews/%s.png", name)));
		} catch (IOException e) {
			itemp = new BufferedImage(16, 16, BufferedImage.TYPE_4BYTE_ABGR);
		}
		preview = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = preview.createGraphics();
		g.scale(2, 2);
		g.drawImage(itemp, 0, 0, null);
		g.dispose();
		ico = new ImageIcon(preview);
	}

	public String toString() {
		return name;
	}

	public ImageIcon getImageIcon() {
		return ico;
	}

	public byte[] getData() {
		return data;
	}

	public BufferedImage getImage() {
		return preview;
	}

	public byte getPalette() {
		return p.b;
	}
}