package ws.nmathe.saber.utils;

import ws.nmathe.saber.Main;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * used for logging information to the console
 */
public class Logging
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    /**
     * used for most general logging (level 5)
     * @param caller the java class from which the command is called
     * @param msg the message to log
     */
    public static void info(Class caller, String msg)
    {
        if(Main.getBotSettingsManager().getLogLevel() < 5) return;

        String now = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);
        String content = "[" + now + "] [Info]" +
                ANSI_RESET + " " + ANSI_CYAN_BACKGROUND + ANSI_BLACK +
                "[" + caller.getSimpleName() + "]" +
                ANSI_RESET + " " + msg +
                ANSI_RESET;

        System.out.println(content);
    }

    /**
     * used only for event announcing (level 4)
     * @param caller the java class from which the command is called
     * @param msg the message to log
     */
    public static void event(Class caller, String msg)
    {
        if(Main.getBotSettingsManager().getLogLevel() < 4) return;

        String now = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);
        String content = "[" + now + "] [Info]" +
                ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + ANSI_BLACK +
                "[" + caller.getSimpleName() + "]" +
                ANSI_RESET + " " + msg +
                ANSI_RESET;

        System.out.println(content);
    }

    /**
     * used for logging command usage (level 2)
     * @param caller the java class from which the command is called
     * @param msg the message to log
     */
    public static void cmd(Class caller, String msg)
    {
        if(Main.getBotSettingsManager().getLogLevel() < 3) return;

        String now = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);
        String content = "[" + now + "] [Cmnd]" +
                ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + ANSI_BLACK +
                "[" + caller.getSimpleName() + "]" +
                ANSI_RESET + " " + msg +
                ANSI_RESET;

        System.out.println(content);
    }

    /**
     * used logging minor (possibly expected) errors (level 2)
     * @param caller the java class from which the command is called
     * @param msg the message to log
     */
    public static void warn(Class caller, String msg)
    {
        if(Main.getBotSettingsManager().getLogLevel() < 2) return;

        String now = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);
        String content = "[" + now + "] " +
                ANSI_RED + "[Warn]" + ANSI_RESET + " " +
                ANSI_YELLOW_BACKGROUND + ANSI_BLACK +
                "[" + caller.getSimpleName() + "]" +
                ANSI_RESET + " " + ANSI_RED + msg +
                ANSI_RESET;

        System.out.println(content);
    }

    /**
     * used for logging significant (unexpected) errors (level 1)
     * @param caller the java class from which the command is called
     * @param error the error
     */
    public static void exception(Class caller, Throwable error)
    {
        if(Main.getBotSettingsManager().getLogLevel() < 1) return;

        String now = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);
        String content = "[" + now + "] " +
                ANSI_PURPLE + "[Excp]" + ANSI_RESET + " " +
                ANSI_PURPLE_BACKGROUND + ANSI_BLACK +
                "[" + caller.getSimpleName() + "]" +
                ANSI_RESET + " " + ANSI_PURPLE + error.getMessage() +
                ANSI_RESET;
        System.out.println(content);
        error.printStackTrace();
    }
}
