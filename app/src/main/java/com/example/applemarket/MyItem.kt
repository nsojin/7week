package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data  class MyItem (
    val aImage:Int,
    val aName:String,
    val aAddress:String,
    val aPrice:Int,
    val aComment:Int,
    var aLike:Int,
    val detail:String,
    val seller:String,
    var likeCount:Boolean): Parcelable