package com.ehaquesoft.bs23androidtask101

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class ResourceCompererTest{

    lateinit var resourceComperer:ResourceComperer
    @Before
    fun setUp(){
        resourceComperer = ResourceComperer()
    }

    @Test
    fun givenResourceStringIsEqualGivenString_returnTrue(){
        var context = ApplicationProvider.getApplicationContext<Context>()
        var reslut = resourceComperer.isEqual(context, R.string.app_name, "BS23AndroidTask101")
        Truth.assertThat(reslut).isTrue()
    }

    @Test
    fun givenResourceStringNotEqualGivenString_returnFalse(){
        var context = ApplicationProvider.getApplicationContext<Context>()
        var reslut = resourceComperer.isEqual(context, R.string.app_name, "AndroidTask101")
        Truth.assertThat(reslut).isFalse()
    }
}