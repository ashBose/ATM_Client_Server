import java.sql.*;

/*
 *  https://web.njit.edu/~theo/courses/cs602/week11/download/jdbcMysql.java
 */

public class jdbcMysql {
	   
	
	String url = "sql2.njit.edu";
	String ucid = "ab822";	//my ucid
	String dbpassword = "etPccaCIk";	//my MySQL password
	Connection conn;
	
	//Establishing connection with the DriverManager 
	public void createData() {
	try {
		// Adding mysql driver 
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	}
	catch (Exception e) {
		System.err.println("Unable to load driver.");
		e.printStackTrace();
	}
	System.out.println("Driver loaded.");
	System.out.println("Establishing connection . . . ");
	try {
		
        // using jdbc connection to connect to database username password
		conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
		
		System.out.println("Connection established.");			
		System.out.println("Creating a Statement object . . . ");

		// MySQL connection is established , now executing MYSQL command
		
		Statement stmt = conn.createStatement();
		System.out.println("Statement object created.");
		/* Below doing all operations 
		 * drop table user
		 * insert data according to schema 
		 * This function is called from Server , when server is initiated, database will be
		 * created. All data will be populated that time. 
		 */
		stmt.executeUpdate("DROP TABLE IF EXISTS user");
		System.out.println("Old table dropped (if it existed).");

		System.out.println("Creating a table . . .");
		//Creating table with attributes and there types
		stmt.executeUpdate("CREATE TABLE user("+
			"id VARCHAR(100) NOT NULL, "+
			"type VARCHAR(100) NOT NULL, "+
			"num VARCHAR(100) NOT NULL, "+
			"name VARCHAR(100) NOT NULL, "+
			"date DATE NOT NULL, "+
			"amount int NOT NULL)");
		System.out.println("Table created.");

		System.out.println("Inserting data in table . . .");

		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
			"VALUES(\"1234\", \"Savings\", \"Ritu1234\", \"Ritu\", '2013-12-12',920)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
			"VALUES(\"1234\", \"CheckIn\", \"Ritu1234\", \"Ritu\", '2016-09-26',9920)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
				"VALUES(\"1224\", \"CheckIn\", \"Kitu1224\", \"Kitu\", '2008-11-05',9200)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
				"VALUES(\"1224\", \"Savings\", \"Kitu1224\", \"Kitu\", '2001-08-28',19200)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
				"VALUES(\"5678\", \"Savings\", \"John5678\", \"John\", '2010-03-09',5679)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
				"VALUES(\"5678\", \"CheckIn\", \"John5678\", \"John\", '2005-02-01',15679)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
				"VALUES(\"6789\", \"CheckIn\", \"Max6789\", \"Max\", '2012-06-03',6780)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
				"VALUES(\"4567\", \"Savings\", \"Ron4567\", \"Ron\", '2016-10-17',6792)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
				"VALUES(\"4592\", \"Savings\", \"Mary4592\", \"Mary\", '2014-11-28',3456)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
				"VALUES(\"5612\", \"CheckIn\", \"Joe5612\", \"Joe\", '2011-03-07',6788)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
				"VALUES(\"7823\", \"CheckIn\", \"Smith7823\", \"Smith\", '2009-01-12',10000)");
		stmt.executeUpdate("INSERT INTO user (id, type, num, name, date, amount) "+
				"VALUES(\"9832\", \"Savings\", \"Alex9832\", \"Alex\", '2002-05-18',7832)");
		System.out.println("Inserted data.");
	}
	catch (SQLException E) {
		System.out.println("SQLException: " + E.getMessage());
		System.out.println("SQLState:     " + E.getSQLState());
		System.out.println("VendorError:  " + E.getErrorCode());
	}
}


	/*
	 *Test code for server
	public static void main(String[] args) throws SQLException{
		TransactionObject tmp = new TransactionObject();
		jdbcMysql jbml =  new jdbcMysql();
		jbml.createData();
		tmp.setName("");
		tmp.setNum("5678");
		tmp.setMessage("msg");
		tmp.setId("5678");
		tmp.setAmount(200);
        //jbml.dataOperations("BALANCE", tmp);		
		jbml.dataOperations("WITHDRAW", tmp);
	} */
	
public void closeConn( ) throws SQLException {
	/*
	 * Closing SQL connection
	 */
	conn.close();
}

public TransactionObject dataOperations(String operation, TransactionObject tmpObj) throws SQLException {
	Statement stmt = conn.createStatement();
	String cmd;
	ResultSet rs;
	float amount;
	int status;
	/*
	 * Here  do all database operation based on Type. Except balance , we do 
	 * updates on all operations.
	 * After the operation is performed, transaction object is updated with proper
	 * message using setMessage API . Those messages will be displayed on the client UI
	 * 

	 */
	switch (operation) {
	case "WITHDRAW":
		amount = tmpObj.getAmount();
		cmd = "SELECT amount from user where id="+tmpObj.getId()+ " and type='"+ tmpObj.getType() + "'";  
		System.out.println(cmd);
		rs = stmt.executeQuery(cmd);
		float tmpamount = 0;
		if (rs.next()) {
			tmpamount = Integer.parseInt(rs.getString("amount"));
		}
		if ( amount >= tmpamount) {
			tmpObj.setMessage("Withdraw unsuccessful");
			break;
		}
		// Doing update operation 
		tmpamount -= amount;
		cmd = "update user set amount= " + tmpamount + " where id=" + tmpObj.getId() +" AND type='"+ tmpObj.getType()+"'";
		status = stmt.executeUpdate(cmd);
		// Updating transaction object
		if (status == 1) // status == 1 when success .
		   tmpObj.setMessage("Withdraw Successful ");
		else
			tmpObj.setMessage("Withdraw unsuccessful");
		break;
	case "BALANCE": // Checking the balance
		cmd = "SELECT amount from user where id="+tmpObj.getId()+ " and type='" +  tmpObj.getType() + "'" ;  
		System.out.println(cmd);
		rs = stmt.executeQuery(cmd);
		if (rs.next()) {
			tmpObj.setMessage(" Balance is " + rs.getString("amount"));
			//System.out.println(tmpObj.getMessage());
		}		
		break;
	case "TRANSFER"://need to add database command to transfer from one account to other
		 
		break;
	case "ADD": // need to add 
		cmd = "INSERT INTO user (id, type, num, name, date, amount) values(";
		cmd += "'" + tmpObj.getId() +"',";
		cmd += "'" + tmpObj.getType() +"',";
		cmd += "'" + tmpObj.getNum() +"',";
		cmd += "'" + tmpObj.getName() +"',";
		cmd +=  "'2016-12-18',0)";
		
		System.out.println(cmd);
		status = stmt.executeUpdate(cmd);
		// Updating transaction object
		if (status == 1) // status == 1 when success .
		   tmpObj.setMessage("Adding new user  Successful ");
		else
			tmpObj.setMessage("Adding new user unsuccessful");
		break;
	case "DEPOSIT":
		/* 
		 * for type of deposit need to add the current amount with deposited amount
		 */
		amount = tmpObj.getAmount();
		cmd = "SELECT amount from user where id="+tmpObj.getId()+ " and type='" + tmpObj.getType() +"'" ;  
		//System.out.println(cmd);
		rs = stmt.executeQuery(cmd);
		if (rs.next()) {
			amount += Integer.parseInt(rs.getString("amount"));
		}
		cmd = "update user set amount= " + amount + " where id=" + tmpObj.getId() + " AND type='" + tmpObj.getType() + "'";
		status = stmt.executeUpdate(cmd);
		if (status == 1)
		   tmpObj.setMessage("Deposit Successful ");
		else
			tmpObj.setMessage("Deposit unsuccessful");
		break;
	}	
	return tmpObj;
}

}
