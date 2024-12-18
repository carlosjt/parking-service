package py.com.parking.models.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import py.com.parking.utils.DateFormat;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeSerializer extends JsonSerializer<LocalTime> {
    @Override
    public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
        DateTimeFormatter timeFormatter;
        if (getFormat() != null) {
            timeFormatter = DateTimeFormatter.ofPattern(getFormat());
            jsonGenerator.writeString(localTime.format(timeFormatter));
        } else {
            timeFormatter = DateTimeFormatter.ofPattern(DateFormat.TIME);
            jsonGenerator.writeString(localTime.format(timeFormatter));
        }
    }
    protected String getFormat() {
        return null;
    }
}

