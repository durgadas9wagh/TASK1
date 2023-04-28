package test;

import java.util.Scanner;

public class TrainDetails {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter TrainName: ");
		String trainname = sc.nextLine();

		System.out.println("Enter Boarding: ");
		String boarding = sc.nextLine();

		System.out.println("Enter Destination: ");
		String destination = sc.nextLine();

		System.out.println("Enter TrainType: ");
		String traintype = sc.nextLine();

		System.out.println("Enter Seats Avilable: ");
		int seats = Integer.parseInt(sc.nextLine());

		System.out.println("Enter ArrivalTime: ");
		String arrivalTime = sc.nextLine();

		System.out.println("Enter DepartureTime: ");
		String departureTime = sc.nextLine();

		System.out.println("Enter trainno: ");
		String trainno = sc.nextLine();

		Dao dao = new TicketDaoImpl();

		String result = dao.TrainDetails(trainname, boarding, destination, traintype, seats, arrivalTime, departureTime,trainno);

		System.out.println(result);

		sc.close();

	}


}