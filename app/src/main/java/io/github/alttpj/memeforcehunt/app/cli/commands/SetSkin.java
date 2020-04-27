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

package io.github.alttpj.memeforcehunt.app.cli.commands;

import io.github.alttpj.memeforcehunt.app.cli.ToLogPrintStream;
import io.github.alttpj.memeforcehunt.common.sprites.DefaultSpritemapWithSkins;
import io.github.alttpj.memeforcehunt.common.value.SpritemapWithSkin;
import io.github.alttpj.memeforcehunt.lib.AlttpRomPatcher;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Command(name = "set", description = "Sets a skin.")
public class SetSkin implements Callable<Integer> {

  private static final Logger LOGGER = Logger.getLogger(SetSkin.class.getCanonicalName());
  private static final Logger STDOUT = Logger.getLogger("STDOUT");
  private static final Logger STDERR = Logger.getLogger("STDERR");
  private static final ToLogPrintStream ERRORLOG_PRINTSTREAM = new ToLogPrintStream(STDERR, Level.SEVERE);

  @CommandLine.Option(names = {"-r", "--rom"}, description = "the ROM file (\"*.sfc\") to patch.",
      required = true)
  private File romFileToPatch;

  @CommandLine.Option(names = {"-s", "--skin"}, description = "The Skin ULID or name to patch into the rom.")
  private String skin;

  @CommandLine.Option(names = {"-c", "--custom"}, description = "Patch from this custom spritemap file.")
  private File customSpritemapFile;

  @CommandLine.Option(
      names = {"--offset"},
      converter = HexStringConverter.class,
      description = "Patch offset. Do not touch this setting unless you know what you do!",
      required = false)
  private int patchOffset;

  @Override
  public Integer call() {
    if (this.skin == null && this.customSpritemapFile == null) {
      STDERR.log(Level.SEVERE, "You must either specify -s or -c.");
      CommandLine.usage(this, new ToLogPrintStream(STDERR, Level.SEVERE));
      return 1;
    }

    if (!hasFileAccess()) {
      return 1;
    }

    if (this.skin != null) {
      return patchDefaultSkin();
    }

    if (this.customSpritemapFile != null) {
      STDERR.log(Level.SEVERE, "sorry, not implemented yet! :-P");
      CommandLine.usage(this, ERRORLOG_PRINTSTREAM);
      return 1;
    }

    return 1;
  }

  private Integer patchDefaultSkin() {
    final Optional<SpritemapWithSkin> skinToPatchOpt = DefaultSpritemapWithSkins.values().stream()
        .filter(this::defaultSkinIdOrNameMatches)
        .findAny();

    if (skinToPatchOpt.isEmpty()) {
      STDERR.log(Level.SEVERE, "You must select a valid skin ID or name. [" + this.skin + "] was not found.");
      CommandLine.usage(this.getClass(), new ToLogPrintStream(STDERR, Level.SEVERE));
      return 1;
    }

    final SpritemapWithSkin skinToPatch = skinToPatchOpt.orElseThrow();

    final AlttpRomPatcher alttpRomPatcher = new AlttpRomPatcher();
    if (this.patchOffset != 0) {
      STDOUT.log(Level.INFO, () -> String.format(Locale.ENGLISH, "Setting memory address to [0x%08X].", this.patchOffset & 0xFFFFFF));
      alttpRomPatcher.setOffset(this.patchOffset);
    }

    try {
      alttpRomPatcher.patchROM(this.romFileToPatch.getAbsolutePath(), skinToPatch);
      STDOUT.log(Level.INFO, "Patched successfully.");
    } catch (final IOException ioException) {
      LOGGER.log(Level.SEVERE, ioException,
          () -> "Unable to patch file [" + this.romFileToPatch.getAbsolutePath() + "] "
              + "with Skin [" + skinToPatch + "].");
      CommandLine.usage(this, new ToLogPrintStream(STDERR, Level.SEVERE));

      return 1;
    }

    return 0;
  }

  private boolean defaultSkinIdOrNameMatches(final SpritemapWithSkin defaultSkin) {
    return defaultSkin.getId().toString().equals(this.skin) || defaultSkin.getSpriteName().equals(this.skin);
  }

  private boolean hasFileAccess() {
    if (!this.romFileToPatch.exists()) {
      STDERR.log(Level.SEVERE, "File [" + this.romFileToPatch.getAbsolutePath() + "] does not exist.");
      return false;
    }

    if (!this.romFileToPatch.canWrite()) {
      STDERR.log(Level.SEVERE, "File [" + this.romFileToPatch.getAbsolutePath() + "] is not writeable.");
      return false;
    }
    return true;
  }

  public File getRomFileToPatch() {
    return this.romFileToPatch;
  }

  public void setRomFileToPatch(final File romFileToPatch) {
    this.romFileToPatch = romFileToPatch;
  }

  public String getSkin() {
    return this.skin;
  }

  public void setSkin(final String skin) {
    this.skin = skin;
  }

  static class HexStringConverter implements CommandLine.ITypeConverter<Integer> {

    @Override
    public Integer convert(final String value) {
      return Integer.decode(value);
    }
  }
}
