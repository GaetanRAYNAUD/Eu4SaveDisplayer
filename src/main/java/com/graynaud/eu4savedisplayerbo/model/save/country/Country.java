package com.graynaud.eu4savedisplayerbo.model.save.country;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Country {
    private String player;

    private Integer governmentRank;

    private String governmentName;

    private List<Boolean> continents;

    private List<Boolean> institutions;

    private Date goldenEraDate;

    private Integer capital;

    private Integer tradePort;

    private Double development; //raw_dev

    private List<Integer> color; //map_color

    private String primaryCulture;

    private List<String> acceptedCultures;

    private String religion;

    private String technologyGroup;

    private String unitType;

    private List<Integer> technologies;

    private List<Estate> estates;

    private List<String> rivals;

    private List<String> enemies;

    private List<String> activePolicies;

    private Double powerProjection;

    private Double greatPowerScore;

    private List<String> allies;

    private Double prestige;

    private Double stability;

    private Double treasury;

    private Double income; // estimated_monthly_income

    private Double inflation;

    private Double armyTradition;

    private Double navyTradition;

    private Integer debt;

    private Double corruption;

    private Double legitimacy;

    private Double mercantilism;

    private Double splendor;

    private Map<String, Integer> ideas;

    private Double armyProfessionalism;

    private Double maxManpower;

    private Double maxSailors;

    private List<Integer> losses;

    private Double innovativeness;

    private Double governmentReformProgress;

    public String getPlayer () {
        return player;
    }

    public void setPlayer (String player) {
        this.player = player;
    }

    public Integer getGovernmentRank () {
        return governmentRank;
    }

    public void setGovernmentRank (Integer governmentRank) {
        this.governmentRank = governmentRank;
    }

    public String getGovernmentName () {
        return governmentName;
    }

    public void setGovernmentName (String governmentName) {
        this.governmentName = governmentName;
    }

    public List<Boolean> getContinents () {
        return continents;
    }

    public void setContinents (List<Boolean> continents) {
        this.continents = continents;
    }

    public List<Boolean> getInstitutions () {
        return institutions;
    }

    public void setInstitutions (List<Boolean> institutions) {
        this.institutions = institutions;
    }

    public Date getGoldenEraDate () {
        return goldenEraDate;
    }

    public void setGoldenEraDate (Date goldenEraDate) {
        this.goldenEraDate = goldenEraDate;
    }

    public Integer getCapital () {
        return capital;
    }

    public void setCapital (Integer capital) {
        this.capital = capital;
    }

    public Integer getTradePort () {
        return tradePort;
    }

    public void setTradePort (Integer tradePort) {
        this.tradePort = tradePort;
    }

    public Double getDevelopment () {
        return development;
    }

    public void setDevelopment (Double development) {
        this.development = development;
    }

    public List<Integer> getColor () {
        return color;
    }

    public void setColor (List<Integer> color) {
        this.color = color;
    }

    public String getPrimaryCulture () {
        return primaryCulture;
    }

    public void setPrimaryCulture (String primaryCulture) {
        this.primaryCulture = primaryCulture;
    }

    public List<String> getAcceptedCultures () {
        return acceptedCultures;
    }

    public void setAcceptedCultures (List<String> acceptedCultures) {
        this.acceptedCultures = acceptedCultures;
    }

    public String getReligion () {
        return religion;
    }

    public void setReligion (String religion) {
        this.religion = religion;
    }

    public String getTechnologyGroup () {
        return technologyGroup;
    }

    public void setTechnologyGroup (String technologyGroup) {
        this.technologyGroup = technologyGroup;
    }

    public String getUnitType () {
        return unitType;
    }

    public void setUnitType (String unitType) {
        this.unitType = unitType;
    }

    public List<Integer> getTechnologies () {
        return technologies;
    }

    public void setTechnologies (List<Integer> technologies) {
        this.technologies = technologies;
    }

    public List<Estate> getEstates () {
        return estates;
    }

    public void setEstates (List<Estate> estates) {
        this.estates = estates;
    }

    public List<String> getRivals () {
        return rivals;
    }

    public void setRivals (List<String> rivals) {
        this.rivals = rivals;
    }

    public List<String> getEnemies () {
        return enemies;
    }

    public void setEnemies (List<String> enemies) {
        this.enemies = enemies;
    }

    public List<String> getActivePolicies () {
        return activePolicies;
    }

    public void setActivePolicies (List<String> activePolicies) {
        this.activePolicies = activePolicies;
    }

    public Double getPowerProjection () {
        return powerProjection;
    }

    public void setPowerProjection (Double powerProjection) {
        this.powerProjection = powerProjection;
    }

    public Double getGreatPowerScore () {
        return greatPowerScore;
    }

    public void setGreatPowerScore (Double greatPowerScore) {
        this.greatPowerScore = greatPowerScore;
    }

    public List<String> getAllies () {
        return allies;
    }

    public void setAllies (List<String> allies) {
        this.allies = allies;
    }

    public Double getPrestige () {
        return prestige;
    }

    public void setPrestige (Double prestige) {
        this.prestige = prestige;
    }

    public Double getStability () {
        return stability;
    }

    public void setStability (Double stability) {
        this.stability = stability;
    }

    public Double getTreasury () {
        return treasury;
    }

    public void setTreasury (Double treasury) {
        this.treasury = treasury;
    }

    public Double getIncome () {
        return income;
    }

    public void setIncome (Double income) {
        this.income = income;
    }

    public Double getInflation () {
        return inflation;
    }

    public void setInflation (Double inflation) {
        this.inflation = inflation;
    }

    public Double getArmyTradition () {
        return armyTradition;
    }

    public void setArmyTradition (Double armyTradition) {
        this.armyTradition = armyTradition;
    }

    public Double getNavyTradition () {
        return navyTradition;
    }

    public void setNavyTradition (Double navyTradition) {
        this.navyTradition = navyTradition;
    }

    public Integer getDebt () {
        return debt;
    }

    public void setDebt (Integer debt) {
        this.debt = debt;
    }

    public Double getCorruption () {
        return corruption;
    }

    public void setCorruption (Double corruption) {
        this.corruption = corruption;
    }

    public Double getLegitimacy () {
        return legitimacy;
    }

    public void setLegitimacy (Double legitimacy) {
        this.legitimacy = legitimacy;
    }

    public Double getMercantilism () {
        return mercantilism;
    }

    public void setMercantilism (Double mercantilism) {
        this.mercantilism = mercantilism;
    }

    public Double getSplendor () {
        return splendor;
    }

    public void setSplendor (Double splendor) {
        this.splendor = splendor;
    }

    public Map<String, Integer> getIdeas () {
        return ideas;
    }

    public void setIdeas (Map<String, Integer> ideas) {
        this.ideas = ideas;
    }

    public Double getArmyProfessionalism () {
        return armyProfessionalism;
    }

    public void setArmyProfessionalism (Double armyProfessionalism) {
        this.armyProfessionalism = armyProfessionalism;
    }

    public Double getMaxManpower () {
        return maxManpower;
    }

    public void setMaxManpower (Double maxManpower) {
        this.maxManpower = maxManpower;
    }

    public Double getMaxSailors () {
        return maxSailors;
    }

    public void setMaxSailors (Double maxSailors) {
        this.maxSailors = maxSailors;
    }

    public List<Integer> getLosses () {
        return losses;
    }

    public void setLosses (List<Integer> losses) {
        this.losses = losses;
    }

    public Double getInnovativeness () {
        return innovativeness;
    }

    public void setInnovativeness (Double innovativeness) {
        this.innovativeness = innovativeness;
    }

    public Double getGovernmentReformProgress () {
        return governmentReformProgress;
    }

    public void setGovernmentReformProgress (Double governmentReformProgress) {
        this.governmentReformProgress = governmentReformProgress;
    }

    @Override
    public String toString () {
        return "Country{" +
                "player='" + player + '\'' +
                ", governmentRank=" + governmentRank +
                ", governmentName='" + governmentName + '\'' +
                ", continents=" + continents +
                ", institutions=" + institutions +
                ", goldenEraDate=" + goldenEraDate +
                ", capital=" + capital +
                ", tradePort=" + tradePort +
                ", development=" + development +
                ", color=" + color +
                ", primaryCulture='" + primaryCulture + '\'' +
                ", acceptedCultures=" + acceptedCultures +
                ", religion='" + religion + '\'' +
                ", technologyGroup='" + technologyGroup + '\'' +
                ", unitType='" + unitType + '\'' +
                ", technologies=" + technologies +
                ", estates=" + estates +
                ", rivals=" + rivals +
                ", enemies=" + enemies +
                ", activePolicies=" + activePolicies +
                ", powerProjection=" + powerProjection +
                ", greatPowerScore=" + greatPowerScore +
                ", allies=" + allies +
                ", prestige=" + prestige +
                ", stability=" + stability +
                ", treasury=" + treasury +
                ", income=" + income +
                ", inflation=" + inflation +
                ", armyTradition=" + armyTradition +
                ", navyTradition=" + navyTradition +
                ", debt=" + debt +
                ", corruption=" + corruption +
                ", legitimacy=" + legitimacy +
                ", mercantilism=" + mercantilism +
                ", splendor=" + splendor +
                ", ideas=" + ideas +
                ", armyProfessionalism=" + armyProfessionalism +
                ", maxManpower=" + maxManpower +
                ", maxSailors=" + maxSailors +
                ", losses=" + losses +
                ", innovativeness=" + innovativeness +
                ", governmentReformProgress=" + governmentReformProgress +
                '}';
    }
}
