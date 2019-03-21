package com.example.joy.myFirstSpringBoot.services;

import java.time.LocalDate;

public interface IBirthdayService {
	public LocalDate getValidBirthday(String birthdayString) ;
	
	public String getBirthDOW(LocalDate birthday);
	
	public String getChineseZodiac(LocalDate birthday);

	public  String getStarSign(LocalDate birthday) ;
}
