package ru.strict.office.excel;

import org.apache.poi.ss.usermodel.*;

class CellStyleFactory {

    private Workbook source;

    CellStyleFactory(Workbook source) {
        this.source = source;
    }

    public CellStyle createDefaultStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)1);
        style.setFillForegroundColor((short)1);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.NO_FILL);
        style.setBottomBorderColor((short)8);
        style.setLeftBorderColor((short)8);
        style.setRightBorderColor((short)8);
        style.setTopBorderColor((short)8);

        return style;
    }

    public CellStyle createGoodStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)42);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }

    public CellStyle createNeutralStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)43);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }

    public CellStyle createBadStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)29);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }

    public CellStyle createLightBlueStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)49);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }

    public CellStyle createBlueStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)30);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }

    public CellStyle createDarkBlueStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)56);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }

    public CellStyle createCoolStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)41);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }

    public CellStyle createOrangeStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)52);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }

    public CellStyle createYellowStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)51);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }

    public CellStyle createGreenStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)50);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }

    public CellStyle createRedStyle(){
        CellStyle style = source.createCellStyle();
        style.setFillBackgroundColor((short)64);
        style.setFillForegroundColor((short)2);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setBorderTop(BorderStyle.NONE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBottomBorderColor((short)0);
        style.setLeftBorderColor((short)0);
        style.setRightBorderColor((short)0);
        style.setTopBorderColor((short)0);

        return style;
    }
}
