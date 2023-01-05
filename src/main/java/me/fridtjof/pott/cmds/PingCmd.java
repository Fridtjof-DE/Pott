package me.fridtjof.pott.cmds;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import me.fridtjof.puddingapi.jda.GuildCommand;

public class PingCmd extends GuildCommand {

    public PingCmd(String commandName, MessageReceivedEvent event) {
        super(commandName, event);
    }

    @Override
    public void logic() {
        if (arguments().length == 1) {
            long time = System.currentTimeMillis();
            event.getMessage().addReaction(Emoji.fromFormatted("✅")).queue();
            event.getChannel().sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> {
                        response.editMessageFormat("Pong: `%d` ms", System.currentTimeMillis() - time).queue();
                    });
        }
    }
}
