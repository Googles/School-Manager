package GuiFX.controls;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import GuiFX.Resources;

/**
 * NotesBrowser defines an extended TreeView that defines TreeCell actions
 * and add a few core elements like the treeRoot item and the css stylesheet 
 * @author Googles
 *
 */
public class NotesBrowser extends TreeView<String>
{
    private IconView rootIcon;
    private ExtendedTreeItem treeRoot;
    
    public NotesBrowser()
    {
	this.setEditable(true);

	rootIcon = new IconView(Resources.rootImage);
	rootIcon.setSize(28.5, 28.5);

	treeRoot = new ExtendedTreeItem("Notes", true);
	treeRoot.setGraphic(rootIcon);
	treeRoot.setIsRoot(true);
	setRoot(treeRoot);

	this.setId("menubar_Notes");
	this.setShowRoot(true);
	this.setRoot(treeRoot);

	this.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>()
	{
	    @Override
	    public TreeCell<String> call(TreeView<String> p)
	    {
		return new TextFieldTreeCellImpl();
	    }
	});

	treeRoot.setIsRoot(true);
	treeRoot.setExpanded(true);
    }

    /**
     * TextFieldTreeCellImpl handles actions on TreeView items
     * Core functions: - Right Click on Folder -> create Folder, add Note, delete, rename
     * 		       - Right Click on Note -> add Note, delete, rename
     * @author Googles
     * @credits  Oracle TreeView Samples(http://docs.oracle.com/javafx/2/ui_controls/TreeViewSample.java.html)
     *
     */
    private final class TextFieldTreeCellImpl extends TreeCell<String>
    {

	private TextField textField;

	private ContextMenu noteMenu = new ContextMenu();
	private ContextMenu folderMenu = new ContextMenu();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TextFieldTreeCellImpl()
	{
	    //Initialize MenuItems
	    MenuItem addFolder = new MenuItem("Create Folder");
	    MenuItem addNote = new MenuItem("Create Note");
	    MenuItem delete = new MenuItem("Delete");
	    MenuItem rename = new MenuItem("Rename");
	    
	    //Add MenuItem to dedicated ContextMenus
	    noteMenu.getItems().addAll(rename, delete);
	    folderMenu.getItems().addAll(addNote, addFolder, rename, delete);

	    //Set MenuItem actions
	    addNote.setOnAction(new EventHandler()
	    {
		public void handle(Event t)
		{
		    // Change -> create file in directory -> add File to Tree
		    TreeItem newNote = new TreeItem<String>("New Note");
		    getTreeItem().getChildren().add(newNote);
		}
	    });

	    rename.setOnAction(new EventHandler()
	    {

		@Override
		public void handle(Event t)
		{
		    startEdit();
		}
	    });

	}

	/**
	 * Creates TextField on TreeCell Element to rename it
	 */
	@Override
	public void startEdit()
	{
	    if (!((ExtendedTreeItem) getTreeItem()).isRoot())
	    {
		super.startEdit();
		if (textField == null)
		{
		    createTextField();
		}
		setText(null);
		setGraphic(textField);
		textField.selectAll();
	    }
	}

	/**
	 * Cancel Edit; obvious comment is obvious...
	 */
	@Override
	public void cancelEdit()
	{
	    super.cancelEdit();

	    setText((String) getItem());
	    setGraphic(getTreeItem().getGraphic());
	}

	/**
	 * Updates TreeCell to new name and attach ContextMenus to different TreeCells
	 */
	@Override
	public void updateItem(String item, boolean empty)
	{
	    super.updateItem(item, empty);

	    if (empty)
	    {
		setText(null);
		setGraphic(null);
	    }

	    else
	    {
		if (isEditing())
		{
		    if (textField != null)
		    {
			textField.setText(getString());
		    }
		    setText(null);
		    setGraphic(textField);
		}
		else
		{
		    setText(getString());
		    setGraphic(getTreeItem().getGraphic());

		    if (((ExtendedTreeItem) getTreeItem()).isDirectory() && !((ExtendedTreeItem) getTreeItem()).isRoot())
		    {
			setContextMenu(folderMenu);
		    }
		    else if (!((ExtendedTreeItem) getTreeItem()).isDirectory())
		    {
			setContextMenu(noteMenu);
		    }
		}
	    }
	}

	/**
	 * Initializes TextField and adds onClick actions to handle commit / cancel
	 */
	private void createTextField()
	{
	    textField = new TextField(getString());
	    textField.setOnKeyReleased(new EventHandler<KeyEvent>()
	    {
		@Override
		public void handle(KeyEvent t)
		{
		    if (t.getCode() == KeyCode.ENTER)
		    {
			commitEdit(textField.getText());
		    }
		    else if (t.getCode() == KeyCode.ESCAPE)
		    {
			cancelEdit();
		    }
		}
	    });
	}

	private String getString()
	{
	    return getItem() == null ? "" : getItem().toString();
	}
    }
}