/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecalculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class SimpleCalculatorGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Calculator");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(grid, 250, 350);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(SimpleCalculator.class.getResource("SimpleCalculator.css").toExternalForm());
        
        TextField expressionField = new TextField();
        grid.add(expressionField, 0, 0, 5, 1);

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
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
