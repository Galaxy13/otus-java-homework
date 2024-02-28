import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Homework2Test {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    @BeforeAll
    public static void setOutStream(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void revertOutStream(){
        System.setOut(originalOut);
    }

    @Test
    public void testPrintNTimes(){
        assertEquals()
    }
}
