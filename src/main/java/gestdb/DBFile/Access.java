package gestdb.DBFile;

import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Access {
     private static Connection c;
     private String nf;
     private JFrame jf;
     private boolean connesso=false;
     private int numTab=0;
     private Vector nomi=null;
     private File db;
    
    public void Access(String nameFile, File a)
    { 
     try {
          jf=new JFrame();
          JOptionPane.showMessageDialog(jf, "Apertura del database dal file: "+a.getName()
                  ,"Utility di Apertura da file" , JOptionPane.INFORMATION_MESSAGE);
          Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
          c= DriverManager.getConnection("jdbc:ucanaccess://"+nameFile);
          db=new File(a.getPath());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(jf, "Apertura del database dal file: "+a.getName()
                  ,"Utility di Apertura da file" , JOptionPane.INFORMATION_MESSAGE);
            c=null;
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(jf, "Apertura del database dal file: "+a.getName()
                  ,"Utility di Apertura da file" , JOptionPane.INFORMATION_MESSAGE);
           c=null;
           
        }
    }
    
    public boolean isConnesso() {
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
    
    public void AllExecuteCommands(String query)//esegue tutti i comandi SQL ESCLUSA LA SELEZIONE 
    {
        try {
            JOptionPane.showMessageDialog(jf, "Esecuzione della query: "+query);
            int num;
            Statement stmt=c.createStatement();
            num=stmt.executeUpdate(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(jf, "query: "+"\n"+query+" "+"impossibile da svolgere","errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String MostraTab() throws SQLException //mostra i nomi e numero delle tabelle presenti  nel database
    {
      String nom="";
      DatabaseMetaData d=c.getMetaData();
      ResultSet rs=d.getTables(null, null, "%", null);
      while (rs.next()) 
        nom+="\n"+(rs.getString(3));
    return nom +" \nNumero Tabelle presenti nel database: "+GetNumeroTabelle();
   }      
      
   public void MostraGrafica(String Table)
    {
     ResultSet rs;
         try {
              String query="SELECT * FROM "+Table+" WHERE 1";
              Statement stat = c.createStatement();
              rs=stat.executeQuery(query);
              int colonne=rs.getMetaData().getColumnCount();
              int righe=numeroRighe(Table);
              JTable jtb=new JTable(buildTableModel(rs));
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

    
    
    
    /*public void eseguiQuery(String query)//solo per i comandi select, IL METODO STAMPA NUMERO DI COLONNE, VALORI DELLE COLONNE e nome della tabella in questione
    {
      try {
            JOptionPane.showMessageDialog(jf, "Esecuzione della query: "+query);
            ResultSet rs; 
            ResultSetMetaData rsmd;
            Statement stat;
            stat = c.createStatement();
            rs=stat.executeQuery(query);
            rsmd=rs.getMetaData();
            int colonne=rs.getMetaData().getColumnCount();//ottiene il numero di colonne di una tabella del dbms da una query
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
    
    public void ExecuteQuery(String query)
    {
      try {
            JOptionPane.showMessageDialog(jf, "Esecuzione della query: "+query);
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
            JOptionPane.getRootFrame().dispose();
            JOptionPane.showMessageDialog(null, new JScrollPane(jtb),"Risultato Query",
                      JOptionPane.INFORMATION_MESSAGE);
           } catch (SQLException ex) {
            JOptionPane.showMessageDialog(jf, "query: "+"\n"+query+" "+"impossibile da svolgere","errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int numeroRighe(String Table)
    {
     int n=0;
     if(n!=0)
         n=0;
     ResultSet rs=null;
        try {
           Table t=DatabaseBuilder.open(db).getTable(Table);
            for(Row r: t)
                n++; 
           return n;
        }catch (IOException ex) {//errore
           JOptionPane.showMessageDialog(jf, "impossibile stabilire il numero di righe della tabella "+Table,"errore",JOptionPane.ERROR_MESSAGE);
           return -1;
         }
    }
            
    public int GetNumeroTabelle()//restituisce il numero delle tabelle presenti nel database
    {
      try{
         ResultSet rs = null;
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
    }
    
    
    public void disconnetti()//chiude la connessone dal database
    { 
     try { 
         if(isConnesso()){
            c.close();
            System.gc();
            JOptionPane.showMessageDialog(jf, "connessione chiusa senza problemi");
            connesso=false;
         }    
         else 
             JOptionPane.showMessageDialog(jf, "Niente da chiudere, non ce connesione al database","attenzione",JOptionPane.WARNING_MESSAGE);
     }catch (SQLException ex) {
         JOptionPane.showMessageDialog(jf, "Problemi di disconnesione dal database","errore",JOptionPane.ERROR_MESSAGE);
     }
   }
   
 
    
}
