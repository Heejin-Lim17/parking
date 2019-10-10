package kr.ac.gachon.parking.ParkingFunction

enum class Hour(val hour:String){
    AM12("00시"),
    AM1("01시"),
    AM2("02시"),
    AM3("03시"),
    AM4("04시"),
    AM5("05시"),
    AM6("06시"),
    AM7("07시"),
    AM8("08시"),
    AM9("09시"),
    AM10("10시"),
    AM11("11시"),
    PM12("12시"),
    PM1("13시"),
    PM2("14시"),
    PM3("15시"),
    PM4("16시"),
    PM5("17시"),
    PM6("18시"),
    PM7("19시"),
    PM8("20시"),
    PM9("21시"),
    PM10("22시"),
    PM11("23시")
}
enum class Minute(val minute:String){
    min00("00분"),
    min05("05분"),
    min10("10분"),
    min15("15분"),
    min20("20분"),
    min25("25분"),
    min30("30분"),
    min35("35분"),
    min40("40분"),
    min45("45분"),
    min50("50분"),
    min55("55분")
}
enum class Fee(val fee:String){
    fee00("요금설정 없음"),
    fee10("1000원"),
    fee20("2000원"),
    fee30("3000원"),
    fee40("4000원"),
    fee50("5000원"),
    fee60("6000원"),
    fee70("7000원"),
    fee80("8000원"),
    fee90("9000원"),
    fee100("10000원")
}