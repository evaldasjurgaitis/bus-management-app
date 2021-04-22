package ej.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer
    jacksonObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            final String dateFormat = "yyyy-MM-dd";
            final String timeFormat = "hh:mm:ss";
            final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss";
            JavaTimeModule module = new JavaTimeModule();
            Jackson2ObjectMapperBuilder.json()
                    .modules(module)
                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .serializers(
                            new LocalDateSerializer(
                                    DateTimeFormatter.ofPattern(dateFormat)))
                    .deserializers(
                            new LocalDateDeserializer(
                                    DateTimeFormatter.ofPattern(dateFormat)))
                    .serializers(
                            new LocalTimeSerializer(
                                    DateTimeFormatter.ofPattern(timeFormat)))
                    .deserializers(
                            new LocalTimeDeserializer(
                                    DateTimeFormatter.ofPattern(timeFormat)))
                    .serializers(
                            new LocalDateTimeSerializer(
                                    DateTimeFormatter.ofPattern(dateTimeFormat)))
                    .deserializers(
                            new LocalDateTimeDeserializer(
                                    DateTimeFormatter.ofPattern(dateTimeFormat)))
                    .serializers(new LocalDateSerializer(
                            DateTimeFormatter.ofPattern(dateFormat)))
                    .build();
        };
    }
}
