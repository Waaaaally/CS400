import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {
    private int score = 0;

    @Override
    public void start(final Stage stage) {
        // Step 3 & 4
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setTitle("Dessert in the Desert JavaFX Game");
        
        // Step 5
        Label scoreLabel = new Label("Score: " + score);
        borderPane.setTop(scoreLabel);
        BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        borderPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);
        
        // Step 6
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);

        //Step 6.5 random object creation
        Random random = new Random();

        // TODO: Step 7-10
        Button[] buttons = new Button[8];
        Button dessertButton = new Button("Dessert");
        
        dessertButton.addEventHandler(ActionEvent.ACTION, (event) -> {
            score++;
            randomizeButtonPositions(random, buttons);
            exitButton.requestFocus();

             //SCUFFED?? LMAO I think I'm just dumb.
            borderPane.getChildren().set(0, new Label("Score: " + score));
            borderPane.setTop(borderPane.getChildren().get(0));
            BorderPane.setAlignment(borderPane.getChildren().get(0), Pos.TOP_LEFT);
            
        });
        buttons[0] = dessertButton;

        pane.getChildren().add(dessertButton);
        for(int i = 0; i < 7; i++){
            Button desertButton = new Button("Desert");
            pane.getChildren().add(desertButton);
            buttons[i+1] = desertButton;
            
            desertButton.addEventHandler(ActionEvent.ACTION, (event) -> {
                score--;
                randomizeButtonPositions(random, buttons);
                exitButton.requestFocus();

                borderPane.getChildren().set(0, new Label("Score: " + score));
                borderPane.setTop(borderPane.getChildren().get(0));
                BorderPane.setAlignment(borderPane.getChildren().get(0), Pos.TOP_LEFT);
            });
        }
        


        randomizeButtonPositions(random, buttons);
        exitButton.requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    public void changeScoreLabel(Label label){
        String lableString = "Score : " + score;
        label = new Label(lableString);
    }

    public void randomizeButtonPositions(Random random, Button[] buttons){
        int xPosition, yPosition;
        
        for(Button button : buttons){
            xPosition = random.nextInt(600);
            yPosition = random.nextInt(400);

            button.setLayoutX(xPosition);
            button.setLayoutY(yPosition);
        }
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
