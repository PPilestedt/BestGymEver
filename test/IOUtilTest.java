import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class IOUtilTest {

    Path inputSrcTest = Paths.get("src/customersTest.txt");
    Path outputSrcTest = Paths.get("src/outputTest.txt");

    @Test
    public void extractPersonalIdentityNumberFromStringTest(){
        String line = "7603021234, Alhambra Aromes";

        String PIN = IOUtil.extractPersonalIdentityNumber(line);

        assertEquals(PIN,"7603021234");
        assertNotEquals(PIN,"1234567891");
    }

    @Test
    public void extractNameFromStringTest(){
        String line = "7603021234, Alhambra Aromes";

        String name = IOUtil.extractName(line);

        assertEquals(name,"Alhambra Aromes");
        assertNotEquals(name,"Janne Josefsson");
    }

    @Test
    public void extractDateFromStringTest(){
        String line = "2019-07-01";
        LocalDate dateInString = LocalDate.of(2019,7,1);
        LocalDate dateNotInString = LocalDate.of(1998,1,1);
        LocalDate date = IOUtil.extractDate(line);

        assertEquals(date,dateInString);
        assertNotEquals(date,dateNotInString);
    }

    @Test
    public void dataFromFileToListTest(){

        List<Person> personList = IOUtil.readDataFromFile(inputSrcTest);

        LocalDate date1 = LocalDate.of(1999,12,16);
        LocalDate date2 = LocalDate.of(2020,3,23);

        assertEquals(personList.get(0).getPersonalIdentityNumber(), "7911061234");
        assertEquals(personList.get(0).getName(), "Fritjoff Flacon");
        assertEquals(personList.get(0).getSubscriptionPaymentDate(), date1);

        assertEquals(personList.get(1).getPersonalIdentityNumber(), "7512166544");
        assertEquals(personList.get(1).getName(), "Greger Ganache");
        assertEquals(personList.get(1).getSubscriptionPaymentDate(), date2);
    }

    @Test
    public void exportPersonTest(){

        try {
            Files.deleteIfExists(outputSrcTest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Person person = new Person("1234567891","Janne Josefsson",LocalDate.parse("2020-03-23"));
        IOUtil.exportPerson(person,LocalDate.parse("2020-05-05"),outputSrcTest);

        int count = 0;
        try (Scanner scan = new Scanner(outputSrcTest)){
            while(scan.hasNextLine()) {
                scan.nextLine();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(count,1);
        assertNotEquals(count,0);
    }

    @Test
    public void inputTest(){

        Scanner scanner = new Scanner(System.in);
        IOUtil.isTesting = true;
        String output = IOUtil.getInput();

        assertEquals(output,"Greger Ganache");

    }

}
