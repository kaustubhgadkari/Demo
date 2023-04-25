package com.example.micorservice.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	CurrencyExchangeRepository currencyExchangeRepository;
	@Autowired
	private Environment environment;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from ,@PathVariable String to) {
	CurrencyExchange currencyExchangeObject=	currencyExchangeRepository.findByFromAndTo(from, to);
	 if(currencyExchangeObject==null) {
		 throw new RuntimeException("unable to find data");
	 }
	 String portNumber =environment.getProperty("local.server.port");
	  currencyExchangeObject.setEnvironment(portNumber);
	  
		 return currencyExchangeObject;
	}
}
