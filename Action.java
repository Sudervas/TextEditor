public class Action {
    private String actionType;
    private String oldText;
    private int position;
    private String text;
    public Action(String actionType,String text,int position){
        this.actionType=actionType;
        this.position=position;
        this.text=text;
    }
    public Action(String actionType,String oldText,String newText,int position){
        this.actionType=actionType;
        this.oldText=oldText;
        this.text=newText;
        this.position=position;
    }


    public String getActionType(){return actionType;}
    public void setActionType(String actionType){this.actionType=actionType;}
    public String getOldText(){return oldText;}
    public void setOldText(String text) {
        this.oldText = text;
    }
    public int getPosition(){return position;}
    public void setPosition(int position){this.position=position;}
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

}
