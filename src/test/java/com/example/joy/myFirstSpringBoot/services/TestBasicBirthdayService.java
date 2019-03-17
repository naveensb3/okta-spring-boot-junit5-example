package com.example.joy.myFirstSpringBoot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.joy.myFirstSpringBoot.beans.Birthday;

class TestBasicBirthdayService {
	BasicBirthdayService birthdayService = new BasicBirthdayService();
	
	@Test
	void testGetBirthdayDOW() {
		
		String dow = birthdayService.getBirthDOW(new Birthday(1979,7,14));
		assertEquals("SATURDAY",dow);
		dow = birthdayService.getBirthDOW(new Birthday(2018,1,23));
		assertEquals("TUESDAY",dow);
		dow = birthdayService.getBirthDOW(new Birthday(1972,3,17));
		assertEquals("FRIDAY",dow);
		dow = birthdayService.getBirthDOW(new Birthday(1945,12,2));
		assertEquals("SUNDAY",dow);
		dow = birthdayService.getBirthDOW(new Birthday(2003,8,4));
		assertEquals("MONDAY",dow);
	}
	
	@Test
	void testGetBirthdayChineseSign() {
		String dow = birthdayService.getChineseZodiac(new Birthday(1979,7,14));
		assertEquals("Sheep",dow);
		dow = birthdayService.getChineseZodiac(new Birthday(2018,1,23));
		assertEquals("Dog",dow);
		dow = birthdayService.getChineseZodiac(new Birthday(1972,3,17));
		assertEquals("Rat",dow);
		dow = birthdayService.getChineseZodiac(new Birthday(1945,12,2));
		assertEquals("Rooster",dow);
		dow = birthdayService.getChineseZodiac(new Birthday(2003,8,4));
		assertEquals("Sheep",dow);
	}
	
	@Test
	void testGetBirthdayStarSign() {
		String dow = birthdayService.getStarSign(new Birthday(1979,7,14));
		assertEquals("Cancer",dow);
		dow = birthdayService.getStarSign(new Birthday(2018,1,23));
		assertEquals("Aquarius",dow);
		dow = birthdayService.getStarSign(new Birthday(1972,3,17));
		assertEquals("Pisces",dow);
		dow = birthdayService.getStarSign(new Birthday(1945,12,2));
		assertEquals("Sagittarius",dow);
		dow = birthdayService.getStarSign(new Birthday(2003,8,4));
		assertEquals("Leo",dow);
	}

}
