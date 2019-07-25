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

Simply drop the built ```SgcraftTlg[VERSION].jar``` into the ```/plugins``` folder of your server. At the first start, it will silently crash, as the Telegram integration first needs to be configured.
The plugin now should have created a ```config.yml``` within ```/plugins/SgcraftTlg``` where the following settings should
be set:

    bottoken: [BOT TOKEN]
    chatid: [TARGET CHAT FOR MESSAGES]
    botname: [NAME OF THE BOT]
    
The bot token can be acquired through [@BotFather](https://telegram.me/botfather).

After the config file has been updated, simply restart the server.




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

### Roadmap

   - [ ] Command handler helpers
   - [ ] Minecraft / Telegram user linking
   - [ ] Minecraft chat -> Telegram
   - [ ] Telegram chat -> Minecraft
   - [ ] Bot configuration from within minecraft
   - [ ] Bukkit support
   - [ ] Spigot support
   - [ ] Server-side shaders 🤷
