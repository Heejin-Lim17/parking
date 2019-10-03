package kr.ac.gachon.parking.Group

enum class GroupItemSel(val groupfun:String, val groupinfo:String){
    group1("그룹 검색", "접속할 그룹을 검색해보세요"),
    group2("그룹 생성", "그룹을 만들고 회원을 초대하세요"),
    group3("나의 그룹", "가입되어있는 그룹을 확인해보세요"),
    group4("그룹 관리", "그룹을 관리하세요")
}