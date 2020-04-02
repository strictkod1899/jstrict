package ru.strict.marshaling;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeDeserializer extends JsonDeserializer<Date> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

	public Date deserialize(JsonParser parser, DeserializationContext ignore) throws IOException {
		String dateString = parser.getText();

		try {
			return this.dateFormat.parse(dateString);
		} catch (ParseException var5) {
			return null;
		}
	}

}
