package ua.step.homework;

/*
 * Шаблон для решения домашнеего задания 11. 
 */
/**
 * Шаблон для решения домашнеего задания У Деда Мороза есть часы, которые в
 * секундах показывают сколько осталось до каждого Нового Года. Так как Дед
 * Мороз уже человек в возрасте, то некоторые математические операции он быстро
 * выполнять не в состоянии. Помогите Деду Морозу определить сколько полных
 * дней, часов, минут и секунд осталось до следующего Нового Года, если известно
 * сколько осталось секунд, т. е. разложите время в секундах на полное
 * количество дней, часов, минут и секунд. Выведите результат на консоль.
 */
public class Task11
{
    public static void main(String[] args)
    {
        int SECONDS_TO_NEW_YEAR = 61;
        // данная строка нужна для автоматического теста (смотри Task11Test)
        SECONDS_TO_NEW_YEAR = (args.length == 1) ? Integer.valueOf(args[0]) : SECONDS_TO_NEW_YEAR;
        // используй переменную SECONDS_TO_NEW_YEAR для решения
    }
}