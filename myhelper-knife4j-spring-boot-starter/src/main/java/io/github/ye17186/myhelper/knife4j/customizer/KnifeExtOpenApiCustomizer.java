package io.github.ye17186.myhelper.knife4j.customizer;

import io.github.ye17186.myhelper.knife4j.aotuconfigure.properties.MhKnife4jProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import lombok.AllArgsConstructor;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;

import java.util.Optional;

/**
 * @author ye17186
 * @since 2023-02-06
 */
@AllArgsConstructor
public class KnifeExtOpenApiCustomizer implements GlobalOpenApiCustomizer {

    private MhKnife4jProperties properties;

    @Override
    public void customise(OpenAPI openApi) {

        Contact contact = Optional.ofNullable(openApi.getInfo().getContact()).orElse(new Contact())
                .name(properties.getAuthor())
                .email(properties.getEmail())
                .url(properties.getUrl());

        openApi.getInfo()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .contact(contact);
    }
}
