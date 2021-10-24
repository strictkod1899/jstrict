package ru.strict.office.xls;

import org.jxls.common.Context;

import static ru.strict.util.DateFormatter.*;

class XlsContext extends Context {
    public XlsContext() {
        putVar("dateFormat", DATE_FORMAT_DMY_BY_POINT);
        putVar("dateTimeMmFormat", DATETIME_FORMAT_MM);
        putVar("dateTimeSsFormat", DATETIME_FORMAT_SS);
        putVar("namedDateFormat", NAMED_DATE_FORMATTER);
    }
}
