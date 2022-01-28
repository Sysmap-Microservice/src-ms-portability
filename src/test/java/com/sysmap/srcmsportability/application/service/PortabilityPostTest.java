package com.sysmap.srcmsportability.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.srcmsportability.SrcMsPortabilityApplication;
import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.in.UserService;
import com.sysmap.srcmsportability.application.ports.in.entities.Address;
import com.sysmap.srcmsportability.application.ports.in.entities.LineInformation;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;
import com.sysmap.srcmsportability.application.ports.in.entities.User;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.CellPhoneOperator;
import com.sysmap.srcmsportability.application.ports.in.entities.enums.StatusPortability;
import com.sysmap.srcmsportability.application.ports.out.PortabilityRepository;
import com.sysmap.srcmsportability.application.ports.out.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Mock
    private PortabilityRepository portabilityRepository;

    @Mock
    private UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void contextLoads() {
    }

    public void verificaSeRetornaStatusCriadoAposReceberJsonNoPost() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/ms-src-portability/v1/portability")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(this.getMockInputPortability()));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void verificaSeEstaCadastrandoPortabilidade() {

        Mockito.when(portabilityRepository.savePortability(this.getMockPortability())).thenReturn(this.getMockPortability());
    }

    @Test
    public void verificaSeEstaCadastrandoUsuario() {

        Mockito.when(userRepository.saveUser(this.getMockUser())).thenReturn(this.getMockUser());
    }

    private String getMockInputPortability() {
        String jsonInformadoNaAtividade = "{\n   \"user\":{\n      \"line\":{\n         \"number\":\"999999999\"\n      },\n      \"address\":{\n         \"street\":\"XXXXX\",\n         \"number\":\"XXXXX\",\n         \"city\":\"XXXXX\",\n         \"country\":\"XXXXX\",\n         \"stateOrRegion\":\"XXXX\"\n      },\n      \"name\":\"Jose da Silva\",\n      \"dateOfBirth\":\"1970-01-01\",\n      \"documentNumber\":\"441558478995\"\n   },\n   \"portability\":{\n      \"source\":\"VIVO\",\n      \"target\":\"CLARO\"\n   }\n})";
        return jsonInformadoNaAtividade;
    }

    private Portability getMockPortability() {
        Portability pt = new Portability();
        pt.setSource(CellPhoneOperator.valueOf("VIVO"));
        pt.setStatus(StatusPortability.valueOf("PROCESSANDO_PORTABILIDADE"));
        pt.setTarget(CellPhoneOperator.valueOf("CLARO"));
        return pt;
    }

    private User getMockUser() {
        User user = new User();

        user.setAddress(new Address());
        user.setDateOfBirth(LocalDate.parse("1992-03-16"));
        user.setDocumentNumber("123456789");
        user.setLine(new LineInformation());
        user.setName("Gustavo Riposati");

        return null;
    }
}
