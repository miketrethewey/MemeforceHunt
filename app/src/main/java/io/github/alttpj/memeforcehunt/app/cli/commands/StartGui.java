package io.github.alttpj.memeforcehunt.app.cli.commands;

import java.util.concurrent.Callable;

import io.github.alttpj.memeforcehunt.app.gui.MemeforceHuntGui;
import picocli.CommandLine.Command;

@Command(name = "gui",
         aliases = {"startGui", "start"},
         description = "Starts the gui (default action).",
         descriptionHeading = "Default action if no other action is given.")
public class StartGui implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    final MemeforceHuntGui memeforceHuntGui = new MemeforceHuntGui();

    return memeforceHuntGui.call();
  }
}
