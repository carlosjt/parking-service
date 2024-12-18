package py.com.parking.models.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import py.com.parking.utils.DateValidator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final String dateFormat = DateValidator.getValidFormat(jsonParser.getText());
        if (dateFormat != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
            return LocalDate.parse(jsonParser.getText(), dateTimeFormatter);
        } else {
            throw new RuntimeException("Error LocalDateDeserializer = "+ jsonParser.getText());
        }
    }
}

