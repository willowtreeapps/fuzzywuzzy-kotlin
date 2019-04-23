# FuzzyWuzzy-Kotlin
[![CircleCI](https://circleci.com/gh/willowtreeapps/fuzzywuzzy-kotlin.svg?style=svg)](https://circleci.com/gh/willowtreeapps/fuzzywuzzy-kotlin)

Fuzzy string matching for Kotlin (JVM, iOS) - fork of [the Java fork](https://github.com/xdrop/fuzzywuzzy) of of [Fuzzy Wuzzy Python lib](https://github.com/seatgeek/fuzzywuzzy). For use in on JVM, Android, or Kotlin Multiplatform projects (JVM/Android, iOS, mac, linux)

Useful for selecting the closest matching string from a collection of strings.  Various algorithms are available.

See Java repo or Python repo for usage.

To add to project in the common module add the dependency:

```
sourceSets {
  commonMain {
      dependencies {
          implementation "com.willowtreeapps:fuzzywuzzy-kotlin:0.1.1"
      }
   }
}
```
