package com.techelevator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class NationalParkCLI {

	private static final String MAIN_MENU_OPTION_LIST_PARKS = "View Park List";
	private static final String MAIN_MENU_OPTION_SELECT_PARKS = "Select a Park";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_LIST_PARKS,
			MAIN_MENU_OPTION_SELECT_PARKS, MAIN_MENU_OPTION_EXIT };

	private static final String MAIN_MENU_OPTION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String MAIN_MENU_OPTION_SEARCH_RESERVATIONS = "Search for Available Reservations";
	private static final String MAIN_MENU_OPTION_RETURN_FROM_CAMPGROUNDS = "Return to Main Menu";
	private static final String[] MAIN_MENU_OPTION_CAMPGROUNDS = new String[] { MAIN_MENU_OPTION_VIEW_CAMPGROUNDS,
			MAIN_MENU_OPTION_SEARCH_RESERVATIONS, MAIN_MENU_OPTION_RETURN_FROM_CAMPGROUNDS };

	private static final String MAIN_MENU_OPTION_SEARCH_AVAILABLE_RESERVATIONS = "Search for Available Reservation";
	private static final String MAIN_MENU_OPTION_RETURN_FROM_RESERVATIONS = "Return to Main Menu";
	private static final String MAIN_MENU_OPTION_CREATE_RESERVATION = "Create reservation";	
	private static final String[] MAIN_MENU_OPTION_RESERVATIONS = new String[]{
			MAIN_MENU_OPTION_SEARCH_AVAILABLE_RESERVATIONS, MAIN_MENU_OPTION_RETURN_FROM_RESERVATIONS, MAIN_MENU_OPTION_CREATE_RESERVATION };
		

	private static final String MAIN_MENU_OPTION_MAKE_RESERVATIONS = "Make a Campsite Reservation";
	private static final String MAIN_MENU_OPTION_RETURN_FROM_CREATE_RESERVATIONS = "Return to Main Menu";
	private static final String[] MAIN_MENU_OPTION_CREATE_RESERVATIONS = new String[] {
			MAIN_MENU_OPTION_MAKE_RESERVATIONS, MAIN_MENU_OPTION_RETURN_FROM_CREATE_RESERVATIONS };

	private static final String CONFIRMATION_MESSAGE = "Thanks for your reservation. Here is your confirmation ID";

	private Menu menu;
	private CampgroundDAO campgroundDAO;
	private ParkDAO parkDAO;
	private ReservationDAO reservationDAO;
	private SiteDAO siteDAO;

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/nationalpark");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		NationalParkCLI application = new NationalParkCLI(dataSource);
		application.run();
	}

	public NationalParkCLI(DataSource dataSource) {

		siteDAO = new JDBCsiteDAO(dataSource);
		parkDAO = new JDBCparkDAO(dataSource);
		campgroundDAO = new JDBCcampgroundDAO(dataSource);
		reservationDAO = new JDBCreservationDAO(dataSource);
		this.menu = new Menu(System.in, System.out);
	}

	private void run() {
		while (true) {
			printHeading("Welcome to the National Park System");
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_LIST_PARKS)) {
				handleParkList();
			} else if (choice.equals(MAIN_MENU_OPTION_SELECT_PARKS)) {
				handleSearchParks();
				handleCampgroundMenu();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		}
	}

	private void handleCampgroundMenu() {
		while (true) {
			printHeading("Select an Option");
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTION_CAMPGROUNDS);
			if (choice.equals(MAIN_MENU_OPTION_VIEW_CAMPGROUNDS)) {
				handleCampgroundList();

			} else if (choice.equals(MAIN_MENU_OPTION_SEARCH_RESERVATIONS)) {
				handleSearchReservation();
				handleCreateReservation();
			} else if (choice.equals(MAIN_MENU_OPTION_RETURN_FROM_CAMPGROUNDS)) {
				run();
			}
			
			}
		}

	// Reservations

	private void handleCreateReservation() {
		System.out.println();
		try {
			DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			printHeading("Create Reservation");
			String siteId = getUserInput("What Site Do You Want To Reserve (Enter 0 to Cancel)  : ");
			if (siteId.equals("0")) {
				handleCampgroundMenu();
			}

			Long longsiteid = Long.parseLong(siteId);
			String reservationName = getUserInput("What's the Reservation Name? ");
			String startingDate = getUserInput("What was the arrival Date? (YYYY-MM-DD) ");
			String endingDate = getUserInput("What was the departure Date? (YYYY-MM-DD) ");
			Reservation newReservation = reservationDAO.createReservation(reservationName, longsiteid,
					LocalDate.parse(startingDate, timeformat), LocalDate.parse(endingDate, timeformat));
			System.out.println("\n***" + newReservation.getName() + " created! **** ");
			System.out.println("*** Your Confirmation Number is: " + newReservation.getReservation_id());
		} catch (Exception e) {
			System.out.println("Please enter correct date format: YYYY-MM-DD.");
			handleCreateReservation();
		}
	}

	private void handleSearchReservation() {
		handleCampgroundList();
		try {
			printHeading("Enter Campground Name to Search For Reservation (Enter 0 to Exit): ");
			String campground = getUserInput("");
			if (campground.equals("0")) {
				handleCampgroundMenu();
			}
			String startingDate = getUserInput("What is the arrival Date? (YYYY-MM-DD) ");
			String endingDate = getUserInput("What is the departure Date? (YYYY-MM-DD) ");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			List<Reservation> reservations = reservationDAO.availableReservation(campground,
					LocalDate.parse(startingDate, formatter), LocalDate.parse(endingDate, formatter));
			listAvailableReservations(reservations);
		} catch (Exception e) {
			System.out.println("Please enter correct date format: YYYY-MM-DD.");
			handleCampgroundList();
		}

	}

	private void listAvailableReservations(List<Reservation> reservations) {
		System.out.println();
		System.out.print("Site No. \t \t Max Occup. \t\t Accessible \t\t  Max RV Length \t\t Utility \t\t Cost");
		if (reservations.size() > 0) {
			for (Reservation reservation : reservations) {
				System.out.println();
				System.out.print(reservation.getSite_id() + "\t\t");
				System.out.print(reservation.getOccupancy() + "\t\t");
				System.out.print(reservation.getAccessible() + "\t\t");
				System.out.print(reservation.getMaxRVLength() + "\t\t");
				System.out.print(reservation.getUtilites() + "\t\t");
				System.out.print("$" + reservation.getDailyFee() + "0\t\t");
			}
		}

	}

//Campground list and names
	private void handleCampgroundList() {
		printHeading("Enter a Park Name to Display Campgrounds OR '0' to Exit");
		String parkCamp = getUserInput("");
		if (parkCamp.equals("0")) {
			handleCampgroundMenu();
		}
		List<Campground> allCampgrounds = campgroundDAO.getCampgrounds(parkCamp);
		listCampgroundNames(allCampgrounds);

	}

	private void listCampgroundNames(List<Campground> allCampgrounds) {
		System.out.println();
		System.out.println("Name\t\tOpen\tClose\tDaily Fee");
		if (allCampgrounds.size() > 0) {
			for (Campground campground : allCampgrounds) {
				System.out.println(campground.getName() + "\t" + campground.getOpen_from_mm() + "\t"
						+ campground.getOpen_to_mm() + "\t$" + campground.getDaily_fee() + "0");
			}
		} else {
			System.out.println("\n***NO RESULTS***");
			String input = getUserInput("Enter 'T' to try again OR 'E' to exit to main menu:").toUpperCase();
			if (input.equals("T")) {
				handleCampgroundList();
			} else if (input.equals("E")) {
				run();
			} else {
				System.out.println("Please enter valid input.");
				handleCampgroundList();
			}
		}

	}

	// PARK MENU METHODS

	private void handleParkList() {
		List<Park> allParks = parkDAO.getPark();
		listParkNames(allParks);
	}

	private void listParkNames(List<Park> theParks) {
		System.out.println();
		if (theParks.size() > 0) {
			for (Park park : theParks) {
				System.out.println(park.getName());
			}
		} else {
			System.out.println("\n <<< NOT VALID >>>");
		}

	}

	private void handleSearchParks() {
		printHeading("Select a Park:");
		String parkSearch = getUserInput("");
		List<Park> parks = parkDAO.searchParkByName(parkSearch);
		listParkDetails(parks);

	}

	private void listParkDetails(List<Park> parks) {
		System.out.println();
		if (parks.size() > 0) {
			for (Park park : parks) {
				System.out.println("Name:		" + park.getName());
				System.out.println("Location:	" + park.getLocation());
				System.out.println("Established:	" + park.getEstablish_date());
				System.out.println("Area:		" + park.getArea());
				System.out.println("Visitors:	" + park.getVisitors());
				System.out.println("Description:	" + park.getDescription() + "\n");
			}
		} else {
			System.out.println("<<NOT VALID>>");
			String input = getUserInput("Press 'T' to try again OR 'E' to exit to the Main Menu:").toUpperCase();
			if (input.equals("T")) {
				handleSearchParks();
			} else if (input.equals("E")) {
				run();
			} else {
				System.out.println("Please use a valid input");
				handleSearchParks();
			}
		}
	}

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("_");
		}
		System.out.println();
	}

	private String getUserInput(String prompt) {
		System.out.print(prompt + ">>>");
		return new Scanner(System.in).nextLine();
	}

}
