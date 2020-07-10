package tk.fridtjof.pott;

import org.slf4j.Logger;
import tk.fridtjof.puddingapi.general.io.Config;

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
        config.setDefault("license", false);
        config.setDefault("token", "NzIwMzgxMTk3NjQ5NDQ1MDE0.XuFJSg.NI4T06SjlW8pT3AbJlEVDu0E0YE");
        config.setDefault("motd", "over the databases");
        config.setDefault("prefix", "!");
        config.setDefault("icon", "https://cdn.discordapp.com/attachments/475679464228126721/697921218560393276/sheep.gif");
    }
}
