package homeOffice;

public enum EnumCommands{
    
    PRESS_MOUSE(-1),
    RELEASE_MOUSE(-2),
    PRESS_KEY(-3),
    RELEASE_KEY(-4),
    MOVE_MOUSE(-5);
//    MOUSE_CLICK(-6),
//    TYPE_KEY(-7),
//    MOUSE_DOUBLE_CLICK(-8);
    
    private int abbrev;
    
    EnumCommands(int abbrev){
        this.abbrev = abbrev;
    }
    
    public int getAbbrev(){
        return abbrev;
    }
}