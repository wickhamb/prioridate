package prioridate;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.DefaultCaret;

public class PrioridateUI {
    private static String WELCOME_MESSAGE = "Welcome to Prioridate!";
    private static String SELECT_OPTION = "Please select an option below:";
    private static String SELECTION_FAILED = "Incorrect option, press \"ENTER\" to continue.";
    private static String LOGIN_MESSAGE = "Enter your account information below:";
    private static String LOGIN_FAILED = "The user name or password you entered was incorrect, please press\n" 
    + "\"ENTER\" to continue.";
    private static String WELCOME_USER = "Welcome back, ";
    private static String MENU_TODO = "View to-do list";
    private static String MENU_CLASSES = "View classes";
    private static String MENU_GROUPS = "View classes";
    private static String MENU_NOTIF = "View notifications";
    private static String TODO_LIST_MESSAGE = "Your current to-do list. Enter an item number to view more details";
    private ArrayList<String> subjects;
    private ArrayList<String> tasks;
    private ArrayList<String> priority;
    private int pageCounter = 0;
    private int assignmentCount;
    private int assignmentMax;
    private String currentMenu;
    private Boolean invalidLogin;
    private Scanner scanner;
    private Prioridate prioridate;

    public PrioridateUI()
    {
        this.subjects = new ArrayList<String>(); this.tasks = new ArrayList<String>(); 
        this.priority = new ArrayList<String>();
        subjects.add("Math"); subjects.add("English"); subjects.add("Chemistry"); 
        subjects.add("Math"); subjects.add("Social Studies"); subjects.add("Math");
        subjects.add("Chemistry");
        tasks.add("Exam 1"); tasks.add("Homework 3"); tasks.add("Reading 5"); 
        tasks.add("Homework 2"); tasks.add("Reading 6"); tasks.add("Quiz 5");
        tasks.add("Reading 9");
        priority.add("High"); priority.add("High"); priority.add("Moderate"); 
        priority.add("Low"); priority.add("Low"); priority.add("Low"); 
        priority.add("Low");
        assignmentCount = subjects.size();
        this.invalidLogin = false;
        this.currentMenu = null;
        scanner = new Scanner(System.in);
        //this.Prioridate = Prioridate;
    }

    public void run()
    {
        System.out.println("Initializing Program");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true)
        {
            String welcomeScreenBack = "default";
            welcomePage();
            String welcomeInput = scanner.nextLine();
            String processedWelcome = processWelcomeCommand(getWelcomeUserCommand(welcomeInput));
            if(processedWelcome.equals("failed"))
            {
                continue;
            }
            else if(processedWelcome.equals("L"))
            {
                while(true)
                {
                    invalidLogin = false;
                    String loginScreenBack = "default";
                    blankLoginPage();
                    String loginInput = scanner.next();
                    String username = getUsername(loginInput);
                    if(username.equals("back"))
                    {
                        welcomeScreenBack = "back";
                        break;
                    }
                    partialLoginPage(username);
                    loginInput = scanner.next();
                    String password = getPassword(loginInput);
                    if(password.equals("back"))
                    {
                        welcomeScreenBack = "back";
                        break;
                    }
                    if(invalidLogin == true)
                    {
                        invalidLoginScreen();
                        continue;
                    }
                    else if(invalidLogin == false)
                    {
                        scanner.nextLine();
                        while(true)
                        {
                            String homeScreenBack = "default";
                            HomeScreenView(username);
                            String homeInput = scanner.nextLine();
                            homeInput = getHomeInput(homeInput);
                            if(homeInput.equals("back"))
                            {
                                loginScreenBack = "back";
                                break;
                            }
                            switch(homeInput)
                            {
                                case "error":
                                    failedHomeScreen();
                                    continue;
                                case "1":
                                {
                                    toDoLoop: while(true)
                                    {
                                        String toDoScreenBack = "default";
                                        while(true)
                                        {
                                            todoListScreen(subjects, tasks, priority, assignmentCount, pageCounter);
                                            String toDoInput = scanner.nextLine();
                                            toDoInput = getToDoInput(toDoInput);
                                            if(toDoInput.equals("<") && pageCounter == 0)
                                            {
                                                failedToDoScreen();
                                                continue;
                                            }
                                            switch(toDoInput)
                                            {
                                                case "back":
                                                {
                                                    toDoScreenBack = "back";
                                                    break toDoLoop;
                                                }
                                                case "error":
                                                {
                                                    failedToDoScreen();
                                                    continue;
                                                }
                                                case "<":
                                                {
                                                    assignmentCount = assignmentCount + 5;
                                                    pageCounter = pageCounter - 1;
                                                    continue;
                                                }
                                                case ">":
                                                {
                                                    assignmentCount = assignmentCount - 5;
                                                    pageCounter = pageCounter + 1;
                                                    continue;
                                                }
                                                case "1":
                                                {

                                                }
                                                case "2":
                                                {

                                                }
                                                case "3":
                                                {

                                                }
                                                case "4":
                                                {

                                                }
                                                case "5":
                                                {

                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                                case "2":
                                {
                                    
                                }
                                case "3":
                                {

                                }
                                case "4":
                                {

                                }
                            }
                            if(homeScreenBack.equals("back"))
                            {
                                continue;
                            }
                        }
                    }
                    if(loginScreenBack.equals("back"))
                    {
                        continue;
                    }
                }
            }
            if(processedWelcome.equals("C"))
            {

            }
            if(welcomeScreenBack.equals("back"))
            {
                continue;
            }
            else
            {

            }
        }
    }
    

    private void welcomePage()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(WELCOME_MESSAGE + "\n");
        System.out.println(SELECT_OPTION + "\n");
        System.out.println("  [L] Login\n");
        System.out.println("  [C] Create Account\n");
        System.out.println("\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    private String getWelcomeUserCommand(String command)
    {
        String welcomeCommand = command;
        if(command.equals("L") || command.equals("l") || command.equals("C")
           || command.equals("c") || command.equals("X") || command.equals("x"))
        {
            command = command.toUpperCase();
            welcomeCommand = command;
        }
        else
        {
            welcomeCommand = "error";
        }
        return welcomeCommand;
    }

    private String processWelcomeCommand(String command)
    {
        String processedWelcome = command;
        if(command.equals("X"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("error"))
        {
            clearScreen();
            System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
            System.out.println(SELECTION_FAILED + "\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            scanner.nextLine();
            processedWelcome = "failed";
        }
        return processedWelcome; 
    }

    private void blankLoginPage()
    {
        clearScreen();
        System.out.println("::::::::::::::::::::::::::::::::: Log-in :::::::::::::::::::::::::::::::\n");
        System.out.println(LOGIN_MESSAGE + "\n");
        System.out.println("Username: \n");
        System.out.println("Password: \n");
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Options: [B] Go Back [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        System.out.print("Username: ");
    }

    private String getUsername(String command)
    {
        String loginCommand = command;
        if(command.equals("username"))
        {
            loginCommand = command;
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("B") || command.equals("b"))
        {
            scanner.nextLine();
            loginCommand = "back";
        }
        else
        {
            loginCommand = command;
            invalidLogin = true;
        }
        return loginCommand;
    }

    private void partialLoginPage(String username)
    {
        clearScreen();
        System.out.println("::::::::::::::::::::::::::::::::: Log-in :::::::::::::::::::::::::::::::\n");
        System.out.println(LOGIN_MESSAGE + "\n");
        System.out.println("Username: *********\n");
        System.out.println("Password: \n");
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Options: [B] Go Back [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        System.out.print("Password: ");
    }

    private String getPassword(String command)
    {
        String loginCommand = command;
        if(command.equals("password"))
        {
            loginCommand = command;
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("B") || command.equals("b"))
        {
            scanner.nextLine();
            loginCommand = "back";
        }
        else
        {
            loginCommand = command;
            invalidLogin = true;
        }
        return loginCommand;
    }

    private void invalidLoginScreen()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(LOGIN_FAILED + "\n");
        System.out.println("\n\n\n\n\n\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void HomeScreenView(String username)
    {
        clearScreen();
        System.out.println("::::::::::::::::::::::::::::::::: Home :::::::::::::::::::::::::::::::::\n");
        System.out.println(WELCOME_USER + username + "\n");
        System.out.println(SELECT_OPTION+"\n");
        System.out.println("  1) " + MENU_TODO + "\n");
        System.out.println("  2) " + MENU_CLASSES + "\n");
        System.out.println("  3) " + MENU_GROUPS + "\n");
        System.out.println("  4) " + MENU_NOTIF + "\n");
        System.out.println("\nOptions: [B] Go Back [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    private String getHomeInput(String command)
    {
        String homeCommand = command;
        if(command.equals("1") || command.equals("2") || command.equals("3")
           || command.equals("4"))
        {
            homeCommand = command;
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("B") || command.equals("b"))
        {
            homeCommand = "back";
        }
        else
        {
            homeCommand = "error";
        }
        return homeCommand;
    }

    private void failedHomeScreen()
    {
        clearScreen();
        System.out.println("::::::::::::::::::::::::::::::::: Home :::::::::::::::::::::::::::::::::\n");
        System.out.println(SELECTION_FAILED + "\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        scanner.nextLine();
    }

    private void todoListScreen(ArrayList<String> subjects, ArrayList<String> tasks, ArrayList<String> priority,
    int assignmentCount, int pageCounter)
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: To-Do List ::::::::::::::::::::::::::::::\n");
        System.out.println(TODO_LIST_MESSAGE + "\n");
        System.out.println("#    Subject           Task                  Priority");
        if(assignmentCount > 5)
        {
            for(int i = 0; i < 5; i++)
            {
                int j = i + pageCounter*5;
                processAssignmentLine(subjects, tasks, priority, j);
            }
            if(pageCounter == 0)
            {
                System.out.println("\n\n                   [>] Next Page\n");
            }
            else
            {
                System.out.println("\n\n[<] Previous Page [>] Next Page\n");
            }
            System.out.println("\nOptions: [B] Go Back [X] Exit");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        }
        else
        {
            for(int i = 0; i < assignmentCount; i++)
            {
                int j = i + pageCounter*5;
                processAssignmentLine(subjects, tasks, priority, j);
            }
            System.out.println("\n\n[<] Previous Page\n");
            System.out.println("\nOptions: [B] Go Back [X] Exit");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        }
    }

    private void processAssignmentLine(ArrayList<String> subjects, ArrayList<String> tasks, ArrayList<String> priority, int i)
    {
        String mathSpacing = "              "; String englishSpacing = "           ";
        String chemSpacing = "         "; String socialStudiesSpacing = "    ";
        String examSingleDigit = "                "; String quizSingleDigit = "                ";
        String homeworkSingleDigit = "            "; String readingSingleDigit = "             ";
        System.out.println("");
                    System.out.print((i+1)+"    ");
                    switch(subjects.get(i))
                    {
                        case "Math":
                            System.out.print(subjects.get(i)+mathSpacing);
                            switch(tasks.get(i).substring(0, tasks.get(i).indexOf(" ")))
                            {
                                case "Exam":
                                    System.out.print(tasks.get(i)+examSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Quiz":
                                    System.out.print(tasks.get(i)+quizSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Homework":
                                    System.out.print(tasks.get(i)+homeworkSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Reading":
                                    System.out.print(tasks.get(i)+readingSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                            }
                            break;
                        case "English":
                            System.out.print(subjects.get(i)+englishSpacing);
                            switch(tasks.get(i).substring(0, tasks.get(i).indexOf(" ")))
                            {
                                case "Exam":
                                    System.out.print(tasks.get(i)+examSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Quiz":
                                    System.out.print(tasks.get(i)+quizSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Homework":
                                    System.out.print(tasks.get(i)+homeworkSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Reading":
                                    System.out.print(tasks.get(i)+readingSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                            }
                            break;
                        case "Chemistry":
                            System.out.print(subjects.get(i)+chemSpacing);
                            switch(tasks.get(i).substring(0, tasks.get(i).indexOf(" ")))
                            {
                                case "Exam":
                                    System.out.print(tasks.get(i)+examSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Quiz":
                                    System.out.print(tasks.get(i)+quizSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Homework":
                                    System.out.print(tasks.get(i)+homeworkSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Reading":
                                    System.out.print(tasks.get(i)+readingSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                            }
                            break;
                        case "Social Studies":
                            System.out.print(subjects.get(i)+socialStudiesSpacing);
                            switch(tasks.get(i).substring(0, tasks.get(i).indexOf(" ")))
                            {
                                case "Exam":
                                    System.out.print(tasks.get(i)+examSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Quiz":
                                    System.out.print(tasks.get(i)+quizSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Homework":
                                    System.out.print(tasks.get(i)+homeworkSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                                case "Reading":
                                    System.out.print(tasks.get(i)+readingSingleDigit);
                                    System.out.print(priority.get(i));
                                    break;
                            }
                            break;
                    }
    }

    private String nextPagePreviousPage(String command)
    {
        String pageCommand = command;
        if(command.equals("<") || command.equals(">"))
        {
            pageCommand = command;
        }
        if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else
        {
            pageCommand = "error";
        }
        return pageCommand;
    }

    private String getToDoInput(String command)
    {
        String toDoCommand = command;
        if(command.equals("<") || command.equals(">"))
        {
            toDoCommand = command;
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("B") || command.equals("b") || command.equals(""))
        {
            toDoCommand = "back";
        }
        else
        {
            toDoCommand = "error";
        }
        return toDoCommand;
    }

    private void failedToDoScreen()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: To-Do List ::::::::::::::::::::::::::::::\n");
        System.out.println(SELECTION_FAILED + "\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        scanner.nextLine();
    }

    private void createAccount()
    {

    }

    private String getField(String prompt)
    {
        String one = "one";
        return one;
    }

    private void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args)
    {
        PrioridateUI prioridateUI = new PrioridateUI();
        prioridateUI.run();
    }
}
