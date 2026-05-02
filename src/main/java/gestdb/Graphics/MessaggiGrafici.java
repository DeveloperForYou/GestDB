package gestdb.Graphics;

import javax.swing.JOptionPane;

public class MessaggiGrafici {
   
    public static void InfoApplicazione(){
     JOptionPane.showMessageDialog(null, 
                 "Versione Applicativo : " +FinestraPrincipale.getTitolo()
                 + "\nSviluppato da: Developer for you"
                 + "\nPagina Instagram del progetto: @developerforyou2"
                 +"\nSito web: https://albertogavazzi.it", "informazioni sul programma", 
                 JOptionPane.INFORMATION_MESSAGE);     
    }
    
    public static void ImpostazioniNonDisponibili(){
      JOptionPane.showMessageDialog(null, "Impossibile aprire le impostazioni"
            + "\nFunzione non ancora disponibile!"
            + "\nAttendere un aggiornamento dell'app da parte dello Sviluppatore", 
             "Impostazioni: Funzione non ancora disponibile   ", 
            JOptionPane.ERROR_MESSAGE);
    }
   
    public static void MessaggioConnessioneMancante(){
      JOptionPane.showMessageDialog(null,"Operazione non consentita!"
      + "\n Impossibile disconnettersi da un database senza aver tentato la connessione", 
      "Utility di Disconnessione al Database", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void MessaggioConnessioneMancante1(){
      JOptionPane.showMessageDialog(null,"Operazione non consentita!"
      + "\n Impossibile inviare richieste al database senza aver tentato la connessione", 
      "Utility di Controllo", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void FormatoUrlErrato(){
      JOptionPane.showMessageDialog(null,"Nell'url mancano i simboli : e /"
      + "\n Dati errati", "Utility di Connessione al Database  ", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void ConnessioneChiusaSenzaErrori(){
      JOptionPane.showMessageDialog(null, "Connessione chiusa senza problemi"
      ,"Utility di Disconnessione al Database",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void ConnessioneChiusaConErrori(){
     JOptionPane.showMessageDialog(null, "Problemi di disconnesione dal database",
             "Utility di Disconnessione al Database",JOptionPane.ERROR_MESSAGE);
    }
    
    public static void ConnessioneAnnullata(){
      JOptionPane.showMessageDialog(null,"Connessione Annullata!"
      + "\n Dati Mancanti", "Utility di Connessione al Database  ", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void SegnalazioneInviata(){
      JOptionPane.showMessageDialog(null, "Segnalazione inviata correttamente allo sviluppatore",
      "Utility di Segnalazione Bug: Informazione sulla segnalazione", 
       JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void SegnalazioneNonInviata(Exception e){
      JOptionPane.showMessageDialog(null, "Segnalazione non riuscita: \ncontrollare la connessione a internet"
                    + "\n"+e.getMessage(),"Utility di Segnalazione Bug: Errore", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void SegnalazioneAnnullata(){
      JOptionPane.showMessageDialog(null,"Segnalazione Annullata",
             "Utility di Segnalazione Bug  ", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void SegnalazioneErroreSconosciuto(){
      JOptionPane.showMessageDialog(null,"Si è verificato un errore durante la segnalazione,"
                                 + "\n si prega di riprovare", 
                                 "Utility di Segnalazione Bug ", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void SegnalazioneMailNonValida(){
      JOptionPane.showMessageDialog(null,"Mail non valida,"
                   + "\n si prega di riprovare", 
                   "Utility di Segnalazione Bug ", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void SegnalazioneErroreNoInteternet(){
        JOptionPane.showMessageDialog(null,"Per effettuare una segnalazione è necessario essere connessi a internet", 
        "Utility di Segnalazione Bug  ", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void SegnalazioneFileValido(String filename){
        JOptionPane.showMessageDialog(null,"File Valido"
        +"\n "+filename,
        "Utility di Segnalazione Bug  ", JOptionPane.INFORMATION_MESSAGE);
    
    }
    
    public static void SegnalazioneFileNonValido(String filename){
       JOptionPane.showMessageDialog(null,"File non valido!"
       + "\n "+filename
       + "\n si prega di inserire un file con estensione: JPEG,LOG,PNG"
       + "\n Segnalazione Annullata! ", 
       "Utility di Segnalazione Bug ", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void SegnalazioneNessunFileSelezionato(){
      JOptionPane.showMessageDialog(null,"Nessun File Selezionato",
       "Utility di Segnalazione Bug  ", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void AggiornamentoCompletato(){
     JOptionPane.showMessageDialog(null, "Aggiornamento Completato!"
             + "\n Attendere il riavvio automatico dell'app" ,
             "Utility di aggiornamento", 
             JOptionPane.INFORMATION_MESSAGE); 
    }
    
    public static void AggiornamentoNonCompletato(Exception e){
       JOptionPane.showMessageDialog(null, "Aggiornamento Interrotto per un problema:"
                        + "\nErrore:  "+e.getMessage()
                        , "Utility di Aggiornamento: Errore", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void InizioEstrazione(){
      JOptionPane.showMessageDialog(null, "Estrazione del file di aggiornamento \nattendere"
            + "\nnon chiudere il programma o spegnere il computer", 
              "Utility di aggiornamento: Estrazione", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void EstrazioneConErrori(Exception e){
        JOptionPane.showMessageDialog(null, "Errore di Input/Output"
              + e.getMessage()
            , "Utility di aggionamento: Errore", JOptionPane.ERROR_MESSAGE); 
    }
    
    public static void EstrazioneCompletata(){
      JOptionPane.showMessageDialog(null, "Estrazione Completata" ,
             "Utility di aggiornamento: Informazione", 
             JOptionPane.INFORMATION_MESSAGE); 
    }
    
    public static void EstrazioneImpossibile(){
       JOptionPane.showMessageDialog(null, "Estrazione Impossibile", 
               "Utility di Aggiornamento: Errore di I/O", 
               JOptionPane.ERROR_MESSAGE);
    }
    
    public static void NessunAggiornamentoDisponibile(){
      JOptionPane.showMessageDialog(null, "nessun"
                    + "  aggiornamento", "Utility di Aggiornamento", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void VersioneRecente(){
     JOptionPane.showMessageDialog(null, "nessun"
                        + "  aggiornamento:\n versione più recente in esecuzione ", 
             "Utility di Aggiornamento", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void AggiornamentoDisponibile(String vu){
      JOptionPane.showMessageDialog(null, "Aggiornamento disponibile"
                + "\n Nuova Versione disponibile: " + vu,
                 "Utility di Aggiornamento", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void AggiornamentoAvviato(){
         JOptionPane.showMessageDialog(null, "Avvio Aggionamento in corso..."
                + "\n Si prega di attendere "
                + "\n non spegnere il computer", "Utility di Aggiornamento",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void AggiornamentoInterrotto(){
        JOptionPane.showMessageDialog(null, "Si è verificato un errore"
                    + "\nIl programma si riavvierà",
                     "Errore Grave", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void QueryAnnulllata(){
        JOptionPane.showMessageDialog(null, "Il comando è stato annullato,"
                                      + "\nNessuna Modifica al database apportata!"
                                      ,"Utility Query", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void AnnullaMostraTabella(){
       JOptionPane.showMessageDialog(null, "Il comando è stato annullato,"
                                      + "\nNessuna Tabella verrà mostrata!"
                                      ,"Utility Query", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void FileNonSupportato(){
     JOptionPane.showMessageDialog(null,"Estensione di file non ancora"
                                  + "\nsupportata","Utility di Gestione Database da File: Estensione non supportata",
                                  JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void FileSupportati(){
     JOptionPane.showMessageDialog(null,
                                   "Il file inserito non è di un database!"
                                    + "\ninserire un file del seguente formato:"
                                    + "\n .odb"
                                    + "\n .mdb"
                                    + "\n .accdb"
                                    + "\n .xlsx","Utility di Gestione Database da File: Estensione non supportata",
                                     JOptionPane.INFORMATION_MESSAGE);
    
    }
    
    public static void FileNonSelezionato(){
      JOptionPane.showMessageDialog(null,"Nessun File di Database Selezionato", 
                                                     "Utility di Gestione Database da File",
                                                     JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void MessaggioFileNonAperto(){
      JOptionPane.showMessageDialog(null,"Operazione non consentita!"
      + "\n Impossibile inviare richieste al database senza aver aperto il file", 
      "Utility di Controllo", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void FileChiuso(){
      JOptionPane.showMessageDialog(null,"File Chiuso senza problemi! ", 
      "Utility di Chiusura File Database", JOptionPane.INFORMATION_MESSAGE);
    }
    
}