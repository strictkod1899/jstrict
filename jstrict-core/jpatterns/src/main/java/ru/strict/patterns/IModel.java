package ru.strict.patterns;

/**
 * Стандартная реализация поиска enum по id:
 * <code><pre style="background-color: white; font-family: consolas">
 * public static MyEnum getById(Integer id){
 *     if(id == null){
 *         return null;
 *     }
 *
 *     return Arrays.stream(MyEnum.values())
 *         .filter(item -> item.id == id)
 *         .findFirst()
 *         .orElse(null);
 * }
 * </pre></code>
 */
public interface IModel<ID> extends MapTarget {
    ID getId();
}
