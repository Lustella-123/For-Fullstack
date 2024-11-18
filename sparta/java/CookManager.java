import java.util.Scanner;

public class CookManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 제목 입력
        CookTitle cookTitle = new CookTitle(scanner);
        String title = cookTitle.getTitle();

        // 별점 입력
        CookRating cookRating = new CookRating(scanner);
        double rating = cookRating.getRating();
        String percent = cookRating.getRatingPercentage();

        // 레시피 입력
        CookRecipe cookRecipe = new CookRecipe(scanner);
        String[] recipes = cookRecipe.getRecipes();

        // 출력
        System.out.println("[" + title + "]");
        System.out.println("별점: " + rating + " (" + percent + ")");
        for (int i = 1; i <= recipes.length; i++) {
            System.out.println(i + ". " + recipes[i - 1]);
        }

        scanner.close();
    }
}

class CookTitle {
    private String title;

    public CookTitle(Scanner scanner) {
        System.out.print("요리 제목을 입력하세요. : ");
        this.title = scanner.nextLine();
    }

    public String getTitle() {
        return title;
    }
}

class CookRating {
    private double rating;

    public CookRating(Scanner scanner) {
        System.out.print("요리 별점을 1~5의 실수로 입력하세요.(ex:4.5) : ");
        this.rating = scanner.nextDouble();
        scanner.nextLine(); // 개행 문자 소비
    }

    public double getRating() {
        return rating;
    }

    public String getRatingPercentage() {
        return (rating / 5 * 100) + "%";
    }
}

class CookRecipe {
    private String[] recipes;

    public CookRecipe(Scanner scanner) {
        recipes = new String[10];
        for (int i = 1; i <= 10; i++) {
            System.out.print("요리 레시피를 입력하세요.(" + i + "/10줄) : ");
            recipes[i - 1] = scanner.nextLine();
        }
    }

    public String[] getRecipes() {
        return recipes;
    }
}
