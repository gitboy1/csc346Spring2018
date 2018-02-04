import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    static Connection conn;
    static ResultSet rs;
    static Statement stmt;

    public static void main(String[] args) {
        String host = "jdbc:mysql://turing.cs.missouriwestern.edu:3306/misc";
        String user = "csc254";
        String password = "age126";

//        String query = "SELECT * FROM drawings WHERE name LIKE '%" + DT + "%'";

//        String queryString = "SELECT city, zip_code, lon, lat FROM zips";
        String queryString = "SELECT city, zip_code, lon, lat FROM zips WHERE zip_code LIKE '" + 6450 + "%'";
//        String queryString = "SELECT city, zip_code, lon, lat FROM zips WHERE zip_code LIKE 6450%";

        try {
            conn = DriverManager.getConnection(host, user, password);

            if (conn == null){
                System.out.println("Connection failed.");
            } else {
                System.out.println("We're connected!");

                stmt = conn.createStatement();
                rs = stmt.executeQuery(queryString);

                ResultSetMetaData rsMetaData = rs.getMetaData();
                int numberOfColumns = rsMetaData.getColumnCount();

                System.out.println("Number of colums: " + numberOfColumns);

                for (int i=1; i<=numberOfColumns;i++){
                    System.out.printf("Column %2d: %s (%s)\n", i,
                            rsMetaData.getColumnName(i),
                            rsMetaData.getColumnTypeName(i));
                }
                /*
                * Number of colums: 4
                * Column  1: city (VARCHAR)
                * Column  2: zip_code (VARCHAR)
                * Column  3: lon (FLOAT)
                * Column  4: lat (FLOAT)
                * */

/* equals method should be both city and state */
                while (rs.next()){
                    String zipOfCity = rs.getString("zip_code");
                    String cityName = rs.getString("city");
                    Set<String> resultsInSet = new HashSet<>();
                    resultsInSet.add(rs.getString("zip_code"));
                    //You can loop through a set just like an array
                    for (String result: resultsInSet){
                        System.out.println("Here are the results" + result);
                    }
                    System.out.println(resultsInSet); //And print the whole set like this
                    double latOfCity = rs.getDouble("lat");
                    double lonOfCity = rs.getDouble("lon");
                    Place place = new Place(cityName, zipOfCity, latOfCity, lonOfCity);
                    System.out.println(place);


                }
                System.out.printf("******");

                while (rs.next()){
//                    calculateDistance
                }
                conn.close();
            }
        }catch (SQLException e){
//            e.printStackTrace();

            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

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
    public static ArrayList listOfAllCities(String zip, int radius){
        ArrayList<String > listToReturn = new ArrayList<>();
        String placeName = "";
        int totalPopulation = 0;
        int totalNumHousingUnits = 0;




        return listToReturn;
    }


    private static final int EARTH_RADIUS = 3959; // Approx Earth radius in MI

    public static double calculateDistance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double calculatedDistance = 0;
        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return calculatedDistance = EARTH_RADIUS * c;
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }


    /**
     * Jason Winn
     * http://jasonwinn.org
     * Created July 10, 2013
     *
     * Description: Small class that provides approximate distance between
     * two points using the Haversine formula.
     *
     * Call in a static context:
     * Haversine.distance(47.6788206, -122.3271205,
     *                    47.6788206, -122.5271205)
     * --> 14.973190481586224 [km]
     *
     */

}

