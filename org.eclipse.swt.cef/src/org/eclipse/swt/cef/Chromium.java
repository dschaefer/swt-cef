/*********************************************************************
 * Copyright (c) 2019 QNX Software Systems and others
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *********************************************************************/
package org.eclipse.swt.cef;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.DPIUtil;
import org.eclipse.swt.internal.gtk.GDK;
import org.eclipse.swt.internal.gtk.GTK;
import org.eclipse.swt.internal.gtk.GdkWindowAttr;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;

public class Chromium extends Canvas {

	static {
		try {
			File cefLibDir = Activator.getBundleLocation(new Path("cef/lib/libswt-cef.so"));
			System.load(cefLibDir.getAbsolutePath());
		} catch (URISyntaxException | IOException e) {
			Activator.log("Error loading cef library", e);
		}
	}

	private long chromiumWindow;
	private long xWindow;

	public Chromium(Composite parent, int style) {
		super(parent, style);

		GTK.gtk_widget_realize(handle);
		long window = GTK.gtk_widget_get_window(handle);

		GdkWindowAttr attrs = new GdkWindowAttr();
		attrs.width = 1;
		attrs.height = 1;
		attrs.event_mask = GDK.GDK_KEY_PRESS_MASK | GDK.GDK_KEY_RELEASE_MASK | GDK.GDK_FOCUS_CHANGE_MASK
				| GDK.GDK_POINTER_MOTION_MASK | GDK.GDK_BUTTON_PRESS_MASK | GDK.GDK_BUTTON_RELEASE_MASK
				| GDK.GDK_ENTER_NOTIFY_MASK | GDK.GDK_LEAVE_NOTIFY_MASK | GDK.GDK_EXPOSURE_MASK
				| GDK.GDK_POINTER_MOTION_HINT_MASK;
		attrs.window_type = GDK.GDK_WINDOW_CHILD;

		chromiumWindow = GDK.gdk_window_new(window, attrs, 0);
		xWindow = GDK.gdk_x11_window_get_xid(chromiumWindow);
		GDK.gdk_window_show(chromiumWindow);

		Listener listener = event -> {
			switch (event.type) {
			case SWT.Resize:
				Rectangle clientArea = DPIUtil.autoScaleUp(getClientArea());
				GDK.gdk_window_move(chromiumWindow, clientArea.x, clientArea.y);
				GDK.gdk_window_resize(chromiumWindow, clientArea.width, clientArea.height);
				break;
			case SWT.Dispose:
				if (chromiumWindow != 0) {
					GDK.gdk_window_destroy(chromiumWindow);
					chromiumWindow = 0;
				}
				break;
			}
		};
		addListener(SWT.Resize, listener);
		addListener(SWT.Dispose, listener);

		getDisplay().timerExec(1000, () -> {
			start(xWindow);
			Runnable loop = new Runnable() {
				@Override
				public void run() {
					System.out.println("working");
					work();
					System.out.println("  done");
					getDisplay().timerExec(20, this);
				}
			};
			getDisplay().timerExec(20, loop);
		});
	}

	native static int start(long parentId);

	native static int work();

	public void setUrl(String url) {
	}

}
