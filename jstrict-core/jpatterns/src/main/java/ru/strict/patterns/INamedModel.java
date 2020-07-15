package ru.strict.patterns;

/**
 * Стандартная реализация поиска enum по caption:
 * <code><pre style="background-color: white; font-family: consolas">
 * public static MyEnum getByCaption(String caption){
 *     if(caption == null){
 *         return null;
 *     }
 *
 *     return Arrays.stream(MyEnum.values())
 *         .filter(item -> item.caption.equals(caption))
 *         .findFirst()
 *         .orElse(null);
 * }
 * </pre></code>
 */
public interface INamedModel<ID> extends IModel<ID> {
    String getCaption();
}
