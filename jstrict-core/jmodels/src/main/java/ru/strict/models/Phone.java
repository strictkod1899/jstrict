package ru.strict.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Objects;

@JsonSerialize
@JsonDeserialize
public class Phone {
    /**
     * Тип телефона
     */
    private PhoneType type;
    /**
     * Номер телефона
     */
    private String number;
    /**
     * Добавочный номер
     */
    private String extNumber;

    public Phone() {
    }

    public Phone(PhoneType type, String number) {
        this.type = type;
        this.number = number;
    }

    public Phone(PhoneType type, String number, String extNumber) {
        this.type = type;
        this.number = number;
        this.extNumber = extNumber;
    }

    public String toText() {
        if (extNumber == null || extNumber.length() == 0) {
            return number;
        } else {
            return String.format("%s %s", number, extNumber);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Phone phone = (Phone) o;
        return type == phone.type &&
                Objects.equals(number, phone.number) &&
                Objects.equals(extNumber, phone.extNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number, extNumber);
    }

    @Override
    public String toString() {
        return toText();
    }
}
