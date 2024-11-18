import java.util.Scanner;

public class Cook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 제목
        Title titleObj = new Title(scanner);
        String title = titleObj.getTitle();

        // 평가
        Rating ratingObj = new Rating(scanner);
        double rating = ratingObj.getRating();
        double percent = ratingObj.getPercent();

        // 레시피
        Recipe recipeObj = new Recipe(scanner);
        String[] recipes = recipeObj.getRecipe();

        // 출력
        System.out.println("[" + title + "]");
        System.out.println("별점: " + rating + " (" + percent + "%)");
        for (int i = 1; i <= recipes.length; i++) {
            System.out.println(i + ". " + recipes[i - 1]);
        }

        scanner.close();
    }
}

class Title {
    private String title;

    public Title(Scanner scanner) {
        System.out.print("요리 제목을 입력하세요: ");
        this.title = scanner.nextLine();
    }
    public String getTitle() {
        return title;
    }
}

class Rating {
    private double rating;

    public Rating(Scanner scanner) {
        while (true) {
        System.out.print("요리 평점을 1~5 사이의 실수로 입력하세요 (ex: 4.5): ");
        this.rating = scanner.nextDouble();
        scanner.nextLine(); // 개행 문자 소비

        if (this.rating > 5 || this.rating < 1) {
            System.out.print("잘못된 숫자입니다. ");
        } else break;
        }
    }
    public double getRating() {
        return rating;
    }
    public double getPercent() {
        return (rating / 5) * 100;
    }
}

class Recipe {
    private String[] recipe;

    public Recipe(Scanner scanner) {
        recipe = new String[10]; // 배열 초기화
        for (int i = 1; i <= 10; i++) {
            System.out.print("요리 레시피를 입력하세요. (" + i + "/10줄): ");
            recipe[i - 1] = scanner.nextLine();
        }
    }
    public String[] getRecipe() {
        return recipe;
    }
}