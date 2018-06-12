
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Leaderboard {

    Scanner entry = new Scanner(System.in);

    String SaveFile = "game.data";
    String txtFormat = "%s,%d%n";

    public void Save_leaderboard(int score) throws FileNotFoundException {
        CheckFileExist();

        System.out.print("\t\t\t\t\t\t\t\t   Input your Name: ");
        entry = new Scanner(System.in);
        String name = entry.next();

        Player newPlayer = new Player(name, score);

        FileUtilV4 futil = new FileUtilV4();
        futil.openReadFile(SaveFile);

        int size = futil.readTotalLine();

        futil.reloadReadFile();

        Player[] players = new Player[(size + 1)];
        int i = 0;
        while (futil.getReadScanner().hasNext()) {
            String[] readLine = futil.getReadScanner().next().split(",");
            players[i] = new Player(readLine[0], Integer.parseInt(readLine[1]));
            i++;
        }

        futil.closeReadFile();

        players[size] = newPlayer;

        bsortPlayer(players);
        futil.createNewEmptyFile(SaveFile);
        futil.openWriteFile(SaveFile);

        for (int j = 0; j < players.length; j++) {
            futil.addWriteRecord(txtFormat, players[j].name, players[j].score);
        }

        futil.closeWriteFile();

        System.out.println("\t\t\t\t\t\t\t  Thankyou for playing the game, " + name);
        System.out.println("");
        System.out.println("\t\t\t\t\t\t\t  Check your rank in the leaderboard :))");
    }

    public void load_leaderboard() {
        RpgUtil.clearScreen();
        RpgUtil.delay(300);

        CheckFileExist();

        FileUtilV4 futil = new FileUtilV4();
        futil.openReadFile(SaveFile);

        leaderboard_header();

        int r = 1;
        while (futil.getReadScanner().hasNext()) {
            String[] readLine = futil.getReadScanner().next().split(",");
            String playerName = readLine[0];
            int score = Integer.parseInt(readLine[1]);
            System.out.printf("\t\t\t\t\t\t|\t %-3d       |     %-10s    |        %-3d       | %n", r, playerName, score);
            r++;
        }
        leaderboard_footer();

        futil.closeReadFile();
        System.out.println();
    }

    public void CheckFileExist() {
        File x = new File(SaveFile);

        if (!x.exists()) {
            FileUtilV4 futil = new FileUtilV4();
            futil.createNewEmptyFile(SaveFile);
        }
    }

    public Player[] bsortPlayer(Player[] arrs) {
        Player temp;
        for (int i = 0; i < arrs.length - 1; i++) {
            for (int j = 0; j < arrs.length - 1; j++) {
                if (arrs[j].score < arrs[j + 1].score) {
                    temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                }
            }
        }
        return arrs;
    }

    public void leaderboard_header() {
        System.out.println("\t\t          o    \t\t _        ___   ____  ___      ___  ____   ____    ___    ____  ____   ___   \t           o\n"
                + "\t\t       o^/|\\^o        \t| |      /  _] /    ||   \\    /  _]|    \\ |    \\  /   \\  /    ||    \\ |   \\  \t        o^/|\\^o\n"
                + "\t\t    o_^|\\/*\\/|^_o    \t| |     /  [_ |  o  ||    \\  /  [_ |  D  )|  o  )|     ||  o  ||  D  )|    \\ \t     o_^|\\/*\\/|^_o\n"
                + "\t\t   o\\*`'.\\|/.'`*/o     \t| |___ |    _]|     ||  D  ||    _]|    / |     ||  O  ||     ||    / |  D  |\t    o\\*`'.\\|/.'`*/o\n"                + "\t\t    \\\\\\\\\\\\|//////    \t|     ||   [_ |  _  ||     ||   [_ |    \\ |  O  ||     ||  _  ||    \\ |     |\t     \\\\\\\\\\\\|//////\n"
                + "\t\t     {><><@><><}       \t|     ||     ||  |  ||     ||     ||  .  \\|     ||     ||  |  ||  .  \\|     |\t      {><><@><><}\n"
                + "\t\t     `\"\"\"\"\"\"\"\"\"`      \t|_____||_____||__|__||_____||_____||__|\\_||_____| \\___/ |__|__||__|\\_||_____|\t      `\"\"\"\"\"\"\"\"\"`\n"
                + "\t\t\t\t\t                                                                             ");

        System.out.println();
        System.out.println();
        System.out.println("\t\t\t\t\t\t+------------------+-------------------+------------------+");
        System.out.println("\t\t\t\t\t\t|      ℝ𝔸ℕ𝕂       |   ℙ𝕃𝔸𝕐𝔼ℝ ℕ𝔸𝕄𝔼   |      𝕊ℂ𝕆ℝ𝔼      |");
        System.out.println("\t\t\t\t\t\t+------------------+-------------------+------------------+");

    }

    public void leaderboard_footer() {
        System.out.println("\t\t\t\t\t\t+------------------+-------------------+------------------+");
    }

}
