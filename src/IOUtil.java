import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IOUtil {

    public static boolean isTesting = false;

    public static List<Person> readDataFromFile(Path inputSrc) {
        List<Person> personList = new ArrayList<>();

        try(Scanner scanner = new Scanner(inputSrc)){

            while(scanner.hasNextLine()){
                String line1 = "";
                String line2 = "";

                if(scanner.hasNextLine())
                    line1 = scanner.nextLine();
                if(scanner.hasNextLine())
                    line2 = scanner.nextLine();

                if(!line1.equals("") || !line2.equals("")) {
                    Person person = new Person(extractPersonalIdentityNumber(line1),
                            extractName(line1),
                            extractDate(line2));
                    personList.add(person);
                }
            }

        }catch (FileNotFoundException e){
            System.out.println("Filen kan ej hittas");
            System.out.println(inputSrc);
            e.printStackTrace();
            System.exit(0);
        }catch(IOException e){
            System.out.println("Det gick inte att läsa från fil");
            System.out.println(inputSrc);
            e.printStackTrace();
            System.exit(0);
        }catch(Exception e){
            System.out.println("Något gick fel");
            System.out.println(inputSrc);
            e.printStackTrace();
            System.exit(0);
        }

        return personList;
    }

    public static String extractPersonalIdentityNumber(String line) {
        String[] splitLine = line.split(",");
        return splitLine[0].trim();
    }

    public static String extractName(String line) {
        String[] splitLine = line.split(",");
        return splitLine[1].trim();
    }

    public static LocalDate extractDate(String line) {
        return LocalDate.parse(line);
    }

    public static void exportPerson(Person person, LocalDate dateNow, Path outputSrc) {
        if(!Files.exists(outputSrc)) {
            try {
                Files.createFile(outputSrc);
            } catch (IOException e) {
                System.out.println("Kunde inte skapa outputfilen");
                e.printStackTrace();
            }
        }

        try(BufferedWriter bw = Files.newBufferedWriter(outputSrc, StandardOpenOption.APPEND)){

            bw.append(dateNow.toString());
            bw.append(",");
            bw.append(person.getPersonalIdentityNumber());
            bw.append(",");
            bw.append(person.getName());
            bw.newLine();
            bw.flush();

        }catch(IOException e){
            System.out.println("Kunde inte skriva till " + outputSrc);
            e.printStackTrace();
        }
    }

    public static String getInput() {

        Scanner scanner = new Scanner(System.in);
        String input;

        if(!isTesting) {

            try{
                input = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Du har valt att avsluta programmet");
                return "exit";
            }

        }else
            input = "Greger Ganache";

        return input;
    }

    public static String printMemberToString(Person member, LocalDate dateNow) {
        String memberInfo = "";

        memberInfo += "---Medlemsinfo---\n";
        memberInfo += member.toString();
        memberInfo += "\n";
        memberInfo += "Aktivt medlemskap: ";
        memberInfo += GymLogic.isMembershipActive(member, dateNow) ? "ja\n" : "nej\n";

        return memberInfo;
    }
}
