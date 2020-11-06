package calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class AddAndSub {
    private final Scanner scanner;
    private static final String HELP = "/help";
    private static final String EXIT = "/exit";
    private static final Pattern NUMBER_REGEX = Pattern.compile("[-+\\s]*\\d+([-+\\s]*[-+] *\\d+)*");
    private static final Pattern VARIABLE = Pattern.compile("(?)[a-z]+");
    private static final Pattern VARIABLES_NUMBERS = Pattern.compile("[-+\\s]*\\w+([-+\\s]*[-+] *\\w+)*");

    public AddAndSub() {
        this.scanner = new Scanner(System.in);
    }

    // check if it is a valid variable, then do like variable, else fifth stage
    public void start() {
        Variables variables = new Variables();
        String line;
        while (!EXIT.equals(line = scanner.nextLine().trim())) {
            if (line.isBlank()) {
                continue;
            }
            if (HELP.equals(line)) {
                System.out.println("This is my calculator, it can add and subtract numbers");
            } else if (line.charAt(0) == '/') {
                System.out.println("Unknown command");
            } else if (line.contains("=")) {
                variables.operationAssignment(line);
            } else if (VARIABLE.matcher(line).matches()) {
                variables.showValue(line);
            } else if (VARIABLES_NUMBERS.matcher(line).matches()) {
                variables.calculate(line);
            } else if (!NUMBER_REGEX.matcher(line).matches()) {
                System.out.println("Invalid command");
            } else {
                String[] numbers = getRidOfExtraPMS(line).split(" ");
                int sum = 0;
                for (var i : numbers) {
                    if (!i.isBlank()) {
                        sum += Integer.parseInt(i);
                    }
                }
                System.out.println(sum);
            }
        }
        System.out.println("Bye!");
    }

    static String getRidOfExtraPMS(String line) {
        return line.replaceAll("\\++", " ").replaceAll("- *-", " ").replaceAll("\\s+", " ").replaceAll("- ", "-");
    }
}
