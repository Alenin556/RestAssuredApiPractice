package api.PowerBank.Transactions;

public class DepositTransaction {
    public String date;
    public String time;
    public String name;
    public double amount;

    public DepositTransaction(){
    }

    public DepositTransaction(String date, String time, String name, double amount) {
        this.date = date;
        this.time = time;
        this.name = name;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}
