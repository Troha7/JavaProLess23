package ua.hillelit.lms.logger.loggers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import ua.hillelit.lms.logger.api.Configurable;

public class StdoutLoggerConfiguration implements Configurable {

  private LoggingLevel level;
  private String format;

  private final String PATH = "src/main/resources/loggerConfig.properties";

  public StdoutLoggerConfiguration(LoggingLevel level, String format) {
    this.level = level;
    this.format = format;
  }

  public StdoutLoggerConfiguration() {
    load();
  }

  public void load() {
    try (InputStream input = Files.newInputStream(
        Paths.get(PATH))) {

      Properties prop = new Properties();

      // load a properties file
      prop.load(input);

      // get the property value

      level = levelConfig(prop.getProperty("logger.level"));
      format = prop.getProperty("logger.format");

    } catch (IOException ex) {
      ex.printStackTrace();
    }

    new StdoutLoggerConfiguration(level, format);
  }

  private LoggingLevel levelConfig(String config) {

    switch (LoggingLevel.valueOf(config)) {
      case INFO:
        return LoggingLevel.INFO;
      case DEBUG:
        return LoggingLevel.DEBUG;
      default:
        return null;
    }

  }

  public LoggingLevel getLevel() {
    return level;
  }

  public String getFormat() {
    return format;
  }

}
