package model.score;

import model.account.Account;
import model.constants.Constants;
import model.money.Money;

public class DebetScore extends Score {
    private CreditScore creditScore;

    public DebetScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    protected boolean checkBefore(Money money) {
        if (this.creditScore.getBalance().getValue() < Constants.MIN_CREDIT_BALANCE) {
            throw new IllegalArgumentException("Unable to work with debit score because the balance of the credit score is -20000.");
        }
        return true;
    }

}