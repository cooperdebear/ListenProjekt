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
    
    private Link firstLink;
    private Link lastLink;
    private Link currentLink;
    
    public LinkedList(){
        firstLink = null;
        lastLink = null;
        currentLink = null;
    }
    
    public void insert(int position, TYP content) {
        if(isEmpty()){
            currentLink = new Link(null, null, content);
            firstLink = currentLink;
        }
        else{
            currentLink = firstLink;
            int counter = 1;
            while(counter < position){
                currentLink = currentLink.getSuccessor();
            }
            if(currentLink == firstLink && size() == 1){
                lastLink = currentLink;
                firstLink = new Link (lastLink, null, content);
                currentLink.setPredecessor(firstLink);
                firstLink.setSuccessor(lastLink);
                currentLink = firstLink;
            }
            else if(currentLink == firstLink){
                currentLink.setPredecessor(new Link(currentLink, null, content));
                firstLink = currentLink.getPredecessor();
            }
            if(currentLink == lastLink){
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
    }

    @Override
    public void add(TYP content) {
        if(isEmpty()){
            insert(1 , content);
        }
        else{
            currentLink = getLastLink();
            currentLink.setSuccessor(new Link(null, currentLink, content));
            lastLink = currentLink.getSuccessor();
            currentLink = lastLink;
        }
    }

    @Override
    public void remove(int position) {
        if(!isEmpty() && position <= size()){
            currentLink = firstLink;
            int counter = 1;
            while(counter < position){
                currentLink = currentLink.getSuccessor();
            }
            if(currentLink == firstLink){
                firstLink = currentLink.getSuccessor();
                firstLink.setPredecessor(null);
                currentLink = firstLink;
            }
            else if(currentLink.getSuccessor() == null){
                currentLink = currentLink.getPredecessor();
                currentLink.setSuccessor(null);
                lastLink = currentLink;
            }
            else{
                Link tempSuccessor = currentLink.getSuccessor();
                Link tempPredecessor = currentLink.getPredecessor();
                currentLink = tempPredecessor;
                currentLink.setSuccessor(tempSuccessor);
                tempSuccessor.setPredecessor(currentLink);
            }
        }
        else throw new IllegalArgumentException("Die angegebene Position ist nicht im Geltungsbereich!");
    }
    
    @Override
    public TYP getElement(int position) {
        if(isEmpty() || position > size()){
            return null;
        }
        else{
            currentLink = firstLink;
            int counter = 1;
            while(counter <= position){
                currentLink = currentLink.getSuccessor();
            }
            return (TYP) currentLink.getData();
        }
    }

    @Override
    public int size(){
        if(isEmpty()){
            return 0;
        }
        else{
            currentLink = firstLink;
            int counter = 1;
            while(currentLink.getSuccessor() != null){
                currentLink = currentLink.getSuccessor();
                counter++;
            }
            return counter;
        }
        
    }
    
    public void removeFirst(){
        if(hasNext(firstLink)){
            currentLink = firstLink.getSuccessor();
            currentLink.setPredecessor(null);
            firstLink = currentLink;
        }
        else{
            firstLink = null;
        }
    }
    
    public void removeLast(){
        currentLink = lastLink.getPredecessor();
        currentLink.setSuccessor(null);
        lastLink = currentLink;
    }
    
    public boolean isEmpty(){
        return firstLink.getData() == null;
    }
    
    private boolean hasNext(Link link){
        return link.getSuccessor() != null;
    }
    
    private Link getLastLink(){
        while(currentLink.successor != null){
            currentLink = currentLink.successor;
        }
        return currentLink;
    }
    
// innere Klasse Link
public class Link {
    
    private Link successor = null;
    private Link predecessor = null;
    private Object data = null;
    
    public Link(Link successor, Link predecessor, Object data){
        
        this.successor = successor;
        this.predecessor = predecessor;
        this.data = data;
    }
    
    public Link getSuccessor() {
        return successor;
    }

    public void setSuccessor(Link successor) {
        this.successor = successor;
    }

    public Link getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Link predecessor) {
        this.predecessor = predecessor;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
}    
}
