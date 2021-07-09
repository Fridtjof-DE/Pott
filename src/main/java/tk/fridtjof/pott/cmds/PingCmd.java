package tk.fridtjof.pott.cmds;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import tk.fridtjof.puddingapi.jda.GuildCommand;

public class PingCmd extends GuildCommand {

    public PingCmd(String commandName, GuildMessageReceivedEvent event) {
        super(commandName, event);
    }

    @Override
    public void logic() {
        if (arguments().length == 1) {
            long time = System.currentTimeMillis();
            event.getChannel().sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> {
                        response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                    });
        }
    }
}
