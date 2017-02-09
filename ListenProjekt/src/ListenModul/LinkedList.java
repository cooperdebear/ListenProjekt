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
    private int contentPosition;
    
    public LinkedList(){
        firstLink = null;
        lastLink = null;
        currentLink = null;
    }
    
    @Override
    public void insert(int position, TYP content) {
        if(isEmpty()){
            currentLink = new Link(null, null, content);
            firstLink = currentLink;
        }
        else if(position <= size()){
            iterateCurrentLinkTo(position);
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
            else{
                Link tempPredecessor = currentLink.getPredecessor();
                Link newPredecessor = new Link(currentLink, tempPredecessor, content);
                currentLink.setPredecessor(newPredecessor);
                tempPredecessor.setSuccessor(newPredecessor);
                currentLink = currentLink.getPredecessor();
            }
        }
        else{
            throw new IllegalArgumentException("Die angegebene Position ist nicht im Geltungsbereich!");
        }
    }

    public void replace(int position, TYP content){
        if(isEmpty() && position == 1){
            currentLink = new Link(null, null, content);
            firstLink = currentLink;
        }
        else if(position <= size()){
            iterateCurrentLinkTo(position);
            currentLink.setData(content);
        }
        else{
            throw new IllegalArgumentException("Die angegebene Position ist nicht im Geltungsbereich!");
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
            iterateCurrentLinkTo(position);
            if(currentLink == firstLink){
                removeFirst();
            }
            else if(currentLink == lastLink){
                removeLast();
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
        if(isEmpty()){
            throw new IllegalArgumentException("Die Liste ist Leer!");
        }
        else if(position > size()){
            throw new IllegalArgumentException("Die angegebene Position ist nicht im Geltungsbereich!");
        }
        else{
            iterateCurrentLinkTo(position);
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
            int counter = 0;
            while(hasNext(currentLink)){
                currentLink = currentLink.getSuccessor();
                counter++;
            }
            return counter+1;
        }
        
    }
    
    private void removeFirst(){
        if(hasNext(firstLink)){
            currentLink = firstLink.getSuccessor();
            currentLink.setPredecessor(null);
            firstLink = currentLink;
        }
        else{
            firstLink = null;
        }
    }
    
    private void removeLast(){
        currentLink = lastLink.getPredecessor();
        currentLink.setSuccessor(null);
        lastLink = currentLink;
    }
    
    private boolean isEmpty(){
        return firstLink == null;
    }
    
    private boolean hasNext(Link link){
        return link.getSuccessor() != null;
    }
    
    private Link getLastLink(){
        while(hasNext(currentLink)){
            currentLink = currentLink.successor;
        }
        return currentLink;
    }
    
    private void iterateCurrentLinkTo(int position){
        if(position >= size())
        {
            throw new IllegalArgumentException("Die angegebene Position ist nicht im Geltungsbereich!");
        }        
        else{
            currentLink = firstLink;
            int counter = 0;
            while(counter < position){
                if(currentLink.getSuccessor() != null){
                    currentLink = currentLink.getSuccessor();
                    counter++;
                }
            }   
        }
        
    }
    
    public int giveBackPositionOfContent(TYP content){
        if(contentIsInList(content)){
            return contentPosition;
        }
        else{
            return -1;
        }
    }
    
    public boolean contentIsInList(TYP Content){
        if(isEmpty()){
            return false;
        }
        else{
            Link tempIterator = firstLink;
            int counter = 0;
            while(!Content.equals(tempIterator.getData()))
            {
                counter++;
                if(counter >= size())
                {
                    return false;
                }
                tempIterator = tempIterator.getSuccessor();
            }
            contentPosition = counter;
            return true;
        }
        
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
