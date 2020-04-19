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

import static io.github.alttpj.memeforcehunt.common.value.ItemPalette.BLUE;
import static io.github.alttpj.memeforcehunt.common.value.ItemPalette.GREEN;
import static io.github.alttpj.memeforcehunt.common.value.ItemPalette.RED;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public enum SpritemapWithSkin {
  BENANA("Benana (ajneb174)", "benana", GREEN),
  THINKING("Thinking", "thinking", GREEN),
  SCREAM("Scream", "scream", BLUE),
  VEETORP("Duck (Veetorp)", "veetorp", GREEN),
  BOB("Bob (Karkat | mmxbass)", "bob", GREEN),
  TEA("Tea (ChristosOwen)", "tea", RED),
  PEACH("Peach (ChelseyxLynn)", "peach", RED),
  PUG("Pug (Andy)", "pug", BLUE),
  BIRB("Birb (Kelpsey)", "birb", GREEN),
  COFFEE("Coffee (WillardJBradley)", "coffee", RED),
  GLACEON("Glaceon (Glaceon)", "glaceon", BLUE),
  HONK("Honk (TheDragonFeeney)", "honk", GREEN),
  SNES_LOGO("SNES logo", "snes", GREEN),
  LINK_FACE("Linkface", "linkface", BLUE),
  TRIFORCE("Triforce", "triforce", GREEN),
  BEE("Bee", "bee", GREEN),
  BIG_20("Big 20", "big20", RED),
  BOOTS("Pegasus boots", "boots", RED),
  BROCCOLI("Broccoli", "broccoli", GREEN),
  CHEST("Chest", "chest", GREEN),
  CUCCO("Cucco", "cucco", RED),
  DWARF("Dwarf", "dwarf", BLUE),
  FAIRY("Fairy", "fairy", BLUE),
  ICE_ROD("Ice rod", "icerod", BLUE),
  MEAT("Meat", "meat", RED),
  MAIL("Green mail", "mail", GREEN),
  PUFF("Arrghus puff", "puff", BLUE),
  QUACK("Swag Duck", "quack", GREEN),
  TILE("Tile", "tile", BLUE),
  VITREOUS("Vitreous", "vitreous", GREEN),
  WOODFELLA("Woodfella", "woodfella", BLUE),
  SIX_NINE("69", "69", GREEN),
  COMMON_MAGIC("Common magic", "commonmagic", GREEN),
  ZERO_BOMBS("No bombs", "nobomb", GREEN),
  NO_SILVERS("No silvers", "noag", RED),
  JOY_PENDANT("Joy pendant", "joypendant", BLUE),
  KINSTONE("Kinstone", "kinstone", GREEN),
  FORK("Triumph fork", "fork", GREEN),
  Z1_LINK("Z1 Link", "z1link", GREEN),
  Z1_OLD_MAN("Z1 Old man", "z1oldman", GREEN),
  NAVI("Navi", "navi", BLUE),
  SKULLTULA("Skulltula", "skulltula", GREEN),
  POKEBALL("Pokéball", "pokeball", RED),
  SHARD("Red shard", "shard", RED),
  EGG("Egg", "egg", RED),
  COIN("Coin", "coin", BLUE),
  ONE_UP("1-up", "1up", GREEN),
  MOON("Power moon", "moon", GREEN),
  STAR("Power star", "star", GREEN),
  FEATHER("Feather", "feather", BLUE),
  YOSHI("Baby yoshi", "yoshi", RED),
  Q_BLOCK("? Block", "qblock", GREEN),
  FORTRESS("Fortress", "fortress", RED),
  KOOPA("Koopa Kid", "koopa", BLUE),
  SHINE("Shine sprite", "shine", GREEN),
  CUBE("Cube", "cube", GREEN),
  BEER("Beer", "beer", GREEN),
  PIZZA("Pizza", "pizza", GREEN),
  PIZZA_SLICE("Pizza slice", "pizzaslice", GREEN),
  COOKIE("Cookie", "cookie", BLUE),
  B("[B]", "B", RED),
  POOP("Poop", "poop", RED),
  PYTHON("Python logo", "python", BLUE),
  E404("Image not found", "E404", GREEN),
  ;

  private static final int BUFFER_SIZE = 512;
  private final String description;
  private final String fileName;
  private final ItemPalette itemPalette;
  private final ImageIcon imageIcon;

  SpritemapWithSkin(final String skinDescription, final String fileName, final ItemPalette itemPalette) {
    this.description = skinDescription;
    this.fileName = fileName;
    this.itemPalette = itemPalette;

    final String previewLocation = String.format(Locale.ENGLISH, "/previews/%s.png", fileName);

    BufferedImage itemp;
    try (final InputStream inputStream = SpritemapWithSkin.class.getResourceAsStream(previewLocation)) {
      itemp = ImageIO.read(inputStream);
    } catch (final IOException imageReadEx) {
      itemp = new BufferedImage(16, 16, BufferedImage.TYPE_4BYTE_ABGR);
    }
    this.imageIcon = createImageIcon(itemp);
  }

  protected static ImageIcon createImageIcon(final BufferedImage itemp) {
    final BufferedImage imageIconImage = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR);
    final Graphics2D graphics2D = imageIconImage.createGraphics();

    graphics2D.scale(2, 2);
    graphics2D.drawImage(itemp, 0, 0, null);
    graphics2D.dispose();

    return new ImageIcon(imageIconImage);
  }

  public String getDescription() {
    return this.description;
  }

  /**
   * Returns a 32x32 image icon.
   *
   * @return a 32x32 image icon.
   */
  public ImageIcon getImageIcon() {
    return this.imageIcon;
  }

  public byte[] getData() throws IOException {
    final String spritemapLocation = String.format(Locale.ENGLISH, "/gfx/%s.bin", this.fileName);

    try (final InputStream spritemapInput = this.getClass().getResourceAsStream(spritemapLocation);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      final byte[] buffer = new byte[BUFFER_SIZE];
      int readCount;

      while ((readCount = spritemapInput.read(buffer)) != -1) {
        outputStream.write(buffer, 0, readCount);
      }

      return outputStream.toByteArray();
    }
  }

  public byte getItemPalette() {
    return this.itemPalette.getPaletteIdChest();
  }

  public byte getPaletteOW() {
    return this.itemPalette.getPaletteIdOverworld();
  }

  @Override
  public String toString() {
    return this.description;
  }
}
