package com.algafood;

import com.algafood.config.PayloadExtractor;
import com.algafood.core.util.MessageHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AlgafoodApiApplicationTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected MessageHelper messageHelper;

    protected PayloadExtractor payloadExtractor;
    @SuppressWarnings("rawtypes")
    protected HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    protected ObjectMapper jsonMapper;

    @BeforeEach
    public void init() {
        payloadExtractor = new PayloadExtractor(jsonMapper);
    }

    @Autowired
    protected void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);
    }

    @SuppressWarnings("unchecked")
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
