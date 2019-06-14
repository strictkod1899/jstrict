package ru.strict.db.spring.data;

import ru.strict.models.IModelProvider;

public class ServiceProvider implements IModelProvider<ServiceModel> {
    @Override
    public ServiceModel getById(Integer id) {
        return ServiceModel.getById(id);
    }

    @Override
    public ServiceModel getByCaption(String caption) {
        return ServiceModel.getByCaption(caption);
    }
}
