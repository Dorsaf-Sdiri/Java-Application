package MyPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class BoutSuivPrec implements ActionListener {

	 protected  static  JButton boutSuiv;
	 protected  static JButton boutPrec;
	 protected JTextField txtCinN,txtNomN,txtPrenomN;
	 protected JComboBox<String> comboSexeN;
	 protected  JDateChooser calendrierN;
	 protected  static Connection connectBD;
	 protected  static Statement statementBD; 
	 protected static ResultSet resultCheck; 
	 protected final String url="jdbc:mysql://localhost/db_etude";
	  
public BoutSuivPrec(JTextField txtCinN,JTextField txtNomN,JTextField txtPrenomN
		  ,JComboBox<String> comboSexeN,JDateChooser calendrierN,
			JButton boutSuiv,JButton boutPrec) {
	   
	  this.txtCinN = txtCinN;
	  this.txtNomN = txtNomN;
	  this.txtPrenomN = txtPrenomN;
	  this.comboSexeN = comboSexeN;
	  this.calendrierN = calendrierN;
	  this.boutSuiv = boutSuiv;
	  this.boutPrec = boutPrec;
	  try{
	  connectionBD();
	  }catch(SQLException exceptionConnect){
		  System.out.println("exceptionConnect :"+ exceptionConnect.getMessage());
	  }
}

public void actionPerformed(ActionEvent arg0) {

 if( arg0.getSource() == boutSuiv){	
	try{
		try {
			
			if(resultCheck.next()){
					if(resultCheck.isFirst()){
						resultCheck.next();
						setValuesN(resultCheck);
						boutPrec.setEnabled(true);
						
					}else{
							setValuesN(resultCheck);
							boutPrec.setEnabled(true);
						}
			  }else{
				  resultCheck.last();
				  setValuesN(resultCheck);
					boutSuiv.setEnabled(false);
						}
		} catch (SQLException e) {
		}
	
	}catch(Exception zz){
		
	}
 	}else if(arg0.getSource() == boutPrec){
 		
 	 
		try {
			if(resultCheck.previous()){
			  		if(resultCheck.isLast()){
			  			resultCheck.previous();
			  			setValuesN(resultCheck);
						boutSuiv.setEnabled(true);
			  		}else{
			  			setValuesN(resultCheck);
						boutSuiv.setEnabled(true);
			  		}
			}else{
				resultCheck.first();
				setValuesN(resultCheck);
					boutPrec.setEnabled(false);
				}
		} catch (SQLException e) {
		}
						
						}
		
	}
	
public Connection connectionBD() throws SQLException{
		
	try {
		connectBD = DriverManager.getConnection(url,"root","");
		statementBD = connectBD.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		resultCheck= statementBD.executeQuery(" select * from etudiants");	
		System.out.println(" Connection bien établie ");
		return connectBD;
	} catch  (Exception exceptionConnect){
		   System.out.println("exceptionConnect  : " + exceptionConnect.getMessage());
	}
	return connectBD;
	}
  public void setValuesN(ResultSet resultCheck) throws SQLException  {
		 txtCinN.setText(resultCheck.getString(1));	
		 txtNomN.setText(resultCheck.getString(2));
		 txtPrenomN.setText(resultCheck.getString(3));
		 calendrierN.setDate(resultCheck.getDate(4));
		 String a = resultCheck.getString(5);
		 comboSexeN.setSelectedItem(a ); 
}

public static ResultSet getResultCheck() {
	return resultCheck;
}

public static void setResultCheck(ResultSet resultCheck) {
	BoutSuivPrec.resultCheck = resultCheck;
}

public static  JButton getBoutSuiv() {
	return boutSuiv;
}


public static JButton getBoutPrec() {
	return boutPrec;
}


public static Statement getStatementBD() {
	return statementBD;
}

public static void setStatementBD(Statement statementBD) {
	BoutSuivPrec.statementBD = statementBD;
}

public static Connection getConnectBD() {
	return connectBD;
}


  
	}



