package ListModul;

/**
 * @author cooperdebear
 * 
 * @param <TYP> Legt fest, von welchem Typ das Array sein soll.
 */
public abstract class List<TYP> {
    
    public List(){
        
    }
    
    public abstract void set(int position, TYP content);
    
    public abstract void add(int position, TYP content);
    
    public abstract void add(TYP content);
    
    public abstract void remove(int position);
    
    public abstract TYP getElement(int position);
    
    public abstract int size();
    
    public abstract void sort();
}
