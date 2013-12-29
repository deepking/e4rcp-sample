package e4rcp.compatibility

import org.eclipse.ui.plugin.AbstractUIPlugin
import org.osgi.framework.BundleContext

object Activator {
  import AbstractUIPlugin._
  
  var plugin: Activator = _
  var context: BundleContext = _
  val pluginId = "e4rcp"
  val title = "e4rcp"
  val perspectiveId = "e4rcp.perspective"
  
  def getImageDescriptor(path: String) = {
    imageDescriptorFromPlugin(pluginId, path)
  }
}

class Activator extends AbstractUIPlugin {
  override def start(context: BundleContext) = {
    super.start(context)
    Activator.plugin = this
    Activator.context = context
  }
  
  override def stop(context: BundleContext) = {
    super.stop(context)
  }
}