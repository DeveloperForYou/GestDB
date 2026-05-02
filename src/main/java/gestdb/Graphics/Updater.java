package gestdb.Graphics;

import gestdb.Update.UpdateV2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;

public class Updater extends JFrame implements ActionListener {
    
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JProgressBar jProgressBar1;
    
    public Updater() {
        initComponents();
    }
    
    private void initComponents() {

        jProgressBar1 = new JProgressBar();
        jLabel1 = new JLabel();
        jButton1 = new JButton();
        jLabel2 = new JLabel();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Utility di Aggiornamento");
        setResizable(false);
        jProgressBar1.setValue(0);
        setIconImage(Icona.setImage(getNameClass()));
        jLabel1.setText("Progresso:");

        jButton1.setText("Verifica Aggiornamenti");
        jButton1.addActionListener(this);

        jLabel2.setText("Questa Finestra ti aiuterà a effettuare l'Aggiornamento ad una Versione più recente");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar1, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }  

    @Override
    public void actionPerformed(ActionEvent e) {
      UpdateV2 up=new UpdateV2(FinestraPrincipale.getTitolo());
      up.Pre_Download();
    }
    
    public  String getNameClass(){
     return this.getClass().getSimpleName();
    }
}
