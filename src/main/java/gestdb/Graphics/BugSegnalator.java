package gestdb.Graphics;

import gestdb.BugSegnalator.Email;
import gestdb.Internet.CheckInternet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;

import static javax.swing.BorderFactory.createTitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.commons.io.FilenameUtils;

public class BugSegnalator extends JFrame implements ActionListener {
    
    private JButton jButton1;
    private JButton jButton5;
    private JButton jButton6;
    private JFileChooser jFileChooser1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;

    
    public BugSegnalator() {
        initComponents();
    }
    
    private void initComponents() {

        jPanel1 = new JPanel();
        jFileChooser1 = new JFileChooser();
        jButton5 = new JButton();
        jButton6 = new JButton();
        jTextField1 = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jTextField2 = new JTextField();
        jLabel3 = new JLabel();
        jTextField3 = new JTextField();
        jLabel5 = new JLabel();
        jButton1 = new JButton();
        jLabel6 = new JLabel();
        jLabel4 = new JLabel();
        jLabel7 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jPanel1.setBorder(createTitledBorder(" Name "));
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGap(296, 296, 296))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Utility di segnalazione Bug");
        setResizable(false);
        setIconImage(Icona.setImage(getNameClass()));
        jButton5.setText("Annulla Segnalazione");
        jButton5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              dispose();
              MessaggiGrafici.SegnalazioneAnnullata();
            }
        });
        jButton6.setText("Segnala");
        jButton6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              Email em=null;
              if(CheckInternet.checkInternetConnection()){
               if(!jTextField1.getText().isEmpty() & !jTextField2.getText().isEmpty() & !jTextField3.getText().isEmpty() & !jTextArea1.getText().isEmpty()){
                 if(Email.ValidaEmail(jTextField3.getText())){
                  if(jFileChooser1.getSelectedFile()!=null){
                    try { 
                         File att=jFileChooser1.getSelectedFile();
                         em=new Email(jTextField3.getText(),jTextArea1.getText(),att);
                         dispose();
                         em.sendWithAttached(jTextField3.getText(), jTextArea1.getText(), att);
                     } catch (FileNotFoundException ex) {
                         ex.printStackTrace();
                         MessaggiGrafici.SegnalazioneErroreSconosciuto();
                     }
                 }
                 else{
                     em=new Email(jTextField3.getText(),jTextArea1.getText());
                     dispose(); 
                     em.sendWithOutAttached(jTextField3.getText(), jTextArea1.getText());
                 } 
               }
              }
              else
                   MessaggiGrafici.SegnalazioneMailNonValida();
             }
             else
                  MessaggiGrafici.SegnalazioneErroreNoInteternet();
            }
        });
        jLabel1.setText("Nome:");

        jLabel2.setText("Cognome:");

        jLabel3.setText("Email:");

        jLabel5.setText("File:");

        jButton1.setText("AllegaFile");
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               jFileChooser1.setDialogTitle("Seleziona l'allegato da fornire allo sviluppatore");
               FiltriFile[] ff=new FiltriFile[3];
               ff[0]=new FiltriFile(".log","LogFile");
               ff[1]=new FiltriFile(".png","ImageFile");
               ff[2]=new FiltriFile(".jpeg","ImageFile");
               for(int i=0;i<ff.length;i++)
                 jFileChooser1.addChoosableFileFilter(ff[i]);
               int esito=jFileChooser1.showOpenDialog(null);
               switch(esito){
                  case JFileChooser.APPROVE_OPTION:String ext=FilenameUtils.getExtension(jFileChooser1.getSelectedFile().getName());
                                                   if(Email.ValidaFileAllegato(ext))
                                                       MessaggiGrafici.SegnalazioneFileValido(jFileChooser1.getSelectedFile().getName());
                                                   else
                                                       MessaggiGrafici.SegnalazioneFileNonValido(jFileChooser1.getSelectedFile().getName());
                                                    break;
                                                    
                  case JFileChooser.CANCEL_OPTION:  MessaggiGrafici.SegnalazioneNessunFileSelezionato();
                                                    break;                                  
               }
            }
        });
        jLabel6.setText("Questa Funzione ti aiuterà a segnalare Bug oppure problemi in modo che lo sviluppatore possa correggerli");

        jLabel4.setText("Allegare un file è facoltativo ma Consigliato ");

        jLabel7.setText("Descrivi nel modo più preciso il problema rilevato");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField3, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                                .addComponent(jTextField2))))
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(0, 65, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(72, 72, 72)))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel4))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5))
                .addGap(23, 23, 23))
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