/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecalculator;

import java.util.Arrays;
import java.util.LinkedList;


public class Tokenizer {
    
    private static Tokenizer tokenizer;
    
    public Tokenizer() {}

    
    LinkedList<String> splitIntoTokens(String arithmeticExpression) {
        
        String[] tokens = arithmeticExpression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?<=\\D)(?=\\D)"); //regular expression to split the expression while keeping both numbers and operators as tokens
        return new LinkedList<String>(Arrays.asList(tokens));
    }
    
    public static void main(String[] args) {
        
        Tokenizer testTokenizer = new Tokenizer();
        
        String testExpression = "(4*2)+8/(543-665)";
        LinkedList<String> testTokens = testTokenizer.splitIntoTokens(testExpression);
        System.out.println(testTokens);

    }
}
