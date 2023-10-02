package Views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

public class Welcome {

	public void WelcomeScreen() {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the app");
		System.out.println("Press 1 to login");
		System.out.println("Press 2 to signup");
		System.out.println("Press 0 to exit");
		int choice=0;
		
		try {
			choice = Integer.parseInt(br.readLine());
			
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
		switch (choice) {
		case 1:login();
			
			break;
		case 2:signup();
		
		break;
		case 0:System.exit(0);
		
		break;

		}
	}

	private void signup() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Name:");
		
		String name=sc.nextLine();
		
		System.out.println("Enter Email");
		String email=sc.nextLine();
		
		String genOTP=GenerateOTP.getOTP();
		SendOTPService.sendOTP(email, genOTP);
		System.out.println("Enter the OTP");
		String otp=sc.nextLine();
		if (otp.equals(genOTP)) {
			User user =new User(name, email);
			int response=UserService.saveUser(user);
			
			switch (response) {
			case 0:System.out.println("User Registered...!");
				
				break;

			case 1:System.out.println("User already Exists...!");
				break;
			}
			
		}
		else {
			System.out.println("Wrong OTP...");
		} 
		
		
		
	}

	private void login() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Email:");
		String email=sc.nextLine();
		try {
			if (UserDAO.isExists(email)) {
				String genOTP=GenerateOTP.getOTP();
				SendOTPService.sendOTP(email, genOTP);
				System.out.println("Enter the OTP");
				String otp=sc.nextLine();
				if (otp.equals(genOTP)) {
					new UserView(email).home();
					
				}
				else {
					System.out.println("Wrong OTP...");
				}
				
				}
			else {
				System.out.println("User not found...!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
