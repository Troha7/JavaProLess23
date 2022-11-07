package ua.hillelit.lms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import org.junit.Test;
import ua.hillelit.lms.logger.loggers.FileLoggerConfiguration;
import ua.hillelit.lms.logger.loggers.LoggingLevel;

public class FileLoggerConfigurationTest {

  FileLoggerConfiguration fileLoggerConfiguration = new FileLoggerConfiguration();

  @Test()
  public void testLoggerConfigFilePath() {
    String path = "src/main/resources/loggerConfig.properties";

    File file = new File(path);
    String absolutePath = file.getAbsolutePath();

    assertTrue(absolutePath.endsWith("src/main/resources/loggerConfig.properties"));
  }

  @Test()
  public void testGetFile() {
    String file = fileLoggerConfiguration.getFile();
    assertTrue(file.endsWith("src/main/resources/logs/log.txt"));
  }

  @Test()
  public void testGetLevel() {
    LoggingLevel level = fileLoggerConfiguration.getLevel();
    assertTrue(level == LoggingLevel.DEBUG || level == LoggingLevel.INFO);
  }

  @Test()
  public void testGetMaxSize() {
    long maxSize = fileLoggerConfiguration.getMaxSize();
    assertTrue(maxSize > 0);
  }

  @Test()
  public void testGetFormat() {
    String format = fileLoggerConfiguration.getFormat();
    String[] substring = format.split("%s");
    assertEquals(4, substring.length);
  }

}
