/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecalculator;

import java.util.LinkedList;


public class ArithmeticExpressionParser {
    
    private LinkedList<String> tokensList; 
    
    ArithmeticExpressionParser() {};
    
    public double parseAndEvaluate(LinkedList<String> expressionTokens) {
        
        double value;
        
        if (expressionTokens.isEmpty()) {
            return 0;
        } else {
            this.setTokensList(expressionTokens);
        }
        
        value = evaluateExpression();
        System.out.println(value);
        return value;
    };
    
    private void setTokensList(LinkedList<String> expressionTokens) {
        this.tokensList = expressionTokens;
    }
    
    private double evaluateExpression() {
        
        double value;
        value = evaluateTerm();
        System.out.println(tokensList.peek());
        while (tokensList.peek() != null && (tokensList.peek().equals("+") || tokensList.peek().equals("-"))) {
            String operator = this.tokensList.poll();
            double nextValue = evaluateTerm();
            if (operator.equals("+")) {
                value += nextValue;
            } else if (operator.equals("-")) {
                value -= nextValue;
            } else {
                //error handling
            }
        }

        return value;
    }
    
    private double evaluateTerm() {
   
        double value;
        value = evaluateFactor();
        while (tokensList.peek() != null && (tokensList.peek().equals("*") || tokensList.peek().equals("/"))) {
             String operator = this.tokensList.poll();
             double nextValue = evaluateFactor();
             if (operator.equals("*")) {
                 value *= nextValue;
             } else if (operator.equals("/")) {
                 value /= nextValue;
             } else {
                 //error handling
             }
        }

       return value;
   } 
   
    private double evaluateFactor() {
       
        double value;       
        String next = this.tokensList.poll();
        if (next.equals("(")) {
            value = evaluateExpression();
            next = this.tokensList.poll();
            if (!next.equals(")")) {
                //error handling
            }
        } else {
            value = Double.parseDouble(next);
        }
        return value;
   }
   
    public static void main(String[] args) {
        Tokenizer testTokenizer = new Tokenizer();
        
        String testExpression = "(4*3)+8/(7-3)";
        //String testExpression = "(4*32)+8";
        LinkedList<String> testTokens = testTokenizer.splitIntoTokens(testExpression);
        System.out.println(testTokens);
        ArithmeticExpressionParser parser = new ArithmeticExpressionParser();
        parser.parseAndEvaluate(testTokens);
        System.out.println(testTokens);
       
   }
}
