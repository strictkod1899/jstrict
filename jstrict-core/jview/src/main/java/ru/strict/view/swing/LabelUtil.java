package ru.strict.view.swing;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LabelUtil {

    public String wrapText(String text, int wrapStep) {
        var wrapIndex = 0;
        var textLength = text.length();

        var wrappedText = new StringBuilder("<html>");
        while (wrapIndex < textLength) {
            var newWrapIndex = Math.min((wrapIndex + wrapStep), textLength);
            wrappedText.append(text, wrapIndex, newWrapIndex)
                    .append("<p>");
            wrapIndex = newWrapIndex;
        }
        wrappedText.append("</html>");

        return wrappedText.toString();
    }
}
