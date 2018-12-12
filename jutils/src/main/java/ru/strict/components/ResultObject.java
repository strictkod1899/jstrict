package ru.strict.components;

public class ResultObject<OBJECT> extends ResultMessages {

    private OBJECT object;

    public ResultObject() {
        super();
    }

    public OBJECT getObject() {
        return object;
    }

    public void setObject(OBJECT object) {
        this.object = object;
    }
}
