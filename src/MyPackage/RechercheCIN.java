package MyPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class RechercheCIN implements ActionListener {

	protected JTabbedPane ong ;
	protected JPanel panAjout;
	 protected JTextField txtCinN,txtNomN,txtPrenomN;
	 protected JComboBox<String> comboSexeN;
	 private JDateChooser calendrierN;
	 private Connection connectBD;
	 protected final String url="jdbc:mysql://localhost/db_etude";
public RechercheCIN(JTextField txtCinN,JTextField txtNomN,JTextField txtPrenomN
		  ,JComboBox<String> comboSexeN,JDateChooser calendrierN,JTabbedPane ong,JPanel panAjout) {
	   
	  this.txtCinN = txtCinN;
	  this.txtNomN = txtNomN;
	  this.txtPrenomN = txtPrenomN;
	  this.comboSexeN = comboSexeN;
	  this.calendrierN = calendrierN;
	  this.ong = ong;
	  this.panAjout = panAjout;
		  
	  }

public void actionPerformed(ActionEvent arg0) {
		
		String txtCIN = null;
		txtCIN = JOptionPane.showInputDialog(null,"<html><i>Veuillez saisir votre CIN</i>","Recherche CIN",JOptionPane.QUESTION_MESSAGE);
		
		try{
  			  Statement satementRechCIN = BoutSuivPrec.getConnectBD().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
  			  ResultSet resultRechCIN=satementRechCIN.executeQuery("SELECT * FROM etudiants where CIN='"+txtCIN+"'");
  			 try{
  			  while(txtCIN.equals("") || txtCIN == null){
  				 txtCIN = JOptionPane.showInputDialog(null,"<html><i>Veuillez saisir votre CIN</i>","Recherche CIN",JOptionPane.QUESTION_MESSAGE);
  			 }
  			if(resultRechCIN.next()){
					Loading m=new Loading();    
					 m.setVisible(true);
					ong.setSelectedComponent(panAjout);
	  				setValuesN(resultRechCIN);
	  				m.setEnabled(false);
	  			  }else{
	  			  JOptionPane.showMessageDialog(null,"<html><i>Vérifiez votre CIN !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
	  			  }
  			  }catch(Exception ee){}
			
  		  }catch (SQLException exceptionRechercheCIN) {
  			System.out.println("exceptionRechercheCIN :" + exceptionRechercheCIN.getMessage());
			System.out.println("SQLState" + exceptionRechercheCIN.getSQLState());
			System.out.println("Error" + exceptionRechercheCIN.getErrorCode());
    		}
			   
			
		}
		


public void setValuesN(ResultSet resultCheck) throws SQLException  {
		
		 txtCinN.setText(resultCheck.getString(1));	
		 txtNomN.setText(resultCheck.getString(2));
		 txtPrenomN.setText(resultCheck.getString(3));
		 calendrierN.setDate(resultCheck.getDate(4));
		 comboSexeN.setSelectedItem(resultCheck.getString(5)); 
		 
		 
	}
	
}


