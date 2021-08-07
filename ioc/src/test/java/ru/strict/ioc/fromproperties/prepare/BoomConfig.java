package ru.strict.ioc.fromproperties.prepare;

import lombok.Data;
import ru.strict.ioc.annotation.FromProperties;

@Data
@FromProperties(file = "boom.properties")
public class BoomConfig {
    private String boom1;
    private String boom2;
}
