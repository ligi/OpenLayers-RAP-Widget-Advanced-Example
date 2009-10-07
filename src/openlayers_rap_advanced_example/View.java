/*
 * polymap.org
 * Copyright 2009, Polymap GmbH, and individual contributors as indicated
 * by the @authors tag.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */

package openlayers_rap_advanced_example;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.polymap.rap.widget.openlayers.OpenLayers;
import org.polymap.rap.widget.openlayers.base_types.Bounds;
import org.polymap.rap.widget.openlayers.controls.KeyboardDefaultsControl;
import org.polymap.rap.widget.openlayers.controls.LayerSwitcherControl;
import org.polymap.rap.widget.openlayers.controls.ModifyFeatureControl;
import org.polymap.rap.widget.openlayers.controls.MouseDefaultsControl;
import org.polymap.rap.widget.openlayers.controls.PanZoomBarControl;
import org.polymap.rap.widget.openlayers.controls.SelectFeatureControl;
import org.polymap.rap.widget.openlayers.features.VectorFeature;
import org.polymap.rap.widget.openlayers.layers.VectorLayer;
import org.polymap.rap.widget.openlayers.layers.WMSLayer;

/**
 * The main view of this example
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class View extends ViewPart implements MouseListener {

	public static final String ID = "OpenLayers_RAP_Advanced_Example.view";

	private Shell add_wms_shell, add_control_shell, edit_center_shell,
			create_boxes_shell;

	private Display display;
	private Button open_add_wms_shell_btn, open_set_center_btn,
			open_add_control_btn, open_create_boxes_btn;
	private OpenLayers openlayers;
	private Text wms_add_layers, wms_add_url, wms_add_name, center_lon_field,
			center_lat_field, zoom_field, add_control_field;
	private Text boxes_name_field, boxes_x_count_field, boxes_y_count_field;
	private Button add_wms_btn, add_control_btn, set_center_btn,
			load_wms_example, create_boxes_btn;

	private Boolean center_set = false;

	private Font boldFont;

	public void showAddWMSShell() {

		if (add_wms_shell != null)
			add_wms_shell.close();

		add_wms_shell = new Shell(display);

		FillLayout toolbar_layout = new FillLayout();
		toolbar_layout.type = SWT.HORIZONTAL;
		add_wms_shell.setLayout(toolbar_layout);
		add_wms_shell.setText("Add WMS");
		add_wms_shell.setSize(400, 50);

		Label l = new Label(add_wms_shell, SWT.NONE);
		l.setFont(boldFont);
		l.setText("name:");
		// new Label(add_wms_shell,SWT.NONE).setText("name:");
		wms_add_name = new Text(add_wms_shell, SWT.NONE | SWT.BORDER);
		// new Label(add_wms_shell,SWT.NONE).setText("URL:");
		l = new Label(add_wms_shell, SWT.NONE);
		l.setFont(boldFont);
		l.setText("URL:");
		wms_add_url = new Text(add_wms_shell, SWT.NONE | SWT.BORDER);
		l = new Label(add_wms_shell, SWT.NONE);
		l.setFont(boldFont);
		l.setText("Layers:");

		// new Label(add_wms_shell,SWT.NONE).setText("Layers:");
		wms_add_layers = new Text(add_wms_shell, SWT.NONE | SWT.BORDER);

		load_wms_example = new Button(add_wms_shell, SWT.NONE | SWT.BORDER);
		load_wms_example.setText("Fill");
		load_wms_example.addMouseListener(this);

		add_wms_btn = new Button(add_wms_shell, SWT.NONE | SWT.BORDER);
		add_wms_btn.setText("Add");
		add_wms_btn.addMouseListener(this);

		add_wms_shell.open();
		add_wms_shell.setFocus();
	}

	public void showEditCenterShell() {

		if (edit_center_shell != null)
			edit_center_shell.close();

		edit_center_shell = new Shell(display);
		FillLayout toolbar_layout = new FillLayout();
		toolbar_layout.type = SWT.HORIZONTAL;
		edit_center_shell.setLayout(toolbar_layout);
		edit_center_shell.setText("Edit Center");
		edit_center_shell.setSize(400, 50);

		Label l = new Label(edit_center_shell, SWT.NONE);
		l.setFont(boldFont);
		l.setText("Lon:");
		// new Label(add_wms_shell,SWT.NONE).setText("name:");
		center_lon_field = new Text(edit_center_shell, SWT.NONE | SWT.BORDER);
		// new Label(add_wms_shell,SWT.NONE).setText("URL:");
		l = new Label(edit_center_shell, SWT.NONE);
		l.setFont(boldFont);
		l.setText("Lat:");
		center_lat_field = new Text(edit_center_shell, SWT.NONE | SWT.BORDER);
		l = new Label(edit_center_shell, SWT.NONE);
		l.setFont(boldFont);
		l.setText("Zoom:");

		// new Label(add_wms_shell,SWT.NONE).setText("Layers:");
		zoom_field = new Text(edit_center_shell, SWT.NONE | SWT.BORDER);

		set_center_btn = new Button(edit_center_shell, SWT.NONE | SWT.BORDER);
		set_center_btn.setText("OK");
		set_center_btn.addMouseListener(this);

		edit_center_shell.open();
		edit_center_shell.setFocus();
	}

	public void showCreateBoxesShell() {

		if (create_boxes_shell != null)
			create_boxes_shell.close();

		create_boxes_shell = new Shell(display);
		FillLayout toolbar_layout = new FillLayout();
		toolbar_layout.type = SWT.HORIZONTAL;

		create_boxes_shell.setLayout(toolbar_layout);
		create_boxes_shell.setText("Create Boxes");
		create_boxes_shell.setSize(400, 50);

		Label l = new Label(create_boxes_shell, SWT.NONE);
		l.setFont(boldFont);
		l.setText("Name:");

		boxes_name_field = new Text(create_boxes_shell, SWT.NONE | SWT.BORDER);
		// new Label(add_wms_shell,SWT.NONE).setText("URL:");
		l = new Label(create_boxes_shell, SWT.NONE);
		l.setFont(boldFont);
		l.setText("X-Count");
		boxes_x_count_field = new Text(create_boxes_shell, SWT.NONE
				| SWT.BORDER);
		l = new Label(create_boxes_shell, SWT.NONE);
		l.setFont(boldFont);
		l.setText("Y-Count:");

		// new Label(add_wms_shell,SWT.NONE).setText("Layers:");
		boxes_y_count_field = new Text(create_boxes_shell, SWT.NONE
				| SWT.BORDER);

		create_boxes_btn = new Button(create_boxes_shell, SWT.NONE | SWT.BORDER);
		create_boxes_btn.setText("OK");
		create_boxes_btn.addMouseListener(this);

		create_boxes_shell.open();
		create_boxes_shell.setFocus();
	}

	public void showAddControlShell() {

		if (add_control_shell != null)
			add_control_shell.close();

		add_control_shell = new Shell(display);
		FillLayout toolbar_layout = new FillLayout();
		toolbar_layout.type = SWT.VERTICAL;
		add_control_shell.setLayout(toolbar_layout);
		add_control_shell.setText("Add Control");
		add_control_shell.setSize(400, 70);

		add_control_field = new Text(add_control_shell, SWT.NONE | SWT.BORDER);

		add_control_btn = new Button(add_control_shell, SWT.NONE | SWT.BORDER);
		add_control_btn.setText("OK");
		add_control_btn.addMouseListener(this);

		add_control_shell.open();
		add_control_shell.setFocus();
	}

	public void createPartControl(Composite parent) {
		display = parent.getDisplay();

		// setup bold font
		boldFont = JFaceResources.getFontRegistry().getBold(
				JFaceResources.DEFAULT_FONT);

		Composite top = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		top.setLayout(layout);
		// top banner
		Composite banner = new Composite(top, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL,
				GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 3;
		banner.setLayout(layout);

		open_add_wms_shell_btn = new Button(banner, SWT.PUSH);
		open_add_wms_shell_btn.setText("add WMS");

		open_add_wms_shell_btn.addMouseListener(this);

		open_set_center_btn = new Button(banner, SWT.PUSH);
		open_set_center_btn.setText("set Center");
		open_set_center_btn.addMouseListener(this);

		open_create_boxes_btn = new Button(banner, SWT.PUSH);
		open_create_boxes_btn.setText("add Boxes");
		open_create_boxes_btn.addMouseListener(this);

		/*
		 * open_add_control_btn= new Button(banner,SWT.PUSH);
		 * open_add_control_btn.setText("add Control");
		 * open_add_control_btn.addMouseListener(this);
		 */
		openlayers = new OpenLayers(top, SWT.MULTI | SWT.WRAP);
		openlayers.setLayoutData(new GridData(GridData.FILL_BOTH));

		// add some controls
		openlayers.addControl(new LayerSwitcherControl());
		openlayers.addControl(new MouseDefaultsControl());
		openlayers.addControl(new KeyboardDefaultsControl());
		openlayers.addControl(new PanZoomBarControl());

	}

	public void setFocus() {
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
		Object src = e.getSource();
		if (src == open_add_wms_shell_btn)
			showAddWMSShell();
		else if (src == open_set_center_btn)
			showEditCenterShell();
		else if (src == open_add_control_btn)
			showAddWMSShell();
		else if (src == open_create_boxes_btn)
			showCreateBoxesShell();
		else if (src == add_wms_btn) {
			WMSLayer wms_layer = new WMSLayer(wms_add_name.getText(),
					wms_add_url.getText(), wms_add_layers.getText());
			openlayers.addLayer(wms_layer);

			if (!center_set) {
				openlayers.setCenter(0, 0);
				openlayers.zoomTo(1);
			}

		} else if (src == load_wms_example) {
			wms_add_name.setText("polymap");
			wms_add_url.setText("http://www.polymap.de/geoserver/wms?");
			wms_add_layers.setText("states");
		} else if (src == set_center_btn) {
			openlayers.setCenter(new Double(center_lon_field.getText()),
					new Double(center_lat_field.getText()));
			openlayers.zoomTo(Integer.parseInt(zoom_field.getText()));
			center_set = true;
		} else if (src == create_boxes_btn) {
			VectorLayer multibox_layer = new VectorLayer(boxes_name_field
					.getText());

			int x_size = Integer.parseInt(boxes_x_count_field.getText());
			int y_size = Integer.parseInt(boxes_y_count_field.getText());

			for (int x = -(x_size / 2); x < (-(x_size / 2) + x_size); x++)
				for (int y = -(y_size / 2); y < (-(y_size / 2) + y_size); y++)
					multibox_layer.addFeatures(new VectorFeature(new Bounds(
							2.0 * x, 2.0 * y, 2.0 * x + 1.8, 2.0 * y + 1.8).toGeometry()));

			multibox_layer.setIsBaseLayer(true);
			openlayers.addLayer(multibox_layer);
			openlayers.setBaseLayer(multibox_layer);

			// setting up the Modify Feature Control
			ModifyFeatureControl mfc = new ModifyFeatureControl(multibox_layer);

			openlayers.addControl(mfc);
			mfc.activate();

			if (!center_set) {
				openlayers.setCenter(0, 0);
				openlayers.zoomTo(1);
			}

		}

		/*
		 * else if ( src == add_control_btn)
		 * openlayers.addControl(add_control_field.getText());
		 */
	}

	@Override
	public void mouseUp(MouseEvent e) {
	}
}
