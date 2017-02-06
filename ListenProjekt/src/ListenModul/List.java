package ListenModul;

/**
 * @author cooperdebear
 * 
 * @param <TYP> Legt fest, von welchem Typ das Array sein soll.
 */
public abstract class List<TYP> {
    
    protected List(){
        
    }
        
    // abstract void insert(int position, TYP content);
    
    abstract void add(TYP content);
    
    abstract void remove(int position);
    
    abstract TYP getIndexOf(int position);
    
    abstract int size();
}
