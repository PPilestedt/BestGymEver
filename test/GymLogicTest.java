import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class GymLogicTest {

    Path inputSrcTest = Paths.get("src/customersTest.txt");

    @Test
    public void isCustomerActiveMember(){

        List<Person> personList = IOUtil.readDataFromFile(inputSrcTest);
        LocalDate fakeCurrentDate = LocalDate.parse("2021-03-23");

        //Över ett år gammalt medlemskap
        assertFalse(GymLogic.isMembershipActive(personList.get(0),fakeCurrentDate));

        //medlemskap 2020-03-23, samma dag ett år tidigare
        assertTrue(GymLogic.isMembershipActive(personList.get(1),fakeCurrentDate));

        //medlemskap 2020-03-23, test med större marginal
        fakeCurrentDate = LocalDate.parse("2021-01-01");
        assertTrue(GymLogic.isMembershipActive(personList.get(1),fakeCurrentDate));
    }

    @Test
    public void findPersonInListWithNameOrPIN(){

        List<Person> personList = IOUtil.readDataFromFile(inputSrcTest);

        String input = "Fritjoff Flacon";
        Person person = GymLogic.findMember(input, personList);
        assertEquals(person, personList.get(0));

        input = "7512166544";
        person = GymLogic.findMember(input, personList);
        assertEquals(person, personList.get(1));

        input = "fiskbulle";
        person = GymLogic.findMember(input,personList);
        assertNull(person);
    }

    @Test
    public void memberToStringTest(){

        LocalDate fakeDate = LocalDate.parse("2020-03-23");
        Person person = new Person("7512166544","Greger Ganache", fakeDate);

        String memberInfo = GymLogic.printMemberToString(person,fakeDate);

        String info = "---Medlemsinfo---\n";
        info += "Namn: Greger Ganache \nPersonnummer: 7512166544 \nBetalade årsavgift: 2020-03-23";
        info += "\n";
        info += "Aktivt medlemskap: ja\n";

        assertEquals(memberInfo,info);

    }

}
