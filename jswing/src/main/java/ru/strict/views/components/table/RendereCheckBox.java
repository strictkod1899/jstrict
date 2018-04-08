package ru.strict.views.components.table;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * @author strict
 *         Класс для представления содержимого ячеек столбца в виде флажков (checkbox)
 *         Пример реализации:
 *         table.getColumnModel().getColumn(1).setCellRenderer(new RendereCheckBox());
 *         table.addMouseListener(new MouseAdapter(){
 * @Override public void mousePressed(MouseEvent event){
 * if(table.getSelectedColumn()==1){
 * int selectRow = table.getSelectedRow();
 * try{
 * if((boolean)(table.getValueAt(selectRow, 1))!=true)
 * table.setValueAt(true, selectRow, 1);
 * else
 * table.setValueAt(false, selectRow, 1);
 * } catch(ClassCastException ex){
 * table.setValueAt(true, selectRow, 1);
 * }
 * }
 * }
 * });
 */
public class RendereCheckBox extends JCheckBox implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else
            setForeground(table.getForeground());
        setBackground(table.getBackground());

        setSelected((value != null && value != "" && ((Boolean) value).booleanValue()));
        return this;
    }

}
