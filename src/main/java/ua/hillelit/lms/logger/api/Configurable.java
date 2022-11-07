package ua.hillelit.lms.logger.api;

import ua.hillelit.lms.logger.loggers.LoggingLevel;

public interface Configurable {

  LoggingLevel getLevel();
  String getFormat();

}
