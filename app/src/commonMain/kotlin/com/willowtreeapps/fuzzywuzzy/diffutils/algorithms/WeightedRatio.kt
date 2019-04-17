package com.willowtreeapps.fuzzywuzzy.diffutils.algorithms

import com.willowtreeapps.fuzzywuzzy.ToStringFunction
import com.willowtreeapps.fuzzywuzzy.diffutils.FuzzySearch.partialRatio
import com.willowtreeapps.fuzzywuzzy.diffutils.FuzzySearch.ratio
import com.willowtreeapps.fuzzywuzzy.diffutils.FuzzySearch.tokenSetPartialRatio
import com.willowtreeapps.fuzzywuzzy.diffutils.FuzzySearch.tokenSetRatio
import com.willowtreeapps.fuzzywuzzy.diffutils.FuzzySearch.tokenSortPartialRatio
import com.willowtreeapps.fuzzywuzzy.diffutils.FuzzySearch.tokenSortRatio
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round


class WeightedRatio : BasicAlgorithm() {


    override fun apply(s1: String, s2: String, stringProcessor: ToStringFunction<String>): Int {
        var s1 = s1
        var s2 = s2

        s1 = stringProcessor.apply(s1)
        s2 = stringProcessor.apply(s2)

        val len1 = s1.length
        val len2 = s2.length

        if (len1 == 0 || len2 == 0) {
            return 0
        }

        var tryPartials = TRY_PARTIALS
        val unbaseScale = UNBASE_SCALE
        var partialScale = PARTIAL_SCALE

        val base = ratio(s1, s2)
        val lenRatio = max(len1, len2).toDouble() / min(len1, len2)

        // if strings are similar length don't use partials
        if (lenRatio < 1.5) tryPartials = false

        // if one string is much shorter than the other
        if (lenRatio > 8) partialScale = .6

        if (tryPartials) {

            val partial = partialRatio(s1, s2) * partialScale
            val partialSor = tokenSortPartialRatio(s1, s2) * unbaseScale * partialScale
            val partialSet = tokenSetPartialRatio(s1, s2) * unbaseScale * partialScale

            return round(max(max(max(base.toDouble(), partial), partialSor), partialSet)).toInt()

        } else {

            val tokenSort = tokenSortRatio(s1, s2) * unbaseScale
            val tokenSet = tokenSetRatio(s1, s2) * unbaseScale

            return round(max(max(base.toDouble(), tokenSort), tokenSet)).toInt()

        }

    }

    companion object {

        val UNBASE_SCALE = .95
        val PARTIAL_SCALE = .90
        val TRY_PARTIALS = true
    }

}