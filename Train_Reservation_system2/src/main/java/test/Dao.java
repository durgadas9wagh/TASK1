package test;

public interface Dao {

	public String TrainDetails(String trainname,String boarding, String destination,
			String traintype, int Seats, String ArrivalTime, String DepartureTime, String trainno);



	public String ticketCancel(String trainname, String trainno);

	public boolean adminLogin(String username, String password);

	String Ticket_confirm(String boarding, String destination, String name, int age, String trainname, String trainno);



	String Ticketconfirm(String boarding, String destination, String name, int age, String trainname, String trainno);

	

}