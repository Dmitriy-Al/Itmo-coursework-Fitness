import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;

public class FitnessClub {

    // class Fitness - ПО ЗАДАНИЮ метод регистрации абонемента должен принимать на вход один абонемент и желаемую зону (больше ничего),
    // далее этот абонемент проверяется на возможность посещения и добавляется в одну из зон (массив абонементов)

    private PassTicket[] gymZone = new PassTicket[FitnessZone.GYMZONE.zonePeopleLimit];
    private PassTicket[] poolZone = new PassTicket[FitnessZone.POOLZONE.zonePeopleLimit];
    private PassTicket[] groupZone = new PassTicket[FitnessZone.GROUPZONE.zonePeopleLimit];
    private final Calendar calendar = new GregorianCalendar();
    private final  SimpleDateFormat wholeDateFormat = new SimpleDateFormat("d MMMM yyyy, HH:mm");

    public FitnessClub(PassTicket passTicket, FitnessZone fitnessZone) {
        showZonesCurrentlyInfo();
        System.out.println("<Фитнес клуб работает>");
        checkVisitor(passTicket, fitnessZone);
        showZonesCurrentlyInfo();
        fitnessWorkdayEnd();
    }

    private void checkVisitor(PassTicket passTicket, FitnessZone fitnessZone) {
        if (passTicket.getPassTicketEndDate().get(DAY_OF_YEAR) < calendar.get(DAY_OF_YEAR)) { // Проверки времени оставил всё же ресепшену фитнеса
            System.out.println(SystemMessage.VALID_DAYS_END);
            return;
        }
        if (passTicket.getPassTicketCategory().getGoOutTime() <= calendar.get(HOUR_OF_DAY) || calendar.get(HOUR_OF_DAY) < 8) {
            System.out.println(SystemMessage.TIME_IS_END);
            return;
        }
        if (fitnessZone == FitnessZone.GYMZONE) {
            addToZone(passTicket, fitnessZone, gymZone);
        } else if (fitnessZone == FitnessZone.POOLZONE && passTicket.getPassTicketCategory() != PassTicket.PassTicketCategory.DAYTIME) {
            addToZone(passTicket, fitnessZone, poolZone);
        } else if (fitnessZone == FitnessZone.GROUPZONE && passTicket.getPassTicketCategory() != PassTicket.PassTicketCategory.SINGLE) {
            addToZone(passTicket, fitnessZone, groupZone);
        } else System.out.println(SystemMessage.PASS_TICKET_WRONG);
    }

    private void addToZone(PassTicket passTicket, FitnessZone fitnessZone, PassTicket[] zone) {
        for (int i = 0; i < zone.length; i++) {
            if (zone[i] == null) {
                zone[i] = passTicket;
                System.out.println(passTicket.getVisitor().getVisitorFirstName() + passTicket.getVisitor().getVisitorLastName() +
                        "проходит в " +  fitnessZone.getZoneName() + " " + wholeDateFormat.format(calendar.getTime()));
                break;
            } else System.out.println(SystemMessage.ZONE_IS_FULL);
        }
    }

    protected void showZonesCurrentlyInfo() {
        System.out.println("\n  В тренажёрном зале находятся: ");
        for (PassTicket p : gymZone)
            if (p != null)
                System.out.print(p.getVisitor().getVisitorFirstName() + p.getVisitor().getVisitorLastName() + ";  ");
        System.out.println("\n  В бассейне находятся: ");
        for (PassTicket p : poolZone)
            if (p != null)
                System.out.print(p.getVisitor().getVisitorFirstName() + p.getVisitor().getVisitorLastName() + ";  ");
        System.out.println("\n  На групповых занятиях находятся: ");
        for (PassTicket p : groupZone)
            if (p != null)
                System.out.print(p.getVisitor().getVisitorFirstName() + p.getVisitor().getVisitorLastName() + ";  ");
        System.out.println("\n ");
    }

    private void fitnessWorkdayEnd() {
        calendar.set(HOUR_OF_DAY, 22);
        calendar.set(MINUTE, 0);
        for (int i = 0; i < 20; i++) {
            gymZone[i] = null;
            poolZone[i] = null;
            groupZone[i] = null;
        }
        System.out.println("\n" + wholeDateFormat.format(calendar.getTime()) + " <Фитнес клуб закончил работу, все посетители ушли>");
    }

    public enum FitnessZone {

        GYMZONE(20, "тренажёрный зал"),
        POOLZONE(20, "бассейн"),
        GROUPZONE(20, "зал групповых занятий");

        private int zonePeopleLimit;
        private final String zoneName;

        FitnessZone(int zonesPeopleLimit, String zoneName) {
            this.zonePeopleLimit = zonesPeopleLimit;
            this.zoneName = zoneName;
        }

        String getZoneName(){
            return zoneName;
        }
    }

}
