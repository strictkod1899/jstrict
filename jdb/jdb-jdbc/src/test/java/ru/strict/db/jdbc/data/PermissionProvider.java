package ru.strict.db.jdbc.data;

import ru.strict.models.IModelProvider;

public class PermissionProvider implements IModelProvider<Permission> {
    @Override
    public Permission getById(Integer id) {
        return Permission.getById(id);
    }

    @Override
    public Permission getByCaption(String caption) {
        return Permission.getByCaption(caption);
    }
}
