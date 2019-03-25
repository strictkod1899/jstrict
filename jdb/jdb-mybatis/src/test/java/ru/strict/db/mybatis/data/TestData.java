package ru.strict.db.mybatis.data;

import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;

import java.util.UUID;

public class TestData {
    public static final DtoCountry<Integer> COUNTRY1 = new DtoCountry<>(111, "country1");
    public static final DtoCountry<Integer> COUNTRY1_UPDATED = new DtoCountry<>(111, "country1_updated");
    public static final DtoCountry<UUID> COUNTRY1_UUID = new DtoCountry<>(UUID.randomUUID(), "country1");

    public static final DtoCity<Integer> CITY1 = new DtoCity<>(121, "city1", COUNTRY1.getId());
    public static final DtoCity<Integer> CITY1_UPDATED = new DtoCity<>(121, "city1_updated", COUNTRY1.getId());
    public static final DtoCity<UUID> CITY1_UUID = new DtoCity<>(UUID.randomUUID(), "city1", COUNTRY1_UUID.getId());

}
