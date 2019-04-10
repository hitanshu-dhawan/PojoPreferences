# PojoPreferences
PojoPreferences is an android library for saving your Pojo variables into [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences.html) by generating its boilerplate code using annotation processing.

## Usage
Create your Pojo class by annotating it with <b>`@Pojo`</b> and its variables with <b>`@Pref`</b>
```
@Pojo
public class User {
    @Pref
    private int id;
    @Pref
    private String name;
}
```
If you want to specify default values for your variables
```
@Pojo
public class User {
    @Pref
    private final int id = 2;
    @Pref
    private final String name = "Dhawan";
}
```
Now, PojoPreferences will generate a new class with <b>"Pref"</b> as its suffix. In this case `UserPref`.
<br>
It class will contain all the setters and getters for your Pojo variables.
```
UserPref.setId(context, 7);
UserPref.setName(context, "Hitanshu");

UserPref.getId(context);
UserPref.getName(context);
```

## Download
Add JitPack repository to your root `build.gradle` file
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency to your app `build.gradle` file
```
dependencies {
    implementation 'com.github.hitanshu-dhawan.PojoPreferences:annotations:1.0.2'
    annotationProcessor 'com.github.hitanshu-dhawan.PojoPreferences:compiler:1.0.2'
}
```

## Licence
```
Copyright (c) 2019 Hitanshu Dhawan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
