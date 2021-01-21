package inluppStreams_lambda1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Del3 {
	private static Random ran = new Random();

	public static void main(String[] args) {
		String[] names = { "Alice", "Alicia", "Olivia", "Ella", "Ebba", "Lilly", "Astrid", "Saga", "Freja", "Wilma",
				"Maja", "Agnes", "Elsa", "Alma", "Clara", "Ellie", "Selma", "Julia", "Stella", "Alva", "Signe", "Vera",
				"Ellen", "Leah", "Molly", "Ines", "Ester", "Linnea", "Isabelle", "Sara", "Nova", "Nellie", "Emilia",
				"Emma", "Elvira", "Sigrid", "Iris", "Nora", "Lova", "Juni", "Sofia", "Edith", "Elise", "Celine", "Liv",
				"Elin", "Luna", "Livia", "Leia", "IsabellaAce", "Adam", "Adrian", "Albin", "Alexander", "Alfred",
				"Arvid", "August", "Axel", "Benjamin", "Charlie", "Ebbe", "Elias", "Elliot", "Emil", "Erik", "Filip",
				"Frank", "Gabriel", "Gustav", "Harry", "Henry", "Hugo", "Isak", "Josef", "Leo", "Leon", "Liam", "Loui",
				"Love", "Lucas", "Ludvig", "Matteo", "Melker", "Melvin", "Mohamed", "Nils", "Noah", "Oliver", "Olle",
				"Oscar", "Sixten", "Theo", "Theodor", "Vidar", "Viggo", "Vincent", "Walter", "William" };

		List<MyObject> objects = new ArrayList<>();

		for (int i = 0; i < 50; i++) {
			boolean b = getRandomInt(1, 0) > 0;
			int v = getRandomInt(50, 1);
			String n = names[getRandomInt(names.length - 1, 0)];
			objects.add(new MyObject(b, v, n));
		}
		
		objects.forEach(System.out::println);

		System.out.println("\n1. ");
		objects.stream().filter(o -> o.getValue()>20).forEach(System.out::println);
		
		System.out.println("\n2. Medelvärdet: " + objects.stream().mapToInt(o -> o.getValue()).average().getAsDouble());
		
		System.out.println("\n3. ");
		objects.stream().filter(o -> o.isBool()).forEach(o->o.setName("this is true"));
		objects.forEach(System.out::println);
		
	}

	private static int getRandomInt(int max, int min) {
		int re = ran.nextInt(max + 1 - min) + min;
		return re;
	}
}
