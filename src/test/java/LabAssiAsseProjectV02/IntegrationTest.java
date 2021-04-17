package LabAssiAsseProjectV02;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;

public class IntegrationTest {
    public IntegrationTest() {
    }

    private final StudentValidator studentValidator = new StudentValidator();
    private final TemaValidator temaValidator = new TemaValidator();
    private String filenameStudent = "fisiere/Studenti1.xml";
    private String filenameTema = "fisiere/Teme.xml";
    private String filenameTema1 = "fisiere/Teme1.xml";
    private String filenameNota1 = "fisiere/Note1.xml";
    private String filenameNota = "fisiere/Note.xml";
    private StudentXMLRepo studentXMLRepo = new StudentXMLRepo(filenameStudent);;
    private NotaXMLRepo notaXMLRepo = new NotaXMLRepo(filenameNota1);;
    private TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filenameTema1);;
    private NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
    private Service service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
    @Test
    public void tc_1_addAssignment() {

        service.deleteTema("2");
        Tema t = service.addTema(new Tema("2", "d1", 14, 1));
        assert(t.getDeadline() == 14);

    }
    @Test
    public void tc_1_addStudent() {

        service.deleteStudent("1");
        Student s = service.addStudent("1", "name", 934, "mail");
        assert(s.getNume().equals("name"));
    }
    @Test
    public void tc_1_addGrade() {

        service.deleteNota("1");
        Nota g = new Nota("1", "1", "2", 10.0, LocalDate.now());
        try {
            double finalGrade = service.addNota(g, "feedback");
            assert(finalGrade == 10.0);
        }catch(Exception e){
            assert(e.getMessage().equals("Studentul nu exista!"));
        }
    }

    @Test
    public void tc_1_BigBangTest() {

        tc_1_addAssignment();
        tc_1_addStudent();
        tc_1_addGrade();

    }

    @Test
    public void tc_1_Incremental() {


        tc_1_addStudent();
        service.deleteStudent("1");

    }

    @Test
    public void tc_2_Incremental() {


        tc_1_addStudent();
        tc_1_addAssignment();
        service.deleteTema("2");
        service.deleteStudent("1");

    }

    @Test
    public void tc_3_Incremental() {

        tc_1_addStudent();
        tc_1_addAssignment();
        tc_1_addGrade();
        service.deleteTema("2");
        service.deleteStudent("1");
        service.deleteNota("1");

    }




}
