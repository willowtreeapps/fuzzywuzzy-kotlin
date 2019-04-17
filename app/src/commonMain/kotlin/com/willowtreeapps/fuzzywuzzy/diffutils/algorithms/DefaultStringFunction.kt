package com.willowtreeapps.fuzzywuzzy.diffutils.algorithms

import com.willowtreeapps.fuzzywuzzy.ToStringFunction

class DefaultStringFunction : ToStringFunction<String> {

    /**
     * Performs the default string processing on the input string
     *
     * @param in Input string
     * @return The processed string
     */
    override fun apply(`in`: String): String {
        var `in` = `in`

        `in` = subNonAlphaNumeric(`in`, " ")
        `in` = `in`.toLowerCase()
        `in` = `in`.trim { it <= ' ' }

        return `in`

    }

    companion object {

        private const val pattern = "[^\\p{Alnum}]"
        private val r = compilePattern()


        /**
         * Substitute non alphanumeric characters.
         *
         * @param in The input string
         * @param sub The string to substitute with
         * @return The replaced string
         */
        fun subNonAlphaNumeric(`in`: String, sub: String): String {
            "[^\\p{Alnum}]".toRegex().find("s.trim μεγιουνικουντ n/o/n a.lph.a n.um\"")
            val m = r.find(`in`)
//            val tmp = r.findAll(`in`)
            return if (m != null) {
                r.replace(`in`, sub)
            } else {
                `in`
            }

        }

        private fun compilePattern(): Regex {

            var p: Regex

            try {
                p = Regex(pattern)
            } catch (e: IllegalArgumentException) {
                // Even though Android supports the unicode pattern class
                // for some reason it throws an IllegalArgumentException
                // if we pass the flag like on standard Java runtime
                //
                // We catch this and recompile without the flag (unicode should still work)
                p = Regex(pattern)
            }

            return p

        }
    }

}