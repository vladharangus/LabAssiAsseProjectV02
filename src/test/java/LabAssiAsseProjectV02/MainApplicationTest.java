package LabAssiAsseProjectV02;


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

public class MainApplicationTest {
    private final StudentValidator studentValidator = new StudentValidator();
    private NotaValidator notaValidator;
    private final TemaValidator temaValidator = new TemaValidator();
    private String filenameStudent = "fisiere/Studenti1.xml";
    private String filenameTema = "fisiere/Teme.xml";
    private String filenameTema1 = "fisiere/Teme1.xml";
    private String filenameNota = "fisiere/Note.xml";
    private StudentXMLRepo studentXMLRepo;
    private NotaXMLRepo notaXMLRepo;
    private TemaXMLRepo temaXMLRepo;
    private Service service;

    public MainApplicationTest() {
    }

    @Test
    public void tc_1_addAssignmentValidDeadline() {
        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema1);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
        service.deleteTema("2");
        Tema t = service.addTema(new Tema("2", "d1", 5, 1));
        assert(t.getDeadline() == 5);
    }
    @Test
    public void tc_2_addAssignmentInvalidDeadline() {

        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema1);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);

        try{
            Tema t = service.addTema(new Tema("2", "d1", 15, 1));
        }catch (Exception e){
            assert(e.getMessage().equals("Deadlineul trebuie sa fie intre 1-14."));
        }
    }
    @Test
    public void tc_3_addAssignmentInvalidId() {

        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema1);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);

        try{
            Tema t = service.addTema(new Tema("", "d1", 5, 1));
        }catch (Exception e){
            assert(e.getMessage().equals("Numar tema invalid!"));
        }

    }
    @Test
    public void tc_4_addAssignmentInvalidDescription() {

        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema1);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);

        try{
            Tema t = service.addTema(new Tema("2", "", 5, 1));
        }catch (Exception e){
            assert(e.getMessage().equals("Descriere invalida!"));
        }

    }
    @Test
    public void tc_5_addAssignmentInvalidTurnIn() {

        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema1);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
        try{
            Tema t = service.addTema(new Tema("2", "d1", 5, 0));
        }catch (Exception e){
            assert(e.getMessage().equals("Saptamana primirii trebuie sa fie intre 1-14."));
        }

    }
    @Test
    public void tc_1_addStudentValidName() {
        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
        service.deleteStudent("1");
        Student s = service.addStudent("1", "name", 934, "mail");
        assert(s.getNume().equals("name"));
    }
    @Test
    public void tc_2_addStudentInvalidName() {

        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
        try {
            Student s = service.addStudent("2", null, 934, "mail");
        }catch (Exception e) {
            assert(e.getMessage().equals("Nume incorect!"));
        }
    }
    @Test
    public void tc_3_addStudentValidGroup() {
        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
        service.deleteStudent("3");
        Student s = service.addStudent("3", "name", 934, "mail");
        assert(s.getGrupa() == 934);
    }
    @Test
    public void tc_4_addStudentInvalidGroup() {

        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
        try {
            Student s = service.addStudent("4", "name", -3, "mail");
        }catch (Exception e) {
            assert(e.getMessage().equals("Grupa incorecta!"));
        }
    }
    @Test
    public void tc_5_addStudentValidEmail() {
        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
        service.deleteStudent("5");
        Student s = service.addStudent("5", "name", 934, "mail");
        assert(s.getEmail().equals("mail"));
    }
    @Test
    public void tc_6_addStudentInvalidEmail() {

        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        notaXMLRepo = new NotaXMLRepo(filenameNota);
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
        try {
            Student s = service.addStudent("6", "name", 934, null);
        }catch (Exception e) {
            assert(e.getMessage().equals("Email incorect!"));
        }
    }

}

