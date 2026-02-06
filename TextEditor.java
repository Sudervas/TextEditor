import java.util.EmptyStackException;
import java.util.Stack;

public class TextEditor {
private Stack<Action>undoStack=new Stack<>();
private Stack<Action>redoStack=new Stack<>();
private String text="";
private boolean isUndoRedoOperation;
public void insert(String text,int position) {

    if(position<0||position>this.text.length()){
        throw new IllegalArgumentException("position is invalid");
    }

   if(this.text.length()==0){
           this.text=text;
       }
   else{
       this.text=this.text.substring(0,position)+text+this.text.substring(position,this.text.length());
   }
    if (!isUndoRedoOperation) {
        undoStack.push(new Action("insert", text, position));
        clear();
    }

}

public void delete(int position,int length){

    if(position<0||position>this.text.length()){
        throw new IllegalArgumentException("position is invalid");
    }
    if(position+length>this.text.length()){
        throw new IllegalArgumentException("Deleting length is bigger than text's length");
    }
    String deleted=this.text.substring(position,position+length);
   String first=this.text.substring(0,position);
   String last=this.text.substring(position+length,this.text.length());
   this.text=first+last;
    if (!isUndoRedoOperation) {
        undoStack.push(new Action("delete", deleted, position));
        clear();
    }
}
public void replace(String newText,int position,int length){

    if(position<0||position>this.text.length()){
        throw new IllegalArgumentException("position is invalid");
    }
    if(position+length>this.text.length()){
        throw new IllegalArgumentException("Deleting length is bigger than text's length");
    }
    String changed=this.text.substring(position,position+length);
    this.text=this.text.substring(0,position)+newText+this.text.substring(position+length,this.text.length());
    if (!isUndoRedoOperation) {
        undoStack.push(new Action("replace", changed, newText, position));
        clear();
    }
}
public void undo()throws EmptyStackException{
    if(undoStack.isEmpty()){
        throw new EmptyStackException();
    }
    isUndoRedoOperation = true;
    Action action = undoStack.pop();
    try {
        switch (action.getActionType()) {
            case "insert":
                delete(action.getPosition(), action.getText().length());
                break;
            case "delete":
                insert(action.getText(), action.getPosition());
                break;
            case "replace":
                replace(action.getOldText(), action.getPosition(), action.getText().length());
                break;
            default:
                System.out.println("invalid action type");

                break;


        }
    }finally {
        isUndoRedoOperation=false;
    }

    redoStack.push(action);


}
public void redo()throws EmptyStackException{

    if (redoStack.isEmpty()) {
        throw new EmptyStackException();
    }
    isUndoRedoOperation=true;
    Action action=redoStack.pop();
    try {
        switch (action.getActionType()) {
            case "insert":
                insert(action.getText(), action.getPosition());
                break;
            case "delete":
                delete(action.getPosition(), action.getText().length());
                break;
            case "replace":
                replace(action.getText(), action.getPosition(), action.getOldText().length());
                break;
            default:
                System.out.println("invalid action type");

                break;


        }
    }finally {
        isUndoRedoOperation=false;
    }

    undoStack.push(action);


}
public void clear(){
   redoStack.clear();
}


public String toString(){
   return text;
}
}
