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
        getElapsedTimeFormatted();
        model.update();
        List<Renderable> markedForDelete = new ArrayList<Renderable>();
        List<Renderable> renderables = model.getRenderables();
        for (Renderable entity : renderables) {
            if ((entity instanceof Damagable)){
                Damagable d = (Damagable) entity;
                if (!d.isAlive()){
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
            for (Renderable r: markedForDelete){
                if (entityView.matchesEntity(r)){
                    entityView.markForDelete();
                }
            }
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);
    }

	public Scene getScene() {
        return scene;
    }

    public void removeAllEntities(){
        for (EntityView entityView : entityViews) {
            entityView.markForDelete();
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);
    }

    public void getElapsedTimeFormatted(){
        long currentTime = System.currentTimeMillis();
        long elapsedTimeInMillis = currentTime - startTime;

// Calculate elapsed minutes and seconds
        long elapsedMinutes = elapsedTimeInMillis / (60 * 1000); // 60,000 milliseconds in a minute
        long elapsedSeconds = (elapsedTimeInMillis / 1000) % 60; // 1,000 milliseconds in a second

        System.out.printf("Elapsed Time: %d minutes %d seconds%n", elapsedMinutes, elapsedSeconds);
    }

}
