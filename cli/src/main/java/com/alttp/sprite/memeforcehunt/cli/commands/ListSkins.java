package com.alttp.sprite.memeforcehunt.cli.commands;

import com.alttp.sprite.memeforcehunt.common.value.Skin;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(name = "list", aliases = {"ls"}, description = "List available skins.")
public class ListSkins implements Callable<Integer>  {

    @Override
    public Integer call() throws Exception {
        final Skin[] values = Skin.values();

        Arrays.stream(values)
                .forEach(ListSkins::printSkin);

        return 0;
    }

    private static void printSkin(Skin skin) {
        final String format = String.format(Locale.ENGLISH,
                "[%3d] - %s",
                skin.ordinal(),
                skin.getName());

        System.out.println(format);
    }
}
