package me.fridtjof.pott.cmds;

import me.fridtjof.pott.Pott;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import me.fridtjof.puddingapi.general.utils.MojangAPI;
import me.fridtjof.puddingapi.general.utils.UUIDConverter;
import me.fridtjof.puddingapi.jda.GuildCommand;

public class UuidCmd extends GuildCommand {

    public UuidCmd(String commandName, MessageReceivedEvent event) {
        super(commandName, event);
    }

    @Override
    public void logic() {

        Pott pott = Pott.getInstance();
        String p = pott.config.getString("prefix");

        if (arguments().length == 1) {
            if (arguments()[0].equalsIgnoreCase(p + "uuid")) {
                event.getMessage().addReaction(Emoji.fromFormatted("❌")).queue();
                event.getChannel().sendMessage("**Please enter a name!**").queue();
            }
        }

        if (arguments().length == 2) {
            if (arguments()[0].equalsIgnoreCase(p + "uuid")) {
                event.getMessage().addReaction(Emoji.fromFormatted("✅")).queue();
                String uuid = MojangAPI.getUuidFromUsername(arguments()[1]);
                if(uuid == null) {
                    event.getChannel().sendMessage("**Error!** - The Mojang-servers are down or the user doesn't exist.").queue();
                } else {
                    event.getChannel().sendMessage("**The UUID of `" + arguments()[1] + "` is:**\n`" + uuid + "`\n`" + UUIDConverter.fromTrimmed(uuid) + "`").queue();
                }
            }
        }
    }
}
