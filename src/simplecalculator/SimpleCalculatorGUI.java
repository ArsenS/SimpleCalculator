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
    
    private TextField expressionField;
    private String inputText = "";
    private final Button[] buttons = new Button[19];
    
    private Tokenizer tokenizer;
    private ArithmeticExpressionParser parser;
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Calculator");
        
        GridPane grid = setupLayoutGrid();
        
        Scene scene = new Scene(grid, 280, 350);
        scene.getStylesheets().add(SimpleCalculatorGUI.class.getResource("SimpleCalculatorGUI.css").toExternalForm());
        primaryStage.setScene(scene);
        
        instantiateParserComponents();
        setupExpressionField(grid);
        setupGUIButtons(grid);
        setupMainButtonsEventHandlers();
        setupClearButtonEventHandler();
        setupEqualsButtonEventHandler();
         
        primaryStage.show();
    }
    
    private GridPane setupLayoutGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));
        return grid;
    }
    
    private void instantiateParserComponents() {
        tokenizer = new Tokenizer();
        parser = new ArithmeticExpressionParser();
    }
    
    private void setupExpressionField(GridPane grid) {
        expressionField = new TextField();
        expressionField.setEditable(false);
        grid.add(expressionField, 0, 0, 5, 1);
    }
    
    private void setupGUIButtons(GridPane grid) {
        
        String[][] buttonLabels = { {"C", "(", ")", "/"},
                                    {"7", "8", "9", "*"},
                                    {"4", "5", "6", "+"},
                                    {"1", "2", "3", "-"},
                                    {".", "0", "="} };
        
        int currentButton = 0;       
        for (int i = 0; i < buttonLabels.length; i++) {
            for (int j = 0; j < buttonLabels[i].length; j++) {
                buttons[currentButton] = new Button(buttonLabels[i][j]);
                grid.add(buttons[currentButton], j, i+1);
                currentButton++;                
            }
        }
        
        //setup for CSS
        buttons[0].setId("clear");
        buttons[buttons.length-1].setId("equals");
        grid.setColumnSpan(buttons[buttons.length-1], 2); 
    }
    
    private void setupMainButtonsEventHandlers() {
        for (Button button: buttons) {     
           
            String buttonText = button.getText();
            if (!buttonText.equals("C") && !buttonText.equals("=")) {
                button.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        updateInputText(button.getText());
                        expressionField.setText(inputText);
                    }
                });
            }
        }
    }
    
    private void setupClearButtonEventHandler() {
        buttons[0].setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                resetInputText();
                expressionField.setText(inputText);
            }
        });
    }
       
    private void setupEqualsButtonEventHandler() {
        buttons[18].setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String arithmeticExpression = expressionField.getText();
                if (!arithmeticExpression.equals("")) {
                    evaluateAndDisplay(arithmeticExpression);
                }
            }
        });
    }
    
    private void evaluateAndDisplay(String expression) {
        try {
            double value = parser.parseAndEvaluate(tokenizer.splitIntoTokens(expression));    
            this.inputText = trimDecimalIfInteger(value);
            this.expressionField.setText(inputText);
        } catch (InvalidExpressionException e) {
            this.expressionField.setText("Error");
            resetInputText();
        }
    }
    
    private String trimDecimalIfInteger(double value) {
        String val;
        if (value % 1 == 0.0) {
            val = Integer.toString((int)value);
        } else {
            val = Double.toString(value);
        }
        return val;
    }
    
    private void updateInputText(String newValue) {
        this.inputText += newValue;
    }
    
    private void resetInputText() {
        this.inputText = "";
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
