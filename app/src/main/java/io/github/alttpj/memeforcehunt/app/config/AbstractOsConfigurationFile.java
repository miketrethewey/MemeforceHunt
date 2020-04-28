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

package io.github.alttpj.memeforcehunt.app.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class AbstractOsConfigurationFile {

  private static final Logger LOG = java.util.logging.Logger.getLogger(AbstractOsConfigurationFile.class.getCanonicalName());

  private File configDirectory;

  private final File configFile;

  private boolean isUsable;

  public AbstractOsConfigurationFile() {
    // default
    initConfigDirectory();
    this.configFile = new File(this.configDirectory, "config.yaml");
    if (!this.configFile.exists()) {
      try {
        Files.createFile(this.configFile.toPath());
        LOG.log(Level.INFO, String.format(Locale.ENGLISH, "Using config dir: [%s].", this.configFile.getAbsolutePath()));
      } catch (final IOException ioException) {
        LOG.log(Level.SEVERE, ioException,
            () -> String.format(Locale.ENGLISH, "Unable to write to Config File [%s].", this.configFile.getAbsolutePath()));
      }
    }
    if (this.configFile.exists() && this.configFile.canWrite()) {
      this.isUsable = true;
    }
  }

  /* *********************************************
   *             INITIALISATION METHODS
   ***********************************************/

  private void initConfigDirectory() {
    if (couldBeWindows()) {
      this.configDirectory = getWindowsConfigDir().orElseThrow();
      tryCreateConfigDir();
      return;
    }

    if (isMacOS()) {
      this.configDirectory = getOsxConfigDir().orElseThrow();
      tryCreateConfigDir();
      return;
    }

    // POSIX / Linux / UNIX

    this.configDirectory = Optional.ofNullable(System.getenv("XDG_CONFIG_HOME"))
        .map(File::new)
        .or(() -> Optional.of(new File(System.getProperty("user.home"), ".config")))
        .map(file -> new File(file, "memeforcehunt"))
        .orElseThrow();
    tryCreateConfigDir();
  }

  private boolean isMacOS() {
    final String os = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH);

    return os.startsWith("mac") || os.contains("os x");
  }

  protected Optional<File> getOsxConfigDir() {
    return Optional.ofNullable(System.getProperty("user.home"))
        .map(File::new)
        .map(homeDir -> new File(homeDir, "Library/Preferences/io.github.alttpj.memeforcehunt"));
  }


  private boolean couldBeWindows() {
    final String os = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH);

    return os.startsWith("win") || System.getenv("AppData") != null || System.getenv("LocalAppData") != null;
  }

  private Optional<File> getWindowsConfigDir() {
    return Optional.ofNullable(System.getenv("LocalAppData"))
        .or(() -> Optional.ofNullable(System.getenv("LOCALAPPDATA")))
        .or(() -> Optional.ofNullable(System.getenv("AppData")))
        .or(() -> Optional.ofNullable(System.getenv("APPDATA")))
        .map(File::new)
        .or(() -> Optional.of(new File(System.getenv("user.home"), "AppData")))
        .map(file -> new File(file, "memeforcehunt"));
  }

  private void tryCreateConfigDir() {
    try {
      Files.createDirectories(this.configDirectory.toPath());
    } catch (final IOException ioEx) {
      LOG.log(Level.WARNING, ioEx, () -> "Unable to create config directory: [" + this.configDirectory.getAbsolutePath() + "].");
    }
  }

  /* *********************************************
   *            GETTER FOR SUBCLASSES
   ***********************************************/

  protected boolean isUsable() {
    return this.isUsable;
  }

  public File getConfigFile() {
    return this.configFile;
  }

  public Path getConfigFilePath() {
    return this.configFile.toPath();
  }
}
