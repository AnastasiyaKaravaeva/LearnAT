package model.score;

import model.account.Account;
import model.constants.Constants;
import model.money.Money;

public class CurrentScore extends Score {
    private DebetScore debetScore;

    public CurrentScore(Money balance, Account owner, Integer
            number, DebetScore debetScore) {
        super(balance, owner, number);
        this.debetScore = debetScore;
    }

    @Override
    protected boolean checkBefore(Money money) {
        double usdValueIn = money.getValue() * money.getCurrency().getUsdCource();
        if (usdValueIn > Constants.MAX_CURRENT_DEPOSIT) {
            this.debetScore.addMoney(new Money(Constants.DEBIT_SCORE_DEPOSIT, "USD"));
        }
        return true;
    }
}

