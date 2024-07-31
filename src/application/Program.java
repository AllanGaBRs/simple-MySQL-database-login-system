package application;

import java.util.Scanner;

import model.verification.Verification;

public class Program {

	public static void main(String[] args) {
		 
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Password: ");
		String senha = sc.nextLine();
		
		System.out.println(Verification.path(email, senha, sc));			
		
		
		sc.close();
		
	}

}
