import java.time.LocalDate;
import java.util.List;

public class GymLogic {

    public static Person findMember(String input, List<Person> personList){
        for(Person p : personList){
            if(p.getName().equalsIgnoreCase(input) || p.getPersonalIdentityNumber().equalsIgnoreCase(input))
                return p;
        }
        return null;
    }

    public static boolean isMembershipActive(Person person, LocalDate currentDate) {
        LocalDate subscriptionDate = person.getSubscriptionPaymentDate();
        return subscriptionDate.isAfter(currentDate.minusYears(1)) ||
                subscriptionDate.isEqual(currentDate.minusYears(1));
    }

    public static String printMemberToString(Person member, LocalDate dateNow) {
        String memberInfo = "";

        memberInfo += "---Medlemsinfo---\n";
        memberInfo += member.toString();
        memberInfo += "\n";
        memberInfo += "Aktivt medlemskap: ";
        memberInfo += isMembershipActive(member, dateNow) ? "ja\n" : "nej\n";

        return memberInfo;
    }
}
