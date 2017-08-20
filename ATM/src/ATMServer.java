import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;

public class ATMServer extends Thread {
	
	private ServerSocket serverSocket;
    private int port;
    private static int address;
    private boolean running = false;
    private jdbcMysql jbmql;

    //ATMServer Socket is created with port number and address of the host
    public ATMServer( int port, int address )
    {
        this.port = port;
        this.address = address;
        jbmql = new jdbcMysql();
        jbmql.createData();
    }

    public void startServer()
    {
        try
        {
            serverSocket = new ServerSocket( port, address );
            
            this.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void stopServer()
    {
        running = false;
        System.out.println("Server stopped listening");
    }

    @Override
    public void run()
    {
        running = true;
        while( running )
        {
            try
            {
                System.out.println( "Listening for a connection" );
                // Call accept() to receive the next connection
                Socket socket = serverSocket.accept();
                // Pass the socket to the ATMRequestHandler thread for processing
                AtmRequestHandler requestHandler = new AtmRequestHandler(socket,jbmql );
                requestHandler.start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//http://www.javaworld.com/article/2853780/core-java/socket-programming-for-scalable-systems.html?page=2
		
		int port = 9655;
        System.out.println( "Start server on port: " + port );
        System.out.println("Your Host addr: " + InetAddress.getLocalHost().getHostAddress());
        ATMServer server = new ATMServer( port , address);
        server.startServer();
        
        //server.stopServer();
        
    }
}

class AtmRequestHandler extends Thread
{

	private Socket socket;
	private  jdbcMysql jbmql;
	
    AtmRequestHandler( Socket socket, jdbcMysql jbmql )
    {
        this.socket = socket;
        this.jbmql = jbmql;
    }

    public TransactionObject handleDatabase(TransactionObject transaction) throws SQLException {
    	
    	// create database connection.
    	switch (transaction.getMessage()) {
    	case "WITHDRAW":
    		return jbmql.dataOperations("WITHDRAW", transaction);
    		
    	case "BALANCE":
    		return jbmql.dataOperations("BALANCE", transaction);
    		
    	case "TRANSFER":
    		return jbmql.dataOperations("TRANSFER", transaction);
    			
    	case "DEPOSIT":	
    		return jbmql.dataOperations("DEPOSIT", transaction);
    		
    	case "ADD":	
    		return jbmql.dataOperations("ADD", transaction);	
    			
    	}
		//return transaction;
		return transaction;
    }
    
    @Override
    public void run()
    {
        try
        {
        	
  
        	System.out.println( "Received a connection" );
        	TransactionObject transaction;
        	while (true)
        	{
        	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());	
        	transaction = (TransactionObject)ois.readObject();
        	System.out.println( "Connection with " + transaction.getType());
        	if (transaction.getType().equals("DONE")) {
        		System.out.println( "Connection closed request from client" );
        		transaction.setMessage("Thank You");
        		oos.writeObject(transaction);
        		//oos.close();
	            socket.close();
	            System.out.println( "Connection closed" );
	            return;
            }
            transaction = handleDatabase(transaction);
           // System.out.println(transaction.getMessage());
            oos.writeObject(transaction);
            //oos.close();
            //ois.close();
        	}
        }
        catch( Exception e )
        {
            e.printStackTrace();
            return;
        }
    }

}