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

package io.github.alttpj.memeforcehunt.common.value;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import javax.imageio.ImageIO;

public class ShippedSpritemapWithSkin extends AbstractSpritemapWithSkin {


  private final String skinResourceName;

  public ShippedSpritemapWithSkin(final String description,
                                  final String skinResourceName,
                                  final ItemPalette palette) {
    super(description, palette);
    this.skinResourceName = skinResourceName;
  }

  @Override
  protected BufferedImage getImage() {
    final String previewLocation = String.format(Locale.ENGLISH, "/previews/%s.png", getSkinResourceName());

    BufferedImage itemp;
    try (final InputStream inputStream = DefaultSpritemapWithSkins.class.getResourceAsStream(previewLocation)) {
      itemp = ImageIO.read(inputStream);
    } catch (final IOException imageReadEx) {
      itemp = new BufferedImage(16, 16, BufferedImage.TYPE_4BYTE_ABGR);
    }

    return itemp;
  }

  @Override
  protected InputStream getSpritemapInputStream() {
    final String spritemapLocation = String.format(Locale.ENGLISH, "/gfx/%s.bin", getSkinResourceName());

    return this.getClass().getResourceAsStream(spritemapLocation);
  }

  private String getSkinResourceName() {
    return this.skinResourceName;
  }
}
