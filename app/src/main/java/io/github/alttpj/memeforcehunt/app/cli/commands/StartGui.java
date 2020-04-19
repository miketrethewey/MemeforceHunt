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
