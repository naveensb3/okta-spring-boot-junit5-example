package com.example.joy.myFirstSpringBoot.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.joy.myFirstSpringBoot.SpringBootRestApiApplication;
import com.example.joy.myFirstSpringBoot.beans.Birthday;
import com.example.joy.myFirstSpringBoot.beans.BirthdayChineseZodiac;
import com.example.joy.myFirstSpringBoot.beans.BirthdayDOW;
import com.example.joy.myFirstSpringBoot.beans.BirthdayStarSign;
import com.example.joy.myFirstSpringBoot.services.BasicBirthdayService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { ITTestBirthdayInfoController.TestResourceServerConfiguration.class,
		SpringBootRestApiApplication.class })
@AutoConfigureMockMvc
@WebMvcTest(controllers= {BirthdayInfoController.class,BasicBirthdayService.class})
class ITTestBirthdayInfoController {
	private final static String TEST_USER_ID = "user-id-123";
	private static ObjectMapper mapper = new ObjectMapper();
	String bd1 = getJSONString(new Birthday(1979,7,14));
	String bd2 = getJSONString(new Birthday(2018,1,23));
	String bd3 = getJSONString(new Birthday(1972, 3, 17));
	String bd4 = getJSONString(new Birthday(1945, 12, 2));
	String bd5 = getJSONString(new Birthday(2003, 8, 4));
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
			result = mockMvc.perform(MockMvcRequestBuilders.post("/birthday/dayOfWeek").content(birthday)
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
			 		.andReturn();
			
			BirthdayDOW resultDOW = mapper.readValue(result.getResponse().getContentAsString(),BirthdayDOW.class);
	        assertNotNull(resultDOW);
	        assertEquals(dow, resultDOW.getDayOfWeek());
		} catch (Exception e) {
			fail();
		}
	}
	
	private void testZodiak(String birthday, String czs) {
		MvcResult result;
		try {
			result = mockMvc.perform(MockMvcRequestBuilders.post("/birthday/chineseZodiac").content(birthday)
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
			 		.andReturn();
			
			BirthdayChineseZodiac resultCZ = mapper.readValue(result.getResponse().getContentAsString(),BirthdayChineseZodiac.class);
	        assertNotNull(resultCZ);
	        assertEquals(czs, resultCZ.getChineseZodiacSign());
		} catch (Exception e) {
			fail();
		}
	}
	
	private void testStarSign(String birthday, String ss) {
		MvcResult result;
		try {
			result = mockMvc.perform(MockMvcRequestBuilders.post("/birthday/starSign").content(birthday)
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
			 		.andReturn();
			
			BirthdayStarSign resultSS = mapper.readValue(result.getResponse().getContentAsString(),BirthdayStarSign.class);
	        assertNotNull(resultSS);
	        assertEquals(ss, resultSS.getStarSign());
		} catch (Exception e) {
			fail();
		}
	}
	
	public static String getJSONString(final Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@EnableResourceServer
	public static class TestResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(ResourceServerSecurityConfigurer security) {
			security.stateless(false);
		}
	}
}
