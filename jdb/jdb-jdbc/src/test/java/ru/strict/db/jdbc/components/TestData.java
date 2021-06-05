package ru.strict.db.jdbc.components;

public final class TestData {

    public static final ru.strict.db.jdbc.components.Country<Long>
            COUNTRY1 = new ru.strict.db.jdbc.components.Country<>(1L, "country1");
    public static final ru.strict.db.jdbc.components.Country<Long>
            COUNTRY2 = new ru.strict.db.jdbc.components.Country<>(2L, "country2");
    public static final ru.strict.db.jdbc.components.Country<Long>
            COUNTRY3 = new ru.strict.db.jdbc.components.Country<>(3L, "country3");
    public static final ru.strict.db.jdbc.components.Country<Long>
            UPDATED_COUNTRY1 = new ru.strict.db.jdbc.components.Country<>(1L, "country1-2");

    public static final ru.strict.db.jdbc.components.Country<Long>
            FILL_COUNTRY1 = new ru.strict.db.jdbc.components.Country<>(1L, "country4");
    public static final ru.strict.db.jdbc.components.Country<Long>
            UPDATED_FILL_COUNTRY1 = new Country<>(1L, "country4-2");



    public static final ru.strict.db.jdbc.components.City<Long>
            CITY1 = new ru.strict.db.jdbc.components.City<>(1L, "city1", COUNTRY1.getId());
    public static final ru.strict.db.jdbc.components.City<Long>
            CITY2 = new ru.strict.db.jdbc.components.City<>(2L, "city2", COUNTRY1.getId());
    public static final ru.strict.db.jdbc.components.City<Long>
            CITY3 = new ru.strict.db.jdbc.components.City<>(3L, "city3", COUNTRY2.getId());
    public static final ru.strict.db.jdbc.components.City<Long>
            UPDATED_CITY1 = new ru.strict.db.jdbc.components.City<>(1L, "city1-2", COUNTRY2.getId());

    public static final ru.strict.db.jdbc.components.City<Long>
            FILL_CITY1 = new ru.strict.db.jdbc.components.City<>(1L, "city4", COUNTRY1.getId());
    public static final ru.strict.db.jdbc.components.City<Long> UPDATED_FILL_CITY1 = new City<>(1L, "city4-2", COUNTRY1.getId());

    static {
        FILL_COUNTRY1.addCity(CITY1);
        FILL_COUNTRY1.addCity(CITY2);
        UPDATED_FILL_COUNTRY1.addCity(CITY1);
        UPDATED_FILL_COUNTRY1.addCity(CITY2);

        FILL_CITY1.setCountry(COUNTRY1);
        UPDATED_FILL_CITY1.setCountry(COUNTRY1);
    }

    private TestData() {
    }
}
