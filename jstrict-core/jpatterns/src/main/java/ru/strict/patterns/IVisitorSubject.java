package ru.strict.patterns;

/**
 * Pattern 'Visitor'. Класс, который применяет Visitor
 * (Visitor, в свою очередь, определяет набор методов visit с разными типами классов,
 * которые преедают в параметры)
 * @param <RESULT> Результат выполнения метода visitor
 */
public interface IVisitorSubject<RESULT> {
    /**
     * Применить конкретный объект Visitor.
     * Стандартная реализация метода: return visitor.visit(this);
     * @param visitor Visitor, который выполняет действия над текущим классом
     * @return
     */
    RESULT acceptVisitor(IVisitor<RESULT> visitor);
}
