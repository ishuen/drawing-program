package w.ish;

import java.util.Arrays;

public class Canvas {
  char[][] canvas;

  public void createCanvas(int width, int height) {
    canvas = new char[width][height];
    for (int i = 0; i < canvas.length; i++) {
      Arrays.fill(canvas[i], ' ');
    }
  }

  public void createNewLine(int x1, int y1, int x2, int y2) {
    if (isValidXAxis(x1) && isValidXAxis(x2) && isValidYAxis(y1) && isValidYAxis(y2)) {
      if (x1 != x2 && y1 != y2) {
        System.out.print("Invalid direction specified.");
      }
      if (x1 == x2) {
        createVerticalLine(x1, y1, y2);
      } else if (y1 == y2) {
        createHorizontalLine(y1, x1, x2);
      }
    } else {
      System.out.print("Invalid number provided");
    }
  }

  public void createRectangle(int x1, int y1, int x2, int y2) {
    if (isValidXAxis(x1) && isValidXAxis(x2) && isValidYAxis(y1) && isValidYAxis(y2)) {
      createVerticalLine(x1, y1, y2);
      createVerticalLine(x2, y1, y2);
      createHorizontalLine(y1, x1, x2);
      createHorizontalLine(y2, x1, x2);
    } else {
      System.out.print("Invalid number provided");
    }
  }

  private void createHorizontalLine(int y, int x1, int x2) {
    for (int i = 1; i <= canvas.length; i++) {
      if (i <= x1 && i >= x2 || i >= x1 && i <= x2) {
        canvas[i - 1][y - 1] = 'x';
      }
    }
  }

  private void createVerticalLine(int x, int y1, int y2) {
    for (int i = 1; i <= canvas[x].length; i++) {
      if (i <= y1 && i >= y2 || i >= y1 && i <= y2) {
        canvas[x - 1][i - 1] = 'x';
      }
    }
  }

  private boolean isValidXAxis(int x) {
    return x > 0 || x < canvas.length;
  }

  private boolean isValidYAxis(int y) {
    return y > 0 || y < canvas[0].length;
  }

  public void printCanvas() {
    printHorizontalBorder();
    for (int j = 0; j < canvas[0].length; j++) {
      System.out.print("|");
      for (char[] col : canvas) {
        System.out.print(col[j]);
      }
      System.out.print("|\n");
    }
    printHorizontalBorder();
  }

  public void bucketFill(int x, int y, char c) {

  }

  private void printHorizontalBorder() {
    System.out.print("--");
    for (int i = 0; i < canvas.length; i++) {
      System.out.print("-");
    }
    System.out.print("\n");
  }
}
