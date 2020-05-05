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

package io.github.alttpj.memeforcehunt.common.sprites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import io.github.alttpj.memeforcehunt.common.sprites.impl.ShippedSpritemapWithSkin;
import io.github.alttpj.memeforcehunt.common.value.ItemPalette;
import io.github.alttpj.memeforcehunt.common.value.SpritemapWithSkin;
import io.github.alttpj.memeforcehunt.common.value.ULID;

import org.junit.jupiter.api.Test;

import java.util.List;

public class DefaultSpritemapWithSkinsTest {

  @Test
  public void testSpritesLoadable() {
    // given
    final List<SpritemapWithSkin> values = DefaultSpritemapWithSkins.values();

    //
    assertFalse(values.isEmpty());
  }

  @Test
  public void testDisplayNameDefaultsToSpriteName() {
    // given
    final ShippedSpritemapWithSkin shippedSpritemapWithSkin = new ShippedSpritemapWithSkin(new ULID().nextULID(),
        "testSprite",
        null,
        "desc",
        "author",
        "/res",
        "/prev",
        ItemPalette.BLUE);

    // when
    final String displayName = shippedSpritemapWithSkin.getDisplayName();

    // then
    assertEquals(shippedSpritemapWithSkin.getSpriteName(), displayName);
  }
}
