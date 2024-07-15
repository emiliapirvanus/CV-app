package cvapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cvapp.io.Console;
import cvapp.io.Keyboard;
import cvapp.model.CV;
import cvapp.repository.IDB;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private final IDB db;
    private final ObjectMapper objectMapper;
    private final Keyboard keyboard = new Keyboard();
    private final Console console = new Console();

    private final Scanner scanner = new Scanner(System.in);

    public App(IDB db) {
        this.db = db;
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void run() throws IOException {

        console.displayOptions();
        int input = scanner.nextInt();
        scanner.nextLine();

        while (true) {

            switch (input) {
                case 1 -> readCV();
                case 2 -> displayCvFilteredByLastName();
                case 3 -> displayAllCVs();
                case 4 -> deleteCV();
                case 5 -> displayCvFilteredByGraduationYear();

            }

            console.displayOptions();
            input = scanner.nextInt();
            scanner.nextLine();
        }

    }

    private void readCV() throws IOException {
        CV cv = keyboard.readCVFromConsole();
        db.add(cv);
    }

    private void displayAllCVs() throws JsonProcessingException {
        for (CV cv : db.loadAllCVs()) {
            System.out.println(objectMapper.writeValueAsString(cv));
        }
    }

    private void deleteCV() throws IOException {
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine().toLowerCase();
        Optional<CV> cvByLastName = db.findCVByLastName(lastName);
        if (cvByLastName.isPresent()) {
            db.delete(cvByLastName.get());
            System.out.println("Deleted");
        } else {
            System.out.println("CV not found");
        }
    }

    private void displayCvFilteredByLastName() throws JsonProcessingException {

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine().toLowerCase();
        Optional<CV> cv = db.findCVByLastName(lastName);
        if (cv.isEmpty()) {
            System.out.println("CV not found");
        } else {
            System.out.println(objectMapper.writeValueAsString(cv.get()));
        }

    }

    private void displayCvFilteredByGraduationYear() throws JsonProcessingException {
        System.out.println("Enter education end date: ");
        String endDate = scanner.nextLine().toLowerCase();
        List<CV> listCvsByGraduationYear = db.findCVsByEducationEndDate(endDate);
        if (listCvsByGraduationYear.isEmpty()) {
            System.out.println("CV not found");
        } else {
                System.out.println(objectMapper.writeValueAsString(listCvsByGraduationYear));
        }

    }

}
