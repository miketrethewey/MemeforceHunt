package memeforce;

import javax.swing.JButton;

public class SkinButton extends JButton {
	private static final long serialVersionUID = 1130434853839396656L;

	private final Skin skin;

	public SkinButton(Skin skin) {
		super();
		this.skin = skin;
		this.setIcon(skin.getImageIconSmall());
	}

	public Skin getSkin() {
		return skin;
	}
}