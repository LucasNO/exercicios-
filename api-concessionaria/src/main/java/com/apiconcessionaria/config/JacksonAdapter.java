package com.apiconcessionaria.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration
public class JacksonAdapter implements WebMvcConfigurer {

    protected static final String NUMBER = "number";
    protected static final String LAST = "last";
    protected static final String TOTAL_PAGES = "totalPages";
    protected static final String FIRST = "first";
    protected static final String CONTENT = "content";

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return  new Jackson2ObjectMapperBuilder()
                .failOnUnknownProperties(false)
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .serializerByType(Page.class, new JsonPageSerializer());

    }

    public class JsonPageSerializer extends JsonSerializer<Page> {

        @Override
        public void serialize(Page page, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {

            ObjectMapper om=new ObjectMapper()
                    .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                    .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            jsonGen.writeStartObject();
            jsonGen.writeFieldName(NUMBER);
            jsonGen.writeNumber(page.getNumber());
            jsonGen.writeFieldName(LAST);
            jsonGen.writeBoolean(page.isLast());
            jsonGen.writeFieldName(TOTAL_PAGES);
            jsonGen.writeNumber(page.getTotalPages());
            jsonGen.writeFieldName(FIRST);
            jsonGen.writeBoolean(page.isFirst());
            jsonGen.writeFieldName(CONTENT);
            jsonGen.writeRawValue(om.writerWithView(serializerProvider.getActiveView())
                    .writeValueAsString(page.getContent()));
            jsonGen.writeEndObject();
        }

    }
}
