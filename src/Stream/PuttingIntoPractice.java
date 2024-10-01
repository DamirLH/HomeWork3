package Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> transactionsForYear = transactions.stream()
                .filter(t -> t.getYear()==2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();
        System.out.println("1. Транзакции за 2011 год и отсортированные по сумме: \n"+transactionsForYear);


        List<String> tradersCity = transactions.stream()
                .map(t->t.getTrader().getCity())
                .distinct().toList();
        System.out.println("2. Список неповторяющихся городов, в которых работают трейдеры: \n"+tradersCity);

        List<String> tradersFromCambridge = transactions.stream()
                .filter(t->t.getTrader().getCity().equals("Cambridge"))
                .map(t->t.getTrader().getName())
                .sorted(Comparator.naturalOrder())
                .distinct().toList();
        System.out.println("3. Все трейдеры из Кембриджа, отсортированные по именам: \n"+tradersFromCambridge);

        List<String> tradersList = transactions.stream()
                .map(t->t.getTrader().getName())
                .sorted(Comparator.naturalOrder())
                .distinct().toList();
        System.out.println("4. Все имена трейдеров, отсортированные в алфавитном порядке: \n"+tradersList);

        List<String> milanTrader = transactions.stream()
                .filter(t->t.getTrader().getCity().equals("Milan"))
                .map(t->t.getTrader().getName())
                .distinct().toList();
        System.out.println("5. Трейдер из Милана: \n"+milanTrader);

        int cambridgeTotal = transactions.stream()
                .filter(t->t.getTrader().getCity().equals("Cambridge"))
                .mapToInt(t->t.getValue())
                .sum();
        System.out.println("6. Сумма всех транзакций трейдеров из Кембриджа: \n"+cambridgeTotal);

        int maxAmount = transactions.stream()
                .mapToInt(t->t.getValue())
                .max()
                .orElse(0);
        System.out.println("7. Максимальная сумма среди всех транзакций: \n"+maxAmount);

        int minAmount = transactions.stream()
                .mapToInt(t->t.getValue())
                .min()
                .orElse(0);
        System.out.println("7. Минимальная сумма среди всех транзакций: \n"+minAmount);
    }
}