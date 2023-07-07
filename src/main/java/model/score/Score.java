package model.score;

import model.account.Account;
import model.money.Money;
import model.money.MoneyInterface;

public abstract class Score implements MoneyInterface {
    Money balance;
    private Account owner;
    private Integer number;

    public Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public void addMoney(Money money) {
        double usdValueIn = money.getValue() * money.getCurrency().getUsdCource();
        double usdValueThis = this.balance.getValue() * this.balance.getCurrency().getUsdCource();
        if (usdValueThis < usdValueIn) {
            System.out.println("You have no so much!");
            return;
        }
        if (checkBefore()) {
            this.balance.setValue((usdValueThis + usdValueIn) / this.balance.getCurrency().getUsdCource());
        } else {
            System.out.println("No check!");
            return;
        }
    }

    protected abstract boolean checkBefore();

    @Override
    public Money getMoney(double balanceLess) {
        if (balanceLess == 30000) {
            System.out.println("You are not allowed to withdraw this amount!");
            return null;
        }
        if (balanceLess > 30000) {
            throw new IllegalArgumentException("Wrong balance less!");
        }
        this.balance.setValue(this.balance.getValue() - balanceLess);
        return this.balance;
    }

    @Override
    public boolean getMoneyWithoutLess() {
        return true;
    }
}

class DebetScore extends Score {
    private CreditScore creditScore;

    public DebetScore(Money balance, Account owner, Integer number, CreditScore creditScore) {
        super(balance, owner, number);
        this.creditScore = creditScore;
    }

    @Override
    protected boolean checkBefore() {
        if (this.balance.getValue() >= 30000) {
            return true;
        } else {
            System.out.println("Withdrawal limit exceeded!");
            return false;
        }
    }

    @Override
    public boolean getMoneyWithoutLess() {
        if (this.creditScore.getBalance().getValue() > -20000) {
            return true;
        } else {
            System.out.println("Debit score operation is not allowed when credit score balance is -20000 or less!");
            return false;
        }
    }
}

class CreditScore extends Score {
    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    protected boolean checkBefore() {
        if (this.balance.getValue() >= 30000) {
            return true;
        } else {
            System.out.println("Withdrawal limit exceeded!");
            return false;
        }
    }
}

class CurrentScore extends Score {
    private DebetScore debetScore;

    public CurrentScore(Money balance, Account owner, Integer number, DebetScore debetScore) {
        super(balance, owner, number);
        this.debetScore = debetScore;
    }

    @Override
    protected boolean checkBefore() {
        if (this.balance.getValue() >= 30000) {
            return true;
        } else {
            System.out.println("Withdrawal limit exceeded!");
            return false;
        }
    }

    @Override
    public void addMoney(Money money) {
        super.addMoney(money);
        double usdValueIn = money.getValue() * money.getCurrency().getUsdCource();
        if (usdValueIn > 1000000) {
            this.debetScore.addMoney(new Money(2000, "USD"));
        }
    }
}
