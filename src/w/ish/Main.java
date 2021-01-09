package w.ish;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String USER_INPUT_PREFIX = "enter command: ";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Canvas canvas = new Canvas();
        boolean isCanvasCreated = false;
        while (!input.equals(USER_INPUT_PREFIX + 'Q')) {
            String[] command = input.split(" ");
            switch (command[2]) {
                case "C":
                    if (isValidArgument(command, 5)) {
                        canvas.createCanvas(Integer.parseInt(command[3]), Integer.parseInt(command[4]));
                        isCanvasCreated = true;
                    }
                    break;
                case "L":
                    if (isValidArgument(command, 7) && isCanvasCreated) {
                        canvas.createNewLine(Integer.parseInt(command[3]), Integer.parseInt(command[4]),
                                Integer.parseInt(command[5]), Integer.parseInt(command[6]));
                    }
                    break;
                case "R":
                    if (isValidArgument(command, 7) && isCanvasCreated) {
                        canvas.createRectangle(Integer.parseInt(command[3]), Integer.parseInt(command[4]),
                                Integer.parseInt(command[5]), Integer.parseInt(command[6]));
                    }
                    break;
                case "B":
                    if (isValidArgument(command, 6) && isCanvasCreated) {
                        canvas.bucketFill(Integer.parseInt(command[3]), Integer.parseInt(command[4]), command[5].charAt(0));
                    }
                    break;
            }
            if (isCanvasCreated) {
                canvas.printCanvas();
            }
            input = scanner.nextLine();
        }
    }
    private static boolean isValidArgument(String[] input, int expected) {
        if (input.length != expected) {
            System.out.println("Invalid input.");
            return false;
        }
        return true;
    }
}
