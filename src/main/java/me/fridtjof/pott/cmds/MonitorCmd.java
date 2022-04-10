package me.fridtjof.pott.cmds;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import me.fridtjof.puddingapi.jda.GuildCommand;

public class MonitorCmd extends GuildCommand {

    public MonitorCmd(String commandName, MessageReceivedEvent event) {
        super(commandName, event);
    }

    @Override
    public void logic() {
        if(arguments().length == 1) {
            Runtime runtime = Runtime.getRuntime();
            long memTotal = runtime.totalMemory() / 1048576;
            long memFree = runtime.freeMemory() / 1048576;
            long memUsed = memTotal - memFree;
            double memPercent = (double) memUsed / (double) memTotal * 100;

            event.getChannel().sendMessage("Memory usage: `" + (int) memPercent + "%` - `" + memUsed + "mb`/`" + memTotal + "mb` - `" + memFree + "mb` free!").queue();
        }
    }
}
