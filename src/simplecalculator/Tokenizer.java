package simplecalculator;

import java.util.Arrays;
import java.util.LinkedList;


public class Tokenizer {
    
    public Tokenizer() {}
    
    LinkedList<String> splitIntoTokens(String arithmeticExpression) {
        
        String[] tokens = arithmeticExpression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?<=\\D)(?=\\D)"); //regular expression to split the expression while keeping both numbers and operators as tokens
        return new LinkedList<String>(Arrays.asList(tokens));
    }
}
