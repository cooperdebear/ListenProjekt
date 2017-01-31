package ListenModul;

/**
 * @author cooperdebear
 * 
 * @param <TYP> Legt fest, von welchem Typ das Array sein soll.
 * 
 */
public class List<TYP> {
    private TYP[] list;
    private int counter;
    
    public List(){
        this.list = (TYP[]) new Object[10];
        counter = 0;
    }
    
    public void set(int position, TYP content){
        if(fieldIsInArrayLength(position)){
            list[position] = content;
            counter++;
        }
        else
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
    }
    
    public void insert(int position, TYP content){
        if(!fieldIsInArrayLength(position)){
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
        }
        else if(position > size()){
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
        }
        else if(!fieldIsUsed(position) && fieldIsInArrayLength(position)){
            set(position, content);
        }            
        else if(fieldIsUsed(position)){
            for(int i = giveBackNextFreeField(); i > position; i--){
                set(i, list[i-1]);
                counter--;
            }
            set(position, content);
        }
        else if(fieldIsUsed(position) && allFieldsAfterPositionAreUsed(position)){
            list = giveBackIncreasedArray(list);
            for(int i = size(); i > position; i--){
                set(i, list[i-1]);
                counter--;
            }
            set(position, content);
        }
    }
    
    public void add(TYP content){
        
        if(fieldIsInArrayLength(size()))
            set(size(), content);
        else{
            list = giveBackIncreasedArray(list);
            set(size(), content);
        }
    }
    
    public void remove(int position){
        if( fieldIsInArrayLength(position)){
            
            if(isLastField(position))
                list = giveBackIncreasedArray(list);
            
            for(int i = position; i <= (size()-1); i++)
                list[i] = list[i+1];
            counter--;
        }
        else
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
    }
    
    private boolean fieldIsUsed(int position){
        if(position < size()){
            return true;
        }
        else{
            return false;
        }
    }
    
    private boolean allFieldsAfterPositionAreUsed(int position){
        for(int i = position; i < list.length; i++){
            if(i < size()){
                return false;
            }
        }
        return true;
    }
    
    private boolean allFieldsBeforePositionAreUsed(int position){
        for(int i = 0; i < position; i++){
            if(list[i]==null){
                return false;
            }
        }
        return true;
    }
    
    private boolean allFieldsAreUsed(){
        if(size() == list.length){
            return true;
        }
        
        return false;
    }
    
    private boolean isLastField(int position){
        if(position == (list.length-1))
            return true;
        else
            return false;
    }
    
    private int giveBackNextFreeField(){
        if(size() < list.length){
            return (size()+1);
        }
        else{
            list = giveBackIncreasedArray(list);
            return (size()+1);
        }
    }
    
    private boolean fieldIsInArrayLength(int position){
        if(position < list.length && position >= 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    private TYP[] giveBackIncreasedArray(TYP[] array){
        array = (TYP[]) new List[list.length*2];
        System.arraycopy(list, 0, array, 0, list.length);
        return array;
    }
    
    public TYP get(int position){
        return list[position];
    }
    
    public int size(){
        return counter;           
    }
}
