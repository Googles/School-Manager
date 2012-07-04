package GuiFX.controls;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import GuiFX.Resources;

/**
 * ExtendedTreeItem is
 * 
 * @author Googles
 * 
 */
public class ExtendedTreeItem extends TreeItem<String>
{
    //htmlText of Editor is being stored in htmlText
    private String htmlText;

    public String getFullPath()
    {
	return (this.htmlText);
    }

    public void setFullPath(String htmlText)
    {
	this.htmlText = htmlText;
    }

    private boolean isDirectory;

    public boolean isDirectory()
    {
	return (this.isDirectory);
    }

    public void setIsDirectory(boolean isDirectory)
    {
	this.isDirectory = isDirectory;
    }

    private boolean isRoot;

    public void setIsRoot(boolean isRoot)
    {
	this.isRoot = isRoot;
    }

    public boolean isRoot()
    {
	return isRoot;
    }

    /**
     * Constructor creates new ExtendedTreeItem and sets EventHandler for Folder Elements
     * @param value: text displayed on the TreeItem
     * @param isDirectory: defines if the TreeItem is a directory (can include Notes)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ExtendedTreeItem(String value, boolean isDirectory)
    {
	super(value);
	setIsDirectory(isDirectory);

	if (isDirectory() && !isRoot())
	{
	    setGraphic(new IconView(Resources.folderCollapseImage));
	}
	else if (!isDirectory())
	{
	    setGraphic(new IconView(Resources.fileImage));
	}

	this.addEventHandler(TreeItem.branchExpandedEvent(), new EventHandler()
	{
	    @Override
	    public void handle(Event e)
	    {
		ExtendedTreeItem source = (ExtendedTreeItem) e.getSource();
		if (source.isDirectory() && source.isExpanded() && !source.isRoot())
		{
		    IconView iv = (IconView) source.getGraphic();
		    iv.setImage(Resources.folderExpandImage);
		}
	    }
	});

	this.addEventHandler(TreeItem.branchCollapsedEvent(), new EventHandler()
	{
	    @Override
	    public void handle(Event e)
	    {
		ExtendedTreeItem source = (ExtendedTreeItem) e.getSource();
		if (source.isDirectory() && !source.isExpanded() && !source.isRoot())
		{
		    IconView iv = (IconView) source.getGraphic();
		    iv.setImage(Resources.folderCollapseImage);
		}
	    }
	});
    }
}