package org.project.jobportal;

import java.sql.*;
import org.project.jobportal.Employer;
import org.project.jobportal.HomePage;
import org.project.jobportal.Job;
import org.project.jobportal.JobSeeker;

public class DatabaseProvider {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=hruday");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	/*finally {
			if(rs!=null) {
				try {
					rs.close();
					pstmt.close();
				con.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}

	public void employeestore(String name, String phone, String email, String password, String companyName,
			String companyType, String job, String employerId) throws SQLException {
		String employeestore = "insert into batchthree.employee values(?,?,?,?,?,?,?,?)";
		connect();
		pstmt = con.prepareStatement(employeestore);
		pstmt.setString(1, name);
		pstmt.setString(2, phone);
		pstmt.setString(3, email);
		pstmt.setString(4, password);
		pstmt.setString(5, companyName);
		pstmt.setString(6, companyType);
		pstmt.setString(7, job);
		pstmt.setString(8, employerId);
		pstmt.executeUpdate();
	}

	public void jobseekerStore(String name, String password, String email, String phone, String experience, String city,
			String jobSeekerId) throws SQLException {
		String jobseekerstore = "insert into batchthree.jobseeker values(?,?,?,?,?,?,?)";
		connect();
		pstmt = con.prepareStatement(jobseekerstore);
		pstmt.setString(1, name);
		pstmt.setString(2, password);
		pstmt.setString(3, email);
		pstmt.setString(4, phone);
		pstmt.setString(5, experience);
		pstmt.setString(6, city);
		pstmt.setString(7, jobSeekerId);
		pstmt.executeUpdate();
	}

	public void jobstore(String companyName, String jobTitle, String language, String employment, String jobDescription,
			String jobLocation, String employerId,String jobId) throws SQLException {
		String jobstore = "insert into batchthree.jobs values(?,?,?,?,?,?,?,?)";
		connect();
		pstmt = con.prepareStatement(jobstore);
		pstmt.setString(1, companyName);
		pstmt.setString(2, jobTitle);
		pstmt.setString(3, language);
		pstmt.setString(4, employment);
		pstmt.setString(5, jobDescription);
		pstmt.setString(6, jobLocation);
		pstmt.setString(7, employerId);
		pstmt.setString(8,jobId);
		pstmt.executeUpdate();
	}

	public void jobApplicantStore(JobSeeker js, String jobid) throws SQLException {
		String jobApplicantStore = "insert into batchthree.jobApplicants values(?,?,?,?,?,?,?,?)";
		connect();
		pstmt = con.prepareStatement(jobApplicantStore);
		pstmt.setString(1, js.getName());
		pstmt.setString(2, js.getEmail());
		pstmt.setString(3, js.getPassword());
		pstmt.setString(4, js.getPhone());
		pstmt.setString(5, js.getExperience());
		pstmt.setString(6, js.getCity());
		pstmt.setString(7, js.getjobSeekerId());
		pstmt.setString(8, jobid);
		pstmt.executeUpdate();
	}

	public void init() throws Exception {
		employeretrive();
		jobseekerretrive();
		jobretrive();
		jobApplicantretrive();
	}

	public void employeretrive() throws SQLException {
		String empretrive = "select * from batchthree.employee";
		connect();
		pstmt = con.prepareStatement(empretrive);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Employer employer = new Employer();
			employer.setName(rs.getString(1));
			employer.setPhone(rs.getString(2));
			employer.setEmail(rs.getString(3));
			employer.setPassword(rs.getString(4));
			employer.setCompanyName(rs.getString(5));
			employer.setCompanyType(rs.getString(6));
			employer.setJob(rs.getString(7));
			employer.setEmployerId(rs.getString(8));
			HomePage.employers.put(employer.getEmail(), employer);
		}
	}

	public void jobseekerretrive() throws SQLException {
		String jobskretrive = "select * from batchthree.jobseeker";
		connect();
		pstmt = con.prepareStatement(jobskretrive);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			JobSeeker jobseeker = new JobSeeker();
			jobseeker.setName(rs.getString(1));
			jobseeker.setPassword(rs.getString(2));
			jobseeker.setEmail(rs.getString(3));
			jobseeker.setPhone(rs.getString(4));
			jobseeker.setExperience(rs.getString(5));
			jobseeker.setCity(rs.getString(6));
			jobseeker.setjobSeekerId(rs.getString(7));
			HomePage.jobseekers.put(jobseeker.getEmail(), jobseeker);

		}
	}

	public void jobretrive() throws SQLException {
		String jobretrive = "select * from batchthree.jobs";
		connect();
		pstmt = con.prepareStatement(jobretrive);
		rs = pstmt.executeQuery();
		
		while (rs.next()) 
		{
			Job j = new Job();
			j.companyName = rs.getString(1);
			j.jobTitle = rs.getString(2);
			j.language = rs.getString(3);
			j.employment = rs.getString(4);
			j.jobDescription = rs.getString(5);
			j.jobLocation = rs.getString(6);
			j.employerId = rs.getString(7);
			j.jobId=rs.getString(8);
			HomePage.jobs.add(j);
		}
	}

	public void jobApplicantretrive() throws SQLException {
		String jobapplicantretrive = "select * from batchthree.jobApplicants";
		connect();
		pstmt = con.prepareStatement(jobapplicantretrive);
		rs = pstmt.executeQuery();
		

		while (rs.next()) {
			JobSeeker jobseeker = new JobSeeker();
			jobseeker.setName(rs.getString(1));
			jobseeker.setPassword(rs.getString(2));
			jobseeker.setEmail(rs.getString(3));
			jobseeker.setPhone(rs.getString(4));
			jobseeker.setExperience(rs.getString(5));
			jobseeker.setCity(rs.getString(6));
			jobseeker.setjobSeekerId(rs.getString(7));
			HomePage.applicants.put(rs.getString(8), jobseeker);
		}
	}
	
}