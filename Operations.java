package calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Operations {
    private final Scanner scanner;
    private static final String HELP = "/help";
    private static final String EXIT = "/exit";
    private static final Pattern VARIABLE = Pattern.compile("(?)[a-z]+");
    private static final Pattern VARIABLES_NUMBERS = Pattern.compile("[-+\\s()^]*\\w+[() ]*([-+/*\\s() ^]*[-+/* ^] *[() ]*\\w+[() ]*)*");

    public Operations() {
        this.scanner = new Scanner(System.in);
    }

    // check if it is a valid variable, then do like variable, else fifth stage
    void start() {
        Variables variables = new Variables();
        String line;
        while (!EXIT.equals(line = scanner.nextLine().trim())) {
            if (line.isBlank()) {
                continue;
            }
            if (HELP.equals(line)) {
                System.out.println("This is my calculator, it can add, subtract, divide and multiply numbers");
            } else if (line.charAt(0) == '/') {
                System.out.println("Unknown command");
            } else if (line.contains("=")) {
                variables.operationAssignment(line);
            } else if (VARIABLE.matcher(line).matches()) {
                variables.showValue(line);
            } else if (VARIABLES_NUMBERS.matcher(line).matches()) {
                variables.checkAndCalc(line);
            } else {
                System.out.println("Invalid command");
            }
        }
        System.out.println("Bye!");
    }

}
