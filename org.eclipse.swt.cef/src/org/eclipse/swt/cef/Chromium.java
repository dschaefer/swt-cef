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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class Chromium extends Composite {

	static {
		try {
			File cefLibDir = Activator.getBundleLocation(new Path("cef/lib/libswt-cef.so"));
			System.load(cefLibDir.getAbsolutePath());
			Display.getDefault().asyncExec(() -> {
				start();
				Runnable loop = new Runnable() {
					@Override
					public void run() {
						work();
						Display.getDefault().timerExec(20, this);
					}
				};
				Display.getDefault().timerExec(20, loop);
			});
		} catch (URISyntaxException | IOException e) {
			Activator.log("Error loading cef library", e);
		}
	}

	public Chromium(Composite parent, int style) {
		super(parent, style);
	}

	native static int start();

	native static int work();

	public void setUrl(String url) {
	}

}
