package ListenModul;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ListHandler{
    
    private  LinkedList<Integer> list;
    private Scanner scanner;
    private String eingabe;
    private int position;
    private int content;
    
    public ListHandler(){
        list = new LinkedList<>();
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[]args){
        ListHandler testListe = new ListHandler();
        testListe.starte();
    }
    
    private void starte(){
        while(true){
            processInput();
        }
    }
    
    private void processInput(){
        System.out.println("Was willst du tun? anfuegen/einfuegen/ersetzen/loeschen/anzeigen/allesAnzeigen/suchen/beenden");
        eingabe = scanner.next();
        switch(eingabe)
        {
            case "anfuegen": addContent(); break;
            case "einfuegen": insertContentAtSpecificPoint(); break;
            case "ersetzen": replaceContentAtSpecificPoint(); break;
            case "loeschen" : removeContentAtSpecificPoint(); break;
            case "anzeigen": showContentAtSpecificPoint(); break;
            case "allesAnzeigen": showAllContent(); break;
            //case "suchen": giveBackPositionForContent(); break;
            case "beenden": System.exit(0); break;
            default: System.out.println("Bitte gib etwas von den vorgegebenen Worten ein!");
        }
    }
    
    private void addContent(){
        askForInput();
        list.add(content);
        System.out.println("Erfolgreich angefügt!");
    }
    
    private void insertContentAtSpecificPoint(){
        askForPosition();
        askForInput();
        try{
            list.insert(position ,content);
            System.out.println("Erfolgreich eingefügt!");
        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }
    
    private void replaceContentAtSpecificPoint(){
        askForPosition();
        askForInput();
        try{
            list.set(position ,content);
            System.out.println("Erfolgreich ersetzt!");
        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }
    
    private void removeContentAtSpecificPoint(){
        askForPosition();
        try{
            list.remove(position);
            System.out.println("Erfolgreich gelöscht!");
        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }        
    }
    
    private void showContentAtSpecificPoint(){
        askForPosition();
        try {
            System.out.println(list.getElement(position));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
    
    private void showAllContent()
    {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.getElement(i));
        }
    }
    
    private void askForPosition(){
        System.out.println("An welcher Stelle?");
        try{
            position = scanner.nextInt();
        }catch(InputMismatchException e){
            System.err.println(e.getMessage());
        }
    }
    
    private void askForInput() {
        System.out.println("Was willst du einfügen/suchen?");
        try{
            content = scanner.nextInt();
        }catch(InputMismatchException e){
            System.err.println(e.getMessage());
        }
    }
    
   // private void giveBackPositionForContent(){
   //     askForInput();
    //    if(list.contentIsInList(content)){
   //         System.out.println("Das Gesuchte befindet sich an Position: " + list.giveBackPositionOfContent(content));
   //     }
   //     else{
   //         System.out.println("Das Gesuchte wurde nicht gefunden.");
   //     }
    //}
}
