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
 * $Id: $
 *
 * @author 	Marcus -LiGi- Bueschleb
 * 	mail to 		ligi (at) polymap (dot) de
 *                  
 * @version $Revision: $
 *
 * Intension of this class:
 *  The main view of this example
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

public class View 
	extends ViewPart
	implements MouseListener 
{

	public static final String ID = "OpenLayers_RAP_Advanced_Example.view";

	private Shell add_wms_shell;
	private Display display;
	private Button toolbar_btn;
	private OpenLayers openlayers;
	private Text wms_add_name;
	private Text wms_add_url;
	private Text wms_add_layers;
	private Button add_wms_btn;
	private Button load_wms_example;
	
	public void createAddWMSShell()
	{
		// setup bold font
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    
	
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

	public void createPartControl(Composite parent) {
		display = parent.getDisplay();
		createAddWMSShell();
		
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
		
		
		toolbar_btn= new Button(banner,SWT.PUSH);
		toolbar_btn.setText("add WMS");
		
		toolbar_btn.addMouseListener(this);
		
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
		if ( src==toolbar_btn)
		{
			add_wms_shell.open();
			add_wms_shell.setFocus();
		}
		else if (src== add_wms_btn)
		{
			openlayers.addWMS(wms_add_name.getText(), wms_add_name.getText(), wms_add_url.getText(), wms_add_layers.getText());
		}
		else if (src== load_wms_example)
		{
			wms_add_name.setText("polymap");
			wms_add_url.setText("http://www.polymap.de/geoserver/wms?");
			wms_add_layers.setText("states");
		}
	}

	@Override
	public void mouseUp(MouseEvent e) {
	}
}
