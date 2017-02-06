/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListenModul;

/**
 *
 * @author cooperdebear
 * @param <TYP> Gibt an, von welchem Typ die Inhalte des Arrays sein sollen.
 */
public class ArrayList<TYP> extends List<TYP>{
    
    private TYP[] arrayList;
    private int counter;
    
    public ArrayList(){
        this.arrayList = (TYP[]) new Object[10];
        counter = 0;
    }
    
    public void set(int position, TYP content){
        if(fieldIsInArrayLength(position)){
            arrayList[position] = content;
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
                set(i, arrayList[i-1]);
                counter--;
            }
            set(position, content);
        }
        else if(fieldIsUsed(position) && allFieldsAfterPositionAreUsed(position)){
            arrayList = giveBackIncreasedArray(arrayList);
            for(int i = size(); i > position; i--){
                set(i, arrayList[i-1]);
                counter--;
            }
            set(position, content);
        }
    }
    
    @Override
    public void add(TYP content){
        if(!allFieldsAreUsed())
            set(size(), content);
        else{
            arrayList = giveBackIncreasedArray(arrayList);
            set(size(), content);
        }
    }
    
    @Override
    public void remove(int position){
        if( fieldIsInArrayLength(position)){
            
            if(isLastField(position))
                arrayList = giveBackIncreasedArray(arrayList);
            
            for(int i = position; i <= (size()-1); i++)
                arrayList[i] = arrayList[i+1];
            counter--;
        }
        else
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
    }
    
    private boolean fieldIsUsed(int position){
        return position < size();
    }
    
    private boolean allFieldsAfterPositionAreUsed(int position){
        for(int i = position; i < arrayList.length; i++){
            if(i < size()){
                return false;
            }
        }
        return true;
    }
    
    private boolean allFieldsAreUsed(){
        return size() == arrayList.length;
    }
    
    private boolean isLastField(int position){
        return position == (arrayList.length-1);
    }
    
    private int giveBackNextFreeField(){
        if(size() < arrayList.length){
            return (size()+1);
        }
        else{
            arrayList = giveBackIncreasedArray(arrayList);
            return (size()+1);
        }
    }
    
    private boolean fieldIsInArrayLength(int position){
        return position < arrayList.length && position >= 0;
    }
    
    private TYP[] giveBackIncreasedArray(TYP[] array){
        array = (TYP[]) new List[array.length*2];
        System.arraycopy(array, 0, array, 0, array.length);
        return array;
    }
    
    @Override
    public TYP getIndexOf(int position){
        return arrayList[position];
    }
    
    @Override
    public int size(){
        return counter;           
    }
}
