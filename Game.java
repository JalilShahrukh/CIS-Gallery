//This is the main file which holds and calls the 

package game;

import game.panes.GameIntroPane;
import game.panes.GameOverPane;
import game.panes.GamePlayPane;
import game.panes.HighScorePane;
import game.panes.MainMenuPane;
import game.panes.PausePane;
import game.panes.SettingsPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*
 * THIS IS THE MAIN CLASS
 * It connects all the panes together
 */
public final class Game extends Application {
// ------------------------- DATA FIELD ----------------------------//	
	// Window settings:							   				 	//
	private static final String WINDOW_TITLE = "Shooting Gallery";  //
	private static final int WINDOW_WIDTH = 800;   					//	
	private static final int WINDOW_HEIGHT = 600;  					//	
												   					//				
	// Scenes:								 	   					//					
	private Scene gameIntroScene;			       					//	
	private Scene mainMenuScene;			       					//	
	private Scene settingsScene; 			       					//
	private Scene highScoreScene;			       					//
	private Scene gamePlayScene;			       					//
	private Scene pauseScene;				       					//
	private Scene gameOverScene;			       					//
											       					//
	// Panes:								       					//	
	private GameIntroPane gameIntroPane;	       					//	
	private MainMenuPane mainMenuPane;		       					//  NOTE: Pane means "plain layout"
	private SettingsPane settingsPane;		       					//		  or you can think of it as
	private HighScorePane highScorePane;	       					//		  a container that holds things inside
	private GamePlayPane gamePlayPane;								//		  
	private PausePane pausePane;			       					//		  The scenes will display these layouts	
	private GameOverPane gameOverPane;								//		  along with the things they contain 	
// -----------------------------------------------------------------//		  inside (e.g. images, text, buttons..etc)	
	
	
	@Override
	public void start(Stage primaryStage) {
		// Initialize panes:
		gameIntroPane = new GameIntroPane();
		mainMenuPane = new MainMenuPane();
		settingsPane = new SettingsPane();
		highScorePane = new HighScorePane();
		gamePlayPane = new GamePlayPane();
		pausePane = new PausePane();
		gameOverPane = new GameOverPane();
		
		// Initialize Scenes:
		gameIntroScene = new Scene(gameIntroPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		mainMenuScene = new Scene(mainMenuPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		settingsScene = new Scene(settingsPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		highScoreScene = new Scene(highScorePane, WINDOW_WIDTH, WINDOW_HEIGHT);
		gamePlayScene = new Scene(gamePlayPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		pauseScene = new Scene(pausePane, WINDOW_WIDTH, WINDOW_HEIGHT);
		gameOverScene = new Scene(gameOverPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		// Indicate scene order:
		gameIntroPane.setNextScene(mainMenuScene, primaryStage);	   // The next scene is 'mainMenuScene',
																	   // and 'primaryStage' will display that scene.
		
		mainMenuPane.setPlayScene(gamePlayScene, primaryStage);	       // if play button is clicked, the next scene is
																	   // 'gamePlayScene', and 'primaryStage' will display it.
		
		mainMenuPane.setHighScoreScene(highScoreScene, primaryStage);  // if highscore button is clicked, the next scene is
																	   // 'highScoreScene', and 'primaryStage' will display it.
		
		mainMenuPane.setSettingsScene(settingsScene, primaryStage);    // if settings button is clicked, the next scene is
																	   // 'settingsScene' and 'primaryStage' will display it.
		
		
		
		
		
		
		primaryStage.setTitle(WINDOW_TITLE);	// Set window title
		primaryStage.setScene(gameIntroScene);  // specify the first scene
		primaryStage.show();					// make the window visible	
	}
	
	public static void main (String [] args) {
			launch(args);
	}
}
