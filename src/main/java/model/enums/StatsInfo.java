package model.enums;

public enum StatsInfo {

    /**
     * 
     */
    TOTALS("Totals"),

    /**
     * 
     */
    WINS("Wins"),

    /**
     * 
     */
    LOSS("Loss"),

    /**
     * 
     */
    RECORD("Record"),

    /**
     * 
     */
    WINS_RATE("Wins_rate"),

    /**
     * 
     */
    LOSS_RATE("Loss_rate");

    private String name;

    StatsInfo(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
