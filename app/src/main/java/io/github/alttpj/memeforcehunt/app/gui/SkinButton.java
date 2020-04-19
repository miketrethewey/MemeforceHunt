package io.github.alttpj.memeforcehunt.app.gui;

import static io.github.alttpj.memeforcehunt.app.gui.SwingAppConstants.PADDING;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import io.github.alttpj.memeforcehunt.common.value.SpritemapWithSkin;

public class SkinButton extends JButton {

  private static final long serialVersionUID = 1130434853839396656L;
  private final SpritemapWithSkin spritemapWithSkin;

  public SkinButton(final SpritemapWithSkin spritemap) {
    super();
    this.spritemapWithSkin = spritemap;
    SwingUtilities.invokeLater(this::init);
  }

  void init() {
    final ImageIcon imageIcon = this.spritemapWithSkin.getImageIcon();
    setIcon(imageIcon);
    setBorder(PADDING);
  }

  public SpritemapWithSkin getSpritemapWithSkin() {
    return this.spritemapWithSkin;
  }
}
