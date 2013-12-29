package e4rcp.compatibility

import org.eclipse.equinox.app.IApplication
import org.eclipse.equinox.app.IApplicationContext
import org.eclipse.ui.PlatformUI


class Application extends IApplication {
  override def start(context: IApplicationContext) = {
    val display = PlatformUI.createDisplay()
    try {
      val retCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor)
      if (retCode == PlatformUI.RETURN_RESTART) IApplication.EXIT_RESTART
      else IApplication.EXIT_OK
    }
    finally {
      display.dispose()
    }
  }
  
  override def stop() = {
    if (PlatformUI.isWorkbenchRunning) {
      val workbench = PlatformUI.getWorkbench
      val display = workbench.getDisplay
      display.syncExec(new Runnable {
        def run = {
          if (!display.isDisposed) workbench.close()
        }
      })
    }
  }
}