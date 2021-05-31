package org.project.jobportal;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HomePage {

	protected Client client;
	protected static Map<String, Employer> employers = new HashMap<String, Employer>();
	protected static Map<String, JobSeeker> jobseekers = new HashMap<String, JobSeeker>();
	protected static Map<String, JobSeeker> applicants = new HashMap<String, JobSeeker>();

	protected static List<Job> jobs = new ArrayList<Job>();

	public Client createClient() {

		Scanner in = new Scanner(System.in);

		
		System.out.println("Register as\n");
		System.out.println("1.Employer  |  2.Job Seeker");
		int option;

		for (boolean valid = false; !valid;) {
			option = in.nextInt();
			if (option == 1) {
				client = new Employer();
				valid = true;
			} else if (option == 2) {
				client = new JobSeeker();
				valid = true;
			} else {
				System.out.println("Wrong option try again!!");
			}
		}

		return client;
	}

	public void loginDetails() {

		Scanner in = new Scanner(System.in);
		SignUp signUp = new SignUp();
		System.out.println("========================================");
		System.out.println("\t\t Home");
		System.out.println("========================================");

		System.out.println("1.SignIn    |  2.SignUp");
		int option;

		for (boolean valid = false; !valid;) {
			option = in.nextInt();
			switch (option) {

			case 1: {
				lobby();
				valid = true;
			}
				break;

			case 2: {
				Client client = createClient();
				signUp.signUpAs(client, HomePage.employers, HomePage.jobseekers);
				lobby();
				valid = true;
			}
				break;

			default: {
				System.out.println("Wrong option try again!!");
			}
			}
		}
	}

	public void lobby() {
		SignIn signIn = new SignIn();
		EmployersLobby eLobby = new EmployersLobby();
		JobSeekersLobby jLobby = new JobSeekersLobby();

		Client client = signIn.signInAs(HomePage.employers, HomePage.jobseekers);

		if (client != null) {
			if (client instanceof Employer) {
				eLobby.employersMenu(client, jobs);
			} else if (client instanceof JobSeeker) {
				jLobby.jobSeekersMenu(client, jobs);
			}
		} else {
			loginDetails();
		}

	}
}
