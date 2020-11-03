package calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class AddAndSub {
    private final Scanner scanner;
    private static final String HELP = "/help";
    private static final String EXIT = "/exit";
    private static final Pattern NUMBER_REGEX = Pattern.compile("[-+\\s]*\\d+([-+\\s]*[-+] *\\d+)*");

    public AddAndSub() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        String line;
        while (!EXIT.equals(line = scanner.nextLine().trim())) {
            if (line.isBlank()) {
                continue;
            }
            if (HELP.equals(line)) {
                System.out.println("This is my calculator, it can add and subtract numbers");
            } else if (line.charAt(0) == '/') {
                System.out.println("Unknown command");
            } else if (!NUMBER_REGEX.matcher(line).matches()) {
                System.out.println("Invalid command");
            } else {
                line = line.replaceAll("\\++", " ").replaceAll("- *-", " ").replaceAll("\\s+", " ").replaceAll("- ", "-");
                String[] numbers = line.split(" ");
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
}
