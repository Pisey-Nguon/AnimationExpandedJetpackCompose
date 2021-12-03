package com.example.jetpackcomposeexample.ui.home

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeexample.model.UserModel
import com.example.jetpackcomposeexample.sampledata.SampleData
import com.example.jetpackcomposeexample.utils.PlaceholderState

class MainActivityViewModel:ViewModel() {

    // mutableState holds state which is observed by the UI
    var listUserModel = mutableStateListOf<UserModel>()
    var deletedItemUser = mutableStateListOf<UserModel>()
    var placeholderState = mutableStateOf<PlaceholderState>(value = PlaceholderState.Fetching)
    var isEnableLoadMore = true

    init {
        initDataToColumn()
    }

    private fun initDataToColumn(){
        Handler(Looper.getMainLooper()).postDelayed({
            placeholderState.value = PlaceholderState.Error(throwable = Throwable())
        },1000)
    }

    fun toggleExpanded(itemUserModel: UserModel){
        listUserModel = listUserModel.also {
            val indexMakeChange = it.indexOf(itemUserModel)
            //We use copy property to make on any item
            it[indexMakeChange] = it[indexMakeChange].copy(isExpanded = !it[indexMakeChange].isExpanded)
        }
    }

    fun loadMoreListUserModel(){
        if(isEnableLoadMore){
            isEnableLoadMore = false
            Handler(Looper.getMainLooper()).postDelayed({
                listUserModel.addAll(SampleData.allUserInfo())
                isEnableLoadMore = true
            },2000)

        }
    }

    fun retry(){
        placeholderState.value = PlaceholderState.Fetching
        Handler(Looper.getMainLooper()).postDelayed({
            placeholderState.value = PlaceholderState.Completed(isHaveLoadMore = true)
            listUserModel.clear()
            listUserModel.addAll(SampleData.allUserInfo())
        },1000)
    }

    fun removeItem(deletedItem:UserModel){
        deletedItemUser.add(deletedItem)
    }

}