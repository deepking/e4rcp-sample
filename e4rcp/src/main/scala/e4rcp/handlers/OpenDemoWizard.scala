package e4rcp.handlers

import org.eclipse.e4.core.di.annotations.Execute
import org.eclipse.swt.widgets.Shell
import javax.inject.Named
import org.eclipse.e4.ui.services.IServiceConstants
import org.eclipse.e4.core.contexts.Active
import org.eclipse.jface.wizard.WizardDialog
import e4rcp.parts.DemoWizard
import e4rcp.parts.DemoWizard

class OpenDemoWizard {

  @Execute
  //def execute(@Named(IServiceConstants.ACTIVE_SHELL) shell: Shell) {
  def execute(@Active shell: Shell) {
    val dialog = new WizardDialog(shell, new DemoWizard(shell))
    dialog.open()
  }
}