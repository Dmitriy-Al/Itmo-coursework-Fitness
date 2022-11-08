import java.util.Calendar;
import java.util.GregorianCalendar;

public class PassTicket {

    private int clientBirthYear;
    private int validityDays;
    private String clientFirstName;
    private String clientLastName;
    private PassTicketCategory passTicketCategory;

    private final Calendar calendar = new GregorianCalendar();

    public PassTicket(PassTicketCategory category, int validityDays, int clientBirthYear, String clientFirstName, String clientLastName) {

        if (validityDays <= 0 || clientBirthYear < calendar.get(Calendar.YEAR) - 100 || clientBirthYear > calendar.get(Calendar.YEAR) - 10 ||
                clientFirstName.length() < 2 || clientLastName.length() < 2) {
            throw new IllegalArgumentException("Данные введены некорректно <Information inserted incorrect>");
        } else if (!category.equals(PassTicketCategory.ONETIME)) {
            this.validityDays = validityDays;
            this.clientBirthYear = clientBirthYear;
            this.clientFirstName = clientFirstName;
            this.clientLastName = clientLastName;
            passTicketCategory = category;
        } else {
            this.validityDays = 1;
            this.clientBirthYear = clientBirthYear;
            this.clientFirstName = clientFirstName;
            this.clientLastName = clientLastName;
            passTicketCategory = category;
        }
    }

    protected PassTicketCategory getTicketCategory() {
        return passTicketCategory;
    }

    protected String getClientFirstName() {
        return clientFirstName + " ";
    }

    protected String getClientLastName() {
        return clientLastName + " ";
    }

    protected int getValidityDays() {
        return validityDays;
    }

}
//В фитнес клубе есть три типа абонементов:
//
//Разовый (на один день). По разовому абонементу клиенты могут посещать бассейн и тренажерный зал с 8 до 22 часов.
//Дневной (срок действия может быть любым). По данному абонементу клиенты могут посещать тренажерный зал и групповые занятия (но не бассейн) с 8 до 16 часов.
//Полный (срок действия может быть любым). По данному абонементу клиенты могут посещать тренажерный зал, бассейн и групповые занятия с 8 до 22 часов.
//Каждый абонемент хранит дату регистрации (текущая дата) и дату окончания регистрации. Каждый абонемент хранит информацию о владельце. Данные о владельце: имя, фамилия, год рождения.