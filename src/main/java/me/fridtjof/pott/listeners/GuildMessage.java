package me.fridtjof.pott.listeners;

import me.fridtjof.pott.Pott;
import me.fridtjof.pott.cmds.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuildMessage extends ListenerAdapter {

    public Logger logger = LoggerFactory.getLogger(GuildMessage.class);

    static Pott pott = Pott.getInstance();
    String p = pott.config.getString("prefix");

    Counting counting = new Counting();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        new PingCmd(p + "ping", event);
        new HeadCmd(p + "head", event);
        new UuidCmd(p + "uuid", event);
        new MonitorCmd(p + "monitor", event);
        new MonitorCmd(p + "monitor", event);
        counting.count(event);

        //SPAM
        if (event.getMessage().getContentRaw().equalsIgnoreCase("ja")) {
            event.getChannel().sendMessage("nein").queue();
        } else if (event.getMessage().getContentRaw().equalsIgnoreCase("nein")) {
            event.getChannel().sendMessage("doch").queue();
        } else if (event.getMessage().getContentRaw().equalsIgnoreCase("bier") || event.getMessage().getContentRaw().equalsIgnoreCase("beer")) {
            event.getChannel().sendMessage("||https://www.brauerei-allersheim.de/||").queue();
            System.out.println("BIER");
        }
    }
}
