package ua.step.homework;
/*
 * Шаблон для решения домашнеего задания 1 часть 1. 
 */

/**
 * В переменных х и y хранятся два натуральных числа. Создайте программу,
 * выводящую на консоль число, котороя является остатком от деления x на y.
 * 
 */
public class Task01_1
{
    public static void main(String[] args)
    {
        int x = 1;
        int y = 2;
        // строки ниже нужны для тестирования, не удаляйтее ее и не изменяйте
        x = (args.length == 0) ? x : Integer.valueOf(args[0]);
        y = (args.length == 0) ? y : Integer.valueOf(args[1]);
        // пишите код ниже испоьзуя переменные объявленные выше, проверить
        // решение можно запустив @see TestTask01_1.java
    }
}