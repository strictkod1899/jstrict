package ru.strict.validate;

import ru.strict.exceptions.ValidateException;

import java.util.Collection;

/**
 * Валидатор. При валидации выбрасывается ValidateException
 */
public final class DetailsValidator {

    private String details;

    DetailsValidator(String details, Object[] args) {
        details = details == null ? "" : details;
        this.details = String.format(details, args);
    }

    public void isNull(Object value, String caption) {
        if (value == null) {
            throw new ValidateException(caption, "value is NULL", details);
        }
    }

    public void isEmptyOrNull(String value, String caption) {
        if (CommonValidate.isEmptyOrNull(value)) {
            throw new ValidateException(caption, "value is EMPTY or NULL", details);
        }
    }

    public void isEmptySpaceOrNull(String value, String caption) {
        if (CommonValidate.isEmptySpaceOrNull(value)) {
            throw new ValidateException(caption, "value is EMPTY SPACE or NULL", details);
        }
    }

    public void isEmptyOrNull(Collection<?> collection, String caption) {
        if (CommonValidate.isEmptyOrNull(collection)) {
            throw new ValidateException(caption, "collection is EMPTY or NULL", details);
        }
    }

    public void isEmptyOrNull(Object[] array, String caption) {
        if (CommonValidate.isEmptyOrNull(array)) {
            throw new ValidateException(caption, "array is EMPTY or NULL", details);
        }
    }

    public void isLess(long number, String caption, long minValue) {
        if (number < minValue) {
            throw new ValidateException(caption, String.format("number (%s) < %s", number, minValue, details));
        }
    }

    public void isLess(double number, String caption, double minValue) {
        if (number < minValue) {
            throw new ValidateException(caption, String.format("number (%s) < %s", number, minValue, details));
        }
    }

    public void isLessOrEquals(double number, String caption, double minValue) {
        if (number <= minValue) {
            throw new ValidateException(caption, String.format("number (%s) <= %s", number, minValue, details));
        }
    }

    public void isLessOrEquals(long number, String caption, long minValue) {
        if (number <= minValue) {
            throw new ValidateException(caption, String.format("number (%s) <= %s", number, minValue, details));
        }
    }

    public void isMore(long number, String caption, long maxValue) {
        if (number > maxValue) {
            throw new ValidateException(caption, String.format("number (%s) > %s", number, maxValue, details));
        }
    }

    public void isMore(double number, String caption, double maxValue) {
        if (number > maxValue) {
            throw new ValidateException(caption, String.format("number (%s) > %s", number, maxValue, details));
        }
    }

    public void isMoreOrEquals(long number, String caption, long maxValue) {
        if (number >= maxValue) {
            throw new ValidateException(caption, String.format("number (%s) >= %s", number, maxValue, details));
        }
    }

    public void isMoreOrEquals(double number, String caption, double maxValue) {
        if (number >= maxValue) {
            throw new ValidateException(caption, String.format("number (%s) >= %s", number, maxValue), details);
        }
    }

    public void isRange(long number, String caption, long minValue, long maxValue) {
        if (CommonValidate.isRange(number, minValue, maxValue)) {
            throw new ValidateException(caption, String.format("number (%s) is in range (%s...%s)",
                    number,
                    minValue,
                    maxValue,
                    details)
            );
        }
    }

    public void isNotRange(long number, String caption, long minValue, long maxValue) {
        if (!CommonValidate.isRange(number, minValue, maxValue)) {
            throw new ValidateException(caption, String.format("number (%s) isn't in range (%s...%s)",
                    number,
                    minValue,
                    maxValue,
                    details)
            );
        }
    }
}
