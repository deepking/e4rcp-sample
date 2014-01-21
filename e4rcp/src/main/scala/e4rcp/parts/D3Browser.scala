package e4rcp.parts

import javax.annotation.PostConstruct
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.browser.Browser
import org.eclipse.swt.SWT
import scala.io.Source
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.browser.BrowserFunction

class D3Browser {

  @PostConstruct
  def createPartControl(parent: Composite) {
    parent.setLayout(new FillLayout)
    val browser = new Browser(parent, SWT.NONE)
    val src = Source.fromURL(getClass.getResource("/index.html"))
    
    browser.setText(src.mkString)
    
    src.close()
 
    // called by javascript
    new BrowserFunction(browser, "javafunc") {
      override def function(args: Array[Object]) = {
        println(args.mkString(" "))
        null
      }
    }
    
  }
  
}
