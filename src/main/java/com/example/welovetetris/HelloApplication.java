package com.example.welovetetris;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.input.KeyCode;

public class HelloApplication extends Application {
    // set the size of the board to be displayed
    public static final int BOARD_WIDTH = 20;
    public static final int BOARD_HEIGHT = 20;
    // set the starting position of the "frog"
    private int x = 0; //BOARD_WIDTH/2;
    private int y = 0; //BOARD_HEIGHT/2;
    Board b = new Board();
    boolean[][] board = b.getArray();

    //CHANGE MADE - TRUE TO FALSE
    boolean pieceLanded = false;

    /**
     * Set up the starting scene of your application given the primaryStage (basically the window)
     * https://docs.oracle.com/javase/8/javafx/api/index.html
     *
     * @param primaryStage the primary container for scenes
     */
    @Override
    public void start(Stage primaryStage) {

        // Add a title to the application window
        primaryStage.setTitle("Hello World!");

        // prepare the scene layout to use a BorderPane -- a top, bottom, left, right, center style pane layout
        // https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
        BorderPane layout = new BorderPane();

        // Create a new scene using this layout
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html
        // define the size of this scene
        double WINDOW_WIDTH = 1280;
        double WINDOW_HEIGHT = 720;
        Scene exampleScene = new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);

        // make this scene the initial (and for now only) scene in your application
        primaryStage.setScene(exampleScene);

        // create a new text node to display text on the interface
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/text/Text.html
        Text frame = new Text(nextFrame());
        Font frameFont = new Font("Courier New", 20);
        frame.setFont(frameFont);
        frame.setTextAlignment(TextAlignment.CENTER);
        frame.setFill(Color.ORCHID);
        // add this text field to the layout
        layout.setCenter(frame);

        // define code to run every time a KeyPressed event is detected on this window to check for ESC to close
        // NOTE: there even is of type javafx.scene.input.KeyEvent
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyEvent.html
        exampleScene.setOnKeyPressed(event -> {
            // check if the key that was pressed is the ESC key
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                System.exit(0);
            }
            else if (event.getCode().equals(KeyCode.DOWN)) {
                moveDown();
                frame.setText(nextFrame());
            }
            else if (event.getCode().equals(KeyCode.LEFT)) {
                moveLeft();
                frame.setText(nextFrame());
            }
            else if (event.getCode().equals(KeyCode.RIGHT)) {
                moveRight();
                frame.setText(nextFrame());
            }
        });

        // display the interface
        primaryStage.show();
    }

    // define simple move functions to change the value of x and y (frog location)
    public void moveDown() {
        if (y < BOARD_HEIGHT-1) {
            if (!board[y+1][x]) {
                y += 1;
            } else {
                pieceLanded = true;
                board[y][x] = true;
                x = 10;
            }
        }
    }
    public void moveLeft() {
        if (x > 0) {
            if(!board[y][x-1]) {
                x -= 1;
            }
        }
    }
    public void moveRight() {
        if (x < BOARD_WIDTH-1) {
            if(!board[y][x+1]) {
                x += 1;
            }
        }

        //TODO: MOVE ELSE STATEMENT TO moveDown WHEN THE METHOD IS FINISHED !!!
        else {
            pieceLanded = true;
            board[y][x] = true;
            x = 10;
        }
    }

    // draw a board using BOARD_WIDTH, BOARD_HEIGHT, x, and y

    /*
    public String firstFrame() {
        StringBuilder frame = new StringBuilder();
        // add a top border
        frame.append("-".repeat(BOARD_WIDTH));
        // add each row of the board
        for (int r = 0; r < BOARD_HEIGHT; r++) {
            frame.append('\n');
            // add a left border
            frame.append('|');
            // fill in this row (possibly including a frog)
            for (int c = 0; c < BOARD_WIDTH; c++) {
                if (r == 0 && c == x) {
                    frame.append('F');
                    board[r][c] = true;
                } else {
                    frame.append(' ');
                }
            }
            // add a right border
            frame.append('|');
        }
        // add a bottom border
        frame.append('\n');
        frame.append("-".repeat(BOARD_WIDTH));
        return frame.toString();
    }

     */
    public String nextFrame() {

        char current = 'C';

        StringBuilder frame = new StringBuilder();
        frame.append("T E T R I S\n");
        frame.append("-".repeat(BOARD_WIDTH));
        // add each row of the board
        for (int r = 0; r < BOARD_HEIGHT; r++) {
            frame.append('\n');
            // add a left border
            frame.append('|');
            // fill in this row (possibly including a frog)
            for (int c = 0; c < BOARD_WIDTH; c++) {
                //adding landed pieces
                if (board[r][c]) {
                    frame.append('F');
                } else {
                    //moving piece currently being played with
                    if (r == y && c == x) {
                        frame.append(current);
                    } else {
                        frame.append(' ');
                    }
                }
            }
            // add a right border
            frame.append('|');
        }
        // add a bottom border
        frame.append('\n');
        frame.append("-".repeat(BOARD_WIDTH));
        return frame.toString();
    }
    public static void main(String[] args) {
        launch();
    }
}