package game.panes;

import java.text.DecimalFormat;
import java.util.ArrayList;

import game.utilities.Target;
import game.weapon.Gun;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;


// THIS CLASS REPRESENTS THE GAMEPLAY:
// It contains targets, a weapon, 
public class GamePlayPane extends Pane{
	// Labels
	Label timerLabel;
	Label scoreLabel;
	// For time and score
	int timeLeft = 444;
	int score = 0;
	
	// For the format of the score
	DecimalFormat scoreF = new DecimalFormat("#000");
	
	// There are 5 people
	ArrayList<Target>targets = new ArrayList<Target>();
	Gun gun;
	
	// Background Image
	private static final Image BG_IMG
		= new Image(GamePlayPane.class.getResourceAsStream("../images/campusbackground.png"));
	private ImageView background;
	
	
	// CONSTRUCTOR
	public GamePlayPane() {
		// Initialize timer Label
		timerLabel = new Label("TIME LEFT: " + timeLeft);
		timerLabel.setFont(new Font(30));
		
		// Initialize Score Label
		scoreLabel = new Label("SCORE: " + scoreF.format(score));
		scoreLabel.setFont(new Font(30));
		scoreLabel.setTextFill(Color.WHITE);
		
		// build media:
		Media media = new Media(GamePlayPane.class.getResource("../sounds/bg.mp3").toString());
		MediaPlayer player = new MediaPlayer(media);
		
		//background
		background = new ImageView(BG_IMG);
		
		// Allocate timerLabel appropriately 
		timerLabel.setTranslateX(500);
		// Player can now play;
		player.play();
		
		// Let's have a gun for the player to use
		gun = new Gun();
		gun.setParentPane(this); // THIS CLASS/ Container should be it parent
		
		
		// Add background this class
		this.getChildren().add(background);
		
		// Allocate all
		for(int i = 0; i < 5; i++) {
			Target t= new Target(new java.util.Random().nextInt(2));			
			t.setTranslateY(new java.util.Random().nextInt(100)+200);
			t.setSpeed(new java.util.Random().nextInt(10)+5);
			t.buildAnimation(new java.util.Random().nextInt(2));
			targets.add(t);
			this.getChildren().add(targets.get(i));
		}
		

	
				
		
		this.getChildren().addAll(scoreLabel,timerLabel, gun);
		buildTimer();
		buildCollisionDetection();
		
		
		
		gun.relocate(0, 550);
	}	
	//Tracks the time up to completion
	private void buildTimer() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000/30), e->{
			if(timeLeft>1)
				timeLeft--;
			else if(timeLeft == 1) {
				timeLeft = 0;
				Label label = new Label("TIME FINISHED");
				label.setFont(new Font(50));
				label.setTextFill(Color.RED);
				this.getChildren().add(label);
			}
			
			timerLabel.setText("TIME LEFT: " + timeLeft);
			
			
		}));
		
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	// THIS WILL BUILD THE COLLISION DETECTION WITH BEAN AND PEOPLE
	private void buildCollisionDetection() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), e->{
			for(int i = 0; i < targets.size(); i++) {
				// GET BOTH BOUNDS ( of student and bean)
				Bounds stBound = targets.get(i).localToScene(targets.get(i).getBoundsInLocal());
				Bounds beanBound = gun.getBean().localToScene(gun.getBean().getBoundsInLocal());
				
				// IF BOTH BOUNDS INTERSECTS THAT MEANS THEY ARE TOUCHING
				if(stBound.intersects(beanBound)) {
					// MINUS 50 if you hit a good person
					if(targets.get(i).getInfo() == Target.GOOD_PERSON)
						score -= 50;
					else
						// PLYS 50 if you hit a bad person
						score += 50;
					
					targets.get(i).setTranslateY(targets.get(i).getTranslateY()+9999);
					Target t= new Target(new java.util.Random().nextInt(2));			
					t.setTranslateY(new java.util.Random().nextInt(100)+200);
					t.setSpeed(new java.util.Random().nextInt(10)+5);
					t.buildAnimation(new java.util.Random().nextInt(2));
					this.getChildren().add(t);
					targets.add(t);
					
				}
				
			}
			scoreLabel.setText("Score: " + scoreF.format(score));
			
			
		}));
		
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
}
