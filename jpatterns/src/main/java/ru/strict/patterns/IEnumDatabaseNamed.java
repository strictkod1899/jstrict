package ru.strict.patterns;

public interface IEnumDatabaseNamed<ID, ENUM> extends IEnumDatabase<ID>{
    String getCaption();

    /**
     * <pre>
     * Стандартная реализация:
     * Arrays.stream(MyEnum.values())
     *                 .filter(item -> item.getCaption().equals(caption))
     *                 .findFirst()
     *                 .orElse(null);
     * </pre>
     * @param caption
     * @return
     */
    ENUM getByCaption(String caption);
}
