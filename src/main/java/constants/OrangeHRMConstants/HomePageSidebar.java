package constants.OrangeHRMConstants;

public enum HomePageSidebar {
    PIM("PIM"),
    ADMIN("Admin"),
    LEAVE(""),
    TIME(""),
    MYINFO("My Info");

    public final String name;
    private HomePageSidebar(String name){
        this.name= name;
    }
}
