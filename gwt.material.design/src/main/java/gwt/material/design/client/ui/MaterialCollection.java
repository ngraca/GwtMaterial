package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ListItem;
import gwt.material.design.client.custom.MaterialWidget;
import gwt.material.design.client.custom.UnorderedList;
import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCollection extends MaterialWidget {

	private static MaterialCollectionUiBinder uiBinder = GWT.create(MaterialCollectionUiBinder.class);

	interface MaterialCollectionUiBinder extends UiBinder<Widget, MaterialCollection> {
	}

	@UiField 
	UnorderedList collection;
	
	public MaterialCollection() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Add each item on a collection container
	 * @param collection, the item to be added 
	 */
	@UiChild(tagname = "item")
	public void onAddItem(Widget item){
	
		ListItem list = new ListItem(item);
		list.addStyleName("collection-item");
		collection.add(list);
	}
	
	@UiChild(tagname = "header")
	public void onAddHeader(Widget item){
		ListItem list = new ListItem(item);
		list.addStyleName(MaterialResources.INSTANCE.materialcss().collectionHeader());
		collection.add(list);
	}
	
	@UiChild(tagname = "dismissable")
	public void onAddDismissable(Widget item){
		ListItem list = new ListItem(item);
		list.addStyleName("collection-item dismissable");
		collection.add(list);
	}
	
	@UiChild(tagname = "avatar")
	public void onAddAvatar(Widget item){
		ListItem list = new ListItem(item);
		list.addStyleName("collection-item avatar");
		collection.add(list);
		if(item instanceof MaterialPanel){
			HTMLPanel panel = (HTMLPanel) item;
			for(Widget w : panel){
				if(w instanceof MaterialIcon){
					w.addStyleName("secondary-content");
				}
			}
		}
	}

}
