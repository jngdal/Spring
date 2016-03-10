package com.example.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Person;

@Controller
public class WebController {
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/web", method = RequestMethod.GET)
	public String showForm(Person person) {
		return "form";
	}

	@RequestMapping(value = "/web", method = RequestMethod.POST)
	public String checkPersonInfo(@Valid Person person,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				Locale currentLocale = LocaleContextHolder.getLocale();
				System.out.print(currentLocale.toLanguageTag());
				System.out.println(messageSource.getMessage(
						error.getDefaultMessage(), null, currentLocale));
			}

			return "form";
		}
		return "redirect:/results";
	}

}