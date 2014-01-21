package e4rcp.parts

import org.eclipse.jface.wizard.WizardPage
import org.eclipse.jface.wizard.IWizardPage
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.swt.widgets.Composite
import org.eclipse.jface.wizard.Wizard
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Text
import org.eclipse.jface.layout.GridLayoutFactory

class DemoWizard extends Wizard {

  val page1 = new WizardPage1("page1")
  val page2 = new WizardPage1("page2")

  override def addPages() {
    page1.getControl()
    addPage(page1)
    addPage(page2)
  }

  //override def getNextPage(page: IWizardPage) = ???

  override def performFinish = true

}

class WizardPage1(pageName: String)
    extends WizardPage(pageName) {

  setTitle(pageName)
  setDescription(pageName + " description")

  override def createControl(parent: Composite) {
    // 必須跟其他page不同composite
    val panel = new Composite(parent, SWT.NONE)
    setControl(panel)
    GridLayoutFactory.fillDefaults().applyTo(panel)

    val labelName = new Label(panel, SWT.NONE)
    labelName.setText("Name" + pageName)

    val txtName = new Text(panel, SWT.SINGLE | SWT.BORDER)
    txtName.setText("your name")
  }
}
