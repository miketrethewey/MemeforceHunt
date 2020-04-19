/*
 * Copyright 2020-2020 the ALttPJ Team @ https://github.com/alttpj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.alttpj.memeforcehunt.app.gui;

import static io.github.alttpj.memeforcehunt.app.gui.SwingAppConstants.PADDING;

import io.github.alttpj.memeforcehunt.common.value.SpritemapWithSkin;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

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
