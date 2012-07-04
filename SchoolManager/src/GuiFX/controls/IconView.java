package GuiFX.controls;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconView extends ImageView
{
    public IconView(Image icon)
    {
	super(icon);
	this.setFitHeight(24);
	this.setFitWidth(24);
    }
    
    public void setSize(double height, double width)
    {
	this.setFitHeight(height);
	this.setFitWidth(width);
    }
}
