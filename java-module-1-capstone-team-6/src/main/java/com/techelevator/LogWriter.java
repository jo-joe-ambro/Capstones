package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogWriter {
	public void writer(String typeOfTransaction, double amount, double balance) {
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		LocalDate today = LocalDate.now();
		LocalTime now = LocalTime.now();
		try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(new File("./log.txt"), true))) {

			String printToday = today.toString();
			String printTime = now.toString().substring(0, now.toString().length());
			String printTypeOfTransaction = typeOfTransaction.toString();
			double printAmount = amount;
			double printBalance = balance;

			logWriter.println(printToday + " " + printTime + " " + String.format("%-25s", printTypeOfTransaction)
					+ String.format("%-10s", "$" + printAmount) + String.format("%-10s", "$" + printBalance));

		} catch (FileNotFoundException e) {
			
			e.getMessage();
		}
	}
}
