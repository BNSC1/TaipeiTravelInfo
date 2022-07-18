package com.bn.taipeitravelinfo

import com.bn.taipeitravelinfo.data.model.Attraction
import org.junit.Assert.assertEquals
import org.junit.Test

class ModelTest {
    @Test
    fun testTelConversion() {
        assertEquals("0900000000", mockAttractionForTelTest("+886-900-000000"))
        assertEquals("+20-500-000000", mockAttractionForTelTest("+20-500-000000"))
        assertEquals("0225363001", mockAttractionForTelTest("+886-2-25363001"))
        assertEquals("062536301", mockAttractionForTelTest("+886-6-2536301"))
    }

    private fun mockAttractionForTelTest(tel: String) = Attraction(
        0,"","",tel,"","","","",listOf()
    ).localTel
}