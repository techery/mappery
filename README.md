## Mappery
Kotlin class mapping tool with no magic or hassle

### Introduction
Set up `Mappery` instance via builder:

```kotlin
val mappery = Mappery.Builder()
                    .map(ModelA::class.java)
                    .to(ModelB::class.java, ModelAToModelBConverter())
                    .from(ModelB::class.java, ModelBToModelAConverter())
                    .build()
```

Use configured instance to convert models:

```kotlin
val a = ModelA()
val b = mappery.convert(a, ModelB::class.java)
```

### Note
* Interoperable with Java;
* Java primitives are supported too.

### Download 
[![](https://jitpack.io/v/techery/mappery.svg)](https://jitpack.io/#techery/mappery)
[![Build Status](https://travis-ci.org/techery/mappery.svg?branch=master)](https://travis-ci.org/techery/mappery)

```groovy
repositories {
    maven { url "https://jitpack.io" }
}
dependencies {
    compile "com.github.techery:mappery:$libVersion"
    compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    compile "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"
}
```

### License

    Copyright (c) 2017 Techery

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
