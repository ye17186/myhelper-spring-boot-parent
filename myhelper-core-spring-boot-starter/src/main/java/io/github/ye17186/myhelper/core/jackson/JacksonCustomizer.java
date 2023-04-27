package io.github.ye17186.myhelper.core.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ye17186
 * @since 2023-02-08
 */
public class JacksonCustomizer implements Jackson2ObjectMapperBuilderCustomizer {

    private final String pattern = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));

        builder.simpleDateFormat(pattern)
                .modules(javaTimeModule, new Jdk8Module())
                .serializationInclusion(JsonInclude.Include.ALWAYS)
                .failOnEmptyBeans(false)
                .failOnUnknownProperties(false);
    }
}
