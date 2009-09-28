package openlayers_rap_advanced_example;

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
import org.polymap.rap.widget.openlayers.layers.WMSLayer;

/**
 *   The main view of this example
 *   
 *  @author Marcus -LiGi- B&uuml;schleb < mail:	ligi (at) polymap (dot) de >
 *
*/

public class View 
	extends ViewPart
	implements MouseListener 
{

	public static final String ID = "OpenLayers_RAP_Advanced_Example.view";

	private Shell add_wms_shell,add_control_shell,edit_center_shell;
	
	private Display display;
	private Button open_add_wms_shell_btn,open_set_center_btn,open_add_control_btn;
	private OpenLayers openlayers;
	private Text wms_add_layers,wms_add_url,wms_add_name,center_lon_field,center_lat_field,zoom_field,add_control_field;
	private Button add_wms_btn,add_control_btn,set_center_btn,load_wms_example ;
	
	private Font boldFont;
	
	public void createAddWMSShell()
	{
		add_wms_shell=new Shell(display);
		FillLayout toolbar_layout=new FillLayout();
		toolbar_layout.type=SWT.HORIZONTAL;
		add_wms_shell.setLayout( toolbar_layout );
		add_wms_shell.setText( "Add WMS" );
		add_wms_shell.setSize(400,50);
	
		Label l=new Label(add_wms_shell,SWT.NONE);
		l.setFont(boldFont);
		l.setText("name:");
		//new Label(add_wms_shell,SWT.NONE).setText("name:");
		wms_add_name=new Text(add_wms_shell,SWT.NONE | SWT.BORDER);
		//new Label(add_wms_shell,SWT.NONE).setText("URL:");
		l=new Label(add_wms_shell,SWT.NONE);
		l.setFont(boldFont);
		l.setText("URL:");
		wms_add_url=new Text(add_wms_shell,SWT.NONE | SWT.BORDER);
		l=new Label(add_wms_shell,SWT.NONE);
		l.setFont(boldFont);
		l.setText("Layers:");

		//new Label(add_wms_shell,SWT.NONE).setText("Layers:");
		wms_add_layers=new Text(add_wms_shell,SWT.NONE | SWT.BORDER);
		
		load_wms_example=new Button(add_wms_shell,SWT.NONE | SWT.BORDER);
		load_wms_example.setText("Fill");
		load_wms_example.addMouseListener(this);	
		
		add_wms_btn=new Button(add_wms_shell,SWT.NONE | SWT.BORDER);
		add_wms_btn.setText("Add");
		add_wms_btn.addMouseListener(this);	
		
	}

	public void createEditCenterShell()
	{
	
		edit_center_shell=new Shell(display);
		FillLayout toolbar_layout=new FillLayout();
		toolbar_layout.type=SWT.HORIZONTAL;
		edit_center_shell.setLayout( toolbar_layout );
		edit_center_shell.setText( "Edit Center" );
		edit_center_shell.setSize(400,50);
	
		Label l=new Label(edit_center_shell,SWT.NONE);
		l.setFont(boldFont);
		l.setText("Lon:");
		//new Label(add_wms_shell,SWT.NONE).setText("name:");
		center_lon_field=new Text(edit_center_shell,SWT.NONE | SWT.BORDER);
		//new Label(add_wms_shell,SWT.NONE).setText("URL:");
		l=new Label(edit_center_shell,SWT.NONE);
		l.setFont(boldFont);
		l.setText("Lat:");
		center_lat_field=new Text(edit_center_shell,SWT.NONE | SWT.BORDER);
		l=new Label(edit_center_shell,SWT.NONE);
		l.setFont(boldFont);
		l.setText("Zoom:");

		//new Label(add_wms_shell,SWT.NONE).setText("Layers:");
		zoom_field=new Text(edit_center_shell,SWT.NONE | SWT.BORDER);
		
		set_center_btn=new Button(edit_center_shell,SWT.NONE | SWT.BORDER);
		set_center_btn.setText("OK");
		set_center_btn.addMouseListener(this);	
		
	}

	public void createAddControlShell()
	{
		add_control_shell=new Shell(display);
		FillLayout toolbar_layout=new FillLayout();
		toolbar_layout.type=SWT.VERTICAL;
		add_control_shell.setLayout( toolbar_layout );
		add_control_shell.setText( "Add Control" );
		add_control_shell.setSize(400,70);
	
		add_control_field=new Text(add_control_shell,SWT.NONE | SWT.BORDER);
		
		add_control_btn=new Button(add_control_shell,SWT.NONE | SWT.BORDER);
		add_control_btn.setText("OK");
		add_control_btn.addMouseListener(this);	
	}

	public void createPartControl(Composite parent) {
		display = parent.getDisplay();
		
		// setup bold font
		boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    
		
		createAddWMSShell();
		createEditCenterShell();
		createAddControlShell();
		
		Composite top = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		top.setLayout(layout);
		// top banner
		Composite banner = new Composite(top, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 3;
		banner.setLayout(layout);
		
		
		open_add_wms_shell_btn= new Button(banner,SWT.PUSH);
		open_add_wms_shell_btn.setText("add WMS");
		
		open_add_wms_shell_btn.addMouseListener(this);
		
		
		open_set_center_btn= new Button(banner,SWT.PUSH);
		open_set_center_btn.setText("set Center");
		open_set_center_btn.addMouseListener(this);
		
		/*open_add_control_btn= new Button(banner,SWT.PUSH);
		open_add_control_btn.setText("add Control");
		open_add_control_btn.addMouseListener(this);
		*/
		openlayers=new OpenLayers(top,SWT.MULTI | SWT.WRAP);
		openlayers.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	public void setFocus() {
	}
	

	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}
	
	@Override
	public void mouseDown(MouseEvent e) {
		Object src=e.getSource();
		if ( src==open_add_wms_shell_btn)
		{
			add_wms_shell.open();
			add_wms_shell.setFocus();
		}
		else if (src==open_set_center_btn)
		{
			edit_center_shell.open();
			edit_center_shell.setFocus();
		}
		else if (src==open_add_control_btn)
		{
			add_control_shell.open();
			add_control_shell.setFocus();
		}
		else if (src== add_wms_btn)
		{
			WMSLayer wms_layer=new WMSLayer(openlayers, wms_add_name.getText(), wms_add_url.getText(), wms_add_layers.getText());
			openlayers.addLayer(wms_layer);
		}
		else if (src== load_wms_example)
		{
			wms_add_name.setText("polymap");
			wms_add_url.setText("http://www.polymap.de/geoserver/wms?");
			wms_add_layers.setText("states");
		}
		else if ( src==set_center_btn)
		{
			openlayers.setCenter(new Double(center_lon_field.getText()), new Double(center_lat_field.getText()));
			openlayers.zoomTo(Integer.parseInt(zoom_field.getText()));
		}
		/*else if ( src == add_control_btn)
			openlayers.addControl(add_control_field.getText());
			*/
	}

	@Override
	public void mouseUp(MouseEvent e) {
	}
}
