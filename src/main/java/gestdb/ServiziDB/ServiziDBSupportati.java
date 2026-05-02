package gestdb.ServiziDB;

public enum ServiziDBSupportati {
    ONE{
        @Override
        public String toString(){
         return "MySQL";
        }
    },
    TWO{
        @Override
        public String toString(){
         return "PostgreSQL";
        }
    };
    
    public static String getValue(ServiziDBSupportati sdb) {
        return sdb.toString();
    }
    
    public static int getQuantiValori(){
      return ServiziDBSupportati.values().length;
    }
    
    public static int OrdineEnum(ServiziDBSupportati sdb){
      return sdb.ordinal();
    }
    
    
    public static String OttieniEnum(int ordinal){
       ServiziDBSupportati[] sdb =ServiziDBSupportati.values();    
       return sdb[ordinal].toString();
    }
}