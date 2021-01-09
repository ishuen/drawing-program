package w.ish;

public class Main {

    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        canvas.createCanvas(20, 4);
        canvas.printCanvas();
        canvas.createNewLine(1, 2, 6, 2);
        canvas.printCanvas();
        canvas.createNewLine(6, 3, 6, 4);
        canvas.printCanvas();
        canvas.createRectangle(14, 1, 18, 3);
        canvas.printCanvas();
    }


}
