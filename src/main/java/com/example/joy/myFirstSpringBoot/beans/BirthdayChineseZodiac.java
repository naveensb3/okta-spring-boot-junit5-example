package com.example.joy.myFirstSpringBoot.beans;

public class BirthdayChineseZodiac {
	String birthday;
	String chineseZodiacSign;
	public BirthdayChineseZodiac() {
		//needed for bean deserialization
	}
	
	public BirthdayChineseZodiac(Birthday b, String sign) {
		birthday = b.getBirthDate().toString();
		chineseZodiacSign=sign;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String bd) {
		this.birthday = bd;
	}
	public String getChineseZodiacSign() {
		return chineseZodiacSign;
	}
	public void setChineseZodiacSign(String chineseZodiacSign) {
		this.chineseZodiacSign = chineseZodiacSign;
	}
	
}
