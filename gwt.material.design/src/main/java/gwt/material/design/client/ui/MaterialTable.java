package gwt.material.design.client.ui;

import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;

public class MaterialTable<T> extends DataGrid<T>{


	public MaterialTable() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	public MaterialTable(int pageSize, ProvidesKey<T> keyProvider) {
		super(pageSize, keyProvider);
		// TODO Auto-generated constructor stub
	}

	public MaterialTable(int pageSize, Resources resources, ProvidesKey<T> keyProvider, Widget loadingIndicator) {
		super(pageSize, resources, keyProvider, loadingIndicator);
		// TODO Auto-generated constructor stub
	}

	public MaterialTable(int pageSize, Resources resources, ProvidesKey<T> keyProvider) {
		super(pageSize, resources, keyProvider);
		// TODO Auto-generated constructor stub
	}

	public MaterialTable(int pageSize, Resources resources) {
		super(pageSize, resources);
		// TODO Auto-generated constructor stub
	}

	public MaterialTable(int pageSize) {
		super(pageSize);
		// TODO Auto-generated constructor stub
	}

	public MaterialTable(ProvidesKey<T> keyProvider) {
		super(keyProvider);
		// TODO Auto-generated constructor stub
	}
	
	

}
