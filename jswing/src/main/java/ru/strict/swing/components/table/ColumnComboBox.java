package ru.strict.swing.components.table;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * @author strict
 *         Класс реализующий визуализацию компонентов, по нажатию мыши, в ячейке таблицы, используя: JTextField, JCheckBox или JComboBox.
 *
 *         Пример реализации:
 *         JComboBox cmb = new JComboBox();
 *         cmb.addItem("М");
 *         cmb.addItem("Ж");
 *         ColumnComboBox cbEd = new ColumnComboBox(table);
 *         cbEd.setEditorAt(2, new DefaultCellEditor(cmb));
 *         table.getColumn("Заголовок 2").setCellEditor(cbEd);
 */

public class ColumnComboBox implements TableCellEditor {

    protected Hashtable hashEditors;
    protected TableCellEditor cellEditor, defCellEditor;
    private JTable table;

    public ColumnComboBox(JTable table) {
        this.table = table;
        hashEditors = new Hashtable();
        defCellEditor = new DefaultCellEditor(new JTextField());
    }

    public void setEditorAt(int row, TableCellEditor editor) {
        hashEditors.put(row, editor);
    }

    @Override
    public JComponent getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return (JComponent) cellEditor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    public Object getCellEditorValue() {
        return cellEditor.getCellEditorValue();
    }

    @Override
    public boolean stopCellEditing() {
        return cellEditor.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
        cellEditor.cancelCellEditing();
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        selectEditor((MouseEvent) event);
        return cellEditor.isCellEditable(event);
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        cellEditor.addCellEditorListener(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        cellEditor.removeCellEditorListener(l);
    }

    @Override
    public boolean shouldSelectCell(EventObject event) {
        selectEditor((MouseEvent) event);
        return cellEditor.shouldSelectCell(event);
    }

    protected void selectEditor(MouseEvent event) {
        int row = (event == null) ?
                table.getSelectionModel().getAnchorSelectionIndex() :
                table.rowAtPoint(event.getPoint());

        cellEditor = (TableCellEditor) hashEditors.get(row);
        if (cellEditor == null)
            cellEditor = defCellEditor;
    }

}
