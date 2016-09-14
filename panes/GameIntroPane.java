package game.panes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/* ***************************************************
	Author: [first name] [last name]
	Last Modification:	[DD/MM/YY] [99:99] [AM/PM]  	 
 * ***************************************************/

public final class GameIntroPane extends Pane {
//------------------------ DATA FIELD ------------------------------//
	// Video-File link:											    //	
	private final String VIDEO_FILE = "../videos/CIS GALLERY_1.mp4";//
																    //			
	// For media control and view:								    //	
	private Media media;										    //			
	private MediaPlayer mediaPlayer;							    //	
	private MediaView mediaView;								    //
																    //	
	// A time-line to keep on track of the video:				    //	
	private Timeline timeline;									    //
																    //		
	// In order to go to the next scene:						    //
	private Scene nextScene;									    //
	private Stage stage;										    //
//------------------------------------------------------------------//
	
	
/* **********************
 * 		Constructor	
 * **********************/
	public GameIntroPane() {
		// Initialize media:
		media = new Media(GameIntroPane.class.getResource(VIDEO_FILE).toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView = new MediaView(mediaPlayer);
		
		// Add the mediaView to this pane:
		getChildren().add(mediaView);
						
		// In order to play the video:
		mediaPlayer.play();
		//mediaPlayer.setMute(true);
		
		// build a time-line that checks for the end of the video:
		buildTimeline();
		
	}
	

/* **********************
 * 	  Build Time-line
 * *********************/	
	public void buildTimeline() {
		// Duration for one cycle will be one second:
		final Duration ONE_SECOND = Duration.millis(1);
		
		// The event that is going to be checked in that one second
		final EventHandler<ActionEvent> EVENT = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// if the media player reaches the end of the video:
				if(mediaPlayer.getCurrentTime().toSeconds() >= media.getDuration().toSeconds()-0.10) {
					timeline.stop();
					if(nextScene != null)
						stage.setScene(nextScene);
						MainMenuPane.READY = true;
				}
			}
		};
		
		// Lets put the duration and the event inside a key frame:
		final KeyFrame KEYFRAME = new KeyFrame(ONE_SECOND, EVENT);
		
		// Initialize the time-line by telling it which key frame to run:
		timeline = new Timeline(KEYFRAME);		
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
	}
	

	public void setNextScene(Scene nextScene, Stage stage) {
		this.nextScene = nextScene;
		this.stage = stage;
	}
	
}

