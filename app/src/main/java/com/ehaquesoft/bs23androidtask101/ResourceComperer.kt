package com.ehaquesoft.bs23androidtask101

import android.content.Context

class ResourceComperer {

    fun isEqual(context:Context, resourceId:Int, string:String):Boolean{
        return context.getString(resourceId) == string
    }
}