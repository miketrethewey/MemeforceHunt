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
	SCREAM ("scream", BLUE),
	LINK_FACE ("linkface", BLUE),
	TRIFORCE ("triforce", GREEN),
	BEE ("bee", GREEN),
	BIG_20 ("big20", RED),
	BOB ("bob", GREEN),
	BOOTS ("boots", RED),
	BROCCOLI ("broccoli", GREEN),
	CHEST ("chest", GREEN),
	CUCCO ("cucco", RED),
	DWARF ("dwarf", BLUE),
	FAIRY ("fairy", BLUE),
	ICE_ROD ("icerod", BLUE),
	MEAT ("meat", RED),
	MAIL ("mail", GREEN),
	PUFF ("puff", BLUE),
	QUACK ("quack", GREEN),
	VITREOUS ("vitreous", GREEN),
	WOODFELLA ("woodfella", BLUE),
	SIX_NINE ("69", GREEN),
	KINSTONE ("kinstone", GREEN),
	Z1_LINK ("z1link", GREEN),
	Z1_OLD_MAN ("z1oldman", GREEN),
	NAVI ("navi", BLUE),
	SKULLTULA ("skulltula", GREEN),
	FORK ("fork", GREEN),
	BIRB ("birb", GREEN),
	HONK ("honk", GREEN),
	GLACEON ("glaceon", BLUE),
	POKEBALL ("pokeball", RED),
	COFFEE ("coffee", RED),
	COIN ("coin", BLUE),
	ONE_UP ("1up", GREEN),
	MOON ("moon", GREEN),
	STAR ("star", GREEN),
	Q_BLOCK ("qblock", GREEN),
	B ("B", RED),
	POOP ("poop", RED),
	PYTHON ("python", BLUE),
	PIZZA ("pizza", GREEN),
	PIZZA_SLICE ("pizzaslice", GREEN),
	E404 ("E404", GREEN),
	;

	private final String name;
	private final Palette p;
	private final byte[] data;
	private final BufferedImage preview;
	private final ImageIcon ico;
	private final ImageIcon icoSmall;

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
		icoSmall = new ImageIcon(itemp);
	}

	public String toString() {
		return name;
	}

	public ImageIcon getImageIcon() {
		return ico;
	}

	public ImageIcon getImageIconSmall() {
		return icoSmall;
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