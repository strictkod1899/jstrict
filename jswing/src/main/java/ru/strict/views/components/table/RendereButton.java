
package ru.strict.views.components.table;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author strict
 *         Класс для представления содержимого ячеек столбца в виде кнопок
 *         Пример реализации:
 *         table.getColumnModel().getColumn(3).setCellRenderer(new RendereButton());
 *         table.addMouseListener(new MouseAdapter(){
 * @Override public void mousePressed(MouseEvent event){
 * if(table.getSelectedColumn()==3)
 * int selectRow = table.getSelectedRow();
 * }
 * });
 */

public class RendereButton extends JButton implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(Color.RED);
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(Color.RED);
            setBackground(table.getBackground());
        }

        setText("x");

        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
            if (table.isCellEditable(row, column)) {
                setForeground(UIManager.getColor("Table.focusCellForeground"));
                setBackground(UIManager.getColor("Table.focusCellBackground"));
            }
        } else
            setBorder(new EmptyBorder(1, 2, 1, 2));

        setFont(table.getFont());
        setText("x");
        return this;
    }

}
