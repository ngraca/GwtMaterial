package gwt.material.design.client;

import gwt.material.design.client.resources.MaterialResources;
import gwt.material.design.client.resources.ResourcesLoader;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_material_design implements EntryPoint {
	
	public void onModuleLoad() {
		new ResourcesLoader(MaterialResources.INSTANCE);
	}
}
