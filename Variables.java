package calculator;

import java.util.HashMap;
import java.util.Map;
/*
* This class provide variables
* it has three methods
* variables consist only of english letters */
public class Variables {
    private final Map<String, Integer> variables = new HashMap<>();

    public void operationAssignment(String line) {
        String[] arguments = AddAndSub.getRidOfExtraPMS(line).replaceAll("\\s+", "").split("=");
        if (arguments.length != 2) {
            System.out.println("Invalid assignment");
        }
        if (arguments[0].matches("(?i).*[^a-z]+.*")) {
            System.out.println("Invalid identifier");
        } else if (arguments[1].matches("(?i).*([^a-z\\d-]|-?[a-z]+\\d+|-?\\d+[a-z]+)+.*")) {
            System.out.println("Invalid assignment");
        }
        if (arguments[1].matches("-?[\\d]+")) {
            variables.put(arguments[0], Integer.parseInt(arguments[1]));
        } else if (arguments[1].matches("(?i)-?[a-z]+")) {
            String temp = arguments[1].charAt(0) == '-' ? arguments[1].substring(1) : arguments[1];
            if (variables.get(temp) != null) {
                variables.put(arguments[0], arguments[1].charAt(0) == '-' ? variables.get(temp) * -1 : variables.get(temp));
            } else {
                System.out.println("Unknown variable");
            }
        }
    }

    public void showValue(String line) {
        if (variables.get(line) != null) {
            System.out.println(variables.get(line));
        } else {
            System.out.println("Unknown variable");
        }
    }

    public void calculate(String line) {
        String[] arguments = AddAndSub.getRidOfExtraPMS(line).split("\\s+");
        int sum = 0;
        for (var i : arguments) {
            if (i.matches("[+-]?\\d+")) {
                sum += Integer.parseInt(i.charAt(0) == '+' ? i.substring(1) : i);
                continue;
            } else if (i.matches("(?i)[+-]?[a-z]+")) {
                String temp = i.charAt(0) == '+' || i.charAt(0) == '-' ? i.substring(1) : i;
                boolean isMinus = i.charAt(0) == '-';
                if (!variables.containsKey(temp)) {
                    System.out.println("Unknown variable");
                    return;
                }
                sum += isMinus ? -1 * variables.get(temp) : variables.get(temp);
                continue;
            }
            System.out.println("Smth is went wrong");
            return;
        }
        System.out.println(sum);
    }
}
