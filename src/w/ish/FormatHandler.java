package w.ish;

import java.util.concurrent.Callable;

public class FormatHandler {
  public static void handleFormat(Runnable runnable) {
    try {
      runnable.run();
    } catch (NumberFormatException e) {
      System.out.println("Invalid input: Non-integer argument included");
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invalid input: Should have correct number of arguments");
    } catch (Exception e) {
      System.out.println("Invalid input");
    }
  }

  public static boolean handleFormat(Callable<Boolean> callable) {
    try {
      return callable.call();
    } catch (NumberFormatException e) {
      System.out.println("Invalid input: Non-integer argument included");
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invalid input: Should have correct number of arguments");
    } catch (Exception e) {
      System.out.println("Invalid input");
    }
    return false;
  }
}
