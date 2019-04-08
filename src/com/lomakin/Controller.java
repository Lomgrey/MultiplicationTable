package com.lomakin;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class Controller {

    private final double DEFAULT_MATRIX_ITEM_WIDTH = 50;
    private final double DEFAULT_MATRIX_ITEM_HEIGHT = 40;

    public TextField dimensionField;
    public ScrollPane rootPane;

    public void changeTable() {

        int dimension = Integer.valueOf(dimensionField.getText());
        generateMatrix(dimension, dimension);

    }

    private void generateMatrix(int rows, int columns) {
        rows++;
        columns++;

        GridPane matrixPane = new GridPane();
        matrixPane.setGridLinesVisible(true);

        for(int y = 1; y < rows; y++){
            for(int x = 1; x < columns; x++){
                TextField tf = numericFieldOfMatrix(x * y);
                matrixPane.add(tf, x, y);
            }
        }

        // нумирование столбцов и колон
        Label firstLabel = emptyLabel();
        matrixPane.add(firstLabel, 0, 0);

        for (int i = 1; i < rows; i++) {
            Label vertLabel = verticalInfoLabel(String.valueOf(i));

            GridPane.setRowIndex(vertLabel,i);
            GridPane.setColumnIndex(vertLabel,0);
            matrixPane.getChildren().add(vertLabel);
        }

        for (int i = 1; i < columns; i++) {
            Label horLabel = horizontalInfoLabel(String.valueOf(i));

            GridPane.setRowIndex(horLabel,0);
            GridPane.setColumnIndex(horLabel,i);
            matrixPane.getChildren().add(horLabel);
        }

        rootPane.setContent(matrixPane);
    }

    private Label emptyLabel(){
        Label label = new Label();
        label.setPrefSize(DEFAULT_MATRIX_ITEM_WIDTH / 2, DEFAULT_MATRIX_ITEM_HEIGHT / 2);

        return label;
    }

    private Label horizontalInfoLabel(String text){
        return infoLabel(text, DEFAULT_MATRIX_ITEM_WIDTH, DEFAULT_MATRIX_ITEM_HEIGHT / 2);

    }

    private Label verticalInfoLabel(String text){
        return infoLabel(text, DEFAULT_MATRIX_ITEM_WIDTH / 2, DEFAULT_MATRIX_ITEM_HEIGHT);
    }

    private Label infoLabel(String text, double width, double height){
        Label label = new Label();
        label.setPrefSize(width, height);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(12));
        label.setText(text);

        return label;
    }

    private TextField numericFieldOfMatrix(String val, double widthOfTextField){
        TextField tf = new TextField();
        tf.setPrefSize(widthOfTextField ,DEFAULT_MATRIX_ITEM_HEIGHT);
        tf.setAlignment(Pos.CENTER);
        tf.setEditable(false);
        tf.setFont(Font.font(12));
        tf.setText(val);

        return tf;
    }

    private TextField numericFieldOfMatrix(long value){
        return numericFieldOfMatrix(String.valueOf(value), DEFAULT_MATRIX_ITEM_WIDTH);
    }
}
