package com.stackroute.goingPlaces.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpAndRunning implements ErrorController{
	@RequestMapping("/")
	public String home() {
        return "Up and Running";
    }
	@RequestMapping("/error")
	public String err() {
        return "Illegal url. If you are trying to access GoingPlacesRestful API go to /swagger-ui.html";
    }
	@Override
	public String getErrorPath() {
		return "/error";
	}
}
