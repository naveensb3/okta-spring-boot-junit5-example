package com.example.joy.myFirstSpringBoot.beans;

import java.time.LocalDate;

public class Birthday {
	int fourDigitYear; 
	int month; 
	int day; 
	
	public Birthday() {}
	public Birthday(int year, int month,int day) {
		this.fourDigitYear=year;
		this.month=month;
		this.day=day;
	}
	public int getFourDigitYear() {
		return fourDigitYear;
	}
	public void setFourDigitYear(int fourDigitYear) {
		this.fourDigitYear = fourDigitYear;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	public LocalDate getBirthDate() {
		LocalDate d = LocalDate.of(this.getFourDigitYear(), this.getMonth(), this.getDay());
		return d;
	}
	
	
}
