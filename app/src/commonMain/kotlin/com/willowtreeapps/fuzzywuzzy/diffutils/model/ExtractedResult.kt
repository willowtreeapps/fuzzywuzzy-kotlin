package com.willowtreeapps.fuzzywuzzy.diffutils.model

class ExtractedResult(var string: String?, val score: Int, val index: Int) : Comparable<ExtractedResult> {

    override fun compareTo(o: ExtractedResult): Int {
        return this.score.compareTo(o.score)
    }

    override fun toString(): String {
        return "(string: $string, score: $score, index: $index)"
    }
}