public class Place {
    String cityName;
    String stateOfCity;
    String totPop;
    double latitude;
    double longitude;
//    double distanceFromOrigin;


    public Place(String name, String state, double latitude, double longitude, String totPop) {
        this.cityName = name;
        this.stateOfCity = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.totPop = totPop;
//        this.distanceFromOrigin = distanceFromOrigin;
    }



    @Override
    public String toString() {
        return "Place{" +
                "name='" + cityName + '\'' +
                ", state='" + stateOfCity + '\'' +
                ", households=" + latitude +
                ", population=" + totPop +

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
