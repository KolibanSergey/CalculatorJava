import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        exp = exp.replaceAll(" ", "");
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            throw new IllegalArgumentException("строка не является математической операцией");
        }
        String[] data = exp.split(regexActions[actionIndex]);
        if (data.length != 2) {
            throw new IllegalArgumentException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            int result;
            if ((a < 0) || (a > 10) || (b < 0) || (b > 10)) {
                throw new IllegalArgumentException("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более");
            }
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            if (isRoman) {
                if (result < 1) {
                    throw new NumberFormatException("в римской системе нет отрицательных чисел");
                }
                System.out.println(converter.intToRoman(result));
            } else {
                System.out.println(result);
            }
        } else {
            throw new NumberFormatException("используются одновременно разные системы счисления");
        }
    }
}