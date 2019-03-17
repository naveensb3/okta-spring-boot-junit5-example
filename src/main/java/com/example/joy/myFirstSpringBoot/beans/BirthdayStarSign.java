package com.example.joy.myFirstSpringBoot.beans;

public class BirthdayStarSign {
	String birthday;
	String starSign;
	public BirthdayStarSign() {
		//needed for bean deserialization
	}
	public BirthdayStarSign(Birthday b, String sign) {
		birthday = b.getBirthDate().toString();
		starSign=sign;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String bd) {
		this.birthday = bd;
	}
	public String getStarSign() {
		return starSign;
	}
	public void setStarSign(String starSign) {
		this.starSign = starSign;
	}
	
}
