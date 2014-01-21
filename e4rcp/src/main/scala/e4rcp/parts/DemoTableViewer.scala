package e4rcp.parts

import javax.annotation.PostConstruct
import org.eclipse.swt.widgets.Composite
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.swt.SWT
import scala.beans.BeanProperty
import org.eclipse.jface.viewers.TableViewerColumn
import org.eclipse.core.databinding.observable.list.WritableList
import scala.collection.JavaConversions._
import org.eclipse.jface.databinding.viewers.ViewerSupport
import org.eclipse.core.databinding.beans.BeanProperties
import org.eclipse.core.databinding.beans.IBeanValueProperty
import org.eclipse.core.databinding.property.value.IValueProperty
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeSupport
import org.eclipse.jface.layout.GridLayoutFactory
import org.eclipse.jface.layout.GridDataFactory
import scala.collection.mutable.ArrayBuffer

class DemoTableViewer {

  case class Person(var name: String, var age: Int) {
    val propertyChangeSupport = new PropertyChangeSupport(this)

    def getName = name
    def setName(name: String) = propertyChangeSupport.firePropertyChange("name", this.name, this.name = name)

    def getAge = age
    def setAge(age: Int) = propertyChangeSupport.firePropertyChange("age", this.age, this.age = age)

    def addPropertyChangeListener(propertyName: String, listener: PropertyChangeListener) = propertyChangeSupport.addPropertyChangeListener(propertyName, listener)

    def removePropertyChangeListener(listener: PropertyChangeListener) = propertyChangeSupport.removePropertyChangeListener(listener)
  }

  val models = ArrayBuffer(Person("a", 1), Person("b", 2), Person("c", 3))

  @PostConstruct
  def createPartControl(parent: Composite) {
    GridLayoutFactory.fillDefaults.applyTo(parent)

    val header = Array("name", "age")
    val table = new TableViewer(parent, SWT.NONE)
    GridDataFactory.fillDefaults.applyTo(table.getControl)

    // Column header
    val col1 = new TableViewerColumn(table, SWT.NONE)
    col1.getColumn.setText(header(0))
    col1.getColumn.setWidth(100)
    val col2 = new TableViewerColumn(table, SWT.NONE)
    col2.getColumn.setText(header(1))
    col2.getColumn.setWidth(100)

    // databinding
    val input = new WritableList(models, classOf[Person])
    val properties = BeanProperties.values(header).toArray[IValueProperty]
    ViewerSupport.bind(table, input, properties)

    table.getTable.setHeaderVisible(true)
    table.getTable.setLinesVisible(true)

    // demo databinding
    parent.getDisplay.asyncExec(new Runnable {
      override def run {
        Thread.sleep(3000)
        println("set 100")
        models(0).setAge(100)
        
       input.remove(1)
      }
    })
  }
}