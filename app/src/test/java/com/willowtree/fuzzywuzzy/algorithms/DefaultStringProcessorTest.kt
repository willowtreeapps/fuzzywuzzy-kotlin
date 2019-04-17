package com.willowtreeapps.fuzzywuzzy.algorithms

import com.willowtreeapps.fuzzywuzzy.diffutils.algorithms.DefaultStringFunction
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.regex.Pattern

class DefaultStringProcessorTest {

    @Test
    fun testProcess() {
        val inp = "s.trim μεγιουνικουντ n/o/n a.lph.a n.um"

        assertEquals("s trim μεγιουνικουντ n o n a lph a n um", DefaultStringFunction().apply(inp))
        Pattern.UNICODE_CHARACTER_CLASS
    }
}