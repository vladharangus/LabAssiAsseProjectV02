package main.java.app;






public class MainApplication {

    public static void main(String[] args) {
        main.java.validation.StudentValidator studentValidator = new main.java.validation.StudentValidator();
        main.java.validation.TemaValidator temaValidator = new main.java.validation.TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

       //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
        //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
        //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
        //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);

        main.java.repository.StudentXMLRepo studentXMLRepository = new main.java.repository.StudentXMLRepo(filenameStudent);
        main.java.repository.TemaXMLRepo temaXMLRepository = new main.java.repository.TemaXMLRepo(filenameTema);
        main.java.validation.NotaValidator notaValidator = new main.java.validation.NotaValidator(studentXMLRepository, temaXMLRepository);
        main.java.repository.NotaXMLRepo notaXMLRepository = new main.java.repository.NotaXMLRepo(filenameNota);
        main.java.service.Service service = new main.java.service.Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        main.java.view.UI ui = new main.java.view.UI(service);
        ui.run();
    }

}
