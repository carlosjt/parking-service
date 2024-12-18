package py.com.parking.models.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import py.com.parking.utils.DateValidator;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, IOException {
        final String timeFormat = DateValidator.getValidFormat(jsonParser.getText());
        if (timeFormat != null) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
            return LocalTime.parse(jsonParser.getText(), timeFormatter);
        } else {
            throw new RuntimeException("Error LocalTimeDeserializer = " + jsonParser.getText());
        }
    }
}

