import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CanvasTest {

  @InjectMocks
  private Canvas canvas;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldCreateNewCanvas() {
    int width = 1;
    int height = 2;
    boolean result = canvas.createCanvas(width, height);
    char[][] canvasMatrix = Whitebox.getInternalState(canvas, "canvas");
    assertTrue(result);
    assertEquals(width, canvasMatrix.length);
    assertEquals(height, canvasMatrix[0].length);
  }

  @Test
  public void shouldNotCreateNewCanvas() {
    int width = 0;
    int height = 0;
    boolean result = canvas.createCanvas(width, height);
    char[][] canvasMatrix = Whitebox.getInternalState(canvas, "canvas");
    assertFalse(result);
    assertNull(canvasMatrix);
  }

  @Test
  public void shouldDrawVerticalLine() {
    int x1 = 3;
    int y1 = 1;
    int x2 = 3;
    int y2 = 4;
    char[][] matrix = prepareMatrix(x2, y2);
    Whitebox.setInternalState(canvas, "canvas", matrix);
    canvas.createNewLine(x1, y1, x2, y2);
    char[][] canvasMatrix = Whitebox.getInternalState(canvas, "canvas");
    for (int i = 0; i < x2; i++) {
      for (int j = 0; j < y2; j++) {
        if (i == x1 - 1) {
          assertEquals('x', canvasMatrix[i][j]);
        } else {
          assertEquals(' ', canvasMatrix[i][j]);
        }
      }
    }
  }

  @Test
  public void shouldDrawHorizontalLine() {
    int x1 = 1;
    int y1 = 4;
    int x2 = 3;
    int y2 = 4;
    char[][] matrix = prepareMatrix(x2, y2);
    Whitebox.setInternalState(canvas, "canvas", matrix);
    canvas.createNewLine(x1, y1, x2, y2);
    char[][] canvasMatrix = Whitebox.getInternalState(canvas, "canvas");
    for (int i = 0; i < x2; i++) {
      for (int j = 0; j < y2; j++) {
        if (j == y1 - 1) {
          assertEquals('x', canvasMatrix[i][j]);
        } else {
          assertEquals(' ', canvasMatrix[i][j]);
        }
      }
    }
  }

  @Test
  public void shouldNotDrawLine() {
    int width = 3;
    int height = 4;
    char[][] matrix = prepareMatrix(width, height);
    Whitebox.setInternalState(canvas, "canvas", matrix);
    canvas.createNewLine(0, 0, 0, height + 1);
    char[][] canvasMatrix = Whitebox.getInternalState(canvas, "canvas");
    checkIdentical(matrix, canvasMatrix);
  }

  @Test
  public void shouldNotDrawDiagonalLine() {
    int x1 = 1;
    int y1 = 1;
    int x2 = 4;
    int y2 = 4;
    int width = 5;
    int height = 5;
    char[][] matrix = prepareMatrix(width, height);
    Whitebox.setInternalState(canvas, "canvas", matrix);
    canvas.createNewLine(x1, y1, x2, y2);
    char[][] canvasMatrix = Whitebox.getInternalState(canvas, "canvas");
    checkIdentical(matrix, canvasMatrix);
  }

  @Test
  public void shouldCreateRectangle() {
    int x1 = 2;
    int y1 = 2;
    int x2 = 4;
    int y2 = 5;
    int width = 5;
    int height = 6;
    char[][] matrix = prepareMatrix(width, height);
    Whitebox.setInternalState(canvas, "canvas", matrix);
    canvas.createRectangle(x1, y1, x2, y2);
    char[][] canvasMatrix = Whitebox.getInternalState(canvas, "canvas");
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if ((i >= x1 - 1 && i <= x2 - 1 && j == y1 - 1) || (i == x1 - 1 && j >= y1 - 1 && j <= y2 - 1) ||
                (i >= x1 - 1 && i <= x2 - 1 && j == y2 - 1) || (i == x2 - 1 && j >= y1 - 1 && j <= y2 - 1)) {
          assertEquals('x', canvasMatrix[i][j]);
        } else {
          assertEquals(' ', canvasMatrix[i][j]);
        }
      }
    }
  }

  @Test
  public void shouldNotCreateRectangle() {
    int x1 = 2;
    int y1 = 2;
    int x2 = 4;
    int y2 = 5;
    int width = 3;
    int height = 3;
    char[][] matrix = prepareMatrix(width, height);
    Whitebox.setInternalState(canvas, "canvas", matrix);
    canvas.createRectangle(x1, y1, x2, y2);
    char[][] canvasMatrix = Whitebox.getInternalState(canvas, "canvas");
    checkIdentical(matrix, canvasMatrix);
  }

  @Test
  public void shouldFillWholeCanvas() {
    int x = 2;
    int y = 2;
    int width = 5;
    int height = 6;
    char[][] matrix = prepareMatrix(width, height);
    Whitebox.setInternalState(canvas, "canvas", matrix);
    canvas.bucketFill(x, y, '*');
    char[][] canvasMatrix = Whitebox.getInternalState(canvas, "canvas");
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        assertEquals('*', canvasMatrix[i][j]);
      }
    }
  }

  @Test
  public void shouldNotFillOnLine() {
    int x = 2;
    int y = 2;
    int width = 5;
    int height = 6;
    char[][] matrix = prepareMatrix(width, height);
    for (int i = 0; i < height - 1; i++) {
      matrix[x - 1][i] = 'x';
    }
    Whitebox.setInternalState(canvas, "canvas", matrix);
    canvas.bucketFill(x, y, '*');
    char[][] canvasMatrix = Whitebox.getInternalState(canvas, "canvas");
    checkIdentical(matrix, canvasMatrix);
  }

  private char[][] prepareMatrix(int width, int height) {
    char[][] matrix = new char[width][height];
    for (int i = 0; i < matrix.length; i++) {
      Arrays.fill(matrix[i], ' ');
    }
    return matrix;
  }

  private void checkIdentical(char[][] originalMatrix, char[][] newMatrix) {
    int width = originalMatrix.length;
    int height = originalMatrix[0].length;
    assertEquals(width, newMatrix.length);
    assertEquals(height, newMatrix[0].length);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        assertEquals(originalMatrix[i][j], newMatrix[i][j]);
      }
    }
  }

}
