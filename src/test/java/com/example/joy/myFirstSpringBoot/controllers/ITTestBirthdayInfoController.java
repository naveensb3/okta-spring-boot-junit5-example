package com.example.joy.myFirstSpringBoot.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import com.example.joy.myFirstSpringBoot.SpringBootRestApiApplication;
import com.example.joy.myFirstSpringBoot.services.BasicBirthdayService;

@ContextConfiguration(classes = { ITTestBirthdayInfoController.TestResourceServerConfiguration.class,
		SpringBootRestApiApplication.class })
@AutoConfigureMockMvc
@WebMvcTest(controllers= {BirthdayInfoController.class,BasicBirthdayService.class})
class ITTestBirthdayInfoController {
	private final static String TEST_USER_ID = "user-id-123";
	String bd1 = LocalDate.of(1979,7,14).format(DateTimeFormatter.ISO_DATE);
	String bd2 = LocalDate.of(2018,1,23).format(DateTimeFormatter.ISO_DATE);
	String bd3 = LocalDate.of(1972, 3, 17).format(DateTimeFormatter.ISO_DATE);
	String bd4 = LocalDate.of(1945, 12, 2).format(DateTimeFormatter.ISO_DATE);
	String bd5 = LocalDate.of(2003, 8, 4).format(DateTimeFormatter.ISO_DATE);
    @Autowired
	private  MockMvc mockMvc;

  
	
	@Test
	@WithMockUser(username = TEST_USER_ID)
	public void testGetBirthdayDOW() throws Exception {
		testDOW(bd1,"SATURDAY");
		testDOW(bd2,"TUESDAY");
		testDOW(bd3,"FRIDAY");
		testDOW(bd4,"SUNDAY");
		testDOW(bd5,"MONDAY");
	}

	@Test
	@WithMockUser(username = TEST_USER_ID)
	public void testGetBirthdayChineseSign() throws Exception {
		testZodiak(bd1,"Sheep");
		testZodiak(bd2,"Dog");
		testZodiak(bd3,"Rat");
		testZodiak(bd4,"Rooster");
		testZodiak(bd5,"Sheep");
	}
	
	@Test
	@WithMockUser(username = TEST_USER_ID)
	public void testGetBirthdaytestStarSign() throws Exception {
		testStarSign(bd1,"Cancer");
		testStarSign(bd2,"Aquarius");
		testStarSign(bd3,"Pisces");
		testStarSign(bd4,"Sagittarius");
		testStarSign(bd5,"Leo");
	}

	private void testDOW(String birthday, String dow) {
		MvcResult result;
		try {
			result = mockMvc.perform(MockMvcRequestBuilders.post("/birthday/dayOfWeek").with(security()).content(birthday)
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
			 		.andReturn();
			
			String resultDOW = result.getResponse().getContentAsString();
	        assertNotNull(resultDOW);
	        assertEquals(dow, resultDOW);
		} catch (Exception e) {
			fail();
		}
	}
	
	private void testZodiak(String birthday, String czs) {
		MvcResult result;
		try {
			result = mockMvc.perform(MockMvcRequestBuilders.post("/birthday/chineseZodiac").with(security()).content(birthday)
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
			 		.andReturn();
			
			String resultCZ =result.getResponse().getContentAsString();
	        assertNotNull(resultCZ);
	        assertEquals(czs, resultCZ);
		} catch (Exception e) {
			fail();
		}
	}
	
	private void testStarSign(String birthday, String ss) {
		MvcResult result;
		try {
			result = mockMvc.perform(MockMvcRequestBuilders.post("/birthday/starSign").with(security()).content(birthday)
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
			 		.andReturn();
			
			 String resultSS = result.getResponse().getContentAsString();
	        assertNotNull(resultSS);
	        assertEquals(ss, resultSS);
		} catch (Exception e) {
			fail();
		}
	}
	public static RequestPostProcessor security() {
        return SecurityMockMvcRequestPostProcessors.securityContext(SecurityContextHolder.getContext());
}

	@EnableResourceServer
	public static class TestResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(ResourceServerSecurityConfigurer security) {
			security.stateless(false);
		}
	}
	
}
