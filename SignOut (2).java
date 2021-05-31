package org.project.jobportal;

public class SignOut {

	public void signOut() {

		System.out.println("\n========================================");
		System.out.println("\t\t Signing out");
		System.out.println("========================================");

		HomePage homepg = new HomePage();
		homepg.loginDetails();
	}
}
