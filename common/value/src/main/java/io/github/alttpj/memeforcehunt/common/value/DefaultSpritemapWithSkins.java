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

public enum DefaultSpritemapWithSkins {
  BENANA(new ShippedSpritemapWithSkin("Benana (ajneb174)", "benana", GREEN)),
  THINKING(new ShippedSpritemapWithSkin("Thinking", "thinking", GREEN)),
  SCREAM(new ShippedSpritemapWithSkin("Scream", "scream", BLUE)),
  VEETORP(new ShippedSpritemapWithSkin("Duck (Veetorp)", "veetorp", GREEN)),
  BOB(new ShippedSpritemapWithSkin("Bob (Karkat | mmxbass)", "bob", GREEN)),
  TEA(new ShippedSpritemapWithSkin("Tea (ChristosOwen)", "tea", RED)),
  PEACH(new ShippedSpritemapWithSkin("Peach (ChelseyxLynn)", "peach", RED)),
  PUG(new ShippedSpritemapWithSkin("Pug (Andy)", "pug", BLUE)),
  BIRB(new ShippedSpritemapWithSkin("Birb (Kelpsey)", "birb", GREEN)),
  COFFEE(new ShippedSpritemapWithSkin("Coffee (WillardJBradley)", "coffee", RED)),
  GLACEON(new ShippedSpritemapWithSkin("Glaceon (Glaceon)", "glaceon", BLUE)),
  HONK(new ShippedSpritemapWithSkin("Honk (TheDragonFeeney)", "honk", GREEN)),
  SNES_LOGO(new ShippedSpritemapWithSkin("SNES logo", "snes", GREEN)),
  LINK_FACE(new ShippedSpritemapWithSkin("Linkface", "linkface", BLUE)),
  TRIFORCE(new ShippedSpritemapWithSkin("Triforce", "triforce", GREEN)),
  BEE(new ShippedSpritemapWithSkin("Bee", "bee", GREEN)),
  BIG_20(new ShippedSpritemapWithSkin("Big 20", "big20", RED)),
  BOOTS(new ShippedSpritemapWithSkin("Pegasus boots", "boots", RED)),
  BROCCOLI(new ShippedSpritemapWithSkin("Broccoli", "broccoli", GREEN)),
  CHEST(new ShippedSpritemapWithSkin("Chest", "chest", GREEN)),
  CUCCO(new ShippedSpritemapWithSkin("Cucco", "cucco", RED)),
  DWARF(new ShippedSpritemapWithSkin("Dwarf", "dwarf", BLUE)),
  FAIRY(new ShippedSpritemapWithSkin("Fairy", "fairy", BLUE)),
  ICE_ROD(new ShippedSpritemapWithSkin("Ice rod", "icerod", BLUE)),
  MEAT(new ShippedSpritemapWithSkin("Meat", "meat", RED)),
  MAIL(new ShippedSpritemapWithSkin("Green mail", "mail", GREEN)),
  PUFF(new ShippedSpritemapWithSkin("Arrghus puff", "puff", BLUE)),
  QUACK(new ShippedSpritemapWithSkin("Swag Duck", "quack", GREEN)),
  TILE(new ShippedSpritemapWithSkin("Tile", "tile", BLUE)),
  VITREOUS(new ShippedSpritemapWithSkin("Vitreous", "vitreous", GREEN)),
  WOODFELLA(new ShippedSpritemapWithSkin("Woodfella", "woodfella", BLUE)),
  SIX_NINE(new ShippedSpritemapWithSkin("69", "69", GREEN)),
  COMMON_MAGIC(new ShippedSpritemapWithSkin("Common magic", "commonmagic", GREEN)),
  ZERO_BOMBS(new ShippedSpritemapWithSkin("No bombs", "nobomb", GREEN)),
  NO_SILVERS(new ShippedSpritemapWithSkin("No silvers", "noag", RED)),
  JOY_PENDANT(new ShippedSpritemapWithSkin("Joy pendant", "joypendant", BLUE)),
  KINSTONE(new ShippedSpritemapWithSkin("Kinstone", "kinstone", GREEN)),
  FORK(new ShippedSpritemapWithSkin("Triumph fork", "fork", GREEN)),
  Z1_LINK(new ShippedSpritemapWithSkin("Z1 Link", "z1link", GREEN)),
  Z1_OLD_MAN(new ShippedSpritemapWithSkin("Z1 Old man", "z1oldman", GREEN)),
  NAVI(new ShippedSpritemapWithSkin("Navi", "navi", BLUE)),
  SKULLTULA(new ShippedSpritemapWithSkin("Skulltula", "skulltula", GREEN)),
  POKEBALL(new ShippedSpritemapWithSkin("Pokéball", "pokeball", RED)),
  SHARD(new ShippedSpritemapWithSkin("Red shard", "shard", RED)),
  EGG(new ShippedSpritemapWithSkin("Egg", "egg", RED)),
  COIN(new ShippedSpritemapWithSkin("Coin", "coin", BLUE)),
  ONE_UP(new ShippedSpritemapWithSkin("1-up", "1up", GREEN)),
  MOON(new ShippedSpritemapWithSkin("Power moon", "moon", GREEN)),
  STAR(new ShippedSpritemapWithSkin("Power star", "star", GREEN)),
  FEATHER(new ShippedSpritemapWithSkin("Feather", "feather", BLUE)),
  YOSHI(new ShippedSpritemapWithSkin("Baby yoshi", "yoshi", RED)),
  Q_BLOCK(new ShippedSpritemapWithSkin("? Block", "qblock", GREEN)),
  FORTRESS(new ShippedSpritemapWithSkin("Fortress", "fortress", RED)),
  KOOPA(new ShippedSpritemapWithSkin("Koopa Kid", "koopa", BLUE)),
  SHINE(new ShippedSpritemapWithSkin("Shine sprite", "shine", GREEN)),
  CUBE(new ShippedSpritemapWithSkin("Cube", "cube", GREEN)),
  BEER(new ShippedSpritemapWithSkin("Beer", "beer", GREEN)),
  PIZZA(new ShippedSpritemapWithSkin("Pizza", "pizza", GREEN)),
  PIZZA_SLICE(new ShippedSpritemapWithSkin("Pizza slice", "pizzaslice", GREEN)),
  COOKIE(new ShippedSpritemapWithSkin("Cookie", "cookie", BLUE)),
  B(new ShippedSpritemapWithSkin("[B]", "B", RED)),
  POOP(new ShippedSpritemapWithSkin("Poop", "poop", RED)),
  PYTHON(new ShippedSpritemapWithSkin("Python logo", "python", BLUE)),
  E404(new ShippedSpritemapWithSkin("Image not found", "E404", GREEN)),
  ;

  private final SpritemapWithSkin spritemapWithSkin;

  DefaultSpritemapWithSkins(final SpritemapWithSkin spritemapWithSkin) {
    this.spritemapWithSkin = spritemapWithSkin;
  }

  public SpritemapWithSkin getSpritemapWithSkin() {
    return this.spritemapWithSkin;
  }
}
