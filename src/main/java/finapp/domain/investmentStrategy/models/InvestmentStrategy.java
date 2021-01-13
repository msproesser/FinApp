package finapp.domain.investmentStrategy.models;

import finapp.domain.investment.models.Investment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvestmentStrategy {

    private final StrategyDefinition strategyDefinition;
    private final Investment investment;
    private final ScoreContext scoreContext;

    public InvestmentStrategy(final StrategyDefinition strategyDefinition, final Investment investment, final ScoreContext scoreContext) {
        this.strategyDefinition = strategyDefinition;
        this.investment = investment;
        this.scoreContext = scoreContext;
    }

    public String getTicker() {
        return strategyDefinition.getTicker();
    }

    public String getCategory() {
        return strategyDefinition.getCategory();
    }

    public Integer getQuantity() {
        return investment.getQuantity();
    }

    public BigDecimal getAverageValue() {
        return investment.getAveragePrice();
    }

    public BigDecimal getTotalInvestValue() {
        return investment.getTotalPrice();
    }

    public BigDecimal getMarketValue() {
        return scoreContext.getMarketValueOf(getTicker());
    }

    public BigDecimal getTotalMarketValue() {
        return getMarketValue().multiply(BigDecimal.valueOf(getQuantity()));
    }

    public Integer getScore() {
        return strategyDefinition.getScore();
    }

    public BigDecimal getExpectedPercentage() {
        return BigDecimal.valueOf(scoreContext.getScore()).divide(BigDecimal.valueOf(getScore()), RoundingMode.UNNECESSARY);
    }

    public BigDecimal getActualPercentage() {
        return getTotalMarketValue().divide(scoreContext.getValue());
    }

    public BigDecimal getRentability() {
        return getProfit().divide(getTotalInvestValue());
    }

    public BigDecimal getProfit() {
        return getTotalMarketValue().subtract(getTotalInvestValue());
    }
}
