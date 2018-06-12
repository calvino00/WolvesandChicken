
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class FileUtilV4 {

    // instance variable: formatter
    private Formatter formatter;

    // instance variable: scanner;
    private Scanner scanner;

    private String openFile;

    // to check if the given path file/directory is exists
    public String checkFileDirExist(String path) {
        File x = new File(path);
        String s = "Error File/Directory Check!";
        if (x.exists()) {
            if (x.isFile()) {
                s = "'" + x.getName() + "' is a File and Exist.";
            } else if (x.isDirectory()) {
                s = "'" + x.getName() + "' is a Directory and Exist.";
            }
        } else {
            s = "This File/Directory does not exist!";
        }
        return s;
    }

    // to create a new emtpy file by given path
    public String createNewEmptyFile(String path) {
        Formatter x;
        String s = "";
        try {
            x = new Formatter(path);
            s = "File '" + path + "' created.";
        } catch (FileNotFoundException e) {
            s = "Error Creating File '" + path + "' : " + e;
        }
        return s;
    }

    // to open the file
    public String openWriteFile(String path) {
        String s = "";
        try {
            formatter = new Formatter(path);
            s = "File '" + path + "' is opening.";
        } catch (FileNotFoundException e) {
            s = "Error Opening File '" + path + "' : " + e;
        }
        return s;
    }

    // to add record/data to opening file
    public String addWriteRecord(String format, Object... objs) {
        formatter.format(format, objs);
        return "Record added to file.";
    }

    // to close opening file
    public String closeWriteFile() {
        formatter.close();
        return "File closed.";
    }

    // to open read file
    public String openReadFile(String path) {
        openFile = path;
        String s = "";
        try {
            scanner = new Scanner(new File(openFile));
            s = "File '" + path + "' is opening to read.";
        } catch (FileNotFoundException e) {
            s = "Error Opening Read File '" + path + "' : " + e;
        }
        return s;
    }

    public int readTotalLine() {
        int c = 0;
        while (this.scanner.hasNext()) {
            c++;
            this.scanner.nextLine();
        }
        return c;
    }

    public void reloadReadFile() throws FileNotFoundException {
        scanner = new Scanner(new File(openFile));
    }

    // to read opening file
    public Scanner getReadScanner() {
        return this.scanner;
    }

    // to close reading file
    public String closeReadFile() {
        scanner.close();
        return "Read file closed.";
    }
    
    public boolean checkFileExist(String path) {
        File x = new File(path);
        if (x.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
