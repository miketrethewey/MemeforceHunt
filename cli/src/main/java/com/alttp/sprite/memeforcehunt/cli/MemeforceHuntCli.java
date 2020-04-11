package com.alttp.sprite.memeforcehunt.cli;

import com.alttp.sprite.memeforcehunt.cli.commands.ListSkins;
import com.alttp.sprite.memeforcehunt.cli.commands.SetSkin;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParseResult;

@Command(subcommands = {
        ListSkins.class,
        SetSkin.class
})
public class MemeforceHuntCli implements Callable<Integer> {

    public static void main(String[] args) {
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
