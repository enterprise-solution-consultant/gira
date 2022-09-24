package cybersoft.javabackend.java18.gira.common.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtils {
    public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    public static final DateTimeFormatter DATETIME_FORMATTER =
        DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    public static String now() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }
}
