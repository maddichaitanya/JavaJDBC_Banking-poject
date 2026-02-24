import java.sql.*;
import java.util.Scanner;

class BankJDBC {
	
	
    static final String URL = "jdbc:mysql://localhost:3306/bankdb";
    static final String USER = "root";
    static final String PASS = "***";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Connection con = DriverManager.getConnection(URL, USER, PASS);

        while (true) {
			// Start 
            System.out.println("\n******** WELCOME ********");
            System.out.println("1. CREATE ACCOUNT");
            System.out.println("2. LOGIN ");
            System.out.print("Enter your response: ");
            int opt = sc.nextInt();

            switch (opt) {

                // ================= CREATE ACCOUNT =================
                case 1: {

                    System.out.print("Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    System.out.print("Contact: ");
                    long contact = sc.nextLong();

                    System.out.print("Pin: ");
                    int pin = sc.nextInt();
					
                    System.out.print("Amount: ");
                    double balance = sc.nextDouble();
					
					sc.nextLine();
					System.out.print("Account Type: ");
					String type=sc.nextLine();
					
					//---------- using try catch to handleing an exception ------------
					try
					{
						String sql = "INSERT INTO account(name,contact,pin,balance) VALUES(?,?,?,?)";
						String sqlt= "INSERT into transactions(name,amount,type) VALUES(?,?,?)";
                    PreparedStatement ps = con.prepareStatement(sql);
					PreparedStatement pst = con.prepareStatement(sqlt);

                    ps.setString(1, name);
                    ps.setLong(2, contact);
                    ps.setInt(3, pin);
                    ps.setDouble(4, balance);
					
					pst.setString(1, name);
                   // pst.setLong(2, contact);
                    pst.setDouble(2, balance);
					pst.setString(3,type);
					
					pst.executeUpdate();
                    ps.executeUpdate();

                    System.out.println("Account Created Successfully!");
					}
					
					//-------- number should be unique --------------
					
					catch (SQLIntegrityConstraintViolationException e)
					{
					 System.out.println("Account already exists with this contact number! ");
					}
					 catch (SQLException e) {
						 
						    System.out.println("Invalid contact details ! ");
							System.out.println("Database Error: " + e.getMessage());
							}
					
					 /* catch (SQLException e) {
							System.out.println("Database Error: " + e.getMessage()); 
							}  */
                    break; 
                }

                // ================= LOGIN =================
                case 2: {

                    System.out.print("Contact: ");
                    long contact = sc.nextLong();

                    System.out.print("Pin: ");
                    int pin = sc.nextInt();
					
					//----------- checking user Credentials --------
                    String sql = "SELECT * FROM account WHERE contact=? AND pin=?";
                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setLong(1, contact);
                    ps.setInt(2, pin);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {

                        System.out.println("Login Successful!");
						
						// --------------- Features of my bank ---------
                        while (true) {

                            System.out.println("\n1.Deposit");
                            System.out.println("2.Withdraw");
                            System.out.println("3.Check Balance");
							System.out.println("4.Transaction");
                            System.out.println("5.Logout");
                            System.out.print("Enter option: ");
                            int opt1 = sc.nextInt();

                            switch (opt1) {

                                // ===== Deposit =====
                                case 1: {
                                    System.out.print("Enter amount: ");
                                    double amt = sc.nextDouble();

                                    String dep = "UPDATE account SET balance = balance + ? WHERE contact=?";
                                    PreparedStatement ps1 = con.prepareStatement(dep);
                                    ps1.setDouble(1, amt);
                                    ps1.setLong(2, contact);
                                    ps1.executeUpdate();
									
									

                                    System.out.println("Amount Deposited!");
                                    break;
                                }

                                // ===== Withdraw =====
                                case 2: {
                                     System.out.print("Enter amount: ");
                                     double amt = sc.nextDouble();
                                     
									 System.out.print("Contact: ");
									 long contact1 = sc.nextLong();

									System.out.print("Pin: ");
									int pin1 = sc.nextInt();

									String sql1 = "SELECT * FROM account WHERE contact=? AND pin=?";
									PreparedStatement ps9 = con.prepareStatement(sql1);

									ps9.setLong(1, contact1);
									ps9.setInt(2, pin1);

									ResultSet rs9 = ps9.executeQuery();
									
									
									if (rs9.next()) {
									
                                    String check = "SELECT balance FROM account WHERE contact=?";
                                    PreparedStatement ps2 = con.prepareStatement(check);
                                    ps2.setLong(1, contact);
                                    ResultSet rs2 = ps2.executeQuery();
									
									
                                    if (rs2.next()) {
                                        double bal = rs2.getDouble("balance");

                                        if (amt <= bal) {

                                            String wd = "UPDATE account SET balance = balance - ? WHERE contact=?";
                                            PreparedStatement ps3 = con.prepareStatement(wd);
                                            ps3.setDouble(1, amt);
                                            ps3.setLong(2, contact);
                                            ps3.executeUpdate();

                                            System.out.println("Amount Withdrawn!");
                                        } else {
                                            System.out.println("Insufficient Balance!");
                                        }
                                    }  }
									else {
										System.out.println(" Invalid Credentials! ");
									}
                                    break;
                                }

                                // ===== Check Balance =====
                                case 3: {
									
									System.out.print("Contact: ");
									long contact2 = sc.nextLong();

									System.out.print("Pin: ");
									int pin2 = sc.nextInt();

									String sql2 = "SELECT * FROM account WHERE contact=? AND pin=?";
									PreparedStatement ps8 = con.prepareStatement(sql2);

									ps8.setLong(1, contact2);
									ps8.setInt(2, pin2);

									ResultSet rs8 = ps8.executeQuery();
									
									if (rs8.next()) {
                                    String balQuery = "SELECT balance FROM account WHERE contact=?";
                                    PreparedStatement ps4 = con.prepareStatement(balQuery);
                                    ps4.setLong(1, contact);

                                    ResultSet rs4 = ps4.executeQuery();
                                    if (rs4.next()) {
										System.out.println("name : " + rs8.getString("name"));
										System.out.println("contact : " + rs8.getLong("contact"));
                                        System.out.println("Balance : " + rs4.getDouble("balance"));
                                    }}
									
									else {
										System.out.println("Invalid Credentials! ");
									}
                                    break;
                                }
								
								//=========== Transaction ========
								case 4:
									System.out.println(" This feature is building ");
									System.out.print("Contact: ");
									long contact3 = sc.nextLong();

									System.out.print("Pin: ");
									int pin3 = sc.nextInt();

									String sql3 = "SELECT * FROM account WHERE contact=? AND pin=?";
									PreparedStatement ps7 = con.prepareStatement(sql3);

									ps7.setLong(1, contact3);
									ps7.setInt(2, pin3);

									ResultSet rs7 = ps7.executeQuery();
									if (rs7.next())
									{
										
									}
									else {
										System.out.println("Invalid Credentials! ");
									}
									break;
									
                                // ===== Logout =====
                                case 5:
                                    System.out.println("Logged Out!");
                                    break;

                                default:
                                    System.out.println("Invalid Option!");
                            }

                            if (opt1 == 4)
                                break;
                        }

                    } else {
                        System.out.println("Invalid Credentials!");
                    }

                    break;
                }

                default:
                    System.out.println("Invalid Option!");
            }
        }
    }
}
