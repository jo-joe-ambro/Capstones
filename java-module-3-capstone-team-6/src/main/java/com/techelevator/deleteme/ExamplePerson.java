package com.techelevator.deleteme;

import java.util.ArrayList;
import java.util.List;

public class ExamplePerson {
	private String firstName;
	private String lastName;
	private int age;
	private List<String> nicknames;

	public ExamplePerson() {
		firstName = "Tom";
		lastName = "Medvitz";
		age = 40;
		
		nicknames = new ArrayList<String>();
		nicknames.add("Tommy");
		nicknames.add("Captain Awesome");
		nicknames.add("World's Best Instructor");
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public List<String> getNicknames() {
		return nicknames;
	}

}
