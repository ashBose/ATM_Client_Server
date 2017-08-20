import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

/*
 *  //http://www.javaworld.com/article/2853780/core-java/socket-programming-for-scalable-systems.html
 * http://www.java2s.com/Code/Java/Network-Protocol/ServerSocketandSocketforSerializableobject.htm
 */
public class ATMClient {
	private String host;
	private int port;
	private Socket socket;
	
	
	public ATMClient(String host, int port) {
		this.host = host;
		this.port  = port;
	}

	public Socket connect() throws IOException {
		try {
			socket = new Socket( host, port );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socket;
	}
	
	public TransactionObject communicate(TransactionObject outtransaction, statementFactory factory) throws IOException {
	        TransactionObject intransaction = null;    
	        try {
	        	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());	 
	        	ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	        	oos.writeObject(outtransaction);
				intransaction = (TransactionObject)ois.readObject();
				statementType objStmtType = factory.createStatements("selection");
				System.out.println(objStmtType.print());
				//oos.close();
				//ois.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	        //System.out.println(" hello");
			return intransaction;
	}
	
	public void close() throws IOException {
		socket.close();
	}
	
	public static void main(String[] args) {
		String host = "128.235.24.149";
		int port = 9655;
		TransactionObject tmp = new TransactionObject();
		tmp.setName("");
		tmp.setNum("5678");
		tmp.setMessage("msg");
		tmp.setId("5678");
		tmp.setAmount(200);
		tmp.setType("BALANCE");
		TransactionObject tmp_op;
		ATMClient atm = new ATMClient(host, port);
		statementFactory factory = myStatement.getUniqueInstance();
		try {
			atm.connect();
			tmp_op = atm.communicate(tmp, factory);
			System.out.println(tmp_op.getMessage());
			tmp.setType("DONE");
			atm.communicate(tmp, factory);
			atm.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}