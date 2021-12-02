package com.example.jetpackcomposeexample.ui.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeexample.model.UserModel
import com.example.jetpackcomposeexample.sampledata.SampleData

class MainActivityViewModel:ViewModel() {

    // mutableState holds state which is observed by the UI
    var listUserModel = mutableStateListOf<UserModel>()
    var deletedItemUser = mutableStateListOf<UserModel>()

    init {
        initDataToColumn()
    }

    private fun initDataToColumn(){
        listUserModel.addAll(SampleData.allUserInfo())
    }

    fun toggleExpanded(itemUserModel: UserModel){
        listUserModel = listUserModel.also {
            val indexMakeChange = it.indexOf(itemUserModel)
            //We use copy property to make on any item
            it[indexMakeChange] = it[indexMakeChange].copy(isExpanded = !it[indexMakeChange].isExpanded)
        }
    }

    fun removeItem(deletedItem:UserModel){
        deletedItemUser.add(deletedItem)
    }

}