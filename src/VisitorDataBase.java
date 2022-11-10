import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.YEAR;

public class VisitorDataBase {

    // данные о владельце д.б. вынесены в отдельный класс
    private int visitorBirthYear;
    private String visitorFirstName;
    private String visitorLastName;
    private Calendar date = new GregorianCalendar();

    public VisitorDataBase(String visitorFirstName, String visitorLastName, int visitorBirthYear) {
        if ((visitorFirstName.length() < 2 || visitorLastName.length() < 2) ||
                (visitorBirthYear < date.get(YEAR) - 100 || visitorBirthYear > date.get(YEAR) - 10)) {
            throw new IllegalArgumentException("Имя и фамилия посетителя не могут быть короче двух символов, возраст не может быть более 100 лет и менее 10 лет");
        } else {
            this.visitorFirstName = visitorFirstName;
            this.visitorLastName = visitorLastName;
            this.visitorBirthYear = visitorBirthYear;
        }
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
