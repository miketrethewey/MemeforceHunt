package io.github.alttpj.memeforcehunt.cli.commands;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.Callable;

import io.github.alttpj.memeforcehunt.common.value.Skin;
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

    private static void printSkin(final Skin skin) {
        final String format = String.format(Locale.ENGLISH,
            "[%3d] - %s",
            skin.ordinal(),
            skin.getName());

        System.out.println(format);
    }
}
