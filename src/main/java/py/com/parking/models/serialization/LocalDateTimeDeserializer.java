package py.com.parking.models.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import py.com.parking.utils.DateValidator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, IOException {
        final String dateTimeFormat = DateValidator.getValidFormat(jsonParser.getText());
        if (dateTimeFormat != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
            return LocalDateTime.parse(jsonParser.getText(), dateTimeFormatter);
        } else {
            throw new RuntimeException("Error LocalDateTimeDeserializer = " + jsonParser.getText());
        }
    }
}

