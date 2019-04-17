package com.willowtreeapps.fuzzywuzzy.diffutils.algorithms

import com.willowtreeapps.fuzzywuzzy.StringProcessor


@Deprecated("Use {@code ToStringFunction#NO_PROCESS} instead.")
class NoProcess : StringProcessor() {

    @Deprecated("")
    override fun process(`in`: String): String {
        return `in`
    }

}