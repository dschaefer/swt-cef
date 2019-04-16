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
import org.eclipse.swt.widgets.Composite;

public class Chromium extends Composite {

	static {
		try {
			File cefLibDir = Activator.getBundleLocation(new Path("cef/lib/libswt-cef.so"));
			System.load(cefLibDir.getAbsolutePath());
		} catch (URISyntaxException | IOException e) {
			Activator.log("Error loading cef library", e);
		}
	}

	public Chromium(Composite parent, int style) {
		super(parent, style | SWT.EMBEDDED);
		getDisplay().timerExec(1000, () -> {
			start(embeddedHandle);
			Runnable loop = new Runnable() {
				@Override
				public void run() {
					work();
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
