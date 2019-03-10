package com.graynaud.eu4savedisplayerbo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graynaud.eu4savedisplayerbo.model.save.Eu4Save;
import com.graynaud.eu4savedisplayerbo.model.save.country.Country;
import com.graynaud.eu4savedisplayerbo.model.save.general.*;
import com.graynaud.eu4savedisplayerbo.model.save.province.Building;
import com.graynaud.eu4savedisplayerbo.model.save.province.Province;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SaveReadUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveReadUtils.class);

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.M.d");

    public static byte[] saveFileToJson(MultipartFile file) throws IOException, ParseException {
        Eu4Save save = readSaveContent(new String(file.getBytes(), "Windows-1252"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        return objectMapper.writeValueAsBytes(save);
    }

    private static Eu4Save readSaveContent(String data) throws ParseException {
        Eu4Save save = new Eu4Save();
        save.setDate(extractSaveDate(data));
        save.setSaveGameVersion(extractGameVersion(data));
        save.setDlcEnabled(extractDLC(data));
        save.setMultiPlayer(extractMultiplayer(data));
        save.setStartDate(extractStartDate(data));
        save.setMapAreaData(extractMapAreaData(data));
        save.setInstitutions(extractInstitutions(data));
        save.setProductionsLeader(extractProductionsLeader(data));
        save.setGreatPowers(extractGreatPowers(data));
        save.setEmpire(extractEmpire(data, EmpireType.HRE));
        save.setCelestialEmpire(extractEmpire(data, EmpireType.CELESTIAL));
        save.setProvinces(extractProvinces(data));
        save.setCountries(extractCountries(data));

        return save;
    }

    private static Date extractSaveDate(String data) throws ParseException {
        return readSimpleDate(getValueFromKey(data, "\ndate"));
    }

    private static SaveGameVersion extractGameVersion(String data) {
        SaveGameVersion version = new SaveGameVersion();
        int start = StringUtils.indexOf(data, "\nsavegame_version=");
        int end = StringUtils.indexOf(data, "\n}\n", start);
        String subData = StringUtils.substring(data, start, end);

        version.setFirst(readSimpleInteger(getValueFromKey(subData, "first")));
        version.setSecond(readSimpleInteger(getValueFromKey(subData, "second")));
        version.setThird(readSimpleInteger(getValueFromKey(subData, "third")));
        version.setForth(readSimpleInteger(getValueFromKey(subData, "forth")));

        return version;
    }

    private static List<DLC> extractDLC(String data) {
        int start = StringUtils.indexOf(data, "\ndlc_enabled=") + 14;
        int end = StringUtils.indexOf(data, "\n}\n", start);
        String subData = StringUtils.substring(data, start, end);

        return readListString(subData).stream()
                .map(SaveReadUtils::readSimpleString)
                .map(DLC::getByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static Boolean extractMultiplayer(String data) {
        return readSimpleBoolean(getValueFromKey(data, "\nmulti_player"));
    }

    private static Date extractStartDate(String data) throws ParseException {
        return readSimpleDate(getValueFromKey(data, "\nstart_date"));
    }

    private static List<MapAreaData> extractMapAreaData(String data) {
        int start = StringUtils.indexOf(data, "\nmap_area_data") + 16;
        int end = StringUtils.indexOf(data, "\n}\n", start);
        String subData = StringUtils.substring(data, start, end);

        List<String> areasData = Arrays.stream(subData.trim().split("}\n\t}\n"))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());

        List<MapAreaData> mapAreasData = new ArrayList<>();
        areasData.forEach(areaData -> {
            MapAreaData mapAreaData = new MapAreaData();
            List<String> datas = Arrays.stream(areaData.split("=\\{", 2))
                    .map(String::trim)
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());

            if (datas.size() != 2) {
                LOGGER.error("Data for map area as not 2 values ! Go next ! ");
                LOGGER.error(areaData);
                return;
            }

            mapAreaData.setKey(datas.get(0));
            mapAreaData.setCountryStates(extractCountryStates(datas.get(1)));

            mapAreasData.add(mapAreaData);
        });

        return mapAreasData;
    }

    private static List<CountryState> extractCountryStates(String data) {
        List<String> countryStatesData = getListMatching(data, "country_state=\\{.*?}");

        List<CountryState> countryStates = new ArrayList<>();
        countryStatesData.forEach(countryStateData -> {
            CountryState countryState = new CountryState();
            countryState.setProsperity(readSimpleDouble(getValueFromKey(countryStateData, "prosperity")));

            String country = countryStateData.indexOf("\n", countryStateData.indexOf("country=") + 8) <= 0 ? countryStateData.substring(
                    countryStateData.indexOf("country=") + 8) : getValueFromKey(countryStateData, "country");
            countryState.setCountry(readSimpleString(country));

            countryStates.add(countryState);
        });

        return countryStates;
    }

    private static List<Institution> extractInstitutions(String data) {
        int start = StringUtils.indexOf(data, "\ninstitution_origin={") + 22;
        int end = StringUtils.indexOf(data, "}\ntrade={", start);
        String subData = StringUtils.substring(data, start, end).trim();

        List<String> datas = Arrays.stream(subData.split("}"))
                .map(String::trim)
                .map(s -> s.replaceAll("[^0-9 .]", ""))
                .collect(Collectors.toList());

        List<List<String>> infos = datas.stream()
                .map(SaveReadUtils::readSameLineListString)
                .collect(Collectors.toList());

        List<Institution> institutions = new ArrayList<>();

        for (int i = 0; i < infos.get(0).size(); i++) {
            Institution institution = new Institution();
            institution.setOrigin(readSimpleInteger(infos.get(0).get(i)));
            institution.setEnabled(readSimpleBoolean(infos.get(1).get(i)));
            institution.setPenalties(readSimpleDouble(infos.get(2).get(i)));

            institutions.add(institution);
        }

        return institutions;
    }

    private static List<String> extractProductionsLeader(String data) {
        return readSameLineListString(getObjectFromKey(data, "production_leader_tag"));
    }

    private static List<GreatPower> extractGreatPowers(String data) {
        int start = StringUtils.indexOf(data, "\ngreat_powers={") + 16;
        int end = StringUtils.indexOf(data, "\n}\n", start);
        String subData = StringUtils.substring(data, start, end).trim();

        List<String> greatPowersString = getListMatching(subData, "original=\\{.*?}");

        return greatPowersString.stream().map(greatPowerString -> {
            GreatPower greatPower = new GreatPower();
            greatPower.setCountry(readSimpleString(getValueFromKey(greatPowerString, "country")));
            greatPower.setValue(readSimpleDouble(getValueFromKey(greatPowerString, "value")));
            return greatPower;
        }).collect(Collectors.toList());
    }

    private static Empire extractEmpire(String data, EmpireType empireType) {
        int start = StringUtils.indexOf(data, empireType.getKey()) + empireType.getKey().length() + 1;
        int end = StringUtils.indexOf(data, "}\n}\n", start);
        String subData = StringUtils.substring(data, start, end).trim();

        if (! subData.contains("emperor=\"")) {
            return null; //No emperor means no empire
        }

        List<String> oldEmperorsString = getListMatching(subData, "old_emperor=\\{.*?}");
        SortedSet<OldEmperor> oldEmperors = oldEmperorsString.stream()
                .map(oldEmperorString -> {
                    try {
                        OldEmperor oldEmperor = new OldEmperor();
                        oldEmperor.setCountry(readSimpleString(getValueFromKey(oldEmperorString, "country")));
                        oldEmperor.setDate(readSimpleDate(getValueFromKey(oldEmperorString, "date")));
                        return oldEmperor;
                    } catch (ParseException e) {
                        LOGGER.error("Error while reading date for old empere: {}", oldEmperorsString, e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(TreeSet::new));

        Empire empire = new Empire();
        empire.setEmperor(readSimpleString(getValueFromKey(subData, "emperor")));
        empire.setImperialInfluence(readSimpleDouble(getValueFromKey(subData, "imperial_influence")));
        empire.setReformLevel(readSimpleInteger(getValueFromKey(subData, "reform_level")));
        empire.setOldEmperors(oldEmperors);

        if (empireType.isHasElectors()) {
            empire.setElectors(readSameLineListString(getObjectFromKey(subData, "electors")));
        }

        return empire;
    }

    private static List<Province> extractProvinces(String data) {
        int start = StringUtils.indexOf(data, "\nprovinces={") + 13;
        int end = StringUtils.indexOf(data, "\n}\n", start);
        String subData = StringUtils.substring(data, start, end).trim();

        List<String> provincesString = getListMatching(subData, "-\\d+=\\{.*?\n\t}");
        List<Province> provinces = new ArrayList<>();
        provincesString.forEach(provinceString -> {
            Province province = new Province();
            province.setId(readSimpleInteger(provinceString.substring(1, provinceString.indexOf("="))));
            province.setName(readSimpleString(getValueFromKey(provinceString, "name")));
            province.setOwner(readSimpleString(getValueFromKey(provinceString, "owner")));
            province.setController(readSimpleString(getValueFromKey(provinceString, "controller")));
            province.setInstitutions(readSameLineListDouble(getObjectFromKey(provinceString, "institutions")));
            province.setEstate(readSimpleInteger(getValueFromKey(provinceString, "\n\t\testate")));
            province.setCores(readListString(getObjectFromKey(provinceString, "cores")));
            province.setCulture(readSimpleString(getValueFromKey(provinceString, "culture")));
            province.setReligion(readSimpleString(getValueFromKey(provinceString, "religion")));
            province.setBaseTax(readSimpleDouble(getValueFromKey(provinceString, "base_tax")));
            province.setBaseProduction(readSimpleDouble(getValueFromKey(provinceString, "base_production")));
            province.setBaseManpower(readSimpleDouble(getValueFromKey(provinceString, "base_manpower")));
            province.setTradeGood(readSimpleString(getValueFromKey(provinceString, "\n\t\ttrade_goods")));
            province.setLocalAutonomy(readSimpleDouble(getValueFromKey(provinceString, "\n\t\tlocal_autonomy")));
            province.setBuildings(extractBuildings(provinceString));
            province.setTradePower(readSimpleDouble(getValueFromKey(provinceString, "\n\t\ttrade_power")));
            province.setCenterOfTrade(readSimpleInteger(getValueFromKey(provinceString, "\n\t\tcenter_of_trade")));
            province.setHre(readSimpleBoolean(getValueFromKey(provinceString, "\n\t\thre")));


            provinces.add(province);
        });

        return provinces;
    }

    private static List<Building> extractBuildings(String data) {
        String subData = getObjectFromKey(data, "building_builders");

        if (subData == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(subData.split("\n"))
                .map(String::trim)
                .map(buildingData -> {
                    List<String> datas = Arrays.stream(buildingData.split("="))
                            .map(String::trim)
                            .collect(Collectors.toList());

                    if (datas.size() != 2) {
                        LOGGER.error("Can't parse building data: {}", buildingData);
                        return null;
                    }

                    Building building = new Building();
                    building.setName(datas.get(0));
                    building.setBuilder(readSimpleString(datas.get(1)));
                    return building;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static List<Country> extractCountries(String data) {
        int start = StringUtils.indexOf(data, "\ncountries={") + 13;
        int end = StringUtils.indexOf(data, "\n}\nactive_advisors", start);
        String subData = StringUtils.substring(data, start, end).trim();

        int startPlayers = StringUtils.indexOf(data, "\nplayers_countries={") + 21;
        int endPlayers = StringUtils.indexOf(data, "\n}\n", startPlayers);
        String subDataPlayers = StringUtils.substring(data, startPlayers, endPlayers).trim();

        List<String> playersData = Arrays.stream(subDataPlayers.split("\n"))
                .map(SaveReadUtils::readSimpleString)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());

        List<String> countriesString = getListMatching(subData, "\n\t[A-Z]{3}=\\{.*?\n\t}");
        countriesString.subList(0, 3).clear(); //Remove 3 first countries: rebels, pirates, natives

        List<Country> countries = new ArrayList<>();
        countriesString.forEach(countryString -> {
            Double dev = readSimpleDouble(getValueFromKey(countryString, "\n\t\traw_development"));

            if (dev == null) { // no dev means country does not exist
                return;
            }

            Country country = new Country();
            country.setTag(readSimpleString(countryString.substring(0, 3)));
            country.setPlayer(extractPlayer(playersData, country.getTag()));
            country.setGovernmentRank(readSimpleInteger(getValueFromKey(countryString, "\n\t\tgovernment_rank")));
            country.setGovernmentName(readSimpleString(getValueFromKey(countryString, "\n\t\tgovernment_name")));
            country.setContinents(readSameLineListBoolean(getObjectFromKey(countryString, "\n\t\tcontinent")));
            country.setInstitutions(readSameLineListBoolean(getObjectFromKey(countryString, "\n\t\tinstitutions")));
            country.setCapital(readSimpleInteger(getValueFromKey(countryString, "\n\t\tcapital")));
            country.setTradePort(readSimpleInteger(getValueFromKey(countryString, "\n\t\ttrade_port")));
            country.setDevelopment(dev);
            country.setColor(readSameLineListInteger(getObjectFromKey(countryString, "map_color")));
            country.setPrimaryCulture(readSimpleString(getValueFromKey(countryString, "\n\t\tprimary_culture")));
            country.setAcceptedCultures(getListValuesFromKey(countryString, "\n\t\taccepted_culture", "Can't parse accepted culture: {}"));
            country.setReligion(readSimpleString(getValueFromKey(countryString, "\n\t\treligion")));
            country.setTechnologyGroup(readSimpleString(getValueFromKey(countryString, "\n\t\ttechnology_group")));
            country.setUnitType(readSimpleString(getValueFromKey(countryString, "\n\t\tunit_type")));
            country.setTechnologies(extractTechnologies(countryString));
            country.setRivals(getListOfValuesInObject(countryString, "\n\t\trival", "country"));
            country.setEnemies(getListValuesFromKey(countryString, "\n\t\tenemy", "Can't parse enemy: {}"));
            country.setActivePolicies(getListOfValuesInObject(countryString, "\n\t\tactive_policy", "\tpolicy"));
            country.setPowerProjection(readSimpleDouble(getValueFromKey(countryString, "\n\t\tcurrent_power_projection")));
            country.setGreatPowerScore(readSimpleDouble(getValueFromKey(countryString, "\n\t\tgreat_power_score")));
            country.setAllies(readListString(getObjectFromKey(countryString, "allies")));
            country.setPrestige(readSimpleDouble(getValueFromKey(countryString, "\n\t\tprestige")));
            country.setStability(readSimpleDouble(getValueFromKey(countryString, "\n\t\tstability")));
            country.setTreasury(readSimpleDouble(getValueFromKey(countryString, "\n\t\ttreasury")));
            country.setIncome(readSimpleDouble(getValueFromKey(countryString, "\n\t\testimated_monthly_income")));
            country.setInflation(readSimpleDouble(getValueFromKey(countryString, "\n\t\tinflation")));
            country.setArmyTradition(readSimpleDouble(getValueFromKey(countryString, "\n\t\tarmy_tradition")));
            country.setNavyTradition(readSimpleDouble(getValueFromKey(countryString, "\n\t\tnavy_tradition")));
            country.setDebt(extractDebt(countryString));
            country.setCorruption(readSimpleDouble(getValueFromKey(countryString, "\n\t\tcorruption")));
            country.setLegitimacy(readSimpleDouble(getValueFromKey(countryString, "\n\t\tlegitimacy")));
            country.setMercantilism(readSimpleDouble(getValueFromKey(countryString, "\n\t\tmercantilism")));
            country.setSplendor(readSimpleDouble(getValueFromKey(countryString, "\n\t\tsplendor")));
            country.setIdeas(extractIdeas(countryString));
            country.setArmyProfessionalism(readSimpleDouble(getValueFromKey(countryString, "\n\t\tarmy_professionalism")));
            country.setMaxManpower(readSimpleDouble(getValueFromKey(countryString, "\n\t\tmax_manpower")));
            country.setMaxSailors(readSimpleDouble(getValueFromKey(countryString, "\n\t\tmax_sailors")));
            country.setLosses(readSameLineListInteger(getObjectFromKey(countryString, "\n\t\t\tmembers")));
            country.setInnovativeness(readSimpleDouble(getValueFromKey(countryString, "\n\t\tinnovativeness")));
            country.setGovernmentReformProgress(readSimpleDouble(getValueFromKey(countryString, "\n\t\tgovernment_reform_progress")));

            try {
                country.setGoldenEraDate(readSimpleDate(getValueFromKey(countryString, "\n\t\tgolden_era_date")));
            } catch (ParseException e) {
                LOGGER.error("Can't parse goldenEra date for: {}", country.getTag());
            }

            countries.add(country);
        });

        return countries;
    }

    private static String extractPlayer(List<String> playersData, String tag) {
        int index = playersData.lastIndexOf(tag);

        if (index < 0) {
            return null;
        }

        return playersData.get(index - 1);
    }

    private static List<Integer> extractTechnologies(String data) {
        String subData = getObjectFromKey(data, "technology");

        if (StringUtils.isBlank(subData)) {
            return new ArrayList<>();
        }

        return getListOfValues(subData, "Can't parse technology data: {}")
                .map(SaveReadUtils::readSimpleInteger)
                .collect(Collectors.toList());
    }

    private static Integer extractDebt (String data) {
        List<String> loans = getListMatching(data, "\n\t\tloan=\\{.*?\n\t\t}");

        if (loans == null || loans.isEmpty()) {
            return 0;
        }

        List<String> amounts = loans.stream()
                                      .map(match -> readSimpleString(getValueFromKey(match, "\tamount")))
                                      .collect(Collectors.toList());

        return amounts.stream()
                      .map(SaveReadUtils::readSimpleInteger)
                      .filter(Objects::nonNull)
                      .mapToInt(Integer::intValue)
                      .sum();
    }

    private static Map<String, Integer> extractIdeas(String data) {
        String subData = getObjectFromKey(data, "active_idea_groups");

        if (subData == null) {
            return null;
        }

        Map<String, Integer> ideas = new LinkedHashMap<>();
        Arrays.stream(subData.split("\n"))
                .map(String::trim)
                .forEach(ideaData -> {
                    List<String> datas = Arrays.stream(ideaData.split("="))
                            .map(String::trim)
                            .collect(Collectors.toList());

                    if (datas.size() != 2) {
                        LOGGER.error("Can't parse ideas data: {}", ideaData);
                        return;
                    }

                    ideas.put(datas.get(0), readSimpleInteger(datas.get(1)));
                });

        return ideas;
    }

    private static List<String> getListOfValuesInObject(String data, String key, String field) {
        List<String> matches = getListMatching(data, key + "=\\{.*?}");

        if (matches == null || matches.isEmpty()) {
            return null;
        }

        return matches.stream()
                .map(match -> readSimpleString(getValueFromKey(match, field)))
                .collect(Collectors.toList());
    }

    private static Stream<String> getListOfValues(String data, String message) {
        return Arrays.stream(data.split("\n"))
                .map(String::trim)
                .map(subData -> {
                    List<String> datas = Arrays.stream(subData.split("="))
                            .map(String::trim)
                            .collect(Collectors.toList());

                    if (datas.size() != 2) {
                        LOGGER.error(message, subData);
                        return null;
                    }

                    return readSimpleString(datas.get(1));
                })
                .filter(StringUtils::isNotBlank);
    }

    private static List<String> getListValuesFromKey(String data, String key, String message) {
        List<String> subData = getListMatchingSingleLine(data, key + "=.*");

        if (subData == null || subData.isEmpty()) {
            return null;
        }

        return subData.stream()
                .map(infos -> {
                    List<String> datas = Arrays.stream(infos.split("="))
                            .map(String::trim)
                            .collect(Collectors.toList());

                    if (datas.size() != 2) {
                        LOGGER.error(message, subData);
                        return null;
                    }

                    return readSimpleString(datas.get(1));
                })
                .collect(Collectors.toList());
    }

    private static String getValueFromKey(String data, String key) {
        int start = StringUtils.indexOf(data, key + "=") + key.length() + 1;
        int end = StringUtils.indexOf(data, "\n", start);

        if (start < key.length() + 1 || end < key.length() + 1) {
            return null;
        }

        return StringUtils.substring(data, start, end).trim();
    }

    private static String getObjectFromKey(String data, String key) {
        int start = StringUtils.indexOf(data, key + "={") + key.length() + 2;
        int end = StringUtils.indexOf(data, "}", start);

        if (start < key.length() + 2 || end < key.length() + 2) {
            return null;
        }

        return StringUtils.substring(data, start, end).trim();
    }

    private static List<String> getListMatching(String data, String regex) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex, Pattern.DOTALL).matcher(data);

        while (matcher.find()) {
            matches.add(matcher.group().trim());
        }

        return matches;
    }

    private static List<String> getListMatchingSingleLine(String data, String regex) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(data);

        while (matcher.find()) {
            matches.add(matcher.group().trim());
        }

        return matches;
    }

    private static String readSimpleString(String data) {
        if (StringUtils.isBlank(data)) {
            return null;
        }

        return RegExUtils.replaceAll(data.trim(), "\"", "").trim();
    }

    private static Date readSimpleDate(String data) throws ParseException {
        if (StringUtils.isBlank(data)) {
            return null;
        }

        return DATE_FORMAT.parse(data.trim());
    }

    private static Boolean readSimpleBoolean(String data) {
        if (StringUtils.isBlank(data)) {
            return null;
        }

        return "1".equalsIgnoreCase(data.trim()) || "yes".equalsIgnoreCase(data);
    }

    private static Integer readSimpleInteger(String data) {
        if (StringUtils.isBlank(data)) {
            return null;
        }

        return new Integer(data.trim());
    }

    private static Double readSimpleDouble(String data) {
        if (StringUtils.isBlank(data)) {
            return null;
        }

        return new Double(data.trim());
    }

    private static List<String> readListString(String data) {
        if (data == null) {
            return null;
        }

        return Arrays.stream(data.trim().split("\n"))
                .filter(StringUtils::isNotBlank)
                .map(SaveReadUtils::readSimpleString)
                .collect(Collectors.toList());
    }

    private static List<String> readSameLineListString(String data) {
        if (data == null) {
            return null;
        }

        return Arrays.stream(data.trim().split(" "))
                .filter(StringUtils::isNotBlank)
                .map(SaveReadUtils::readSimpleString)
                .collect(Collectors.toList());
    }

    private static List<Integer> readSameLineListInteger(String data) {
        if (data == null) {
            return null;
        }

        return Arrays.stream(data.trim().split(" "))
                .filter(StringUtils::isNotBlank)
                .map(SaveReadUtils::readSimpleInteger)
                .collect(Collectors.toList());
    }

    private static List<Boolean> readSameLineListBoolean(String data) {
        if (data == null) {
            return null;
        }

        return Arrays.stream(data.trim().split(" "))
                .filter(StringUtils::isNotBlank)
                .map(SaveReadUtils::readSimpleBoolean)
                .collect(Collectors.toList());
    }

    private static List<Double> readSameLineListDouble(String data) {
        if (data == null) {
            return null;
        }

        return Arrays.stream(data.trim().split(" "))
                .filter(StringUtils::isNotBlank)
                .map(SaveReadUtils::readSimpleDouble)
                .collect(Collectors.toList());
    }
}
