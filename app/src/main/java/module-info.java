module memeforcehunt.app {
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires javafx.swing;
  requires java.desktop;
  requires java.logging;

  requires info.picocli;
  requires memeforcehunt.common.sprites;
  requires memeforcehunt.common.value;
  requires memforcehunt.lib;
  requires org.jfxtras.styles.jmetro;

  // fxml needs to modify the gui classes.
  opens io.github.alttpj.memeforcehunt.app.gui to javafx.fxml;
  opens io.github.alttpj.memeforcehunt.app.gui.main to javafx.fxml;

  opens io.github.alttpj.memeforcehunt.app.cli.commands to info.picocli;

  // those classes are accessible from outside, e.g. for the JavaFX loader.
  exports io.github.alttpj.memeforcehunt.app.cli;
  exports io.github.alttpj.memeforcehunt.app.cli.commands;
  exports io.github.alttpj.memeforcehunt.app.gui;
}
