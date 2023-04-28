

package test;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int check = 1;

		try {
			while(check !=2)
			{
			
				System.out.println();
				System.out.println("**********************************************************");
			    System.out.println("------------: Train Ticket Reservation System:------------");
				System.out.println("**********************************************************");

				
				TicketDaoImpl dao = new TicketDaoImpl();


				System.out.println();
				System.out.println("1. Admin");
				System.out.println("2. Customer");
				System.out.println();

				System.out.println("Enter your choice:");
				int choice = Integer.parseInt(sc.nextLine());

				if(choice==1)
				{
					System.out.println();
					System.out.println("Enter username: ");
					String username = sc.nextLine();

					System.out.println("Enter Password: ");
					String password = sc.nextLine();


					boolean login = dao.adminLogin(username, password);

					System.out.println(login);

					if(login)     
					{
						

					System.out.println("1. Insert train Details");
					System.out.println("2. Go back");
					System.out.println("3. Exit");

					System.out.println("");
					System.out.println("Enter your choice which one you want:");
					int insertorNot =Integer.parseInt(sc.nextLine());
					System.out.println(insertorNot);
					if(insertorNot==1)

					{
						System.out.println("Enter trainName: ");
						String trainname =sc.nextLine();

						System.out.println("Enter Boarding: ");
						String boarding =sc.nextLine();

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

						String result = dao.TrainDetails(trainname, boarding, destination, traintype, seats, arrivalTime, departureTime,trainno);

						System.out.println(result);

					}
					else if(insertorNot==2)
					{
						check = 1;
					}
					else if(insertorNot==3)
					{
						check = 2;
					}
					else
					{
						System.out.println("Please Enter Correction Information Next Time!");
					}
					}
				}
				else if(choice ==2)
				{
					System.out.println();
					System.out.println("1. Book Ticket");
					System.out.println("2. Cancel Ticket");
					System.out.println("3. Go back");
					System.out.println("4. Exit");


					System.out.println();
					System.out.println("Enter your choice: ");
					int bookorNot = Integer.parseInt(sc.nextLine());

					if(bookorNot==1)
					{
						System.out.println();

						try(Connection conn =DButil.provideConnection()) {


							PreparedStatement ps = conn.prepareStatement("Select * from traindetails");

							ResultSet rs = ps.executeQuery();

							System.out.println("ENTER ALL INFORMATION LIKE TRAINNO,BOARDING,DESTINATION,SEATS");
							System.out.println("Train no"+"  "+" available Seats"+"  "+"Start from "+"  "+"Last STN");
							while(rs.next())
							{

								System.out.print("  "+rs.getString("TRAINNO")+"     ");
								System.out.print(rs.getInt("SEATS")+"               ");
								System.out.print(rs.getString("BOARDING")+"         ");
								System.out.print(rs.getString("DESTINATION"));



								System.out.println();
							}

						} catch (Exception e) {
							System.out.println(e.getMessage());
						} 

						System.out.println();
						System.out.println("Enter Boarding Location: ");
						String boarding = sc.nextLine();

						System.out.println("Enter Destination Location: ");
						String destination = sc.nextLine();

						System.out.println("Enter Your Name: ");
						String name = sc.nextLine();//disconnect kr de ok

						System.out.println("Enter Your Age: ");
						int age = Integer.parseInt(sc.nextLine());

						System.out.println("Enter Your Train Name: ");
						String trainname = sc.nextLine();
						
						System.out.println("Enter your Train number:");
						String trainno=sc.nextLine();
						
						

						String res = dao.Ticketconfirm(boarding,destination,name,age,trainname,trainno);

						System.out.println();
						System.out.println(res);
						check = 1;


					}
					else if(bookorNot==2)
					{
						System.out.println("Enter Train No: ");
						String trainno = sc.nextLine();

						System.out.println("Enter Train Name: ");
						String trainname = sc.nextLine();


						String result = dao.ticketCancel(trainname,trainno);


						System.out.println();

						
						System.out.println(result);

					}
					else if(bookorNot==3)
					{
						check = 1;
					}
					else if(bookorNot==4)
					{
						check = 2;
					}
					else
					{
						System.out.println("Please Enter Correction Information Next Time!");
					}

				}
				else
				{
					System.out.println("Please Enter Correction Information Next Time!");
				}

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		
		System.out.println("Thank you  !");
		System.out.println("**********************************************************");
	    System.out.println("------------: Train Ticket Reservation System:------------");
		System.out.println("**********************************************************");

		
		sc.close();
	}
}