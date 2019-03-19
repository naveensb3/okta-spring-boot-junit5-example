package com.example.joy.myFirstSpringBoot.services;

import java.time.LocalDate;

public interface IBirthdayService {
	public LocalDate getValidBirthday(String bd) ;
	
	public String getBirthDOW(LocalDate bd);
	
	public String getChineseZodiac(LocalDate bd);

	public  String getStarSign(LocalDate bd) ;
}
