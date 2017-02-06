/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListenModul;

/**
 *
 * @author cooperdebear
 */
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
