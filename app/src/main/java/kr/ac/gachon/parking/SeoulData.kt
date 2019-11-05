package kr.ac.gachon.parking

public class SeoulData {
    var lat: String? = null
    var lng: String? = null

    public fun get_lat() : String? {
        return lat
    }
    public fun get_lng(): String? {
        return lng
    }
    public fun set_lat(lat:String) {
        this.lat = lat
    }
    public fun set_lng(lng:String) {
        this.lng = lng
    }
}