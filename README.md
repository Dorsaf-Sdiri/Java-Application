Le package nommé MyPackage contient  les classes:

*  MaFrame : une instance de la classe JFrame renferme

### Les composants#

·         L’appel des contrôleurs d’événements (rechCIN, boutonSup, BoutModif, boutonAjout, boutonOK, boutonSuiv,MesActions ).

·         Les désactivations des boutons : précédant, suivant s’il s’agit respectivement du premier, dernier enregistrement.ainsi que la désactivation du bonton Ajouter et Annuler 

      La vu :


###Ong : objet de JTabbedPane renferme trois objets Jpanel  ( panAjout, panRes et panStat )#

#### *  panAjout : on distingue 3 zones (pNord, pCentre, pEst)

    pNord : objet de JPanel contient les composants placés par un GridBagLayout (txtCinN; txtNomN, txtPrenomN, calendrierN, comboSexeN, boutModif, boutSup, boutPrec, boutsuiv)

   pCentre : objet de BgPanel contient les composants (labCIN, txtCinC, labNom, txtNomC, labPrenom, txtPrenomC, labSexe, comboSexeC, labDate, calendrierC )

         pEst : objet de JPanel contient deux boutons (boutAjout ,boutAnnulerAjout)

 

#### *  panRes : on distingue 3 zones (pNord2,  pCentre2,  pEast2)

         pNord2: objet de JPanel contenant les composants (labKeyWord,txtKeyWord, boutOk, boutAnnulerRech) placées en FlowLayout.

         pCentre 2:objet de BgPanel contenant l 


         pEast2 : contenant le composant lab (logo de ESSAIT)


#### * panSta : content cPanel (de type ChartPanel), nbT, nbH, nbF



           


 

 

### Les contrôleurs d’événements #

v  BontonAjout  

v  BontonModifier

v  BoutonOK

v  BoutonSuprimer

v  BoutSuivPrec Contient la méthode connectionBD qui ouvre une connexion connectBD 

v  RechercheCIN
