import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

class GUIBank extends JFrame{
	  
	private JFrame LoginCard, LoginAdmin, LoginUser, CreateAccount,BankTransaction;
	private JPanel WelcomePage, WelcomeAdmin, WelcomeUser, CreateAccountP, Transaction;
	private JButton admin, balance,user, admin_login, user_login, withdraw, deposit, transfer, create ;
	private JTextField  AccessName, AccessPassword, AdminName, AdminPassword, UserID, UserPassword,
	WithdrawAmount, DepositAmount, TransferAmountfrom, TransferAmountto,AccNo, NewUserName, NewUserID, NewUserType;
	private JLabel enterAccessName,enterAccessPassword,enterAdminName,enterAdminPassword,enterUserName,
	enterUserPassword,enterNewUserID, enterNewUserType, enterNewUserName,enterUserID, enterWithdraw, enterDeposit, entersource,
	enterdestination;
	private JTextField TransferAmount;

	private static int flag = 0 ;
	private Map<String, String> unamepass, adminpass;
	
	public GUIBank()
	{
		Bank_Welcome_Page();
		credentials();	
	}
	
	public void credentials() {
		
		unamepass = new HashMap<String, String>();
        unamepass.put("ritu", "abc");
        unamepass.put("kitu", "xyz");
        
        
        adminpass = new HashMap<String, String>();
        adminpass.put("rituAd", "abcAd");
        adminpass.put("kituAd", "xyzAd");
	}
	
	public void Bank_Welcome_Page()
	{
		int flag = 0;
		LoginCard= new JFrame(" Welcome to the Bank!");
		LoginCard.setSize(500,500);
        LoginCard.setLayout(new GridLayout(6, 6));
		LoginCard.setVisible(true);
		LoginCard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginCard.addWindowListener(new WindowAdapter() {
        	 
	         public void windowClosing(WindowEvent windowEvent){
	               //System.exit(0);
	        	  //swingControlDemo.prepareGUI();
	        	 Admin_Welcome_Page();
	   
	            }  
		}); 
	         
		
        
		WelcomePage = new JPanel();
        WelcomePage.setLayout(new FlowLayout());
		//LoginCard.setBackground(Color.RED);
		
		
		admin = new JButton("Admin");
		admin.setSize(10, 10);
		
		
		user = new JButton("User");
		user.setSize(10,10);
		

		LoginCard.add(WelcomePage);
		WelcomePage.add(admin);
		WelcomePage.add(user);
		
		admin.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	    
	        	 Admin_Welcome_Page();
	         }
	           });
		
		user.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	        	 
	        	 User_Welcome_Page();
	         }
	           });

		//LoginCard.add(WelcomePage);
		LoginCard.setVisible(true);
		
	
	}
	
	public void Admin_Welcome_Page()
	{
		LoginAdmin= new JFrame(" Welcome Admin!");
		LoginAdmin.setSize(500,500);
		LoginAdmin.setLayout(new GridLayout(6, 6));
		LoginAdmin.setVisible(true);
		LoginAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		WelcomeAdmin = new JPanel();
		WelcomeAdmin.setLayout(new FlowLayout());
        //LoginAdmin.setBackground(Color.RED);
		
		
		admin_login = new JButton("Login");
		admin_login.setSize(10, 10);
		
		enterAdminName= new JLabel("Enter Admin Name",JLabel.RIGHT);
		enterAdminName.setSize(300,200);
		AdminName = new JTextField(10);
		AdminName.setSize(10,50);
		//AccessName.setAlignmentY(0);
		
		enterAdminPassword= new JLabel("Enter Password",JLabel.RIGHT);
		enterAdminPassword.setSize(300,200);
		AdminPassword = new JTextField(10);
		AdminPassword.setSize(10,50);
		
		admin_login.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	        	 String uname  = AdminName.getText();
	             String pass = AdminPassword.getText();
	             
	             
	             if ( adminpass.containsKey(uname)) {
	            	 Create_Account();
	              }
	             else {
	            	 //close window
	             }
	        	 
	         }
	           });
		
	
		/*lab = new JLabel("This is test Label");*/
		LoginAdmin.add(WelcomeAdmin);

		WelcomeAdmin.add(enterAdminName);
		WelcomeAdmin.add(AdminName);
		WelcomeAdmin.add(enterAdminPassword);
		WelcomeAdmin.add(AdminPassword);
		WelcomeAdmin.add(admin_login);
		

		//LoginCard.add(WelcomePage);
		LoginAdmin.setVisible(true);
	}
	
	public void User_Welcome_Page()
	{
		LoginUser= new JFrame(" Welcome User!");
		LoginUser.setSize(500,500);
		LoginUser.setLayout(new GridLayout(6, 6));
		LoginUser.setVisible(true);
		LoginUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		WelcomeUser = new JPanel();
		WelcomeUser.setLayout(new FlowLayout());
        //LoginUser.setBackground(Color.RED);
		
		
		user_login = new JButton("Login");
		user_login.setSize(10, 10);
		
		enterUserName= new JLabel("Enter User ID",JLabel.RIGHT);
		enterUserName.setSize(300,200);
		UserID = new JTextField(10);
		UserID.setSize(10,50);
		//AccessName.setAlignmentY(0);
		
		enterUserPassword= new JLabel("Enter Password",JLabel.RIGHT);
		enterUserPassword.setSize(300,200);
		UserPassword = new JTextField(10);
		UserPassword.setSize(10,50);
		
		
		
		user_login.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	        	 String uname  = UserID.getText();
	             String pass = UserPassword.getText();
	             
	             if ( unamepass.containsKey(uname)) {
	            	 Bank_Transaction();
	              }
	             else {
	            	 //close window
	             }
	         }
	           });
		
		/*lab = new JLabel("This is test Label");*/
		LoginUser.add(WelcomeUser);

		WelcomeUser.add(enterUserName);
		WelcomeUser.add(UserID);
		WelcomeUser.add(enterUserPassword);
		WelcomeUser.add(UserPassword);
		WelcomeUser.add(user_login);
		LoginUser.setVisible(true);
	}
	
	public void bank_communicate(TransactionObject transaction) {
		//all communications using client
		
	}
	public void Create_Account()
	{
		CreateAccount= new JFrame(" Create Account Holder");
		CreateAccount.setSize(500,500);
		CreateAccount.setLayout(new GridLayout(6, 6));
		CreateAccount.setVisible(true);
		CreateAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CreateAccountP = new JPanel();
		CreateAccountP.setLayout(new FlowLayout());
		CreateAccount.setBackground(Color.RED);
		
		
		create = new JButton("Create Account");
		create.setSize(10, 10);
		
		enterNewUserName= new JLabel("Enter New User Name",JLabel.RIGHT);
		enterNewUserName.setSize(300,200);
		enterNewUserID= new JLabel("Enter New User ID",JLabel.RIGHT);
		enterNewUserID.setSize(300,200);
		enterNewUserType= new JLabel("Enter New User Type Checkin/Savings",JLabel.RIGHT);
		enterNewUserType.setSize(300,200);
		NewUserName = new JTextField(10);
		NewUserName.setSize(10,50);
		NewUserID = new JTextField(10);
		NewUserID.setSize(10,50);
		NewUserType = new JTextField(10);
		NewUserType.setSize(10,50);
		

		
		create.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {  
	        	 //adminpass.put(key, value)
	        	 TransactionObject tobj = new TransactionObject();
	        	 tobj.setId(NewUserID.getText());
	             tobj.setName(NewUserName.getText());
	             tobj.setType(NewUserType.getText());
	             tobj.setMessage("ADD");
	             bank_communicate(tobj);
	         }
	           });
		
		/*lab = new JLabel("This is test Label");*/
		CreateAccount.add(CreateAccountP);

		CreateAccountP.add(enterNewUserName);
		CreateAccountP.add(NewUserName);
		CreateAccountP.add(enterNewUserID);
		CreateAccountP.add(NewUserID);
		CreateAccountP.add(enterNewUserType);
		CreateAccountP.add(NewUserType);
		CreateAccountP.add(create);
		

		//LoginCard.add(WelcomePage);
		CreateAccount.setVisible(true);
	}
	
	public void Bank_Transaction()
	{
		BankTransaction= new JFrame(" Create Account Holder");
		BankTransaction.setSize(600,600);
		BankTransaction.setLayout(new GridLayout(6, 6,5,5));
		BankTransaction.setVisible(true);
		BankTransaction.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Transaction = new JPanel();
		Transaction.setLayout(new FlowLayout());
		BankTransaction.setBackground(Color.RED);
		
		
		withdraw = new JButton("Withdraw");
		withdraw.setSize(10, 10);
		//withdraw.add(Box.createHorizontalStrut(10));
		deposit = new JButton("Deposit");
		deposit.setSize(10, 10);
		//deposit.add(Box.createHorizontalStrut(10));
		transfer = new JButton("Transfer");
		transfer.setSize(10, 10);
		
		balance = new JButton("Balance");
		balance.setSize(10, 10);
		
		enterUserID= new JLabel("Enter User ID",JLabel.RIGHT);
		enterUserID.setSize(300,200);
		
		enterWithdraw= new JLabel("Enter amount to be Withdrawn");
		enterWithdraw.setSize(300,200);
		enterDeposit= new JLabel("Enter amount to be deposited");
		enterDeposit.setSize(300,200);
		entersource= new JLabel("Savings/Checkin",JLabel.RIGHT);
		entersource.setSize(300,200);
		enterdestination= new JLabel("Transfer amount",JLabel.RIGHT);
		enterdestination.setSize(300,200);
		
		AccNo = new JTextField(10);
		AccNo.setSize(10,50);
		WithdrawAmount = new JTextField(10);
		WithdrawAmount.setSize(10,50);
		DepositAmount = new JTextField(10);
		DepositAmount.setSize(10,50);
		TransferAmountfrom = new JTextField(10);
		TransferAmountfrom.setSize(10,50);
		TransferAmount = new JTextField(10);
		TransferAmount.setSize(10,50);
		
		withdraw.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {  
	        	 TransactionObject tobj = new TransactionObject();
	        	 tobj.setId(AccNo.getText());
	             tobj.setType(entersource.getText());
	             tobj.setAmount(Float.parseFloat(WithdrawAmount.getText()));
	             tobj.setMessage("WITHDRAW");
	             //tobj.setNum();
	             bank_communicate(tobj);
	         }
	       });
		
		transfer.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {  
	        	 TransactionObject tobj = new TransactionObject();
	        	 tobj.setId(AccNo.getText());
	             tobj.setType(entersource.getText());
	             tobj.setAmount(Float.parseFloat(TransferAmount.getText()));
	             tobj.setMessage("TRANSFER");
	             bank_communicate(tobj);
	         }
	           });
		
		deposit.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {  
	        	 TransactionObject tobj = new TransactionObject();
	        	 tobj.setId(AccNo.getText());
	             tobj.setType(entersource.getText());
	             tobj.setAmount(Float.parseFloat(DepositAmount.getText()));
	             tobj.setMessage("DEPOSIT");
	             bank_communicate(tobj);
	         }
	           });
		
		balance.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {  
	        	 TransactionObject tobj = new TransactionObject();
	        	 tobj.setId(AccNo.getText());
	             tobj.setType(entersource.getText());
	             tobj.setMessage("BALANCE");
	             bank_communicate(tobj);
	         }
	           });
		
		/*lab = new JLabel("This is test Label");*/
		BankTransaction.add(Transaction);

		Transaction.add(enterUserID);
		Transaction.add(AccNo);
		
		Transaction.add(enterWithdraw);
		Transaction.add(WithdrawAmount);
		Transaction.add(withdraw);
		
		Transaction.add(enterDeposit);
		Transaction.add(DepositAmount);
		Transaction.add(deposit);
		
		Transaction.add(entersource);
		Transaction.add(TransferAmountfrom);
		
		
		Transaction.add(enterdestination);
		Transaction.add(TransferAmount);
		Transaction.add(transfer);
		Transaction.add(balance);
		

		//LoginCard.add(WelcomePage);
		BankTransaction.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new GUIBank();
	}

}