import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;

public class FitnessClub {

    // class Fitness - ПО ЗАДАНИЮ метод регистрации абонемента должен принимать на вход один абонемент и желаемую зону (больше ничего),
    // далее этот абонемент проверяется на возможность посещения и добавляется в одну из зон (массив абонементов)

    private final int zonesPeopleLimit = 20;
    private final String validDaysEnd = "Посетитель не может войти, так как срок действия абонемента закончился";
    private final String zoneIsFull = "Посетитель не может войти, так как зал вмещает не более 20 человек";
    private final String timeIsEnd = "Посетитель не может войти, так как время для посещения истекло";
    private final String passTicketWrong = "Посетитель не может войти в желаемую зону с данным типом абонемента";
    private final String[] validityZone = {"gymZone", "poolZone", "groupZone"}; // Зоны доступные для посещения(массив строк или перечислений)
    private PassTicket[] gymZone = new PassTicket[zonesPeopleLimit];
    private PassTicket[] poolZone = new PassTicket[zonesPeopleLimit];
    private PassTicket[] groupZone = new PassTicket[zonesPeopleLimit];
    private Calendar calendar = new GregorianCalendar();
    private SimpleDateFormat wholeDateFormat = new SimpleDateFormat("d MMMM yyyy, HH:mm");

    public FitnessClub(PassTicket passTicket, String trainingZone) {
        if (isValidityZone(trainingZone).equals("gymZone")) {
            setVisitorInGymZone(passTicket);
        } else if (isValidityZone(trainingZone).equals("poolZone")) {
            setVisitorInPoolZone(passTicket);
        } else if (isValidityZone(trainingZone).equals("groupZone")) {
            setVisitorInGroupZone(passTicket);
        }
        fitnessZonesCurrentlyInfo();
        fitnessWorkdayEnd();
    }

    private String isValidityZone(String trainingZone) {
        for (String s : validityZone) {
            if (s.equals(trainingZone)) return trainingZone;
        }
        throw new IllegalArgumentException("Аргументом для <trainingZone> могут быть только: \"gymZone\", \"poolZone\", \"groupZone\"");
    }

    private void setVisitorInGymZone(PassTicket passTicket) {
        if (passTicket.getPassTicketEndDate().get(DAY_OF_YEAR) < calendar.get(DAY_OF_YEAR)) {
            System.out.println(validDaysEnd);
            return;
        }
        if (calendar.get(HOUR_OF_DAY) > 8) {
            if ((passTicket.getPassTicketCategory().equals("single") || passTicket.getPassTicketCategory().equals("unlimited") && calendar.get(HOUR_OF_DAY) < 22) ||
                    (passTicket.getPassTicketCategory().equals("daytime") && calendar.get(HOUR_OF_DAY) < 16)) {
                for (int i = 0; i < gymZone.length; i++) {
                    if (gymZone[i] == null) {
                        gymZone[i] = passTicket;
                        System.out.println(passTicket.getVisitor().getVisitorFirstName() + passTicket.getVisitor().getVisitorLastName() +
                                "проходит в тренажёрный зал " + wholeDateFormat.format(calendar.getTime()));
                        break;
                    } else {
                        System.out.println(zoneIsFull);
                    }
                }
            } else System.out.println(timeIsEnd);
        }
    }

    private void setVisitorInPoolZone(PassTicket passTicket) {
        if (passTicket.getPassTicketEndDate().get(DAY_OF_YEAR) < calendar.get(DAY_OF_YEAR)) {
            System.out.println(validDaysEnd);
            return;
        }
        if (passTicket.getPassTicketCategory().equals("daytime")) {
            System.out.println(passTicketWrong);
        } else if (passTicket.getPassTicketCategory().equals("single") || passTicket.getPassTicketCategory().equals("unlimited") &&
                calendar.get(HOUR_OF_DAY) > 8 && calendar.get(HOUR_OF_DAY) < 22) {
            for (int i = 0; i < poolZone.length; i++) {
                if (poolZone[i] == null) {
                    poolZone[i] = passTicket;
                    System.out.println(passTicket.getVisitor().getVisitorFirstName() + passTicket.getVisitor().getVisitorLastName() +
                            "проходит в бассейн " + wholeDateFormat.format(calendar.getTime()));
                    break;
                } else {
                    System.out.println(zoneIsFull);
                }
            }
        } else System.out.println(timeIsEnd);
    }

    private void setVisitorInGroupZone(PassTicket passTicket) {
        if (passTicket.getPassTicketEndDate().get(DAY_OF_YEAR) < calendar.get(DAY_OF_YEAR)) {
            System.out.println(validDaysEnd);
            return;
        }
        if (passTicket.getPassTicketCategory().equals("single")) {
            System.out.println(passTicketWrong);
        } else if (calendar.get(HOUR_OF_DAY) > 8) {
            if ((passTicket.getPassTicketCategory().equals("daytime") && calendar.get(HOUR_OF_DAY) < 16) ||
                    passTicket.getPassTicketCategory().equals("unlimited") && calendar.get(HOUR_OF_DAY) < 22) {
                for (int i = 0; i < groupZone.length; i++) {
                    if (groupZone[i] == null) {
                        groupZone[i] = passTicket;
                        System.out.println(passTicket.getVisitor().getVisitorFirstName() + passTicket.getVisitor().getVisitorLastName() +
                                "проходит в зону групповых тренировок " + wholeDateFormat.format(calendar.getTime()));
                        break;
                    } else {
                        System.out.println(zoneIsFull);
                    }
                }
            } else System.out.println(timeIsEnd);
        }
    }

    protected void fitnessZonesCurrentlyInfo() {
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
        System.out.println("\n" + wholeDateFormat.format(calendar.getTime()) + " *Фитнес клуб закончил работу, все посетители ушли*");
        for (int i = 0; i < zonesPeopleLimit; i++) {
            gymZone[i] = null;
            poolZone[i] = null;
            groupZone[i] = null;
        }
    }

}

