
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    static int x;
    static Scanner input;
    static String w;

    public static void main(String[] args) throws IOException {
        menu();
    }

    public static void menu() throws IOException {
        boolean input_menu = false;

        RpgUtil.clearScreen();
        RpgUtil.delay(300);
        print_menu();

        do {
            try {
                System.out.print("\t\t\t\t\t\t\t\t☛ ℂ𝕙𝕠𝕚𝕔𝕖: ");
                input = new Scanner(System.in);
                x = input.nextInt();

                if (x <= 0 || x > 4) {
                    throw new IllegalArgumentException("Please input a valid number from 1 to 4 !!");
                } else if (x >= 1 || x <= 4) {
                    input_menu = true;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("\t\t\t\t\t\tError : " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("\t\t\t\t\t\"ALPHABETIC INPUT DETECTED !\" Please input a valid number between 1 to 4 !!");
            }
        } while (input_menu == false);

        choice(x);
    }

    public static void print_menu() {
        welcome_menu();
        RpgUtil.delay(500);
        menuchoice();
    }

    public static void choice(int x) throws IOException {
        switch (x) {
            case 1:
                loading();
                Game in1 = new Game();
                in1.Game();
                break;

            case 2:
                loading();
                Leaderboard in2 = new Leaderboard();
                in2.load_leaderboard();
                break;

            case 3:
                loading();
                About.print();
                break;

            case 4:
                exit();
                break;

            default:
                System.out.println("NO choice detected");

        }
        System.out.println("");
        back_menu();
    }

    public static void back_menu() throws IOException {
        do {
            try {
                System.out.print("\t\t\t\t\t\t     Back to Main Menu? (yes / no = QUIT GAME ) : ");
                input = new Scanner(System.in);
                w = input.next();

                if (w.equalsIgnoreCase("yes")) {
                    Menu masuk = new Menu();
                    masuk.menu();
                } else if (w.equalsIgnoreCase("no")) {
                    exit();
                } else {
                    throw new IllegalArgumentException("Please input yes / no");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("\t\t\t\t\t\t\t\tError : " + e.getMessage());
            }

        } while (!w.equalsIgnoreCase("yes") && !w.equalsIgnoreCase("no"));
    }

    public static void welcome_menu() {
        System.out.println("\t\t __          __  _                              _______           \t      _..::::.._   \n"
                + "\t\t \\ \\        / / | |                            |__   __|            \t    .:::::/ |::::. \n"
                + "\t\t  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___       | | ___          \t   ::::::/ V|:::::: \n"
                + "\t\t   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\      | |/ _ \\    \t\t  ::::::/'  |:::::::\n"
                + "\t\t    \\  /\\  /  __/ | (_| (_) | | | | | |  __/      | | (_) |         \t  ::::<_,   (:::::::\n"
                + "\t\t     \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|      |_|\\___/     \t\t   :::::|    \\:::::\n"
                + "\t\t                                                                    \t    '::/      \\::'");

        System.out.println("\t\t\t\t __          __   _                                     _    _____ _     _      _                    ,~~.   \n"
                + "\t\t\t\t \\ \\        / /  | |                    /\\             | |  / ____| |   (_)    | |             ,    (  ^ )> \n"
                + "\t\t\t\t  \\ \\  /\\  / /__ | |_   _____  ___     /  \\   _ __   __| | | |    | |__  _  ___| | _____        )`~~'   (  \n"
                + "\t\t\t\t   \\ \\/  \\/ / _ \\| \\ \\ / / _ \\/ __|   / /\\ \\ | '_ \\ / _` | | |    | '_ \\| |/ __| |/ / __|      (  .__)   ) \n"
                + "\t\t\t\t    \\  /\\  / (_) | |\\ V /  __/\\__ \\  / ____ \\| | | | (_| | | |____| | | | | (__|   <\\__ \\       `-.____,'  \n"
                + "\t\t\t\t     \\/  \\/ \\___/|_| \\_/ \\___||___/ /_/    \\_\\_| |_|\\__,_|  \\_____|_| |_|_|\\___|_|\\_\\___/     .  .  .  .  .  \n");

        System.out.println("");
    }

    public static void menuchoice() {
        RpgUtil.delay(300);
        System.out.println("\t\t\t✲´*。.❄¨¯`*✲。❄。*。¨¯`*✲✲´*。.❄¨¯`*✲。❄。*。¨¯`*✲✲´*。.❄¨¯`*✲。❄。*。¨¯`*✲✲´*。.❄¨¯`*✲。❄。*。¨¯`*✲");
        System.out.println("\t\t\t\t\t\t\t  .-.   .-..----..-. .-..-. .-.\n"
                + "\t\t\t\t\t\t\t  |  `.'  || {_  |  `| || { } |\n"
                + "\t\t\t\t\t\t\t  | |\\ /| || {__ | |\\  || {_} |\n"
                + "\t\t\t\t\t\t\t  `-' ` `-'`----'`-' `-'`-----'");

        RpgUtil.delay(300);
        System.out.println("");
        System.out.println("\t\t\t\t\t\t\t\t✧ 𝟙. ℕ𝕖𝕨 𝔾𝕒𝕞𝕖 ✧");
        RpgUtil.delay(300);
        System.out.println("\t\t\t\t\t\t\t\t♕ 𝟚. 𝕃𝕖𝕒𝕕𝕖𝕣𝕓𝕠𝕒𝕣𝕕 ♕");
        RpgUtil.delay(300);
        System.out.println("\t\t\t\t\t\t\t\t✽ 𝟛. 𝔸𝕓𝕠𝕦𝕥 ✽");
        RpgUtil.delay(300);
        System.out.println("\t\t\t\t\t\t\t\t➤ 𝟜. 𝔼𝕩𝕚𝕥 ➤");
        RpgUtil.delay(300);

    }

    public static void exit() {
        RpgUtil.clearScreen();
        RpgUtil.delay(300);
        System.out.println();
        System.out.println("\t\t\t\t\t\t\t _____ _                 _                     \n"
                + "\t\t\t\t\t\t\t/__   \\ |__   __ _ _ __ | | __ /\\_/\\___  _   _ \n"
                + "\t\t\t\t\t\t\t  / /\\/ '_ \\ / _` | '_ \\| |/ / \\_ _/ _ \\| | | |\n"
                + "\t\t\t\t\t\t\t / /  | | | | (_| | | | |   <   / \\ (_) | |_| |\n"
                + "\t\t\t\t\t\t\t \\/   |_| |_|\\__,_|_| |_|_|\\_\\  \\_/\\___/ \\__,_|\n"
                + "\t\t\t\t\t\t\t                                               ");
        RpgUtil.delay(300);
        System.out.println("\t\t\t\t\t\t\t\t\t   ___          \n"
                + "\t\t\t\t\t\t\t\t\t  / __\\__  _ __ \n"
                + "\t\t\t\t\t\t\t\t\t / _\\/ _ \\| '__|\n"
                + "\t\t\t\t\t\t\t\t\t/ / | (_) | |   \n"
                + "\t\t\t\t\t\t\t\t\t\\/   \\___/|_|   \n"
                + "\t\t\t\t\t\t\t\t\t                ");
        RpgUtil.delay(300);
        System.out.println("\t\t\t\t\t   __       _             _               _____ _              ___                     \n"
                + "\t\t\t\t\t  /__\\ __  (_) ___  _   _(_)_ __   __ _  /__   \\ |__   ___    / _ \\__ _ _ __ ___   ___ \n"
                + "\t\t\t\t\t /_\\| '_ \\ | |/ _ \\| | | | | '_ \\ / _` |   / /\\/ '_ \\ / _ \\  / /_\\/ _` | '_ ` _ \\ / _ \\\n"
                + "\t\t\t\t\t//__| | | || | (_) | |_| | | | | | (_| |  / /  | | | |  __/ / /_\\\\ (_| | | | | | |  __/\n"
                + "\t\t\t\t\t\\__/|_| |_|/ |\\___/ \\__, |_|_| |_|\\__, |  \\/   |_| |_|\\___| \\____/\\__,_|_| |_| |_|\\___|\n"
                + "\t\t\t\t\t         |__/       |___/         |___/                                                ");
        RpgUtil.delay(300);
        System.out.println("");
        System.out.println("");

        System.exit(0);
    }

    public static void loading() {
        RpgUtil.clearScreen();
        RpgUtil.delay(300);
        System.out.print("\t\t\t\t\t\t\t  _                 _ _           \n"
                + "\t\t\t\t\t\t\t | |   ___  __ _ __| (_)_ _  __ _ \n"
                + "\t\t\t\t\t\t\t | |__/ _ \\/ _` / _` | | ' \\/ _` |\n"
                + "\t\t\t\t\t\t\t |____\\___/\\__,_\\__,_|_|_||_\\__, |\n"
                + "\t\t\t\t\t\t\t                            |___/ ");
        System.out.print(" . ");
        RpgUtil.delay(300);
        System.out.print(" . ");
        RpgUtil.delay(300);
        System.out.print(" . ");
        RpgUtil.delay(300);
        System.out.print(" . ");
        RpgUtil.delay(300);
        System.out.println(" . ");
    }

}
