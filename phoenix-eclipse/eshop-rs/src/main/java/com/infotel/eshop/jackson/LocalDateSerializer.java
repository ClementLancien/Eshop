package com.infotel.eshop.jackson;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

	protected LocalDateSerializer() {
		super(LocalDate.class);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		// value : valeur a serialisée a l'aide d'un generateur et d'un provider
		generator.writeString(value.toString());
	}

}
