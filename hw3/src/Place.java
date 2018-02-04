public class Place {
    String cityName;
    String zipOfCity;
    double latitude;
    double longitude;
//    double distanceFromOrigin;


    public Place(String name, String zip, double latitude, double longitude) {
        this.cityName = name;
        this.zipOfCity = zip;
        this.latitude = latitude;
        this.longitude = longitude;
//        this.distanceFromOrigin = distanceFromOrigin;
    }




    @Override
    public String toString() {
        return "Place{" +
                "name='" + cityName + '\'' +
                ", region='" + zipOfCity + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
//                ", distanceFromOrigin=" + distanceFromOrigin +
                '}';
    }
}
