package gwt.material.design.client.ui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MaterialPanel extends HTMLPanel{

	private String color = "";
	private String align = "";
	private String textColor = "";
	private String shadow = "";
	
	public MaterialPanel(SafeHtml safeHtml) {
		super(safeHtml);
		// TODO Auto-generated constructor stub
	}

	public MaterialPanel(String tag, String html) {
		super(tag, html);
		// TODO Auto-generated constructor stub
	}

	public MaterialPanel(String html) {
		super(html);
		// TODO Auto-generated constructor stub
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		this.addStyleName(color);
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
		if(align.equals("valign-wrapper")){
			this.addStyleName(align);
		}else{
			this.addStyleName(align + "-align");
		}
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		this.addStyleName(textColor + "-text");
	}

	public String getShadow() {
		return shadow;
	}

	public void setShadow(String shadow) {
		this.shadow = shadow;
		this.addStyleName("z-depth-" + shadow);
	}

	
	
}
