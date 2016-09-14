package game.weapon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;




/*
 *  This class contains all the functionalities of the gun
 */
public class Gun extends Pane{
	private static final Image WHEEL_IMG = 
			new Image(Gun.class.getResourceAsStream("../images/weapon_gear_updated.png"));
	
	private static final Image CANON_IMG = 
			new Image(Gun.class.getResourceAsStream("../images/weapon_shortened_updated.png"));
	
	private static final Image BEAN_IMG =
			new Image(Gun.class.getResourceAsStream("../images/Beanbag_1_transparent.png"));
	
	
	private ImageView wheel;
	private ImageView canon;
	private ImageView tempBean = new ImageView();
	private boolean allowToMove;
	private String arrowPressed;
	private double speed = 10;
	private double currentRotation;
	private boolean allowToShoot = true;
	
	private Pane parentPane;
	
	public Gun () {
		// Initialize Data field:
		initialize();
		
		// Build keyboard event listeners
		buildKeyFunctionalities();
		
		// build animation for the gun when moving
		buildAnimation();
		
	}
	
	private void initialize() {
		wheel = new ImageView(WHEEL_IMG);
		canon = new ImageView(CANON_IMG);
		
		final double DSP = 0.6;
		
		wheel.setFitWidth(WHEEL_IMG.getWidth()*0.3*DSP);
		wheel.setFitHeight(WHEEL_IMG.getHeight()*0.3*DSP);
		
		canon.setFitWidth(CANON_IMG.getWidth()*0.3*DSP);
		canon.setFitHeight(CANON_IMG.getHeight()*0.7*DSP);
		
		wheel.setTranslateX(wheel.getTranslateX()+5);
		canon.setTranslateY(canon.getTranslateY()-35);
		
		
	
		allowToMove = false;
		arrowPressed = "";
		canon.setFocusTraversable(true);
		wheel.setFocusTraversable(true);
		this.getChildren().addAll(wheel, canon); // Canon in front
		canon.setTranslateY(canon.getTranslateY()-10);
		canon.setTranslateX(canon.getTranslateX()+3);
		currentRotation = wheel.getRotate();
	}
	
	
	
/* ****************************
 * 	build Key Functionalities
 * ****************************/
	private void buildKeyFunctionalities() {
		this.setOnKeyPressed(e-> {
			switch(e.getCode()) {
			
			// IF LEFT KEY IS PRESSED
			case LEFT:
				arrowPressed = "LEFT";
				allowToMove = true;
				break;
				
			// IF RIGHT KEY IS PRESSED
			case RIGHT:
				arrowPressed = "RIGHT";
				allowToMove = true;
				break;
				
			// IF SPACE KEY IS PRESSED:
			case SPACE:
				
				if(allowToShoot)
					shoot();
				break;
			default:
				break;
			}
		});
		
		this.setOnKeyReleased(e->{
			switch(e.getCode()) {
			
			// IF LEFT KEY IS RELEASED
			case LEFT:
				arrowPressed = "";
				allowToMove = false;
				break;
				
			// IF RIGHT KEY IS RELEASED
			case RIGHT:
				arrowPressed = "";
				allowToMove = false;
				break;
			default:
					break;
			}
		});
		
	}
	
	
	// BUILD GUN ANIMATION
	private void buildAnimation() {
		Timeline timeline;
		KeyFrame keyframe = new KeyFrame(Duration.millis(1), e-> {
			// IF THE GUN IS ALLOW TO MOVE and IF KEY PRESSED = RIGHT
			if(allowToMove && arrowPressed.equals("RIGHT")) {
				// Move the entire gun to the right:
				this.setTranslateX(this.getTranslateX()+speed/(1000/60));
				currentRotation += speed/(1000/60);
				
				// Rotate Wheel in positive direction
				this.wheel.setRotate(currentRotation);
				
			}
			// IF THE GUN IS ALLOW TO MOVE and IF KEY PRESSED = LEFT
			else if(allowToMove && arrowPressed.equals("LEFT")) {
				// Move the entire gun to the left:
				this.setTranslateX(this.getTranslateX()-speed/(1000/60));
				currentRotation -= speed/(1000/60);
				
				// Ratate Wheel in negative direction:
				this.wheel.setRotate(currentRotation);
				
			}
		});
		timeline = new Timeline(keyframe);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	// SHOOT BEAN BAG
	private void shoot() {
		allowToShoot = false;
		// Get a new bean
		
		ImageView bean = new ImageView(BEAN_IMG);
		tempBean = bean;
		// Set the right size:
		bean.setFitHeight(60*0.7);
		bean.setFitWidth(60*0.7);
		
		// Re-position it's current location (near the canon)
		bean.setTranslateX(this.getTranslateX()+20);
		bean.setTranslateY(this.getTranslateY()+520);
		
		// build the translational motion, with a duration of 1 second, and the obj being move (bean)
		TranslateTransition tt = new TranslateTransition(Duration.millis(1000), bean);
		tt.setByY(-1000); // Its going to move -1000 pixels;
		parentPane.getChildren().add(1,bean); // add this bean to this gameplay pane.
		
		// Destroy the bean when it is done, once destroyed, you are allow to shoot:
		tt.setOnFinished(e-> {parentPane.getChildren().remove(bean); 
			allowToShoot = true;
		});
		
		// Play the translational of motion (in other words, SHOOT!);
		tt.play();
	}
	
	public void setParentPane(Pane parentPane) {
		this.parentPane = parentPane;
	}
	
	public ImageView getBean() {
		return this.tempBean;
	}
	

}
