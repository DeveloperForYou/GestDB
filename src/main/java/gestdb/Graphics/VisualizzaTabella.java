package gestdb.Graphics;

import gestdb.ServiziDB.GestioneChiamateDB;
import gestdb.ServiziDB.MySQL;
import gestdb.ServiziDB.PostgreDB;
import gestdb.ServiziDB.ServiziDBSupportati;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;

public class VisualizzaTabella extends JFrame{
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JList<String> jList1;
    private JScrollPane jScrollPane1;
    private DefaultListModel<String> model;
    
    public VisualizzaTabella(){
      initComponents();
    }
    
    private void initComponents() {

        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        model= new DefaultListModel<>();
        jList1 = new JList<>(model);
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("MostraTabellaForm");
        setResizable(false);
        setSize(new Dimension(500, 350));
        setIconImage(Icona.setImage(getNameClass()));
        jLabel1.setText("Questo form ti aiuterà a selezionare la tabella di cui ottenere una stampa completa");

        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
        jList1.setDropMode(DropMode.ON);
        InizializzaListaTabelle();
        
        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("    Seleziona nella lista sottostante la tabella da stampare:");
        jLabel3.setText("Tabelle Trovate: "+jList1.getModel().getSize());
        jButton1.setText("Mostra Tabella");
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String table=jList1.getSelectedValue();
                dispose();
                if(table!=null | table!="")
                   GestioneChiamateDB.Azione("Mostra Tabella", table);
                else
                    MessaggiGrafici.AnnullaMostraTabella();
            }
        });
        
        jButton2.setText("Annulla");
        jButton2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MessaggiGrafici.AnnullaMostraTabella();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(114, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(29, 29, 29))))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jLabel3))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    public String getNameClass(){
     return this.getClass().getSimpleName();
    }

    private void InizializzaListaTabelle() {
       String servizio=GestioneChiamateDB.getServizio();
       if(servizio!=null){
        if(servizio.equals(ServiziDBSupportati.getValue(ServiziDBSupportati.ONE))){//servizio=MySQL
           ArrayList<String> tab=MySQL.MostraTabelle();
           for(int i=0;i<tab.size();i++)
             model.addElement(tab.get(i));
        }
        if(servizio.equals(ServiziDBSupportati.getValue(ServiziDBSupportati.TWO))){//servizio=Postgre
           ArrayList<String> tab=PostgreDB.MostraTabelle();
           for(int i=0;i<tab.size();i++)
             model.addElement(tab.get(i));
       

        }
       }
       else{
       
       
       }
    }
     
}