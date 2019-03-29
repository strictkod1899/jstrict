package ru.strict.patterns;

public interface IEnumDatabaseNamed<ID, ENUM> extends IEnumDatabase<ID>{
    /**
     * <pre>
     * Стандартная реализация поиска enum по caption:
     * Arrays.stream(MyEnum.values())
     *                 .filter(item -> item.getCaption().equals(caption))
     *                 .findFirst()
     *                 .orElse(null);
     * </pre>
     * @return
     */
    String getCaption();
}
