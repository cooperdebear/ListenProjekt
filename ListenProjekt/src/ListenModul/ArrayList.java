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
    
    @Override
    public void set(int position, TYP content){
        if(fieldIsInArrayLength(position)){
            arrayList[position] = content;
            counter++;
        }
        else
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
    }
    
    @Override
    public void insert(int position, TYP content){
        if(!fieldIsInArrayLength(position)){
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
        }
        else if(position >= size()){
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
                set(i, getElement(i-1));
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
        if(fieldIsInArrayLength(position) && position < size()){
            
            if(isLastField(position)){
                arrayList = giveBackIncreasedArray(arrayList);
            }
            for(int i = position; i <= (size()-1); i++){
                if( i == (size()-1) )
                {
                    arrayList[i] = null;
                }
                else{
                    arrayList[i] = getElement(i+1);
                }
            }
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
        if((size()+1) < arrayList.length){
            return (size());
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
        array = (TYP[]) new Object[arrayList.length*2];
        System.arraycopy(arrayList, 0, array, 0, arrayList.length);
        return array;
    }
    
    @Override
    public TYP getElement(int position){
        if(fieldIsInArrayLength(position) && position < size()){
            return arrayList[position];
        }
        else{
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
        }
    }
    
    @Override
    public int size(){
        return counter;           
    }
}
