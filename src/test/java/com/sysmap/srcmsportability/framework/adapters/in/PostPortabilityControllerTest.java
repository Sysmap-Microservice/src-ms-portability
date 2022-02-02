package com.sysmap.srcmsportability.framework.adapters.in;

import com.sysmap.srcmsportability.SrcMsPortabilityApplication;
import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)

@SpringBootTest(
        classes = SrcMsPortabilityApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class PostPortabilityControllerTest {

    @MockBean
    private PortabilityService portabilityService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void verifyIfReturnsOkToCorrectInformedInTheActivity() throws Exception {
        this.makeRequest(this.jsonWithAllInformation());
    }

    @Test
    public void verifyIfReturnsOkWithoutUser() throws Exception {
        this.makeRequest(this.jsonWithoutUser());
    }

    @Test
    public void verifyIfReturnsOkWithoutPortability() throws Exception {
        this.makeRequest(this.jsonWithoutPortability());
    }

    @Test
    public void verifyIfReturnsOkWithoutLineInformation() throws Exception {
        this.makeRequest(this.jsonWithoutLineInformation());
    }

    @Test
    public void verifyIfReturnsOkWithoutAddress() throws Exception {
        this.makeRequest(this.jsonWithoutAddressInformation());
    }

    @Test
    public void verifyIfReturnsOkEmptyJson() throws Exception {
        RequestBuilder requestBuilder = post("/ms-src-portability/v1/portability")
                .accept(MediaType.APPLICATION_JSON)
                .content("")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    // Novo possível cenário -> se der erro ao cadastrar posso publicar no kafka ?

    private void makeRequest(String json) throws Exception {
//        Mockito.when(portabilityService.createPortability(Mockito.any(InputPortability.class))).thenReturn(Mockito.any(Portability.class));
        RequestBuilder requestBuilder = post("/ms-src-portability/v1/portability")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    private String jsonWithAllInformation() {
        return "{\n" +
                "   \"user\":{\n" +
                "      \"line\":{\n" +
                "         \"number\":\"999999999\"\n" +
                "      },\n" +
                "      \"address\":{\n" +
                "         \"street\":\"XXXXX\",\n" +
                "         \"number\":\"XXXXX\",\n" +
                "         \"city\":\"XXXXX\",\n" +
                "         \"country\":\"XXXXX\",\n" +
                "         \"stateOrRegion\":\"XXXX\"\n" +
                "      },\n" +
                "      \"name\":\"Jose da Silva\",\n" +
                "      \"dateOfBirth\":\"1970-01-01\",\n" +
                "      \"documentNumber\":\"441558478995\"\n" +
                "   },\n" +
                "   \"portability\":{\n" +
                "      \"source\":\"VIVO\",\n" +
                "      \"target\":\"CLARO\"\n" +
                "   }\n" +
                "}";
    }

    private String jsonWithoutUser() {

        return "{\n" +
                "   \"portability\":{\n" +
                "      \"source\":\"VIVO\",\n" +
                "      \"target\":\"CLARO\"\n" +
                "   }\n" +
                "}";
    }

    private String jsonWithoutPortability() {
        return "{\n" +
                "   \"user\":{\n" +
                "      \"line\":{\n" +
                "         \"number\":\"999999999\"\n" +
                "      },\n" +
                "      \"address\":{\n" +
                "         \"street\":\"XXXXX\",\n" +
                "         \"number\":\"XXXXX\",\n" +
                "         \"city\":\"XXXXX\",\n" +
                "         \"country\":\"XXXXX\",\n" +
                "         \"stateOrRegion\":\"XXXX\"\n" +
                "      },\n" +
                "      \"name\":\"Jose da Silva\",\n" +
                "      \"dateOfBirth\":\"1970-01-01\",\n" +
                "      \"documentNumber\":\"441558478995\"\n" +
                "   }\n" +
                "}";
    }

    private String jsonWithoutLineInformation() {
        return "{\n" +
                "   \"user\":{\n" +
                "      \"address\":{\n" +
                "         \"street\":\"XXXXX\",\n" +
                "         \"number\":\"XXXXX\",\n" +
                "         \"city\":\"XXXXX\",\n" +
                "         \"country\":\"XXXXX\",\n" +
                "         \"stateOrRegion\":\"XXXX\"\n" +
                "      },\n" +
                "      \"name\":\"Jose da Silva\",\n" +
                "      \"dateOfBirth\":\"1970-01-01\",\n" +
                "      \"documentNumber\":\"441558478995\"\n" +
                "   },\n" +
                "   \"portability\":{\n" +
                "      \"source\":\"VIVO\",\n" +
                "      \"target\":\"CLARO\"\n" +
                "   }\n" +
                "}";
    }

    private String jsonWithoutAddressInformation() {
        return "{\n" +
                "   \"user\":{\n" +
                "      \"line\":{\n" +
                "         \"number\":\"999999999\"\n" +
                "      },\n" +
                "      \"name\":\"Jose da Silva\",\n" +
                "      \"dateOfBirth\":\"1970-01-01\",\n" +
                "      \"documentNumber\":\"441558478995\"\n" +
                "   },\n" +
                "   \"portability\":{\n" +
                "      \"source\":\"VIVO\",\n" +
                "      \"target\":\"CLARO\"\n" +
                "   }\n" +
                "}";
    }
}