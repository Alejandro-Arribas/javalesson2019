package ua.step.homework02;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Задание: Ввести строку с клавиатуры. Из введённой строки выбрать все слова
 * начинающиеся на гласные буквы (e, o, a, u, i или y) и заканчивающиеся на согласные. Вывести
 * отобранные слова на консоль.
 */
public class Task04 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите строку: ");
		String line = scanner.nextLine();

		// TODO: Пишите код здесь
		String words = "eoauiy";
		Arrays.stream(line.toLowerCase().split("\\s+"))
				.filter(word -> word.length() >= 2
						&& 	words.contains(word.substring(0, 1))
						&& !words.contains(word.substring(word.length()-1)))
				.forEach(s -> System.out.print(s + " "));

	}
}
