package ListModul;

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
    public void add(int position, TYP content) {
        if(isEmpty() && position == 0){
            currentLink = new Link(null, null, content);
            firstLink = currentLink;
            lastLink = currentLink;
        }
        else if(position == size()){
            add(content);
        }
        else if(position >= 0 && position < size()){
            iterateCurrentLinkTo(position);
            if(currentLink == firstLink && size() == 1){
                firstLink = new Link (lastLink, null, content);
                currentLink.setPredecessor(firstLink);
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

    private boolean isEmpty(){
        return firstLink == null;
    }
    
    private void iterateCurrentLinkTo(int position){
        currentLink = firstLink;
        for(int counter = 0; counter < position; counter++){
            if(currentLink.getSuccessor() != null){
                currentLink = currentLink.getSuccessor();
            }
        }   
    }
    
    @Override
    public void set(int position, TYP content){
        if(position >= 0 && position < size()){
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
            add(0 , content);
        }
        else{
            currentLink = lastLink;
            currentLink.setSuccessor(new Link(null, currentLink, content));
            lastLink = currentLink.getSuccessor();
        }
    }

    @Override
    public void remove(int position) {
        if(!isEmpty() && position < size() && position >= 0){
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
    
     private void removeFirst(){
        if(hasNext(firstLink)&& firstLink != lastLink){
            currentLink = firstLink.getSuccessor();
            currentLink.setPredecessor(null);
            firstLink = currentLink;
        }
        else{
            firstLink = null;
            currentLink = null;
            lastLink = null;
        }
    }
    
    private void removeLast(){
        currentLink = lastLink.getPredecessor();
        currentLink.setSuccessor(null);
        lastLink = currentLink;
    }
    
    @Override
    public TYP getElement(int position) {
        if(isEmpty()){
            throw new IllegalArgumentException("Die Liste ist Leer!");
        }
        else if(position < 0 || position >= size()){
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
            Link tempCurrentLink = firstLink;
            int counter = 1;
            while(hasNext(tempCurrentLink)){
                tempCurrentLink = tempCurrentLink.getSuccessor();
                counter++;
            }
            return counter;
        }
        
    }
    
    private boolean hasNext(Link link){
        return link.getSuccessor() != null;
    }
    
    public int giveBackPositionOfContent(TYP content){
        if(contentIsInList(content)){
            return contentPosition;
        }
        else{
            return -1;
        }
    }
    
    public boolean contentIsInList(TYP content){
        if(isEmpty()){
            return false;
        }
        else{
            Link tempIterator = firstLink;
            int counter = 0;
            while(!content.equals(tempIterator.getData()))
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
private class Link {
    
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
