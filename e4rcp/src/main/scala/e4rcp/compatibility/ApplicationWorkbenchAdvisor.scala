package e4rcp.compatibility

import org.eclipse.core.runtime.Platform
import org.eclipse.jface.action.IMenuManager
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.ui.IWorkbenchWindow
import org.eclipse.ui.application.ActionBarAdvisor
import org.eclipse.ui.application.IActionBarConfigurer
import org.eclipse.ui.application.IWorkbenchConfigurer
import org.eclipse.ui.application.IWorkbenchWindowConfigurer
import org.eclipse.ui.application.WorkbenchAdvisor
import org.eclipse.ui.application.WorkbenchWindowAdvisor
import org.eclipse.ui.ide.IDE
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin
import org.osgi.framework.Bundle


class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {
  import org.eclipse.ui.ide.IDE
  override def createWorkbenchWindowAdvisor(configurer: IWorkbenchWindowConfigurer) = {
    new ApplicationWorkbenchWindowAdvisor(configurer)
  }
  
  override def getInitialWindowPerspectiveId() = Activator.perspectiveId
  
  override def initialize(configurer: IWorkbenchConfigurer) = {
    super.initialize(configurer)
    // inserted: register workbench adapters
    IDE.registerAdapters()
    
    // inserted: register images for rendering explorer view
    val ICONS_PATH = "icons/full/"
    val PATH_OBJECT = ICONS_PATH + "obj16/"
    val ideBundle = Platform.getBundle(IDEWorkbenchPlugin.IDE_WORKBENCH)
    declareWorkbenchImage(configurer, ideBundle, IDE.SharedImages.IMG_OBJ_PROJECT, PATH_OBJECT + "prj_obj.gif", true)
    declareWorkbenchImage(configurer, ideBundle, IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED, PATH_OBJECT + "cprj_obj.gif", true)
  }
  
  private def declareWorkbenchImage(config: IWorkbenchConfigurer, ideBundle: Bundle, symbolicName: String, path: String, shared: Boolean) = {
    val url = ideBundle.getEntry(path)
    val desc = ImageDescriptor.createFromURL(url)
    config.declareImage(symbolicName, desc, shared)
  }
}

class ApplicationWorkbenchWindowAdvisor(config: IWorkbenchWindowConfigurer) extends WorkbenchWindowAdvisor(config) {
  override def createActionBarAdvisor(configurer: IActionBarConfigurer) = {
    new ApplicationActionBarAdvisor(configurer)
  }
  
  override def preWindowOpen() = {
    val config = getWindowConfigurer()
    config.setShowPerspectiveBar(true)
  }
}

class ApplicationActionBarAdvisor(config: IActionBarConfigurer) extends ActionBarAdvisor(config) {
  override def makeActions(window: IWorkbenchWindow) = {
  }
  
  override def fillMenuBar(menuBar: IMenuManager) = {
  }
}