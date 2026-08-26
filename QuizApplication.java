import java.util.Scanner;
import java.util.concurrent.*;

public class QuizApplication {

    static Scanner sc = new Scanner(System.in);

    static String[] questions = {
        "Which language is used to develop Android applications?",
        "Which keyword is used to create a class in Java?",
        "Which method is the starting point of a Java program?",
        "Which data type is used to store decimal numbers?",
        "Which symbol is used to end a statement in Java?"
    };

    static String[][] options = {
        {"1. Java", "2. HTML", "3. CSS", "4. SQL"},
        {"1. function", "2. class", "3. create", "4. method"},
        {"1. start()", "2. run()", "3. main()", "4. execute()"},
        {"1. int", "2. char", "3. double", "4. boolean"},
        {"1. .", "2. :", "3. ;", "4. ,"}
    };

    static int[] answers = {1, 2, 3, 3, 3};

    public static void main(String[] args) {

        int score = 0;
        int correct = 0;
        int incorrect = 0;

        System.out.println("================================");
        System.out.println("       JAVA QUIZ APPLICATION");
        System.out.println("================================");
        System.out.println("You have 10 seconds for each question.");

        for (int i = 0; i < questions.length; i++) {

            System.out.println("\n--------------------------------");
            System.out.println("Question " + (i + 1) + " of " + questions.length);
            System.out.println("--------------------------------");

            System.out.println(questions[i]);

            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.println("\nTimer: 10 seconds");

            ExecutorService executor = Executors.newSingleThreadExecutor();

            Future<Integer> future = executor.submit(() -> {
                System.out.print("Enter your answer: ");
                return sc.nextInt();
            });

            int userAnswer = -1;

            try {

                for (int time = 10; time > 0; time--) {

                    if (future.isDone()) {
                        break;
                    }

                    System.out.print("\rTime remaining: " + time + " seconds ");

                    Thread.sleep(1000);
                }

                if (future.isDone()) {
                    userAnswer = future.get();

                    System.out.println();

                    if (userAnswer == answers[i]) {
                        System.out.println("Correct!");
                        score += 10;
                        correct++;
                    } else {
                        System.out.println("Wrong!");
                        System.out.println("Correct answer: " + answers[i]);
                        incorrect++;
                    }

                } else {

                    System.out.println("\n\nTime's up!");
                    System.out.println("Correct answer: " + answers[i]);

                    future.cancel(true);
                    incorrect++;
                }

            } catch (Exception e) {

                System.out.println("\nInvalid input!");
                incorrect++;

            } finally {

                executor.shutdownNow();
            }
        }

        System.out.println("\n================================");
        System.out.println("            RESULT");
        System.out.println("================================");

        System.out.println("Total Questions : " + questions.length);
        System.out.println("Correct Answers : " + correct);
        System.out.println("Incorrect Answers : " + incorrect);
        System.out.println("Final Score     : " + score + "/" + (questions.length * 10));

        double percentage =
                ((double) score / (questions.length * 10)) * 100;

        System.out.printf("Percentage      : %.2f%%\n", percentage);

        System.out.println("================================");

    }
}