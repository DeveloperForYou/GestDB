package gestdb.Graphics;

import gestdb.BugSegnalator.Email;
import gestdb.Setting.SettFile;
import gestdb.DBFile.Access;
import gestdb.Update.Update;
import gestdb.ServiziDB.PostgreDB;
import gestdb.ServiziDB.MySQL;
import gestdb.Internet.CheckInternet;

import com.dropbox.core.DbxException;

import java.sql.Connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.commons.io.FilenameUtils;

@Deprecated
public class Grafica extends JFrame implements ActionListener, MouseListener,KeyListener {
   private  JPanel jp;
   private  JButton [] jb;
   private JLabel jl;
   private MySQL db;
   private PostgreDB pdb;
   private String servizioDB="";//Indica il Servizio scelto per la connnessione al database
    
    public Grafica()
    {
     jp=new JPanel();
     jl=new JLabel("Premi uno di questi bottoni per ottenere un risultato:");
     jb=new JButton[4];
     jb[0]=new JButton("Connessione al database");
     jb[1]=new JButton("Disconnesione dal database");
     jb[2]=new JButton("Mostra l'intero database");
     jb[3]=new JButton("Esegui una query");
     setSize(410,410);
     setTitle("GestDB Beta 4 ");
     setResizable(false);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     jp.setLayout(null);
     super.setJMenuBar(createMenuBar());
     getContentPane().add(jp);
     jl.setBounds(50, 30, 300, 50);
     jb[0].setBounds(100,80,210,50);
     jb[1].setBounds(100,140,210,50);
     jb[2].setBounds(100,200,210,50);
     jb[3].setBounds(100,260,210,50);
     jp.add(jl);
     for(int i=0;i<jb.length;i++){
      jp.add(jb[i]);
      jb[i].addActionListener(this); 
      //jb[i].addKeyListener(this);
      //jb[i].setMnemonic(KeyEvent.VK_ALT);
     }
     //jp.addMouseListener(this);
    }
         
  
  private JMenuBar createMenuBar()
  {
    JMenuBar menuBar=new JMenuBar();
    JMenu jm=new JMenu("Funzioni");
    JMenuItem info=new JMenuItem("Info");
    JMenuItem update=new JMenuItem("Aggiorna");
    JMenuItem impo=new JMenuItem("Impostazioni");
    JMenuItem segnala=new JMenuItem("Segnala un errore");
    jm.add(info);
    jm.addSeparator();
    jm.add(impo);
    jm.addSeparator();
    jm.add(update);
    jm.addSeparator();
    jm.add(segnala);
    impo.addActionListener(impo());
    info.addActionListener(info());
    update.addActionListener(aggiorna());
    segnala.addActionListener(segnala());
    menuBar.add(jm);
   return menuBar;
  }
   
  private ActionListener info()
  {
   ActionListener in=new ActionListener(){
      @Override
       public void actionPerformed(ActionEvent ed) {
         JOptionPane.showMessageDialog(null, 
                    getTitle()
                 + "\nSviluppato da: Developer for you"
                 + "\nPagina Instagram del progetto: @developerforyou2", "informazioni sul programma", JOptionPane.INFORMATION_MESSAGE);
        }
      };
    return in;
   }
  
  private ActionListener impo()//qui ci sono le impostazioni dell'app
  {
   JFrame jf=new JFrame();
   JPanel jp=new JPanel();
   JButton chiudi=new JButton("Esci senza salvare");
   JButton salva=new JButton("Salva modifiche");
   JRadioButton[] cr=new JRadioButton[10];
   ActionListener imp=new ActionListener(){
       @Override
       public void actionPerformed(ActionEvent e) {
       // JOptionPane.showMessageDialog(null, "Impossibile aprire le impostazioni"
       //       + "\nFunzione non ancora disponibile!"
       //       + "\nFunzione Disponibile in futuro"
       //       + "\nAttendere un aggiornamento dell'app dallo Sviluppatore", "Impostazioni: Funzione non ancora disponibile   ", JOptionPane.ERROR_MESSAGE);
        for(int i=0;i<cr.length;i++){
          jp.add(cr[i]);
          cr[i].addActionListener(this);
        }
        SettFile sf=new SettFile();
        jf.setSize(410,410);
        jf.setResizable(false);   
        jf.setVisible(true);
        jp.setLayout(null);
        jp.add(chiudi);
        jp.add(salva);
        jf.setTitle("Impostazioni");
        jf.add(jp);
       }
   };
   return imp;
  }
  
  private ActionListener aggiorna()
  {
   JFrame jf=new JFrame();
   JPanel jp=new JPanel();
   JButton jb=new JButton("Verifica Aggiornamenti");
   JLabel aggiorna=new JLabel("Verifica Aggiornamenti: ");
   JLabel vc=new JLabel("VersioneCorrente:"+getTitle());
   JProgressBar jpb=new JProgressBar(0,100);
   ActionListener up=new ActionListener(){
      @Override
       public void actionPerformed(ActionEvent ec) {
        jf.setSize(410,410);
        jf.setResizable(false);   
        jf.setVisible(true);
        jp.setLayout(null);
        jf.setTitle("Utility di aggiornamento app");
        jf.add(jp);
        if(jb.getText().equals(ec.getActionCommand()))
        {
         JOptionPane.showMessageDialog(null, "Controllo presenza Connessione a internet "
                     ,"Utility di Controllo", JOptionPane.INFORMATION_MESSAGE);
         CheckInternet ci=new CheckInternet();
         if(ci.checkInternetConnection()){
            JOptionPane.showMessageDialog(null, "Connessione a internet Presente "
                     ,"Utility di Controllo", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.getRootFrame().dispose();
          try{
              JOptionPane.showMessageDialog(null, "Funzione ancora in fase di test"
                      + "\nsi prega di procedere con cautela",
                        "Utility di Aggiornamento: informazione: Funzione in fase di test", JOptionPane.INFORMATION_MESSAGE);
              String vc=getTitle();//titolo attuale dell'app/versione attuale dell'app
              //Update2 up2=new Update2(vc);
              //up2.Download();
              Update up=new Update(vc); 
              jpb.setValue(10);
              up.download();
             } catch (IOException | DbxException ex) {
               JOptionPane.showMessageDialog(null, "impossibile verificare gli aggiornamenti", 
                      "Utility di Aggiornamento", JOptionPane.INFORMATION_MESSAGE);
            }
         }
         else{
              JOptionPane.showMessageDialog(null, "Connessione a internet Assente"
                                            +"\nimpossibile verificare gli aggiornamenti", 
                                            "Utility di Aggiornamento", JOptionPane.INFORMATION_MESSAGE);
         }
        }
       }
      };
    aggiorna.setBounds(5, 50, 200,50 );
    vc.setBounds(150, 50 , 200, 50);
    jb.setBounds(50, 150, 250, 50);
    jpb.setBounds(20, 90, 100, 20);
    jp.add(aggiorna);
    jp.add(vc);
    jp.add(jb);
    jp.add(jpb);
    jb.addActionListener(up);
    return up;
  }
  
  private ActionListener segnala()
  {
    JFrame jf=new JFrame();
    JTextField n=new JTextField();
    JTextField c=new JTextField();
    JTextField e=new JTextField();
    JTextField s=new JTextField();
    JPanel jp=new JPanel();
    JLabel nome=new JLabel("NOME:");
    JLabel cognome=new JLabel("COGNOME:");
    JLabel email=new JLabel("EMAIL:");
    JLabel segnala=new JLabel("Descrivi il problema rilevato ");
    JButton jb=new JButton("Segnala allo Sviluppatore");
    JFileChooser jfc=new JFileChooser();
    ActionListener segn=new ActionListener(){
      @Override
       public void actionPerformed(ActionEvent ew) {
        jf.setTitle("Utility di segnalazione Bug");
        jf.setSize(410,410);
        jf.setResizable(false);   
        jf.setVisible(true);
        jp.setLayout(null);
        jf.add(jp);
        Email Email = null;
         if(jb.getText().equals(ew.getActionCommand()))
         {
                String name,surname,em,re;
                name=n.getText();
                surname=c.getText();
                em=e.getText();
                re=s.getText();
                int scelta=JOptionPane.showConfirmDialog(jp, "Vuoi fornire in allegato un file per lo sviluppatore? ", "Richiesta",JOptionPane.INFORMATION_MESSAGE);
                if(scelta==0){
                 jfc.setDialogTitle("Seleziona l'allegato da fornire allo sviluppatore");
                 int esito=jfc.showOpenDialog(jp);
                 File a=jfc.getSelectedFile();
                 Email=new Email(em,re,a);
                 if(em!=null & re!=null & esito==JFileChooser.APPROVE_OPTION){
                   try{
                       Email.sendWithAttached(em, re,a);
                       n.setText("");
                       c.setText("");
                       e.setText("");
                       s.setText("");
                    } catch (FileNotFoundException ex) {
                     JOptionPane.showMessageDialog(jp, "lettura dell'allegato impossibile", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                 }
                 if(esito==JFileChooser.CANCEL_OPTION)
                 {
                  JOptionPane.showMessageDialog(jp, "Allegato non trovato "
                          + "\nSegnalazione Abortita","Utility di segnalazione", JOptionPane.INFORMATION_MESSAGE);
                  n.setText("");
                  c.setText("");
                  e.setText("");
                  s.setText("");
                 }
                } 
                if(scelta==1)
                {
                  Email=new Email(em, re);
                  JOptionPane.showMessageDialog(jp, "Invio Segnalazione senza allegato "
                          ,"Utility di segnalazione", JOptionPane.INFORMATION_MESSAGE);
                   if(em!=null & re!=null){
                     Email.sendWithOutAttached(em, re);  
                     n.setText("");
                     c.setText("");
                     e.setText("");
                     s.setText("");
                  }
                 }
                if(scelta==2){
                   JOptionPane.showMessageDialog(jp, "Nessuna segnalazione da inviare "
                   + "\nSegnalazione Abortita","Utility di segnalazione", JOptionPane.INFORMATION_MESSAGE);
                   n.setText("");
                   c.setText("");
                   e.setText("");
                   s.setText("");
                }
            }
        }
    };
    nome.setBounds(5,10,50,50);
    n.setBounds(45, 30, 150, 20);
    cognome.setBounds(5, 50, 70, 50);
    c.setBounds(72,80,150,20);
    email.setBounds(5, 110, 70, 50);
    e.setBounds(5, 140, 200,50);
    segnala.setBounds(5, 180, 200, 50);
    s.setBounds(5,210,200,50 );
    jb.setBounds(5,300,250,50 );
    jp.add(nome);
    jp.add(cognome);
    jp.add(email);
    jp.add(segnala);
    jp.add(n);
    jp.add(c);
    jp.add(e);
    jp.add(s);
    jp.add(jb);
    jb.addActionListener(segn);
   return segn;
  }
  
  private boolean controllo()//serve a dire se siamo passati dal tasto connetti
  {
    boolean controllo=false;
    switch(servizioDB){
        
        case "MySQL":if(db!=null)
                       controllo=true;
                     break;
        
        case "PostgreSQL":if(pdb!=null)
                            controllo=true;
                           break;
     }
    return controllo;
  }
   
   private ActionListener ConnectForMYSQL()
   {
     ActionListener my=new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
             JOptionPane.getRootFrame().dispose();
             String url=JOptionPane.showInputDialog(null, "inserisci l'url del database", null,1);
             System.out.println(url+" "+url.length());
             if(url==null){  
                JOptionPane.showMessageDialog(null, "l'url per connettersi al database non puo' essere vuoto oppure l'"+url+"  non è corretto, prego riprovare. ", "errore", JOptionPane.ERROR_MESSAGE);
                db=null;
                return;
               }
             else 
                {
                 if(url.length()>4){
                  String user=JOptionPane.showInputDialog(null, "inserisci l'username del database e premi OK(se non è necessario premere direttamente OK)", null,1);
                  String password=JOptionPane.showInputDialog(null, "inserisci la password del database e premi OK(se non è necessaria premere direttamente OK)", null,1);
                  db=new MySQL(url,user,password);
                  Connection app=db.connetti();
                  if(app==null){
                     db=null;
                     return;
                    }
                }
                 else{
                      JOptionPane.showMessageDialog(null, "Lunghezza URL database errata, prego riprovare. ", "errore", JOptionPane.ERROR_MESSAGE);
                      db=null;
                      return;
              }
            }        
         servizioDB="MySQL";
        }
     };
    return my;
   }
   
   private ActionListener ConnectForPostgreSQL()
   {
     ActionListener ps=new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
             JOptionPane.getRootFrame().dispose();
             String url=JOptionPane.showInputDialog(null, "inserisci l'url del database", null,1);
             System.out.println(url+" "+url.length());
             if(url==null){  
                JOptionPane.showMessageDialog(null, "l'url per connettersi al database non puo' essere vuoto oppure l'"+url+"  non è corretto, prego riprovare. ", "errore", JOptionPane.ERROR_MESSAGE);
                pdb=null;
                return;
               }
             else 
                {
                 if(url.length()>4){
                  String user=JOptionPane.showInputDialog(null, "inserisci l'username del database e premi OK(se non è necessario premere direttamente OK)", null,1);
                  String password=JOptionPane.showInputDialog(null, "inserisci la password del database e premi OK(se non è necessaria premere direttamente OK)", null,1);
                  pdb=new PostgreDB(url,user,password);
                  Connection app=pdb.connetti();
                  if(app==null){
                     pdb=null;
                     return;
                    }
                }
                 else{
                      JOptionPane.showMessageDialog(null, "Lunghezza URL database errata, prego riprovare. ", "errore", JOptionPane.ERROR_MESSAGE);
                      pdb=null;
                      return;
              }
            }
             servizioDB="PostgreSQL";        
        }
    };
    return ps;
   }
   
   private ActionListener OpenFromFile()
   {
    ActionListener open=new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc=new JFileChooser();
            jfc.setDialogTitle("Seleziona il file del database");
            int esito=jfc.showOpenDialog(jp);
            File a=jfc.getSelectedFile();
            if(esito==JFileChooser.APPROVE_OPTION)//bisogna decidere le estensioni supportate, File: (.mdb e odb) .accdb .xls
            {
             JOptionPane.getRootFrame().dispose();
             String est="."+FilenameUtils.getExtension(a.getName());
             switch(est){
                 case ".odb": JOptionPane.showMessageDialog(null,"Estensione di file non ancora"
                              + "\nsupportata","Estensione non supportata",JOptionPane.INFORMATION_MESSAGE);
                              //odb o=new odb();
                              //o.prova(a.getName(),a);
                               break;
                 case ".mdb": Access mdb=new Access();
                              mdb.Access(a.getPath(), a);
                              Sottomenu(mdb);
                               break;
                 case ".accdb": Access accdb=new Access();
                                accdb.Access(a.getPath(), a);
                                Sottomenu(accdb);
                                 break;
                 case ".xlsx":  JOptionPane.showMessageDialog(null,"Estensione di file non ancora"
                                + "\nsupportata","Estensione non supportata",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                 default: JOptionPane.showMessageDialog(null,"Il file inserito non è di un database!"
                         + "\ninserire un file del seguente formato:"
                         + "\n .odb"
                         + "\n .mdb"
                         + "\n .accdb"
                         + "\n .xlsx","Estensione non supportata",JOptionPane.INFORMATION_MESSAGE);
                          break;
              }
            }
            if(esito==JFileChooser.CANCEL_OPTION){
                JOptionPane.showMessageDialog(null,"Nessun Database Selezionato", "Seleziona il database",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    };
    return open;
   }
     
    @Override
    public void actionPerformed(ActionEvent e) {
         if(jb[0].getText().compareTo(e.getActionCommand())==0){//tasto connetti MySQL
                JButton jb1=new JButton("Connessione tramite MySQL");
                JButton jb2=new JButton("Aprire il Database da File");
                JButton jb3=new JButton("Connessione tramite PostgreSQL");
                Object []obj={jb1,jb2,jb3};
                jb1.addActionListener(ConnectForMYSQL());
                jb2.addActionListener(OpenFromFile());
                jb3.addActionListener(ConnectForPostgreSQL());
                int n=JOptionPane.showOptionDialog(null,"Scegli il metodo di lavoro con il database"
                        + "\nCliccare x in alto a destra per chiudere questa finestra","Scelta di lavoro con "
                                + "il database", 1, JOptionPane.QUESTION_MESSAGE,null,  obj,obj );
              System.out.println(servizioDB);  
                   if(n==-1)
                    return;
         }
         switch(servizioDB){
         
           case "MySQL": if(jb[1].getText().compareTo(e.getActionCommand())==0)//tasto disconnetti MySQL
                          if(controllo()){
                           db.disconnetti();
                           db=null;
                          }
                          else
                            JOptionPane.showMessageDialog(null, "Devi prima connetterti al database", "errore", JOptionPane.ERROR_MESSAGE);
               
                         if(jb[2].getText().compareTo(e.getActionCommand())==0){//tasto stampa MySQL 
                           String tab="";
                           if(controllo())
                           { 
                               tab=JOptionPane.showInputDialog(null, " "+"inserisci la tabella che desideri mostrare"
                                       + "\nTabelle Disponibili nel database:"
                                       + "\n\n"+db.MostraTabelle(), null,1);
                             if(tab==null | tab.equals(""))
                             {
                               JOptionPane.showMessageDialog(null, "impossibile mostrare la tabella"+" "+tab
                               +"\nPotrebbe non essere presente nel database","errore",JOptionPane.ERROR_MESSAGE);
                             return;
                            } 
                            else
                              db.MostraGrafica(tab);
                          }
                          else
                              JOptionPane.showMessageDialog(null, "Devi prima connetterti al database", "errore", JOptionPane.ERROR_MESSAGE);
                        } 
               
                        if(jb[3].getText().compareTo(e.getActionCommand())==0){ //sotto menu query
                          if(controllo()){
                            JFrame jf2=new JFrame();
                            JPanel jp2=new JPanel();
                            JButton[] jb2=new JButton[4];
                            JLabel j2=new JLabel("Scegli uno di questi comandi da impartire al database: ");
                            jf2.setTitle("Sottomenù query");
                            jf2.setSize(410,410);
                            jf2.setResizable(false);
                            jf2.getContentPane().add(jp2);
                            jp2.setLayout(null);
                            jf2.setVisible(true);
                            jf2.add(jp2);
                            j2.setBounds(50, 30, 350, 50);
                            jb2[0]=new JButton("COMANDI DDL, es. CREATE,DROP,ALTERTABLE ecc");
                            jb2[1]=new JButton("COMANDI DML, es. INSERT,DELETE,UPDATE ecc ");
                            jb2[2]=new JButton("COMANDI DQL, es. SELECT E QUERY NIDIFICATE");
                            jb2[3]=new JButton("COMANDI DCL, es. GRANT,REVOKE,CREATE USER ecc");
                            jb2[0].setBounds(50, 80, 350,50);
                            jb2[1].setBounds(50,140,350,50);
                            jb2[2].setBounds(50,200,350,50);
                            jb2[3].setBounds(50,260,350,50);
                            jp2.add(j2);
                            for(int i=0;i<jb2.length;i++){
                                jp2.add(jb2[i]);
                                jb2[i].addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ex) {
                                    if(jb2[0].getText().compareTo(ex.getActionCommand())==0){
                                     String query=JOptionPane.showInputDialog(jf2, "inserisci la query da effettuare:", null, 1);
                                    if(query!=null)
                                       db.AllExecuteCommands(query);
                                }
               
                                if(jb2[1].getText().compareTo(ex.getActionCommand())==0){
                                    String query=JOptionPane.showInputDialog(jf2, "inserisci la query da effettuare:", null, 1);
                                    if(query!=null)
                                        db.AllExecuteCommands(query);
                                } 
               
                                if(jb2[2].getText().compareTo(ex.getActionCommand())==0){
                                    String query=JOptionPane.showInputDialog(jf2, "inserisci la query da effettuare:", null, 1);
                                    if(query!=null)
                                      db.ExecuteQuery(query);
                                }
                            
                                if(jb2[3].getText().compareTo(ex.getActionCommand())==0){
                                    String query=JOptionPane.showInputDialog(jf2, "inserisci la query da effettuare:", null, 1);
                                    if(query!=null)
                                      db.AllExecuteCommands(query);
                                }       
                           }         
                        });
                   } 
               }
              else
                 JOptionPane.showMessageDialog(null, "Devi prima connetterti al database", "errore", JOptionPane.ERROR_MESSAGE);
              }
              break;     
        
           case "PostgreSQL": if(jb[1].getText().compareTo(e.getActionCommand())==0)//tasto disconnetti MySQL
                                 if(controllo()){
                                   pdb.disconnetti();
                                   pdb=null;
                                }
                               else
                                  JOptionPane.showMessageDialog(null, "Devi prima connetterti al database", "errore", JOptionPane.ERROR_MESSAGE);
               
                               if(jb[2].getText().compareTo(e.getActionCommand())==0){//tasto stampa MySQL 
                                String tab=null;
                                 if(controllo())
                                 {
                                     tab=JOptionPane.showInputDialog(null, " "+"inserisci la tabella che desideri mostrare"
                                             + "\nTabelle Disponibili nel database:"
                                             + "\n\n"+pdb, null,1);
                                   if(tab==null | tab.equals(""))
                                   {
                                    JOptionPane.showMessageDialog(null, "impossibile mostrare la tabella"+" "+tab
                                    +"\nPotrebbe non essere presente nel database","errore",JOptionPane.ERROR_MESSAGE);
                                   return;
                                 }
                                 else
                                     pdb.MostraGrafica(tab);
                              }
                              else
                                 JOptionPane.showMessageDialog(null, "Devi prima connetterti al database", "errore", JOptionPane.ERROR_MESSAGE);
                              } 
               
                              if(jb[3].getText().compareTo(e.getActionCommand())==0){ //sotto menu query
                               if(controllo()){
                                 JFrame jf2=new JFrame();
                                 JPanel jp2=new JPanel();
                                 JButton[] jb2=new JButton[4];
                                 JLabel j2=new JLabel("Scegli uno di questi comandi da impartire al database: ");
                                 jf2.setTitle("Sottomenù query");
                                 jf2.setSize(410,410);
                                 jf2.setResizable(false);
                                 jf2.getContentPane().add(jp2);
                                 jp2.setLayout(null);
                                 jf2.setVisible(true);
                                 jf2.add(jp2);
                                 j2.setBounds(50, 30, 350, 50);
                                 jb2[0]=new JButton("COMANDI DDL, es. CREATE,DROP,ALTERTABLE ecc");
                                 jb2[1]=new JButton("COMANDI DML, es. INSERT,DELETE,UPDATE ecc ");
                                 jb2[2]=new JButton("COMANDI DQL, es. SELECT E QUERY NIDIFICATE");
                                 jb2[3]=new JButton("COMANDI DCL, es. GRANT,REVOKE,CREATE USER ecc");
                                 jb2[0].setBounds(50, 80, 350,50);
                                 jb2[1].setBounds(50,140,350,50);
                                 jb2[2].setBounds(50,200,350,50);
                                 jb2[3].setBounds(50,260,350,50);
                                 jp2.add(j2);
                                 for(int i=0;i<jb2.length;i++){
                                  jp2.add(jb2[i]);
                                  jb2[i].addActionListener(new ActionListener() {
                                  @Override
                                  public void actionPerformed(ActionEvent ex) {
                                    if(jb2[0].getText().compareTo(ex.getActionCommand())==0){
                                      String query=JOptionPane.showInputDialog(jf2, "inserisci la query da effettuare:", null, 1);
                                      if(query!=null)
                                        pdb.AllExecuteCommands(query);
                                    }
               
                                    if(jb2[1].getText().compareTo(ex.getActionCommand())==0){
                                       String query=JOptionPane.showInputDialog(jf2, "inserisci la query da effettuare:", null, 1);
                                       if(query!=null)
                                        pdb.AllExecuteCommands(query);
                                    } 
               
                                    if(jb2[2].getText().compareTo(ex.getActionCommand())==0){
                                      String query=JOptionPane.showInputDialog(jf2, "inserisci la query da effettuare:", null, 1);
                                      if(query!=null)
                                       pdb.ExecuteQuery(query);
                                    }
                            
                                    if(jb2[3].getText().compareTo(ex.getActionCommand())==0){
                                      String query=JOptionPane.showInputDialog(jf2, "inserisci la query da effettuare:", null, 1);
                                      if(query!=null)
                                       pdb.AllExecuteCommands(query);
                                    }       
                                   }         
                                 });
                            } 
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Devi prima connetterti al database", "errore", JOptionPane.ERROR_MESSAGE);
           }
          break;                     
        }
     }
    
    public ActionListener SottomenuAccess(JButton[] jb,Access a)
    {
     ActionListener a1=new ActionListener()
     {
       @Override
        public void actionPerformed(ActionEvent e) {
                            if(jb[0].getText().compareTo(e.getActionCommand())==0){
                               String query=JOptionPane.showInputDialog(null, "inserisci la query da effettuare:", null, 1);
                               if(query!=null)
                                a.AllExecuteCommands(query);
                            }
               
                            if(jb[1].getText().compareTo(e.getActionCommand())==0){
                              String query=JOptionPane.showInputDialog(null,"inserisci la query da effettuare:", null, 1);
                              if(query!=null)
                               a.AllExecuteCommands(query);
                            } 
               
                            if(jb[2].getText().compareTo(e.getActionCommand())==0){
                              String query=JOptionPane.showInputDialog(null, "inserisci la query da effettuare:", null, 1);
                              if(query!=null)
                                a.ExecuteQuery(query);
                            }
                            
                            if(jb[3].getText().compareTo(e.getActionCommand())==0){
                             String query=JOptionPane.showInputDialog(null, "inserisci la query da effettuare:", null, 1);
                              if(query!=null)
                               a.AllExecuteCommands(query);
                            }       
         }
     };
      return a1;
    }

            
    public void Sottomenu(Access a)
    {
                   JFrame jf2=new JFrame();
                   JPanel jp2=new JPanel();
                   JButton[] jb2=new JButton[4];
                   JLabel j2=new JLabel("Scegli uno di questi comandi da impartire al database: ");
                   jf2.setTitle("Sottomenù query");
                   jf2.setSize(410,410);
                   jf2.setResizable(false);
                   jf2.getContentPane().add(jp2);
                   jp2.setLayout(null);
                   jf2.setVisible(true);
                   jf2.add(jp2);
                   j2.setBounds(50, 30, 350, 50);
                   jb2[0]=new JButton("COMANDI DDL, es. CREATE,DROP,ALTERTABLE ecc");
                   jb2[1]=new JButton("COMANDI DML, es. INSERT,DELETE,UPDATE ecc ");
                   jb2[2]=new JButton("COMANDI DQL, es. SELECT E QUERY NIDIFICATE");
                   jb2[3]=new JButton("COMANDI DCL, es. GRANT,REVOKE,CREATE USER ecc");
                   jb2[0].setBounds(50, 80, 350,50);
                   jb2[1].setBounds(50,140,350,50);
                   jb2[2].setBounds(50,200,350,50);
                   jb2[3].setBounds(50,260,350,50);
                   jp2.add(j2);
                   for(int i=0;i<jb2.length;i++){
                     jp2.add(jb2[i]);
                     jb2[i].addActionListener(SottomenuAccess(jb2,a));
                   }
    
    }
            
    public String getTitolo()
    {
      return getTitle();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
      
     
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}