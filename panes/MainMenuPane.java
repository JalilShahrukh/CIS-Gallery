package game.panes;

import game.utilities.MenuButton;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/* ***************************************************
	Author: [first name] [last name]
	Last Modification:	[DD/MM/YY] [99:99] [AM/PM]  	 
 * ***************************************************/

public class MainMenuPane extends Pane{
/* **********************************
   	         IMAGES
 * **********************************/
	private static final Image BACKGROUND_IMG = 
			new Image(MainMenuPane.class.getResourceAsStream("../images/menu-background.png"));
	private static final Image TITLE_IMG =
			new Image(MainMenuPane.class.getResourceAsStream("../images/menu-title.png"));
	private static final Image MENUBOX_IMG =
			new Image(MainMenuPane.class.getResourceAsStream("../images/menu-box.png"));
	private static final Image UNHOVER_PLAY_IMG =
			new Image(MainMenuPane.class.getResourceAsStream("../images/unhover-play-button.png"));
	private static final Image UNHOVER_HIGHSCORE_IMG =
			new Image(MainMenuPane.class.getResourceAsStream("../images/unhover-highscore-button.png"));
	private static final Image UNHOVER_SETTINGS_IMG =
			new Image(MainMenuPane.class.getResourceAsStream("../images/unhover-settings-button.png"));
	private static final Image HOVER_PLAY_IMG =
			new Image(MainMenuPane.class.getResourceAsStream("../images/hover-play-button.png"));
	private static final Image HOVER_HIGHSCORE_IMG =
			new Image(MainMenuPane.class.getResourceAsStream("../images/hover-highscore-button.png"));
	private static final Image HOVER_SETTINGS_IMG =
			new Image(MainMenuPane.class.getResourceAsStream("../images/hover-settings-button.png"));
/* **********************************************/
	
	// For a one-time animation:
	public static boolean READY = false;
	private static boolean FIRST_TIME = true;
	private Timeline timeline;
	
	// To view the the images:
	private ImageView menuBackground;
	private ImageView menuTitle;
	private ImageView menuBox;
	
	// To do fade-animation effects:
	private FadeTransition backgroundFadeTransition;
	private FadeTransition titleFadeTransition;	
	private FadeTransition boxFadeTransition;
	
	// To do some translate-animation effects:
	private TranslateTransition titleTranslateTransition;
	private TranslateTransition boxTranslateTransition;
	
	// MenuButtons for the menu
	private MenuButton playButton;
	private MenuButton highScoreButton;
	private MenuButton settingsButton;
	
	// Vertical Box:
	private VBox vbox;
	
	// In order to go to the next possible stage:
	private Scene playScene;
	private Scene settingsScene;
	private Scene highScoreScene;
	private Stage stage;


/* *********************
		CONSTRUCTOR
 * *********************/    
	public MainMenuPane() {		
		initialize();
		relocateObjects();
		buildAnimation();
		buildButtonsControl();

		// Add the menuBackground and menuTitle to this pane.
		this.getChildren().addAll(menuBackground, menuTitle, menuBox, vbox);
	}
	
	

  /* **********************
   * 	  INITIALIZE
   * **********************/
	public void initialize() {
		//Initialize image views:
		menuBackground = new ImageView(BACKGROUND_IMG);		
		menuTitle = new ImageView(TITLE_IMG);
		menuBox = new ImageView(MENUBOX_IMG);
		menuBox.setOpacity(0);						
				
		// Initialize menu-buttons:
		playButton = new MenuButton(UNHOVER_PLAY_IMG, HOVER_PLAY_IMG);
		highScoreButton = new MenuButton(UNHOVER_HIGHSCORE_IMG, HOVER_HIGHSCORE_IMG);
		settingsButton = new MenuButton(UNHOVER_SETTINGS_IMG, HOVER_SETTINGS_IMG);
				
		// Give the menu buttons to a vertical container [NOTE: VBox is also a layout]
		vbox = new VBox(playButton, highScoreButton, settingsButton);
		vbox.setVisible(false);
	}
	
	
  /* ***********************
   * 	RelocateObjects
   * ***********************/
	public void relocateObjects() {
		/* ****************************************************
		 * NOTE: the following code will place some of the menu 
		 * objects at a certain starting location--right before 
		 * the animation occurs.  
		 * ****************************************************/

		// Put the title on the center
		double fixedTitle = (BACKGROUND_IMG.getWidth() - TITLE_IMG.getWidth())/2;
		menuTitle.setTranslateX(menuTitle.getTranslateX()+fixedTitle);
		menuTitle.setTranslateY(-500);

		
		menuBox.setTranslateX(-1000);	// puts the menuBox out of bounce
		menuBox.setTranslateY(180);		// puts the menuBox
	}
	
	
	
	
 /* **********************
  * 	  ANIMATION
  * *********************/	
	public void buildAnimation() {
		/* ********************************************************
		 * 	NOTE: the following codes will animate each menu object
		 * ********************************************************/		
		
		
		// FADING ANIMATION OF THE MENU TITLE
		titleFadeTransition = new FadeTransition(Duration.millis(500), menuTitle);
		titleFadeTransition.setFromValue(0);
		titleFadeTransition.setToValue(1);

		// TRANSLATE ANIMATION OF THE MENU TITLE
		titleTranslateTransition = new TranslateTransition(Duration.millis(500), menuTitle);
		titleTranslateTransition.setFromY(-500);
		titleTranslateTransition.setToY(80);

		// FADE ANIMATION OF THE MENU BACKGROUND
		backgroundFadeTransition = new FadeTransition(Duration.millis(800), menuBackground);
		backgroundFadeTransition.setFromValue(0); // from opacity: 0%
		backgroundFadeTransition.setToValue(1);   // to opacity: 100%  		
		backgroundFadeTransition.setOnFinished(new EventHandler<ActionEvent>(){
			// This means: "if the background animation finishes.." 
			@Override   
			public void handle(ActionEvent event) {
				// "..then play the other animations."
				titleTranslateTransition.play();
				titleFadeTransition.play();
				boxFadeTransition.play();
				boxTranslateTransition.play();  
			}
		});
		//backgroundFadeTransition.play();
		
		// FADE ANIMATION OF THE MENU BOX
		boxFadeTransition = new FadeTransition(Duration.millis(1200), menuBox);
		boxFadeTransition.setFromValue(0);
		boxFadeTransition.setToValue(1);
		
		// TRANSLATE ANIMATION OF THE MENU BOX
		double fixedBox = (BACKGROUND_IMG.getWidth() - MENUBOX_IMG.getWidth())/2; // to get box in center
		boxTranslateTransition = new TranslateTransition(Duration.millis(500), menuBox);
		boxTranslateTransition.setFromX(-500);
		boxTranslateTransition.setToX(fixedBox);
		boxTranslateTransition.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				vbox.relocate(menuBox.getTranslateX()+10, menuBox.getTranslateY()+120);
				vbox.setVisible(true);
			}
		});
		
		// TIMELINE: This checks whether the main menu is ready to animate: it will only animate once!
		timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent event) {
				if(READY && FIRST_TIME) {
					FIRST_TIME = false;
					backgroundFadeTransition.play();
					timeline.stop();					
				}
				
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
  /* **************************
   *  BUILD BUTTONS CONTROL
   * **************************/
	public void buildButtonsControl() {
		//If play button is clicked
		playButton.setOnMouseClicked(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				if(playScene != null)
					stage.setScene(playScene);
			}
		});
		
		//If high-score button is clicked
		highScoreButton.setOnMouseClicked(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				if(highScoreScene != null)
					stage.setScene(highScoreScene);
			}
		});
		
		//If high-score button is clicked
		settingsButton.setOnMouseClicked(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				if(settingsScene != null)
					stage.setScene(settingsScene);
			}
		});
	}
		

	
	
	
  /* ************************
   * 	SCENE SETTERS
   * ************************/
	public void setPlayScene(Scene playScene, Stage stage) {
		this.playScene = playScene;
		this.stage = stage;
	}
	
	public void setSettingsScene(Scene settingsScene, Stage stage) {
		this.settingsScene = settingsScene;
		this.stage = stage;
	}
	
	public void setHighScoreScene(Scene highScoreScene, Stage stage) {
		this.highScoreScene = highScoreScene;
		this.stage = stage;
	}
}

