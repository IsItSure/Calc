import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Integer> romanNumerals = new HashMap<>();

    static {
        romanNumerals.put("I", 1);
        romanNumerals.put("II", 2);
        romanNumerals.put("III", 3);
        romanNumerals.put("IV", 4);
        romanNumerals.put("V", 5);
        romanNumerals.put("VI", 6);
        romanNumerals.put("VII", 7);
        romanNumerals.put("VIII", 8);
        romanNumerals.put("IX", 9);
        romanNumerals.put("X", 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение (например, 2 + 2 или II + II):");
        String input = scanner.nextLine();

        String[] parts = input.split(" ");

        if (parts.length != 3) {
            System.out.println("Некорректный формат ввода.");
            return;
        }

        try {
            int num1, num2;

            if (isRoman(parts[0]) && isRoman(parts[2])) {
                num1 = romanToArabic(parts[0]);
                num2 = romanToArabic(parts[2]);
            } else {
                num1 = Integer.parseInt(parts[0]);
                num2 = Integer.parseInt(parts[2]);

                if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                    throw new UnsupportedOperationException("Введите числа от 1 до 10 включительно.");
                }
            }

            int result;
            switch (parts[1]) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    if (isRoman(parts[0]) && isRoman(parts[2]) && result <= 0) {
                        throw new UnsupportedOperationException("Результат вычитания римских цифр не может быть отрицательным.");
                    }
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                default:
                    throw new UnsupportedOperationException("Неподдерживаемая операция: " + parts[1]);
            }

            if (isRoman(parts[0]) && isRoman(parts[2])) {
                System.out.println("Результат: " + arabicToRoman(result));
            } else {
                System.out.println("Результат: " + result);
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка при парсинге чисел.");
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isRoman(String input) {
        return input.matches("^(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
    }

    private static int romanToArabic(String input) {
        return romanNumerals.get(input);
    }

    private static String arabicToRoman(int num) {
        if (num <= 0 || num > 10) {
            throw new UnsupportedOperationException("Римские цифры не могут представлять числа меньше 1 или больше 10.");
        }

        for (Map.Entry<String, Integer> entry : romanNumerals.entrySet()) {
            if (entry.getValue() == num) {
                return entry.getKey();
            }
        }

        return null;
    }
}
