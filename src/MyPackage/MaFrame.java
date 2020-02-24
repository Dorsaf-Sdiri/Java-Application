
package MyPackage;


///Importation des packages///

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import org.jfree.chart.*; 
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;
public class MaFrame extends JFrame {
	
///Définition des objets///	 

	 private JMenuBar barreMenus;
	 private JMenu menuEtudiant,menuCouleurs;
	 private JMenuItem recherche,quitter,rechercheCIN,rechercheNom,bleu,rouge,vert;
	 protected JTabbedPane ong ;
	 protected JButton boutSuiv,boutPrec,boutModif,boutSup,boutAjout,boutAnnulerAjout,boutOK,boutAnnulerRech;
	 protected JLabel lab,labCIN,labNom,labPrenom,labSexe,labDate,labKeyWord;
	 protected GridBagLayout gridAjoutN,gridAjoutC,gridAjoutE,gridResC;
	 protected JTextField txtCinC,txtNomC,txtPrenomC,txtCinN,txtNomN,txtPrenomN,txtKeyWord;
	 protected JComboBox<String> comboSexeN,comboSexeC;
	 protected BorderLayout bordAjout,bordRes,bordStat;
	 protected JPanel pNord,pEst,panRes,panAjout,pNord2,pEast2,panStat;
	 protected BgPanel pCentre,pCentre2,pCentre3;
	 protected FlowLayout flowResN;
	 private JDateChooser calendrierN,calendrierC;
	 protected JTextFieldDateEditor dateEditorC,dateEditorN;
	 protected final String url="jdbc:mysql://localhost/db_etude";
	 protected ChartPanel cPanel;
	 
public MaFrame() throws SQLException {

///Nom et Caractéristiques de l'application///
	
super ();


ImageIcon img = new ImageIcon("data.png");
setIconImage(img.getImage());
setTitle("Gestion des étudiants");
setSize(900,800);
setLocation(100,100);

///Menubar///

barreMenus = new JMenuBar();
setJMenuBar(barreMenus);

///Menu Etudiant///

menuEtudiant= new JMenu("Etudiant");
ImageIcon im1 = new ImageIcon("id.png");
menuEtudiant.setIcon(im1);

barreMenus.add(menuEtudiant);
recherche= new JMenu("Recherche");
menuEtudiant.add(recherche);
menuEtudiant.addSeparator();
quitter = new JMenuItem("Quitter");
menuEtudiant.add(quitter);

///Menu Recherche///

rechercheCIN = new JMenuItem("Recherche CIN");
recherche.add(rechercheCIN);

rechercheNom = new JMenuItem("Recheche NOM");
recherche.add(rechercheNom);

///Menu Couleurs///

menuCouleurs= new JMenu("Couleurs");
ImageIcon im2 = new ImageIcon("colors.png");
menuCouleurs.setIcon(im2);
barreMenus.add(menuCouleurs);
menuCouleurs.addSeparator();
bleu = new JMenuItem("bleu");
bleu.setBackground(Color.BLUE);
menuCouleurs.add(bleu);
bleu.setText("<html><font color = #FFFFFF >bleu</font></html>");
menuCouleurs.addSeparator();

rouge = new JMenuItem();
rouge.setText("<html><font color = #FFFFFF >Rouge</font></html>");
menuCouleurs.add(rouge);
rouge.setBackground(Color.RED);
menuCouleurs.addSeparator();
vert = new JMenuItem("vert");
menuCouleurs.add(vert);
vert.setBackground(Color.GREEN);
vert.setText("<html><font color = #FFFFFF >vert</font></html>");

/// Ajout des onglets///

ong = new JTabbedPane(JTabbedPane.TOP);

panAjout= new JPanel();
ong.addTab("Ajout & Mise à jour ",panAjout);

panRes= new JPanel();
ong.addTab("Résultat Recherche", panRes);

panStat= new JPanel();
ong.addTab("Statistiques", panStat);

this.add(ong);

bordAjout = new BorderLayout();
bordRes = new BorderLayout();
bordStat = new BorderLayout();

panAjout.setLayout(bordAjout);
panRes.setLayout(bordRes);
panStat.setLayout(bordStat);

///Initialisation des conteneurs///

///Onglet Ajout & Mise à jour ///

pNord=new JPanel();
pCentre=new BgPanel();
pEst=new JPanel();


pNord.setBackground(Color.blue);
panAjout.add(pNord,"North");

panAjout.add(pCentre, "Center");

pEst.setBackground(Color.WHITE);
panAjout.add(pEst, "East");



///Creation et mise en place des objets///

///panneau 1///

gridAjoutN=new GridBagLayout();
GridBagConstraints contraintesN = new GridBagConstraints();
pNord.setLayout(gridAjoutN);

contraintesN.insets = new Insets(5, 5, 5, 5);
txtCinN = new JTextField(10);
txtCinN.setFocusable(false);
contraintesN.gridx=0;
contraintesN.gridy=0;
gridAjoutN.setConstraints(txtCinN, contraintesN);
pNord.add(txtCinN);

contraintesN.insets = new Insets(5, 5, 5, 5);
txtNomN = new JTextField(10);
contraintesN.gridx=1;
contraintesN.gridy=0;
gridAjoutN.setConstraints(txtNomN, contraintesN);
pNord.add(txtNomN);

txtPrenomN = new JTextField(10);
contraintesN.gridx=2;
contraintesN.gridy=0;
gridAjoutN.setConstraints(txtPrenomN, contraintesN);
pNord.add(txtPrenomN);

calendrierN = new JDateChooser();
calendrierN.setDateFormatString("yyyy-MM-dd");
dateEditorN = (JTextFieldDateEditor)calendrierN.getDateEditor();
dateEditorN.setHorizontalAlignment(JTextField.CENTER);
calendrierN.setFont(new Font("Dialog", Font.ITALIC, 11));
calendrierN.setSize(new Dimension(105, 0));
contraintesN.gridx=3;
contraintesN.gridy=0;
gridAjoutN.setConstraints(calendrierN, contraintesN);
pNord.add(calendrierN);


String[] gender={"Masculin","Féminin"};
comboSexeN=new JComboBox<String>(gender);
contraintesN.gridx=4;
contraintesN.gridy=0;
gridAjoutN.setConstraints(comboSexeN, contraintesN);
pNord.add(comboSexeN);


boutModif = new JButton("Modifier",new ImageIcon("modif.png"));
boutSup = new JButton("Supprimer",new ImageIcon("delete.png"));
boutPrec = new JButton(new ImageIcon("previous.png"));
boutSuiv = new JButton(new ImageIcon("next.png"));

contraintesN.gridx=5;
contraintesN.gridy=0;
gridAjoutN.setConstraints(boutModif, contraintesN);
pNord.add(boutModif);

contraintesN.gridx=6;
contraintesN.gridy=0;
gridAjoutN.setConstraints(boutSup, contraintesN);
pNord.add(boutSup);

contraintesN.gridx=1;
contraintesN.gridy=1;
gridAjoutN.setConstraints(boutPrec, contraintesN);
pNord.add(boutPrec);

contraintesN.gridx=4;
contraintesN.gridy=1;
gridAjoutN.setConstraints(boutSuiv, contraintesN);
pNord.add(boutSuiv);

///panneau 2///

gridAjoutC=new GridBagLayout();
GridBagConstraints contraintesC = new GridBagConstraints();
pCentre.setLayout(gridAjoutC);

contraintesC.insets = new Insets(5, 5, 5, 5);
contraintesC.anchor = GridBagConstraints.WEST;

labCIN=new JLabel();
labCIN.setText("<html><font color = #FFFFFF >CIN</font></html>");
labCIN.setFont(new Font("",Font.ITALIC, 14));
txtCinC = new JTextField(10);
txtCinC.setFont(new Font("",Font.ITALIC, 14));

labNom=new JLabel("Nom");
labNom.setText("<html><font color = #FFFFFF >Nom</font></html>");
labNom.setFont(new Font("",Font.ITALIC, 14));
txtNomC = new JTextField(10);
txtNomC.setFont(new Font("",Font.ITALIC, 14));

labPrenom=new JLabel("Prénom");
labPrenom.setText("<html><font color = #FFFFFF >Prénom</font></html>");
labPrenom.setFont(new Font("",Font.ITALIC, 14));
txtPrenomC = new JTextField(10);
txtPrenomC.setFont(new Font("",Font.ITALIC, 14));

labSexe=new JLabel("sexe");
labSexe.setText("<html><font color = #FFFFFF >Sexe</font></html>");
labSexe.setFont(new Font("",Font.ITALIC, 14));

labDate=new JLabel("Date de naissance");
labDate.setText("<html><font color = #FFFFFF >Date de naissance</font></html>");
labDate.setFont(new Font("",Font.ITALIC, 14));

String[] gender1= {"Masculin","Féminin"};
comboSexeC=new JComboBox<String>(gender1);


contraintesC.gridx=0;
contraintesC.gridy=0;
gridAjoutC.setConstraints(labCIN, contraintesC);
pCentre.add(labCIN);

contraintesC.gridx=1;
contraintesC.gridy=0;
gridAjoutC.setConstraints(txtCinC, contraintesC);
pCentre.add(txtCinC);

contraintesC.gridx=0;
contraintesC.gridy=1;
gridAjoutC.setConstraints(labNom, contraintesC);
pCentre.add(labNom);

contraintesC.gridx=1;
contraintesC.gridy=1;
gridAjoutC.setConstraints(txtNomC, contraintesC);
pCentre.add(txtNomC);

contraintesC.gridx=2;
contraintesC.gridy=1;
gridAjoutC.setConstraints(labPrenom, contraintesC);
pCentre.add(labPrenom);
contraintesC.gridx=3;
contraintesC.gridy=1;
gridAjoutC.setConstraints(txtPrenomC, contraintesC);
pCentre.add(txtPrenomC);

contraintesC.gridx=2;
contraintesC.gridy=0;
gridAjoutC.setConstraints(labSexe, contraintesC);
pCentre.add(labSexe);
contraintesC.gridx=3;
contraintesC.gridy=0;
gridAjoutC.setConstraints(comboSexeC, contraintesC);
pCentre.add(comboSexeC);

contraintesC.gridx=0;
contraintesC.gridy=2;
gridAjoutC.setConstraints(labDate, contraintesC);
pCentre.add(labDate);

contraintesC.gridx=1;
contraintesC.gridy=2;
calendrierC = new JDateChooser();
calendrierC.setDateFormatString("yyyy-MM-dd");
dateEditorC = (JTextFieldDateEditor)calendrierC.getComponent(1);
dateEditorC.setHorizontalAlignment(JTextField.CENTER);
calendrierC.setFont(new Font("Dialog", Font.ITALIC, 11));
calendrierC.setSize(new Dimension(105, 0));
gridAjoutC.setConstraints(calendrierC, contraintesC);
pCentre.add(calendrierC);

///panneau 3///

gridAjoutE=new GridBagLayout();
GridBagConstraints contraintesE = new GridBagConstraints();
pEst.setLayout(gridAjoutE);

boutAjout = new JButton("Ajouter",new ImageIcon("add.png"));
boutAnnulerAjout = new JButton("Annuler",new ImageIcon("annuler.png"));
contraintesE.insets = new Insets(0,0,5,0);
contraintesE.anchor = GridBagConstraints.PAGE_START;
contraintesE.gridx=0;
contraintesE.gridy=0;
gridAjoutE.setConstraints(boutAjout, contraintesE);
pEst.add(boutAjout);

contraintesE.gridx=0;
contraintesE.gridy=2;
gridAjoutE.setConstraints(boutAnnulerAjout, contraintesE);
pEst.add(boutAnnulerAjout);


///onglet resultat recherche///

pNord2=new JPanel();
pCentre2=new BgPanel();
pEast2=new JPanel();

pNord2.setBackground(Color.blue);
panRes.add(pNord2,"North");

panRes.add(pCentre2, "Center");

panRes.add(pEast2, "East");

///Creation et mise en place des objets///
///panneau 1///

gridResC=new GridBagLayout();
pCentre2.setLayout(gridResC);

flowResN=new FlowLayout();
pNord2.setLayout(flowResN);

labKeyWord=new JLabel("KeyWord");
txtKeyWord = new JTextField(10);

pNord2.add(labKeyWord);
pNord2.add(txtKeyWord);

boutOK = new JButton("OK");
boutAnnulerRech = new JButton("Annuler");
pNord2.add(boutOK);
pNord2.add(boutAnnulerRech);

///panneau 2//
lab = new JLabel(new ImageIcon("logo_ESSAIT.PNG"));
pEast2.add(lab);

///Onglet Ajout & Mise à jour ///

pCentre3=new BgPanel();
panStat.add(pCentre3);

///Appel des actions///

RechercheCIN rechCIN = new RechercheCIN(txtCinN,txtNomN,txtPrenomN,comboSexeN,calendrierN,ong,panAjout);
rechercheCIN.addActionListener(rechCIN);

BoutonSuprimer boutonSup = new BoutonSuprimer(txtCinN,txtNomN,txtPrenomN,txtKeyWord,comboSexeN,calendrierN,dateEditorN,boutSuiv,boutPrec,pCentre2,pCentre3);
boutSup.addActionListener(boutonSup);

BoutonModifier BoutModif = new BoutonModifier(txtCinN,txtNomN,txtPrenomN,txtKeyWord,comboSexeN,calendrierN,dateEditorN,pCentre2,pCentre3);
boutModif.addActionListener(BoutModif);

BoutonAjout boutonAjout = new BoutonAjout(txtCinC,txtNomC,txtPrenomC,txtCinN,txtNomN,txtPrenomN,txtKeyWord,comboSexeC,comboSexeN,calendrierC,calendrierN,dateEditorC,pCentre2,pCentre3);
boutAjout.addActionListener(boutonAjout);

BoutonOK boutonOK = new BoutonOK(txtKeyWord,pCentre2);
boutOK.addActionListener(boutonOK);

BoutSuivPrec boutonSuiv = new BoutSuivPrec(txtCinN,txtNomN,txtPrenomN,comboSexeN,calendrierN,boutSuiv,boutPrec);
boutSuiv.addActionListener(boutonSuiv);
boutPrec.addActionListener(boutonSuiv);

quitter.addActionListener(new MesActions());
bleu.addActionListener((ActionListener) new MesActions());
rouge.addActionListener(new MesActions());
vert.addActionListener(new MesActions());
rechercheNom.addActionListener(new MesActions());

boutAnnulerAjout.addActionListener(new MesActions());
boutAnnulerRech.addActionListener(new MesActions());

// désactivation des boutons ajoute et annuler //

boutAjout.setEnabled(false);
boutAnnulerAjout.setEnabled(false);
txtCinC.addKeyListener(new KeyAdapter() {
public void keyTyped(KeyEvent e) { 
	boutAjout.setEnabled(true);
	boutAnnulerAjout.setEnabled(true);
    if (txtCinC.getText().length() >= 8 ) 
        e.consume(); 
}  
});

txtNomC.addKeyListener(new KeyAdapter() {
public void keyTyped(KeyEvent e) { 
	boutAjout.setEnabled(true);
	boutAnnulerAjout.setEnabled(true);
}  
});

txtPrenomC.addKeyListener(new KeyAdapter() {
public void keyTyped(KeyEvent e) { 
	
	boutAjout.setEnabled(true);
	boutAnnulerAjout.setEnabled(true);}
 
});

// connexion à la bses //

valeursInitialesN();
valeursInitialesC();

try{
	boutPrec.setEnabled(false);
	 if(BoutSuivPrec.getResultCheck().next()) 
		 {
		 BoutSuivPrec.getResultCheck().absolute(1);
		 setValuesN(BoutSuivPrec.getResultCheck());
		 boutSuiv.setEnabled(true);
		 }else{
			 boutSuiv.setEnabled(false);
			 boutPrec.setEnabled(false);
		 }
	
}
		catch (Exception exceptionConnect){
			System.out.println("exceptionConnect : " +exceptionConnect.getMessage());
			
		}
		finally{
			if ( BoutSuivPrec.getResultCheck() == null)
				try{
					BoutSuivPrec.getResultCheck().close();
				}
			catch(Exception closing){
				JOptionPane.showMessageDialog(null,"<html><i>Vous n'êtes pas connéctés à votre base !</i>","ERROR!",JOptionPane.ERROR_MESSAGE);
				
				System.out.println("closing : " +closing.getMessage());
			}}


try{
	GridBagLayout gridStat=new GridBagLayout();
	GridBagConstraints contraintesStat = new GridBagConstraints(); 
	pCentre3.setLayout(gridStat);

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





}

// classe MesActions des actionListeners //

private class MesActions implements ActionListener{
public synchronized void actionPerformed(ActionEvent e){
 if(e.getSource()==quitter){
			 Object[] options = {"Oui","Non"};
		int   ON= JOptionPane.showOptionDialog(null,"<html><i>Voulez vous  vraiment quitter cette application ?</i>","Quitter",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
		if (ON==0)  System.exit(0);
 }else if (e.getSource()==bleu){
			pNord.setBackground(Color.BLUE);
			pNord2.setBackground(Color.BLUE);
			bleu.setEnabled(false);
			rouge.setEnabled(true);
			vert.setEnabled(true);
			
 }else if (e.getSource()==rouge){
			pNord.setBackground(Color.red);
			pNord2.setBackground(Color.red);
			bleu.setEnabled(true);
			rouge.setEnabled(false);
			vert.setEnabled(true);
			
 }else if (e.getSource()==vert){
			pNord.setBackground(Color.GREEN);
			pNord.setBackground(Color.GREEN);
			bleu.setEnabled(true);
			rouge.setEnabled(true);
			vert.setEnabled(false);
			
 }else if (e.getSource()==rechercheNom){
	    	ong.setSelectedComponent(panRes);
	    	
}else if(e.getSource()==boutAnnulerAjout){
		valeursInitialesC();
		
}else if(e.getSource()==boutAnnulerRech){
		txtKeyWord.setText("");
	}else if(e.getSource()== panStat){
		
	}
	
	}
}


// méthodes utilisés //

public void valeursInitialesC() {
	 txtCinC.setText("");	
	 txtNomC.setText("");
	 txtPrenomC.setText("");
	 comboSexeC.setSelectedItem("Masculin");
	 dateEditorC.setText(null);
}
public void valeursInitialesN() {
	 txtCinN.setText("");	
	 txtNomN.setText("");
	 txtPrenomN.setText("");
	 comboSexeN.setSelectedItem("Masculin");
	 dateEditorN.setText(null);
}
public void setValuesN(ResultSet resultCheck) throws SQLException  {
	
	 txtCinN.setText(resultCheck.getString(1));	
	 txtNomN.setText(resultCheck.getString(2));
	 txtPrenomN.setText(resultCheck.getString(3));
	 calendrierN.setDate(resultCheck.getDate(4));
	 String a = resultCheck.getString(5);
	 comboSexeN.setSelectedItem(a); 
	 
	 
}

}
	