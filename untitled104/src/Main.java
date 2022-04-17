import java.util.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;


class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())), families.get(new Random().nextInt(families.size())), new Random().nextInt(100), Sex.values()[new Random().nextInt(Sex.values().length)], Education.values()[new Random().nextInt(Education.values().length)]));
        }
        long count = persons.stream().filter(age -> age.getAge() < 18).count();
        System.out.println(count);
        System.out.println();


        List<String> listOfConscripts = persons.stream()
                .filter(sex -> sex.getSex() == Sex.MAN)
                .filter(age -> age.getAge() > 18 && age.getAge() < 27)
                .map(family -> family.getFamily()).collect(Collectors.toList());
        System.out.println(listOfConscripts);
        System.out.println();

        List<String> educationList = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN && x.getAge() > 18 && x.getAge() < 65)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(x -> x.getFamily()))
                .map(String::valueOf).collect(Collectors.toList());
        System.out.println(educationList);

        List<String> educationList1 = persons.stream()
                .filter(x -> x.getSex() == Sex.WOMAN && x.getAge() > 18 && x.getAge() < 60)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(x -> x.getFamily()))
                .map(String::valueOf).collect(Collectors.toList());
        System.out.println(educationList1);
    }
}
