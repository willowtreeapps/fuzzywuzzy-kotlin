package com.willowtreeapps.fuzzywuzzy.diffutils

import com.willowtreeapps.fuzzywuzzy.ToStringFunction
import com.willowtreeapps.fuzzywuzzy.diffutils.algorithms.Utils
import com.willowtreeapps.fuzzywuzzy.diffutils.model.BoundExtractedResult
import com.willowtreeapps.fuzzywuzzy.diffutils.model.ExtractedResult


class Extractor {

    var cutoff: Int = 0

    constructor() {
        this.cutoff = 0
    }

    constructor(cutoff: Int) {
        this.cutoff = cutoff
    }

    fun with(cutoff: Int): Extractor {
        this.cutoff = cutoff
        return this
    }

    /**
     * Returns the list of choices with their associated scores of similarity in a list
     * of [ExtractedResult]
     *
     * @param query The query string
     * @param choices The list of choices
     * @param func The function to apply
     * @return The list of results
     */
    fun extractWithoutOrder(query: String, choices: Collection<String>,
                            func: Applicable): List<ExtractedResult> {
        val yields = ArrayList<ExtractedResult>()
        var index = 0

        for (s in choices) {

            val score = func.apply(query, s)

            if (score >= cutoff) {
                yields.add(ExtractedResult(s, score, index))
            }
            index++
        }

        return yields
    }

    /**
     * Returns the list of choices with their associated scores of similarity in a list
     * of [ExtractedResult]
     *
     * @param query The query string
     * @param choices The list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func The function to apply
     * @return The list of results
     */
    fun <T> extractWithoutOrder(query: String, choices: Collection<T>,
                                toStringFunction: ToStringFunction<T>, func: Applicable): List<BoundExtractedResult<T>> {

        val yields = ArrayList<BoundExtractedResult<T>>()
        var index = 0

        for (t in choices) {

            val s = toStringFunction.apply(t)
            val score = func.apply(query, s)

            if (score >= cutoff) {
                yields.add(BoundExtractedResult(t, s, score, index))
            }
            index++
        }

        return yields

    }

    /**
     * Find the single best match above a score in a list of choices.
     *
     * @param query  A string to match against
     * @param choices A list of choices
     * @param func   Scoring function
     * @return An object containing the best match and it's score
     */
    fun extractOne(query: String, choices: Collection<String>, func: Applicable): ExtractedResult {
        val extracted = extractWithoutOrder(query, choices, func)

        return extracted.max()!!
    }

    /**
     * Find the single best match above a score in a list of choices.
     *
     * @param query  A string to match against
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func   Scoring function
     * @return An object containing the best match and it's score
     */
    fun <T> extractOne(query: String, choices: Collection<T>, toStringFunction: ToStringFunction<T>,
                       func: Applicable): BoundExtractedResult<T> {

        val extracted = extractWithoutOrder(query, choices, toStringFunction, func)

        return extracted.max()!!

    }

    /**
     * Creates a **sorted** list of [ExtractedResult]  which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param func    The scoring function
     * @return A list of the results
     */
    fun extractTop(query: String, choices: Collection<String>, func: Applicable): List<ExtractedResult> {
        val best = extractWithoutOrder(query, choices, func)
//        Collections.sort(best, Collections.reverseOrder())

        return best.sortedDescending()
    }

    /**
     * Creates a **sorted** list of [ExtractedResult]  which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func    The scoring function
     * @return A list of the results
     */
    fun <T> extractTop(query: String, choices: Collection<T>,
                       toStringFunction: ToStringFunction<T>, func: Applicable): List<BoundExtractedResult<T>> {

        val best = extractWithoutOrder(query, choices, toStringFunction, func)
        //TODO check this
//        Collections.sort(best, Collections.reverseOrder())

        return best.sortedDescending()
    }

    /**
     * Creates a **sorted** list of [ExtractedResult] which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param limit   Limits the number of results and speeds up
     * the search (k-top heap sort) is used
     * @return A list of the results
     */
    fun extractTop(query: String, choices: Collection<String>, func: Applicable, limit: Int): List<ExtractedResult> {
        val best = extractWithoutOrder(query, choices, func)

        val results = Utils.findTopKHeap(best, limit)
//        Collections.reverse(results)

        return results.sortedDescending()
    }

    /**
     * Creates a **sorted** list of [ExtractedResult] which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param limit   Limits the number of results and speeds up
     * the search (k-top heap sort) is used
     * @return A list of the results
     */
    fun <T> extractTop(query: String, choices: Collection<T>,
                       toStringFunction: ToStringFunction<T>, func: Applicable, limit: Int): List<BoundExtractedResult<T>> {

        val best = extractWithoutOrder(query, choices, toStringFunction, func)

        val results = Utils.findTopKHeap(best, limit)
//        Collections.reverse(results)

        return results.sortedDescending()
    }
}
