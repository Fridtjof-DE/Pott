package me.fridtjof.pott.cmds;

import me.fridtjof.pott.Pott;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Counting {

    static Pott pott = Pott.getInstance();

    String channelId;
    String msg;
    int msgNumber;
    int searchedNumber;
    String lastUserId = "0";
    String newUserId = "1";

    public Counting() {
    }

    public void count(MessageReceivedEvent event) {
        msg = event.getMessage().getContentRaw();
        newUserId = event.getAuthor().getId();
        channelId = event.getChannel().getId();

        System.out.println("---------------" + pott.countingData.getString(channelId + "_searched"));

        if(pott.countingData.getString(channelId + "_searched") != null) {
            searchedNumber = Integer.parseInt(pott.countingData.getString(channelId + "_searched"));
            lastUserId = pott.countingData.getString(channelId + "_blocked");

            if (isNumeric(msg)) {
                msgNumber = Integer.parseInt(msg);

                if (lastUserId.equals(newUserId)) {
                    event.getMessage().addReaction(Emoji.fromFormatted("🛑")).queue();
                    event.getChannel().sendMessage("Du bist noch nicht wieder an der Reihe **" + event.getAuthor().getName() + "**!").queue();

                } else {
                    if (searchedNumber == msgNumber) {
                        searchedNumber = searchedNumber + 1;
                        event.getMessage().addReaction(Emoji.fromFormatted("✅")).queue();
                    } else {
                        searchedNumber = 1;
                        event.getMessage().addReaction(Emoji.fromFormatted("❌")).queue();
                    }

                    pott.countingData.setValue(channelId + "_searched", searchedNumber + "");
                }

                pott.countingData.setValue(channelId + "_blocked", newUserId);
            }
        }
    }



    public static boolean isNumeric(String string) {
        int intValue;


        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
}
