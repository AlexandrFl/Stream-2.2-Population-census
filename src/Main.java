import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Екатерина", "Иван", "Ольга", "Петр", "Светлана", "Александр");
        List<String> families = Arrays.asList("Иванова", "Абросимов", "Свиридова", "Завадский", "Семенова", "Чайников");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println("\\/ Количество людей старше 18 лет \\/");

        Long count = persons.stream()
                .filter(x -> x.getAge() > 18)
                .count();
        System.out.println(count);

        System.out.println("\\/ Количество мужчин старше 18 и моложе 27 лет \\/");

        Long manCount = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 18)
                .filter((x -> x.getAge() < 27))
                .count();
        System.out.println(manCount);

        System.out.println("\\/ Количество людей потенциально работоспособных людей с высшим образованием \\/");

        List<Person> manPerson = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 65)
                .collect(Collectors.toList());

        List<Person> womanPerson = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getSex() == Sex.WOMAN)
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 60)
                .collect(Collectors.toList());

        womanPerson.addAll(manPerson);
        womanPerson.sort(Comparator.comparing(Person::getFamily));

        System.out.println(womanPerson);
    }
}
