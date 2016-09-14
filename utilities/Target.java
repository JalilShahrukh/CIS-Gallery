package game.utilities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/*
 * Target Class
 * - This class contains two forms: Good and Bad
 * - but it can only be one of them.
 * - Good means that its good to shoot at
 * - Bad means that its bad to shoot at.
 * - There is also animation implemented here
 *   which makes the target move left and right
 */
public class Target extends Pane {
	// Images:
	private static final Image [] TARGET_IMG = {
		new Image(Target.class.getResourceAsStream("../images/redRobot.gif")),
		new Image(Target.class.getResourceAsStream("../images/blueRobot2.gif"))
	};
	
	// ImageView
	private ImageView target;
	
	// Values for Target info:
	public static final int GOOD_PERSON = 0;
	public static final int BAD_PERSON = 1;
	private int info;
	
	// Values for Target directions:
	public static final int RIGHT = 0;
	public static final int LEFT = 1;
	int dir; // direction holder
	
	// Place holder
	private Rectangle rect = new Rectangle(30,30);
	
	// Timeline for animation:
	Timeline timeline;
	
	// speed of the target 
	private int speed = 15;
	
	
	// CONSTRUCTOR:
	public Target(int info) {
		target = new ImageView(TARGET_IMG[info]);
		target.setFitWidth(65);
		target.setFitHeight(100);
		this.info = info;
		this.getChildren().add(target);
	
	}
	
	// build the translational animation of the target:
	public void buildAnimation(final int direction) {
		dir = direction;
		
		// This creates an animation timeline with a duration of 30 FPS
		timeline = new Timeline(new KeyFrame(Duration.millis(1000/30), e-> {
			// if direction is RIGHT
			if(dir == RIGHT) {
					// This moves the target towards the right
					this.setTranslateX(this.getTranslateX() + speed);
					this.setScaleX(1);
					
					// Go LEFT if it reaches the right end
					if(this.getBoundsInParent().getMinX()>770) {
						dir= LEFT;
					}
			}

			// if direction is LEFT
			else if (dir == LEFT) {
					
					// This moves the target towards the left
					this.setTranslateX(this.getTranslateX() - speed);
					this.setScaleX(-1);
					
					// Go RIGHT if it reaches the left end
					if(this.getBoundsInParent().getMinX()<=0) {
						dir= RIGHT;
					}
			}
			
		}));
		
		// repeat the animation indefinite (unless stopped of course)
		timeline.setCycleCount(Timeline.INDEFINITE);
		
		// play animation;
		timeline.play();
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getInfo() {
		return this.info;
	}
	
}
