package org.pranit.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

	@RequestMapping(produces=MediaType.TEXT_PLAIN_VALUE)
	public String getResoponse(){
		return "hi";
	}
}
