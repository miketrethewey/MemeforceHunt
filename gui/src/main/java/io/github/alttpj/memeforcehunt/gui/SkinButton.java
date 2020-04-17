package io.github.alttpj.memeforcehunt.gui;

import javax.swing.JButton;

import io.github.alttpj.memeforcehunt.common.value.Skin;

public class SkinButton extends JButton {

  private static final long serialVersionUID = 1130434853839396656L;

  private final Skin skin;

  public SkinButton(final Skin skin) {
    super();
    this.skin = skin;
    this.setIcon(skin.getImageIconSmall());
    this.setBackground(null);
  }

  public Skin getSkin() {
    return this.skin;
  }
}
