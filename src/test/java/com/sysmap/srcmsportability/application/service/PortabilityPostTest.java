package com.sysmap.srcmsportability.application.service;

import com.sysmap.srcmsportability.SrcMsPortabilityApplication;
import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.in.UserService;
import com.sysmap.srcmsportability.application.ports.in.entities.Address;
import com.sysmap.srcmsportability.application.ports.in.entities.LineInformation;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.User;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.CellPhoneOperator;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)

@SpringBootTest(
        classes = SrcMsPortabilityApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class PortabilityPostTest {

    @Mock
    private PortabilityService portabilityService;

    @Mock
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    // json enviado de forma correta igual especificado na task - TESTE OK
    @Test
    public void verifyIfReturnsOkToCorrectJsonInformedInTheActivity() throws Exception {
        this.makeRequest(this.getMockInputPortability());
    }

    // se o json for vazio ? - TESTE FALHA
    @Test
    public void verifyIfReturnsOkToEmptyJson() throws Exception {
        this.makeRequest("");
    }

    // se for faltando o usuario ? - TESTE FALHA
    @Test
    public void verifyIfReturnsOkJsonWithoutUser() throws Exception {
        this.makeRequest(this.getMockInputPortabilityWithoutUser());
    }

    // se for faltando a portabilidade ? - TESTE FALHA
    @Test
    public void verifyIfReturnsOkJsonWithoutPortability() throws Exception {
        this.makeRequest(this.getMockInputPortabilityWithoutPortability());
    }

    // se for faltando a linha ? - TESTE OK
    @Test
    public void verifyIfReturnsOkJsonWithoutLine() throws Exception {
        this.makeRequest(this.getMockInputPortabilityWithoutLine());
    }

    // se for faltando o endereço ? - TESTE OK
    @Test
    public void verifyIfReturnsOkJsonWithoutAddress() throws Exception {
        this.makeRequest(this.getMockInputPortabilityWithoutAddress());
    }

    // se o json for outro ? - TESTE FALHA
    @Test
    public void verifyIfReturnsOkDifferentJson() throws Exception {
        this.makeRequest(this.getMockDifferentJson());
    }
    
    // se der erro ao cadastrar posso publicar no kafka ?

    private String getMockInputPortability() {
        String json = "{\n   \"user\":{\n      \"line\":{\n         \"number\":\"999999999\"\n      },\n      \"address\":{\n         \"street\":\"XXXXX\",\n         \"number\":\"XXXXX\",\n         \"city\":\"XXXXX\",\n         \"country\":\"XXXXX\",\n         \"stateOrRegion\":\"XXXX\"\n      },\n      \"name\":\"Jose da Silva\",\n      \"dateOfBirth\":\"1970-01-01\",\n      \"documentNumber\":\"441558478995\"\n   },\n   \"portability\":{\n      \"source\":\"VIVO\",\n      \"target\":\"CLARO\"\n   }\n}";
        return json;
    }

    private String getMockInputPortabilityWithoutUser() {
        String json = "{\n   \"portability\":{\n      \"source\":\"VIVO\",\n      \"target\":\"CLARO\"\n   }\n}";
        return json;
    }

    private String getMockInputPortabilityWithoutPortability() {
        String json = "{\n   \"user\":{\n      \"line\":{\n         \"number\":\"999999999\"\n      },\n      \"address\":{\n         \"street\":\"XXXXX\",\n         \"number\":\"XXXXX\",\n         \"city\":\"XXXXX\",\n         \"country\":\"XXXXX\",\n         \"stateOrRegion\":\"XXXX\"\n      },\n      \"name\":\"Jose da Silva\",\n      \"dateOfBirth\":\"1970-01-01\",\n      \"documentNumber\":\"441558478995\"\n   }}";
        return json;
    }

    private String getMockInputPortabilityWithoutLine() {
        String json = "{\n   \"user\":{\n      \"address\":{\n         \"street\":\"XXXXX\",\n         \"number\":\"XXXXX\",\n         \"city\":\"XXXXX\",\n         \"country\":\"XXXXX\",\n         \"stateOrRegion\":\"XXXX\"\n      },\n      \"name\":\"Jose da Silva\",\n      \"dateOfBirth\":\"1970-01-01\",\n      \"documentNumber\":\"441558478995\"\n   },\n   \"portability\":{\n      \"source\":\"VIVO\",\n      \"target\":\"CLARO\"\n   }\n}";
        return json;
    }

    private String getMockInputPortabilityWithoutAddress() {
        String json = "{\n   \"user\":{\n      \"line\":{\n         \"number\":\"999999999\"\n      },\n   \"name\":\"Jose da Silva\",\n      \"dateOfBirth\":\"1970-01-01\",\n      \"documentNumber\":\"441558478995\"\n   },\n   \"portability\":{\n      \"source\":\"VIVO\",\n      \"target\":\"CLARO\"\n   }\n}";
        return json;
    }

    private String getMockDifferentJson() {
        String json = "\"{\\n   \\\"teste\\\":{\\n     \\\"v1\\\" : \\\"12345\\\",\\n     \\\"v2\\\": \\\"56789\\\"\\n   }\\n}\"";
        return json;
    }

    private Portability getMockPortability() {
        Portability portability = new Portability();
        portability.setSource(CellPhoneOperator.valueOf("VIVO"));
        portability.setStatus(StatusPortability.valueOf("PROCESSANDO_PORTABILIDADE"));
        portability.setTarget(CellPhoneOperator.valueOf("CLARO"));
        return portability;
    }

    private User getMockUser() {
        User user = new User();
        user.setAddress(new Address());
        user.setDateOfBirth(LocalDate.parse("1992-03-16"));
        user.setDocumentNumber("123456789");
        user.setLine(new LineInformation());
        user.setName("Gustavo Riposati");
        return user;
    }

    private void makeRequest(String json) throws Exception {
        Mockito.when(portabilityService.createPortability(this.getMockPortability())).thenReturn(this.getMockPortability());
        Mockito.when(userService.createUser(this.getMockUser())).thenReturn(this.getMockUser());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/ms-src-portability/v1/portability")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
}