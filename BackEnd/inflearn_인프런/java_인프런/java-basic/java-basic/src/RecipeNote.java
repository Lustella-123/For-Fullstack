import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipeNote {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 자료구조 선택 입력
        System.out.print("저장할 자료구조명을 입력합니다. (List / Set / Map): ");
        String structure = scanner.nextLine();

        // 요리 제목 입력
        System.out.print("내가 좋아하는 요리 제목을 입력합니다: ");
        String recipeTitle = scanner.nextLine();

        // 레시피 단계 입력
        List<String> recipeSteps = new ArrayList<>();
        System.out.println("요리 레시피를 한 문장씩 입력하세요. 입력을 마치려면 '끝'을 입력하세요.");
        while (true) {
            String step = scanner.nextLine();
            if (step.equals("끝")) {
                break;
            }
            recipeSteps.add(step);
        }

        // 출력
        System.out.println("[ " + structure + " 으로 저장된 " + recipeTitle + " ]");
        for (int i = 0; i < recipeSteps.size(); i++) {
            System.out.println((i + 1) + ". " + recipeSteps.get(i));
        }

        scanner.close();
    }
}
