import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("STUDENT GRADE CALCULATOR");
        System.out.println("------------------------");

        // Step 1: Input number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Validate input (must be positive)
        if (numSubjects <= 0) {
            System.out.println("Invalid number of subjects. Exiting...");
            return;
        }

        // Step 2: Take marks for each subject (out of 100)
        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        System.out.println("\nEnter marks obtained (out of 100) for each subject:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();

            // Validate marks (must be between 0 and 100)
            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid marks! Marks should be between 0 and 100.");
                i--; // Retry input for this subject
                continue;
            }

            totalMarks += marks[i];
        }

        // Step 3: Calculate Average Percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Step 4: Determine Grade
        String grade = calculateGrade(averagePercentage);

        // Step 5: Display Results
        System.out.println("\n----- RESULTS -----");
        System.out.println("Total Marks: " + totalMarks + " / " + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    // Helper method to calculate grade based on average percentage
    private static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+ (Excellent)";
        } else if (averagePercentage >= 80) {
            return "A (Very Good)";
        } else if (averagePercentage >= 70) {
            return "B+ (Good)";
        } else if (averagePercentage >= 60) {
            return "B (Above Average)";
        } else if (averagePercentage >= 50) {
            return "C (Average)";
        } else if (averagePercentage >= 40) {
            return "D (Pass)";
        } else {
            return "F (Fail)";
        }
    }
}
