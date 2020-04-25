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

package io.github.alttpj.memeforcehunt.app.gui.main;

import io.github.alttpj.memeforcehunt.common.value.SpritemapWithSkin;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.TextAlignment;

import java.awt.image.BufferedImage;

public class SkinListCell extends ListCell<SpritemapWithSkin> {

  private static final Insets DEFAULT_PADDING = new Insets(0.0d, 1.0d, 0.0d, 5.0d);

  public SkinListCell() {
    graphicTextGapProperty().set(15.0);
    setTextAlignment(TextAlignment.LEFT);
    setPadding(DEFAULT_PADDING);
  }

  @Override
  protected void updateItem(final SpritemapWithSkin item, final boolean empty) {
    super.updateItem(item, empty);

    if (item == null) {
      return;
    }

    final BufferedImage bufferedImage = item.getImage();
    final WritableImage fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
    final ImageView drawnItem = new ImageView(fxImage);
    drawnItem.setFitWidth(32.0);
    drawnItem.setFitHeight(32.0);
    setGraphic(drawnItem);
    setText(item.getSpriteName() + " by " + item.getAuthor() + "\n" + item.getDescription());
  }
}
