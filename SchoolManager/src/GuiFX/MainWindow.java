package GuiFX;

import GuiFX.controls.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainWindow extends Application
{
    private Scene scene;
    private BorderPane root;
    private ToolBar toolbar;
    private VBox menubar;

    private NotesBrowser browser;
    private Editor editor;

    public static void main(String[] args)
    {
	launch();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
	root = new BorderPane();

	scene = new Scene(root, 1280, 800);
	scene.getStylesheets().add(getClass().getResource("project.css").toExternalForm());

	initTopbar();
	initMenubar();
	editor = new Editor();

	stage.setTitle("School Manager");

	//set Stage preferences
	stage.setMinHeight(512);
	stage.setMinWidth(768);
	stage.setScene(scene);
	stage.show();

	//Workaround to let the Editor display correctly
	WebView webview = (WebView) editor.getChildren().get(1).lookup("WebView");
	GridPane.setHgrow(webview, Priority.ALWAYS);
	GridPane.setVgrow(webview, Priority.ALWAYS);
    }

    /**
     * Initializes the ToolBar on top 
     */
    public void initTopbar()
    {
	toolbar = new ToolBar();

	HBox titleBox = new HBox();
	Region spacer = new Region();
	Label projectName = new Label("School Manager");
	projectName.setId("projectName");

	HBox.setHgrow(spacer, Priority.ALWAYS);
	titleBox.getChildren().addAll(projectName, spacer);
	titleBox.setAlignment(Pos.CENTER_LEFT);

	toolbar.getItems().addAll(titleBox, spacer);
	toolbar.setId("toolbar");
	toolbar.setPrefHeight(65);
	toolbar.setMaxHeight(65);

	root.setTop(toolbar);
    }

    /**
     * Initializes the Menubar located on the left in the root layout
     * The Menubar is responsible for changing categories and display the associated elements
     */
    private void initMenubar()
    {
	ToggleButton tb1 = new ToggleButton("School");
	tb1.setId("toggle-button");

	ToggleButton tb2 = new ToggleButton("Social");
	tb2.setId("toggle-button");

	ToggleButton tb3 = new ToggleButton("Notes");
	tb3.setId("toggle-button");

	tb1.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent event)
	    {

	    }
	});

	tb2.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent event)
	    {

	    }
	});

	tb3.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent arg0)
	    {
		browser.setVisible(true);
		root.setCenter(editor);
	    }
	});

	ToggleGroup group = new ToggleGroup();
	tb1.setToggleGroup(group);
	tb2.setToggleGroup(group);
	tb3.setToggleGroup(group);

	group.selectToggle(tb1);

	HBox hBox = new HBox();
	hBox.getChildren().addAll(tb1, tb2, tb3);

	menubar = new VBox();
	menubar.getChildren().add(hBox);
	menubar.setId("menubar");

	// Initializing Notes Menu
	browser = new NotesBrowser();
	browser.setVisible(false);

	// Inizializing Menubar
	menubar.getChildren().add(browser);
	VBox.setVgrow(browser, Priority.ALWAYS);

	root.setLeft(menubar);
    }
}