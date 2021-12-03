package com.example.jetpackcomposeexample.utils

sealed class PlaceholderState{
    object Fetching:PlaceholderState()
    data class Completed(val isHaveLoadMore:Boolean):PlaceholderState()
    data class Error(val throwable: Throwable):PlaceholderState()
}
