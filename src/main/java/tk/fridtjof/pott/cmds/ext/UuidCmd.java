package tk.fridtjof.pott.cmds.ext;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import tk.fridtjof.pott.Pott;
import tk.fridtjof.pott.cmds.GuildCommand;
import tk.fridtjof.puddingapi.general.utils.MojangAPI;
import tk.fridtjof.puddingapi.general.utils.UUIDConverter;

public class UuidCmd extends GuildCommand {

    public UuidCmd(String commandName, GuildMessageReceivedEvent event) {
        super(commandName, event);
    }

    @Override
    public void logic() {

        Pott pott = Pott.getInstance();
        String p = pott.config.getString("prefix");

        if (arguments().length == 1) {
            if (arguments()[0].equalsIgnoreCase(p + "uuid")) {
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("**Please enter a name!**").queue();
            }
        }

        if (arguments().length == 2) {
            if (arguments()[0].equalsIgnoreCase(p + "uuid")) {
                event.getMessage().addReaction("✔️").queue();
                String uuid = MojangAPI.getUuidFromUsername(arguments()[1]);
                if(uuid == null) {
                    event.getChannel().sendMessage("**Error!**").queue();
                } else {
                    event.getChannel().sendMessage("**The UUID of `" + arguments()[1] + "` is:**\n`" + uuid + "`\n`" + UUIDConverter.fromTrimmed(uuid) + "`").queue();
                }
            }
        }
    }
}
