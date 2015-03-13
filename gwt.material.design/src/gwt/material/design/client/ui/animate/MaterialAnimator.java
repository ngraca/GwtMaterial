package gwt.material.design.client.ui.animate;

import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

public class MaterialAnimator {

	
	public static void animate(final Transition transition,final Widget w, int delayMillis){
		switch (transition) {
		case SCALE:
			w.addStyleName(MaterialResources.INSTANCE.materialcss().materialScaleInitial());
			break;
		case PULL:
			w.addStyleName(MaterialResources.INSTANCE.materialcss().pullInitial());
			break;
		default:
			break;
		}
		
		Timer t = new Timer() {
			
			@Override
			public void run() {
				applyAnimation(transition, w);
			}

			private void applyAnimation(Transition transition, Widget w) {
				switch (transition) {
				case SCALE:
					w.addStyleName(MaterialResources.INSTANCE.materialcss().materialScale());
					break;
				case PULL:
					w.addStyleName(MaterialResources.INSTANCE.materialcss().pull());
					break;
				default:
					break;
				}
			}
		};
		t.schedule(delayMillis);
		w.removeStyleName(MaterialResources.INSTANCE.materialcss().pull());
		w.removeStyleName(MaterialResources.INSTANCE.materialcss().materialScale());
	}
	
}
