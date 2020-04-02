package ru.strict.models;

import static ru.strict.utils.StringUtil.*;

public class Address {
    /**
     * Почтовый индекс
     */
    private String zipCode;
    /**
     * Страна
     */
    private String country;
    /**
     * Регион
     */
    private String region;
    /**
     * Район
     */
    private String area;
    /**
     * Город
     */
    private String city;
    /**
     * Улица
     */
    private String street;
    /**
     * Номер дома
     */
    private String house;
    /**
     * Корпус
     */
    private String building;
    /**
     * Номер квартиры (помещения)
     */
    private String flat;

    @Override
    public String toString() {
        return join(", ", zipCode, country, region, area, city, street, house, building, flat);
    }
}
