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
