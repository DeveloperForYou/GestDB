package gestdb.Graphics;

import gestdb.ServiziDB.GestioneChiamateDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class ConnessioneDB extends JFrame implements  ActionListener{  
    
    private JButton jButton1;//conetti
    private JButton jButton2;//annulla
    private JFrame jFrame1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JPasswordField jPasswordField1;//pass
    private JTextField jTextField1;//username
    private JTextField jTextField2;//url
    private JComboBox<String> jComboBox1;
    
    public ConnessioneDB() {
        initComponents();
    }

    private void initComponents() {
        setIconImage(Icona.setImage(getNameClass()));
        jFrame1 = new JFrame();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jLabel2 = new JLabel();
        jPasswordField1 = new JPasswordField();
        jLabel3 = new JLabel();
        jTextField2 = new JTextField();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jComboBox1 = new JComboBox<>();
        jLabel6=new JLabel();
        GroupLayout jFrame1Layout = new GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Utility di connessione al Database");
        setResizable(false);
        
        jButton1.setText("Connetti");
        jButton1.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
           String servizio=jComboBox1.getSelectedItem().toString();  
           GestioneChiamateDB.setServizio(servizio);              
           String url=jTextField2.getText();
           String user=jTextField1.getText();
           String pw=new String(jPasswordField1.getPassword());
           if((!user.isEmpty()) & (!url.isEmpty())){
              if(GestioneChiamateDB.ValidaURL(url)){
                if(GestioneChiamateDB.PreparaConnessione(url,user,pw,servizio)){
                    dispose();
                    FinestraPrincipale.InizializzaTextAreaRemota();
                }
              }
           }
           else{
              MessaggiGrafici.ConnessioneAnnullata();
           }
          }
        });
        jButton2.setText("Annulla");
        jButton2.addActionListener(new ActionListener(){
           @Override
          public void actionPerformed(ActionEvent e){
            MessaggiGrafici.ConnessioneAnnullata();
            dispose();
          }
        });
        
        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        jLabel3.setText("Url Database:");

        jLabel4.setText("se non richiesto, non inserire ");

        jLabel5.setText("Questo form ti aiuterà a connetterti al database");
        jComboBox1.setModel(new DefaultComboBoxModel<>(GestioneChiamateDB.getServiziSupportati()));
        jLabel6.setText("Seleziona il servizio del Database");
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
                        .addGap(364, 364, 364))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(85, 85, 85))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
            .addGroup(layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(32, 32, 32))
        );
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public String getNameClass(){
     return this.getClass().getSimpleName();
    }   
}