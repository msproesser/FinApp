package finapp.domain.investmentStrategy.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MacroStrategy {

    private final Map<String, InvestmentCategory> categories = new HashMap<>();

    public MacroStrategy(final Set<InvestmentCategory> investmentCategories) {
        investmentCategories.forEach(category -> categories.put(category.getId(), category));
    }

    public Snapshot takeSnapshot() {
        Snapshot snapshot = new Snapshot(LocalDate.now(), new HashMap<>());
        return categories.values().stream()
                .map(InvestmentCategory::takeSnapshot)
                .reduce(snapshot, Snapshot::merge);
    }

    private Integer getScoreTotal() {
        return categories.values().stream()
                .map(InvestmentCategory::getScore)
                .reduce(0, Integer::sum);
    }

    private BigDecimal getTotalMarketValue() {
        return categories.values().stream()
                .map(InvestmentCategory::getTotalMarketValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalInvestValue() {
        return categories.values().stream()
                .map(InvestmentCategory::getTotalInvestValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
