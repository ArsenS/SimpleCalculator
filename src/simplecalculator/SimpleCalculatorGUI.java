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
    private TextField expressionField;
    
    private Tokenizer tokenizer;
    private ArithmeticExpressionParser parser;
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Calculator");
        
        GridPane grid = setupLayoutGrid();
        
        Scene scene = new Scene(grid, 280, 350);
        scene.getStylesheets().add(SimpleCalculatorGUI.class.getResource("SimpleCalculatorGUI.css").toExternalForm());
        primaryStage.setScene(scene);
        
        setupParserComponents();
        setupExpressionField(grid);
        setupGUIButtons(grid);
        setupButtonsEventHandlers();
        setupClearButtonEventHandler();
        setupEqualButtonEventHandler();
         
        primaryStage.show();
    }
    
    
    private void updateInputText(String newValue) {
        this.inputText += newValue;
    }
    
    private void resetInputText() {
        this.inputText = "";
    }
    
    private void setupParserComponents() {
        tokenizer = new Tokenizer();
        parser = new ArithmeticExpressionParser();
    }
    
    private GridPane setupLayoutGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));
        return grid;
    }
    
    private void setupExpressionField(GridPane grid) {
        expressionField = new TextField();
        expressionField.setEditable(false);
        grid.add(expressionField, 0, 0, 5, 1);
    }
    
    private void setupGUIButtons(GridPane grid) {
        buttons[0] = new Button("0");
        grid.add(buttons[0], 1, 4);
        buttons[1] = new Button("1");
        grid.add(buttons[1], 0, 3);
        buttons[2] = new Button("2");
        grid.add(buttons[2], 1, 3);
        buttons[3] = new Button("3");
        grid.add(buttons[3], 2, 3);
        buttons[4] = new Button("4");
        grid.add(buttons[4], 0, 2);
        buttons[5] = new Button("5");
        grid.add(buttons[5], 1, 2);
        buttons[6] = new Button("6");
        grid.add(buttons[6], 2, 2);
        buttons[7] = new Button("7");
        grid.add(buttons[7], 0, 1);
        buttons[8] = new Button("8");
        grid.add(buttons[8], 1, 1);
        buttons[9] = new Button("9");
        grid.add(buttons[9], 2, 1);
        buttons[10] = new Button(".");
        grid.add(buttons[10], 0, 4);
        
        buttons[11] = new Button("/");
        grid.add(buttons[11], 3, 1);
        buttons[12] = new Button("*");
        grid.add(buttons[12], 3, 2);
        buttons[13] = new Button("-");
        grid.add(buttons[13], 3, 3);
        buttons[14] = new Button("+");
        grid.add(buttons[14], 3, 4);
        buttons[15] = new Button("=");
        grid.add(buttons[15], 2, 4);
        buttons[16] = new Button("C");
        grid.add(buttons[16], 6, 0);
    }
    
    private void setupButtonsEventHandlers() {
        for (Button button: buttons) {         
           
            button.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                    updateInputText(button.getText());
                    expressionField.setText(inputText);
                }
            });
        }
    }
    
    private void setupClearButtonEventHandler() {
        buttons[16].setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                resetInputText();
                expressionField.setText(inputText);
            }
        });
    }
    
    private void setupEqualButtonEventHandler() {
        buttons[15].setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                String arithmeticExpression = expressionField.getText();
                try {
                    double value = parser.parseAndEvaluate(tokenizer.splitIntoTokens(arithmeticExpression));
                    
                    if (value % 1 == 0.0) {
                        inputText = Integer.toString((int)value);
                    } else {
                        inputText = Double.toString(value);
                    }
                    expressionField.setText(inputText);
                } catch (InvalidExpressionException e) {
                    expressionField.setText("Error");
                }
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
