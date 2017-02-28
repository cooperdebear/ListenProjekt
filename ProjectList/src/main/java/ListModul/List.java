package ListModul;

/**
 * @author cooperdebear
 * 
 * @param <TYP> Legt fest, von welchem Typ das Array sein soll.
 */
public abstract class List<TYP extends Comparable<TYP>> {
    
    public List(){
        
    }
    
    public abstract void set(int position, TYP content);
    
    public abstract void add(int position, TYP content);
    
    public abstract void add(TYP content);
    
    public abstract void remove(int position);
    
    public abstract TYP getElement(int position);
    
    public abstract int size();
    
    public void sort() {
        if(size() > 0){
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
        TYP pivot = getElement(right);
        
        do{
            while(getElement(i).compareTo(pivot) <= 0 && i < right){
                i++;
            }
            while(getElement(j).compareTo(pivot) >= 0 && j > left){
                j--;
            }
            if(i < j){
                TYP tempElement = getElement(i);
                set(i, getElement(j));
                set(j, tempElement);
            }
        }while(i < j);
        
        if(getElement(i).compareTo(pivot) == 1){
            TYP tempElement = getElement(i);
            set(i, pivot);
            set(right, tempElement);
        }
        return i;
    }
}
