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