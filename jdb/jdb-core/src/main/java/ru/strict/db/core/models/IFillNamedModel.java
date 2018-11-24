package ru.strict.db.core.models;

public interface IFillNamedModel<ID> extends IFillModelBase<ID> {
    void setCaption(String caption);
}
