package e4rcp.parts

import javax.annotation.PostConstruct
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridLayout
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.swt.layout.GridData
import org.eclipse.e4.ui.di.Focus

class ScalaPart {
  var tableViewer: TableViewer = _

  @PostConstruct
  def createPartControl(parent: Composite) = {
    parent.setLayout(new GridLayout)

    val label = new Label(parent, SWT.NONE)
    label.setText("Scala Sample")

    tableViewer = new TableViewer(parent)
    tableViewer.add("Scala item 1")
    tableViewer.add("Scala item 2")
    tableViewer.add("Scala item 3")
    tableViewer.add("Scala item 4")
    tableViewer.add("Scala item 5")
    tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH))
  }
  
  @Focus
  def setFocus() = {
    tableViewer.getTable.setFocus()
  }
}