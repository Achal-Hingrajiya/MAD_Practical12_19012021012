package com.example.practical12_19012021012

class UserData {
    companion object {

        var arrOfUser = ArrayList<User>()

        fun addUser(user : User){
            arrOfUser.add(user)
        }
    }
}

class User(
    var id: Long,
    var name: Name,
    var phone: String,
    var address: String
) {

}

class Name(
    var firstName: String,
    var lastName: String
) {

    fun getName() :String{

        return this.firstName + this.lastName
    }
}