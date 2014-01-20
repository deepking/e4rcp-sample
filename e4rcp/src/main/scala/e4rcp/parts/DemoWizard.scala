package e4rcp.parts

import org.eclipse.jface.wizard.WizardPage
import org.eclipse.jface.wizard.IWizardPage
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.swt.widgets.Composite
import org.eclipse.jface.wizard.Wizard
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Text

class DemoWizard(parent: Composite) extends Wizard {
  
  val page1 = new WizardPage1(parent, "page1")
  val page2 = new WizardPage1(parent, "page2")
  
  override def addPages() {
    page1.getControl()
    addPage(page1)
    addPage(page2)
  }
  
  //override def getNextPage(page: IWizardPage) = ???
  
  override def performFinish = true
 
}

class WizardPage1(parent: Composite, pageName: String) 
		extends WizardPage(pageName) {
  
  setControl(parent)
  setTitle(pageName)
  setDescription(pageName)
    
  override def createControl(parent: Composite) {
    val labelName = new Label(parent, SWT.NONE)
    labelName.setText("Name")
    
    val txtName = new Text(parent, SWT.NONE)
    
  }
}
