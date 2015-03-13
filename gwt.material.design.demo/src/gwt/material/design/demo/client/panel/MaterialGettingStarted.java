package gwt.material.design.demo.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialGettingStarted extends Composite{

	private static MaterialGettingStartedUiBinder uiBinder = GWT
			.create(MaterialGettingStartedUiBinder.class);

	interface MaterialGettingStartedUiBinder extends
			UiBinder<Widget, MaterialGettingStarted> {
	}

	public MaterialGettingStarted() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("btnDownloadAsset")
	void onDownloadAsset(ClickEvent e){
		Window.open("http://materializecss.com/bin/materialize-v0.95.3.zip", "_blank", "");
	}

	@UiHandler("btnDownloadJar")
	void onDownloadJar(ClickEvent e){
		Window.open("http://gwt-material.appspot.com/bin/gwt.material.design.15.3.jar", "_blank", "");
	}
	
}	
