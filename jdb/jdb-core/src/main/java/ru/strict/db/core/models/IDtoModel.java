package ru.strict.db.core.models;

public interface IDtoModel<DTO> {
    void fillByDto(DTO dto);
}
