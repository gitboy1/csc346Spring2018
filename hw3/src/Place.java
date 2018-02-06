public class Place {
    String cityName;
    String stateOfCity;
    String totPop;
    String totalHouseholds;
    double latitude;
    double longitude;
//    double distanceFromOrigin;


    public Place(String name, String state, double latitude, double longitude, String totPop, String households) {
        this.cityName = name;
        this.stateOfCity = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.totPop = totPop;
        this.totalHouseholds= households;

//        this.distanceFromOrigin = distanceFromOrigin;
    }



    @Override
    public String toString() {
        return "Place{" +
                "city='" + cityName + '\'' +
                ", state='" + stateOfCity + '\'' +
                ", population=" + totPop +
                ", households=" + totalHouseholds +

//                ", distanceFromOrigin=" + distanceFromOrigin +
                '}';
    }

//    @Override
//    public String toString() {
//        return "Place{" +
//                "name='" + cityName + '\'' +
//                ", zip='" + zipOfCity + '\'' +
//                ", latitude=" + latitude +
//                ", longitude=" + longitude +
//                ", population=" + totPop +
//
////                ", distanceFromOrigin=" + distanceFromOrigin +
//                '}';
//    }
}

