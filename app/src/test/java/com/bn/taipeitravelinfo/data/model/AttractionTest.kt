package com.bn.taipeitravelinfo.data.model

import org.junit.Assert
import org.junit.Test

class AttractionTest {

    @Test
    fun getLocalTel() {
        Assert.assertEquals("0900000000", mockAttraction("+886-900-000000").localTel)
        Assert.assertEquals("+20-500-000000", mockAttraction("+20-500-000000").localTel)
        Assert.assertEquals("0225363001", mockAttraction("+886-2-25363001").localTel)
        Assert.assertEquals("062536301", mockAttraction("+886-6-2536301").localTel)
    }

    private fun mockAttraction(tel: String) = Attraction(
        0, "", "", tel, "", "", "", "", listOf()
    )
}