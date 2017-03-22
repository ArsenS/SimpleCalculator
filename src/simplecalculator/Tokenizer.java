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
}
