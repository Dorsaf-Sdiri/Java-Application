package MyPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BoutonOK implements ActionListener {

	 protected BgPanel pCentre2;
	 protected JTextField txtKeyWord;
	 protected final String url="jdbc:mysql://localhost/db_etude";
	  
public BoutonOK(JTextField txtKeyWord,BgPanel pCentre2) {
	   this.txtKeyWord = txtKeyWord;
		  this.pCentre2 = pCentre2;
	 }
	
public void actionPerformed(ActionEvent arg0) {
	try{	 
	  try{
		Statement statmentRechNom = BoutSuivPrec.getConnectBD().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet resultRechNom = statmentRechNom.executeQuery("select * from etudiants where NOM like '%" + txtKeyWord.getText() + "%'");
		
			if(txtKeyWord.getText().equals("")){
				Loading m=new Loading();    
			    m.setVisible(true);
			pCentre2.removeAll();
			pCentre2.repaint();
			pCentre2.revalidate();
			m.setEnabled(false);
				JOptionPane.showMessageDialog(null,"<html><i>Pas de mot clé !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
				}else{
				  
					
					if(resultRechNom.next()){
						ResultSetTableModel rtm = new ResultSetTableModel( resultRechNom );
						
						Loading m=new Loading();    
					    m.setVisible(true);
					TablePanel tablePanel = new TablePanel( rtm );
					pCentre2.removeAll();
					pCentre2.repaint();
					pCentre2.revalidate();
					pCentre2.add(new JScrollPane(tablePanel));
					
					m.setEnabled(false);
					}else{
						Loading m=new Loading();    
					    m.setVisible(true);
					    m.setEnabled(false);
						JOptionPane.showMessageDialog(null,"<html><i>Pas de résultat pour ce mot clé !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
					pCentre2.removeAll();
				pCentre2.repaint();
				pCentre2.revalidate();
				
				
				}
				
				}
		 
		}catch(Exception connexion){
				   JOptionPane.showMessageDialog(null,"<html><i>Vous n'êtes pas connéctés à votre base  !</i>\n"+"<html><i>Veuillez redemarrer l'application et vérifiez votre connextion !</i>","ERROR!",JOptionPane.ERROR_MESSAGE);	   
		}
	}catch(Exception exceptionTab){
		 System.out.println("exceptionSup : "+exceptionTab.getMessage());
		   JOptionPane.showMessageDialog(null,"<html><i>Vérifiez votre base ou votre mot clé !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);   
		  
	}		   
			
		}
		

	
	}

