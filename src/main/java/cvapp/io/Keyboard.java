package cvapp.io;

import cvapp.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Keyboard {
    private static final Scanner scanner = new Scanner(System.in);

    public CV readCVFromConsole() {

        CV cv = new CV();

        cv.setFirstName(promptForStringInput("First name: "));
        cv.setLastName(promptForStringInput("Last name: "));

        Contact contact = readContactFromConsole();
        cv.setContact(contact);
        List<Education> educationList = readEducationFromConsole();
        cv.setEducation(educationList);
        List<WorkExperience> workExperienceList = readWorkExperiencesFromConsole();
        cv.setWorkExperience(workExperienceList);
        List<String> skills = readSkillsFromConsole();
        cv.setSkills(skills);

        cv.setGithubLink(promptReadAndValidate("GitHub link: ", CV::isValidLink, "Invalid link. Try again."));

        return cv;
    }

    private static Contact readContactFromConsole() {

        Contact contact = new Contact();
        Address address = readAddressFromConsole();

        String phoneNumber = promptReadAndValidate("Phone number: ",
                Contact::isValidNumber, "Invalid number format. Try again.");
        contact.setPhoneNumber(phoneNumber);

        String emailAddress = promptReadAndValidate("Email: ",
                Contact::isValidEmailAddress, "Invalid email format. Try again");
        contact.setEmailAddress(emailAddress);

        contact.setAddress(address);

        return contact;
    }

    private static Address readAddressFromConsole() {

        Address address = new Address();

        address.setStreet(promptForStringInput("Street: "));
        address.setStreetNumber(promptReadAndValidate("Street number: ",
                Address::isStreetNumberValid, "Invalid input. Try again."));

        return address;
    }

    private static List<Education> readEducationFromConsole() {

        List<Education> educations = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);

        while (true) {

            String response = promptForStringInput("Do you want to enter education? (yes/no): ");

            if (response.equals("no")) {
                break;
            }

            Education education = new Education();
            education.setCollegeName(promptForStringInput("College name: "));
            education.setStartDate(promptReadAndValidate("Start date: ",
                    Education::isValidStartDate, "Invalid year format"));

            System.out.println("If there is no end date, please type - present.");

            education.setEndDate(promptReadAndValidate("End date: ",
                    Education::isValidEndDate, "Invalid year format"));

            String responseDegree = promptForStringInput("Do you have a degree? (yes/no): ");

            if (!responseDegree.equals("yes")) {
                break;
            }
            education.setDegree(promptForStringInput("Degree"));

            educations.add(education);
        }

        return educations;
    }

    private static List<WorkExperience> readWorkExperiencesFromConsole() {
        List<WorkExperience> workExperiences = new ArrayList<>();

        while (true) {

            String response = promptForStringInput("Do you want to enter work experience? (yes/no): ");

            if (!response.equals("yes")) {
                break;
            }

            WorkExperience workExperience = new WorkExperience();
            workExperience.setCompanyName(promptForStringInput("Company name: "));
            workExperience.setJobTitle(promptForStringInput("Job title"));
            workExperience.setStartDate(promptReadAndValidate("Start date: ",
                    WorkExperience::isValidStartDate, "Invalid year format"));

            System.out.println("If there is no end date, please type - present.");
            workExperience.setEndDate(promptReadAndValidate("End date: ",
                    WorkExperience::isValidEndDate, "Invalid year format"));
            workExperience.setJobDescription(promptForStringInput("Job description"));

            workExperiences.add(workExperience);
        }

        return workExperiences;

    }

    private static List<String> readSkillsFromConsole() {
        List<String> skills = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);

        while (true) {

            String response = promptForStringInput("Do you want to enter a skill? (yes/no): ");
            if (!response.equals("yes")) {
                break;
            }

            String skill = promptForStringInput("Enter a skill: ");

            skills.add(skill);
        }

        return skills;
    }

    private static String promptReadAndValidate(String promptMessage, Predicate<String> validator, String errorMessage) {
        String inputValue = promptForStringInput(promptMessage);
        boolean isValid = validator.test(inputValue);

        if (!isValid) {
            System.out.println(errorMessage);
            return promptReadAndValidate(promptMessage, validator, errorMessage);
        } else {
            return inputValue;
        }

    }

    private static String promptForStringInput(String message) {
        System.out.println(message);
        return scanner.nextLine().toLowerCase();
    }

}
