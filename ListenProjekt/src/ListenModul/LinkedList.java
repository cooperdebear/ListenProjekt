/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListenModul;

/**
 *
 * @author cooperdebear
 * @param <TYP> Gibt an, von welchem Typ der Inhalt der Liste sein soll.
 */
public class LinkedList<TYP> extends List<TYP> {
    
    private Link firstLink = null;
    private Link lastLink = null;
    private Link currentLink = null;
    
    public LinkedList(){
        
    }
    
    public void insert(TYP content) {
        if(isEmpty()){
            currentLink = new Link(null, null, content);
            firstLink = currentLink;
        }
        else if(currentLink.getSuccessor() == null){
            currentLink.setSuccessor(new Link(null, currentLink, content));
            lastLink = currentLink.getSuccessor();
            currentLink = lastLink;
        }
        else{
            Link tempSuccessor = currentLink.getSuccessor();
            currentLink.setSuccessor(new Link(tempSuccessor, currentLink, content));
            tempSuccessor.setPredecessor(currentLink.getSuccessor());
            currentLink = currentLink.getSuccessor();
        }
    }

    @Override
    public void add(TYP content) {
       
    }

    @Override
    public void remove(int position) {

    }

    @Override
    public TYP getIndexOf(int position) {
        
    }

    @Override
    public int size(){
        
    }
    
    public void removeFirst(){
        
    }
    
    public void removeLast(){
        
    }
    
    public boolean isEmpty(){
        
    }
    
    private boolean hasNext(){
        
    }
    
    private boolean hasPrevious(){
        
    }    
}
