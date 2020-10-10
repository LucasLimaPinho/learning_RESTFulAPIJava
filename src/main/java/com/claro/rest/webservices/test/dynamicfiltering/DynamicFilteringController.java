package com.claro.rest.webservices.test.dynamicfiltering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class DynamicFilteringController {
	
	@GetMapping("/dynamicfiltering")
	public MappingJacksonValue dynamicFiltering() {
		
		SomeOtherBean someOtherBean = new SomeOtherBean("value1","value2","value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1","field2");
				
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someOtherBean);
		mapping.setFilters(filters);
		
		return mapping;
		
	}

}
