package com.example.jetpackcomposeexample.sampledata

import com.example.jetpackcomposeexample.model.UserModel

object SampleData {
    fun allUserInfo():List<UserModel>{
        val listUserProfileModel = ArrayList<UserModel>()
        for (i in 0 until 20){
            listUserProfileModel.add(UserModel(imageUrl = "https://farm2.staticflickr.com/1533/26541536141_41abe98db3_z_d.jpg",userName = "jane Roffer",desc = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum"))
        }
        return listUserProfileModel
    }
}