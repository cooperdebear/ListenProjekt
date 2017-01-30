package ListenModul;

import java.util.InputMismatchException;

/**
 * @author cooperdebear
 */
public class List {
    String[] liste = new String[5];
    
    public List(){
        
    }
    
    public void set(int position, String content){
        try{
            liste[position] = content;
        }catch(InputMismatchException e){
            System.err.println("Die Position darf nicht verwendet werden! "+ e.getMessage());
        }
    }
    
    public void add(int position, String content){
        if(!fieldIsUsed(position) && fieldIsInArrayLength(position)){
            set(position, content);
        }
        else if(fieldIsUsed(position) && !allFieldsAfterPositionAreUsed(position)){
            for(int i = giveBackNextFreeFieldAfterPosition(position); i > position; i--){
                set(i, liste[i-1]);
            }
            set(position, content);
        }
        else if(fieldIsUsed(position) && allFieldsAfterPositionAreUsed(position)){
            liste = giveBackIncreasedArray(liste);
            for(int i = liste.length; i > position; i--){
                set(i, liste[i-1]);
            }
            set(position, content);
        }
        else if(allFieldsAreUsed()){
            liste = giveBackIncreasedArray(liste);
            position = (liste.length-1);
            set(position, content);
        }
        else{
            
        }
    }
    
    public void remove(int position){
        liste[position] = null;
    }
    
    private boolean fieldIsUsed(int position){
        if(liste[position] != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    private boolean allFieldsAfterPositionAreUsed(int position){
        for(int i = position; i < liste.length; i++){
            if(liste[i]==null){
                return false;
            }
        }
        return true;
    }
    
    private boolean allFieldsBeforePositionAreUsed(int position){
        for(int i = 0; i < position; i++){
            if(liste[i]==null){
                return false;
            }
        }
        return true;
    }
    
    private boolean allFieldsAreUsed(){
        for(int i = 0; i < liste.length; i++){
            if(liste[i] == null){
                return false;
            }
        }
        return true;
    }
    
    private int giveBackNextFreeFieldAfterPosition(int position){
        if(allFieldsAfterPositionAreUsed(position) == false){
            for(int i = position; i < liste.length; i++){
                if(liste[i] == null){
                    return i;
                }
            }
        }
        return liste.length;
    }
    
    private boolean fieldIsInArrayLength(int position){
        if(position < liste.length && position >= 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    private String[] giveBackIncreasedArray(String[] array){
        array = new String[liste.length + 1];
        System.arraycopy(liste, 0, array, 0, liste.length);
        return array;
    }
    
    public String get(int position){
        return liste[position];
    }
    
    public int size(){
        return liste.length;
    }
}
