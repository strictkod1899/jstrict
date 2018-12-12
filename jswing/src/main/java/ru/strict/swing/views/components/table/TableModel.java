package ru.strict.swing.views.components.table;

import ru.strict.validates.ValidateBaseValue;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.*;

//TODO: Накидать проверок исключений в различные методы
/**
 * <p>Реализация модели таблицы</p>
 *
 * <i>Object [][] masO = { <br/>
 * {"Строка 1", Color.BLACK, "Значение 1", new Boolean(true)}, <br/>
 * {"Строка 2", Color.RED, "Значение 2", new Boolean(true)}, <br/>
 * {"Строка 3", Color.GREEN, "Значение 3", new Boolean(false)} <br/>
 * }; <br/>
 * <br/>
 * String [] str = {"Столбец 1", "Столбец 2", "Столбец 3", "Столбец 4"}; <br/>
 * <br/>
 *
 * setLayout(new FlowLayout()); <br/>
 * TableModel tablemodel = new TableModel(masO, str); <br/>
 * JTable table = new JTable(tablemodel); <br/>
 * table.setAutoCreateRowSorter(true); <br/>
 * table.setDefaultRenderer(Color.class, new RendereColor()); <br/>
 * <br/>
 * JComboBox cmb = new JComboBox(); <br/>
 * cmb.addItem("М"); <br/>
 * cmb.addItem("Ж"); <br/>
 * ColumnComboBox cbEd = new ColumnComboBox(table); <br/>
 * // Отображать в виде ComboBox третью строку таблицы <br/>
 * cbEd.setEditorAt(2, new DefaultCellEditor(cmb)); <br/>
 * // Отображать в виде ComboBox второй столбец таблицы <br/>
 * table.getColumn("Столбец 3").setCellEditor(cbEd); <br/>
 * add(new JScrollPane(table)); <br/>
 * setDefaultCloseOperation(EXIT_ON_CLOSE); <br/>
 * pack(); <br/>
 * setVisible(true); <br/>
 */
public class TableModel extends AbstractTableModel {

    /**
     * Список заголовков таблицы
     */
    private LinkedList<String> listColumnTitle;
    /**
     * Список содержимого таблицы
     */
    private List<List<Object>> listContentTable;

    /**
     * Список столбцов разрешенных к редактированию
     */
    private LinkedList<Integer> listEditColumn;

    /**
     * Разрешено ли редактировать таблицу
     */
    private boolean isEditTable;

    //<editor-fold defaultstate="collapsed" desc="Конструкторы">

    /**
     * Конструктор модели таблицы. Пустой
     */
    public TableModel() {
        super();
        listContentTable = new LinkedList<>();
        listColumnTitle = new LinkedList<>();
    }

    /**
     * Конструктор модели таблицы с передачей содержимого и заголовков таблицы в виде массива
     *
     * @param masContentTable массив содержимого таблицы
     * @param masTitleTable   массив заголовков таблицы
     */
    public TableModel(Object[][] masContentTable, String[] masTitleTable) {
        super();

        listContentTable = new LinkedList<>();
        listColumnTitle = new LinkedList<>();

        fillContentTable(masContentTable, masTitleTable);
    }

    /**
     * Конструктор модели таблицы с передачей содержимого и заголовков таблицы в виде списка
     *
     * @param listContentTable список содержимого таблицы
     * @param listTitleTable   список заголовков таблицы
     */
    public TableModel(List<List<Object>> listContentTable, Collection<String> listTitleTable) {
        super();

        this.listContentTable = new LinkedList<>();
        listColumnTitle = new LinkedList<>();

        fillContentTable(listContentTable, listTitleTable);
    }

    /**
     * Конструктор модели таблицы с передачей содержимого и заголовков таблицы в виде массива
     *
     * @param masContentTable массив содержимого таблицы
     * @param masTitleTable   массив заголовков таблицы
     */
    public TableModel(String[][] masContentTable, String[] masTitleTable) {
        super();

        listContentTable = new LinkedList<>();
        listColumnTitle = new LinkedList<>();

        fillContentTable(masContentTable, masTitleTable);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Обработка переданных значений из конструктора">

    /**
     * Обработка переданных значений из конструктора в виде массива
     *
     * @param masContentTable массив содержимого таблицы
     * @param masTitleTable   массив заголовков таблицы
     */
    private void fillContentTable(Object[][] masContentTable, String[] masTitleTable) {

        int maxLength = 0;

        for (int i = 0; i < masContentTable.length; i++) {
            if (masContentTable[i].length > maxLength)
                maxLength = masContentTable[i].length;
        }

        if (maxLength != masTitleTable.length) {
            System.err.println("Разное количество столбцов у заголовков и значений");
            return;
        }

        // Добавление названий заголовков
        for (int i = 0; i < masTitleTable.length; i++)
            listColumnTitle.addLast(masTitleTable[i]);

        // Добавление значений в ячейки
        for (int i = 0; i < masContentTable.length; i++) {
            listContentTable.add(new LinkedList<>());
            for (int j = 0; j < maxLength; j++) {
                if (j < masContentTable[i].length)
                    listContentTable.get(listContentTable.size() - 1).add(masContentTable[i][j]);
                else
                    listContentTable.get(listContentTable.size() - 1).add("");
            }
        }

        fireTableRowsInserted(0, listContentTable.size() - 1);

        // Если таблица пустая, то добавляем пустую строку
        if(getRowCount()==0)
            addRow(1);
    }

    /**
     * Обработка переданных значений из конструктора в ввиде списка
     *
     * @param listContentTable спиок содержимого таблицы
     * @param listTitleTable   список заголовков таблицы
     */
    private void fillContentTable(List<List<Object>> listContentTable, Collection<String> listTitleTable) {

        int maxLength = 0;

        for (int i = 0; i < listContentTable.size(); i++) {
            if (listContentTable.get(i).size() > maxLength)
                maxLength = listContentTable.get(i).size();
        }

        if (maxLength != listTitleTable.size()) {
            System.err.println("Разное количество столбцов у заголовков и значений");
            return;
        }

        Iterator<String> iterColumnTitile = listTitleTable.iterator();
        // Добавление названий заголовков
        while(iterColumnTitile.hasNext())
            listColumnTitle.addLast(iterColumnTitile.next());

        // Добавление значений в ячейки
        for (int i = 0; i < listContentTable.size(); i++) {
            this.listContentTable.add(new LinkedList<>());
            for (int j = 0; j < maxLength; j++) {
                if (j < listContentTable.get(i).size())
                    this.listContentTable.get(this.listContentTable.size() - 1).add(listContentTable.get(i).get(j));
                else
                    this.listContentTable.get(this.listContentTable.size() - 1).add("");
            }
        }

        fireTableRowsInserted(0, this.listContentTable.size() - 1);

        // Если таблица пустая, то добавляем пустую строку
        if(getRowCount()==0)
            addRow(1);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Добавление строки">

    /**
     * Добавление нескольких пустых строк
     * @param countRow количество строк
     */
    public void addRow(int countRow) {
        for (int i = 0; i < countRow; i++) {
            listContentTable.add(new LinkedList<>());
            for (int j = 0; j < listColumnTitle.size(); j++)
                listContentTable.get(listContentTable.size() - 1).add(" ");
        }
        fireTableRowsInserted(listContentTable.size() - 1 - countRow, listContentTable.size() - 1);
    }

    /**
     * Добавление новой строки
     * @param list Добавляемая строка со значениями
     */
    public void addRow(List<Object> list) {
        // Если первая строка содержит только пустые символы, тогда удаляем ее
        if(getRowCount()==1) {
            Iterator iterRow = getListContentTable().get(0).iterator();
            boolean bool = false;
            while(iterRow.hasNext()){
                if(!ValidateBaseValue.isEmptyOrNull(String.valueOf(iterRow.next()))) {
                    bool = true;
                    break;
                }
            }
            if(!bool){
                listContentTable.remove(0);
            }
        }

        List<Object> listVal = list.stream()
                .map(e -> {
                    if (e instanceof Date)
                        return new SimpleDateFormat("dd.MM.yyy").format(e);
                    return String.valueOf(e);
                })
                .collect(Collectors.toList());

        listContentTable.add(listVal);
        fireTableRowsInserted(listContentTable.size() - 1 - 1, listContentTable.size() - 1);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Добавление столбцов">

    /**
     * Добавление нескольких столбцов
     * @param countColumn количество добавляемых столбцов
     */
    public void addColumn(int countColumn) {

        for (int j = 0; j < countColumn; j++)
            listColumnTitle.addLast("");

        for (int i = 0; i < listContentTable.size(); i++) {
            for (int j = 0; j < countColumn; j++)
                listContentTable.get(i).add(" ");
        }

        fireTableStructureChanged();
    }

    /**
     * Добавление нового столбца с заголовком
     * @param columnTitle заголовок нового столбца
     */
    public void addColumn(String columnTitle) {
        listColumnTitle.add(columnTitle);

        for (int i = 0; i < listContentTable.size(); i++) {
            listContentTable.get(i).add(" ");
        }
        fireTableStructureChanged();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Получить количество строк таблицы">

    /**
     * Получить количество строк таблицы
     * @return
     */
    @Override
    public int getRowCount() {
        return listContentTable.size();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Получить количество столбцов таблицы">
    /**
     * Получить количество столбцов таблицы
     * @return
     */
    @Override
    public int getColumnCount() {
        try {
            return listContentTable.get(0).size();
        } catch (IndexOutOfBoundsException | NoSuchElementException ex) {
            return 0;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Получить значение ячейки таблицы">

    /**
     * Получить значение ячейки таблицы
     * @param rowIndex номер строки, начиная с нуля
     * @param columnIndex номер столбца, начиная с нуля
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            listContentTable = new LinkedList<>(listContentTable);
            return listContentTable.get(rowIndex).get(columnIndex);
        } catch (IndexOutOfBoundsException | NoSuchElementException ex) {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Получить заголовок столбца">

    /**
     * Получить заголовок столбца
     * @param col номер столбца
     * @return
     */
    @Override
    public String getColumnName(int col) {
        return listColumnTitle.get(col);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Визуализация значений таблицы">

    /**
     * Визуализация значений таблицы
     * @param col номер столбца
     * @return
     */
    @Override
    public Class getColumnClass(int col) {
        return listContentTable.get(0).get(col).getClass();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Определение ячеек разрешенных к редактированию">

    /**
     * Реализация ячеек разрешенных к редактированию
     * @param row
     * @param col
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        if(isEditTable) {
            if (listEditColumn != null) {
                for (Integer column : listEditColumn) {
                    if (col == column)
                        return false;
                }
            }
            return true;
        }else
            return false;
    }

    /**
     * Получить список столбцов разрешенных к редактированию
     * @return
     */
    public LinkedList<Integer> getListEditColumn() {
        return listEditColumn;
    }

    /**
     * Установить список столбцов разрешенных к редактированию
     * @param listEditColumn
     */
    public void setListEditColumn(LinkedList<Integer> listEditColumn) {
        this.listEditColumn = listEditColumn;
    }

    /**
     * Добавить столбец не разрешенный к редактированию
     * @param index номер столбца, который нельзя редактировать
     * @return
     */
    public LinkedList<Integer> addIndexEditColumn(int index){
        if(listEditColumn==null)
            listEditColumn = new LinkedList<Integer>();
        listEditColumn.add(index);
        return listEditColumn;
    }

    /**
     * Узнать свойство, разрешено ли редактировать таблицу
     * @return
     */
    public boolean isEditTable() {
        return isEditTable;
    }

    /**
     * Установить свойство: разрешено ли редактировать таблицу или нет
     * @param editTable
     */
    public void setEditTable(boolean editTable) {
        isEditTable = editTable;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Установить новые значения">

    /**
     * Установить новое значения в ячейку
     * @param value новое значение
     * @param row номер строки
     * @param col номер столбца
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        listContentTable.get(row).set(col, value);
        fireTableRowsUpdated(row - 1, row - 1);
    }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Установка размера столбцов таблицы по содержимому">

    /**
     * Установка размера столбцов таблицы по содержимому
     * @param table таблица, для которой применяется метод
     */
    public final static void setColumnsWidthContent(JTable table) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JTableHeader th = table.getTableHeader();
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            int prefWidth =
                    Math.round(
                            (float) th.getFontMetrics(
                                    th.getFont()).getStringBounds(th.getTable().getColumnName(i),
                                    th.getGraphics()
                            ).getWidth()
                    );
            column.setPreferredWidth(prefWidth + 10);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Установка определенного размера столбца">

    /**
     * Установка определенного размера столбца
     * @param table таблица, для которой применяется метод
     * @param columnIndex номер столбца
     * @param width новая ширина столбца
     */
    public final static void setWidthColumn(JTable table, int columnIndex, int width) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JTableHeader th = table.getTableHeader();
        TableColumn column = table.getColumnModel().getColumn(columnIndex);
        column.setPreferredWidth(width + 10);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Установка размера столбов по определнной ширине">

    /**
     * Установка размера столбов по определнной ширине
     * @param table таблица, для которой применяется метод
     * @param width требуемая ширина таблицы
     */
    public final static void setWidthColumns(JTable table, int width) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JTableHeader th = table.getTableHeader();
        int countColumn = table.getModel().getColumnCount();
        int widthColumn = (int)((double)width/(double)countColumn);
        for(int i=0; i<countColumn; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(widthColumn);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Установка названий столбцов">

    /**
     * Установка названий столбцов
     * @param masTitle массив названий столбцов
     */
    public void setTitleTable(String[] masTitle) {
        listColumnTitle.removeAll(listColumnTitle);
        listColumnTitle.addAll(Arrays.asList(masTitle));

        if (listContentTable.isEmpty()) {
            listContentTable.add(new LinkedList<>());
            String[] masTemp = new String[masTitle.length];
            Arrays.fill(masTemp, "");
            listContentTable.get(0).addAll(Arrays.asList(masTemp));
            listContentTable.get(0).set(1, false);
        }
        fireTableDataChanged();
    }

    public void setTitleTable(List<String> listTitle) {
        listColumnTitle.removeAll(listColumnTitle);
        listColumnTitle.addAll(listTitle);

        if (listContentTable.isEmpty()) {
            listContentTable.add(new LinkedList<>());
            String[] masTemp = new String[listTitle.size()];
            Arrays.fill(masTemp, "");
            listContentTable.get(0).addAll(Arrays.asList(masTemp));
            listContentTable.get(0).set(1, false);
        }
        fireTableDataChanged();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Методы удаления">

    /**
     * Удаление столбца
     * @param columnIndex номер столбца для удаления
     */
    public void removeColumn(int columnIndex) {
        listColumnTitle.remove(columnIndex);

        for (int i = 0; i < listContentTable.size(); i++)
            listContentTable.get(i).remove(columnIndex);

        if(listContentTable.size()==0)
            addRow(1);

        fireTableStructureChanged();
    }

    /**
     * Удаление строки
     * @param rowIndex номер строки для удаления
     */
    public void removeRow(int rowIndex) {
        listContentTable.remove(rowIndex);

        if(listContentTable.size()==0)
            addRow(1);

        fireTableStructureChanged();
    }

    /**
     * Удаление всех данных из таблицы (небезопасно). Используется внутри класса модели
     */
    private void removeAll() {
        listColumnTitle.removeAll(listColumnTitle);
        listColumnTitle.addLast("");
        listContentTable.removeAll(listContentTable);
        listContentTable.add(new LinkedList<>());
    }

    /**
     * Безопасное удаление всех данных из таблицы (Включая заголоки). Метод разрешен к использованию пользователем
     */
    public void removeAllSafe() {
        while (listColumnTitle.size() > 0) {
            removeColumn(0);
        }
    }

    /**
     * Удаление всего содержимого таблицы (Удаление всех строк из таблицы)
     */
    public void removeRows() {
        listContentTable.removeAll(listContentTable);
        LinkedList<Object> list = new LinkedList<>();
        for (int i = 0; i < listColumnTitle.size(); i++)
            list.add("");
        listContentTable.add(list);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Установка нового содержимого таблицы">

    /**
     * Метод устанавливает новое содержимое таблицы
     *
     * @param table     экземпляр таблицы
     * @param listTitle список заголовков таблицы
     * @param list      список содержимого таблицы
     */
    public static void setNewContent(JTable table, Collection<String> listTitle, List<List<Object>> list) {
        TableModel tableModel = (TableModel) table.getModel();
        tableModel.removeAll();

        // Удаляем все столбцы таблицы
        for (int i = 0; i < tableModel.getColumnCount(); i++)
            table.removeColumn(table.getColumnModel().getColumn(0));

        Iterator<String> iteratorTitle = listTitle.iterator();
        Iterator<List<Object>> iterator = list.iterator();

        // Добавляем новые столбцы
        while (iteratorTitle.hasNext())
            tableModel.addColumn(iteratorTitle.next());

        // Удаляем пустой столбец
        tableModel.removeColumn(0);

        // Добавляем данные в таблицу
        while (iterator.hasNext()) {
            tableModel.addRow(1);
            List<Object> listRow = iterator.next();
            Iterator<Object> iteratorRow = listRow.iterator();
            int i = 0;

            while (iteratorRow.hasNext()) {
                tableModel.setValueAt(iteratorRow.next(), tableModel.getRowCount() - 1, i);
                i++;
            }
        }

        tableModel.removeRow(0);
        // Если таблица пустая, то добавляем пустую строку
        if(tableModel.getRowCount()==0)
            tableModel.addRow(1);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get-методы">

    /**
     * Получить список заголовков столбцов таблицы
     *
     * @return
     */
    public LinkedList<String> getListColumnTitle() {
        return listColumnTitle;
    }

    /**
     * Получить список содержимого таблицы
     *
     * @return
     */
    public List<List<Object>> getListContentTable() {
        return listContentTable;
    }

    //</editor-fold>
}
