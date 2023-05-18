/**
 * 
 */
/**
 * @author nahush
 *
 */

 /**
     * 
     * @implNote You need to ensure that the JavaScript engine is available in your Java environment.
     * 
     * 
     * 
     */

package com.test;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 
 * class accepts multiple mathematical expressions from the user 
 * and stores them in a list until the user enters "end" to finish input
 * 
 * 
 * 
 *  JavaScript engine is used to evaluate the expression using the eval() method. 
 *  The result is then printed on the console.
 */
public class EvaluateExpressions {
    public static void main(String[] args) {
        List<String> expressions = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expressions (type 'end' to finish):");

        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            expressions.add(input);
        }

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        for (String expression : expressions) {
            try {
                Object result = engine.eval(expression);
                System.out.println(expression + " => " + result);
            } catch (ScriptException e) {
                System.out.println("Failed to evaluate expression: " + expression);
                e.printStackTrace();
            }
        }
    }
   
}
