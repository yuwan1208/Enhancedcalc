import java.util.Scanner;

public class EnhancedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        // Display Menu
        do {
            System.out.println();
            System.out.println("Choose a function to plot:");
            System.out.println("1. y = x (Linear)");
            System.out.println("2. y = x^2 (Quadratic)");
            System.out.println("3. y = sin(x) (Sine wave)");
            System.out.println("4. y = cos(x) (Cosine Wave)");
            System.out.println("5. y = x^3 (Cubic)");
            System.out.println("6. Exit");
            System.out.flush();

            System.out.print("Enter choice (1-6): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please choose a valid number from 1-6.");
                scanner.next();
            }
            choice = scanner.nextInt();

            if (choice >= 1 && choice <= 5) {
                plot(choice);
            } else if (choice != 6) {
                System.out.println("Invalid input! Please choose a valid number from 1-6.");
            }
        } while (choice != 6);

        System.out.println("Exiting Calculator...");
        scanner.close();
    }

    // Resolution section
    public static void plot(int choice) {
        int width = 80;
        int height = 25;
        double xMin = -10, xMax = 10;
        double yMin = -5, yMax = 5;

        if (choice == 5) {
            xMin = -3;
            xMax = 3;
            yMin = -30;
            yMax = 30;
        }

        double xStep = (xMax - xMin) / (width - 1);
        double yStep = (yMax - yMin) / (height - 1);
        double yTolerance = yStep / 2;
        double xTolerance = xStep / 2;

        for (int row = 0; row < height; row++) {
            double y = yMax - row * yStep;
            StringBuilder line = new StringBuilder();

            for (int col = 0; col < width; col++) {
                double x = xMin + col * xStep;
                double fx = 0;

                switch (choice) {
                    case 1:
                        fx = x;
                        break;
                    case 2:
                        fx = x * x;
                        break;
                    case 3:
                        fx = Math.sin(x);
                        break;
                    case 4:
                        fx = Math.cos(x);
                        break;
                    case 5:
                        fx = x * x * x;
                        break;
                    default:
                        fx = Double.NaN;
                }

                if (Math.abs(fx - y) < yTolerance) {
                    line.append("*");
                } else if (Math.abs(x) < xTolerance && Math.abs(y) < yTolerance) {
                    line.append("+");
                } else if (Math.abs(y) < yTolerance) {
                    line.append("-");
                } else if (Math.abs(x) < xTolerance) {
                    line.append("|");
                } else {
                    line.append(" ");
                }
            }

            System.out.println(line);
        }
    }
}
