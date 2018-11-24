package ru.strict.patterns;

public interface IEnumNamed<ENUM> {
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
