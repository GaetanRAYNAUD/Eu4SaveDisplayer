package com.graynaud.eu4savedisplayerbo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graynaud.eu4savedisplayerbo.model.save.Eu4Save;
import com.graynaud.eu4savedisplayerbo.model.save.general.*;
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

public class SaveReadUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveReadUtils.class);

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.M.d");

    public static byte[] saveFileToJson(MultipartFile file) throws IOException, ParseException {
        Eu4Save save = readSaveContent(new String(file.getBytes()));
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
        save.setEmpire(extractEmpire(data));

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
        data = data.substring(data.indexOf("\n") + 1);
        data = data.substring(data.indexOf("\n") + 1);
        data = StringUtils.chomp(data);
        data = data.substring(0, data.lastIndexOf("\n")).trim(); //Remove first line and last line because don't need it

        List<String> countryStatesData = Arrays.stream(data.replaceFirst("country_state=\\{", "").split("}\n\t\t\tcountry_state=\\{"))
                .filter(StringUtils::isNotBlank)
                .filter(s -> ! s.contains("investments={"))
                .map(String::trim)
                .collect(Collectors.toList());

        List<CountryState> countryStates = new ArrayList<>();
        countryStatesData.forEach(countryStateData -> {
            CountryState countryState = new CountryState();
            countryState.setProsperity(readSimpleDouble(getValueFromKey(countryStateData, "prosperity")));

            String country = countryStateData.indexOf("\n", countryStateData.indexOf("country=") + 8) <= 0 ? countryStateData.substring(
                    countryStateData.indexOf("country=") + 8) : getValueFromKey(countryStateData, "country");
            countryState.setCountry(country.replaceAll("\"", ""));

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
        int end = StringUtils.indexOf(data, "}\n}", start);
        String subData = StringUtils.substring(data, start, end).trim();

        List<String> greatPowersString = Arrays.stream(subData.split("}"))
                .filter(greatPowerString -> ! greatPowerString.contains("leaving="))
                .map(greatPowerString -> greatPowerString.trim().replaceAll("original=\\{", "").trim())
                .collect(Collectors.toList());

        return greatPowersString.stream().map(greatPowerString -> {
            GreatPower greatPower = new GreatPower();
            greatPower.setCountry(readSimpleString(getValueFromKey(greatPowerString, "country")));
            greatPower.setValue(readSimpleDouble(getValueFromKey(greatPowerString, "value")));
            return greatPower;
        }).collect(Collectors.toList());
    }

    private static Empire extractEmpire(String data) {
        int start = StringUtils.indexOf(data, "\nempire={") + 10;
        int end = StringUtils.indexOf(data, "}\n}\n", start);
        String subData = StringUtils.substring(data, start, end).trim();

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
        empire.setElectors(readSameLineListString(getObjectFromKey(subData, "electors")));
        empire.setOldEmperors(oldEmperors);

        return empire;
    }

    private static String getValueFromKey(String data, String key) {
        int start = StringUtils.indexOf(data, key + "=") + key.length() + 1;
        int end = StringUtils.indexOf(data, "\n", start);

        return StringUtils.substring(data, start, end).trim();
    }

    private static String getObjectFromKey(String data, String key) {
        int start = StringUtils.indexOf(data, key + "={") + key.length() + 2;
        int end = StringUtils.indexOf(data, "}", start);

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

    private static String readSimpleString(String data) {
        return RegExUtils.replaceAll(data.trim(), "\"", "").trim();
    }

    private static Date readSimpleDate(String data) throws ParseException {
        return DATE_FORMAT.parse(data.trim());
    }

    private static Boolean readSimpleBoolean(String data) {
        return "1".equalsIgnoreCase(data.trim()) || "yes".equalsIgnoreCase(data);
    }

    private static Integer readSimpleInteger(String data) {
        return new Integer(data.trim());
    }

    private static Double readSimpleDouble(String data) {
        return new Double(data.trim());
    }

    private static List<String> readListString(String data) {
        return Arrays.stream(data.trim().split("\n"))
                .filter(StringUtils::isNotBlank)
                .map(SaveReadUtils::readSimpleString)
                .collect(Collectors.toList());
    }

    private static List<String> readSameLineListString(String data) {
        return Arrays.stream(data.trim().split(" "))
                .filter(StringUtils::isNotBlank)
                .map(SaveReadUtils::readSimpleString)
                .collect(Collectors.toList());
    }

    private static List<Integer> readSameLineListInteger(String data) {
        return Arrays.stream(data.trim().split(" "))
                .filter(StringUtils::isNotBlank)
                .map(SaveReadUtils::readSimpleInteger)
                .collect(Collectors.toList());
    }

    private static List<Boolean> readSameLineListBoolean(String data) {
        return Arrays.stream(data.trim().split(" "))
                .filter(StringUtils::isNotBlank)
                .map(SaveReadUtils::readSimpleBoolean)
                .collect(Collectors.toList());
    }

    private static List<Double> readSameLineDouble(String data) {
        return Arrays.stream(data.trim().split(" "))
                .filter(StringUtils::isNotBlank)
                .map(SaveReadUtils::readSimpleDouble)
                .collect(Collectors.toList());
    }
}
