import java.io.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
readFile();

    }
    public static void readFile()throws FileNotFoundException {
        TextEditor te=new TextEditor();
        Scanner input=new Scanner(System.in);
        Scanner reader=null;
        String actionType=null;
        String text=null;
        int length=0;
        String newText=null;
        int position=0;
            try {
                System.out.print("Please enter file name:");
                String fName=input.nextLine();
                    reader = new Scanner(new File(fName));
                       while(reader.hasNextLine()){
                           String line = reader.nextLine();
                           String[] arr = line.split(" ");
                           actionType = arr[0];
                              switch (actionType){
                                  case "insert":
                                      int lastSpaceIndex = line.lastIndexOf(" ");
                                      int firstSpaceIndex = line.indexOf(" ");
                                      position = Integer.parseInt(line.substring(lastSpaceIndex + 1));
                                      text = line.substring(firstSpaceIndex + 1, lastSpaceIndex);
                                     te.insert(text,position);
                                     System.out.println(te);
                                     break;
                                  case "delete":
                                      position=Integer.parseInt(arr[1]);
                                      length=Integer.parseInt(arr[2]);
                                      te.delete(position,length);
                                      System.out.println(te);
                                      break;

                                  case "replace":
                                      newText=arr[1];
                                      position=Integer.parseInt(arr[2]);
                                      length=Integer.parseInt(arr[3]);
                                      te.replace(newText,position,length);
                                      System.out.println(te);
                                         break;
                                  case "undo":
                                      te.undo();
                                      System.out.println(te);
                                      break;
                                  case "redo":
                                      te.redo();
                                      System.out.println(te);
                                      break;
                                  default:
                                      System.out.println("invalid action type");
                                      break;


                              }


                    }
            } catch (FileNotFoundException e) {
                    System.out.println("File cannot find");
            }

    }
}