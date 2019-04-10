package com.techelevator.deleteme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleAndroidController {

	@RequestMapping("/example")
	public Map fetchDataAsJson() {

		// Set up an arbitrary piece of data to return...
		Map<String, Object> json = new HashMap<String, Object>();
		
		List<String> names = new ArrayList<String>();
		names.add("Tom");
		names.add("Ben");
		names.add("Goofus");
		names.add("Gallant");
		
		
		// example of a custom data structure, which can also be included:
		ExamplePerson b = new ExamplePerson();
		
		
		// some garbage, to prove that everything can get JSON-ified
		json.put("key1", "value1");
		json.put("array2", new int[] {1, 2, 3});
		json.put("names", names);
		json.put("person", b);

		
		// we're no longer returning a String as a logical view name
		//   we're just returning an object (that Jackson will serialize into JSON for us)		
		return json;
		//   Navigate to --
		//        http://localhost:8080/m3-java-capstone/api/example/
		//                  to see it
	}
	
}
