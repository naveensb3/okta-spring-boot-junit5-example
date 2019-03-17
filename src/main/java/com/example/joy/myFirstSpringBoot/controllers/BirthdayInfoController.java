package com.example.joy.myFirstSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.joy.myFirstSpringBoot.beans.Birthday;
import com.example.joy.myFirstSpringBoot.beans.BirthdayChineseZodiac;
import com.example.joy.myFirstSpringBoot.beans.BirthdayDOW;
import com.example.joy.myFirstSpringBoot.beans.BirthdayStarSign;
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
	public ResponseEntity<BirthdayDOW> getDayOfWeek(@RequestBody Birthday bd) {
		birthdayService.checkBirthdayIsValid(bd);

		String dow = birthdayService.getBirthDOW(bd);
		BirthdayDOW bdow = new BirthdayDOW(bd, dow);

		return new ResponseEntity<BirthdayDOW>(bdow, HttpStatus.OK);

	}

	@PostMapping("/chineseZodiac")
	public ResponseEntity<BirthdayChineseZodiac> getChineseZodiac(@RequestBody Birthday bd) {
		birthdayService.checkBirthdayIsValid(bd);
		String sign = birthdayService.getChineseZodiac(bd);
		BirthdayChineseZodiac bsign = new BirthdayChineseZodiac(bd, sign);

		return new ResponseEntity<BirthdayChineseZodiac>(bsign, HttpStatus.OK);
	}

	@PostMapping("/starSign")
	public ResponseEntity<BirthdayStarSign> getStarSign(@RequestBody Birthday bd) {
		birthdayService.checkBirthdayIsValid(bd);
		String sign = birthdayService.getStarSign(bd);
		BirthdayStarSign bsign = new BirthdayStarSign(bd, sign);

		return new ResponseEntity<BirthdayStarSign>(bsign, HttpStatus.OK);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {

		return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

}
