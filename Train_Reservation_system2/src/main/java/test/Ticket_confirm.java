package test;

import java.util.Scanner;

public class Ticket_confirm {

	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Train No: ");
		String trainno = sc.nextLine();

		System.out.println("Enter Name: ");
		String trainname = sc.nextLine();


	    Dao dao =new TicketDaoImpl();


	    String result = dao.ticketCancel(trainno,trainname);

	    System.out.println();

	    System.out.println(result);


		sc.close();


	}

}