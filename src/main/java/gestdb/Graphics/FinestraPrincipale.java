package gestdb.Graphics;

import gestdb.DBFile.Access;
import gestdb.ServiziDB.GestioneChiamateDB;
import gestdb.ServiziDB.MappaConnessioni;
import gestdb.ServiziDB.MySQL;
import gestdb.ServiziDB.PostgreDB;
import gestdb.ServiziDB.ServiziDBSupportati;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
    
import org.apache.commons.io.FilenameUtils;

public class FinestraPrincipale extends JFrame {
    
    private JButton ApriFile;
    private JButton ChiudiFile;
    private JButton Connessione;
    private JTextArea DatiLoc;
    private static JTextArea DatiRem;
    private JButton Disconnessione;
    private JButton MostraTabellaLoc;
    private JButton MostraTabellaRem;
    private JButton QueryLoc;
    private JButton QueryRem;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JMenu jMenu1;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JFileChooser jfc;
	
    public FinestraPrincipale() {
        initComponents();
    }

    private void initComponents() {

        jSeparator1 = new JSeparator();
        jLabel2 = new JLabel();
        Connessione = new JButton();
        Disconnessione = new JButton();
        jLabel3 = new JLabel();
        MostraTabellaRem = new JButton();
        QueryRem = new JButton();
        jLabel4 = new JLabel();
        jScrollPane1 = new JScrollPane();
        DatiRem = new JTextArea();
        jSeparator2 = new JSeparator();
        jLabel5 = new JLabel();
        ApriFile = new JButton();
        ChiudiFile = new JButton();
        jLabel6 = new JLabel();
        MostraTabellaLoc = new JButton();
        QueryLoc = new JButton();
        jLabel7 = new JLabel();
        jScrollPane2 = new JScrollPane();
        DatiLoc = new JTextArea();
        jSeparator3 = new JSeparator();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenuItem2 = new JMenuItem();
        jMenuItem3 = new JMenuItem();
        jMenuItem4 = new JMenuItem();
        jfc=new JFileChooser();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(800, 800));
        setTitle(getTitolo());
        setIconImage(Icona.setImage(getNameClass()));
        InizializzaParteRemota();
        InizializzaParteLocale();
        InizializzaMenuFunzioni();
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(Connessione, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Disconnessione, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(QueryRem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(MostraTabellaRem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addComponent(jSeparator2, GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(ChiudiFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ApriFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(MostraTabellaLoc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(QueryLoc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
            .addComponent(jSeparator3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(Connessione)
                            .addComponent(MostraTabellaRem))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(Disconnessione)
                            .addComponent(QueryRem)))
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(ApriFile)
                            .addComponent(MostraTabellaLoc))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(ChiudiFile)
                            .addComponent(QueryLoc))
                        .addGap(90, 90, 90))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
    
    public static String getTitolo(){
      return "GestDB Beta 4";
    }
     
    public void InizializzaParteRemota(){
        jLabel2.setText("Gestione Connessione Database Remoto");

        Connessione.setText("Connessione");
        Connessione.addActionListener((ActionEvent evt) -> {
            new ConnessioneDB().setVisible(true);
        });

        Disconnessione.setText("Disconnessione");
        Disconnessione.addActionListener((ActionEvent evt) -> {
            DisconnessioneDB.Disconnessione();
            DatiRem.setText("");
        });

        jLabel3.setText("Comandi Database Remoto");

        MostraTabellaRem.setText("Mostra Tabella");
        MostraTabellaRem.addActionListener((ActionEvent evt) -> {
            if(MappaConnessioni.NoConnessione()==false)
                MessaggiGrafici.MessaggioConnessioneMancante1();
            else
                AvviaMostraTabella();
        });
        
        QueryRem.setText("Esegui una Query");
        QueryRem.addActionListener((ActionEvent evt) -> {
            if(MappaConnessioni.NoConnessione()==false)
                MessaggiGrafici.MessaggioConnessioneMancante1();
            else
                AvviaFormQuery();
        });
        jLabel4.setText("Dati Database Remoto");

        DatiRem.setEditable(false);
        DatiRem.setColumns(20);
        DatiRem.setRows(5);
        jScrollPane1.setViewportView(DatiRem);
    }
    
    public void InizializzaParteLocale(){
        jLabel5.setText("Gestione Database Locale");

        ApriFile.setText("Apri File");
        ApriFile.addActionListener((ActionEvent e) -> {
            jfc.setDialogTitle("Seleziona il file del database su cui operare");
            int esito=jfc.showOpenDialog(null);
            switch (esito){
                
                case JFileChooser.APPROVE_OPTION:  File a=jfc.getSelectedFile();
                String est="."+FilenameUtils.getExtension(a.getName());
                GestioneChiamateDB.setFileName(jfc.getSelectedFile().getName());
                switch(est){
                    case ".odb": MessaggiGrafici.FileNonSupportato();
                    //odb o=new odb();
                    //o.prova(a.getName(),a);
                    break;
                    
                    case ".mdb": Access mdb=new Access();
                    mdb.Access(a.getPath(), a);
                    break;
                    
                    case ".accdb": Access accdb=new Access();
                    accdb.Access(a.getPath(), a);
                    break;
                    
                    case ".xlsx":  MessaggiGrafici.FileNonSupportato();
                    break;
                    
                    default: MessaggiGrafici.FileSupportati();
                        break;
                }
                break;
                
                case JFileChooser.CANCEL_OPTION: MessaggiGrafici.FileNonSelezionato();
                break;
                
            }
        });

        ChiudiFile.setText("Chiudi File");
        ChiudiFile.addActionListener((ActionEvent e) -> {
            File open=jfc.getSelectedFile();
            if(open!=null){
                open=null;
                MessaggiGrafici.FileChiuso();
            }
            else
                MessaggiGrafici.MessaggioFileNonAperto();
        });
        jLabel6.setText("Comandi Database Locale ");

        MostraTabellaLoc.setText("Mostra Tabella ");
        MostraTabellaLoc.addActionListener((ActionEvent evt) -> {
            File open=jfc.getSelectedFile();
            if(open!=null)
                AvviaMostraTabella();
            else
                MessaggiGrafici.MessaggioFileNonAperto();
        });

        QueryLoc.setText("Esegui una Query");
        QueryLoc.addActionListener((ActionEvent e) -> {
            File open=jfc.getSelectedFile();
            if(open!=null)
                AvviaFormQuery();
            else
                MessaggiGrafici.MessaggioFileNonAperto();
        });

        jLabel7.setText("Dati Database Locale          ");

        DatiLoc.setEditable(false);
        DatiLoc.setColumns(20);
        DatiLoc.setRows(5);
        jScrollPane2.setViewportView(DatiLoc);
    }
    
    public void InizializzaMenuFunzioni(){
        jMenu1.setText("Funzioni");
        jMenuItem1.setText("Informazioni");
        jMenuItem1.addActionListener((ActionEvent evt) -> {
            MessaggiGrafici.InfoApplicazione();
        });
        jMenu1.add(jMenuItem1);
        jMenu1.addSeparator();
        jMenuItem2.setText("Verifica Aggiornamenti");
        jMenuItem2.addActionListener((ActionEvent evt) -> {
            Updater up=new Updater();
            up.setVisible(true);
        });
        jMenu1.add(jMenuItem2);
        jMenu1.addSeparator();
        jMenuItem3.setText("Impostazioni");
        jMenuItem3.addActionListener((ActionEvent evt) -> {
            MessaggiGrafici.ImpostazioniNonDisponibili();
        });
        jMenu1.add(jMenuItem3);
        jMenu1.addSeparator();
        jMenuItem4.setText("Segnala un bug");
        jMenuItem4.addActionListener((ActionEvent evt) -> {
            BugSegnalator bs=new BugSegnalator();
            bs.setVisible(true);
        });
        jMenu1.add(jMenuItem4);
        //jMenu1.addSeparator(); --> Rimuovere Commento quando si aggiunge una nuova voce al menù
        jMenuBar1.add(jMenu1);
        
        setJMenuBar(jMenuBar1);
    }
    
    public void AvviaFormQuery(){
      QueryForm qf=new QueryForm();
      qf.setVisible(true);
    }
    
    public void AvviaMostraTabella(){
      VisualizzaTabella vb=new VisualizzaTabella();
      vb.setVisible(true);
    }
    
    public static void InizializzaTextAreaRemota(){
      String servizio=GestioneChiamateDB.getServizio();
      if(servizio.equals(ServiziDBSupportati.getValue(ServiziDBSupportati.ONE))){//servizio=MySQL
         String[] str=new String[3];
         str[0]="URL: "+MySQL.getConnetti();
         str[1]="User: "+MySQL.getUser();
         str[2]="Servizio: "+servizio;
         for(int i=0;i<str.length;i++)
          DatiRem.append("\n"+str[i]);
      }
      if(servizio.equals(ServiziDBSupportati.getValue(ServiziDBSupportati.TWO))){//servizio=Postgre
         String[] str=new String[3];
         str[0]="URL: "+PostgreDB.getConnetti();
         str[1]="User: "+PostgreDB.getUser();
         str[2]="Servizio: "+servizio;
         for(int i=0;i<str.length;i++)
          DatiRem.append("\n"+str[i]);
      }
    }
    
    public String getNameClass(){
     return this.getClass().getSimpleName();
    }
    
    
}