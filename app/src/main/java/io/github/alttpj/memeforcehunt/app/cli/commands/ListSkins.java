package io.github.alttpj.memeforcehunt.app.cli.commands;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.Callable;

import io.github.alttpj.memeforcehunt.common.value.SpritemapWithSkin;
import picocli.CommandLine.Command;

@Command(name = "list", aliases = {"ls"}, description = "List available skins.")
public class ListSkins implements Callable<Integer>  {

    @Override
    public Integer call() throws Exception {
        final SpritemapWithSkin[] values = SpritemapWithSkin.values();

        Arrays.stream(values)
                .forEach(ListSkins::printSkin);

        return 0;
    }

    private static void printSkin(final SpritemapWithSkin spritemapWithSkin) {
        final String format = String.format(Locale.ENGLISH,
            "[%3d] - %s",
            spritemapWithSkin.ordinal(),
            spritemapWithSkin.getName());

        System.out.println(format);
    }
}
