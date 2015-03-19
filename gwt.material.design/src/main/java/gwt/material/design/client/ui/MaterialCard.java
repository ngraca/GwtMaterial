package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCard extends Composite {

	private static MaterialCardUiBinder uiBinder = GWT
			.create(MaterialCardUiBinder.class);

	interface MaterialCardUiBinder extends UiBinder<Widget, MaterialCard> {
	}

	@UiField
	Image imgCard;
	
	@UiField 
	Label lblTitle, lblDescription;
	
	@UiField 
	HTMLPanel cardPanel, cardContentPanel, cardRevealPanel,cardRevealContent, actionPanel, headerPanel;
	
	private ImageResource resource;
	private String url = "";
	private String title="";
	private String description = "";
	private String size = "medium";
	private String type = "";
	
	public MaterialCard() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ImageResource getResource() {
		return resource;
	}

	public void setResource(ImageResource resource) {
		this.resource = resource;
		imgCard.setResource(resource);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		imgCard.setUrl(url);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		generateCard();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		lblDescription.setText(description);
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
		if(!size.isEmpty()){
			cardPanel.addStyleName(size);
		}
	}

	public String getType() {
		return type;
	}
	
	@UiChild( tagname = "link" )
	public void addWidget(Widget item) {
		item.getElement().getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
		item.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		actionPanel.add(item);
	} 
	
	@UiChild( tagname = "reveal" )
	public void addRevealContent(Widget item){
		cardRevealContent.add(item);
	}

	public void setType(String type) {
		this.type = type;
		generateCard();
	}
	
	private void generateCard(){
		switch (type) {
		case "reveal":
			cardContentPanel.clear();
			cardRevealPanel.clear();
			lblTitle.removeFromParent();
			cardContentPanel.add(new HTML("<span class='card-title activator grey-text text-darken-4'>"+title+"<i class='mdi-navigation-more-vert right'></i></span>"));
			
			cardContentPanel.getElement().getStyle().setPaddingBottom(0, Unit.PX);
			cardRevealPanel.add(new HTML("<span class='card-title activator grey-text text-darken-4'>"+title+"<i class='mdi-navigation-close right'></i></span>"));
			cardRevealPanel.add(lblDescription);
			cardRevealPanel.add(cardRevealContent);
			break;
		case "basic":
			cardContentPanel.clear();
			cardRevealPanel.removeFromParent();
			cardContentPanel.add(new HTML("<span class='card-title black-text'>"+title+"</span>"));
			cardContentPanel.add(lblDescription);
			headerPanel.removeFromParent();
			break;
		case "image":
			cardRevealPanel.removeFromParent();
			cardRevealPanel.removeFromParent();
			lblTitle.setText(title);
			lblDescription.setText(description);
			break;
		default:
			break;
		}
	}
	

}
