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

import io.github.alttpj.memeforcehunt.common.sprites.DefaultSpritemapWithSkins;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DefaultSpriteTab extends HBox implements Initializable {
  @FXML
  private ItemSkinList defaultSpritesItemSkinList;

  @FXML
  private Button patchButton;

  @FXML
  private Button randomButton;

  public DefaultSpriteTab() {
    // fmxl
    final FXMLLoader fxmlLoader =
        new FXMLLoader(getClass().getResource("/io/github/alttpj/memeforcehunt/app/gui/main/DefaultSprites.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (final IOException ioException) {
      throw new RuntimeException(ioException);
    }
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    DefaultSpritemapWithSkins.values().forEach(this.defaultSpritesItemSkinList::addSkin);
  }

  public ItemSkinList getDefaultSpritesItemSkinList() {
    return this.defaultSpritesItemSkinList;
  }

  public Button getPatchButton() {
    return this.patchButton;
  }

  public Button getRandomButton() {
    return this.randomButton;
  }
}
