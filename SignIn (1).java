package org.project.jobportal;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JLabel;

public class SignIn {

	public Client IsValidUser(Map<String, Employer> employers, Map<String, JobSeeker> jobseekers) {

		Scanner in = new Scanner(System.in);
		System.out.println("\nEnter your E-mail id:");
		String email = in.nextLine();
		System.out.println("Enter your Password:");
		String userPwd = in.nextLine();
		String pwd = "";

		System.out.println("Login as\n");
		System.out.println("1.Employer  |  2.Job Seeker");
		Client client = null;
		int option;

		for (boolean valid = false; !valid;) {
			option = in.nextInt();
			if (option == 1) {
				if (employers.containsKey(email)) {

					client = employers.get(email);
					pwd = employers.get(email).getPassword();
				} else {
					return null;
				}
				valid = true;
			} else if (option == 2) {
				if (jobseekers.containsKey(email)) {
					client = jobseekers.get(email);
					pwd = jobseekers.get(email).getPassword();
				} else {
					return null;
				}
				valid = true;
			} else {
				System.out.println("Wrong option try again!!");
			}
		}

		if (!userPwd.equals(pwd)) {
			return null;
		} else {
			return client;
		}

	}

	public Client signInAs(Map<String, Employer> employers, Map<String, JobSeeker> jobseekers) {

		SignIn sign = new SignIn();
		System.out.println("========================================");
		System.out.println("\t\t Login");
		System.out.println("========================================");

		Client client = sign.IsValidUser(employers, jobseekers);
		if (client != null) {
			System.out.println("Logged in Successfully\n");
			return client;
		} else {

			System.out.println("\nInvalid mail id or Password");
			System.out.println("Try Again?\n");
			return null;
		}
	}

}
