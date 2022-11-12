import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_YEAR;

public class PassTicket {
    // Все доступности описать тут
    private int validityDays;
    private Calendar passTicketDate;
    private Calendar passTicketEndDate;
    private VisitorDataBase visitor;
    private PassTicketCategory passTicketCategory;

    public PassTicket(VisitorDataBase visitor, PassTicketCategory passTicketCategory, int validityDays) {
        passTicketDate = new GregorianCalendar();

        if (validityDays < 1 || validityDays > 1000) {
            throw new IllegalArgumentException("Срок действия абонемента не может быть менее 1 дня и более 1000 дней");
        } else {
            this.passTicketCategory = passTicketCategory;
            this.validityDays = validityDays;
            if (passTicketCategory != PassTicketCategory.SINGLE) validityDays = 1;
            passTicketEndDate = new GregorianCalendar();
            passTicketEndDate.add(DAY_OF_YEAR, +validityDays);
            this.visitor = visitor;
        }
    }

    protected VisitorDataBase getVisitor() {
        return visitor;
    }

    protected Calendar getPassTicketEndDate() {
        return passTicketEndDate;
    }

    protected PassTicketCategory getPassTicketCategory() {
        return passTicketCategory;
    }

    public enum PassTicketCategory {

        SINGLE(22),
        DAYTIME(16),
        UNLIMITED(22);

        private int goOutTime;

        PassTicketCategory(int goOutTime) {
            this.goOutTime = goOutTime;
        }

        int getGoOutTime() {
            return goOutTime;
        }
    }

}

//В фитнес клубе есть три типа абонементов:
//
//Разовый (на один день). По разовому абонементу клиенты могут посещать бассейн и тренажерный зал с 8 до 22 часов.
//Дневной (срок действия может быть любым). По данному абонементу клиенты могут посещать тренажерный зал и групповые занятия (но не бассейн) с 8 до 16 часов.
//Полный (срок действия может быть любым). По данному абонементу клиенты могут посещать тренажерный зал, бассейн и групповые занятия с 8 до 22 часов.
//Каждый абонемент хранит дату регистрации (текущая дата) и дату окончания регистрации. Каждый абонемент хранит информацию о владельце. Данные о владельце: имя, фамилия, год рождения.
