package gwt.material.design.client.ui;

import gwt.material.design.client.custom.CustomAnchor;
import gwt.material.design.client.custom.CustomButton;
import gwt.material.design.client.custom.CustomIcon;
import gwt.material.design.client.custom.MaterialWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialButton extends MaterialWidget implements HasClickHandlers{

	private static MaterialButtonUiBinder uiBinder = GWT.create(MaterialButtonUiBinder.class);

	interface MaterialButtonUiBinder extends UiBinder<Widget, MaterialButton> {
	}
	
	@UiField HTMLPanel panel;
	@UiField CustomButton button;
	@UiField CustomAnchor anchor;
	@UiField CustomIcon iconElem;
	@UiField Label label;
	
	private String text = "";
	private String type = "";
	private String icon = "";
	private String iconPosition = "";
	private String size = "";
	private String width = "";
	
	private boolean disable;

	public MaterialButton() {
		initWidget(uiBinder.createAndBindUi(this));
		initButtonStyles();
	}
	
	public MaterialButton(String text, String color, String waves) {
		initWidget(uiBinder.createAndBindUi(this));
		this.text = text;
		this.color = color;
		this.waves = waves;
		initButtonStyles();
	}
	
	
	public MaterialButton(String icon, String color, String type, String waves, String tooltip) {
		initWidget(uiBinder.createAndBindUi(this));
		this.icon = icon;
		this.color = color;
		this.type = type;
		this.waves = waves;
		this.tooltip = tooltip;
		initButtonStyles();
	}


	public MaterialButton(String text, String type, String icon, String iconPosition, String size, String tooltip) {
		initWidget(uiBinder.createAndBindUi(this));
		this.text = text;
		this.type = type;
		this.icon = icon;
		this.iconPosition = iconPosition;
		this.size = size;
		this.tooltip = tooltip;
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		initButtonStyles();
	}

	private void initButtonStyles(){
		switch (type) {
		case "flat":
			changeType(anchor);
			break;
		default:
			changeType(button);
			button.addStyleName("btn");
			break;
		}
		super.applyMaterialEffect();
	}
	
	private void changeType(ComplexPanel w){
		super.setWidget(w);
	
		panel.clear();
		w.add(iconElem);
		w.removeFromParent();
		
		if(isDisable()){
			waves = "";
			color = "";
			w.addStyleName("disabled");
		}
		
		if(!type.isEmpty()) w.addStyleName("btn-" + type);
		
		
		if(!text.isEmpty()){
			label.setText(text);
			w.add(label);
		}else label.removeFromParent();
		
		if(!icon.isEmpty()) iconElem.addStyleName(icon);
		else iconElem.removeFromParent();
		
		if(!iconPosition.isEmpty()) iconElem.addStyleName(iconPosition);
		
		if(!size.isEmpty()) button.addStyleName("btn-" + size);
		
		if(!width.isEmpty()) {
			this.getElement().setAttribute("style", "width: " + width+ ";");
			w.getElement().setAttribute("style", "width: " + width+ ";");
		}
		
		
		panel.add(w);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		label.setText(text);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconPosition() {
		return iconPosition;
	}

	public void setIconPosition(String iconPosition) {
		this.iconPosition = iconPosition;
		
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}


	public String getWidth() {
		return width;
	}


	public void setWidth(String width) {
		this.width = width;
	}


	public CustomButton getButton() {
		return button;
	}


	public void setButton(CustomButton button) {
		this.button = button;
	}

	
	
}
