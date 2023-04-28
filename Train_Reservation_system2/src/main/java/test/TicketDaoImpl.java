

package test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class TicketDaoImpl implements  Dao {

	@Override
	public String TrainDetails(String trainname,String boarding, String destination,
			String trainType, int Seats, String ArrivalTime, String DepartureTime,String trainno)
	{
		String message = "Not inserted !";

		try (Connection conn = DButil.provideConnection()) {

			PreparedStatement ps =  conn.prepareStatement("insert into traindetails values (?,?,?,?,?,?,?,?) ");

			ps.setString(1, trainname);
			ps.setString(2, boarding);
			ps.setString(3, destination);
			ps.setString(4, trainType);
			ps.setInt(5, Seats);
			ps.setString(6, ArrivalTime);
			ps.setString(7, DepartureTime);
			ps.setString(8, trainno);
			
			int x = ps.executeUpdate();

			if(x>0)
			{
				message = "Train Details Inserted Sucessfully !";
			}



		} catch (Exception e) {
			message = e.getMessage();
		}




		return message;
	}

	@Override
	public String Ticketconfirm(String boarding, String destination, String name,int age,String trainname, String trainno) {

		String message = "Seat Not Available !";

		try (Connection conn = DButil.provideConnection()){

			PreparedStatement ps = conn.prepareStatement("select seats,trainno from traindetails where boarding=? and destination=?");

			ps.setString(1, boarding);
			ps.setString(2, destination);

			ResultSet rs = ps.executeQuery();
			System.out.println("hello");

			try {

				if(rs.next())
				{	System.out.println("Inside If");
					int x = rs.getInt("Seats");

					if(x>0)
					{	System.out.println("avalaible seats :"+x);
						PreparedStatement ps2 = conn.prepareStatement("Update traindetails set Seats=Seats-1 where boarding=?");

						ps2.setString(1, boarding);

						ps2.executeUpdate();

						PreparedStatement ps3 = conn.prepareStatement("insert into customerdetails values(?,?,?,?,?,?)");

						ps3.setString(1, boarding);
						ps3.setString(2, destination);
						ps3.setString(3,name);
						ps3.setInt(4, age);
						ps3.setString(5, trainname);
						ps3.setString(6, trainno);
						
						ps3.executeUpdate();
						PreparedStatement ps4  = conn.prepareStatement("select * from customerdetails where name=?");

						ps4.setString(1, name);

						ResultSet rs2 = ps4.executeQuery();

						if(rs2.next())
						{
							System.out.println("congradulation");
							System.out.println("TrainNo: "+ rs2.getString(1));
							System.out.println("Passenger Name: "+rs2.getString(3));
							System.out.println("Passenger Age: "+rs2.getInt(4));	
							System.out.println("TrainName: "+rs2.getString(5));
						}
						message = "Ticked confirmed !";
					}

				}
				else 
				{
					message = "Please Enter Valid Location !";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}


		} catch (Exception e) {
			message = e.getMessage();
		}
		return message;
	}

	@Override
	public String ticketCancel(String trainname, String trainno) {

		String message = "Ticket Canellation Unsucessful !";


		try (Connection conn = DButil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("delete from customerdetails where trainname = ? and trainno = ?");

			ps.setString(1,trainname);
			ps.setString(2, trainno);

			int x= ps.executeUpdate();

			PreparedStatement ps2 = conn.prepareStatement("Update traindetails set seats = seats+1 where trainno = ?");

			ps2.setString(1, trainno);

			ps2.executeUpdate();

			if(x>0)
			{
				message = "Ticket Cancellation Sucessful !";
			}

		} catch (Exception e) {
			message = e.getMessage();
		}


		return message;
	}



	



	@Override
	public boolean adminLogin(String username, String password) {
    String message = null;
    boolean isLoggedIn = false; // Add a boolean variable to track if the login is successful

    try (Connection conn = DButil.provideConnection()) {
        // Replace DButil with your own utility class to provide database connection

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM adminlogin WHERE username = ? AND password = ?");

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Welcome " + rs.getString(1) + "!");
            System.out.println("System login and connected successfully");

            // Add your admin login logic here

            isLoggedIn = true; // Set isLoggedIn to true upon successful login
        } else {
            System.out.println("Invalid admin login credentials. Please try again.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return isLoggedIn; // Return the isLoggedIn value
}

	@Override
	public String Ticket_confirm(String boarding, String destination, String name, int age, String trainname,
			String trainno) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}

	
	
	
	
