package constants.OrangeHRMConstants;

public enum PIMSidebar {
    PERSONAL_DETAILS("Personal Details"),
    CONTACT_DETAILS("Contact Details");


    public final String name;
    private PIMSidebar(String name){
        this.name= name;
    }
}
