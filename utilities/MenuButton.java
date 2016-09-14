
package game.utilities;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/* ***************************************************
	Author: [first name] [last name]
	Last Modification:	[DD/MM/YY] [99:99] [AM/PM]  	 
 * ***************************************************/
public class MenuButton extends StackPane {
	
//------------- DATA FIELD --------------//
    private ImageView menuButton;        //
    private Image unHoverImage;          //
    private Image hoverImage;            //
//---------------------------------------//
    
    
 /* ********************
  *    CONSTRUCTOR   
  * *******************/
    public MenuButton(Image unHoverImage, Image hoverImage) {
    	
    	// Set the images;
    	setUnHoverImage(unHoverImage);
        setHoverImage(hoverImage);
        
        // Set the menu button with the unHoverImage
        menuButton = new ImageView(unHoverImage);
        
        // build the unHover and hover functionalities:
        buildHoverControls();
        
        
        // Add the menu button to this pane:
        this.getChildren().add(menuButton);
        
    }
    
    
 /* *******************
  *     MUTATORS
  * ******************/
    
    // hoverImage Setter:
    public void setHoverImage(Image hoverImage) {
    	this.hoverImage = hoverImage;
    }
    
    // unHoverImage Setter:
    public void setUnHoverImage(Image unHoverImage) {
    	this.unHoverImage = unHoverImage;
    }
    
 /* ******************
  *    ACCESSORS  
  * *****************/
    
    // hoverImage Getter:
    public Image getHoverImage() {
    	return this.hoverImage;
    }
    
    // unHoverImage Getter:
    public Image getUnHoverImage() {
    	return this.unHoverImage;
    }
    
    
    
    
 /* ************************
  *   buildHoverControls()  
  * ************************/
    private void buildHoverControls() {
    	
    	// Specify what happens when the mouses touches/entered this menu button
    	this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	// set the menu button's image to hover-image
                menuButton.setImage(hoverImage);
            }
            
        });
        
    	// Specify what happens when the mouses exits this button:
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	// Set the menu button's image to unHover-image:
                menuButton.setImage(unHoverImage);
            }
        });
    }   
}
