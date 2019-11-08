package kr.ac.gachon.parking.Data

public class SeoulData {
    var lat: String? = null
    var lng: String? = null
    var addr: String? = null
    var basic_time: String? = null
    var basic_fee: Int? =null
    var add_time: Int? = null
    var add_fee: Int? = null
    var fee_division: String? =null
    var saturday_fee_devision: String? =null
    var holiday_fee_division: String? =null
    var weekday_close_time: String? =null
    var weekend_close_time: String? =null
    var holiday_close_time: String? =null
    var name:String?=null
    var cars:Int?=null


    public fun get_lat() : String? {
        return lat
    }
    public fun get_lng(): String? {
        return lng
    }
    public fun get_addr() : String? {
        return this.addr
    }
    public fun get_basictime() : String? {
        return this.basic_time
    }
    public fun get_basicfee() : Int? {
        return this.basic_fee
    }
    public fun get_addtime() : Int? {
        return this.add_time
    }
    public fun get_addfee() : Int? {
        return this.add_fee
    }
    public fun get_name():String?{
        return this.name
    }
    public fun get_cars():Int?{
        return this.cars
    }

    public fun set_lat(lat:String) {
        this.lat = lat
    }
    public fun set_lng(lng:String) {
        this.lng = lng
    }
    public fun set_addr(addr:String) {
        this.addr = addr
    }
    public fun set_basictime(basictime:String) {
        this.basic_time = basictime
    }
    public fun set_basicfee(basicfee:Int) {
        this.basic_fee = basicfee
    }
    public fun set_addtime(addtime:Int) {
        this.add_time = addtime
    }
    public fun set_addfee(addfee:Int) {
        this.add_fee = addfee
    }

    public fun set_fee_division(fee_division:String) {
        this.fee_division = fee_division
    }
    public fun set_saturday_fee_devision(saturday_fee_devision:String) {
        this.saturday_fee_devision = saturday_fee_devision
    }
    public fun set_holiday_fee_division(holiday_fee_division:String) {
        this.holiday_fee_division = holiday_fee_division
    }
    public fun set_weekday_close_time(weekday_close_time:String) {
        this.weekday_close_time = weekday_close_time
    }
    public fun set_weekend_close_time(weekend_close_time:String) {
        this.weekend_close_time = weekend_close_time
    }
    public fun set_holiday_close_time(holiday_close_time:String) {
        this.holiday_close_time = holiday_close_time
    }
    public fun set_name(name:String){
        this.name=name
    }
    public fun set_cars(cars:Int){
        this.cars=cars
    }
}