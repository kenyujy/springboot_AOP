package bank_mockup.entity_model;

public class BankAccount {

    private long account_id;

    private String holder_name;

    private double balance;

    private String auth_string;

    private String create_date;

    public BankAccount(long account_id, String holder_name, double balance, String auth_string, String create_date) {
        this.account_id = account_id;
        this.holder_name = holder_name;
        this.balance = balance;
        this.auth_string = auth_string;
        this.create_date = create_date;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getAuth_string() {
        return auth_string;
    }

    public void setAuth_string(String auth_string) {
        this.auth_string = auth_string;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "account_id=" + account_id +
                ", holder_name='" + holder_name + '\'' +
                ", balance=" + balance +
                ", auth_string='" + auth_string + '\'' +
                ", create_date='" + create_date + '\'' +
                '}';
    }
}
