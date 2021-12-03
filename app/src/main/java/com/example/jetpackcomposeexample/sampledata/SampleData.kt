package com.example.jetpackcomposeexample.sampledata

import com.example.jetpackcomposeexample.model.UserModel

object SampleData {
    var currentIndex = 1
    var totalIndex = 10
    fun allUserInfo():ArrayList<UserModel>{
        val listUserProfileModel = ArrayList<UserModel>()
        for (i in currentIndex..totalIndex){
            listUserProfileModel.add(UserModel(id = currentIndex.toString(),imageUrl = "https://farm2.staticflickr.com/1533/26541536141_41abe98db3_z_d.jpg",userName = "Pisey Nguon",desc = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum"))
            currentIndex += 1
        }
        totalIndex += 10
        return listUserProfileModel
    }
}