package e4rcp.parts

import javax.annotation.PostConstruct
import org.eclipse.swt.widgets.Composite
import org.eclipse.jface.viewers.TreeViewer
import org.eclipse.swt.SWT
import org.eclipse.jface.viewers.ITreeContentProvider
import java.io.File
import org.eclipse.jface.viewers.Viewer
import org.eclipse.jface.viewers.LabelProvider
import org.eclipse.swt.graphics.Image
import org.eclipse.e4.ui.di.Focus
import javax.annotation.PreDestroy
import org.osgi.framework.FrameworkUtil
import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.Path
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.ISharedImages
import org.eclipse.jface.viewers.StyledCellLabelProvider
import org.eclipse.jface.viewers.ViewerCell
import org.eclipse.jface.viewers.StyledString

class FileExplorer {
  var tree: TreeViewer = _
  
  @PostConstruct
  def createPartControl(parent: Composite) {
    val folderImage = PlatformUI.getWorkbench.getSharedImages.getImage(ISharedImages.IMG_OBJ_FOLDER)
    
    tree = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL)
    tree.setContentProvider(new FileContentProvider)
    tree.setLabelProvider(new FileViewLabelProvider(folderImage))
    tree.setInput(File.listRoots)
  }

  @Focus
  def setFocus = tree.getControl.setFocus()

  @PreDestroy
  def destroy = Unit
}

class FileContentProvider extends ITreeContentProvider {
  override def inputChanged(viewer: Viewer, oldInput: Any, newInput: Any) = Unit
  
  override def getElements(inputElement: Any) = inputElement.asInstanceOf[Array[Object]]
  
  override def dispose() = Unit
  
  override def getChildren(parentElement: Any) = parentElement.asInstanceOf[File].listFiles.toArray[Object]
  
  override def getParent(element: Any) = element.asInstanceOf[File].getParentFile()
  
  override def hasChildren(element: Any) = element match {
    case file: File => file.isDirectory
    case _ => false
  }
}

class FileViewLabelProvider(image: Image) extends StyledCellLabelProvider {
  override def update(cell: ViewerCell) {
    cell.getElement match {
      case file: File =>
        val text = new StyledString
        if (file.isDirectory) {
          val image = PlatformUI.getWorkbench.getSharedImages.getImage(ISharedImages.IMG_OBJ_FOLDER)
          cell.setImage(image)
          val name = if (file.getName.isEmpty) file.getPath else file.getName
          text.append(name)
          val count = if (file.listFiles == null) 0 else file.listFiles.length
          text.append(" (" + count + ")", StyledString.COUNTER_STYLER)
        }
        else {
          val image = PlatformUI.getWorkbench.getSharedImages.getImage(ISharedImages.IMG_OBJ_FILE)
          cell.setImage(image)
          val name = if (file.getName.isEmpty) file.getPath else file.getName
          text.append(name)
        }
        
        cell.setText(text.toString)
        cell.setStyleRanges(text.getStyleRanges)
    }
  }
}