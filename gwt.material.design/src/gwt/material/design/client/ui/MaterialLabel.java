package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.user.client.ui.Label;

public class MaterialLabel extends Label {
	
	String fontSize = "";

	public MaterialLabel() {
		// TODO Auto-generated constructor stub
	}

	public MaterialLabel(Element element) {
		super(element);
		// TODO Auto-generated constructor stub
	}

	public MaterialLabel(String text, boolean wordWrap) {
		super(text, wordWrap);
		// TODO Auto-generated constructor stub
	}

	public MaterialLabel(String text, Direction dir) {
		super(text, dir);
		// TODO Auto-generated constructor stub
	}

	public MaterialLabel(String text, DirectionEstimator directionEstimator) {
		super(text, directionEstimator);
		// TODO Auto-generated constructor stub
	}

	public MaterialLabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		this.getElement().getStyle().setFontSize(Double.valueOf(fontSize), Unit.EM);
	}

	
}
