package ru.strict.models;

import ru.strict.patterns.MapTarget;

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
 * @return
 */
public interface IModelBase<ID> extends MapTarget {
    ID getId();
}
