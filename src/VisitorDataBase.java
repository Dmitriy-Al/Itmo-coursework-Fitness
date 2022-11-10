public class VisitorDataBase {

    // данные о владельце д.б. вынесены в отдельный класс
    private int visitorBirthYear;
    private String visitorFirstName;
    private String visitorLastName;

    public VisitorDataBase(String visitorFirstName, String visitorLastName, int visitorBirthYear) {
        this.visitorFirstName = visitorFirstName;
        this.visitorLastName = visitorLastName;
        this.visitorBirthYear = visitorBirthYear;
    }

    protected String getVisitorFirstName() {
        return visitorFirstName + " ";
    }

    protected String getVisitorLastName() {
        return visitorLastName + " ";
    }

    protected int getVisitorBirthYear() {
        return visitorBirthYear;
    }

}
