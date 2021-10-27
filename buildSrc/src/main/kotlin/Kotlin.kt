/**
 * des 管理kotlin相关依赖
 * @author zs
 * @date   2020/9/15
 */
object Kotlin {
    const val kotlinVersion = "1.3.61"
    const val jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"

    val coroutines = Coroutines
    object Coroutines {
        private const val coroutines_version = "1.3.7"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    }

//    val compose = Compose
//    object Compose {
//        private const val compose_version = "1.1.0-alpha06"
//        const val ui = "androidx.compose.ui:ui:$compose_version"
//        const val tooling = "androidx.compose.ui:ui-tooling:$compose_version"
//        const val foundation = "androidx.compose.foundation:foundation:$compose_version"
//        const val material = "androidx.compose.material:material:$compose_version"
//        const val core = "androidx.compose.material:material-icons-core:$compose_version"
//        const val extended = "androidx.compose.material:material-icons-extended:$compose_version"
//        const val livedata = "androidx.compose.runtime:runtime-livedata:$compose_version"
//        const val rxjava2 = "androidx.compose.runtime:runtime-rxjava2:$compose_version"
//    }
}