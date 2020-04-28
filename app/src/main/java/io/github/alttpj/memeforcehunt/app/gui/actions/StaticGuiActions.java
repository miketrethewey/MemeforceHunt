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

package io.github.alttpj.memeforcehunt.app.gui.actions;

import io.github.alttpj.memeforcehunt.app.config.YamlConfigurator;
import io.github.alttpj.memeforcehunt.common.value.SpritemapWithSkin;
import io.github.alttpj.memeforcehunt.lib.AlttpRomPatcher;

import javafx.application.HostServices;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public final class StaticGuiActions {

  private static final URI RELEASES = URI.create("https://github.com/alttpj/MemeforceHunt/releases");
  private static final URI WIKI = URI.create("https://github.com/alttpj/MemeforceHunt/wiki");

  private StaticGuiActions() {
    // util class
  }

  public static void tryOpenAboutPage(final HostServices hostServices) {
    try {
      hostServices.showDocument(RELEASES.toString());
    } catch (final UnsupportedOperationException | SecurityException urlOpenEx) {
      final Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Unable to open your browser");
      alert.setContentText("Unable to open your browser. Error message:\n[" + urlOpenEx.getMessage() + "].");
      alert.show();
    }
  }

  public static void patch(final File romToPatch, final SpritemapWithSkin selectedItem) throws IOException {
    final YamlConfigurator yamlConfigurator = new YamlConfigurator();
    final int customOffset = yamlConfigurator.getCustomOffsetAddress();
    final AlttpRomPatcher alttpRomPatcher = new AlttpRomPatcher();

    if (yamlConfigurator.useCustomPatchOffset() && customOffset != 0) {
      alttpRomPatcher.setOffset(customOffset);
    }

    alttpRomPatcher.patchROM(romToPatch.getAbsolutePath(), selectedItem);
  }

  public static void tryOpenHelpPage(final HostServices hostServices) {
    try {
      hostServices.showDocument(WIKI.toString());
    } catch (final UnsupportedOperationException | SecurityException urlOpenEx) {
      final Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Unable to open your browser");
      alert.setContentText("Unable to open your browser. Error message:\n[" + urlOpenEx.getMessage() + "].");
      alert.show();
    }
  }
}
