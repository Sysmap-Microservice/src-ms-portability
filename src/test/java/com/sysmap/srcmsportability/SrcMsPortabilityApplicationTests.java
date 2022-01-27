package com.sysmap.srcmsportability;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.srcmsportability.application.ports.in.PortabilityService;
import com.sysmap.srcmsportability.application.ports.in.UserService;
import com.sysmap.srcmsportability.framework.adapters.in.dto.InputPortability;
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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = SrcMsPortabilityApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc

class SrcMsPortabilityApplicationTests {

	@Mock
	private PortabilityService portabilityService;

	@Mock
	private UserService userService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	void contextLoads() {
	}

	public void testInsert() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/ms-src-portability/v1/portability")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(this.getMockInputPortability()));

		mockMvc.perform(mockRequest)
				.andExpect(status().isCreated());
	}

	private String getMockInputPortability() {

		String json = "{\n   \"user\":{\n      \"line\":{\n         \"number\":\"999999999\"\n      },\n      \"address\":{\n         \"street\":\"XXXXX\",\n         \"number\":\"XXXXX\",\n         \"city\":\"XXXXX\",\n         \"country\":\"XXXXX\",\n         \"stateOrRegion\":\"XXXX\"\n      },\n      \"name\":\"Jose da Silva\",\n      \"dateOfBirth\":\"1970-01-01\",\n      \"documentNumber\":\"441558478995\"\n   },\n   \"portability\":{\n      \"source\":\"VIVO\",\n      \"target\":\"CLARO\"\n   }\n})";
		return json;
	}

}
