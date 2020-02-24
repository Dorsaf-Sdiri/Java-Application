package MyPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class BoutonSuprimer implements ActionListener {

	 protected JTextField txtCinN,txtNomN,txtPrenomN,txtKeyWord;
	 protected JComboBox<String> comboSexeN;
	 private JDateChooser calendrierN;
	 private JButton boutSuiv,boutPrec;
	 protected JTextFieldDateEditor dateEditorN;
	 protected final String url="jdbc:mysql://localhost/db_etude";
	 protected BgPanel pCentre2,pCentre3;
public BoutonSuprimer(JTextField txtCinN,JTextField txtNomN,JTextField txtPrenomN,JTextField txtKeyWord
		  ,JComboBox<String> comboSexeN,JDateChooser calendrierN,JTextFieldDateEditor dateEditorN,JButton boutSuiv,JButton boutPrec,BgPanel pCentre2,BgPanel pCentre3) {
	   
	  this.txtCinN = txtCinN;
	  this.txtNomN = txtNomN;
	  this.txtPrenomN = txtPrenomN;
	  this.comboSexeN = comboSexeN;
	  this.calendrierN = calendrierN;
	  this.dateEditorN = dateEditorN;
	  this.boutSuiv= boutSuiv;
	  this.boutPrec= boutPrec;
	  this.txtKeyWord = txtKeyWord;
	  this.pCentre2 = pCentre2;
	  this.pCentre3 = pCentre3;
	  	  
	  }

public void actionPerformed(ActionEvent arg0) {
		 int option=JOptionPane.showConfirmDialog(null,"<html><i>Voulez-vous vraiment supprimer cette personne</i>","Suppression",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.YES_OPTION){
				try{ 
				 Statement statementSup = BoutSuivPrec.getConnectBD().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				 statementSup.executeUpdate(" DELETE FROM etudiants WHERE CIN=" + txtCinN.getText());
				 try{
				 Loading m=new Loading();    
				 m.setVisible(true);
				 JOptionPane.showMessageDialog(null,"<html><i>Etudiant supprimé !</i>","DONE!",JOptionPane.INFORMATION_MESSAGE);
				 m.setEnabled(false);
				 Statement statementSup1 = BoutSuivPrec.getConnectBD().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				 ResultSet resultSup = statementSup1.executeQuery("select * from etudiants");

				 if( resultSup.next()){
					 
					 resultSup.absolute(1);
					 
					 
				 setValuesN(resultSup);
				 JButton bout = new JButton();
					bout.addActionListener(new BoutSuivPrec(txtCinN,txtNomN,txtPrenomN,comboSexeN,calendrierN,boutSuiv,boutPrec));
					BoutSuivPrec.getBoutSuiv().setEnabled(true);
					BoutSuivPrec.getBoutPrec().setEnabled(false);
				 }else{
					
					 valeursInitialesN();
					 JButton bout = new JButton();
						bout.addActionListener(new BoutSuivPrec(txtCinN,txtNomN,txtPrenomN,comboSexeN,calendrierN,boutSuiv,boutPrec));
						BoutSuivPrec.getBoutSuiv().setEnabled(false);
						BoutSuivPrec.getBoutPrec().setEnabled(false);
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
			}catch( SQLException exceptionSup){
				System.out.println("exceptionSup : "+exceptionSup.getMessage());
			}
			}catch(Exception exc ){
				}
			}
	    
			
		}
		

  public void setValuesN(ResultSet resultCheck) throws SQLException  {
		
		 txtCinN.setText(resultCheck.getString(1));	
		 txtNomN.setText(resultCheck.getString(2));
		 txtPrenomN.setText(resultCheck.getString(3));
		 calendrierN.setDate(resultCheck.getDate(4));
		 comboSexeN.setSelectedItem(resultCheck.getString(5)); 
		 
		 
	}

public void valeursInitialesN() {
		 txtCinN.setText("");	
		 txtNomN.setText("");
		 txtPrenomN.setText("");
		 comboSexeN.setSelectedItem("Masculin");
		 dateEditorN.setText(null);
	}

}



