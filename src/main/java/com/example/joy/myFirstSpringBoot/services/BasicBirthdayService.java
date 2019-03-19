package com.example.joy.myFirstSpringBoot.services;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;


@Service
public class BasicBirthdayService implements IBirthdayService{
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
	
	@Override
	public LocalDate getValidBirthday(String isoString) {
		if (isoString ==null) {     
			throw new RuntimeException("Must include birthday");
		}
		try {
			LocalDate birthdate = LocalDate.parse(isoString, formatter);
			return birthdate;
		}catch(Exception e ) {
			throw new RuntimeException("Must include valid birthday in yyyy-MM-dd format");
		}
		
	}
	@Override
	public String getBirthDOW(LocalDate bd) {
		return bd.getDayOfWeek().toString();
	}
	@Override
	public String getChineseZodiac(LocalDate bd) {
		int year = bd.getYear();
		switch (year % 12) {
		case 0:
			return "Monkey";
		case 1:
			return "Rooster";
		case 2:
			return "Dog";
		case 3:
			return "Pig";
		case 4:
			return "Rat";
		case 5:
			return "Ox";
		case 6:
			return "Tiger";
		case 7:
			return "Rabbit";
		case 8:
			return "Dragon";
		case 9:
			return "Snake";
		case 10:
			return "Horse";
		case 11:
			return "Sheep";
		}

		return "";

	}
	@Override
	public  String getStarSign(LocalDate bd) {
		int day = bd.getDayOfMonth();
		int month = bd.getMonthValue();

		if (month==12 && day >=22 || month==1 && day < 20) {
			return "Capricorn";
		} else if (month==1 && day>=20 || month==2 && day < 19){
			return "Aquarius";
		}else if (month==2 && day >= 19 || month==3 && day < 21) {
			return "Pisces";
		}else if (month==3 && day >= 21 || month==4 && day < 20) {
			return "Aries";
		}else if (month==4 && day >= 20 || month==5 && day < 21) {
			return "taurus";
		}else if (month==5 && day >= 21 || month==6 && day < 21) {
			return "Gemini";
		}else if (month==6 && day >= 21 || month==7 && day < 23) {
			return "Cancer";
		}else if (month==7 && day >= 23 || month==8 && day < 23) {
			return "Leo";
		}else if (month==8 && day >= 23 || month==9 && day < 23) {
			return "Virgo";
		}else if (month==9 && day >= 23 || month==10 && day < 23) {
			return "Libra";
		}else if (month==10 && day >= 23 || month==11 && day < 22) {
			return "Scorpio";
		}else if (month==11 && day >= 22 || month==12 && day < 22) {
			return "Sagittarius";
		}
		return "";
	}
}
