package org.eclipse.swt.chromium;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	@Override
	public void start(BundleContext context) throws Exception {
		Activator.context = context;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		Activator.context = null;
	}

	public static File getBundleLocation(IPath path) throws URISyntaxException, IOException {
		URL url = FileLocator.find(context.getBundle(), path);
		return new File(FileLocator.toFileURL(url).toURI());
	}

	public static void log(String message, Throwable e) {
		Platform.getLog(context.getBundle())
				.log(new Status(IStatus.ERROR, context.getBundle().getSymbolicName(), message, e));
	}
}
