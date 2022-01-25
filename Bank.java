import java.io.*;
import java.lang.* ;
import java.util.* ;
/*Слегка запутался и были некоторые проблемы,
но вроде все нормально,возможно есть ошибки и недочеты
 */

class Person {
    private String name, surname, phone ;

    public Person(String name, String surname, String phone)
    {
        this.name = name ;
        this.surname = surname ;
        this.phone = phone ;
    }
    
    void setName(String name)
    {
        this.name = name ;
    }

    void setSurname(String surname)
    {
        this.surname = surname ;
    }

    void setPhone(String phone)
    {
        this.phone = phone ;
    }

    String getName()
    {
        return this.name ;
    }

    String getSurname()
    {
        return this.surname ;
    }
    
    String getPhone()
    {
        return this.phone ;
    }
}

class Bill {
    private Integer amount ;

    public Bill(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

class Account {
    private Person person ;
    private Bill bill ;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Account(Person person, Bill bill) {
        this.person = person;
        this.bill = bill;
    }
}

class Payment {
    private Bill bill ;

    public Payment(Bill bill) {
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}

class PaymentService {
    public void pay(Account a, Payment p) {
        if(a.getBill().getAmount() >= p.getBill().getAmount()) {
            a.getBill().setAmount(a.getBill().getAmount()-p.getBill().getAmount());
        } else {
            System.out.println("Insufficient money!");
        }
    }
}

class Adjustment {
    private Bill bill ;
}

class Deposit {
    private Bill bill ;

    public Deposit(Bill bill) {
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}

class DepositService {
    public void deposit(Account a, Deposit d) {
        a.getBill().setAmount(a.getBill().getAmount() + d.getBill().getAmount());
    }
}

class TransferService {
    public void transfer(Account a, Account b, int amount) {
        if(a.getBill().getAmount() >= amount) {
            a.getBill().setAmount(a.getBill().getAmount() - amount);
            b.getBill().setAmount(b.getBill().getAmount() + amount);
        } else {
            System.out.println("Insufficient money!");
        }
    }
}

public class Bank {
    public static void main(String[] args)
    {
        /* initialize accounts */
        Account aset = new Account(new Person("Aset", "Aset", "123"), new Bill(1000-7)) ;
        Account amirlan = new Account(new Person("Amirlan", "Amirlan", "88005553535"), new Bill(1000000)) ;

        /* initialize services */
        PaymentService paymentService = new PaymentService() ;
        DepositService depositService = new DepositService() ;
        TransferService transferService = new TransferService() ;

        /* initialize payments */
        Payment electricityPayment = new Payment(new Bill(100)) ;
        Payment waterPayment = new Payment(new Bill(50)) ;

        /* pay */
        paymentService.pay(aset, electricityPayment);
        System.out.println("Aset's Bill: " + aset.getBill().getAmount());
        paymentService.pay(amirlan, waterPayment);
        System.out.println("Amirlan's Bill: " + amirlan.getBill().getAmount());

        /* deposit */
        Deposit deposit = new Deposit(new Bill(1000000)) ;
        depositService.deposit(aset, deposit);
        System.out.println("Aset's Bill after deposit: " + aset.getBill().getAmount());

        /* transfer */
        transferService.transfer(amirlan, aset, 1000); ;
        System.out.println("Aset's Bill after transfer: " + aset.getBill().getAmount());
        System.out.println("Amirlan's Bill after transfer: " + amirlan.getBill().getAmount());
    }
}
