package me.fridtjof.pott;

import me.fridtjof.pott.listeners.GuildMessage;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import me.fridtjof.puddingapi.general.io.Config;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pott {

    private String[] args;

    private static Pott instance;
    public static Pott getInstance() {
        return instance;
    }

    public static JDA shardMan;
    public static JDABuilder builder;

    long startTime;
    public Logger logger = LoggerFactory.getLogger(Pott.class);
    public Config config = new Config("", "config");
    public Config countingData = new Config("", "counting_data");

    public static void main(String[] args) {
        new Pott();
    }

    public Pott() {
        this.args = args;
        instance = this;
        logger.info("Starting Pott!");
        logger.info("Debug: " + logger.isDebugEnabled());
        init();
    }

    private void init() {
        logger.info("Initialization...");
        new ConfigManager(config, logger);
        if(config.getBoolean("eula")) {
            start();
        } else {
            logger.error("You need to agree to the eula in order to run the bot. Go to config.pddg and set it to true.");
        }
    }

    private void start() {
        logger.info("Building the bot...");
        startTime = System.currentTimeMillis();

        builder = JDABuilder.createDefault(config.getString("token"));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching(config.getString("motd")));
        builder.addEventListeners(new GuildMessage());

        shardMan = builder.build();

        long bootTime = System.currentTimeMillis() - startTime;
        logger.info("Bot successfully build in " + bootTime + " ms!");

        stop();
    }


    public void stop() {
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                if(reader.readLine().equals("stop") || reader.readLine().equals("stop")) {
                    reader.close();
                    builder.setStatus(OnlineStatus.OFFLINE);
                    logger.info("Stopping...");
                    shardMan.shutdownNow();
                    logger.info("Shutdown erfolgreich");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
