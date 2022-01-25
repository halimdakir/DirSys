package com.dirsys.worktest.util;

public class Queries {

    public static String queryToGetPopulationPerLandscapeForEachYear(String year){
        return  "{\n" +
                "\t\"query\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"code\": \"Region\",\n" +
                "\t\t\t\"selection\": {\n" +
                "\t\t\t\t\"filter\": \"vs:ELandskap\",\n" +
                "\t\t\t\t\"values\": [\n" +
                "\t\t\t\t\t\"101\",\n" +
                "\t\t\t\t\t\"102\",\n" +
                "\t\t\t\t\t\"103\",\n" +
                "\t\t\t\t\t\"104\",\n" +
                "\t\t\t\t\t\"105\",\n" +
                "\t\t\t\t\t\"106\",\n" +
                "\t\t\t\t\t\"107\",\n" +
                "\t\t\t\t\t\"108\",\n" +
                "\t\t\t\t\t\"109\",\n" +
                "\t\t\t\t\t\"110\",\n" +
                "\t\t\t\t\t\"211\",\n" +
                "\t\t\t\t\t\"212\",\n" +
                "\t\t\t\t\t\"213\",\n" +
                "\t\t\t\t\t\"214\",\n" +
                "\t\t\t\t\t\"215\",\n" +
                "\t\t\t\t\t\"217\",\n" +
                "\t\t\t\t\t\"316\",\n" +
                "\t\t\t\t\t\"318\",\n" +
                "\t\t\t\t\t\"319\",\n" +
                "\t\t\t\t\t\"320\",\n" +
                "\t\t\t\t\t\"321\",\n" +
                "\t\t\t\t\t\"322\",\n" +
                "\t\t\t\t\t\"323\",\n" +
                "\t\t\t\t\t\"324\",\n" +
                "\t\t\t\t\t\"325\",\n" +
                "\t\t\t\t\t\"999\"\n" +
                "\t\t\t\t]\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"code\": \"Tid\",\n" +
                "\t\t\t\"selection\": {\n" +
                "\t\t\t\t\"filter\": \"item\",\n" +
                "\t\t\t\t\"values\": [\n" +
                "\t\t\t\t\t\""+year+"\"\n" +
                "\t\t\t\t]\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"response\": {\n" +
                "\t\t\"format\": \"json\"\n" +
                "\t}\n" +
                "}";
    }
    public static String queryPopulationCountryForSomeYears(){
        return "\n" +
                "{\n" +
                "\"query\": [\n" +
                " {\n" +
                " \"code\": \"ContentsCode\",\n" +
                "  \"selection\": {\n" +
                "    \"filter\": \"item\",\n" +
                "    \"values\": [\n" +
                "      \"BE0101N1\"\n" +
                "    ]\n" +
                "   }\n" +
                "},\n" +
                "{\n" +
                "  \"code\": \"Tid\",\n" +
                "   \"selection\": {\n" +
                "   \"filter\": \"item\",\n" +
                "   \"values\": [\n" +
                "   \"2019\",\n" +
                "   \"2018\",\n" +
                "   \"2017\"\n" +
                "   ]\n" +
                "  }\n" +
                " }\n" +
                "],\n" +
                "\"response\": {\n" +
                "  \"format\": \"json\"\n" +
                " }\n" +
                "}";
    }

    public static String queryPopulationPerLandscapeBetweenSomeYears(){
        return "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:ELandskap\",\n" +
                "        \"values\": [\n" +
                "          \"101\",\n" +
                "          \"102\",\n" +
                "          \"103\",\n" +
                "          \"104\",\n" +
                "          \"105\",\n" +
                "          \"106\",\n" +
                "          \"107\",\n" +
                "          \"108\",\n" +
                "          \"109\",\n" +
                "          \"110\",\n" +
                "          \"211\",\n" +
                "          \"212\",\n" +
                "          \"213\",\n" +
                "          \"214\",\n" +
                "          \"215\",\n" +
                "          \"217\",\n" +
                "          \"316\",\n" +
                "          \"318\",\n" +
                "          \"319\",\n" +
                "          \"320\",\n" +
                "          \"321\",\n" +
                "          \"322\",\n" +
                "          \"323\",\n" +
                "          \"324\",\n" +
                "          \"325\",\n" +
                "          \"999\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Tid\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"2017\",\n" +
                "          \"2018\",\n" +
                "          \"2019\"\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"response\": {\n" +
                "    \"format\": \"json\"\n" +
                "  }\n" +
                "}";
    }

    public static String[] LandscapeList(){
        return new String[]{"Skåne","Blekinge","Öland","Halland","Småland","Gotland","Västergötland","Östergötland","Bohuslän","Dalsland","Närke","Södermanland","Värmland","Västmanland","Uppland","Dalarna","Gästrikland","Hälsingland","Härjedalen","Medelpad","Ångermanland","Jämtland",
                "Västerbotten","Lappland","Norrbotten","Unknown Landskap"};
    }
}
