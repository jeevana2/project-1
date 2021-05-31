package org.project.jobportal;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployersLobby {

	int count;
	boolean flag;

	public void employersMenu(Client client, List<Job> jobs) {
		Scanner in = new Scanner(System.in);
		SignOut signout = new SignOut();
		Employer employer = (Employer) client;
		System.out.println("Hello " + employer.getName() + " What would you like to do today?");
		int option;
		System.out.println("\n1.PostJobs\n2.ViewApplicants\n3.Logout");
		for (boolean valid = false; !valid;) {
			option = in.nextInt();
			switch (option) {

			case 1: {
				postJobs(client, jobs);
				valid = true;
			}
				break;

			case 2: {
				viewApplicants(client, jobs);
				valid = true;
			}
				break;


			case 3: {
				signout.signOut();
				valid = true;
			}
				break;

			default: {
				System.out.println("Wrong option try again!!");
			}
			}
		}

	}

	public void viewApplicants(Client client, List<Job> jobs) {

		Scanner in = new Scanner(System.in);
		SignOut signout = new SignOut();
		Employer employer = (Employer) client;
		int choice;
		boolean flag = false;

		for (Job jobsPosted : jobs) {
			if ((jobsPosted.employerId).equals(employer.getEmployerId())) {
				flag = true;
				HomePage.applicants.forEach((key, value) -> {
					if ((jobsPosted.jobId).equals(key))
						System.out.println("--" + (++count) + "------------------------");
					System.out.println("Name: " + value.getName());
					System.out.println("E-mail ID: " + value.getEmail());
					System.out.println("Password: " + value.getPassword());
					System.out.println("Phone number: " + value.getPhone());
					System.out.println("City: " + value.getCity());
					System.out.println("Experience:" + value.getExperience());
				});
			}
		}
		if (count == 0) {
			System.out.println("No Applicants have applied!!");
		}
		if (!flag) {
			System.out.println("No jobs posted yet!!");
		}

		int option;
		System.out.println("\n1.Menu\n2.Logout");
		for (boolean valid = false; !valid;) {
			option = in.nextInt();
			switch (option) {

			case 1: {
				employersMenu(client, jobs);
				valid = true;
			}
				break;

			case 2: {
				signout.signOut();
				valid = true;
			}
				break;

			default: {
				System.out.println("Wrong option try again!!");
			}
			}
		}
	}

	public void postJobs(Client client, List<Job> jobs) {

		Scanner in = new Scanner(System.in);
		Employer employer = (Employer) client;

		Job job = new Job();
		System.out.println("Enter your Company name:");
		job.companyName = in.nextLine();
		System.out.println("Enter the Job title:");
		job.jobTitle = in.nextLine();
		System.out.println("Enter the main language:");
		job.language = in.nextLine();
		System.out.println("Enter the kind of employment:");
		job.employment = in.nextLine();
		System.out.println("Enter the Job description:");
		job.jobDescription = in.nextLine();
		System.out.println("Enter the Job Location:");
		job.jobLocation = in.nextLine();
		job.jobId = Integer.toString(jobs.size() + 1);
		job.employerId = employer.getEmployerId();
		DatabaseProvider db = new DatabaseProvider();
		try {
			db.jobstore(job.companyName, job.jobTitle, job.language, job.employment, job.jobDescription,
					job.jobLocation, job.employerId,job.jobId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jobs.add(job);
		System.out.print("Job posted successfully\n");
		SignOut l = new SignOut();

		int option;
		System.out.println("\n1.Menu\n2.Logout");
		for (boolean valid = false; !valid;) {
			option = in.nextInt();
			switch (option) {

			case 1: {
				employersMenu(client, jobs);
				valid = true;
			}
				break;

			case 2: {
				l.signOut();
				valid = true;
			}
				break;

			default: {
				System.out.println("Wrong option try again!!");
			}
			}
		}
	}
}
