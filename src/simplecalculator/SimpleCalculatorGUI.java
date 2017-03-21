/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecalculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class SimpleCalculatorGUI extends Application {
    
    private String inputText = "";
    private static Tokenizer tokenizer;
    private static ArithmeticExpressionParser parser;
    
    private void updateInputText(String newChar) {
        this.inputText += newChar;
    }
    
    private void resetInputText() {
        this.inputText = "";
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Calculator");
        
        tokenizer = new Tokenizer();
        parser = new ArithmeticExpressionParser();
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(grid, 280, 350);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(SimpleCalculator.class.getResource("SimpleCalculator.css").toExternalForm());
        
        TextField expressionField = new TextField();
        expressionField.setEditable(false);
        grid.add(expressionField, 0, 0, 5, 1);
        Button clearBtn = new Button("C");
        grid.add(clearBtn, 6, 0);

        Button num0Btn = new Button("0");
        grid.add(num0Btn, 0, 4, 2, 1);
        num0Btn.setId("zero");
        Button num1Btn = new Button("1");
        grid.add(num1Btn, 0, 3);
        Button num2Btn = new Button("2");
        grid.add(num2Btn, 1, 3);
        Button num3Btn = new Button("3");
        grid.add(num3Btn, 2, 3);
        Button num4Btn = new Button("4");
        grid.add(num4Btn, 0, 2);
        Button num5Btn = new Button("5");
        grid.add(num5Btn, 1, 2);
        Button num6Btn = new Button("6");
        grid.add(num6Btn, 2, 2);
        Button num7Btn = new Button("7");
        grid.add(num7Btn, 0, 1);
        Button num8Btn = new Button("8");
        grid.add(num8Btn, 1, 1);
        Button num9Btn = new Button("9");
        grid.add(num9Btn, 2, 1);
        
        Button divBtn = new Button("/");
        grid.add(divBtn, 3, 1);
        Button multBtn = new Button("*");
        grid.add(multBtn, 3, 2);
        Button subBtn = new Button("-");
        grid.add(subBtn, 3, 3);
        Button addBtn = new Button("+");
        grid.add(addBtn, 3, 4);
        Button equalBtn = new Button("=");
        grid.add(equalBtn, 2, 4);
        
        num0Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("0");
                expressionField.setText(inputText);
            }
        });
        
        num1Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("1");
                expressionField.setText(inputText);
            }
        });
        
        num2Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("2");
                expressionField.setText(inputText);
            }
        });
        
        num3Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("3");
                expressionField.setText(inputText);
            }
        });
        
        num4Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("4");
                expressionField.setText(inputText);
            }
        });
        
        num5Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("5");
                expressionField.setText(inputText);
            }
        });
        
        num6Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("6");
                expressionField.setText(inputText);
            }
        });
        
        num7Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("7");
                expressionField.setText(inputText);
            }
        });
        
        num8Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("8");
                expressionField.setText(inputText);
            }
        });
        
        num9Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("9");
                expressionField.setText(inputText);
            }
        });
        
        divBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("/");
                expressionField.setText(inputText);
            }
        });
        
        multBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("*");
                expressionField.setText(inputText);
            }
        });
        
        subBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("-");
                expressionField.setText(inputText);
            }
        });
        
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                updateInputText("+");
                expressionField.setText(inputText);
            }
        });
        
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                resetInputText();
                expressionField.setText(inputText);
            }
        });
                
        equalBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                String arithmeticExpression = expressionField.getText();
                try {
                    double value = parser.parseAndEvaluate(tokenizer.splitIntoTokens(arithmeticExpression));
                    inputText = Double.toString(value);
                    expressionField.setText(inputText);
                } catch (InvalidExpressionException e) {
                    expressionField.setText("error");
                }
                
            }
        });
        
        primaryStage.show();
        

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
