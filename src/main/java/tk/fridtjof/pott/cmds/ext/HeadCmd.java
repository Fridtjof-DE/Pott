package tk.fridtjof.pott.cmds.ext;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import tk.fridtjof.pott.Pott;
import tk.fridtjof.pott.cmds.GuildCommand;
import tk.fridtjof.puddingapi.general.utils.MojangAPI;

import java.awt.*;

public class HeadCmd extends GuildCommand {

    public HeadCmd(String commandName, GuildMessageReceivedEvent event) {
        super(commandName, event);
    }

    @Override
    public void logic() {
        Pott pott = Pott.getInstance();
        String p = pott.config.getString("prefix");

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.GREEN);
        embedBuilder.setFooter("Pott by Fridtjof_DE#2605");

        if (arguments().length == 1) {
            if (arguments()[0].equalsIgnoreCase(p + "head")) {
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("**Please enter a name!**").queue();
            }
        }

        if (arguments().length == 2) {
            if (arguments()[0].equalsIgnoreCase( p + "head")) {
                event.getMessage().addReaction("✔️").queue();
                String uuid = MojangAPI.getUuidFromUsername(arguments()[1]);
                if(uuid == null) {
                    event.getChannel().sendMessage("**Error!**").queue();
                } else {
                    embedBuilder.setImage("https://mc-heads.net/head/" + uuid + "/128.png");
                    embedBuilder.setAuthor("Head render of " + arguments()[1], "https://discord.gg/XzZZAme", pott.config.getString("icon"));
                    event.getChannel().sendMessage(embedBuilder.build()).queue();
                }
            }
        }
    }
}
