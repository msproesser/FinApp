package finapp.domain.investmentStrategy.commands;

import finapp.domain.investmentStrategy.models.CategoryDefinition;

public class CreateStrategyCategoryCommand {

    private final String id;
    private final Integer score;

    public CreateStrategyCategoryCommand(final String id, final Integer score) {
        this.id = id;
        this.score = score;
    }

    public CategoryDefinition toStrategyCategory() {
        return new CategoryDefinition(id, score);
    }
}
