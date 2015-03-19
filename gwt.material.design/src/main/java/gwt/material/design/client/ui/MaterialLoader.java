package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class MaterialLoader {
	private static PreLoader preLoader = new PreLoader();
	private static HTMLPanel indeterminateProgress = new HTMLPanel("<div id='lean-overlay' style='display: block; opacity: 0.8; background: #fff;'></div><div class='progress' style='z-index: 1000; position: fixed !important;' ><div class='indeterminate' '></div></div>"); 

	public static void showLoading(boolean isShow) {
		if (isShow) {
			RootPanel.get().add(preLoader);
		} else {
			preLoader.removeFromParent();
		}

	}
	

	public static void showProgress(boolean isShow){
		if(isShow){
			indeterminateProgress.getElement().getStyle().setPosition(Position.ABSOLUTE);
			indeterminateProgress.getElement().getStyle().setTop(0, Unit.PCT);
			indeterminateProgress.getElement().getStyle().setWidth(100, Unit.PCT);
			indeterminateProgress.getElement().getStyle().setLeft(0, Unit.PCT);
			indeterminateProgress.getElement().getStyle().setZIndex(1000);
			RootPanel.get().add(indeterminateProgress);
		}else{
			indeterminateProgress.removeFromParent();
		}
	}	
}
