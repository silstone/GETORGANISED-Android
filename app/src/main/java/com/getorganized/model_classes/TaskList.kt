package com.getorganized.model_classes

import android.os.Parcel
import android.os.Parcelable

  class TaskList {


    private var name: String? = null
    private var colorname: String? = null


    fun getname(): String? {
        return name
    }

    fun setname(recieverID: String?) {
        this.name = recieverID
    }

    fun getcolorname(): String? {
        return colorname
    }

    fun setcolorname(color: String?) {
        this.colorname = color
    }



}