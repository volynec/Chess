import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static final String INPUT_FILE = "INPUT.TXT";
    public static final String OUTPUT_FILE = "OUTPUT.TXT";
    static String remark = "";

    public static void main(String[] args) {
        try {
            parseFileAndCalculate();
            writeFile();
        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода/выводв! ");
            System.out.println("Подробная отладочная информация: ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Произошла неизвестная ошибка! ");
            System.out.println("Подробная отладочная информация: ");
            e.printStackTrace();
        }

    }

    private static void parseFileAndCalculate() throws IOException {
//        String remark = "";
        Scanner scanner = new Scanner(Paths.get(INPUT_FILE));
        String line = scanner.nextLine().toUpperCase();
        Pattern p = Pattern.compile("[A-H]{1}[1-8]{1}[-\\w]{1}[A-H]{1}[1-8]{1}");
        Matcher m = p.matcher(line);
        boolean ft = m.matches();
        if (!ft) {
            remark = "ERROR";
            scanner.close();
            return;
        }

        char[] chars = line.toCharArray();

        int y1 = Integer.parseInt(String.valueOf(chars[1]));
        int y2 = Integer.parseInt(String.valueOf(chars[4]));
        int x1 = charToInt(chars[0]);
        int x2 = charToInt(chars[3]);

        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);


        if ((dx == 1 & dy == 2) || (dx == 2 & dy == 1))
            remark = "YES";
        else
            remark = "NO";

        scanner.close();


    }


    private static void writeFile() throws IOException {
        try {
            FileWriter fw = new FileWriter(OUTPUT_FILE);
            Writer output = new BufferedWriter(fw);
            output.write(remark);

            output.flush();
            output.close();
            fw.close();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public static int charToInt(char c) {
        switch (c) {
            case 'A':
                return 1;

            case 'B':
                return 2;
            case 'C':
                return 3;
            case 'D':
                return 4;
            case 'E':
                return 5;
            case 'F':
                return 6;
            case 'G':
                return 7;
            case 'H':
                return 8;
        }
        return 0;
    }
}