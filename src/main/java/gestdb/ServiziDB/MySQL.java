package gestdb.ServiziDB;

import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

     /*connetti="jdbc:mysql://localhost:3306/registrati
     ?zeroDateTimeBehavior=convertToNull.
      user="root";
      pw="";
    
      SELECT * FROM registrati WHERE 1
      SELECT * FROM prova1

      UPDATE `registrati` SET `nome`='p'
      INSERT INTO `prova1`(`prova`, `name`, `scuola`, `vacanza`) VALUES (4,8,9,2)
      
      CREATE TABLE PROVA5( NAME CHAR(11) PRIMARY KEY);
      DROP TABLE PROVA5;
    */

public class MySQL {
    
    private static String connetti;//url della connesione al database
    private static String user;
    private static String pw;
    private static JFrame jf;
    private static int numTab=0;
    private Vector nomi=null;//vettore  nomi tabelle del database 
    private static Connection c;
    private static boolean connesso;//se vale true siamo connessi al database, false altrimenti

    public static boolean isConnesso() {
        try {
            if(c!=null & !c.isClosed())
               return connesso=true;    
            else 
               return connesso=false;   
        } catch (SQLException ex) {
            ex.printStackTrace();
            return connesso=false;
        }
     }
    
    public MySQL(String url, String user,String password)
    {
     jf=new JFrame();   
     try {
         JOptionPane.showMessageDialog(jf, "Avvio del driver in corso");
         Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        connetti=url;
        this.user=user;
        pw=password;
        connesso=false;
    }

    public static Connection getC() {
        return c;
    }

    public static String getConnetti() {
        return connetti;
    }

    public static String getUser() {
        return user;
    }
    
    public static Connection connetti() 
    { 
      try{
          JOptionPane.showMessageDialog(jf, "Connensione in corso");
          c=DriverManager.getConnection("jdbc:mysql://"+connetti+"?zeroDateTimeBehavior=convertToNull", user, pw);
          JOptionPane.showMessageDialog(jf, "La connessione è andata a buon fine");
          connesso=true;
        return c;
       }
       catch(SQLException e){
          e.printStackTrace();
          JOptionPane.showMessageDialog(jf, "La connessione non  è andata a buon fine","errore",JOptionPane.ERROR_MESSAGE);
          SetToNull();
         return c=null;
       }
    }
     
    //Riscrivere
    public static void AllExecuteCommands(String query)//esegue tutti i comandi SQL ESCLUSA LA SELEZIONE 
    {
        try {
            JOptionPane.showMessageDialog(jf, "Esecuzione della query: "+query);
            if(!isConnesso())
                connetti();
            int num=0;
            Statement stmt=c.createStatement();
            num=stmt.executeUpdate(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(jf, "query: "+"\n"+query+" "+"impossibile da svolgere","errore", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
     }
    
    public static ArrayList<String> MostraTabelle() //mostra i nomi e numero delle tabelle presenti nel database specificato in fase di connessione
    {
      ArrayList<String> nom=new ArrayList<String>();
      if(!isConnesso())    
         c=connetti();
        try {
            Statement stmt=c.createStatement();
            ResultSet rs=stmt.executeQuery("SHOW TABLES;");
            while (rs.next()) 
              nom.add(rs.getString(1));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    return nom;
   }      
      
   public static void MostraGrafica(String Table)
   {
      if(!isConnesso())    
       c=connetti();
      ResultSet rs;
         try {
              String query="SELECT * FROM "+Table+" WHERE 1";
              Statement stat = c.createStatement();
              rs=stat.executeQuery(query);
              int colonne=rs.getMetaData().getColumnCount();
              int righe=numeroRighe(Table);
              JTable jtb=new JTable(buildTableModel(rs));
              jtb.setEnabled(false);
              JOptionPane.showMessageDialog(null, "la Query  "+ query +" ha prodotto dalla tabella : "+Table
                              + "\nun totale di "+righe+" righe"
                              + "\nun totale di "+colonne+" colonne"
                              + "","Informazioni sulla tabella :"+Table,JOptionPane.INFORMATION_MESSAGE); 
              JOptionPane.showMessageDialog(null, new JScrollPane(jtb),"Risultato Query",
                      JOptionPane.INFORMATION_MESSAGE);
              rs.close();
              stat.close();
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(jf, "impossibile mostrare la tabella   "+ Table +" richiesta ");
               ex.printStackTrace();
        }
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException
    {
     
     ResultSetMetaData metaData = rs.getMetaData();
     
     // names of columns
     Vector<String> columnNames = new Vector<String>();
     int columnCount = metaData.getColumnCount();
     for (int column = 1; column <= columnCount; column++) 
        columnNames.add(metaData.getColumnName(column));
    
    // data of the table
     Vector<Vector<Object>> data = new Vector<Vector<Object>>();
     while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) 
            vector.add(rs.getObject(columnIndex));
        data.add(vector);
      }
     return new DefaultTableModel(data, columnNames);
    }
    
    public static void ExecuteQuery(String query)//solo per i comandi select, IL METODO STAMPA NUMERO DI COLONNE, VALORI DELLE COLONNE e nome della tabella in questione
    {
      try {
            JOptionPane.showMessageDialog(jf, "Esecuzione della query: "+query);
            if(!isConnesso())    
             c=connetti();
            ResultSet rs=null;  
            Statement stat;
            stat = c.createStatement();
            rs=stat.executeQuery(query);
            ResultSetMetaData rsmd;
            rsmd=rs.getMetaData();
            int colonne=rs.getMetaData().getColumnCount();
            String nomeTab=rsmd.getTableName(colonne);
            int righe=numeroRighe(nomeTab);
            JTable jtb=new JTable(buildTableModel(rs));
            JOptionPane.showMessageDialog(null, "la Query  "+ query +" ha prodotto dalla tabella : "+nomeTab
                              + "\nun totale di "+righe+" righe"
                              + "\nun totale di "+colonne+" colonne"
                              + "","Informazioni sulla tabella :"+nomeTab,
                      JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, new JScrollPane(jtb),"Risultato Query",
                      JOptionPane.INFORMATION_MESSAGE);
           } catch (SQLException ex) {
            JOptionPane.showMessageDialog(jf, "query: "+"\n"+query+" "+"impossibile da svolgere","errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static int numeroRighe(String Table)
    {
     int n=0;
     if(!isConnesso())
         c=connetti();
     if(n!=0)
         n=0;
     ResultSet rs=null;
        try {
            Statement st=c.createStatement();
            rs=st.executeQuery("SELECT COUNT(*) FROM "+Table+"  WHERE 1");
            rs.next();
            n=rs.getInt(1);
            return n;
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(jf, "impossibile connettersi al database per stabilire il numero di righe della tabella "+Table,"errore",JOptionPane.ERROR_MESSAGE);
           return -1; //errore
        }
    }
    
    public static boolean disconnetti()//chiude la connessone dal database
    { 
     boolean esito=false;
     try { 
         if(isConnesso()){
            c.close();
            SetToNull();
            System.gc();
           // JOptionPane.showMessageDialog(jf, "connessione chiusa senza problemi");
            connesso=false;
            esito=true;
         }    
         else 
             esito=false;
            // JOptionPane.showMessageDialog(jf, "Niente da chiudere, non ce connesione al database","attenzione",JOptionPane.WARNING_MESSAGE);
       }catch (SQLException ex) {
         esito=false;
         //JOptionPane.showMessageDialog(jf, "Problemi di disconnesione dal database","errore",JOptionPane.ERROR_MESSAGE);
     }
     return esito;
   }
   
    private static void SetToNull()
    {
     connetti=null;
     user=null;
     pw=null;
    }
                    
    /*public static int GetNumeroTabelle()//restituisce il numero delle tabelle presenti nel database
    {
      try{
         ResultSet rs = null;
         if(!isConnesso())    
           c=connetti();
         if(numTab!=0)
             numTab=0;
         DatabaseMetaData d=c.getMetaData();
         rs=d.getTables(null, null, "%", null);
           while(rs.next())
               numTab++;
         return numTab;
         } catch (SQLException ex) {
             ex.printStackTrace();
             JOptionPane.showMessageDialog(jf, "impossibile connettersi al database per stabilire il numero di tabelle","errore",JOptionPane.ERROR_MESSAGE);
           return -1;//errore 
         }
    }*/
    
     /*public void eseguiQuery(String query)//solo per i comandi select, IL METODO STAMPA NUMERO DI COLONNE, VALORI DELLE COLONNE e nome della tabella in questione
    {
      try {
            JOptionPane.showMessageDialog(jf, "Esecuzione della query: "+query);
            if(!isConnesso())    
             c=connetti();
            ResultSet rs; 
            ResultSetMetaData rsmd;
            Statement stat;
            stat = c.createStatement();
            rs=stat.executeQuery(query);
            rsmd=rs.getMetaData();
            int colonne=rs.getMetaData().getColumnCount();//ottiene il numero di colonne di una tabella del MySQL da una query
            System.out.println("la QUERY ha prodotto dalla tabella "+" "+rsmd.getTableName(colonne)+"  ha trovato "+colonne+"  colonne");
            String col;//stringa di appoggio 
            int righe=numeroRighe(rsmd.getTableName(colonne));
            System.out.println("righe della tabella: "+rsmd.getTableName(colonne)+" "+" "+righe); 
            while(rs.next()){
               for(int i=0;i<colonne;i++)
               {
                 System.out.println();
                 col=rs.getString(i+1);
                 System.out.println(rsmd.getColumnName(i+1)+"="+col);
               }
              System.out.println("\n");
            }
           } catch (SQLException ex) {
            JOptionPane.showMessageDialog(jf, "query: "+"\n"+query+" "+"impossibile da svolgere","errore", JOptionPane.ERROR_MESSAGE);
        }
     }*/
    
     /*private Vector cn() throws SQLException
    {
     nomi=new Vector();
     if(!isConnesso())    
        c=connetti();
     ResultSet rs;
     DatabaseMetaData rsmd;
     rsmd=c.getMetaData();
     rs=rsmd.getTables(null, null, "%", null);
     int numtab=GetNumeroTabelle();
     while(rs.next())
     {
       for(int i=0;i<numtab;i++){
         nomi.add(rs.getMetaData().getTableName(i+1));
         System.out.println(nomi.elementAt(0));
         }
     }
     
     return nomi;
    }*/
    
    
    
   /* public void MostraInteroDatabase()
    {
        try {
            cn();
        } catch (SQLException ex) {
           
        }
        
    
    
    }  */ 
   
   
    /*public void mostra(String tabella)//mostra il database completo con nomi delle tabelle  e per ognuna di esse i suoi attributi
    {//al momento mostra  attributi e contenuto delle tabella fornita come parametro 
     if(!isConnesso())    
       c=connetti();
      ResultSet rs;
         try {
              
              String query="SELECT * FROM "+tabella+" WHERE 1";
              Statement stat = c.createStatement();
              rs=stat.executeQuery(query);
              int colonne=rs.getMetaData().getColumnCount();
              
              System.out.println("colonne della Tabella" +"  "+rs.getMetaData().getTableName(colonne)+"  ha trovato "+colonne+"  colonne");
              String col;
              int righe=numeroRighe(tabella);
              System.out.println("righe della tabella: "+tabella+" "+" "+righe); 
              while(rs.next()){
              for(int i=0;i<colonne;i++)
               {
                 col=rs.getString(i+1);
                 System.out.println(rs.getMetaData().getColumnName(i+1)+"="+col);
               }
               System.out.println("\n"); 
              }
              rs.close();
              stat.close();
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(jf, "impossibile mostrare il database  ");
               ex.printStackTrace();
        }
    }*/
    
       /*public Vector catturanomi() throws SQLException//cattura i nomi delle tabelle presenti nel database e li inserisce in un vettore
    {
         nomi = new Vector();
         if(!isConnesso())    
             c=connetti();
         DatabaseMetaData d=c.getMetaData();
         ResultSet rs=d.getTables(null, null, "%", null);
         Vector record=new Vector();
         while(rs.next())
            record.add(rs.getString(3));
        nomi.add(record);
        return nomi;
    }*/
    
 }