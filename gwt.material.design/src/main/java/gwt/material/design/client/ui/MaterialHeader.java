package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class MaterialHeader extends Composite {

	private static MaterialHeaderUiBinder uiBinder = GWT.create(MaterialHeaderUiBinder.class);

	interface MaterialHeaderUiBinder extends UiBinder<Widget, MaterialHeader> {
	}
	
	@UiField Image imgLogo;
	@UiField HTMLPanel navigation, panel;
	
	private String color = "white";
	private String textColor = "black";
	
	private ImageResource logo;
	private String url;

	public MaterialHeader() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiChild( tagname = "item" )
	public void addWidget(Widget item) {
		navigation.add(item);
	} 

	public void addNavItem(Widget w){
		navigation.add(w);
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		panel.getElement().setAttribute("style", "color: " + textColor);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		panel.addStyleName(color);
	}

	public ImageResource getLogo() {
		return logo;
	}

	public void setLogo(ImageResource logo) {
		this.logo = logo;
		imgLogo.setResource(logo);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		imgLogo.setUrl(url);
	}

	public Image getImgLogo() {
		return imgLogo;
	}

	public void setImgLogo(Image imgLogo) {
		this.imgLogo = imgLogo;
	}
	
	

}
