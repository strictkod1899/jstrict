package ru.strict.swing.components.table;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * @author strict
 *
 * Класс для предоставления визуализации ячейки таблицы в виде многострочного текста
 *         Пример реализации:
 *         table.setDefaultRenderer(String.class, new RendereTextArea());
 *
 *          В setDefaultRenderer(Class<?> columnClass, TableCellRenderer renderer) указываем тип обрабатываемых этим рендером классов.
 *          Или же можно подключить на конкретный столбец методом getColumnModel().getColumn(int index).setCellRenderer(TableCellRenderer renderer);
 */
public class RendereTextArea extends JTextArea implements TableCellRenderer {

    private static final long serialVersionUID = 1L;
    private Font font;

    public RendereTextArea(Font font) {
        super();
        this.font = font;
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object arg1, boolean isSelected, boolean hasFocus, int row, int column) {
        String data = (String) arg1.toString();
        this.setText(data);
        int lineWidth = this.getFontMetrics(this.getFont()).stringWidth(data);
        int lineHeight = this.getFontMetrics(this.getFont()).getHeight();
        int rowWidth = table.getCellRect(row, column, true).width;
        setFont(font);

        int newRowHeight =((lineWidth / rowWidth) * (lineHeight)) + lineHeight * 2;
        if (table.getRowHeight(row) != newRowHeight) {
            table.setRowHeight(row, newRowHeight);
        }
        return this;
    }
}
