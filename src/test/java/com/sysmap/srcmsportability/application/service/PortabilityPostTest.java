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
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

@SpringBootTest(
        classes = SrcMsPortabilityApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class PortabilityPostTest {

    @MockBean
    private PortabilityService portabilityService;

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void verifyIfReturnsOkToCorrectInformedInTheActivity() throws Exception {

        Mockito.when(portabilityService.createPortability(this.getMockPortability())).thenReturn(this.getMockPortability());
        Mockito.when(userService.createUser(this.getMockUser())).thenReturn(this.getMockUser());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/ms-src-portability/v1/portability")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(this.getMockPortability()))
                .content(this.mapper.writeValueAsString(this.getMockUser()));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    public void verifyIfReturnsOkWithoutUser() throws Exception {

        Mockito.when(portabilityService.createPortability(this.getMockPortability())).thenReturn(this.getMockPortability());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/ms-src-portability/v1/portability")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(this.getMockPortability()));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    public void verifyIfReturnsOkWithoutPortability() throws Exception {
        Mockito.when(userService.createUser(this.getMockUser())).thenReturn(this.getMockUser());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/ms-src-portability/v1/portability")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(this.getMockUser()));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    public void verifyIfReturnsOkWithoutLineInformation() throws Exception {
        Mockito.when(portabilityService.createPortability(this.getMockPortability())).thenReturn(this.getMockPortability());
        Mockito.when(userService.createUser(this.getMockUserWithoutLineInformation())).thenReturn(this.getMockUserWithoutLineInformation());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/ms-src-portability/v1/portability")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(this.getMockPortability()))
                .content(this.mapper.writeValueAsString(this.getMockUserWithoutLineInformation()));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    public void verifyIfReturnsOkWithoutAddress() throws Exception {
        Mockito.when(portabilityService.createPortability(this.getMockPortability())).thenReturn(this.getMockPortability());
        Mockito.when(userService.createUser(this.getMockUserWithoutAddress())).thenReturn(this.getMockUserWithoutAddress());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/ms-src-portability/v1/portability")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(this.getMockPortability()))
                .content(this.mapper.writeValueAsString(this.getMockUserWithoutAddress()));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    // Novo possível cenário -> se der erro ao cadastrar posso publicar no kafka ?

    private Portability getMockPortability() {
        Portability portability = new Portability();

        portability.setSource(CellPhoneOperator.valueOf("VIVO"));
        portability.setStatus(StatusPortability.valueOf("PROCESSANDO_PORTABILIDADE"));
        portability.setTarget(CellPhoneOperator.valueOf("CLARO"));

        return portability;
    }

    private User getMockUser() {
        User user = new User();
//        user.setDateOfBirth(LocalDate.now());
        user.setDocumentNumber("441558478995");
        user.setName("Jose da Silva");

        LineInformation  lineInformation = new LineInformation();
        lineInformation.setNumber("999999999");
        user.setLine(lineInformation);

        Address address = new Address();
        address.setNumber("XXXXX");
        address.setCity("XXXXX");
        address.setNumber("XXXXX");
        address.setCountry("XXXXX");
        address.setStreet("XXXXX");
        address.setStateOrRegion("XXXXX");
        user.setAddress(address);

        return user;
    }

    private User getMockUserWithoutLineInformation() {
        User user = new User();
//        user.setDateOfBirth(LocalDate.now());
        user.setDocumentNumber("441558478995");
        user.setName("Jose da Silva");

        Address address = new Address();
        address.setNumber("XXXXX");
        address.setCity("XXXXX");
        address.setNumber("XXXXX");
        address.setCountry("XXXXX");
        address.setStreet("XXXXX");
        address.setStateOrRegion("XXXXX");
        user.setAddress(address);

        return user;
    }

    private User getMockUserWithoutAddress() {
        User user = new User();
//        user.setDateOfBirth(LocalDate.now());
        user.setDocumentNumber("441558478995");
        user.setName("Jose da Silva");

        LineInformation  lineInformation = new LineInformation();
        lineInformation.setNumber("999999999");
        user.setLine(lineInformation);

        return user;
    }
}