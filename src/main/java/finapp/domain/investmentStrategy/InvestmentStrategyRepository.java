package finapp.domain.investmentStrategy;

import finapp.domain.investmentStrategy.models.StrategyDefinition;
import finapp.domain.investmentStrategy.models.CategoryDefinition;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface InvestmentStrategyRepository {

    CompletableFuture<Void> createStrategyDefinition(StrategyDefinition strategyDefinition);

    CompletableFuture<Void> createCategoryDefinition(CategoryDefinition categoryDefinition);

    CompletableFuture<Set<CategoryDefinition>> getCategoryDefinitions();

    CompletableFuture<Set<StrategyDefinition>> getStrategyDefinitions();
}
