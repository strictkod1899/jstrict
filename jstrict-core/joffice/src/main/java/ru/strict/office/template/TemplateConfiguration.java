package ru.strict.office.template;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public class TemplateConfiguration {
    private float marginLeft;
    private float marginRight;
    private float marginTop;
    private float marginBottom;

    private String fontFilePath;
    private float fontSize;
    private float fontWeight;
    private int fontStyle;
    private BaseColor fontColor;
    private String encoding;

    private GetSheetType getSheetType;
    private int usageSheetsCount;
    private BorderConfigType borderConfigType;
    private float borderWidth;
    private float widthPercentage;
    private float minPadding;

    public float getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
    }

    public float getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(float marginRight) {
        this.marginRight = marginRight;
    }

    public float getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(float marginTop) {
        this.marginTop = marginTop;
    }

    public float getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
    }

    public String getFontFilePath() {
        return fontFilePath;
    }

    public void setFontFilePath(String fontFilePath) {
        this.fontFilePath = fontFilePath;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public float getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(float fontWeight) {
        this.fontWeight = fontWeight;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public BaseColor getFontColor() {
        return fontColor;
    }

    public void setFontColor(BaseColor fontColor) {
        this.fontColor = fontColor;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public GetSheetType getGetSheetType() {
        return getSheetType;
    }

    public void setGetSheetType(GetSheetType getSheetType) {
        this.getSheetType = getSheetType;
    }

    public int getUsageSheetsCount() {
        return usageSheetsCount;
    }

    public void setUsageSheetsCount(int usageSheetsCount) {
        this.usageSheetsCount = usageSheetsCount;
    }

    public BorderConfigType getBorderConfigType() {
        return borderConfigType;
    }

    public void setBorderConfigType(BorderConfigType borderConfigType) {
        this.borderConfigType = borderConfigType;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public float getWidthPercentage() {
        return widthPercentage;
    }

    public void setWidthPercentage(float widthPercentage) {
        this.widthPercentage = widthPercentage;
    }

    public float getMinPadding() {
        return minPadding;
    }

    public void setMinPadding(float minPadding) {
        this.minPadding = minPadding;
    }

    public enum BorderConfigType {
        /**
         * Система сохраняет форматирование границ из excel-документа
         */
        AUTO,
        /**
         * Система устанавливает строгое форматирование границ
         */
        FIX
    }

    public enum GetSheetType {
        /**
         * Система использует все существующие страницы
         */
        AUTO,
        /**
         * Система использует страницы, указанные в usageSheetsCount
         */
        FIX
    }

    public enum BorderWidth {
        THIN(0.5f),
        HAIR(1f),
        THICK(3.5f);

        private float width;

        BorderWidth(float width) {
            this.width = width;
        }

        public float getWidth() {
            return width;
        }
    }
}
