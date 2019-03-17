package com.example.joy.myFirstSpringBoot.services;

import com.example.joy.myFirstSpringBoot.beans.Birthday;

public interface IBirthdayService {
	public void checkBirthdayIsValid(Birthday bd) ;
	
	public String getBirthDOW(Birthday bd);
	
	public String getChineseZodiac(Birthday bd);

	public  String getStarSign(Birthday bd) ;
}
