package py.com.parking.producer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.jackson.ObjectMapperCustomizer;
import jakarta.inject.Singleton;
import py.com.parking.models.serialization.LocalDateDeserializer;
import py.com.parking.models.serialization.LocalDateSerializer;
import py.com.parking.models.serialization.LocalDateTimeDeserializer;
import py.com.parking.models.serialization.LocalDateTimeSerializer;
import py.com.parking.models.serialization.LocalTimeDeserializer;
import py.com.parking.models.serialization.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Singleton
public class ObjectMapperFactory implements ObjectMapperCustomizer {
    @Override
    public int priority() {
        return ObjectMapperCustomizer.QUARKUS_CUSTOMIZER_PRIORITY;
    }

    @Override
    public void customize(ObjectMapper objectMapper) {
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SimpleModule customModule = new SimpleModule("ParkingServiceModule",
                new Version(1, 0, 0, null, "py.com.parking", "parking-service"));

        customModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        customModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());

        customModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        customModule.addSerializer(LocalDate.class, new LocalDateSerializer());

        customModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        customModule.addSerializer(LocalTime.class, new LocalTimeSerializer());

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(customModule);
    }
}

