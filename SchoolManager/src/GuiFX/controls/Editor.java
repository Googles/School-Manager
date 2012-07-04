package GuiFX.controls;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

/**
 * This class defines a VBox, that contains an Optionbar (HBox) and a HTMLEditor.
 * Editor is used as "Center" Element in the root layout for the category Notes and handles
 * the text that is stored within the TreeView Elements
 * @author Googles
 *
 */

public class Editor extends VBox
{
    private HTMLEditor editor;
    private HBox optionbar;
    
    private TextField title;
    private Button save;
    
    public Editor()
    {
	//Initializing HTMLEditor & changing its layout to set all toolbar elements into one line
	editor = new HTMLEditor();
	editor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	editor.lookup(".top-toolbar").setDisable(true);
	editor.lookup(".top-toolbar").setManaged(false);
	editor.setId("editor");
	((ToolBar) editor.lookup(".bottom-toolbar")).getItems()
	.addAll(FXCollections.observableArrayList(((ToolBar)editor.lookup(".top-toolbar")).getItems()));
	
	//Initializing the optionbar and adding the two core elements
	optionbar = new HBox();
	
	title = new TextField();
	title.setPromptText("Insert Title");
	title.setPrefHeight(20);
	title.setId("editor-title");
	
	save = new Button("Save");
	save.setPrefSize(95, 35);
	save.setId("editor-button");
	
	optionbar.getChildren().addAll(title, save);
	optionbar.setPrefHeight(36.5);
	optionbar.setId("editor-titlebar");
	HBox.setHgrow(title, Priority.ALWAYS);
	
	//Set Editor Options, HTML Editor shall take all free space up
	VBox.setVgrow(editor, Priority.ALWAYS);
	this.setMinSize(0, 0);
	this.setPrefHeight(34);
	this.getChildren().addAll(optionbar, editor);
    }
}