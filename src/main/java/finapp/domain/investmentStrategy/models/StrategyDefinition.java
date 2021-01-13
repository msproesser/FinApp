package finapp.domain.investmentStrategy.models;

import finapp.domain.investment.models.Investment;

import java.math.BigDecimal;
import java.util.Objects;

public class StrategyDefinition {

    private final String id;
    private final String ticker;
    private final String category;
    private final Integer score;

    public StrategyDefinition(final String id, final String ticker, final String category, final Integer score) {
        this.id = id;
        this.ticker = ticker;
        this.category = category;
        this.score = score;
    }

    public String getCategory() {
        return category;
    }

    public String getTicker() {
        return ticker;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final StrategyDefinition that = (StrategyDefinition) o;
        return id.equals(that.id) && ticker.equals(that.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticker);
    }
}
