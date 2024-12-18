package py.com.parking.models.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import py.com.parking.utils.DateFormat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
        DateTimeFormatter dateTimeFormatter;
        if (getFormat() != null) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(getFormat());
            jsonGenerator.writeString(localDateTime.format(dateTimeFormatter));
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DateFormat.DATE_TIME_WITHOUT_MILISECONDS);
            jsonGenerator.writeString(localDateTime.format(dateTimeFormatter));
        }
    }

    protected String getFormat() {
        return null;
    }
}

