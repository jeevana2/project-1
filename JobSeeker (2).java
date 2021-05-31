package org.project.jobportal;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class JobSeeker implements Client {

	private String name;
	private String password;
	private String email;
	private String phone;
	private String experience;
	private String city;
	private String jobSeekerId;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getjobSeekerId() {
		return name;
	}

	public void setjobSeekerId(String jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	@Override
	public void getDetails() {

		Scanner in = new Scanner(System.in);

		System.out.println("Enter your Name:");
		this.setName(in.nextLine());
		System.out.println("Enter your E-mail ID:");
		this.setEmail(in.nextLine());
		System.out.println("Enter your Password:");
		this.setPassword(in.nextLine());
		System.out.println("Enter your Phone number:");
		this.setPhone(in.nextLine());
		System.out.println("Enter your City:");
		this.setCity(in.nextLine());
		System.out.println("Enter your Experience:");
		this.setExperience(in.nextLine());
		jobSeekerId = this.email + this.phone;
		DatabaseProvider db = new DatabaseProvider();
		try {
			db.jobseekerStore(name, password, email, phone, experience, city, jobSeekerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewDetails() {

		System.out.println("Name: " + this.getName());
		System.out.println("E-mail ID: " + this.getEmail());
		System.out.println("Password: " + this.getPassword());
		System.out.println("Phone number: " + this.getPhone());
		System.out.println("City: " + this.getCity());
		System.out.println("Experience:" + this.getExperience());
		this.jobSeekerId = this.phone + this.email;
	}

}
