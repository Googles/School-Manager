package GuiFX;

import javafx.scene.image.Image;

/**
 * Defines public resources for different Classes
 * @author Googles
 *
 */
public class Resources
{
    public static Image rootImage = new Image(Resources.class.getResourceAsStream("../icons/notes_root.png"));
    public static Image folderCollapseImage = new Image(Resources.class.getResourceAsStream("../icons/folder_closed.png"));
    public static Image folderExpandImage = new Image(Resources.class.getResourceAsStream("../icons/folder_open.png"));
    public static Image fileImage = new Image(Resources.class.getResourceAsStream("../icons/note.png"));
}
