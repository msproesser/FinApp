package finapp.domain.investmentStrategy.models;

import java.math.BigDecimal;

public class ScoreContext {

    private Integer score = 0;
    private BigDecimal value = BigDecimal.ZERO;
    private final TickerPriceProvider priceProvider;

    public ScoreContext(final TickerPriceProvider priceProvider) {
        this.priceProvider = priceProvider;
    }

    public Integer getScore() {
        return score;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getMarketValueOf(String ticker) {
        return priceProvider.getMarketPriceOf(ticker);
    }

    public ScoreContext add(InvestmentStrategy investmentStrategy) {
        this.score = score + investmentStrategy.getScore();
        this.value = value.add(investmentStrategy.getTotalInvestValue());
        return this;
    }
}
