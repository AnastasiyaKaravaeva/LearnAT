import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        System.out.println("Enter numbers!");

        while (!"q".equals(userInput)) {
            try {
                int firstNumber = scanner.nextInt();
                int secondNumber = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Enter operator:");
                char operator = scanner.next().charAt(0);
                double result = calculate(operator, firstNumber, secondNumber);

                System.out.println(result);
            } catch (InputMismatchException ex) {
                System.out.println("Wrong input data!");
                scanner.nextLine();
                continue; // перезапуск цикла
            }

            userInput = scanner.nextLine();
        }
    }

    /**
     * Выполняет операцию над двумя числами в зависимости от переданного оператора.
     * @param operator оператор (+, -, *, /, !)
     * @param numberOne первое число
     * @param numberTwo второе число
     * @return результат операции
     */
    private static double calculate(char operator, int numberOne, int numberTwo) {
        double result = 0;

        switch (operator) {
            case '+':
                result = plus(numberOne, numberTwo);
                break;
            case '-':
                result = minus(numberOne, numberTwo);
                break;
            case '*':
                result = multiply(numberOne, numberTwo);
                break;
            case '/':
                if (numberTwo == 0) {
                    System.out.println("Division by zero is not possible.");
                    break;
                }
                result = divide(numberOne, numberTwo);
                break;
            case '!':
                result = factorial(numberOne);
                break;
            default:
                System.out.println("Invalid operator.");
                break;
        }

        return result;

    }

    /**
     * Выполняет сложение двух чисел.
     */
    private static int plus(int numberOne, int numberTwo) {
        return numberOne + numberTwo;
    }

    /**
     * Выполняет вычитание двух чисел.
     */
    private static int minus(int numberOne, int numberTwo) {
        return numberOne - numberTwo;
    }

    /**
     * Выполняет умножение двух чисел.
     */
    private static int multiply(int numberOne, int numberTwo) {
        return numberOne * numberTwo;
    }

    /**
     * Выполняет деление двух чисел.
     */
    private static double divide(int numberOne, int numberTwo) {
        return (double) numberOne / numberTwo;
    }

    /**
     * Вычисляет факториал числа.
     */
    private static int factorial(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;

    }
}
