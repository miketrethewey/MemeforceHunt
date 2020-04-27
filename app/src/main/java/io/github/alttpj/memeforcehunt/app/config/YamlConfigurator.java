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

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YamlConfigurator {

  private static final Logger LOG = java.util.logging.Logger.getLogger(YamlConfigurator.class.getCanonicalName());

  private File configDirectory;

  private final File configFile;

  private boolean isUsable;

  public YamlConfigurator() {
    // default
    initConfigDirectory();
    this.configFile = new File(this.configDirectory, "config.yaml");
    if (!this.configFile.exists()) {
      try {
        Files.createFile(this.configFile.toPath());
        LOG.log(Level.INFO, "Using config dir: [" + this.configFile.getAbsolutePath() + "].");
      } catch (final IOException ioException) {
        LOG.log(Level.SEVERE, ioException, () -> "Unable to write to Config File [" + this.configFile.getAbsolutePath() + "].");
      }
    }
    if (this.configFile.exists() && this.configFile.canWrite()) {
      this.isUsable = true;
    }
  }

  public boolean useCustomPatchOffset() {
    if (!this.isUsable) {
      return false;
    }

    return readFromYaml("useCustomPatchOffset", false);
  }

  private <T> T readFromYaml(final String fieldName, final T defaultValue) {
    final Yaml yaml = YamlProvider.createYaml();
    try (final InputStream yamlInputStream = Files.newInputStream(this.configFile.toPath(), StandardOpenOption.READ)) {
      final Map<String, Object> yamlConfig = yaml.load(yamlInputStream);

      return (T) yamlConfig.get(fieldName);
    } catch (final ClassCastException | IOException ioEx) {
      LOG.log(Level.SEVERE, ioEx, () -> "Unable to read config from [" + this.configFile.getAbsolutePath() + "].");
      return defaultValue;
    }
  }

  public void setCustomPatchOffset(final boolean useCustomOffset) {
    writeField("useCustomPatchOffset", useCustomOffset);
  }

  private void writeField(final String fieldName, final Object value) {
    if (!this.isUsable) {
      return;
    }

    final Yaml yaml = YamlProvider.createYaml();
    final Map<String, Object> yamlConfig = new ConcurrentHashMap<>();

    try (final InputStream yamlInputStream = Files.newInputStream(this.configFile.toPath(), StandardOpenOption.READ)) {
      final Map<String, Object> loadedConfig = yaml.load(yamlInputStream);
      if (loadedConfig != null) {
        yamlConfig.putAll(loadedConfig);
      }
    } catch (final ClassCastException | IOException ioEx) {
      LOG.log(Level.SEVERE, ioEx, () -> "Unable to read config from [" + this.configFile.getAbsolutePath() + "].");
      final Path destination = new File(this.configFile.getAbsolutePath() + ".old").toPath();

      // make a backup before (over-)writing.
      try {
        Files.copy(this.configFile.toPath(), destination);
      } catch (final IOException copyIoEx) {
        LOG.log(Level.SEVERE, copyIoEx,
            () -> String.format(Locale.ENGLISH, "Unable to write backup from [%s] to [%s].",
                this.configFile,
                destination));
      }
    }

    yamlConfig.put(fieldName, value);

    try (final OutputStream outputStream = Files.newOutputStream(this.configFile.toPath(), StandardOpenOption.WRITE);
         final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
      yaml.dump(yamlConfig, outputStreamWriter);
      outputStreamWriter.flush();
    } catch (final IOException ioException) {
      LOG.log(Level.SEVERE, ioException, () -> "Unable to write config to [" + this.configFile.getAbsolutePath() + "].");
    }
  }

  protected final void initConfigDirectory() {
    if (couldBeWindows()) {
      this.configDirectory = getWindowsConfigDir().orElseThrow();
      tryCreateConfigDir();
      return;
    }

    final File memeForceHuntConfigDir = Optional.ofNullable(System.getenv("XDG_CONFIG_HOME"))
        .map(File::new)
        .or(() -> Optional.of(new File(System.getProperty("user.home"), ".config")))
        .map(file -> new File(file, "memeforcehunt"))
        .orElseThrow();
    this.configDirectory = memeForceHuntConfigDir;
    tryCreateConfigDir();
  }

  private void tryCreateConfigDir() {
    try {
      Files.createDirectories(this.configDirectory.toPath());
    } catch (final IOException ioEx) {
      LOG.log(Level.WARNING, ioEx, () -> "Unable to create config directory: [" + this.configDirectory.getAbsolutePath() + "].");
    }
  }


  private boolean couldBeWindows() {
    final String os = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH);

    return os.startsWith("win") || System.getenv("AppData") != null || System.getenv("LocalAppData") != null;
  }

  public Optional<File> getWindowsConfigDir() {
    return Optional.ofNullable(System.getenv("LocalAppData"))
        .or(() -> Optional.ofNullable(System.getenv("LOCALAPPDATA")))
        .or(() -> Optional.ofNullable(System.getenv("AppData")))
        .or(() -> Optional.ofNullable(System.getenv("APPDATA")))
        .map(File::new)
        .or(() -> Optional.of(new File(System.getenv("user.home"), "//")))
        .map(file -> new File(file, "memeforcehunt"));
  }

  public int getCustomOffsetAddress() {
    if (!this.useCustomPatchOffset()) {
      return 0;
    }

    if (!this.isUsable) {
      return 0;
    }

    String offset = readFromYaml("offset", "0x0");
    if (offset == null) {
      return 0;
    }

    if (offset.startsWith("0x")) {
      offset = offset.substring(2);
    }

    try {
      return Integer.parseInt(offset, 16);
    } catch (final NumberFormatException nfEx) {
      LOG.log(Level.WARNING, "Invalid field 'offset' was written in yaml file: [" + offset + "].", nfEx);
      writeField("offset", 0);
      return 0;
    }
  }

  public void setCustomOffsetAddress(final String offsetAddressAsHex) {
    writeField("offset", offsetAddressAsHex);
  }
}
