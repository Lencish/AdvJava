import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OvningRegularExpressions {

    static List<Answers> Answers = new ArrayList<>();
    private static int ManyFound = 0;


    public static void main(String[] args) throws FileNotFoundException {

       //part 1
        String alphaBeta = "[Aa][Bb][Cc][Dd][Ee][Ff][Gg][Hh][Ii][Jj][Kk][Ll][Mm][Nn][Oo][Pp][Qq][Rr][Ss][Tt][Uu][Vv][Xx][Yy][Zz]";
        readFiles(alphaBeta, "Part 1", true);

        //Part 2
        String ola = "[Oo][Ll][Aa]";
        readFiles(ola, "Part 2", true);

        //Part 3
        String allToA35 = "[Aa]{3,5}";
        readFiles(allToA35, "Part 3", false);
        Answers.add(new Answers("Part 3", ManyFound));
        ManyFound = 0;
        readFiles(allToA35, "Part 3", true);

       //part 4
        String noAlphaNumValues = "[\\W]{2,}";
        readFiles(noAlphaNumValues, "Part 4", false);
        Answers.add(new Answers("Part 4", ManyFound));
        ManyFound = 0;

        //part 5
        String emails = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}";
        readFiles(emails, "Part 5", false);
        Answers.add(new Answers("Part 5", ManyFound));
        ManyFound = 0; //reset
        readFiles(emails, "Part 5", true);

        //part 6
        String allPhoneNr = "0{1,2}[0-9]{1,3}[\\s/-]?[0-9]{5,}";
        readFiles(allPhoneNr, "Part 6", false);
        Answers.add(new Answers("Part 6", ManyFound));
        ManyFound = 0; //reset
        readFiles(allPhoneNr, "Part 6", true);

        //part 7
        String fourKind = "([A-Za-z])\\1{3}";
        readFiles(fourKind, "Part 7", false);
        Answers.add(new Answers("Part 7", ManyFound));
        ManyFound = 0;

        //part 8
        String licensePl = "[A-Za-z]{3}[/_][0-9]{3}";
        readFiles(licensePl, "Part 8", true);

        //Part 9
        String matchingPar = "[(][A-Za-z]+[)]|[(][0-9]+[)]|[(][\\s]+[)]";
        Answers.add(new Answers("Part 9", ManyFound));
        ManyFound = 0; //reset
        readFiles(matchingPar, "Part 9", true);

        // Part 10
        String ifOrF = "if[\\s]?[(]|for[\\s]?[(]";
        readFiles(ifOrF, "Part 10", true);
        System.out.println(Answers);
    }
//finder
    public static void finder(String inputString, String pattern, String showPart, int currentLine, boolean gettingPosition)  {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(inputString);


        while (matcher.find()) {
            if (matcher.group().length() != 0) {
                if (gettingPosition) {
                    Answers.add(new Answers(showPart, matcher.group(), currentLine, matcher.start(), matcher.end()));
                } else {
                    ManyFound++;
                }

            }
        }
    }
//Scanner
    public static void readFiles(String inputPattern, String showPart, boolean gettingPosition) throws FileNotFoundException {
        File file = new File("text.txt");
        Scanner fileRead = new Scanner(file);

        int counter = 1;

        while (fileRead.hasNextLine()) {
            String thisLineAsString = fileRead.nextLine();

            finder(thisLineAsString, inputPattern, showPart, counter, gettingPosition);

            counter++;
        }
        fileRead.close();
    }

}