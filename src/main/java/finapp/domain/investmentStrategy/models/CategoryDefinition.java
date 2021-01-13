package finapp.domain.investmentStrategy.models;

public class CategoryDefinition {

    private final String id;
    private final Integer score;

    public CategoryDefinition(final String id, final Integer score) {
        this.id = id;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }
}
