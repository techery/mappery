buildscript {

    repositories {
        gradleScriptKotlin()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin"))
    }
}

apply {
    plugin("kotlin")
    from("gradle/plugins/maven-simple.gradle")
}

val kotlinVersion = "1.1.1"

configure<JavaPluginConvention> {
    setSourceCompatibility(1.6)
    setTargetCompatibility(1.6)
}

repositories {
    gradleScriptKotlin()
}

dependencies {
    compile(kotlin("stdlib"))
    testCompile("junit:junit:4.12")
    testCompile("org.assertj:assertj-core:1.7.1")
    testCompile("org.jetbrains.spek:spek:1.0.9")
    testCompile("com.nhaarman:mockito-kotlin:0.5.0")
}

fun kotlin(module: String) = "org.jetbrains.kotlin:kotlin-$module:$kotlinVersion"
