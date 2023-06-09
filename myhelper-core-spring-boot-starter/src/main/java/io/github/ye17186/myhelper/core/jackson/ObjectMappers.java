package io.github.ye17186.myhelper.core.jackson;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author ye17186
 * @since 2023-02-08
 */
public class ObjectMappers {

    public static Jackson2ObjectMapperBuilder builder() {

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        customizer().customize(builder);
        return builder;
    }

    private static JacksonCustomizer customizer() {

        return new JacksonCustomizer();
    }
}
