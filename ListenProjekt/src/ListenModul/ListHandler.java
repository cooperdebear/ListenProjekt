package ListenModul;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ListHandler{
    
    private  List<Integer> liste;
    private Scanner scanner;
    private String eingabe;
    private int position;
    private int content;
    
    public ListHandler(){
        liste = new List<Integer>();
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
        System.out.println("Was willst du tun? anfuegen/einfuegen/ersetzen/loeschen/anzeigen/allesAnzeigen/beenden");
        eingabe = scanner.next();
        switch(eingabe)
        {
            case "anfuegen": addContent(); break;
            case "einfuegen": insertContentAtSpecificPoint(); break;
            case "ersetzen": replaceContentAtSpecificPoint(); break;
            case "loeschen" : removeContentAtSpecificPoint(); break;
            case "anzeigen": showContentAtSpecificPoint(); break;
            case "allesAnzeigen": showAllContent(); break;
            case "beenden": System.exit(0); break;
            default: System.out.println("Bitte gib etwas von den vorgegebenen Worten ein!");
        }
    }
    
    private void addContent(){
        askForInput();
        try{
            liste.add(content);
            System.out.println("Erfolgreich angefügt!");
        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }
    
    private void insertContentAtSpecificPoint(){
        askForPosition();
        askForInput();
        try{
            liste.insert(position ,content);
            System.out.println("Erfolgreich eingefügt!");
        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }
    
    private void replaceContentAtSpecificPoint(){
        askForPosition();
        askForInput();
        try{
            liste.set(position ,content);
            System.out.println("Erfolgreich ersetzt!");
        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }
    
    private void removeContentAtSpecificPoint(){
        askForPosition();
        try{
            liste.remove(position);
            System.out.println("Erfolgreich gelöscht!");
        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }        
    }
    
    private void showContentAtSpecificPoint(){
        askForPosition();
        System.out.println(liste.get(position));
    }
    
    private void showAllContent()
    {
        for (int i = 0; i < liste.size(); i++) {
            System.out.println(liste.get(i));
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
        System.out.println("Was willst du einfügen?");
        try{
            content = scanner.nextInt();
        }catch(InputMismatchException e){
            System.err.println(e.getMessage());
        }
    }
}
