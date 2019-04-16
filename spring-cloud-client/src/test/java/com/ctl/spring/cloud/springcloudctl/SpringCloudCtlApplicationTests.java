package com.ctl.spring.cloud.springcloudctl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringCloudCtlApplicationTests {
    @Autowired
	private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Before
    public void setupMockMvc(){
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void contextLoads() {
		try {
//			mockMvc.perform(MockMvcRequestBuilders.get("uuid"))
//					.andExpect(status().isOk())
//					.andExpect(MockMvcResultMatchers.view().name("/uuid"));

			MvcResult mvcResult = mockMvc.perform(get("/json2").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.content("{\"code\" : \"EXW\", \"description\" : \"code exw\", \"locationQualifier\" : \"DEPARTURE\"}".getBytes()))
					//.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("uuid").exists()).andReturn();
					//.andExpect(jsonPath("uuid.value").value("6305ff33-295e-11e5-ae37-54ee7534021a"))
					//.andExpect(jsonPath("code").value("EXW"));
			String contentAsString = mvcResult.getResponse().getContentAsString();
			System.out.println(contentAsString);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

