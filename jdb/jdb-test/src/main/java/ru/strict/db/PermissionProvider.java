package ru.strict.db;

import ru.strict.models.IModelProvider;

public class PermissionProvider implements IModelProvider<Permission> {
    @Override
    public Permission getById(Integer id) {
        return Permission.getById(id);
    }
}
