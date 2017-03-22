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
    
    public double parseAndEvaluate(LinkedList<String> expressionTokens) throws InvalidExpressionException {
        
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
    
    private double evaluateExpression() throws InvalidExpressionException {
        
        double value;
        boolean valueIsNegative = this.tokensList.peek().equals("-");
        if (valueIsNegative) {
            this.tokensList.poll();
        }
        value = evaluateTerm();
        if (valueIsNegative) {
            value = -value;
        }
        while (tokensList.peek() != null && (tokensList.peek().equals("+") || tokensList.peek().equals("-"))) {
            String operator = this.tokensList.poll();
            double nextValue = evaluateTerm();
            if (operator.equals("+")) {
                value += nextValue;
            } else if (operator.equals("-")) {
                value -= nextValue;
            }
        }

        return value;
    }
    
    private double evaluateTerm() throws InvalidExpressionException {
   
        double value;
        value = evaluateFactor();
        while (tokensList.peek() != null && (tokensList.peek().equals("*") || tokensList.peek().equals("/"))) {
             String operator = this.tokensList.poll();
             double nextValue = evaluateFactor();
             if (operator.equals("*")) {
                 value *= nextValue;
             } else if (operator.equals("/")) {
                 value /= nextValue;
             }
        }
       return value;
   } 
   
    private double evaluateFactor() throws InvalidExpressionException, NumberFormatException {
       
        double value;       
        String next = this.tokensList.poll();
        if (next.equals("(")) {
            value = evaluateExpression();
            if (this.tokensList.peek() == null || !this.tokensList.peek().equals(")")) {
                throw new InvalidExpressionException("Missing right parenthesis");
            } else {
                this.tokensList.poll();
            }
        } else {
            try {
                if (tokensList.peek() != null && tokensList.peek().equals(".")) {
                    //check for floating point number and adjust accordingly
                    next += tokensList.poll()+tokensList.poll();
                }
                value = Double.parseDouble(next);
            } catch (NumberFormatException e) {
                throw new InvalidExpressionException("Invalid expression");
            }
            
        }
        return value;
   }
}
