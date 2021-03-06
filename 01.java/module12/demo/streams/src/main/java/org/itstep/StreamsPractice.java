
package org.itstep;

import java.util.Arrays;
import java.util.List;

public class StreamsPractice {

    @FunctionalInterface
    interface MyInterface<T> {
        void apply(T data); // void println(Object x)
    }

    public static void test(MyInterface obj) {
        obj.apply("test");
    }

    public static void main(String ...args){

        test(data -> System.out.println(data));

        test(System.out::println);

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300), 
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),	
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

		transactions
                .stream()
                //.stream()
                .forEach(System.out::println);

        // Задание 1: найдите все транзакции с 2011 года и отсортируйте их по значению (по возрастанию).
        
        // Задание 2: Найдите все уникальные города, в которых работают трейдеры?

        // Задание 3: Найдите всех трейдеров из Кембриджа и отсортируйте их по названию.
		
        // Задание 4: Полуите строку состоящую из имен всех трейдеров, отсортированных по алфавиту.
        
        // Задание 5: Есть ли в Милане трейдер?        
     
        // Задание 6: Обновите все транзакции, чтобы трейдеры из Милана были из Кембриджа.
       
        // Задание 7: Какова максимальная цена во всех транзакциях?
          
    }
}