package com.example.joy.myFirstSpringBoot.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.joy.myFirstSpringBoot.services.IBirthdayService;

@Controller
@RequestMapping("/birthday")
public class BirthdayInfoController {
	IBirthdayService birthdayService;

	@Autowired 
	BirthdayInfoController(IBirthdayService birthdayService){
		this.birthdayService = birthdayService;
	}
	
	@PostMapping("/dayOfWeek")
	public ResponseEntity<String> getDayOfWeek(@RequestBody String birthdayString) {
		LocalDate birthday = birthdayService.getValidBirthday(birthdayString);

		String dow = birthdayService.getBirthDOW(birthday);
		
		return new ResponseEntity<String>(dow, HttpStatus.OK);

	}

	@PostMapping("/chineseZodiac")
	public ResponseEntity<String> getChineseZodiac(@RequestBody String birthdayString) {
		LocalDate birthday = birthdayService.getValidBirthday(birthdayString);
		String sign = birthdayService.getChineseZodiac(birthday);
		
		return new ResponseEntity<String>(sign, HttpStatus.OK);
	}

	@PostMapping("/starSign")
	public ResponseEntity<String> getStarSign(@RequestBody  String birthdayString) {
		LocalDate birthday = birthdayService.getValidBirthday(birthdayString);
		String sign = birthdayService.getStarSign(birthday);
		
		return new ResponseEntity<String>(sign, HttpStatus.OK);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {

		return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

}
