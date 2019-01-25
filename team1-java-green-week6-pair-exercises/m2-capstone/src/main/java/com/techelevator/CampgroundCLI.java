package com.techelevator;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.sql.DataSource;
import javax.swing.JTextArea;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;

import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;
import com.techelevator.campground.model.jdbc.JDBCReservationDAO;

import com.techelevator.campground.view.Menu;




public class CampgroundCLI {


	private static final String MAIN_MENU_OPTION_ALL_PARKS = "Select a Park for Further Details";
	private static final String MAIN_MENU_OPTION_EXIT = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] {MAIN_MENU_OPTION_ALL_PARKS,
			MAIN_MENU_OPTION_EXIT};

	private static final String VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String SEARCH_FOR_RESERVATION = "Search for Reservation";
	private static final String RETURN_TO_PREV_SCREEN = "Return to Previous Screen";	
	private static final String[] PARK_MENU_OPTIONS = new String[] {VIEW_CAMPGROUNDS,
			SEARCH_FOR_RESERVATION, RETURN_TO_PREV_SCREEN};
	private static final String SEARCH_FOR_AVAIL_RESERVATION = "Search for Available Reservation";
	private static final String[] RESERVATION_MENU_OPTIONS = new String[] {SEARCH_FOR_AVAIL_RESERVATION,
			RETURN_TO_PREV_SCREEN};
	private static final String WHICH_CAMPGROUND = "Which campground (enter 0 to cancel)?";	
	private static final String ARRIVAL_DATE = "What is the arrival date(Enter as MM/DD/YYYY)?";	
	private static final String DEPARTURE_DATE = "What is the departure date(Enter as MM/DD/YYYY)?";	
	private static final String SITE_TO_RESERVE = "Which site should be reserved (enter 0 to cancel)?";
	private static final String RESERVATION_NAME = "What name should the reservation be made under?";
	private static final String ENTER_RESERVATION = "Enter Reservation Id: ";	


	private Menu menu;
	private ParkDAO parkDAO;
	private ReservationDAO reservationDAO;

	private CampgroundDAO campgroundDAO;
	private PrintWriter out;
	private Scanner in;
	private Date arrivalDate, departureDate;
	private boolean campGroundInd = true, invalidSiteSelection = true, campReservationInd = true, parkCampInfoInd = true;



	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		this.menu = new Menu(System.in, System.out);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		parkDAO = new JDBCParkDAO(dataSource);
		campgroundDAO = new JDBCCampgroundDAO(dataSource);	
		reservationDAO = new JDBCReservationDAO(dataSource);
		//siteDAO = new JDBCSiteDAO(dataSource);
		this.out = new PrintWriter(System.out);
		this.in = new Scanner(System.in);


	}
	public void run() {
		while(true) {
			campGroundInd = true;
			//	invalidSiteSelection =  true;
			//	campReservationInd = true;
			//	parkCampInfoInd = true;

			printHeading("VIEW PARKS INTERFACE");
			printHeading("Select a Park for Further Detail");

			Park parkRec;
			List<Park> parkRecList = parkDAO.getAllParks();
			String[] parkNameArray =  getParknames(parkRecList)	;	

			//VIEW PARKS INTERFACE
			String mainChoice = (String)menu.getChoiceFromOptions(parkNameArray);
			parkRec = getPark(parkRecList, mainChoice);

			if (parkRec != null) {

				while (campGroundInd) {
					parkCampInfoInd = true;
					//PARK INFORMATION SCREEN
					String parkChoice = displayParkInformation(parkRec);

					if (parkChoice.equals(VIEW_CAMPGROUNDS)) {
						List<Campground> campRecList = getListofCampsByParkID(parkRec.getId());
						while (parkCampInfoInd) {
							campReservationInd = true;
							//PARK CAMPGROUNDS INFORMATION & COMMAND
							String reserveChoice = displayCampGroundInformation(campRecList);

							if (reserveChoice.equals(SEARCH_FOR_AVAIL_RESERVATION)) {
								while(campReservationInd) {
									invalidSiteSelection = true;
									//PARK CAMPGROUNDS INFORMATION 
									printListOfCamps(campRecList);
									try {
										//WHICH CAMPGROUND QUESTION
										int selectedUserOption = getInputCampground(campRecList);
										if (selectedUserOption > 0) {
											Campground campRec = getCampground(campRecList,selectedUserOption);
											//Get & Validate Two Dates before proceeding
											if (!areDatesInvalid()) {
												List<Reservation> availReserveList = getReservationByRange(arrivalDate,departureDate,campRec.getId());
												while (invalidSiteSelection) {
													//VIEW RESULTS MATCHING SEARCH CRITERIA
													printListOfReservations(availReserveList);
													//SITE TO RESERVE QUESTION
													System.out.println(SITE_TO_RESERVE);

													String siteToReserve = in.nextLine();
													long siteToReserveInput = Long.valueOf(siteToReserve);

													if (siteToReserveInput > 0) {
														String reservationName = getName();
														long siteID = reservationDAO.getSiteID(siteToReserveInput, campRec.getId());
														createReservation(siteID, reservationName,arrivalDate,departureDate);
														campGroundInd = false;
														invalidSiteSelection = false;
														campReservationInd = false;
														parkCampInfoInd = false;
													}
													else if (siteToReserveInput < 0)
														System.out.println("Invalid Site Number entered"); 

													else if (siteToReserveInput == 0)
														invalidSiteSelection = false;

												}
											}
										} else if (selectedUserOption == 0)
											campReservationInd = false;

										else if (selectedUserOption < 0)
											System.out.println("Invalid Option entered");


									} catch(NumberFormatException e) {
										// eat the exception, an error message will be displayed below since choice will be null
									}

								}
							}
							else if (reserveChoice.equals(RETURN_TO_PREV_SCREEN)){
								parkCampInfoInd = false;
								continue;

							}

						}
					}
					else if(parkChoice.equals(SEARCH_FOR_RESERVATION)) {
						int reservationID = getInputReservation(parkRec);
						List<Reservation> confirmedReserveList = reservationDAO.getReservationById(reservationID,parkRec.getId());
						printConfirmedReserve(confirmedReserveList);
					}	
					else if(parkChoice.equals(RETURN_TO_PREV_SCREEN)) {
						campGroundInd = false;
					}					
				}

			}

			else if(mainChoice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}				
		}
	}

	private String getName() {
		boolean checkFlag = true;
		String reservationName = null; 
		while (checkFlag) {
			try {
				System.out.println(RESERVATION_NAME);
				reservationName = in.nextLine();
				if (reservationName.trim().isEmpty())
					System.out.println("Invalid Name Entered");
				else
					checkFlag = false;
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Invalid Name Entered; Please enter again");

			}	
		}

		return reservationName;
	}

	private boolean areDatesInvalid() {
		boolean areDatesInvalid = true;

		while (areDatesInvalid) {
			//GET ARRIVAL DATE
			arrivalDate = getArrivalDate();
			//GET DEPARTURE DATE
			departureDate = getDepartureDate();
			//DATE VALIDATION
			if (arrivalDate.after(departureDate)) {
				System.out.println("Please reenter Dates");
			}
			else if (arrivalDate.compareTo(departureDate) == 0)
				System.out.println("Please reenter Dates");
			else 
				areDatesInvalid = false;
		}	
		return areDatesInvalid;

	}
	private void printConfirmedReserve(List<Reservation> confirmedReserveList) {
		System.out.println();
		if(confirmedReserveList.size() > 0) {
			for(Reservation reservation: confirmedReserveList) {
				System.out.println("Camp Name: " + reservation.getCampName());
				System.out.println("Name: " + reservation.getName());
				System.out.println("Arrival Date:" + new SimpleDateFormat("MM/dd/yyyy").format(reservation.getFromDate()));
				System.out.println("Departure Date:" + new SimpleDateFormat("MM/dd/yyyy").format(reservation.getToDate()));	
			}
		} else {
			System.out.println("\n*** Reservation Not Found ***");
		}
	}		
	private int getInputReservation(Park parkRec) {
		int enteredReservation = 0;
		boolean checkFlag = true;
		System.out.println(ENTER_RESERVATION);
		while (checkFlag == true) {
			String userInput = in.nextLine();
			try {
				enteredReservation = Integer.valueOf(userInput);
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid Reservation Number Entered; Please enter again");
				continue;
			}
			checkFlag = false;
		}
		return enteredReservation;
	}	
	private Date getDepartureDate() {
		Date suppliedDate = null;
		boolean checkFlag = true;

		while (checkFlag) {
			try {
				System.out.println(" ");
				System.out.println(DEPARTURE_DATE);
				String inputDate = in.nextLine();
				checkFlag = isDateInvalid(inputDate);
				if (!checkFlag) 
					suppliedDate = (new SimpleDateFormat("MM/dd/yyyy").parse(inputDate));
				else
					System.out.println("Accepting reservations up to a year! Please enter valid date");

			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Accepting reservations up to a year! Please enter valid date");

			}	
		}

		return suppliedDate;
	}

	private boolean isDateInvalid(String date) 
	{   boolean responseInd = true;
	Date suppliedDate;
	final String DATE_FORMAT = "MM/dd/yyyy";
	Calendar currentCal = Calendar.getInstance(); 
	try {
		currentCal.set(Calendar.HOUR_OF_DAY, 0);
		currentCal.set(Calendar.MINUTE, 0);
		currentCal.set(Calendar.SECOND, 0);
		currentCal.set(Calendar.MILLISECOND, 0);
		Date currentDateWithoutTime = currentCal.getTime();	
		currentCal.set(Calendar.MONTH, (currentCal.get(Calendar.MONTH)+12));
		Date nextYearDateWithoutTime = currentCal.getTime();
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		df.setLenient(false);
		df.parse(date);	
		suppliedDate = (new SimpleDateFormat("MM/dd/yyyy").parse(date));
		if (suppliedDate.compareTo(currentDateWithoutTime) >= 0 &&  (suppliedDate.compareTo(nextYearDateWithoutTime) <= 0))
			responseInd = false;

	} catch (ParseException e) {
		responseInd = true;
	}
	return responseInd;
	}	
	private Date getArrivalDate() {
		Date suppliedDate = null;
		boolean checkFlag = true;

		while (checkFlag) {
			try {
				System.out.println(" ");
				System.out.println(ARRIVAL_DATE);
				String inputDate = in.nextLine();
				checkFlag = isDateInvalid(inputDate);
				if (!checkFlag) 
					suppliedDate = (new SimpleDateFormat("MM/dd/yyyy").parse(inputDate));	
				else
					System.out.println("Accepting reservations up to a year! Please enter valid date");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Accepting reservations up to a year! Please enter valid date");
			}	
		}

		return suppliedDate;
	}

	private int getInputCampground(List<Campground> campRecList) {
		int selectedUserOption = 0;
		boolean checkFlag = true;
		System.out.println(WHICH_CAMPGROUND);
		while (checkFlag == true) {
			String userInput = in.nextLine();
			try {
				selectedUserOption = Integer.valueOf(userInput);
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid Option Selected; Please enter again");
			}

			if (campRecList.size() == 0) {
				System.out.println("Invalid Option Selected; Please enter again");
				continue;
			}		
			else if ((campRecList.size() > 0) && (selectedUserOption > campRecList.size())) {
				System.out.println("Invalid Option Selected; Please enter again");	
				continue;
			}

			else
				checkFlag = false;
		}

		return selectedUserOption;
	}

	private String [] wrapText (String text, int len)
	{
		// return empty array for null text
		if (text == null)
			return new String [] {};

		// return text if len is zero or less
		if (len <= 0)
			return new String [] {text};

		// return text if less than length
		if (text.length() <= len)
			return new String [] {text};

		char [] chars = text.toCharArray();
		Vector lines = new Vector();
		StringBuffer line = new StringBuffer();
		StringBuffer word = new StringBuffer();

		for (int i = 0; i < chars.length; i++) {
			word.append(chars[i]);

			if (chars[i] == ' ') {
				if ((line.length() + word.length()) > len) {
					lines.add(line.toString());
					line.delete(0, line.length());
				}

				line.append(word);
				word.delete(0, word.length());
			}
		}

		// handle any extra chars in current word
		if (word.length() > 0) {
			if ((line.length() + word.length()) > len) {
				lines.add(line.toString());
				line.delete(0, line.length());
			}
			line.append(word);
		}

		// handle extra line
		if (line.length() > 0) {
			lines.add(line.toString());
		}

		String [] ret = new String[lines.size()];
		int c = 0; // counter
		for (Enumeration e = lines.elements(); e.hasMoreElements(); c++) {
			ret[c] = (String) e.nextElement();
		}

		return ret;
	}	
	private String displayParkInformation(Park parkRec) {

		//		drawLoadingText(percentage, String.format(
		//		        "Downloading <b>%s</b> %s: %d%% @ %d KB/s", downloadingText, s,
		//		        percentage, downloadSpeed));

		System.out.println(" ");
		System.out.println("PARK INFORMATION SCREEN \n");

		System.out.println(parkRec.getName() +" National Park");
		System.out.println(" ");
		System.out.println("Location: \t\t" + parkRec.getLocation());
		System.out.println("Established: \t\t" + new SimpleDateFormat("MM/dd/yyyy").format(parkRec.getestablishedDate()));
		System.out.println("Area: \t\t\t" + parkRec.getArea());
		System.out.println("Annual Visitors: \t" + parkRec.getVisitors());
		System.out.println(" ");
		System.out.println(" ");
		String description[] = wrapText(parkRec.getDescription(),80);
		for (int i=0; i< description.length; i++)
		{
			System.out.println(description[i]);
		}

		return (String)menu.getChoiceFromOptions(PARK_MENU_OPTIONS);

	}

	private String displayCampGroundInformation(List<Campground> allcampgrounds) {
		printListOfCamps(allcampgrounds);
		return (String)menu.getChoiceFromOptions(RESERVATION_MENU_OPTIONS);


	}    

	private void handleListAllParksWithName() {
		printHeading("All Parks");
		List<Park> allParks = parkDAO.getAllParks();
		printListOfParks(allParks);
	}

	private List<Campground> getListofCampsByParkID(Long parkID) {
		List<Campground> allcampgrounds = campgroundDAO.getCampsByParkID(parkID);
		return allcampgrounds;
	}	

	private List<Reservation> getReservationByRange(Date arrivalDate, Date departureDate, long campgroundID) {
		List<Reservation> availableReservations = reservationDAO.getReservationByRange(arrivalDate, departureDate, campgroundID);
		return availableReservations;
	}		

	private void printHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}


	private void printListOfParks(List<Park> allParks) {
		System.out.println();
		if(allParks.size() > 0) {
			for(Park park : allParks) {
				System.out.println(park.getName());

			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}
	private String[] getParknames(List<Park> allParks) {
		int i = 0;
		if(allParks.size() > 0) {
			String[] parkNameArray = new String[allParks.size() + 1];
			for(Park park : allParks) {
				parkNameArray[i] = park.getName();	
				i++;
			}	
			parkNameArray[i] = MAIN_MENU_OPTION_EXIT;
			return parkNameArray;
		}		
		return null; 
	} 

	private Park getPark(List<Park> allParks, String parkName) {

		if(allParks.size() > 0) {
			for(Park park : allParks) {
				if (park.getName().compareTo(parkName) == 0) {
					return park;
				}
			}	
		}		
		return null; 
	} 	

	private Campground getCampground(List<Campground> allcamps, long recKey) {

		if(allcamps.size() > 0) {
			for(Campground camp : allcamps) {
				if (camp.getSetKey() == recKey) {
					return camp;
				}
			}	
		}		
		return null; 
	} 		


	private void printListOfCamps(List<Campground> allCamps) {
		System.out.println();

		System.out.println(String.format("%-5s%-35s%-12s%-10s%-15s", " " , "Name", "Open", "Close", "Daily Fee"));

		if(allCamps.size() > 0) {
			for(Campground camp : allCamps) {

				String price = "$" + String.format("%.2f", camp.getDailyFee());

				System.out.println(String.format("%-5s%-35s%-12s%-10s%-15s",camp.getSetKey(), camp.getName(), camp.getOpenFrom(), camp.getOpenTo(), camp.getDailyFee()));

			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}	

	private void printListOfReservations(List<Reservation> availableReservations) {
		System.out.println();

		System.out.println(String.format("%-12s%-14s%-12s%15s%14s%10s", "Site No.", "Max Occup.", "Accessible?", "Max RV Length", "Utility", "$Cost"));

		if(availableReservations.size() > 0) {
			for(Reservation reservation : availableReservations) {
				System.out.println(String.format("%-12d%-14s%-14s%-20s%-13s%1s", reservation.getSiteNumber(), reservation.getMaxOccupancy(), transform(reservation.isAccessible()), transformNull(reservation.getMaxRVLength().toString()), transform(reservation.isUtilities()), reservation.getTotalCost().doubleValue()));

			}
		} else {
			System.out.println("\n*** There No Available Sites. ***");
		}
	}	

	private String transform(Boolean value) {
		if (value == true) {
			return "Yes";
		}
		return "No";
	}

	private String transformNull(String value) {
		if (value.isEmpty()) {
			return "N/A";
		}
		return value;
	}	

	private void createReservation(long siteID, String reservationName, Date arrivalDate, Date departureDate) {
		Reservation reservationRec = new Reservation();
		reservationRec.setSiteId(siteID);
		reservationRec.setName(reservationName);
		reservationRec.setFromDate(arrivalDate);
		reservationRec.setToDate(departureDate);
		Reservation newReservation = reservationDAO.createReservation(reservationRec);

		System.out.println("");
		System.out.println("Reservation Number: " +newReservation.getReservationId());
		System.out.println("Name: " +newReservation.getName());
		System.out.println("Arrival Date: " +new SimpleDateFormat("MM/dd/yyyy").format(newReservation.getFromDate()));
		System.out.println("Departure Date: " +new SimpleDateFormat("MM/dd/yyyy").format(newReservation.getToDate()));
		System.out.println("");
		System.out.println("Thank for your park reservation. Happy Camping!");

	}	
}	

