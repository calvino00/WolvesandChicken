
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);

    String[] Left = {"Wolf-1", "Wolf-2", "Wolf-3", "Chick-1", "Chick-2", "Chick-3"};
    String[] Raft = {"", "", "", "", "", ""};
    String[] Right = {"", "", "", "", "", ""};
    String[] temp = {"", "", "", "", "", ""};

    int turn = 0;
    int left_chick = 3;
    int right_chick = 0;
    int ship_chick = 0;
    int left_wolf = 3;
    int right_wolf = 0;
    int ship_wolf = 0;

    String entry;
    int x;
    int y;
    String press_enter;
    int score;
    String continue_choice;

    boolean inputX = false;
    boolean inputY = false;
    boolean move_animal = false;
    boolean continue_condition = false;

    public void Game() throws FileNotFoundException, IOException {
        RpgUtil.clearScreen();
        RpgUtil.delay(300);
        print();
        header();

        do {
            turn++;
            System.out.println("\t\t\t\t\t\t\t\t\tTurn : " + turn);
            choice();

            inputX_condition();

            add_animal();

            header();

            System.out.print("\t\t\t\t\t\t     Press the ENTER button to transport animal(s) !");
            input = new Scanner(System.in);
            press_enter = input.nextLine();

            transport();

            if (entry.equalsIgnoreCase("no")) {
                oneinput_move(x);
            } else if (entry.equalsIgnoreCase("yes")) {
                twoinput_move(x, y);
            }

            header();

            if (((left_wolf > left_chick) || (right_wolf > right_chick)) && (left_chick != 0 && right_chick != 0)) {
                wolf_win();
                Menu.back_menu();
            } else if (right_wolf == 3 && right_chick == 3) {
                break;
            }

            continue_check();

        } while (right_wolf != 3 || right_chick != 3);
        calculate_score(turn);
        chick_win();

        print_win();

        System.out.println("\t\t\t\t\t\t\t\t     Your Score : " + score);
        System.out.println("");

        Leaderboard in = new Leaderboard();
        in.Save_leaderboard(score);

    }

    public void print() {
        System.out.println("\t\t\t\t\t\t\t\t\tGAME RULES : ");
        RpgUtil.delay(300);
        System.out.println("=================================================================================================================================================================");
        RpgUtil.delay(300);
        System.out.println("  The aim of the puzzle is to transport the three wolves, and three chickens across the river");
        RpgUtil.delay(300);
        System.out.println("  The rules are as follows:");
        RpgUtil.delay(300);
        System.out.println("  1. No more than two animals can cross at once");
        RpgUtil.delay(300);
        System.out.println("  2. There must be at least one animal on the ship");
        RpgUtil.delay(300);
        System.out.println("  3. If the number of wolves on either side is ever greater than the number of chickens then the wolves will eat the chickens and you will fail");
        RpgUtil.delay(300);
        System.out.println("  This puzzle is tough, but not impossible. The minimum number of moves required is 11.");
        RpgUtil.delay(300);
        System.out.println("=================================================================================================================================================================");
        System.out.println("");
        RpgUtil.delay(300);

    }

    public void header() {

        System.out.println("\t\t\t\t\t\t\t+------------+------------+------------+");
        System.out.println("\t\t\t\t\t\t\t|    Left    |    Ship    |    Right   |");
        System.out.println("\t\t\t\t\t\t\t+------------+------------+------------+");
        for (int i = 0; i < 6; i++) {
            System.out.printf("\t\t\t\t\t\t\t| %-10s | %-10s | %-10s | %n", Left[i], Raft[i], Right[i]);
        }
        System.out.println("\t\t\t\t\t\t\t+------------+------------+------------+");

    }

    public void choice() {
        System.out.println("\t\t\t\t\t\t\t\t     1.  Wolf-1");
        System.out.println("\t\t\t\t\t\t\t\t     2.  Wolf-2");
        System.out.println("\t\t\t\t\t\t\t\t     3.  Wolf-3");
        System.out.println("\t\t\t\t\t\t\t\t     4.  Chick-1");
        System.out.println("\t\t\t\t\t\t\t\t     5.  Chick-2");
        System.out.println("\t\t\t\t\t\t\t\t     6.  Chick-3");
    }

    public void add_animal() {
        entry = "no";

        do {
            if (turn % 2 == 1) {
                if (left_chick == 1 && left_wolf == 0) {
                    break;
                } else if (left_chick == 0 && left_wolf == 1) {
                    break;
                }
            } else if (turn % 2 == 0) {
                if (right_chick == 1 && right_wolf == 0) {
                    break;
                } else if (right_chick == 0 && right_wolf == 1) {
                    break;
                }
            }

            try {
                System.out.print("\t\t\t\t\t\t    Do you want to move another animal?? (yes / no) : ");
                input = new Scanner(System.in);
                entry = input.next();

                if (!entry.equalsIgnoreCase("yes") && !entry.equalsIgnoreCase("no")) {
                    throw new IllegalArgumentException("Please input yes / no");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("\t\t\t\t\t\t\t\tError : " + e.getMessage());
            }

        } while (!entry.equalsIgnoreCase("yes") && !entry.equalsIgnoreCase("no"));

        if (entry.equalsIgnoreCase("yes")) {
            inputY_condition();

            twoinput_ship(x, y);
        } else {
            oneinput_ship(x);
        }

    }

    public void continue_check() throws IOException {
        do {
            try {
                System.out.print("\t\t\t\t\t   Do you want to continue ?? (yes = \"Go on\" / no = \"Restart game\") : ");
                input = new Scanner(System.in);
                continue_choice = input.next();

                if (continue_choice.equalsIgnoreCase("yes")) {
                    break;
                } else if (continue_choice.equalsIgnoreCase("no")) {
                    Game masuk = new Game();
                    masuk.Game();
                } else {
                    throw new IllegalArgumentException("Please input yes / no");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("\t\t\t\t\t\t\t\tError : " + e.getMessage());
            }

        } while ((!continue_choice.equalsIgnoreCase("yes")) && (!continue_choice.equalsIgnoreCase("no")));

    }

    public void inputX_condition() {
        do {
            try {
                System.out.print("\t\t\t\t\t\tChoose the first animal you would like to move to the ship = ");
                input = new Scanner(System.in);
                x = input.nextInt();

                if (x <= 0 || x > 6) {
                    throw new IllegalArgumentException("Please input a valid number from 1 to 6 !!");
                } else if (turn % 2 == 1) {
                    if (!Left[x - 1].equals("Wolf-1") && !Left[x - 1].equals("Wolf-2") && !Left[x - 1].equals("Wolf-3") && !Left[x - 1].equals("Chick-1") && !Left[x - 1].equals("Chick-2") && !Left[x - 1].equals("Chick-3")) {
                        throw new IllegalArgumentException("Your choice is not in in the list, Please input the right choice !!");
                    }
                } else if (turn % 2 == 0) {
                    if (!Right[x - 1].equals("Wolf-1") && !Right[x - 1].equals("Wolf-2") && !Right[x - 1].equals("Wolf-3") && !Right[x - 1].equals("Chick-1") && !Right[x - 1].equals("Chick-2") && !Right[x - 1].equals("Chick-3")) {
                        throw new IllegalArgumentException("Your choice is not in in the list, Please input the right choice !!");
                    }
                }

                inputX = true;

            } catch (IllegalArgumentException e) {
                System.out.println("\t\t\t\t\t\t\tError : " + e.getMessage());
                inputX = false;
            } catch (InputMismatchException e) {
                System.out.println("\t\t\t\t\t   \"ALPHABETIC INPUT DETECTED !\" Please input a valid number from 1 to 6 !!");
                inputX = false;
            }
        } while (inputX == false);
    }

    public void inputY_condition() {
        do {
            try {
                System.out.print("\t\t\t\t\t\tChoose the second animal you would like to move to the ship = ");
                input = new Scanner(System.in);
                y = input.nextInt();

                if (y == x) {
                    throw new IllegalArgumentException("Please don't input a same number again");
                } else if (y <= 0 || y > 6) {
                    throw new IllegalArgumentException("Please input a valid number from 1 to 6 !!");
                } else if (turn % 2 == 1) {
                    if (!Left[y - 1].equals("Wolf-1") && !Left[y - 1].equals("Wolf-2") && !Left[y - 1].equals("Wolf-3") && !Left[y - 1].equals("Chick-1") && !Left[y - 1].equals("Chick-2") && !Left[y - 1].equals("Chick-3")) {
                        throw new IllegalArgumentException("Your choice is not in in the list, Please input the right choice !!");
                    }
                } else if (turn % 2 == 0) {
                    if (!Right[y - 1].equals("Wolf-1") && !Right[y - 1].equals("Wolf-2") && !Right[y - 1].equals("Wolf-3") && !Right[y - 1].equals("Chick-1") && !Right[y - 1].equals("Chick-2") && !Right[y - 1].equals("Chick-3")) {
                        throw new IllegalArgumentException("Your choice is not in in the list, Please input the right choice !!");
                    }
                }

                inputY = true;

            } catch (IllegalArgumentException e) {
                System.out.println("\t\t\t\t\t\t\tError : " + e.getMessage());
                inputY = false;
            } catch (InputMismatchException e) {
                System.out.println("\t\t\t\t\t   \"ALPHABETIC INPUT DETECTED !\" Please input a valid numbe from 1 to 6 !!");
                inputY = false;
            }
        } while (inputY == false);
    }

    public void oneinput_ship(int x) {
        RpgUtil.clearScreen();
        RpgUtil.delay(300);
        if (x == 1 || x == 2 || x == 3) { //if condition player input wolf
            if (turn % 2 == 1) { //kiri ke kanan
                Raft[x - 1] = Left[x - 1];
                Left[x - 1] = temp[x - 1];
                left_wolf--;
                ship_wolf++;
            } else { //kanan ke kiri
                Raft[x - 1] = Right[x - 1];
                Right[x - 1] = temp[x - 1];
                right_wolf--;
                ship_wolf++;
            }

        } else if (x == 4 || x == 5 || x == 6) { //else condition player input chicken
            if (turn % 2 == 1) { //kiri ke kanan
                Raft[x - 1] = Left[x - 1];
                Left[x - 1] = temp[x - 1];
                left_chick--;
                ship_chick++;
            } else { //kanan ke kiri
                Raft[x - 1] = Right[x - 1];
                Right[x - 1] = temp[x - 1];
                right_chick--;
                ship_chick++;
            }
        }
    }

    public void oneinput_move(int x) {
        RpgUtil.clearScreen();
        RpgUtil.delay(300);
        if (x == 1 || x == 2 || x == 3) {// if condition player input wolf
            if (Raft[x - 1].equals("Wolf-1") || Raft[x - 1].equals("Wolf-2") || Raft[x - 1].equals("Wolf-3")) {//cek isi array tengah, kalau ada pindahkan itemnya
                if (turn % 2 == 1) {
                    Right[x - 1] = Raft[x - 1];
                    Raft[x - 1] = temp[x - 1];
                    ship_wolf--;
                    right_wolf++;
                } else {
                    Left[x - 1] = Raft[x - 1];
                    Raft[x - 1] = temp[x - 1];
                    ship_wolf--;
                    left_wolf++;
                }
            }
        } else if (x == 4 || x == 5 || x == 6) {
            if (Raft[x - 1].equals("Chick-1") || Raft[x - 1].equals("Chick-2") || Raft[x - 1].equals("Chick-3")) {
                if (turn % 2 == 1) {
                    Right[x - 1] = Raft[x - 1];
                    Raft[x - 1] = temp[x - 1];
                    ship_chick--;
                    right_chick++;
                } else {
                    Left[x - 1] = Raft[x - 1];
                    Raft[x - 1] = temp[x - 1];
                    ship_chick--;
                    left_chick++;
                }
            }
        }
    }

    public void twoinput_ship(int x, int y) {
        RpgUtil.clearScreen();
        RpgUtil.delay(300);
        if (x == 1 || x == 2 || x == 3) { //if condition player input wolf
            if (turn % 2 == 1) { //kiri ke kanan
                Raft[x - 1] = Left[x - 1];
                Left[x - 1] = temp[x - 1];
                left_wolf--;
                ship_wolf++;
            } else { //kanan ke kiri
                Raft[x - 1] = Right[x - 1];
                Right[x - 1] = temp[x - 1];
                right_wolf--;
                ship_wolf++;
            }

        } else if (x == 4 || x == 5 || x == 6) { //else condition player input chicken
            if (turn % 2 == 1) { //kiri ke kanan
                Raft[x - 1] = Left[x - 1];
                Left[x - 1] = temp[x - 1];
                left_chick--;
                ship_chick++;
            } else { //kanan ke kiri
                Raft[x - 1] = Right[x - 1];
                Right[x - 1] = temp[x - 1];
                right_chick--;
                ship_chick++;
            }
        }

        //bagian inputan y
        if (y == 1 || y == 2 || y == 3) { //if condition player input wolf
            if (turn % 2 == 1) {
                Raft[y - 1] = Left[y - 1];
                Left[y - 1] = temp[y - 1];
                left_wolf--;
                ship_wolf++;
            } else {
                Raft[y - 1] = Right[y - 1];
                Right[y - 1] = temp[y - 1];
                right_wolf--;
                ship_wolf++;
            }

        } else if (y == 4 || y == 5 || y == 6) { //else condition player input chicken
            if (turn % 2 == 1) {
                Raft[y - 1] = Left[y - 1];
                Left[y - 1] = temp[y - 1];
                left_chick--;
                ship_chick++;
            } else {
                Raft[y - 1] = Right[y - 1];
                Right[y - 1] = temp[y - 1];
                right_chick--;
                ship_chick++;
            }
        }
    }

    public void twoinput_move(int x, int y) {
        RpgUtil.clearScreen();
        RpgUtil.delay(300);
        if (x == 1 || x == 2 || x == 3) {// if condition player input wolf
            if (Raft[x - 1].equals("Wolf-1") || Raft[x - 1].equals("Wolf-2") || Raft[x - 1].equals("Wolf-3")) {//cek isi array tengah, kalau ada pindahkan itemnya
                if (turn % 2 == 1) {
                    Right[x - 1] = Raft[x - 1];
                    Raft[x - 1] = temp[x - 1];
                    ship_wolf--;
                    right_wolf++;
                } else {
                    Left[x - 1] = Raft[x - 1];
                    Raft[x - 1] = temp[x - 1];
                    ship_wolf--;
                    left_wolf++;
                }
            }
        } else if (x == 4 || x == 5 || x == 6) {
            if (Raft[x - 1].equals("Chick-1") || Raft[x - 1].equals("Chick-2") || Raft[x - 1].equals("Chick-3")) {
                if (turn % 2 == 1) {
                    Right[x - 1] = Raft[x - 1];
                    Raft[x - 1] = temp[x - 1];
                    ship_chick--;
                    right_chick++;
                } else {
                    Left[x - 1] = Raft[x - 1];
                    Raft[x - 1] = temp[x - 1];
                    ship_chick--;
                    left_chick++;
                }
            }
        }

        //bagian inputan y
        if (y == 1 || y == 2 || y == 3) {
            if (Raft[y - 1].equals("Wolf-1") || Raft[y - 1].equals("Wolf-2") || Raft[y - 1].equals("Wolf-3")) {
                if (turn % 2 == 1) {
                    Right[y - 1] = Raft[y - 1];
                    Raft[y - 1] = temp[y - 1];
                    ship_wolf--;
                    right_wolf++;
                } else {
                    Left[y - 1] = Raft[y - 1];
                    Raft[y - 1] = temp[y - 1];
                    ship_wolf--;
                    left_wolf++;
                }
            }
        } else if (y == 4 || y == 5 || y == 6) {
            if (Raft[y - 1].equals("Chick-1") || Raft[y - 1].equals("Chick-2") || Raft[y - 1].equals("Chick-3")) {
                if (turn % 2 == 1) {
                    Right[y - 1] = Raft[y - 1];
                    Raft[y - 1] = temp[y - 1];
                    ship_chick--;
                    right_chick++;
                } else {
                    Left[y - 1] = Raft[y - 1];
                    Raft[y - 1] = temp[y - 1];
                    ship_chick--;
                    left_chick++;
                }
            }
        }
    }

    public void calculate_score(int turn) {
        if (turn == 11) {
            score = 100;
        } else if (turn >= 12 && turn <= 13) {
            score = 90;
        } else if (turn >= 14 && turn <= 15) {
            score = 80;
        } else if (turn >= 16 && turn <= 17) {
            score = 70;
        } else if (turn >= 18 && turn <= 19) {
            score = 60;
        } else if (turn >= 20 && turn <= 21) {
            score = 50;
        } else if (turn >= 22 && turn <= 23) {
            score = 40;
        } else if (turn >= 24 && turn <= 25) {
            score = 30;
        } else if (turn >= 26 && turn <= 27) {
            score = 20;
        } else {
            score = 10;
        }
    }

    public void transport() {
        RpgUtil.clearScreen();
        RpgUtil.delay(300);
        System.out.print("\t\t\t\t  _____                               _   _                       _            _  __ __  \n"
                + "\t\t\t\t |_   _| _ __ _ _ _  ____ __  ___ _ _| |_(_)_ _  __ _   __ _ _ _ (_)_ __  __ _| |/ /_\\ \\ \n"
                + "\t\t\t\t   | || '_/ _` | ' \\(_-< '_ \\/ _ \\ '_|  _| | ' \\/ _` | / _` | ' \\| | '  \\/ _` | | (_-<| |\n"
                + "\t\t\t\t   |_||_| \\__,_|_||_/__/ .__/\\___/_|  \\__|_|_||_\\__, | \\__,_|_||_|_|_|_|_\\__,_|_| /__/| |\n"
                + "\t\t\t\t                       |_|                      |___/                            \\_\\ /_/ ");
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

    public void wolf_win() {
        System.out.println("");
        System.out.println("");
        RpgUtil.delay(300);

        System.out.println("\t\t\t\t\t           _\n"
                + "\t\t\t\t\t          / \\      _-'\n"
                + "\t\t\t\t\t        _/|  \\-''- _ /\n"
                + "\t\t\t\t\t __-' { |           \\\n"
                + "\t\t\t\t\t     /               \\\n"
                + "\t\t\t\t\t     /       \"o.  |o  }\n"
                + "\t\t\t\t\t     |            \\  ;\n"
                + "\t\t\t\t\t      \\             ',        _____                  _____             \n"
                + "\t\t\t\t\t        \\_         __\\      |   __|___ _____ ___   |     |_ _ ___ ___ \n"
                + "\t\t\t\t\t          ''-_    \\.//      |  |  | .'|     | -_|  |  |  | | | -_|  _|\n"
                + "\t\t\t\t\t            / '-____'       |_____|__,|_|_|_|___|  |_____|\\_/|___|_|  \n"
                + "\t\t\t\t\t           /\n"
                + "\t\t\t\t\t         _'\n"
                + "\t\t\t\t\t       _-'");
        System.out.println("");
        System.out.println("");
    }

    public void chick_win() {
        System.out.println("");
        System.out.println("");
        RpgUtil.delay(300);

        System.out.println("\t\t\t\t     __//        __   __   U  ___ u   _   _                                _   _     \n"
                + "\t\t\t\t    /.__.\\       \\ \\ / /    \\/\"_ \\/U |\"|u| |     __        __     ___     | \\ |\"|    \n"
                + "\t\t\t\t    \\ \\/ /        \\ V /     | | | | \\| |\\| |     \\\"\\      /\"/    |_\"_|   <|  \\| |>   \n"
                + "\t\t\t\t '__/    \\       U_|\"|_u.-,_| |_| |  | |_| |     /\\ \\ /\\ / /\\     | |    U| |\\  |u   \n"
                + "\t\t\t\t  \\-      )        |_|   \\_)-\\___/  <<\\___/     U  \\ V  V /  U  U/| |\\u   |_| \\_|    \n"
                + "\t\t\t\t   \\_____/     .-,//|(_       \\\\   (__) )(      .-,_\\ /\\ /_,-.-,_|___|_,-.||   \\\\,-. \n"
                + "\t\t\t\t_____|_|____    \\_) (__)     (__)      (__)      \\_)-'  '-(_/ \\_)-' '-(_/ (_\")  (_/  \n"
                + "\t\t\t\t     \" \"");
        System.out.println("");
        System.out.println("");
    }

    public void print_win() {
        System.out.print("\t\t\t\t\tHOOORAAYYY");
        RpgUtil.delay(250);
        System.out.print(" !");
        RpgUtil.delay(250);
        System.out.print("!");
        RpgUtil.delay(250);
        System.out.print(" YOU");
        RpgUtil.delay(250);
        System.out.print(" WIN");
        RpgUtil.delay(250);
        System.out.print(",");
        RpgUtil.delay(250);
        System.out.print(" Chicks");
        RpgUtil.delay(250);
        System.out.print(" are");
        RpgUtil.delay(250);
        System.out.print(" safely");
        RpgUtil.delay(250);
        System.out.print(" transported");
        RpgUtil.delay(250);
        System.out.print(" to");
        RpgUtil.delay(250);
        System.out.print(" the");
        RpgUtil.delay(250);
        System.out.print(" other");
        RpgUtil.delay(250);
        System.out.print(" side");
        RpgUtil.delay(250);
        System.out.print(" !");
        RpgUtil.delay(250);
        System.out.println("!");
        RpgUtil.delay(250);
        System.out.println("");
        System.out.println("");
    }
}
