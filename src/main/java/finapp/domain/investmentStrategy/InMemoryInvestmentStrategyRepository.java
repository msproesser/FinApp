package finapp.domain.investmentStrategy;

import finapp.domain.investmentStrategy.models.CategoryDefinition;
import finapp.domain.investmentStrategy.models.StrategyDefinition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class InMemoryInvestmentStrategyRepository implements InvestmentStrategyRepository {

    private final Set<StrategyDefinition> strategies = new HashSet<>();
    private final Map<String, CategoryDefinition> categories = new HashMap<>();

    @Override
    public CompletableFuture<Void> createStrategyDefinition(final StrategyDefinition strategyDefinition) {
        strategies.add(strategyDefinition);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> createCategoryDefinition(final CategoryDefinition categoryDefinition) {
        categories.put(categoryDefinition.getId(), categoryDefinition);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Set<CategoryDefinition>> getCategoryDefinitions() {
        return CompletableFuture.completedFuture(new HashSet<>(categories.values()));
    }

    @Override
    public CompletableFuture<Set<StrategyDefinition>> getStrategyDefinitions() {
        return CompletableFuture.completedFuture(strategies);
    }
}
