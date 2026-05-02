package gestdb.Graphics;

import gestdb.ServiziDB.GestioneChiamateDB;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.lang3.StringUtils;

public class QueryForm extends JFrame implements DocumentListener {
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JTextField jTextField2; 
    
    public QueryForm() {
        initComponents();
    }
    
    private void initComponents() {
        
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jLabel2 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jLabel3 = new JLabel();
        jTextField2 = new JTextField();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("QueryForm");
        setResizable(false);
        setSize(new Dimension(500, 500));
        setIconImage(Icona.setImage(getNameClass()));
        jLabel1.setText("      Questo Form ti aiuterà ad effettuare la query al database");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.getDocument().addDocumentListener(this);
        
        jLabel2.setText("Digita qui la query in Liguaggio SQL che vuoi effettuare:");

        jButton1.setText("Invia Query");
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              System.out.println(jTextArea1.getText());
              String query=jTextArea1.getText();
              if(query.isEmpty())
                   MessaggiGrafici.QueryAnnulllata();
              else{
                   String Azione="";
                   if(StringUtils.startsWithIgnoreCase(query, "SELECT"))
                       Azione="Selezione";
                   else
                       Azione="Qualsiasi Comando";
                   GestioneChiamateDB.Azione(Azione, query);
              }
            }
        });

        jButton2.setText("Annulla");
        jButton2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 dispose();
                 MessaggiGrafici.QueryAnnulllata();
            } 
        });
        jLabel3.setText("La query appartiene ai comandi:");

        jTextField2.setEditable(false);
        jTextArea1.getDocument().addDocumentListener(this);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pack();
    }
    
    public String getNameClass(){
      return this.getClass().getSimpleName();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String cmd=jTextArea1.getText();
        if(!cmd.isEmpty())
            StabilisciComando(cmd);
        else
            jTextField2.setText("Sconosciuto");
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String cmd=jTextArea1.getText();
        if(!cmd.isEmpty())
            StabilisciComando(cmd);
        else
            jTextField2.setText("Sconosciuto");
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
    
    public void StabilisciComando(String cmd){
        System.out.println(StringUtils.substring(cmd,0, 8));
        String start=StringUtils.substring(cmd,0, 8);
        String[] ddl={"CREATE","DROP","TRUNCATE","ALTER"};
        String[] dcl={"GRANT","REVOKE"};
        String[] dml={"INSERT","UPDATE","DELETE"};
        String[] tcl={"COMMIT","ROLLBACK","SAVE POINT"};
        String dql="SELECT";
        if(start!=null | start.length()>0){
          if(StringUtils.startsWithIgnoreCase(start, dql))
                jTextField2.setText("DQL");
          else{
               for(int i=0;i<ddl.length;i++){
                 if(StringUtils.containsIgnoreCase(start, ddl[i]))
                     jTextField2.setText("DDL");
               }
               for(int i=0;i<dcl.length;i++){
                 if(StringUtils.containsIgnoreCase(start, dcl[i]))
                     jTextField2.setText("DCL");
               }
              for(int i=0;i<dml.length;i++){
                if(StringUtils.containsIgnoreCase(start, dml[i]))
                     jTextField2.setText("DML");
              }
              for(int i=0;i<tcl.length;i++){
                if(StringUtils.containsIgnoreCase(start, tcl[i]))
                     jTextField2.setText("TCL");
              }
           }
        }
    }
}