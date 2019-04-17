package com.willowtreeapps.fuzzywuzzy

/**
 * Transforms the input string
 *
 */
@Deprecated("Use {@code ToStringFunction<String>} instead.")
abstract class StringProcessor : ToStringFunction<String> {
    // now abstract class because JDK1.7 does not allow default methods in interfaces
    /**
     * Transforms the input string
     *
     * @param in Input string
     * @return The processed string
     */
    @Deprecated("Use {@code ToStringFunction#apply(String)} instead.\n" +
            "      ")
    abstract fun process(`in`: String): String

    override fun apply(item: String): String {
        return process(item)
    }
}
