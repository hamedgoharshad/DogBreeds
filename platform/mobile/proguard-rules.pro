# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn com.squareup.okhttp.**
## Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
## Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
## Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
## Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
-dontnote android.arch.lifecycle.*


-keep class androidx.datastore.*.** {*;}
-keep class com.near.webApi.response.* {*;}
-keep class com.near.common.data.persistent.database.entity.* {*;}

-keepclassmembers class androidx.datastore.preferences.PreferencesProto$PreferenceMap {
    private androidx.datastore.preferences.protobuf.MapFieldLite preferences_;
}


-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}