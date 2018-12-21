
package ru.strict.swing.components.table;

import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author strict
 *
 * Класс для предоставления визуализации ячейки таблицы в определенном цвете
 *         Пример реализации:
 *         table.setDefaultRenderer(Color.class, new RendereColor());
 */

public class RendereColor extends DefaultTableCellRenderer {

    /**
     * Установить цвет ячейки
     * @param value
     */
    @Override
    public void setValue(Object value) {
        try {
            setBackground((Color) value);
        } catch (ClassCastException ignore) {
            ignore.printStackTrace();
        }
    }
}
