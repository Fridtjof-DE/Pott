package me.fridtjof.pott;

import org.slf4j.Logger;
import me.fridtjof.puddingapi.general.io.Config;

public class ConfigManager {

    Logger logger;
    Config config;

    public ConfigManager(Config config, Logger logger) {
        this.logger = logger;
        this.config = config;
        setDefaults();
    }

    private void setDefaults() {
        logger.info("Settings configuration defaults...");
        config.setDefault("eula", false);
        config.setDefault("token", "your token");
        config.setDefault("motd", "over the databases");
        config.setDefault("prefix", "!");
        config.setDefault("icon", "https://cdn.discordapp.com/attachments/475679464228126721/697921218560393276/sheep.gif");
    }
}
