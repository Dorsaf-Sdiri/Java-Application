package MyPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;


public class BoutonAjout implements ActionListener {

	 protected JButton boutSuiv,boutPrec;
	 protected JTextField txtCinC,txtNomC,txtPrenomC,txtCinN,txtNomN,txtPrenomN,txtKeyWord;
	 protected JComboBox<String> comboSexeC,comboSexeN;
	 private JDateChooser calendrierC,calendrierN;
	 protected JTextFieldDateEditor dateEditorC;
	 protected BgPanel pCentre2,pCentre3;
	 protected final String url="jdbc:mysql://localhost/db_etude";
    
public BoutonAjout(JTextField txtCinC,JTextField txtNomC,JTextField txtPrenomC,JTextField txtCinN,JTextField txtNomN,JTextField txtPrenomN,JTextField txtKeyWord
			  ,JComboBox<String> comboSexeC,JComboBox<String> comboSexeN,JDateChooser calendrierC,JDateChooser calendrierN,
		      JTextFieldDateEditor dateEditorC,BgPanel pCentre2,BgPanel pCentre3) {
		  
		  this.txtCinC = txtCinC;
		  this.txtNomC = txtNomC;
		  this.txtPrenomC = txtPrenomC;
		  this.comboSexeC = comboSexeC;
		  this.calendrierC = calendrierC;
		  this.dateEditorC= dateEditorC;
		  this.txtKeyWord = txtKeyWord;
		  this.txtCinN = txtCinN;
		  this.txtNomN = txtNomN;
		  this.txtPrenomN = txtPrenomN;
		  this.comboSexeN = comboSexeN;
		  this.calendrierN = calendrierN;
		  this.pCentre2 = pCentre2;
		  this.pCentre3 = pCentre3;
		  
	  }
		  public void actionPerformed(ActionEvent arg0) {
		
		int option=JOptionPane.showConfirmDialog(null,"<html><i>Voulez-vous vraiment ajouter cette personne</i>","Ajout",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(option == JOptionPane.YES_OPTION){
			boolean testNom = testChar(txtNomC);
			boolean testPrenom = testChar(txtPrenomC);
			if (testNom == false || testPrenom == false){
						JOptionPane.showMessageDialog(null,"<html><i>Vérifier votre nom et prénom !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
						txtNomC.setText("");
						txtPrenomC.setText("");
				}else if ("".equals(txtCinC.getText())==true){ 
					if("".equals(txtNomC.getText())==true && "".equals(txtPrenomC.getText())==true)
						{JOptionPane.showMessageDialog(null,"<html><i>Veuillez remplir les cases CIN, Nom et Prénom !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
					}else if ("".equals(txtNomC.getText())==true)
						JOptionPane.showMessageDialog(null,"<html><i>Veuillez remplir les cases CIN et Nom !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
					else if ("".equals(txtPrenomC.getText())==true)
						JOptionPane.showMessageDialog(null,"<html><i>Veuillez remplir les cases CIN et Prénom !</i>","WARNNG!",JOptionPane.WARNING_MESSAGE);
					else JOptionPane.showMessageDialog(null,"Veuillez remplir la case CIN !","WARNING!",JOptionPane.WARNING_MESSAGE);
				}else{
					
					try{
					  try{
						Statement statementCIN = BoutSuivPrec.getConnectBD().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
       					ResultSet resultCheckCIN=statementCIN.executeQuery("SELECT * FROM etudiants ");
						
					if ( txtCinC.getText().length()< 8){
						if( "".equals(txtNomC.getText())==true && "".equals(txtPrenomC.getText())==true){
								JOptionPane.showMessageDialog(null,"<html><i>La taille de CIN doit contenir 8 chiffres</i> \n"+ "<html><i>Veuillez également remplir les cases Nom et Prénom.</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
							}else if ("".equals(txtNomC.getText())==true){
								JOptionPane.showMessageDialog(null,"<html><i>La taille de CIN doit contenir 8 chiffres</i> \n"+"<html><i>Veillez également remplir la case Nom.</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
							}else if ("".equals(txtPrenomC.getText())==true){
								JOptionPane.showMessageDialog(null,"<html><i>La taille de CIN doit contenir 8 chiffres</i>\n"+"<html><i>Veillez également remplir la case Prénom.</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
							}else {JOptionPane.showMessageDialog(null,"<html><i>La taille de CIN doit contenir 8 chiffres</i>","WARNING!",JOptionPane.WARNING_MESSAGE);}
						}else{
							
							try{
								Integer.parseInt(txtCinC.getText());
								if ( txtCinC.getText().length()< 8)
								{
								if( "".equals(txtNomC.getText())==true && "".equals(txtPrenomC.getText())==true)
									JOptionPane.showMessageDialog(null,"<html><i>Veuillez saisir un CIN de 8 chiffres !</i>\n"+ "<html><i>Les cases Nom et Prénom sont vides !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
								else if ("".equals(txtNomC.getText())==true)
									JOptionPane.showMessageDialog(null,"<html><i>Veuillez saisir un CIN de 8 chiffres !</i>\n"+"<html><i>La case Nom est vide !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
								else if ("".equals(txtPrenomC.getText())==true)
									JOptionPane.showMessageDialog(null,"<html><i>Veuillez saisir un CIN de 8 chiffres !</i>\n"+"<html><i>La case Prénom est vide !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
								else JOptionPane.showMessageDialog(null,"<html><i>Veuillez saisir un CIN de 8 chiffres !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
								txtCinC.setText("");
								
							}else{ 
								Statement statementCIN1 = BoutSuivPrec.getConnectBD().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		       					ResultSet resultCheckCIN1=statementCIN1.executeQuery("SELECT * FROM etudiants where CIN ="+txtCinC.getText());
								if (resultCheckCIN1.next()) {JOptionPane.showMessageDialog(null,"<html><i>Etudiant dejà existant !</i>\n"+"<html><i>Vérifiez votre CIN !</i>","Attention!",JOptionPane.WARNING_MESSAGE);
								txtCinC.setText("");}
								else if ("".equals(txtNomC.getText())==true)
								{if ("".equals(txtPrenomC.getText())==true)
										JOptionPane.showMessageDialog(null,"<html><i>Les cases Nom et Prénom sont vides !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
									else JOptionPane.showMessageDialog(null,"<html><i>La case Nom est vide !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
								}else if ("".equals(txtPrenomC.getText())==true)
									JOptionPane.showMessageDialog(null,"<html><i>La case Prénom est vide !</i>","WARNING!",JOptionPane.WARNING_MESSAGE);
								
								else { 
									
									
									if (calendrierC.getDate()==null) {
										resultCheckCIN.afterLast();
										BoutSuivPrec.getResultCheck().moveToInsertRow();
										BoutSuivPrec.getResultCheck().updateString(1, txtCinC.getText());
										BoutSuivPrec.getResultCheck().updateString(2, txtNomC.getText());
										BoutSuivPrec.getResultCheck().updateString(3, txtPrenomC.getText());
								    	String a =(String) comboSexeC.getSelectedItem();
								    	BoutSuivPrec.getResultCheck().updateString(4, "1990-01-01");
								    	BoutSuivPrec.getResultCheck().updateString(5,a );
								    	BoutSuivPrec.getResultCheck().insertRow();
								    	
								    	
								    	Loading m=new Loading();    
									    m.setVisible(true);
									    m.setEnabled(false);
										m.setVisible(false);
										if(resultCheckCIN.isFirst()){
											
										}
								    }
										else{
											
										resultCheckCIN.afterLast();
										BoutSuivPrec.getResultCheck().moveToInsertRow();
										BoutSuivPrec.getResultCheck().updateString(1, txtCinC.getText());
										BoutSuivPrec.getResultCheck().updateString(2, txtNomC.getText());
										BoutSuivPrec.getResultCheck().updateString(3, txtPrenomC.getText());
									    String a =(String) comboSexeC.getSelectedItem();
									    BoutSuivPrec.getResultCheck().updateString(4, dateEditorC.getText());
									    BoutSuivPrec.getResultCheck().updateString(5,a );
									    BoutSuivPrec.getResultCheck().insertRow();
									    Loading m=new Loading();    
										m.setVisible(true);
										m.setEnabled(false);
										m.setVisible(false);
											
										}
									if(txtCinN.getText().equals("")){
										setValuesN(BoutSuivPrec.getResultCheck());
									}
									BoutSuivPrec.getBoutSuiv().setEnabled(true);
									
									if (comboSexeC.getSelectedItem().toString()=="Masculin") {
										
										JOptionPane.showMessageDialog(null,"<html><i>Etudiant ajouté avec succès !</i>","DONE!",JOptionPane.INFORMATION_MESSAGE);
									
									}else {
										
										JOptionPane.showMessageDialog(null,"<html><i>Etudiante ajoutée avec succès !</i>","DONE!",JOptionPane.INFORMATION_MESSAGE);
										
									}
										}
										valeursInitialesC();
										
									}
								
								  try{
										Statement statmentRechNom = BoutSuivPrec.getConnectBD().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
										ResultSet resultRechNom = statmentRechNom.executeQuery("select * from etudiants where NOM like '%" + txtKeyWord.getText() + "%'");
										
									if(txtKeyWord.getText().equals("")){
									
											pCentre2.removeAll();
											pCentre2.repaint();
											pCentre2.revalidate();
											
									}else{
												  
													
										if(resultRechNom.next()){
												
											    ResultSetTableModel rtm = new ResultSetTableModel( resultRechNom );
												TablePanel tablePanel = new TablePanel( rtm );
												pCentre2.removeAll();
												pCentre2.repaint();
												pCentre2.revalidate();
												pCentre2.add(new JScrollPane(tablePanel));
													
										}else{
											
												pCentre2.removeAll();
												pCentre2.repaint();
												pCentre2.revalidate();
												
												}
												
												}
										 
										}catch(Exception tableException){
										}
								  try{
									  	pCentre3.removeAll();
										pCentre3.repaint();
										pCentre3.revalidate();
										GridBagLayout gridStat=new GridBagLayout();
										GridBagConstraints contraintesStat = new GridBagConstraints(); 
										pCentre3.setLayout(gridStat);
										pCentre3.removeAll();
										pCentre3.repaint();
										pCentre3.revalidate();
										String s = "Masculin";
										String s1 = "Féminin";
										
										  Statement satementStat = BoutSuivPrec.getConnectBD().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
										  
										  ResultSet resultStat=satementStat.executeQuery("SELECT count(*) AS count_F FROM etudiants where SEXE='"+s1+"'");
										  resultStat.next();
										  double countF = resultStat.getInt("count_F");
										  int countF1 = resultStat.getInt("count_F");
										  
										  ResultSet resultStat1=satementStat.executeQuery("SELECT count(*) AS count_H FROM etudiants where SEXE='"+s+"'");
										  resultStat1.next();
										  double countH = resultStat1.getInt("count_H");
										  int countH1 = resultStat1.getInt("count_H");
										  
										  DefaultPieDataset pieDataset = new DefaultPieDataset(); 
										 
										  pieDataset.setValue("Homme en %", (countH*100)/(countH+countF));
										  pieDataset.setValue("Femme en %", (countF*100)/(countH+countF)); 
										  
										  JFreeChart pieChart = ChartFactory.createPieChart("Distribution des étudiants selon le sexe", pieDataset, true, true, true); 
										 
										  PiePlot piePlot = (PiePlot) pieChart.getPlot();
										  piePlot.setExplodePercent(1, 0.5); 
										 
										  ChartPanel cPanel = new ChartPanel(pieChart); 
										  
										  contraintesStat.anchor = GridBagConstraints.WEST;
										  contraintesStat.insets = new Insets(5,5,5,5);
										  
										  int count = countF1 + countH1;
										  JLabel nbT = new JLabel("Nombre total = "+count+"");
										  nbT.setForeground(Color.WHITE);
										  nbT.setFont(new Font("",Font.ITALIC, 14));
										  
										  JLabel nbH = new JLabel("Nombre masculin = "+countH1+"");
										  nbH.setForeground(Color.WHITE);
										  nbH.setFont(new Font("",Font.ITALIC, 14));
										  
										  JLabel nbF = new JLabel("Nombre Féminin = "+countF1+"");
										  nbF.setForeground(Color.WHITE);
										  nbF.setFont(new Font("",Font.ITALIC, 14));
										 
										  contraintesStat.gridx=0;
										  contraintesStat.gridy=0;
										  gridStat.setConstraints(cPanel, contraintesStat);
										  pCentre3.add(cPanel); 
										  
										  contraintesStat.gridx=0;
										  contraintesStat.gridy=2;
										  gridStat.setConstraints(nbT, contraintesStat);
										  pCentre3.add(nbT); 
										  
										  contraintesStat.gridx=0;
										  contraintesStat.gridy=3;
										  gridStat.setConstraints(nbF, contraintesStat);
										  pCentre3.add(nbF); 
										  
										  contraintesStat.gridx=0;
										  contraintesStat.gridy=4;
										  gridStat.setConstraints(nbH, contraintesStat);
										  pCentre3.add(nbH); 
										  
										  
									}catch(Exception ee){
										System.out.println("exceptionRechercheCIN :" + ee.getMessage());
									}
							}catch(NumberFormatException exceptionCIN){
								JOptionPane.showMessageDialog(null,"<html><i>Check your CIN !</i>","ERROR!",JOptionPane.ERROR_MESSAGE);
								System.out.println("exceptionCIN : " + exceptionCIN.getMessage());
    						};
							}
					}catch(Exception connexion){
						valeursInitialesC();
					}
						}catch (Exception exceptionAjout){
						JOptionPane.showMessageDialog(null,"L'opération ajout n'a pas été établie","ERROR!",JOptionPane.ERROR_MESSAGE);	
						System.out.println("exceptionAjout :" + exceptionAjout.getMessage());
						valeursInitialesC();
						}
			
					
					}
			
		}
	}
		  
boolean testChar(JTextField txt){
		char[] txtArray = txt.getText().toCharArray();
		Boolean charTest = true ;
		for (char charTxt : txtArray) {
		     if (Character.isDigit(charTxt)) {charTest = false;
		     }
		     }
		return   charTest;  
	}

public void valeursInitialesC() {
		 txtCinC.setText("");	
		 txtNomC.setText("");
		 txtPrenomC.setText("");
		 comboSexeC.setSelectedItem("Masculin");
		 dateEditorC.setText(null);
	}


public void setValuesN(ResultSet resultCheck) throws SQLException  {
	
	 txtCinN.setText(resultCheck.getString(1));	
	 txtNomN.setText(resultCheck.getString(2));
	 txtPrenomN.setText(resultCheck.getString(3));
	 calendrierN.setDate(resultCheck.getDate(4));
	 comboSexeN.setSelectedItem(resultCheck.getString(5)); 
	 
	 
}
	
	}
