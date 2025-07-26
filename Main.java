import java.util.Scanner;

class User {
    String name;
    int age;
    String gender;
    double heightFeet;
    double weightKg;

    public User(String name, int age, String gender, double heightFeet, double weightKg) {
        this.name = name;
        this.age = age;
        this.gender = gender.toLowerCase();
        this.heightFeet = heightFeet;
        this.weightKg = weightKg;
    }

    public double getHeightCm() {
        return heightFeet * 30.48;
    }
}

class BMRCalculator {
    public static double calculateBMR(User user) {
        double heightCm = user.getHeightCm();

        if (user.gender.equals("male")) {
            return 10 * user.weightKg + 6.25 * heightCm - 5 * user.age + 5;
        } else {
            return 10 * user.weightKg + 6.25 * heightCm - 5 * user.age - 161;
        }
    }
}

class TDEECalculator {
    public static double calculateTDEE(double bmr, int activityChoice) {
        double[] activityFactors = {1.2, 1.375, 1.55, 1.725};
        return bmr * activityFactors[activityChoice - 1];
    }

    public static String getActivityLevel(int choice) {
        switch (choice) {
            case 1:
                return "Sedentary (little to no exercise)";
            case 2:
                return "Lightly Active (light exercise 1–3 days/week)";
            case 3:
                return "Moderately Active (moderate exercise 3–5 days/week)";
            case 4:
                return "Very Active (hard exercise 6–7 days/week)";
            default:
                return "Invalid";
        }
    }

    public static double getCaloriesForGoal(double tdee, String goal) {
        if (goal.equals("loss")) {
            return tdee - 500;
        } else {
            return tdee + 500;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        System.out.print("Enter your gender (male/female): ");
        String gender = sc.next();

        System.out.print("Enter your height in feet: ");
        double heightFeet = sc.nextDouble();

        System.out.print("Enter your weight in kg: ");
        double weightKg = sc.nextDouble();

        User user = new User(name, age, gender, heightFeet, weightKg);

    
        System.out.println("\nChoose your goal:");
        System.out.println("1. Weight Loss");
        System.out.println("2. Weight Gain");
        int goalChoice = sc.nextInt();
        String goal = (goalChoice == 1) ? "loss" : "gain";
        
        double bmr = BMRCalculator.calculateBMR(user);
        System.out.println("\nYour BMR is: " + Math.round(bmr) + " calories/day");

        System.out.println("\nSelect your activity level:");
        System.out.println("1. Sedentary (little to no exercise)");
        System.out.println("2. Lightly Active (light exercise 1–3 days/week)");
        System.out.println("3. Moderately Active (moderate exercise 3–5 days/week)");
        System.out.println("4. Very Active (hard exercise 6–7 days/week)");
        int activityChoice = sc.nextInt();

        double tdee = TDEECalculator.calculateTDEE(bmr, activityChoice);
        String activityLevel = TDEECalculator.getActivityLevel(activityChoice);
        System.out.println("\nYour TDEE (" + activityLevel + ") is: " + Math.round(tdee) + " calories/day");

        double goalCalories = TDEECalculator.getCaloriesForGoal(tdee, goal);

        if (goal.equals("loss")) {
            System.out.println("To lose weight, your daily calorie intake should be: " + Math.round(goalCalories) + " calories");
        } else {
            System.out.println("To gain weight, your daily calorie intake should be: " + Math.round(goalCalories) + " calories");
        }

        System.out.println("\nThank you, " + user.name + "! Stay healthy!");
    }
}
