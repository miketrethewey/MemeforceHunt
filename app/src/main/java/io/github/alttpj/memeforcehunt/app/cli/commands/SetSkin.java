package io.github.alttpj.memeforcehunt.app.cli.commands;

import java.util.concurrent.Callable;

import picocli.CommandLine.Command;

@Command(name = "set", description = "Sets a skin.")
public class SetSkin implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // AlttpRomPatcher.patchROM(fileNameText, SKINS[r]);
        return null;
    }
}
