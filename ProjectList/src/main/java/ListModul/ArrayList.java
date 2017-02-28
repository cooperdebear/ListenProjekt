package ListModul;

/**
 *
 * @author cooperdebear
 * @param <TYP> Gibt an, von welchem Typ die Inhalte des Arrays sein sollen.
 */
    
    private TYP[] arrayList;
    private int counter;
    
    public ArrayList(){
        this.arrayList = (TYP[]) new Object[10];
        counter = 0;
    }
    
    @Override
    public void set(int position, TYP content){
        if(position >= 0 && position < size()){
            arrayList[position] = content;
        }
        else
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
    }
    
    @Override
    public void add(int position, TYP content){
        if(position < 0 || position > size()){
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
        }
        else if(size() == position){
            add(content);
        }            
        else if(fieldIsUsed(position)){
            if(allFieldsAreUsed())
            {
                arrayList = giveBackIncreasedArray(arrayList);
            }
            for(int i = size(); i >= (position+1); i--){                
                counter++;
                set(i, arrayList[i-1]);
                counter--;
            }
            set(position, content);
            counter++;
        }
    }
    
    @Override
    public void add(TYP content){
        if(allFieldsAreUsed()){
            arrayList = giveBackIncreasedArray(arrayList);
        } 
        arrayList[size()] = content;
        counter++;
    }
    
    @Override
    public void remove(int position){
        if(position >= 0 && position < size()){
            
            if(isLastField(position)){
                arrayList = giveBackIncreasedArray(arrayList);
            }
            for(int i = position; i <= (size()-1); i++){
                if( i == (size()-1) )
                {
                    arrayList[i] = null;
                }
                else{
                    arrayList[i] = arrayList[i+1];
                }
            }
            counter--;
        }
        else
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
    }
    
    @Override
    public TYP getElement(int position){
        if(position >= 0 && position < size()){
            return arrayList[position];
        }
        else{
            throw new IllegalArgumentException("Der Wert ist hier nicht zulässig, da er außerhalb des Wertebereichsliegt!");
        }
    }
    
    private boolean fieldIsUsed(int position){
        return position < size();
    }
    
    private boolean allFieldsAreUsed(){
        return size() == arrayList.length;
    }
    
    private boolean isLastField(int position){
        return position == (arrayList.length-1);
    }
    
    private TYP[] giveBackIncreasedArray(TYP[] array){
        array = (TYP[]) new Object[arrayList.length*2];
        System.arraycopy(arrayList, 0, array, 0, arrayList.length);
        return array;
    }
    
    @Override
    public int size(){
        return counter;           
    }
    
    @Override
    public void sort(){
        if(size() <= 2){
            quicksort(0, size()-1);
        }
        else{
            throw new IllegalArgumentException("Die Liste ist Leer!");
        }
    }
    
    private void quicksort(int left, int right){
        if(left < right){
            int divider = split(left, right);
            quicksort(left, divider-1);
            quicksort(divider+1, right);
        }
    }
    
    private int split(int left, int right){
        int i = left;
        int j = right-1;
        TYP pivot = arrayList[right];
        
        do{
            while(arrayList[i].compareTo(pivot) <= 0 && i < right){
                i++;
            }
            while(arrayList[j].compareTo(pivot) >= 0 && j > left){
                j--;
            }
            if(i < j){
                TYP tempElement = getElement(i);
                arrayList[i] = getElement(j);
                arrayList[j] = tempElement;
            }
        }while(i < j);
        
        if(arrayList[i].compareTo(pivot) == 1){
            TYP tempElement = getElement(i);
            arrayList[i] = pivot;
            arrayList[right] = tempElement;
        }
        return i;
    }
    
    private int compare(TYP a, TYP b){
        return a.compareTo(b);
    }

  
}
