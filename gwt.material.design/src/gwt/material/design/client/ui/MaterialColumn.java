package gwt.material.design.client.ui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Widget;

public class MaterialColumn extends MaterialPanel {

	private String grid = "";

	public MaterialColumn() {
		super("");
	}

	
	
	@Override
	public void add(Widget widget) {
		// TODO Auto-generated method stub
		super.add(widget);
	}



	public MaterialColumn(SafeHtml safeHtml) {
		super(safeHtml);
		// TODO Auto-generated constructor stub
	}

	public MaterialColumn(String tag, String html) {
		super(tag, html);
		// TODO Auto-generated constructor stub
	}

	public MaterialColumn(String html) {
		super(html);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName("col");
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
		this.addStyleName(grid);
	}

}
