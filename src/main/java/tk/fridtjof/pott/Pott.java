package tk.fridtjof.pott;

import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.fridtjof.pott.listeners.GuildMsg;
import tk.fridtjof.puddingapi.general.io.Config;

import javax.security.auth.login.LoginException;

public class Pott {

    private static Pott instance;
    public static Pott getInstance() {
        return instance;
    }

    long startTime;
    public Logger logger = LoggerFactory.getLogger(Pott.class);
    public Config config = new Config("", "config");

    public static void main(String[] args) {
        new Pott();
    }

    public Pott() {
        instance = this;
        logger.info("Starting Pott!");
        logger.info("Debug: " + logger.isDebugEnabled());
        init();
    }

    private void init() {
        logger.info("Initialization...");
        new ConfigManager(config, logger);
        if(config.getBoolean("license")) {
            start();
        } else {
            logger.error("You need to agree to the license in order to run the bot. Go to config.pddg and set it to true.");
        }
    }

    private void start() {
        logger.info("Building the bot...");
        startTime = System.currentTimeMillis();

        @SuppressWarnings("deprecation")
        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
        builder.setToken(config.getString("token"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching(config.getString("motd")));

        builder.addEventListeners(new GuildMsg());

        try {
            builder.build();
        } catch (LoginException loginException) {
            logger.error("Login failed!");
            loginException.printStackTrace();
        }

        long bootTime = System.currentTimeMillis() - startTime;
        logger.info("Bot successfully build in " + bootTime + " ms!");
    }
}
