# SgcraftTlg

*bukkitgradle*-based PaperMC plugin which connects Telgram with Minecraft.

PaperMC support: **1.7** to **1.14.4**. Currently in early alpha stage!

## Contributions

Feel free to fork this project, work on it and then make a pull request against **develop** branch.

Please, **DO NOT PUSH ANY TOKEN OR API KEYS**!

## Building

The plugin can be built through gradle tasks provided by *bukkitgradle*.
Most common tasks you'll need:

    ./gradlew build         Builds the plugin
    ./gradlew clean         Cleans the build artifacts
    ./gradlew runServer     Runs a minecraft test server with the plugin
                            installed. The version of the server is
                            declared in build.gradle.
                            
Run the following tasks to create an integrated run configuration for IntelliJ IDEA:

    ./gradlew buildIdeaRun
    
All tasks can be executed on Windows aswell using ```gradlew.bat``` instead of ```gradlew.sh```.

## Usage

Currently, the plugin features a simple API for adding Telegram update handlers and command handlers.

### First use

Simply drop the built ```SgcraftTlg[VERSION].jar``` into the ```/plugins``` folder of your server.
The plugin now should have created a ```config.yml``` within ```/plugins/SgcraftTlg``` where the following settings should
be set:

    bot-token: [BOT TOKEN]
    event-chat-id: [TARGET CHAT FOR EVENTS]
    
The bot token can be acquired through [@BotFather](https://telegram.me/botfather).

After the config file has been updated, simply restart the server.

#### Configure via Minecraft

Server operators can also set the bot token via Minecraft. Type in the following command
into the console:

    /tgsettoken [BOT TOKEN]

### Telegram Updates

Adding an update handler:

    import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.AbstractUpdateHandler;
    import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Models.Update;
    
    public class HelloWorldHandler extends AbstractUpdateHandler {
        @Override
        protected void handleUpdate(Update update) {
            
        }
    }
    
and then in ```PluginContext.java```

    bot = new TelegramBot();
    bot.addUpdateHandler(new HelloWorldHandler());
    
### Telegram Commands

In order to handle Telegram ```/command``` commands, use the ```AbstractCommandHandler.java``` base type:

    import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Commands.Command;
    import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Commands.AbstractCommandHandler;

    public class HelloCommandHandler extends AbstractCommandHandler {
        @Override
        protected void handleCommand(Command command) {
            if(command.getName() != "hello" || command.getParameters().length != 1)
                return;
        }
    }

and then in ```PluginContext.java```

    bot = new TelegramBot();
    bot.addUpdateHandler(new HelloCommandHandler());

### Minecraft interaction

Just use the Bukkit (or PaperMC) API as described in e.g https://hub.spigotmc.org/javadocs/bukkit/overview-summary.html or
in the API documentations of the respective server forks.

## Runtime commands

### Minecraft

| Command | Description |
| --- | --- |
| **/tgsettoken** [TOKEN] | Sets the telegram bot token |

   
### Telegram

| Command | Description |
| --- | --- |
| **/mconline** | Lists all players currently on the server |
| **/mcwhereis** [PLAYER] | Returns the coordinates of the specified player (MC) |

## Runtime events

The bot announces several events to the event channel, if configured and enabled.

| Event | Flag |
| --- | --- |
| onJoin | enable-player-events |
| onLeave | enable-player-events |
| onDeath | enable-player-events |
| onWorldLoad | enable-world-events |
| onWorldUnload | enable-world-events |
| onWeatherChange | enable-weather-events |
| onLightningStrike | enable-weather-events |

## Roadmap

   - [ ] Minecraft / Telegram user linking
   - [ ] Minecraft chat -> Telegram
   - [ ] Telegram chat -> Minecraft
   - [ ] Bot configuration from within minecraft
   - [ ] Server-side shaders 🤷
