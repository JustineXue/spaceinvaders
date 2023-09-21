package invaders.engine;

import java.util.List;
import java.util.ArrayList;

import invaders.entities.EntityViewImpl;
import invaders.entities.SpaceBackground;
import javafx.util.Duration;

import invaders.entities.EntityView;
import invaders.rendering.Renderable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import invaders.logic.Damagable;
import invaders.entities.concrete.Player;
import invaders.physics.Vector2D;
import invaders.entities.GameObject;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class GameWindow {
	private final int width;
    private final int height;
	private Scene scene;
    private Pane pane;
    private GameEngine model;
    private List<EntityView> entityViews;
    private Renderable background;

    private double xViewportOffset = 0.0;
    private double yViewportOffset = 0.0;
    private static final double VIEWPORT_MARGIN = 280.0;


    private long startTime;

    private boolean gameStarted = false;

    private boolean gameOverDisplayed = false;
    private String scoreString;

    private Text timeText;

    private Text livesText;

    public GameWindow(GameEngine model, int width, int height){
		this.width = width;
        this.height = height;
        this.model = model;
        pane = new Pane();
        scene = new Scene(pane, width, height);
        this.background = new SpaceBackground(model, pane);

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(this.model);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

        entityViews = new ArrayList<EntityView>();

        startTime = System.currentTimeMillis();
    }

	public void run() {
         Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17), t -> this.draw()));

         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.play();
    }

    private void draw(){
        List<Renderable> markedForDelete = new ArrayList<Renderable>();
        List<Renderable> renderables = model.getRenderables();
        List<GameObject> gameobjects = model.getGameObjects();
        if (!gameOverDisplayed) {
            if (getElapsedTime() > 5 && gameStarted == false) {
                gameStarted = true;
            }
            if (gameStarted && model.isGameOver()) {
                gameOverDisplayed = true;
            }
            model.update();
            for (Renderable entity : renderables) {
                if ((entity instanceof Damagable) && !(entity instanceof Player)) {
                    Damagable d = (Damagable) entity;
                    if (!d.isAlive()) {
                        markedForDelete.add(entity);
                    }
                }
                boolean notFound = true;
                for (EntityView view : entityViews) {
                    if (view.matchesEntity(entity)) {
                        notFound = false;
                        view.update(xViewportOffset, yViewportOffset);
                        break;
                    }
                }
                if (notFound) {
                    EntityView entityView = new EntityViewImpl(entity);
                    entityViews.add(entityView);
                    pane.getChildren().add(entityView.getNode());
                }
            }

            for (EntityView entityView : entityViews) {
                for (Renderable r : markedForDelete) {
                    if (entityView.matchesEntity(r)) {
                        entityView.markForDelete();
                    }
                }
                if (entityView.isMarkedForDelete()) {
                    pane.getChildren().remove(entityView.getNode());
                }
            }
            entityViews.removeIf(EntityView::isMarkedForDelete);

            displayLives();
            displayTime();
        } else {
            gameOver();
        }
    }

	public Scene getScene() {
        return scene;
    }

    public String getElapsedTimeFormatted(){
        long currentTime = System.currentTimeMillis();
        long elapsedTimeInMillis = currentTime - startTime;

// Calculate elapsed minutes and seconds
        long elapsedMinutes = elapsedTimeInMillis / (60 * 1000); // 60,000 milliseconds in a minute
        long elapsedSeconds = (elapsedTimeInMillis / 1000) % 60; // 1,000 milliseconds in a second

        String time = String.format("Time: %02d:%02d", elapsedMinutes, elapsedSeconds);
        return time;
    }

    public long getElapsedTime(){
        long currentTime = System.currentTimeMillis();
        long elapsedTimeInMillis = currentTime - startTime;

// Calculate elapsed minutes and seconds
        long elapsedSeconds = (elapsedTimeInMillis / 1000);

        return elapsedSeconds;
    }

    public Text generateText(String content, int size, double xPos, double yPos){
        Text newText = new Text(content);
        newText.setFont(Font.font("Arial", size));
        newText.setFill(Color.WHITE);
        newText.setX(xPos);
        newText.setY(yPos);
        newText.setVisible(true);
        return newText;
    }

    public String calculateScore(){
        int aliensKilled = model.getAliensKilled();
        int score = aliensKilled * 100;
        String scoreText = String.format("Score: %d", score);
        return scoreText;
    }

    public void gameOver(){
        double displayX = this.width/2 - 100;
        double displayY = this.height/2;
        Text displayText;
        Text scoreText;
        scoreString = calculateScore();
        if (model.hasPlayerWon()) {
            displayText = generateText("YOU WIN!", 36, displayX, displayY);
        } else {
            displayText = generateText("GAME OVER", 36, displayX, displayY);
        }
        scoreText = generateText(scoreString, 24, displayX, displayY + 50);

        pane.getChildren().add(displayText);
        pane.getChildren().add(scoreText);
    }

    public void displayTime(){
        pane.getChildren().remove(timeText);
        double displayX = this.width - 120;
        double displayY = 5;
        String time = getElapsedTimeFormatted();
        timeText = generateText(time, 18, displayX, displayY + 100);
        pane.getChildren().add(timeText);
    }

    public void displayLives(){
        pane.getChildren().remove(livesText);
        double displayX = 20;
        double displayY = 5 ;
        String lives = String.format("Lives: %d", this.model.getPlayerLives());
        livesText = generateText(lives, 18, displayX, displayY + 100);
        pane.getChildren().add(livesText);
    }

}
