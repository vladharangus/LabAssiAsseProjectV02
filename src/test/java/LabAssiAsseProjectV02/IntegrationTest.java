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
    private NotaValidator notaValidator;
    private final TemaValidator temaValidator = new TemaValidator();
    private String filenameStudent = "fisiere/Studenti1.xml";
    private String filenameTema = "fisiere/Teme.xml";
    private String filenameTema1 = "fisiere/Teme1.xml";
    private String filenameNota1 = "fisiere/Note1.xml";
    private String filenameNota = "fisiere/Note.xml";
    private StudentXMLRepo studentXMLRepo;
    private NotaXMLRepo notaXMLRepo;
    private TemaXMLRepo temaXMLRepo;
    private Service service;
    @Test
    public void tc_1_addAssignmentValidDeadline() {

        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema1);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);

        Tema t = service.addTema(new Tema("2", "d1", 14, 1));
        assert(t.getDeadline() == 5);
        //service.deleteTema("2");
    }
    @Test
    public void tc_1_addStudentValidName() {

        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);

        Student s = service.addStudent("1", "name", 934, "mail");
        assert(s.getNume().equals("name"));
       // service.deleteStudent("1");
    }
    @Test
    public void tc_1_addGrade() {

        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        notaXMLRepo = new NotaXMLRepo(filenameNota1);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);

        Nota g = new Nota("1", "1", "1", 10.0, LocalDate.now());
        double finalGrade = service.addNota(g, "feedback");
        assert(finalGrade == 10.0);
    }

    @Test
    public void tc_1_BigBangTest() {

        tc_1_addAssignmentValidDeadline();
        tc_1_addStudentValidName();
        tc_1_addGrade();

    }


}
