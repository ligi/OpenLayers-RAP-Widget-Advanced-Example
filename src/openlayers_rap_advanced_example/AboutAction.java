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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * About Dialog Action
 * 
 *  @author Marcus -LiGi- B&uuml;schleb < mail:	ligi (at) polymap (dot) de >
 *
*/

public class AboutAction extends Action {
	
	private final IWorkbenchWindow window;
	
	public AboutAction(IWorkbenchWindow window) {
		super("About");
		setId(this.getClass().getName());
		this.window = window;
	}
	
	public void run() {
		if(window != null) {	
			String title = "About OpenLayers RAP Widget Advanced Example";
			String msg =    "Example on how to use the OpenLayers RAP Widget\n\n"
									+ "Created 2009 by Marcus -LiGi- Bueschleb\n"
									+ "for Polymap http://www.polymap.org\n"
									+ "for Questions mail to ligi" + "@" + "polymap.de\n";
			MessageDialog.openInformation( window.getShell(), title, msg ); 
		}
	}
	
}
	