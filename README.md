## BasePerryMvp

### 依赖：</br>
----------
##### Step 1.
###### 添加Jitpack到您的root gradle，如果无法导包，一般情况下都是这个原因，请仔细检查
 ```xml
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
        classpath 'me.tatarka:gradle-retrolambda:3.2.4'
    }
}

allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
 ```
##### Step 2.
###### 在需要使用到的Module中添加以下依赖
 ```xml
dependencies {
    compile 'com.github.weipeilong123:BasePerryMvp:1.0.0'
}
 ```