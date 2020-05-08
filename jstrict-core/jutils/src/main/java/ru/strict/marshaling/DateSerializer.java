package ru.strict.marshaling;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer extends JsonSerializer<Date> {

	private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

	public void serialize(Date value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		String dateString = null;
		if (value != null) {
			dateString = this.dateFormat.format(value);
		}

		generator.writeString(dateString);
	}

}
