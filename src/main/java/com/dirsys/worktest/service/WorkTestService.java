package com.dirsys.worktest.service;

import com.dirsys.worktest.ResponseEntityModel;
import com.dirsys.worktest.model.*;
import com.dirsys.worktest.util.Queries;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class  WorkTestService {

    WebClient client;
    RestTemplate restTemplate;

    @Value("${url.population.landscape}")
    private String url_landscape;
    @Value("${url.population.country}")
    private String url_country;


    public WorkTestService() {
        client = WebClient.create();
        restTemplate = new RestTemplate();
    }

    public List<PercentageChangeModel> getPercentageChangeInPopulationBetweenSomeYears(){

        List<PercentageChangeModel> percentageChangeModelList = new ArrayList<>();

        //Get Sweden population between 2017 - 2019
        List<PopulationSwedenSingelYearModel> populationList = changeResponseEntityToList();

        //Create new that contains new attribute
        for (PopulationSwedenSingelYearModel populationSwedenSingelYearModel : populationList) {
            percentageChangeModelList.add(new PercentageChangeModel(populationSwedenSingelYearModel.getYear(), populationSwedenSingelYearModel.getPopulation(), "-"));
        }

        //Update List with values the percentage change in population between 2017 - 2019
        for (int i=1; i<populationList.size(); i++){
            percentageChangeModelList.get(i).setPercentage_change(new DecimalFormat("###.##").format(calculatePercentageChange(i, populationList))+"%");
        }

        return percentageChangeModelList;
    }


    //Make data easy to read
    private List<PopulationSwedenSingelYearModel> changeResponseEntityToList(){

        List<PopulationSwedenSeveralYearsModel> dataDList = getPopulationCountryBetweenSomeYears();

        List<PopulationSwedenSingelYearModel> populationList = new ArrayList<>();
        for (PopulationSwedenSeveralYearsModel populationSwedenSeveralYearsModel : dataDList) {
            populationList.add(new PopulationSwedenSingelYearModel(populationSwedenSeveralYearsModel.getKey()[0], populationSwedenSeveralYearsModel.getValues()[0]));

        }
        return populationList;
    }


    //Calculate the percentage change in population between 2017 - 2019 % = 100(final value - initial value)/initial value
    private double calculatePercentageChange(int i, List<PopulationSwedenSingelYearModel> list){
        return 100*(Double.parseDouble(list.get(i).getPopulation())-Double.parseDouble(list.get(i-1).getPopulation()))/Double.parseDouble(list.get(i-1).getPopulation());
    }


    //the population country between 2017 - 2019
    private List<PopulationSwedenSeveralYearsModel> getPopulationCountryBetweenSomeYears(){
        String [] years = {"2019", "2018", "2017"};
        return  responseEntity(url_country, buildPostRequestQuery(years, TypePopulation.COUNTRY));
    }

    private QueriesModel buildPostRequestQuery(String[] years, TypePopulation typePopulation){
        QueriesModel queriesModel =  new QueriesModel();

        if (typePopulation == TypePopulation.LANDSCAPE) {

            String[] landscapeCode = Queries.LandscapeCodeList();
            Arrays.sort(Queries.LandscapeCodeList());

            Query[] queries = {new Query(QueryCode.Region.toString(), new Selection("vs:ELandskap", landscapeCode)), new Query(QueryCode.Tid.toString(), new Selection("item", years))};

            queriesModel = new QueriesModel(queries, new QueryResponse("json"));

        }else if (typePopulation == TypePopulation.COUNTRY){
            Query[] queries = {new Query(QueryCode.ContentsCode.toString(), new Selection("item", new String[]{'\"'+"BE0101N1"+'\"'})), new Query(QueryCode.Tid.toString(), new Selection("item", years))};

            queriesModel = new QueriesModel(queries, new QueryResponse("json"));
        }
        return queriesModel;
    }

    public List<PopulationLandscapeYearModel> getPopulationPerLandscapeBetweenSomeYears(){
        String [] years = {"2017", "2018", "2019"};
        List<PopulationSwedenSeveralYearsModel> dataList = responseEntity(url_landscape, buildPostRequestQuery(years, TypePopulation.LANDSCAPE));

         Integer [] yearsWithDuplicates = new Integer[dataList.size()];

        for (int i = 0; i < dataList.size(); i++) {
            yearsWithDuplicates[i] = Integer.parseInt(dataList.get(i).getKey()[1]);
        }

        //Create set from array elements
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>( Arrays.asList(yearsWithDuplicates) );

        //Get back the array without duplicates
        Integer[] yearsWithoutDuplicates = linkedHashSet.toArray(new Integer[] {});

        List<PopulationLandscapeYearModel> populationLandscapeYearModelList = new ArrayList<>();

        for (Integer yearsWithoutDuplicate : yearsWithoutDuplicates) {

            //Filter list of code and population by year
            List<PopulationSwedenSeveralYearsModel> listFiltered = filterListByYear(dataList, yearsWithoutDuplicate.toString());

            //Customise the List of code and population
            List<SortPopulationModel> codeAndPopulationList = new ArrayList<>();

            for (PopulationSwedenSeveralYearsModel populationSwedenSeveralYearsModel : listFiltered) {
                codeAndPopulationList.add(new SortPopulationModel(populationSwedenSeveralYearsModel.getKey()[0], getLandscapeNameByLandscapeCode(populationSwedenSeveralYearsModel.getKey()[0]),Integer.parseInt(populationSwedenSeveralYearsModel.getValues()[0])));
            }

            populationLandscapeYearModelList.add(new PopulationLandscapeYearModel(yearsWithoutDuplicate.toString(), codeAndPopulationList));
        }


        return populationLandscapeYearModelList;
    }

    public List<PopulationPerLandscapeModel> populationPerLandscapeBetweenSomeYears() {
        String [] years = {"2017", "2018", "2019"};
        List<PopulationSwedenSeveralYearsModel> dataList = responseEntity(url_landscape, buildPostRequestQuery(years, TypePopulation.LANDSCAPE));
        Integer [] codeWithDuplicates = new Integer[dataList.size()];

        for (int i = 0; i < dataList.size(); i++) {
            codeWithDuplicates[i] = Integer.parseInt(dataList.get(i).getKey()[0]);
        }

        //Create set from array elements
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>( Arrays.asList(codeWithDuplicates) );

        //Get back the array without duplicates
        Integer[] codeWithoutDuplicates = linkedHashSet.toArray(new Integer[] {});


        List<PopulationPerLandscapeModel> populationPerLandscapeModelList = new ArrayList<>();



            for (Integer codeWithoutDuplicate : codeWithoutDuplicates) {

                List<PopulationSwedenSeveralYearsModel> populationByCodeList = filterListByLandscapeCode(dataList, codeWithoutDuplicate.toString());
                String [] populationPerLandscape = new String[populationByCodeList.size()];


                for (int i =0; i<populationByCodeList.size(); i++ ) {
                    populationPerLandscape[i] = populationByCodeList.get(i).getValues()[0];
                }

                populationPerLandscapeModelList.add(new PopulationPerLandscapeModel(codeWithoutDuplicate.toString(), getLandscapeNameByLandscapeCode(codeWithoutDuplicate.toString()), populationPerLandscape));
            }



        return populationPerLandscapeModelList;
    }


    private List<PopulationSwedenSeveralYearsModel> filterListByYear(List<PopulationSwedenSeveralYearsModel> list, String year){
        return list.stream()
                .filter(e -> e.getKey()[1].equals(year))
                .collect(Collectors.toList());
    }

    private List<PopulationSwedenSeveralYearsModel> filterListByLandscapeCode(List<PopulationSwedenSeveralYearsModel> list, String code){
        return list.stream()
                .filter(e -> e.getKey()[0].equals(code))
                .collect(Collectors.toList());
    }


    public List<SortPopulationModel> sortPopulationPerLandscapeForEachYear(String year){
        String []years = {year};
        //Get population per landscape for specific year
        List<PopulationSwedenSeveralYearsModel>  populationDataList = responseEntity(url_landscape, buildPostRequestQuery(years, TypePopulation.LANDSCAPE));

        //Convert To a new list
        List<SortPopulationModel> ListSorted = new ArrayList<>();
        for (PopulationSwedenSeveralYearsModel populationSwedenSeveralYearsModel : populationDataList) {
            ListSorted.add(new SortPopulationModel(populationSwedenSeveralYearsModel.getKey()[0], getLandscapeNameByLandscapeCode(populationSwedenSeveralYearsModel.getKey()[0]),Integer.parseInt(populationSwedenSeveralYearsModel.getValues()[0])));

        }

        //Sort by population per province for each year.
        Comparator<SortPopulationModel> compareByPopulation = Comparator.comparing(SortPopulationModel::getLandscape_population).reversed();
        ListSorted.sort(compareByPopulation);


        return ListSorted;
    }

    private List<PopulationSwedenSeveralYearsModel> responseEntity(String url, QueriesModel request) {

        ResponseEntityModel objectModel = client.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .syncBody(request.toString())
                .retrieve()
                .bodyToMono(ResponseEntityModel.class)
                .block();

        assert objectModel != null;
        return objectModel.getData().stream().toList();
    }

    private List<Landscape> getLandscapeNames(){
        String [] landscapeNames = Queries.LandscapeList();
        String []years = {"2017"};
        List<PopulationSwedenSeveralYearsModel>  populationDataList = responseEntity(url_landscape, buildPostRequestQuery(years, TypePopulation.LANDSCAPE));
        List<Landscape> landscapeNameList = new ArrayList<>();

        int index = 0;
        for (String name: landscapeNames) {
            landscapeNameList.add(new Landscape(populationDataList.get(index).getKey()[0],name));
            index++;
        }
        return landscapeNameList;
    }

    private String getLandscapeNameByLandscapeCode(String code){
       return getLandscapeNames().stream().filter(e -> e.getLandscape_code().equals(code))
                .collect(Collectors.toList()).get(0).getGetLandscape_name();
    }

}
