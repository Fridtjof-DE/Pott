package tk.fridtjof.pott.cmds;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class GuildCommand {

    public GuildMessageReceivedEvent event;
    public String commandName;

    public GuildCommand(String commandName, GuildMessageReceivedEvent event) {
        this.event = event;
        this.commandName = commandName;

        if(arguments()[0].equalsIgnoreCase(commandName)) {
            logic();
        }
    }

    public String[] arguments() {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        return args;
    }

    public abstract void logic();
}
