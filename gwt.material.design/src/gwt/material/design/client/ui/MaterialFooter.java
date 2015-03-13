package gwt.material.design.client.ui;

import gwt.material.design.client.custom.CustomFooter;
import gwt.material.design.client.custom.ListItem;
import gwt.material.design.client.custom.UnorderedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialFooter extends Composite {

	private static MaterialFooterUiBinder uiBinder = GWT.create(MaterialFooterUiBinder.class);

	interface MaterialFooterUiBinder extends UiBinder<Widget, MaterialFooter> {
	}
	
	@UiField MaterialColumn footerContent;
	@UiField UnorderedList footerLinkList;
	@UiField CustomFooter footerPanel;
	@UiField Label lblCopyright, lblLinkTitle;
	
	private String color = "";
	private String copyright = "";
	private String link = "";
	
	public MaterialFooter() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiChild(tagname = "content")
	public void onAddFooterContent(Widget w){
		footerContent.add(w);
	}
	
	@UiChild(tagname = "link")
	public void onAddFooterLinks(Widget w){
		footerLinkList.add(new ListItem(w));
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		if(!color.isEmpty()) footerPanel.addStyleName(color);
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
		if(!copyright.isEmpty()) lblCopyright.setText(copyright);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
		if(!link.isEmpty()) lblLinkTitle.setText(link);
	}
	
}
