package py.com.parking.models.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import py.com.parking.utils.DateFormat;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {
    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
        DateTimeFormatter dateTimeFormatter;
        if (getFormat() != null) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(getFormat());
            jsonGenerator.writeString(localDate.format(dateTimeFormatter));
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DateFormat.DATE);
            jsonGenerator.writeString(localDate.format(dateTimeFormatter));
        }
    }
    protected String getFormat() {
        return null;
    }
}

