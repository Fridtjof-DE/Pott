package tk.fridtjof.pott.listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tk.fridtjof.pott.Pott;
import tk.fridtjof.pott.cmds.HeadCmd;
import tk.fridtjof.pott.cmds.MonitorCmd;
import tk.fridtjof.pott.cmds.PingCmd;
import tk.fridtjof.pott.cmds.UuidCmd;

public class GuildMsg extends ListenerAdapter {

    static Pott pott = Pott.getInstance();
    String p = pott.config.getString("prefix");

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        System.out.println("test2");
        new PingCmd("!ping", event);
        new HeadCmd("!head", event);
        new UuidCmd("!uuid", event);
        new MonitorCmd("!monitor", event);

        //SPAM
        if (event.getMessage().getContentRaw().equalsIgnoreCase("ja")) {
            event.getChannel().sendMessage("nein").queue();
        } else if (event.getMessage().getContentRaw().equalsIgnoreCase("nein")) {
            event.getChannel().sendMessage("doch").queue();
        } else if (event.getMessage().getContentRaw().equalsIgnoreCase("bier") || event.getMessage().getContentRaw().equalsIgnoreCase("beer")) {
            event.getChannel().sendMessage("https://www.brauerei-allersheim.de/").queue();
        }
    }
}
