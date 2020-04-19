package io.github.alttpj.memeforcehunt.app.cli;

import java.awt.GraphicsEnvironment;
import java.util.concurrent.Callable;

import io.github.alttpj.memeforcehunt.app.cli.commands.ListSkins;
import io.github.alttpj.memeforcehunt.app.cli.commands.SetSkin;
import io.github.alttpj.memeforcehunt.app.cli.commands.StartGui;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(subcommands = {
    ListSkins.class,
    SetSkin.class,
    StartGui.class
})
public class MemeforceHuntApp implements Callable<Integer> {

    private static CommandLine commandLine;

    public static void main(final String[] args) {
        final MemeforceHuntApp memeforceHuntApp = new MemeforceHuntApp();
        commandLine = new CommandLine(memeforceHuntApp);
        commandLine.execute(args);
    }

    @Override
    public Integer call() throws Exception {
        if (GraphicsEnvironment.isHeadless() || GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadlessInstance()) {
            commandLine.usage(System.out);
            return 0;
        }

        return commandLine.execute("gui");
    }
}
