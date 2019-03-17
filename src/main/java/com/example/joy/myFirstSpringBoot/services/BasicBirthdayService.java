package com.example.joy.myFirstSpringBoot.services;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import com.example.joy.myFirstSpringBoot.beans.Birthday;


@Service
public class BasicBirthdayService implements IBirthdayService{
	@Override
	public void checkBirthdayIsValid(Birthday bd) {
		if (bd == null) {     
			throw new RuntimeException("Must include birthday");
		}
		if (bd.getDay() < 1 || bd.getDay() > 31 || (bd.getMonth() == 2 && bd.getDay() > 29) || (
				bd.getDay()>28 && bd.getMonth()==2 && (bd.getFourDigitYear()%4 >0 ||bd.getFourDigitYear() %400==0))
				|| ((bd.getMonth() == 4 | bd.getMonth() == 6 || bd.getMonth() == 9 || bd.getMonth() == 11)
						&& bd.getDay() > 30)) {
			throw new RuntimeException("Invalid Day");
		}
		if (bd.getMonth()<1 || bd.getMonth()>12) {
			throw new RuntimeException("Invalid Year");
		}
	}
	@Override
	public String getBirthDOW(Birthday bd) {
		LocalDate d = bd.getBirthDate();
		return d.getDayOfWeek().toString();
	}
	@Override
	public String getChineseZodiac(Birthday bd) {
		int year = bd.getFourDigitYear();
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
	public  String getStarSign(Birthday bd) {
		int day = bd.getDay();
		int month = bd.getMonth();

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
