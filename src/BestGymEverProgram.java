import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class BestGymEverProgram {

    protected final Path INPUTFILE = Paths.get("src/customers.txt");
    protected final Path OUTPUTFILE = Paths.get("src/output.txt");

    protected List<Person> membershipList;

    public BestGymEverProgram(){
        membershipList = IOUtil.readDataFromFile(INPUTFILE);
    }

    public void programLoop() {

        while(true) {
            System.out.println("Sök efter medlem (Personnummer eller namn): ");
            String input = IOUtil.getInput();

            if(input.equalsIgnoreCase("exit"))
                break;

            Person member = GymLogic.findMember(input, membershipList);
            if(member != null && GymLogic.isMembershipActive(member, LocalDate.now())) {

                IOUtil.exportPerson(member,LocalDate.now(),OUTPUTFILE);
                System.out.println(IOUtil.printMemberToString(member,LocalDate.now()));

            }else
                System.out.println("Personen saknas i registret och är obehörig!\n");
        }
    }

    public static void main(String[] args) {
        BestGymEverProgram prog = new BestGymEverProgram();
        prog.programLoop();
    }
}
