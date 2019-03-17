package com.example.joy.myFirstSpringBoot.beans;

public class BirthdayDOW {
	String birthday;
	String dayOfWeek;
	public BirthdayDOW() {
		//needed for bean deserialization
	}
	public BirthdayDOW(Birthday b, String dayOfWeek) {
		this.dayOfWeek=dayOfWeek;
		this.birthday = b.getBirthDate().toString();
	}

	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
}
