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
            if (!isCommand(command)) {
                input = scanner.nextLine();
                continue;
            }
            switch (command[2]) {
                case "C":
                    isCanvasCreated = FormatHandler.handleFormat(() ->
                            canvas.createCanvas(Integer.parseInt(command[3]), Integer.parseInt(command[4])));
                    break;
                case "L":
                    if (isCanvasCreated) {
                        FormatHandler.handleFormat(() -> canvas.createNewLine(Integer.parseInt(command[3]),
                                Integer.parseInt(command[4]), Integer.parseInt(command[5]), Integer.parseInt(command[6])));
                    }
                    break;
                case "R":
                    if (isCanvasCreated) {
                        FormatHandler.handleFormat(() -> canvas.createRectangle(Integer.parseInt(command[3]),
                                Integer.parseInt(command[4]), Integer.parseInt(command[5]), Integer.parseInt(command[6])));
                    }
                    break;
                case "B":
                    if (isCanvasCreated) {
                        FormatHandler.handleFormat(() -> canvas.bucketFill(Integer.parseInt(command[3]), Integer.parseInt(command[4]), command[5].charAt(0)));
                    }
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
            if (isCanvasCreated) {
                canvas.printCanvas();
            }
            input = scanner.nextLine();
        }
    }

    private static boolean isCommand(String[] input) {
        if (input.length < 3) {
            System.out.println("Invalid input.");
            return false;
        }
        return true;
    }
}
