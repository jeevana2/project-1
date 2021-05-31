package org.project.jobportal;

import java.util.Map;

public class SignUp {

	public void signUpAs(Client client, Map<String, Employer> employers, Map<String, JobSeeker> jobseekers) {

		System.out.println("========================================");
		System.out.println("\t\tSignUp");
		System.out.println("========================================");
		System.out.println("\nEnter your details below\n");

		if (client instanceof Employer) {
			client.getDetails();
			employers.put(((Employer) client).getEmail(), (Employer) client);
		} else if (client instanceof JobSeeker) {
			client.getDetails();
			jobseekers.put(((JobSeeker) client).getEmail(), (JobSeeker) client);
		}

	}

}
