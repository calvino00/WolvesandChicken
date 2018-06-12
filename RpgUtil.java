import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.Date;

public class RpgUtil {

    // update this variable to TRUE if you in development mode
    public static boolean NETBEANS_MODE = true;

    // constanta variable untuk FOREGROUND COLOR CLI  
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = " \u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // constanta variable untuk BACKGROUND COLOR CLI    
    public static final String ANSI_BG_RED = "\u001B[41m";
    public static final String ANSI_BG_GREEN = "\u001B[42m";
    public static final String ANSI_BG_YELLOW = "\u001B[43m";
    public static final String ANSI_BG_BLUE = "\u001B[44m";
    public static final String ANSI_BG_PURPLE = "\u001B[45m";
    public static final String ANSI_BG_CYAN = "\u001B[46m";
    public static final String ANSI_BG_WHITE = "\u001B[47m";

    // untuk mengembalikan nilai random dari rentang int min - max
    public static int randInt(int min, int max) {
        return ((int) (Math.random() * ((max - min) + 1)) + min);
    }

    // untuk mengembalikan nilai random dari rentang double min - max
    public static double randDouble(double min, double max) {
        return (Math.random() * ((max - min) + 1) + min);
    }

    // untuk membesihkan layar CLI
    public static void clearScreen() {
        try {
            if (NETBEANS_MODE) {
                // clear screen di NetBeans Output Window
                Robot pressbot = new Robot();
                pressbot.keyPress(17); // Holds CTRL key.
                pressbot.keyPress(76); // Holds L key.
                pressbot.keyRelease(17); // Releases CTRL key.
                pressbot.keyRelease(76); // Releases L key.        
            } else {
                // clear screen di Windows CMD & Linux Terminal
                final String os = System.getProperty("os.name");
                if (os.contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    System.out.print("\033[H\033[2J");
                }
            }
        } catch (AWTException | IOException | InterruptedException e) {
            System.out.println("Error: " + e);
        }
    }

    // untuk delay/pause proses selama mili-detik
    public static void delay(long milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException ex) {
        }
    }

    // untuk mengambil tanggal dan waktu saat ini
    public static Date getSysDate() {
        return new Date();
    }

    // untuk konversi inputan Integer tangga, bulan, tahun, jam, menit, detik
    // kedalam format Date
    public static Date toDate(int date, int month, int year, int hour, int minute, int sec) {
        Date d = new Date();
        d.setDate(date);
        d.setMonth(month);
        d.setYear(year);
        d.setHours(hour);
        d.setMinutes(minute);
        d.setSeconds(sec);
        return d;
    }
}
