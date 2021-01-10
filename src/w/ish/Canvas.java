package w.ish;

import java.util.Arrays;

public class Canvas {
  char[][] canvas;

  public boolean createCanvas(int width, int height) {
    if (width < 1 || height < 1) {
      System.out.println("Invalid canvas size");
      return false;
    }
    canvas = new char[width][height];
    for (int i = 0; i < canvas.length; i++) {
      Arrays.fill(canvas[i], ' ');
    }
    return true;
  }

  public void createNewLine(int x1, int y1, int x2, int y2) {
    if (isValidXAxis(x1) && isValidXAxis(x2) && isValidYAxis(y1) && isValidYAxis(y2)) {
      if (x1 != x2 && y1 != y2) {
        System.out.println("Invalid direction specified");
      }
      if (x1 == x2) {
        createVerticalLine(x1, y1, y2);
      } else if (y1 == y2) {
        createHorizontalLine(y1, x1, x2);
      }
    } else {
      System.out.println("Invalid number provided");
    }
  }

  public void createRectangle(int x1, int y1, int x2, int y2) {
    if (isValidXAxis(x1) && isValidXAxis(x2) && isValidYAxis(y1) && isValidYAxis(y2)) {
      createVerticalLine(x1, y1, y2);
      createVerticalLine(x2, y1, y2);
      createHorizontalLine(y1, x1, x2);
      createHorizontalLine(y2, x1, x2);
    } else {
      System.out.println("Invalid number provided");
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
    for (int i = 1; i <= canvas[x-1].length; i++) {
      if (i <= y1 && i >= y2 || i >= y1 && i <= y2) {
        canvas[x - 1][i - 1] = 'x';
      }
    }
  }

  private boolean isValidXAxis(int x) {
    return x > 0 && x <= canvas.length;
  }

  private boolean isValidYAxis(int y) {
    return y > 0 && y <= canvas[0].length;
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
    if (!isValidXAxis(x) || !isValidYAxis(y)) {
      System.out.println("Invalid number provided");
    } else {
      if (canvas[x-1][y-1] == 'x') {
        System.out.println("Cannot change line color");
      } else {
        fillColor(x - 1, y - 1, c);
      }
    }
  }

  private void fillColor(int x, int y, char c) {
    if (canvas[x][y] != 'x' && canvas[x][y] != c) {
      canvas[x][y] = c;
      if (x != 0) {
        fillColor(x - 1, y, c);
      }
      if (x < canvas.length - 1) {
        fillColor(x + 1, y, c);
      }
      if (y != 0) {
        fillColor(x, y - 1, c);
      }
      if (y < canvas[0].length - 1) {
        fillColor(x, y + 1, c);
      }
    }
  }
  private void printHorizontalBorder() {
    System.out.print("--");
    for (int i = 0; i < canvas.length; i++) {
      System.out.print("-");
    }
    System.out.print("\n");
  }
}
