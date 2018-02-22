package service;

import hibernate.dao.DAOBase;
import hibernate.dao.impl.*;
import hibernate.entities.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Runner {
    private static Scanner scanner = new Scanner(System.in);
    private static ProjectDAOImpl projectDAO = new ProjectDAOImpl();
    private static CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    private static DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
    private static CompanyDAOImpl companyDAO = new CompanyDAOImpl();
    private static SkillDAOImpl skillDAO = new SkillDAOImpl();

    private Runner() {
    }

    private static void showMenu() {
        String line = "+-----------------------------+";
        StringJoiner sj = new StringJoiner("\n");
        sj.add(line);
        sj.add("|  Project Management System  |");
        sj.add(line);
        sj.add("| 1. Show all projects        |");
        sj.add("| 2. Show all developers      |");
        sj.add("| 3. Add new project          |");
        sj.add("| 4. Add new developer        |");
        sj.add("| 5. Add developer to project |");
        sj.add("| 6. Add new skill            |");
        sj.add("| 7. Delete project           |");
        sj.add("| 8. Delete developer         |");
        sj.add("| 0. Exit                     |");
        sj.add(line);
        sj.add("Your choice: ");
        System.out.print(sj.toString());
    }

    private static void selector(int choice) throws InterruptedException, IOException {
        if (choice == 0) {
            return;
        } else if (choice == 1) {
            showAllProjects();
        } else if (choice == 2) {
            showAllDevelopers();
        } else if (choice == 3) {
            addNewProject();
        } else if (choice == 4) {
            addNewDeveloper();
        } else if (choice == 5) {
            addDeveloperToProject();
        } else if (choice == 6) {
            addNewSkill();
        } else if (choice == 7) {
            deleteProject();
        } else if (choice == 8) {
            deleteDeveloper();
        }
        System.out.print("Press enter to continue...");
        System.in.read();
    }

    private static void addDeveloperToProject() {
        showAllProjects();
        System.out.println("---------------------------");
        System.out.print("Select project id: ");
        Long projectId = scanner.nextLong();
        Project project = projectDAO.getById(projectId);
        System.out.println(project);
        System.out.println("---------------------------");
        showAllDevelopers();
        System.out.println("---------------------------");
        System.out.print("Select developer id: ");
        Long developerId = scanner.nextLong();
        Developer developer = developerDAO.getById(developerId);
        System.out.println(developer);
        developer.getProjects().add(project);
        developerDAO.update(developer);
        System.out.println("Developer " + developer.getName() + " added to project " + project.getProjectName());
    }

    private static void deleteDeveloper() {
        showAllDevelopers();
        System.out.println("---------------------------");
        System.out.print("Select developer id: ");
        Long developerId = scanner.nextLong();
        developerDAO.delete(developerId);
        System.out.println("Developer deleted");
    }

    private static void deleteProject() {
        showAllProjects();
        System.out.println("---------------------------");
        System.out.print("Select project id: ");
        Long projectId = scanner.nextLong();
        Project project = projectDAO.getById(projectId);
        project.getDevelopers().forEach(developer -> {
            developer.getProjects().remove(project);
            developerDAO.update(developer);
        });
        projectDAO.delete(projectId);
        System.out.println("Project deleted");
    }

    private static void showAllDevelopers() {
        developerDAO.getAll().forEach(System.out::println);
    }

    private static void showAllProjects() {
        projectDAO.getAll().forEach(System.out::println);
    }

    private static void addNewSkill() {
        showAllDevelopers();
        System.out.println("---------------------------");
        System.out.print("Select developer id: ");
        Long developerId = scanner.nextLong();
        Developer developer = developerDAO.getById(developerId);
        System.out.println("\t" + developer);
        System.out.print("Skill name: ");
        String name = scanner.next();
        System.out.print("level: ");
        String level = scanner.next();
        developer.getSkills().add(new Skill(name, level));
        developerDAO.update(developer);
        System.out.println("\nSkill added to developer " + developer.getName());
    }

    private static void addNewProject() {
        System.out.print("Project name: ");
        String projectName = scanner.next();
        System.out.print("Cost: ");
        int cost = scanner.nextInt();
        System.out.print("Customer:\n\tCustomer name: ");
        String customerName = scanner.next();
        System.out.print("Company:\n\tCompany name: ");
        String companyName = scanner.next();
        System.out.print("\tYear of foundation: ");
        int year = scanner.nextInt();
        projectDAO.add(new Project(projectName, cost, new Customer(customerName), new Company(companyName, year)));
    }

    private static void addNewDeveloper() {
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        System.out.print("Sex(male/female): ");
        String sex = scanner.next();
        System.out.print("Salary: ");
        int salary = scanner.nextInt();
        developerDAO.add(new Developer(name, age, sex, salary));
    }

    public static void main(String[] args) {
        int choice = -1;
        do {
            showMenu();
            choice = scanner.nextInt();
            try {
                selector(choice);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        } while (choice != 0);
        System.exit(0);
//        companyDAO.sessionFactory.close();
//        customerDAO.sessionFactory.close();
//        developerDAO.sessionFactory.close();
//        companyDAO.sessionFactory.close();
//        skillDAO.sessionFactory.close();
    }
}
