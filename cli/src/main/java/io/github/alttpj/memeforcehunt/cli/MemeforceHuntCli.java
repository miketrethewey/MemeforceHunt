package io.github.alttpj.memeforcehunt.cli;

import java.util.concurrent.Callable;

import io.github.alttpj.memeforcehunt.cli.commands.ListSkins;
import io.github.alttpj.memeforcehunt.cli.commands.SetSkin;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(subcommands = {
        ListSkins.class,
        SetSkin.class
})
public class MemeforceHuntCli implements Callable<Integer> {

    public static void main(final String[] args) {
        final MemeforceHuntCli memeforceHuntCli = new MemeforceHuntCli();
        final CommandLine commandLine = new CommandLine(memeforceHuntCli);
        commandLine.execute(args);
    }

    @Override
    public Integer call() throws Exception {
        new CommandLine(this)
                .usage(System.out);
        return 0;
    }
}
