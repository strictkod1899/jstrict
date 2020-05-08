package ru.strict.db.core.requests;

import ru.strict.patterns.composite.CompositeBase;

public abstract class DbWhereBase<SOURCE, COMPOSIT extends DbWhereBase>
        extends CompositeBase<SOURCE, COMPOSIT>
        implements IDbRequest, IDbParametrizedRequest {
}
