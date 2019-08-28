package ru.strict.models;

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
 * @return
 */
public interface INamedModel<ID> extends IModelBase<ID> {
    String getCaption();
}
