package com.testcase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.test.EvaluateExpressions;

import static org.junit.Assert.assertEquals;
public class EvaluateExpressionsTest {
	@Test
    public void testEvaluateExpressions() {
        String input = "2 * 4 * 4\n" +
                "5 / (7 - 5)\n" +
                "Math.sqrt(Math.pow(5, 2) - Math.pow(4, 2))\n" +
                "Math.sqrt(Math.pow(-3, 2) - Math.pow(4, 2))\n" +
                "end\n";

        String expectedOutput = "2 * 4 * 4 => 32\n" +
                "5 / (7 - 5) => 2.5\n" +
                "Math.sqrt(Math.pow(5, 2) - Math.pow(4, 2)) => 3.0\n" +
                "Math.sqrt(Math.pow(-3, 2) - Math.pow(4, 2)) => NaN\n";

        InputStream originalIn = System.in;
        try {
            InputStream testIn = new ByteArrayInputStream(input.getBytes());
            System.setIn(testIn);

            EvaluateExpressions.main(null);

            assertEquals(expectedOutput, systemOut().toString());
        } finally {
            System.setIn(originalIn);
        }
    }

    private ByteArrayOutputStream systemOut() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }
}

