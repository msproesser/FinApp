package finapp.domain.investmentStrategy.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InvestmentCategory {

    private final CategoryDefinition categoryDefinition;
    private final Set<InvestmentStrategy> investmentStrategies;

    public InvestmentCategory(final CategoryDefinition categoryDefinition, final Set<InvestmentStrategy> investmentStrategies) {
        this.categoryDefinition = categoryDefinition;
        this.investmentStrategies = investmentStrategies;
    }

    public String getId() {
        return categoryDefinition.getId();
    }

    public Integer getScore() {
        return categoryDefinition.getScore();
    }

    public BigDecimal getTotalInvestValue() {
        return investmentStrategies.stream()
                .map(InvestmentStrategy::getTotalInvestValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalMarketValue() {
        return investmentStrategies.stream()
                .map(InvestmentStrategy::getTotalMarketValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Snapshot takeSnapshot() {
        Map<String, BigDecimal> snapshots = investmentStrategies.stream()
                .reduce(new HashMap<>(),
                        (snapshot, strategy) -> {
                            snapshot.put(
                                    strategy.getTicker(),
                                    strategy.getRentability());
                            return snapshot;
                        }, (a, b) -> {
                            a.putAll(b);
                            return a;
                        });
        return new Snapshot(LocalDate.now(), snapshots);
    }
}
