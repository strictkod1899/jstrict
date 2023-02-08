package ru.strict.domainprimitive.title;

import lombok.experimental.UtilityClass;
import ru.strict.test.RandomUtil;

import java.util.UUID;

@UtilityClass
public class TitleStub {

    public Title getTitle() {
        return getTitle(RandomUtil.generateDefaultStr());
    }

    public Title getTitle(String titleStr) {
        return new Title(titleStr);
    }
}
