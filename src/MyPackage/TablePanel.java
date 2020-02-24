package MyPackage;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TablePanel extends JPanel
{
  public TablePanel( TableModel model )
  {
	
    table = new JTable( model );
    table.getPreferredSize();
    setLayout( new BorderLayout() );
    add( new JScrollPane( table ), BorderLayout.CENTER );
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    table.setFillsViewportHeight(true);
    
  }
  private JTable table;
}
