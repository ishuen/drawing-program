import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FormatHandlerTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void shouldExecuteMethod() {
    FormatHandler.handleFormat(() -> true);
    assertEquals("", outContent.toString());
  }

  @Test
  public void shouldHandleNumberFormatException() {
    FormatHandler.handleFormat(() -> { throw new NumberFormatException(""); });
    assertEquals("Invalid input: Non-integer argument included\n", outContent.toString());
  }

  @Test
  public void shouldHandleArrayOutOfBoundException() {
    FormatHandler.handleFormat(() -> { throw new ArrayIndexOutOfBoundsException(""); });
    assertEquals("Invalid input: Should have correct number of arguments\n", outContent.toString());
  }

  @Test
  public void shouldHandleNumberFormatExceptionCallable() {
    boolean result = FormatHandler.handleFormat(() -> { throw new NumberFormatException(""); });
    assertFalse(result);
    assertEquals("Invalid input: Non-integer argument included\n", outContent.toString());
  }

  @Test
  public void shouldHandleArrayOutOfBoundExceptionCallable() {
    boolean result = FormatHandler.handleFormat(() -> { throw new ArrayIndexOutOfBoundsException(""); });
    assertFalse(result);
    assertEquals("Invalid input: Should have correct number of arguments\n", outContent.toString());
  }
}
