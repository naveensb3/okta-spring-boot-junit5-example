package com.example.joy.myFirstSpringBoot.services;

import java.time.LocalDate;

public interface BirthdayService {
    LocalDate getValidBirthday(String birthdayString) ;

    String getBirthDOW(LocalDate birthday);

    String getChineseZodiac(LocalDate birthday);

    String getStarSign(LocalDate birthday) ;
    
    String getStarSignOne(LocalDate birthday) ;
}
