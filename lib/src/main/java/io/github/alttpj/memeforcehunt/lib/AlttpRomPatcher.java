package io.github.alttpj.memeforcehunt.lib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.github.alttpj.memeforcehunt.common.value.SpritemapWithSkin;

public class AlttpRomPatcher {

  public static final int OFFSET = 0x18A800;
  public static final int PAL_LOC = 0x104FE4;
  public static final int PAL_OW = 0x10126E;

  private static final int MAX_SPRITEMAP_SIZE = 1023;

  public static void patchROM(final String romTarget, final SpritemapWithSkin spritemapWithSkin) throws IOException {
    final byte[] romStream = readRom(romTarget);

    writeSkin(romStream, spritemapWithSkin);

    writeRom(romTarget, romStream);
  }

  private static void writeRom(final String romTarget, final byte[] romStream) throws IOException {
    try (final FileOutputStream fsOut = new FileOutputStream(romTarget)) {
      fsOut.write(romStream, 0, romStream.length);
    }
  }

  private static void writeSkin(final byte[] romStream, final SpritemapWithSkin spritemapWithSkin) throws IOException {
    final byte[] data = spritemapWithSkin.getData();
    if (spritemapWithSkin.getData().length > MAX_SPRITEMAP_SIZE) {
      throw new InvalidDataException("Skin too larg!");
    }

    // clear up space (safety)
    int pos = OFFSET;
    for (int i = 0; i < MAX_SPRITEMAP_SIZE; i++, pos++) {
      romStream[pos] = 0;
    }

    // write graphics
    pos = OFFSET;
    for (final byte b : data) {
      romStream[pos++] = b;
    }

    romStream[PAL_LOC] = spritemapWithSkin.getItemPalette();
    romStream[PAL_OW] = spritemapWithSkin.getPaletteOW();
  }

  private static byte[] readRom(final String romTarget) throws IOException {
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
}
