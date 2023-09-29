public class Test {
    public static void main(String[] args) {
        Money money = new Money(10.0, 12.0, 12.0, 11.0, 13.0, 20);
        Money card1Cost = new Money(1.0,1.0,1.0,3.0,4.0, 0);
        Money card2Cost = new Money(1.0,13.0,2.0,3.0,3.0, 0);
        Discount discount = new Discount();
        Player player1 = new Player(money);
        Card card1 = new Card(card1Cost);
        Card card2 = new Card(card2Cost);

        player1.printStatus();
        System.out.println("----------------------------");
        card1.printStatus();
        System.out.println("----------------------------");
        card2.printStatus();
        System.out.println("----------------------------");

        System.out.println("Can player1 purchase card1 ?");
        System.out.println(player1.canPurchase(card1, discount));
        System.out.println("----------------------------");
        System.out.println("Can player1 purchase card2 ?");
        System.out.println(player1.canPurchase(card2, discount));
        System.out.println("----------------------------");
        System.out.println("What happened after player1 purchase card1 ?");
        player1.purchase(card1, discount);
        player1.printStatus();
        System.out.println("----------------------------");
        System.out.println(player1.canPurchase(card2, discount));
        System.out.println("What happened if you got blue discount?");
        discount.adjustDiscount(Color.blue, 0.7);
        System.out.println(player1.canPurchase(card2, discount));

        System.out.println("----------------------------");
        System.out.println("What happened if you buy with discount");
        player1.purchase(card2, discount);
        player1.printStatus();

        System.out.println("----------------------------");
        System.out.println("Can player1 still buy card2?");
        System.out.println(player1.canPurchase(card2, discount));
        player1.purchase(card2, discount);
        player1.printStatus();

    }
}
