# Some methods are only called from tests, so make sure the shrinker keeps them.
-keep class com.navtar.** { *; }

-keep class android.support.com.navatar.math.test.espresso.IdlingResource { *; }
-keep class android.support.com.navatar.math.test.espresso.IdlingRegistry { *; }
-keep class com.google.common.base.Preconditions { *; }
-keep class android.arch.** { *; }

# For Guava:
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

# Proguard rules that are applied to your com.navatar.math.test apk/code.
-ignorewarnings

-keepattributes *Annotation*

-dontnote junit.framework.**
-dontnote junit.runner.**

-dontwarn android.com.navatar.math.test.**
-dontwarn android.support.com.navatar.math.test.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**
-dontwarn com.squareup.javawriter.JavaWriter
# Uncomment this if you use Mockito
-dontwarn org.mockito.**