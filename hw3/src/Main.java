import java.sql.*;
import java.util.*;

/*
        * The program should produce a list of places within the given distance of the specified zip code along
        * with the total population and the total number of housing units.
        * For example, if given a zip code of 64507 and a distance of 300,
        * the program would give a list of all places within 300 miles of St. Joseph.
        *
        * Each place should be listed only once, even if it has several zip codes.
        * For each place be sure to list the place name, state, the total population,
        * and the total number of housing units.
        * It should also list the distance from the source in both kilometers and miles.
        * If there are several zip codes for a place, use any of the distances you calculated.
        *
        * Obviously this program is going to miss places outside the US if you give a latitude and longitude near a US border.
        *
        * */

/*
* To produce this ArrayList I need to calculate distance between two points?
*
* Yes, I need to go through each zipCode and calculate the distance it is from the input code.
*
* If it is less than the input radius add that city (cityName, total population, total housing units) to the List
*
*
*
* */
public class Main {

    static Connection conn;
    static ResultSet rs1;
    static ResultSet rs;
    static Statement stmt1;
    static Statement stmt;

    public static void main(String[] args) {
//       Kansas City 39.0997° N, 94.5786° W
//        String zipCodeForTesting = "64030";// zipCodeForTesting = Grandview, mo zip

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a zip code: ");
        String zipFromUser = input.nextLine();
        System.out.println("Please enter a distance: ");
        double distanceFromUser = input.nextDouble();

        String originQueryString = "select city, lat, zips2.long from zips2 where zipcode = '"+zipFromUser+"'";




//        System.out.println("Here's rosetta: " + haversine(38.39, -94.35));
//        System.out.println("Here's rosetta: "+haversine(39.1, 94.58, 38.39, 94.35));


        String host = "jdbc:mysql://turing.cs.missouriwestern.edu:3306/misc";
        String user = "csc254";
        String password = "age126";

//        String queryStringForAllCities = "select city, sum(estimatedpopulation) AS totalPopulation, sum(taxreturnsfiled) AS households from zips2 where locationtype = 'PRIMARY' GROUP BY city, state";
        String queryStringForAllCities = "select zipcode, lat, zips2.long, city, state, sum(estimatedpopulation) AS totalPopulation, sum(taxreturnsfiled) AS households from zips2 where zipcode like '" + 64 + "%%%' and locationType = 'PRIMARY' GROUP BY city, state";
//        String queryStringForAllCities = "SELECT city, estimatedpopulation AS population, taxreturnsfiled AS numberOfHouseholds, zipcode, zips2.long, lat FROM zips2 WHERE LocationType = 'PRIMARY'";
//        String queryStringForAllCities = "SELECT city, estimatedpopulation AS population, taxreturnsfiled AS numberOfHouseholds, zipcode, zips2.long, lat FROM zips2 WHERE zipcode LIKE '" + 6450 + "%' AND LocationType = 'PRIMARY'";
        //        String queryStringForAllCities = "SELECT city, zip_code, lon, lat FROM zips";
//        String queryStringForAllCities = "SELECT city, estimatedpopulation AS population, taxreturnsfiled AS numberOfHouseholds, zipcode, zips2.long, lat FROM zips2 WHERE zipcode LIKE '" + 6450 + "%' AND LocationType = 'PRIMARY'";
//        String queryStringForAllCities = "SELECT city, estimatedpopulation AS population, taxreturnsfiled AS numberOfHouseholds, zipcode, zips2.long,  lat FROM zips2 WHERE LocationType = 'PRIMARY'";
//        String queryStringForAllCities = "SELECT city, zipcode, zips2.long, lat FROM zips2 WHERE zipcode LIKE '" + 6450 + "%' AND LocationType = '"PRIMARY"'";
//        String queryStringForAllCities = "SELECT city, zip_code, lon, lat FROM zips WHERE zip_code LIKE '" + 6450 + "%'";
//        String queryStringForAllCities = "SELECT city, zip_code, lon, lat FROM zips WHERE zip_code LIKE 6450%";

        try {
            conn = DriverManager.getConnection(host, user, password);

            if (conn == null) {
                System.out.println("Connection failed.");
            } else {
                System.out.println("We're connected!");

                stmt1 = conn.createStatement();
                stmt = conn.createStatement();
                rs1 = stmt1.executeQuery(originQueryString);
                rs = stmt.executeQuery(queryStringForAllCities);


                ResultSetMetaData rsMetaData = rs.getMetaData();
                int numberOfColumns = rsMetaData.getColumnCount();

//                System.out.println("Number of colums: " + numberOfColumns);
//
//                for (int i = 1; i <= numberOfColumns; i++) {
//                    System.out.printf("Column %2d: %s (%s)\n", i,
//                            rsMetaData.getColumnName(i),
//                            rsMetaData.getColumnTypeName(i));
//                }
                /*
                * Number of colums: 4
                * Column  1: city (VARCHAR)
                * Column  2: zip_code (VARCHAR)
                * Column  3: lon (FLOAT)
                * Column  4: lat (FLOAT)
                * */

/* equals method should be both city and state */
/*
* ArrayList<String> hotelResultList = new ArrayList<>(columnCount);
while (results.next()) {
   int i = 1;
   while(i <= columnCount) {
        hotelResultList.add(results.getString(i++));
   }
}
* */

//while (rs1.nex)

                String originCityname = "";
                double originLat = 0;
                double originLong = 0;

                while (rs1.next()){
                    originCityname = rs1.getString("city");
                    originLat = rs1.getDouble("lat");
                    originLong = rs1.getDouble("zips2.long");
                }


                ArrayList<String> resultsInSet = new ArrayList<>();
                System.out.printf("%-20s %-10s %-10s %-20s %-20s %-10s %n", "City", "State", "Total Pop", "Total Households", "Distance in KM", "Distance in MI");
                while (rs.next()) {
                    String zipOfCity = rs.getString("zipcode");
                    String cityName = rs.getString("city");
                    String stateOfCity = rs.getString("state");
                    String population = rs.getString("totalPopulation");
                    String totalHouseholds = rs.getString("households");
                    //You can loop through a set just like an array
                    for (String result : resultsInSet) {
//                        System.out.println("Here are the results " + result);
                    }
//                    System.out.println("Here are the results i resultsInSet: "+resultsInSet); //And print the whole set like this
                    double latOfCity = rs.getDouble("lat");
                    double lonOfCity = rs.getDouble("zips2.long");


                    Place place = new Place(cityName, stateOfCity, latOfCity, lonOfCity, population, totalHouseholds);
//                    System.out.println("place contains: " + place);
//                    double distanceToPlaceFromUserZip = haversine(39.1, 94.58, latOfCity,lonOfCity);
//                    System.out.println(latOfCity + " " + lonOfCity);
                    double distanceToPlaceFromUserZip = haversine(originLat, originLong, latOfCity, lonOfCity);

                    if (distanceToPlaceFromUserZip <= distanceFromUser){

                        System.out.printf("%-20s %-10s %-10s %-20s %-20.2f %-10.2f %n", cityName, stateOfCity, population, totalHouseholds, distanceToPlaceFromUserZip, kilometersToMiles(distanceToPlaceFromUserZip));
//                        System.out.println("Distance from " + cityName + " to " + originCityname + " is: " + distanceToPlaceFromUserZip);
                    }
                }
//                System.out.printf("******");

                while (rs.next()) {
//                    calculateDistance
                }
                conn.close();
            }
        } catch (SQLException e) {
//            e.printStackTrace();

            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static double kilometersToMiles(double distanceInKm) {
        return distanceInKm * 0.621371;
    }

    private static double milesToKilometeres(double distanceInMiles) {
        return distanceInMiles * 1.60934;
    }

    public static ArrayList listOfAllCities(String zip, int radius) {
        ArrayList<String> listToReturn = new ArrayList<>();
        String placeName = "";
        int totalPopulation = 0;
        int totalNumHousingUnits = 0;


        return listToReturn;
    }

    public static final double R = 6372.8; // In kilometers

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {



        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }



}


