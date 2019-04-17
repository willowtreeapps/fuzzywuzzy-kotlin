package com.willowtreeapps.fuzzywuzzy

import com.willowtreeapps.fuzzywuzzy.diffutils.Extractor
import com.willowtreeapps.fuzzywuzzy.diffutils.algorithms.WeightedRatio
import org.junit.Before
import org.junit.Test

class ExtractorTest {

    lateinit var choices: List<String>
    private lateinit var extractor: Extractor

    @Before
    fun setUp() {
        choices = listOf("google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl")
        extractor = Extractor()
    }

    @Test
    fun testExtractWithoutOrder() {

        val res = extractor.extractWithoutOrder("goolge", choices, WeightedRatio())

        assert(res.size == choices.size)
        assert(res[0].score > 0)
    }

    @Test
    fun testExtractOne() {

        val res = extractor.extractOne("goolge", choices, WeightedRatio())

        assert(res.string == "google")

    }

    @Test
    fun testExtractBests() {

        val res = extractor.extractTop("goolge", choices, WeightedRatio())

        assert(res[0].string == "google" && res[1].string == "googleplus")

    }

    @Test
    fun testExtractBests1() {

        val res = extractor.extractTop("goolge", choices, WeightedRatio(), 3)

        assert(res.size == 3)
        assert(res[0].string == "google" && res.get(1).string == "googleplus" && res.get(2).string == "plexoogl")

    }
}