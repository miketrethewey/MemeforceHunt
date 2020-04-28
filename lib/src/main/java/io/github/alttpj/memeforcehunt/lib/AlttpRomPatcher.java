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

package io.github.alttpj.memeforcehunt.lib;

import io.github.alttpj.memeforcehunt.common.value.SpritemapWithSkin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringJoiner;

public class AlttpRomPatcher {

  public static final int DEFAULT_SPRITEMAP_OFFSET = 0x18A800;
  public static final int PAL_LOC = 0x104FE4;
  public static final int PAL_OW = 0x10126E;

  protected static final int MAX_SPRITEMAP_SIZE = 1023;
  private int offset;
  private int paletteLocationChest;
  private int paletteLocationOverworld;

  public AlttpRomPatcher() {
    this.offset = DEFAULT_SPRITEMAP_OFFSET;
    this.paletteLocationChest = PAL_LOC;
    this.paletteLocationOverworld = PAL_OW;
  }

  public void patchROM(final String romTarget, final SpritemapWithSkin spritemapWithSkin) throws IOException {
    final byte[] romStream = readRom(romTarget);

    writeSkin(romStream, spritemapWithSkin);

    writeRom(romTarget, romStream);
  }

  private void writeRom(final String romTarget, final byte[] romStream) throws IOException {
    try (final FileOutputStream fsOut = new FileOutputStream(romTarget)) {
      fsOut.write(romStream, 0, romStream.length);
    }
  }

  protected void writeSkin(final byte[] romStream, final SpritemapWithSkin spritemapWithSkin) throws IOException {
    final byte[] data = spritemapWithSkin.getData();

    if (data.length > MAX_SPRITEMAP_SIZE) {
      throw new IOException(
          "Skin too large! Max is [" + MAX_SPRITEMAP_SIZE + "], but supplied skin contains [" + data.length + "] bytes.");
    }

    final int pos = getOffset();
    // clear up space (safety)
    System.arraycopy(new byte[MAX_SPRITEMAP_SIZE], 0, romStream, pos, MAX_SPRITEMAP_SIZE);
    // write graphics
    System.arraycopy(data, 0, romStream, pos, data.length);

    romStream[getPaletteLocationChest()] = spritemapWithSkin.getItemPalette();
    romStream[getPaletteLocationOverworld()] = spritemapWithSkin.getPaletteOW();
  }

  private byte[] readRom(final String romTarget) throws IOException {
    final byte[] romStream;

    try (final FileInputStream fsInput = new FileInputStream(romTarget)) {
      romStream = new byte[(int) fsInput.getChannel().size()];
      fsInput.read(romStream);
      fsInput.getChannel().position(0);
    }

    return romStream;
  }

  public static String getVersion() {
    try {
      final InputStream versionProps = AlttpRomPatcher.class.getResourceAsStream("/constants/version.properties");
      final Properties properties = new Properties();
      properties.load(versionProps);

      return properties.getProperty("MemforceHunt.version", "UNKNOWN");
    } catch (final IOException ioException) {
      return "UNKNOWN";
    }
  }

  public int getOffset() {
    return this.offset;
  }

  public void setOffset(final int offset) {
    if (offset <= -1) {
      // ignore
      return;
    }
    this.offset = offset;
  }

  public int getPaletteLocationChest() {
    return this.paletteLocationChest;
  }

  public void setPaletteLocationChest(final int paletteLocationChest) {
    this.paletteLocationChest = paletteLocationChest;
  }

  public int getPaletteLocationOverworld() {
    return this.paletteLocationOverworld;
  }

  public void setPaletteLocationOverworld(final int paletteLocationOverworld) {
    this.paletteLocationOverworld = paletteLocationOverworld;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", "AlttpRomPatcher{", "}")
        .add("offset=" + this.offset)
        .add("paletteLocationChest=" + this.paletteLocationChest)
        .add("paletteLocationOverworld=" + this.paletteLocationOverworld)
        .toString();
  }
}
