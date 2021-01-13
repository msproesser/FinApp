package finapp.domain.investmentStrategy.models;

import finapp.domain.investment.models.Investment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MacroStrategyBuilder {

    private final Set<CategoryDefinition> categories = new HashSet<>();
    private final Set<StrategyDefinition> strategies = new HashSet<>();
    private final Map<String, Investment> investments = new HashMap<>();
    private TickerPriceProvider priceProvider;


    public MacroStrategyBuilder withCategoryDefinitions(Set<CategoryDefinition> categories) {
        this.categories.addAll(categories);
        return this;
    }

    public MacroStrategyBuilder withStrategyDefinitions(Set<StrategyDefinition> strategies) {
        this.strategies.addAll(strategies);
        return this;
    }

    public MacroStrategyBuilder withInvestments(Set<Investment> investments) {
        investments.forEach(investment -> this.investments.put(investment.getTicker(), investment));
        return this;
    }

    public MacroStrategyBuilder withTickerPriceProvider(TickerPriceProvider priceProvider) {
        this.priceProvider = priceProvider;
        return this;
    }

    public MacroStrategy build() {
        ScoreContext scoreContext = new ScoreContext(priceProvider);
        Set<InvestmentStrategy> investmentStrategies = getInvestmentStrategies(scoreContext);
        investmentStrategies.forEach(scoreContext::add);
        Set<InvestmentCategory> categories = this.categories.stream()
                .map(categoryDefinition -> new InvestmentCategory(categoryDefinition, filterByCategory(investmentStrategies, categoryDefinition.getId())))
                .collect(Collectors.toSet());
        return new MacroStrategy(categories);
    }

    private Set<InvestmentStrategy> filterByCategory(final Set<InvestmentStrategy> investmentStrategies, final String category) {
        return investmentStrategies.stream()
                .filter(investmentStrategy -> investmentStrategy.getCategory().equals(category))
                .collect(Collectors.toSet());
    }

    private Set<InvestmentStrategy> getInvestmentStrategies(final ScoreContext scoreContext) {
        return strategies.stream().map(strategyDefinition -> {
            Investment investment = investments.getOrDefault(strategyDefinition.getTicker(), new Investment(strategyDefinition.getTicker()));
            return new InvestmentStrategy(strategyDefinition, investment, scoreContext);
        }).collect(Collectors.toSet());
    }
}
