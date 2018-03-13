package com.visdan.hotel.inventory.controller.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.visdan.hotel.inventory.service.api.v1.DiscoveryService;

@RestController
@RequestMapping(value = "v1/tools")
public class DiscoveryController {

	@Autowired
	private DiscoveryService discoveryService;

	@RequestMapping(value = "/eureka/services", method = RequestMethod.GET)
	public List<String> getEurekaServices() {
		return discoveryService.getEurekaServices();
	}
}
