
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static java.util.Calendar.HOUR;

public class Fitness {

    private final int zonesPeopleLimit = 20;
    private final String stringInvalid = "действие абонемента закончилось, посетитель не допущен к занятиям";
    private final String stringZoneLimit = "не может войти, превышен максимальный лимит посетителей";
    private final String stringZoneError = "не может быть допущен до занятий из-за неподходящего типа абонемента";
    private PassTicket[] gymZone = new PassTicket[zonesPeopleLimit];
    private PassTicket[] poolZone = new PassTicket[zonesPeopleLimit];
    private PassTicket[] groupZone = new PassTicket[zonesPeopleLimit];
    Calendar fitnessCalendar = new GregorianCalendar();
    private SimpleDateFormat wholeDateFormat = new SimpleDateFormat("d MMMM yyyy, HH:mm");
    SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

    public Fitness() {
    }

    private void setTicketGymZone(PassTicket[] passTicket) {
        for (int i = 0; i < passTicket.length || i < gymZone.length; i++) {
            if (passTicket.length == i) {
                break;
            } else if (gymZone.length > i) {
                gymZone[i] = passTicket[i];
                System.out.println(wholeDateFormat.format(fitnessCalendar.getTime()) + ": " + passTicket[i].getClientFirstName()
                        + passTicket[i].getClientLastName() + "пришёл заниматься в тренажёрный зал;");
            } else {
                System.out.println(" Тренажёрный зал: " + passTicket[i].getClientFirstName() + passTicket[i].getClientLastName()
                        + stringZoneLimit);
            }
        }
    }

    private void setTicketPoolZone(PassTicket[] passTicket) {
        int exceptionTicketCounter = 0;

        for (int i = 0; i < passTicket.length || i < poolZone.length; i++) {
            if (passTicket.length == i) {
                break;
            } else if (passTicket[i].getTicketCategory().equals(PassTicketCategory.DAYTIME)) {
                System.out.println(" Бассейн: " + passTicket[i].getClientFirstName() + passTicket[i].getClientLastName()
                        + stringZoneError);
                exceptionTicketCounter++;
            } else if (poolZone.length > i - exceptionTicketCounter) {
                poolZone[i - exceptionTicketCounter] = passTicket[i];
                System.out.println(wholeDateFormat.format(fitnessCalendar.getTime()) + ": " + passTicket[i].getClientFirstName()
                        + passTicket[i].getClientLastName() + "пришёл заниматься в бассейн;");
            } else {
                System.out.println(" Бассейн: " + passTicket[i].getClientFirstName() + passTicket[i].getClientLastName()
                        + stringZoneLimit);
            }
        }
    }

    private void setTicketGroupZone(PassTicket[] passTicket) {
        int exceptionTicketCounter = 0;

        for (int i = 0; i < passTicket.length || i < groupZone.length; i++) {
            if (passTicket.length == i) {
                break;
            } else if (passTicket[i].getTicketCategory().equals(PassTicketCategory.ONETIME)) {
                System.out.println(" Групповые тренировки: " + passTicket[i].getClientFirstName() + passTicket[i].getClientLastName()
                        + stringZoneError);
                exceptionTicketCounter++;
            } else if (groupZone.length > i - exceptionTicketCounter) {
                groupZone[i - exceptionTicketCounter] = passTicket[i];
                System.out.println(wholeDateFormat.format(fitnessCalendar.getTime()) + ": " + passTicket[i].getClientFirstName()
                        + passTicket[i].getClientLastName() + "пришёл заниматься в зал групповых тренировок;");
            } else {
                System.out.println(" Групповые тренировки: " + passTicket[i].getClientFirstName() + passTicket[i].getClientLastName()
                        + stringZoneLimit);
            }
        }
    }

    protected void startFitnessWorkingDays(int fitnessWorkingDay, PassTicket[] gymTicket, PassTicket[] poolTicket, PassTicket[] groupTicket) {
        int fitnessWorkDays = 0;
        fitnessCalendar.set(Calendar.MINUTE, 59);
        fitnessCalendar.set(Calendar.HOUR, 8);

        for (int day = 0; day < fitnessWorkingDay; day++) {
            System.out.println("*** Открытие фитнес-клуба - 8:00 ***");
            setTicketGymZone(gymTicket);
            setTicketPoolZone(poolTicket);
            setTicketGroupZone(groupTicket);

            for (int hour = 9; hour <= 22; hour++) {
                for (int x = 0; x < gymZone.length; x++) {
                    if (gymZone[x] != null && gymZone[x].getValidityDays() <= fitnessWorkDays) {
                        System.out.println(gymZone[x].getClientFirstName() + gymZone[x].getClientLastName() + stringInvalid);
                        gymZone[x] = null;
                    } else if (gymZone[x] != null && gymZone[x].getTicketCategory().getPassTime() == hour) {
                        System.out.println(gymZone[x].getClientFirstName() + gymZone[x].getClientLastName() + "покинул тренажёрный зал в "
                                + hourFormat.format(fitnessCalendar.getTime()));
                        gymZone[x] = null;
                    }
                }

                for (int y = 0; y < poolZone.length; y++) {
                    if (poolZone[y] != null && poolZone[y].getValidityDays() <= fitnessWorkDays) {
                        System.out.println(poolZone[y].getClientFirstName() + poolZone[y].getClientLastName() + stringInvalid);
                        poolZone[y] = null;
                    } else if (poolZone[y] != null && poolZone[y].getTicketCategory().getPassTime() == hour) {
                        System.out.println(poolZone[y].getClientFirstName() + poolZone[y].getClientLastName() + "покинул бассейн в "
                                + hourFormat.format(fitnessCalendar.getTime()));
                        poolZone[y] = null;
                    }
                }

                for (int z = 0; z < groupZone.length; z++) {
                    if (groupZone[z] != null && groupZone[z].getValidityDays() <= fitnessWorkDays) {
                        System.out.println(groupZone[z].getClientFirstName() + groupZone[z].getClientLastName() + stringInvalid);
                        groupZone[z] = null;
                    } else if (groupZone[z] != null && groupZone[z].getTicketCategory().getPassTime() == hour) {
                        System.out.println(groupZone[z].getClientFirstName() + groupZone[z].getClientLastName() +
                                "покинул групповые тренировки в " + hourFormat.format(fitnessCalendar.getTime()));
                        groupZone[z] = null;
                    }
                }
                if (hour == 22) {
                    fitnessCalendar.add(Calendar.HOUR, +11);
                } else fitnessCalendar.add(HOUR, +1);
            }
            fitnessWorkDays++;
            System.out.println();
        }
    }

    protected void oneFitnessWorkingDay(PassTicket[] gymTicket, PassTicket[] poolTicket, PassTicket[] groupTicket) {
        fitnessZonesCurrentlyInfo();
        fitnessCalendar.set(Calendar.MINUTE, 59);
        fitnessCalendar.set(Calendar.HOUR, 8);
        System.out.println("*** Открытие фитнес-клуба - 8:00 ***");
        setTicketGymZone(gymTicket);
        setTicketPoolZone(poolTicket);
        setTicketGroupZone(groupTicket);
        fitnessZonesCurrentlyInfo();

        for (int hour = 9; hour <= 22; hour++) {
            for (int x = 0; x < gymZone.length; x++) {
                if (gymZone[x] != null && gymZone[x].getTicketCategory().getPassTime() == hour) {
                    System.out.println(gymZone[x].getClientFirstName() + gymZone[x].getClientLastName() + "покинул тренажёрный зал в "
                            + hourFormat.format(fitnessCalendar.getTime()));
                    gymZone[x] = null;
                }
            }

            for (int y = 0; y < poolZone.length; y++) {
                if (poolZone[y] != null && poolZone[y].getTicketCategory().getPassTime() == hour) {
                    System.out.println(poolZone[y].getClientFirstName() + poolZone[y].getClientLastName() + "покинул бассейн в "
                            + hourFormat.format(fitnessCalendar.getTime()));
                    poolZone[y] = null;
                }
            }

            for (int z = 0; z < groupZone.length; z++) {
                if (groupZone[z] != null && groupZone[z].getTicketCategory().getPassTime() == hour) {
                    System.out.println(groupZone[z].getClientFirstName() + groupZone[z].getClientLastName() +
                            "покинул групповые тренировки в " + hourFormat.format(fitnessCalendar.getTime()));
                    groupZone[z] = null;
                }
            }
            fitnessCalendar.add(HOUR, +1);
        }
        fitnessZonesCurrentlyInfo();
    }

    protected void fitnessZonesCurrentlyInfo() {
        System.out.println("\n          В тренажёрном зале находятся: ");
        for (PassTicket p : gymZone)
            if (p != null) System.out.print(p.getClientFirstName() + p.getClientLastName() + ";  ");
        System.out.println("\n          В бассейне находятся: ");
        for (PassTicket p : poolZone)
            if (p != null) System.out.print(p.getClientFirstName() + p.getClientLastName() + ";  ");
        System.out.println("\n          На групповых занятиях находятся: ");
        for (PassTicket p : groupZone)
            if (p != null) System.out.print(p.getClientFirstName() + p.getClientLastName() + ";  ");
        System.out.println("\n ");
    }
}
//Фитнес содержит информацию об абонементах, которые:
//
//зарегистрированы в тренажерном зале (массив абонементов);
//зарегистрированы в бассейне (массив абонементов);
//зарегистрированы на групповых занятиях (массив абонементов).
//В каждой зоне (бассейн, тренажерный зал, групповые занятия) одновременно может быть зарегистрировано 20 абонементов.
//
//Когда фитнес клуб закрывается, клиенты покидают его (заполнить массив null элементами).
//
//Когда клиент приходит в фитнес клуб, он сообщает желаемую зону и показывает абонемент. Поэтому перед добавлением в один
// из массивов, необходимо проверить можно ли по данному абонементу пройти в желаемую зону, если посетитель не может пройти, необходимо сообщить ему причину.
//
//Посетитель не может пройти:
//
//если абонемент просрочен,
//если он пытается пройти в зону, которая не разрешена по его абонементу,
//если в зоне нет свободных мест.
//Абонемент не может быть зарегистрирован одновременно в нескольких зонах (можно не реализовывать).
//
//Реализовать возможность вывода информации о посетителях: сначала посетителях тренажерного зала, потом бассейна, потом групповых занятий.
//
//Реализовать возможность выводить информацию в консоль каждый раз, когда абонемент регистрируется в одной из зон (добавляется в массив). Информация для вывода:
//
//Фамилия Имя Посещаемая зона (бассейн/тренажерный зал/групповые занятия)
//Дата и время посещения
