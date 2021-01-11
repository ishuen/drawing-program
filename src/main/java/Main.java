import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Canvas canvas = new Canvas();
        boolean isCanvasCreated = false;
        while (!input.equals("Q")) {
            String[] command = input.split(" ");
            if (command.length == 0) {
                input = scanner.nextLine();
                continue;
            }
            switch (command[0]) {
                case "C":
                    isCanvasCreated = FormatHandler.handleFormat(() ->
                            canvas.createCanvas(Integer.parseInt(command[1]), Integer.parseInt(command[2])));
                    break;
                case "L":
                    if (isCanvasCreated) {
                        FormatHandler.handleFormat(() -> canvas.createNewLine(Integer.parseInt(command[1]),
                                Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4])));
                    }
                    break;
                case "R":
                    if (isCanvasCreated) {
                        FormatHandler.handleFormat(() -> canvas.createRectangle(Integer.parseInt(command[1]),
                                Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4])));
                    }
                    break;
                case "B":
                    if (isCanvasCreated) {
                        FormatHandler.handleFormat(() -> canvas.bucketFill(Integer.parseInt(command[1]), Integer.parseInt(command[2]), command[3].charAt(0)));
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
}
