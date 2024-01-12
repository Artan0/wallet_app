[33mcommit 0df6d2a6c73d5081b59fdcbb6cfcd34190a0b740[m[33m ([m[1;36mHEAD -> [m[1;32mmaster[m[33m)[m
Author: Artan0 <artanbayram03@gmail.com>
Date:   Tue Jan 2 17:28:21 2024 +0100

    init commit

[1mdiff --git a/.gitignore b/.gitignore[m
[1mnew file mode 100644[m
[1mindex 0000000..aa724b7[m
[1m--- /dev/null[m
[1m+++ b/.gitignore[m
[36m@@ -0,0 +1,15 @@[m
[32m+[m[32m*.iml[m
[32m+[m[32m.gradle[m
[32m+[m[32m/local.properties[m
[32m+[m[32m/.idea/caches[m
[32m+[m[32m/.idea/libraries[m
[32m+[m[32m/.idea/modules.xml[m
[32m+[m[32m/.idea/workspace.xml[m
[32m+[m[32m/.idea/navEditor.xml[m
[32m+[m[32m/.idea/assetWizardSettings.xml[m
[32m+[m[32m.DS_Store[m
[32m+[m[32m/build[m
[32m+[m[32m/captures[m
[32m+[m[32m.externalNativeBuild[m
[32m+[m[32m.cxx[m
[32m+[m[32mlocal.properties[m
[1mdiff --git a/.idea/.gitignore b/.idea/.gitignore[m
[1mnew file mode 100644[m
[1mindex 0000000..26d3352[m
[1m--- /dev/null[m
[1m+++ b/.idea/.gitignore[m
[36m@@ -0,0 +1,3 @@[m
[32m+[m[32m# Default ignored files[m
[32m+[m[32m/shelf/[m
[32m+[m[32m/workspace.xml[m
[1mdiff --git a/.idea/compiler.xml b/.idea/compiler.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..b589d56[m
[1m--- /dev/null[m
[1m+++ b/.idea/compiler.xml[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="CompilerConfiguration">[m
[32m+[m[32m    <bytecodeTargetLevel target="17" />[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/gradle.xml b/.idea/gradle.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..6d89050[m
[1m--- /dev/null[m
[1m+++ b/.idea/gradle.xml[m
[36m@@ -0,0 +1,19 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="GradleSettings">[m
[32m+[m[32m    <option name="linkedExternalProjectsSettings">[m
[32m+[m[32m      <GradleProjectSettings>[m
[32m+[m[32m        <option name="testRunner" value="GRADLE" />[m
[32m+[m[32m        <option name="distributionType" value="DEFAULT_WRAPPED" />[m
[32m+[m[32m        <option name="externalProjectPath" value="$PROJECT_DIR$" />[m
[32m+[m[32m        <option name="gradleJvm" value="jbr-17" />[m
[32m+[m[32m        <option name="modules">[m
[32m+[m[32m          <set>[m
[32m+[m[32m            <option value="$PROJECT_DIR$" />[m
[32m+[m[32m            <option value="$PROJECT_DIR$/app" />[m
[32m+[m[32m          </set>[m
[32m+[m[32m        </option>[m
[32m+[m[32m      </GradleProjectSettings>[m
[32m+[m[32m    </option>[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/kotlinc.xml b/.idea/kotlinc.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..fdf8d99[m
[1m--- /dev/null[m
[1m+++ b/.idea/kotlinc.xml[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="KotlinJpsPluginSettings">[m
[32m+[m[32m    <option name="version" value="1.9.0" />[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/misc.xml b/.idea/misc.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..0ad17cb[m
[1m--- /dev/null[m
[1m+++ b/.idea/misc.xml[m
[36m@@ -0,0 +1,10 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="ExternalStorageConfigurationManager" enabled="true" />[m
[32m+[m[32m  <component name="ProjectRootManager" version="2" languageLevel="JDK_17" default="true" project-jdk-name="jbr-17" project-jdk-type="JavaSDK">[m
[32m+[m[32m    <output url="file://$PROJECT_DIR$/build/classes" />[m
[32m+[m[32m  </component>[m
[32m+[m[32m  <component name="ProjectType">[m
[32m+[m[32m    <option name="id" value="Android" />[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/vcs.xml b/.idea/vcs.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..94a25f7[m
[1m--- /dev/null[m
[1m+++ b/.idea/vcs.xml[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="VcsDirectoryMappings">[m
[32m+[m[32m    <mapping directory="$PROJECT_DIR$" vcs="Git" />[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/app/.gitignore b/app/.gitignore[m
[1mnew file mode 100644[m
[1mindex 0000000..42afabf[m
[1m--- /dev/null[m
[1m+++ b/app/.gitignore[m
[36m@@ -0,0 +1 @@[m
[32m+[m[32m/build[m
\ No newline at end of file[m
[1mdiff --git a/app/build.gradle.kts b/app/build.gradle.kts[m
[1mnew file mode 100644[m
[1mindex 0000000..2ea6dfd[m
[1m--- /dev/null[m
[1m+++ b/app/build.gradle.kts[m
[36m@@ -0,0 +1,47 @@[m
[32m+[m[32mplugins {[m
[32m+[m[32m    id("com.android.application")[m
[32m+[m[32m    id("org.jetbrains.kotlin.android")[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32mandroid {[m
[32m+[m[32m    namespace = "com.example.wallet_app"[m
[32m+[m[32m    compileSdk = 33[m
[32m+[m
[32m+[m[32m    defaultConfig {[m
[32m+[m[32m        applicationId = "com.example.wallet_app"[m
[32m+[m[32m        minSdk = 25[m
[32m+[m[32m        targetSdk = 33[m
[32m+[m[32m        versionCode = 1[m
[32m+[m[32m        versionName = "1.0"[m
[32m+[m
[32m+[m[32m        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    buildTypes {[m
[32m+[m[32m        release {[m
[32m+[m[32m            isMinifyEnabled = false[m
[32m+[m[32m            proguardFiles([m
[32m+[m[32m                getDefaultProguardFile("proguard-android-optimize.txt"),[m
[32m+[m[32m                "proguard-rules.pro"[m
[32m+[m[32m            )[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m[32m    compileOptions {[m
[32m+[m[32m        sourceCompatibility = JavaVersion.VERSION_1_8[m
[32m+[m[32m        targetCompatibility = JavaVersion.VERSION_1_8[m
[32m+[m[32m    }[m
[32m+[m[32m    kotlinOptions {[m
[32m+[m[32m        jvmTarget = "1.8"[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32mdependencies {[m
[32m+[m
[32m+[m[32m    implementation("androidx.core:core-ktx:1.9.0")[m
[32m+[m[32m    implementation("androidx.appcompat:appcompat:1.6.1")[m
[32m+[m[32m    implementation("com.google.android.material:material:1.11.0")[m
[32m+[m[32m    implementation("androidx.constraintlayout:constraintlayout:2.1.4")[m
[32m+[m[32m    testImplementation("junit:junit:4.13.2")[m
[32m+[m[32m    androidTestImplementation("androidx.test.ext:junit:1.1.5")[m
[32m+[m[32m    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/app/proguard-rules.pro b/app/proguard-rules.pro[m
[1mnew file mode 100644[m
[1mindex 0000000..481bb43[m
[1m--- /dev/null[m
[1m+++ b/app/proguard-rules.pro[m
[36m@@ -0,0 +1,21 @@[m
[32m+[m[32m# Add project specific ProGuard rules here.[m
[32m+[m[32m# You can control the set of applied configuration files using the[m
[32m+[m[32m# proguardFiles setting in build.gradle.[m
[32m+[m[32m#[m
[32m+[m[32m# For more details, see[m
[32m+[m[32m#   http://developer.android.com/guide/developing/tools/proguard.html[m
[32m+[m
[32m+[m[32m# If your project uses WebView with JS, uncomment the following[m
[32m+[m[32m# and specify the fully qualified class name to the JavaScript interface[m
[32m+[m[32m# class:[m
[32m+[m[32m#-keepclassmembers class fqcn.of.javascript.interface.for.webview {[m
[32m+[m[32m#   public *;[m
[32m+[m[32m#}[m
[32m+[m
[32m+[m[32m# Uncomment this to preserve the line number information for[m
[32m+[m[32m# debugging stack traces.[m
[32m+[m[32m#-keepattributes SourceFile,LineNumberTable[m
[32m+[m
[32m+[m[32m# If you keep the line number information, uncomment this to[m
[32m+[m[32m# hide the original source file name.[m
[32m+[m[32m#-renamesourcefileattribute SourceFile[m
\ No newline at end of file[m
[1mdiff --git a/app/src/androidTest/java/com/example/wallet_app/ExampleInstrumentedTest.kt b/app/src/androidTest/java/com/example/wallet_app/ExampleInstrumentedTest.kt[m
[1mnew file mode 100644[m
[1mindex 0000000..c469a5a[m
[1m--- /dev/null[m
[1m+++ b/app/src/androidTest/java/com/example/wallet_app/ExampleInstrumentedTest.kt[m
[36m@@ -0,0 +1,24 @@[m
[32m+[m[32mpackage com.example.wallet_app[m
[32m+[m
[32m+[m[32mimport androidx.test.platform.app.InstrumentationRegistry[m
[32m+[m[32mimport androidx.test.ext.junit.runners.AndroidJUnit4[m
[32m+[m
[32m+[m[32mimport org.junit.Test[m
[32m+[m[32mimport org.junit.runner.RunWith[m
[32m+[m
[32m+[m[32mimport org.junit.Assert.*[m
[32m+[m
[32m+[m[32m/**[m
[32m+[m[32m * Instrumented test, which will execute on an Android device.[m
[32m+[m[32m *[m
[32m+[m[32m * See [testing documentation](http://d.android.com/tools/testing).[m
[32m+[m[32m */[m
[32m+[m[32m@RunWith(AndroidJUnit4::class)[m
[32m+[m[32mclass ExampleInstrumentedTest {[m
[32m+[m[32m    @Test[m
[32m+[m[32m    fun useAppContext() {[m
[32m+[m[32m        // Context of the app under test.[m
[32m+[m[32m        val appContext = InstrumentationRegistry.getInstrumentation().targetContext[m
[32m+[m[32m        assertEquals("com.example.wallet_app", appContext.packageName)[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/AndroidManifest.xml b/app/src/main/AndroidManifest.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..b1e5809[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/AndroidManifest.xml[m
[36m@@ -0,0 +1,26 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?>[m
[32m+[m[32m<manifest xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m    xmlns:tools="http://schemas.android.com/tools">[m
[32m+[m
[32m+[m[32m    <application[m
[32m+[m[32m        android:allowBackup="true"[m
[32m+[m[32m        android:dataExtractionRules="@xml/data_extraction_rules"[m
[32m+[m[32m        android:fullBackupContent="@xml/backup_rules"[m
[32m+[m[32m        android:icon="@mipmap/ic_launcher"[m
[32m+[m[32m        android:label="@string/app_name"[m
[32m+[m[32m        android:roundIcon="@mipmap/ic_launcher_round"[m
[32m+[m[32m        android:supportsRtl="true"[m
[32m+[m[32m        android:theme="@style/Theme.Wallet_App"[m
[32m+[m[32m        tools:targetApi="31">[m
[32m+[m[32m        <activity[m
[32m+[m[32m            android:name=".MainActivity"[m
[32m+[m[32m            android:exported="true">[m
[32m+[m[32m            <intent-filter>[m
[32m+[m[32m                <action android:name="android.intent.action.MAIN" />[m
[32m+[m
[32m+[m[32m                <category android:name="android.intent.category.LAUNCHER" />[m
[32m+[m[32m            </intent-filter>[m
[32m+[m[32m        </activity>[m
[32m+[m[32m    </application>[m
[32m+[m
[32m+[m[32m</manifest>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/example/wallet_app/MainActivity.kt b/app/src/main/java/com/example/wallet_app/MainActivity.kt[m
[1mnew file mode 100644[m
[1mindex 0000000..364b126[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/java/com/example/wallet_app/MainActivity.kt[m
[36m@@ -0,0 +1,11 @@[m
[32m+[m[32mpackage com.example.wallet_app[m
[32m+[m
[32m+[m[32mimport androidx.appcompat.app.AppCompatActivity[m
[32m+[m[32mimport android.os.Bundle[m
[32m+[m
[32m+[m[32mclass MainActivity : AppCompatActivity() {[m
[32m+[m[32m    override fun onCreate(savedInstanceState: Bundle?) {[m
[32m+[m[32m        super.onCreate(savedInstanceState)[m
[32m+[m[32m        setContentView(R.layout.activity_main)[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/drawable/ic_launcher_background.xml b/app/src/main/res/drawable/ic_launcher_background.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..07d5da9[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/drawable/ic_launcher_background.xml[m
[36m@@ -0,0 +1,170 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?>[m
[32m+[m[32m<vector xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m    android:width="108dp"[m
[32m+[m[32m    android:height="108dp"[m
[32m+[m[32m    android:viewportWidth="108"[m
[32m+[m[32m    android:viewportHeight="108">[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#3DDC84"[m
[32m+[m[32m        android:pathData="M0,0h108v108h-108z" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M9,0L9,108"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M19,0L19,108"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M29,0L29,108"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M39,0L39,108"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M49,0L49,108"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M59,0L59,108"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M69,0L69,108"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M79,0L79,108"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M89,0L89,108"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M99,0L99,108"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M0,9L108,9"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M0,19L108,19"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M0,29L108,29"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M0,39L108,39"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M0,49L108,49"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M0,59L108,59"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M0,69L108,69"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M0,79L108,79"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M0,89L108,89"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M0,99L108,99"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M19,29L89,29"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M19,39L89,39"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M19,49L89,49"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M19,59L89,59"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M19,69L89,69"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M19,79L89,79"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M29,19L29,89"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M39,19L39,89"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M49,19L49,89"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M59,19L59,89"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M69,19L69,89"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#00000000"[m
[32m+[m[32m        android:pathData="M79,19L79,89"[m
[32m+[m[32m        android:strokeWidth="0.8"[m
[32m+[m[32m        android:strokeColor="#33FFFFFF" />[m
[32m+[m[32m</vector>[m
[1mdiff --git a/app/src/main/res/drawable/ic_launcher_foreground.xml b/app/src/main/res/drawable/ic_launcher_foreground.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..2b068d1[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/drawable/ic_launcher_foreground.xml[m
[36m@@ -0,0 +1,30 @@[m
[32m+[m[32m<vector xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m    xmlns:aapt="http://schemas.android.com/aapt"[m
[32m+[m[32m    android:width="108dp"[m
[32m+[m[32m    android:height="108dp"[m
[32m+[m[32m    android:viewportWidth="108"[m
[32m+[m[32m    android:viewportHeight="108">[m
[32m+[m[32m    <path android:pathData="M31,63.928c0,0 6.4,-11 12.1,-13.1c7.2,-2.6 26,-1.4 26,-1.4l38.1,38.1L107,108.928l-32,-1L31,63.928z">[m
[32m+[m[32m        <aapt:attr name="android:fillColor">[m
[32m+[m[32m            <gradient[m
[32m+[m[32m                android:endX="85.84757"[m
[32m+[m[32m                android:endY="92.4963"[m
[32m+[m[32m                android:startX="42.9492"[m
[32m+[m[32m                android:startY="49.59793"[m
[32m+[m[32m                android:type="linear">[m
[32m+[m[32m                <item[m
[32m+[m[32m                    android:color="#44000000"[m
[32m+[m[32m                    android:offset="0.0" />[m
[32m+[m[32m                <item[m
[32m+[m[32m                    android:color="#00000000"[m
[32m+[m[32m                    android:offset="1.0" />[m
[32m+[m[32m            </gradient>[m
[32m+[m[32m        </aapt:attr>[m
[32m+[m[32m    </path>[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="#FFFFFF"[m
[32m+[m[32m        android:fillType="nonZero"[m
[32m+[m[32m        android:pathData="M65.3,45.828l3.8,-6.6c0.2,-0.4 0.1,-0.9 -0.3,-1.1c-0.4,-0.2 -0.9,-0.1 -1.1,0.3l-3.9,6.7c-6.3,-2.8 -13.4,-2.8 -19.7,0l-3.9,-6.7c-0.2,-0.4 -0.7,-0.5 -1.1,-0.3C38.8,38.328 38.7,38.828 38.9,39.228l3.8,6.6C36.2,49.428 31.7,56.028 31,63.928h46C76.3,56.028 71.8,49.428 65.3,45.828zM43.4,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2c-0.3,-0.7 -0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C45.3,56.528 44.5,57.328 43.4,57.328L43.4,57.328zM64.6,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2s-0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C66.5,56.528 65.6,57.328 64.6,57.328L64.6,57.328z"[m
[32m+[m[32m        android:strokeWidth="1"[m
[32m+[m[32m        android:strokeColor="#00000000" />[m
[32m+[m[32m</vector>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/layout/activity_main.xml b/app/src/main/res/layout/activity_main.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..17eab17[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/layout/activity_main.xml[m
[36m@@ -0,0 +1,18 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?>[m
[32m+[m[32m<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m    xmlns:app="http://schemas.android.com/apk/res-auto"[m
[32m+[m[32m    xmlns:tools="http://schemas.android.com/tools"[m
[32m+[m[32m    android:layout_width="match_parent"[m
[32m+[m[32m    android:layout_height="match_parent"[m
[32m+[m[32m    tools:context=".MainActivity">[m
[32m+[m
[32m+[m[32m    <TextView[m
[32m+[m[32m        android:layout_width="wrap_content"[m
[32m+[m[32m        android:layout_height="wrap_content"[m
[32m+[m[32m        android:text="Hello World!"[m
[32m+[m[32m        app:layout_constraintBottom_toBottomOf="parent"[m
[32m+[m[32m        app:layout_constraintEnd_toEndOf="parent"[m
[32m+[m[32m        app:layout_constraintStart_toStartOf="parent"[m
[32m+[m[32m        app:layout_constraintTop_toTopOf="parent" />[m
[32m+[m
[32m+[m[32m</androidx.constraintlayout.widget.ConstraintLayout>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml b/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..6f3b755[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?>[m
[32m+[m[32m<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">[m
[32m+[m[32m    <background android:drawable="@drawable/ic_launcher_background" />[m
[32m+[m[32m    <foreground android:drawable="@drawable/ic_launcher_foreground" />[m
[32m+[m[32m    <monochrome android:drawable="@drawable/ic_launcher_foreground" />[m
[32m+[m[32m</adaptive-icon>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml b/app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..6f3b755[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?>[m
[32m+[m[32m<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">[m
[32m+[m[32m    <background android:drawable="@drawable/ic_launcher_background" />[m
[32m+[m[32m    <foreground android:drawable="@drawable/ic_launcher_foreground" />[m
[32m+[m[32m    <monochrome android:drawable="@drawable/ic_launcher_foreground" />[m
[32m+[m[32m</adaptive-icon>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/mipmap-hdpi/ic_launcher.webp b/app/src/main/res/mipmap-hdpi/ic_launcher.webp[m
[1mnew file mode 100644[m
[1mindex 0000000..c209e78[m
Binary files /dev/null and b/app/src/main/res/mipmap-hdpi/ic_launcher.webp differ
[1mdiff --git a/app/src/main/res/mipmap-hdpi/ic_launcher_round.webp b/app/src/main/res/mipmap-hdpi/ic_launcher_round.webp[m
[1mnew file mode 100644[m
[1mindex 0000000..b2dfe3d[m
Binary files /dev/null and b/app/src/main/res/mipmap-hdpi/ic_launcher_round.webp differ
[1mdiff --git a/app/src/main/res/mipmap-mdpi/ic_launcher.webp b/app/src/main/res/mipmap-mdpi/ic_launcher.webp[m
[1mnew file mode 100644[m
[1mindex 0000000..4f0f1d6[m
Binary files /dev/null and b/app/src/main/res/mipmap-mdpi/ic_launcher.webp differ
[1mdiff --git a/app/src/main/res/mipmap-mdpi/ic_launcher_round.webp b/app/src/main/res/mipmap-mdpi/ic_launcher_round.webp[m
[1mnew file mode 100644[m
[1mindex 0000000..62b611d[m
Binary files /dev/null and b/app/src/main/res/mipmap-mdpi/ic_launcher_round.webp differ
[1mdiff --git a/app/src/main/res/mipmap-xhdpi/ic_launcher.webp b/app/src/main/res/mipmap-xhdpi/ic_launcher.webp[m
[1mnew file mode 100644[m
[1mindex 0000000..948a307[m
Binary files /dev/null and b/app/src/main/res/mipmap-xhdpi/ic_launcher.webp differ
[1mdiff --git a/app/src/main/res/mipmap-xhdpi/ic_launcher_round.webp b/app/src/main/res/mipmap-xhdpi/ic_launcher_round.webp[m
[1mnew file mode 100644[m
[1mindex 0000000..1b9a695[m
Binary files /dev/null and b/app/src/main/res/mipmap-xhdpi/ic_launcher_round.webp differ
[1mdiff --git a/app/src/main/res/mipmap-xxhdpi/ic_launcher.webp b/app/src/main/res/mipmap-xxhdpi/ic_launcher.webp[m
[1mnew file mode 100644[m
[1mindex 0000000..28d4b77[m
Binary files /dev/null and b/app/src/main/res/mipmap-xxhdpi/ic_launcher.webp differ
[1mdiff --git a/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.webp b/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.webp[m
[1mnew file mode 100644[m
[1mindex 0000000..9287f50[m
Binary files /dev/null and b/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.webp differ
[1mdiff --git a/app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp b/app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp[m
[1mnew file mode 100644[m
[1mindex 0000000..aa7d642[m
Binary files /dev/null and b/app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp differ
[1mdiff --git a/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp b/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp[m
[1mnew file mode 100644[m
[1mindex 0000000..9126ae3[m
Binary files /dev/null and b/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp differ
[1mdiff --git a/app/src/main/res/values-night/themes.xml b/app/src/main/res/values-night/themes.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..ddf9504[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/values-night/themes.xml[m
[36m@@ -0,0 +1,7 @@[m
[32m+[m[32m<resources xmlns:tools="http://schemas.android.com/tools">[m
[32m+[m[32m    <!-- Base application theme. -->[m
[32m+[m[32m    <style name="Base.Theme.Wallet_App" parent="Theme.Material3.DayNight.NoActionBar">[m
[32m+[m[32m        <!-- Customize your dark theme here. -->[m
[32m+[m[32m        <!-- <item name="colorPrimary">@color/my_dark_primary</item> -->[m
[32m+[m[32m    </style>[m
[32m+[m[32m</resources>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/values/colors.xml b/app/src/main/res/values/colors.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..c8524cd[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/values/colors.xml[m
[36m@@ -0,0 +1,5 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?>[m
[32m+[m[32m<resources>[m
[32m+[m[32m    <color name="black">#FF000000</color>[m
[32m+[m[32m    <color name="white">#FFFFFFFF</color>[m
[32m+[m[32m</resources>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/values/strings.xml b/app/src/main/res/values/strings.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..618a290[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/values/strings.xml[m
[36m@@ -0,0 +1,3 @@[m
[32m+[m[32m<resources>[m
[32m+[m[32m    <string name="app_name">Wallet_App</string>[m
[32m+[m[32m</resources>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/values/themes.xml b/app/src/main/res/values/themes.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..cf6c8b5[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/values/themes.xml[m
[36m@@ -0,0 +1,9 @@[m
[32m+[m[32m<resources xmlns:tools="http://schemas.android.com/tools">[m
[32m+[m[32m    <!-- Base application theme. -->[m
[32m+[m[32m    <style name="Base.Theme.Wallet_App" parent="Theme.Material3.DayNight.NoActionBar">[m
[32m+[m[32m        <!-- Customize your light theme here. -->[m
[32m+[m[32m        <!-- <item name="colorPrimary">@color/my_light_primary</item> -->[m
[32m+[m[32m    </style>[m
[32m+[m
[32m+[m[32m    <style name="Theme.Wallet_App" parent="Base.Theme.Wallet_App" />[m
[32m+[m[32m</resources>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/xml/backup_rules.xml b/app/src/main/res/xml/backup_rules.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..fa0f996[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/xml/backup_rules.xml[m
[36m@@ -0,0 +1,13 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?><!--[m
[32m+[m[32m   Sample backup rules file; uncomment and customize as necessary.[m
[32m+[m[32m   See https://developer.android.com/guide/topics/data/autobackup[m
[32m+[m[32m   for details.[m
[32m+[m[32m   Note: This file is ignored for devices older that API 31[m
[32m+[m[32m   See https://developer.android.com/about/versions/12/backup-restore[m
[32m+[m[32m-->[m
[32m+[m[32m<full-backup-content>[m
[32m+[m[32m    <!--[m
[32m+[m[32m   <include domain="sharedpref" path="."/>[m
[32m+[m[32m   <exclude domain="sharedpref" path="device.xml"/>[m
[32m+[m[32m-->[m
[32m+[m[32m</full-backup-content>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/xml/data_extraction_rules.xml b/app/src/main/res/xml/data_extraction_rules.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..9ee9997[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/res/xml/data_extraction_rules.xml[m
[36m@@ -0,0 +1,19 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?><!--[m
[32m+[m[32m   Sample data extraction rules file; uncomment and customize as necessary.[m
[32m+[m[32m   See https://developer.android.com/about/versions/12/backup-restore#xml-changes[m
[32m+[m[32m   for details.[m
[32m+[m[32m-->[m
[32m+[m[32m<data-extraction-rules>[m
[32m+[m[32m    <cloud-backup>[m
[32m+[m[32m        <!-- TODO: Use <include> and <exclude> to control what is backed up.[m
[32m+[m[32m        <include .../>[m
[32m+[m[32m        <exclude .../>[m
[32m+[m[32m        -->[m
[32m+[m[32m    </cloud-backup>[m
[32m+[m[32m    <!--[m
[32m+[m[32m    <device-transfer>[m
[32m+[m[32m        <include .../>[m
[32m+[m[32m        <exclude .../>[m
[32m+[m[32m    </device-transfer>[m
[32m+[m[32m    -->[m
[32m+[m[32m</data-extraction-rules>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/test/java/com/example/wallet_app/ExampleUnitTest.kt b/app/src/test/java/com/example/wallet_app/ExampleUnitTest.kt[m
[1mnew file mode 100644[m
[1mindex 0000000..8ecce90[m
[1m--- /dev/null[m
[1m+++ b/app/src/test/java/com/example/wallet_app/ExampleUnitTest.kt[m
[36m@@ -0,0 +1,17 @@[m
[32m+[m[32mpackage com.example.wallet_app[m
[32m+[m
[32m+[m[32mimport org.junit.Test[m
[32m+[m
[32m+[m[32mimport org.junit.Assert.*[m
[32m+[m
[32m+[m[32m/**[m
[32m+[m[32m * Example local unit test, which will execute on the development machine (host).[m
[32m+[m[32m *[m
[32m+[m[32m * See [testing documentation](http://d.android.com/tools/testing).[m
[32m+[m[32m */[m
[32m+[m[32mclass ExampleUnitTest {[m
[32m+[m[32m    @Test[m
[32m+[m[32m    fun addition_isCorrect() {[m
[32m+[m[32m        assertEquals(4, 2 + 2)[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/build.gradle.kts b/build.gradle.kts[m
[1mnew file mode 100644[m
[1mindex 0000000..6d9d338[m
[1m--- /dev/null[m
[1m+++ b/build.gradle.kts[m
[36m@@ -0,0 +1,5 @@[m
[32m+[m[32m// Top-level build file where you can add configuration options common to all sub-projects/modules.[m
[32m+[m[32mplugins {[m
[32m+[m[32m    id("com.android.application") version "8.1.1" apply false[m
[32m+[m[32m    id("org.jetbrains.kotlin.android") version "1.9.0" apply false[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/gradle.properties b/gradle.properties[m
[1mnew file mode 100644[m
[1mindex 0000000..3c5031e[m
[1m--- /dev/null[m
[1m+++ b/gradle.properties[m
[36m@@ -0,0 +1,23 @@[m
[32m+[m[32m# Project-wide Gradle settings.[m
[32m+[m[32m# IDE (e.g. Android Studio) users:[m
[32m+[m[32m# Gradle settings configured through the IDE *will override*[m
[32m+[m[32m# any settings specified in this file.[m
[32m+[m[32m# For more details on how to configure your build environment visit[m
[32m+[m[32m# http://www.gradle.org/docs/current/userguide/build_environment.html[m
[32m+[m[32m# Specifies the JVM arguments used for the daemon process.[m
[32m+[m[32m# The setting is particularly useful for tweaking memory settings.[m
[32m+[m[32morg.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8[m
[32m+[m[32m# When configured, Gradle will run in incubating parallel mode.[m
[32m+[m[32m# This option should only be used with decoupled projects. More details, visit[m
[32m+[m[32m# http://www.gradle.org/docs/current/userguide/multi_project_builds.html#sec:decoupled_projects[m
[32m+[m[32m# org.gradle.parallel=true[m
[32m+[m[32m# AndroidX package structure to make it clearer which packages are bundled with the[m
[32m+[m[32m# Android operating system, and which are packaged with your app's APK[m
[32m+[m[32m# https://developer.android.com/topic/libraries/support-library/androidx-rn[m
[32m+[m[32mandroid.useAndroidX=true[m
[32m+[m[32m# Kotlin code style for this project: "official" or "obsolete":[m
[32m+[m[32mkotlin.code.style=official[m
[32m+[m[32m# Enables namespacing of each library's R class so that its R class includes only the[m
[32m+[m[32m# resources declared in the library itself and none from the library's dependencies,[m
[32m+[m[32m# thereby reducing the size of the R class for that library[m
[32m+[m[32mandroid.nonTransitiveRClass=true[m
\ No newline at end of file[m
[1mdiff --git a/gradle/wrapper/gradle-wrapper.jar b/gradle/wrapper/gradle-wrapper.jar[m
[1mnew file mode 100644[m
[1mindex 0000000..e708b1c[m
Binary files /dev/null and b/gradle/wrapper/gradle-wrapper.jar differ
[1mdiff --git a/gradle/wrapper/gradle-wrapper.properties b/gradle/wrapper/gradle-wrapper.properties[m
[1mnew file mode 100644[m
[1mindex 0000000..b77d7cd[m
[1m--- /dev/null[m
[1m+++ b/gradle/wrapper/gradle-wrapper.properties[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m#Tue Jan 02 17:15:33 CET 2024[m
[32m+[m[32mdistributionBase=GRADLE_USER_HOME[m
[32m+[m[32mdistributionPath=wrapper/dists[m
[32m+[m[32mdistributionUrl=https\://services.gradle.org/distributions/gradle-8.0-bin.zip[m
[32m+[m[32mzipStoreBase=GRADLE_USER_HOME[m
[32m+[m[32mzipStorePath=wrapper/dists[m
[1mdiff --git a/gradlew b/gradlew[m
[1mnew file mode 100644[m
[1mindex 0000000..4f906e0[m
[1m--- /dev/null[m
[1m+++ b/gradlew[m
[36m@@ -0,0 +1,185 @@[m
[32m+[m[32m#!/usr/bin/env sh[m
[32m+[m
[32m+[m[32m#[m
[32m+[m[32m# Copyright 2015 the original author or authors.[m
[32m+[m[32m#[m
[32m+[m[32m# Licensed under the Apache License, Version 2.0 (the "License");[m
[32m+[m[32m# you may not use this file except in compliance with the License.[m
[32m+[m[32m# You may obtain a copy of the License at[m
[32m+[m[32m#[m
[32m+[m[32m#      https://www.apache.org/licenses/LICENSE-2.0[m
[32m+[m[32m#[m
[32m+[m[32m# Unless required by applicable law or agreed to in writing, software[m
[32m+[m[32m# distributed under the License is distributed on an "AS IS" BASIS,[m
[32m+[m[32m# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.[m
[32m+[m[32m# See the License for the specific language governing permissions and[m
[32m+[m[32m# limitations under the License.[m
[32m+[m[32m#[m
[32m+[m
[32m+[m[32m##############################################################################[m
[32m+[m[32m##[m
[32m+[m[32m##  Gradle start up script for UN*X[m
[32m+[m[32m##[m
[32m+[m[32m##############################################################################[m
[32m+[m
[32m+[m[32m# Attempt to set APP_HOME[m
[32m+[m[32m# Resolve links: $0 may be a link[m
[32m+[m[32mPRG="$0"[m
[32m+[m[32m# Need this for relative symlinks.[m
[32m+[m[32mwhile [ -h "$PRG" ] ; do[m
[32m+[m[32m    ls=`ls -ld "$PRG"`[m
[32m+[m[32m    link=`expr "$ls" : '.*-> \(.*\)$'`[m
[32m+[m[32m    if expr "$link" : '/.*' > /dev/null; then[m
[32m+[m[32m        PRG="$link"[m
[32m+[m[32m    else[m
[32m+[m[32m        PRG=`dirname "$PRG"`"/$link"[m
[32m+[m[32m    fi[m
[32m+[m[32mdone[m
[32m+[m[32mSAVED="`pwd`"[m
[32m+[m[32mcd "`dirname \"$PRG\"`/" >/dev/null[m
[32m+[m[32mAPP_HOME="`pwd -P`"[m
[32m+[m[32mcd "$SAVED" >/dev/null[m
[32m+[m
[32m+[m[32mAPP_NAME="Gradle"[m
[32m+[m[32mAPP_BASE_NAME=`basename "$0"`[m
[32m+[m
[32m+[m[32m# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.[m
[32m+[m[32mDEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'[m
[32m+[m
[32m+[m[32m# Use the maximum available, or set MAX_FD != -1 to use that value.[m
[32m+[m[32mMAX_FD="maximum"[m
[32m+[m
[32m+[m[32mwarn () {[m
[32m+[m[32m    echo "$*"[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32mdie () {[m
[32m+[m[32m    echo[m
[32m+[m[32m    echo "$*"[m
[32m+[m[32m    echo[m
[32m+[m[32m    exit 1[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32m# OS specific support (must be 'true' or 'false').[m
[32m+[m[32mcygwin=false[m
[32m+[m[32mmsys=false[m
[32m+[m[32mdarwin=false[m
[32m+[m[32mnonstop=false[m
[32m+[m[32mcase "`uname`" in[m
[32m+[m[32m  CYGWIN* )[m
[32m+[m[32m    cygwin=true[m
[32m+[m[32m    ;;[m
[32m+[m[32m  Darwin* )[m
[32m+[m[32m    darwin=true[m
[32m+[m[32m    ;;[m
[32m+[m[32m  MINGW* )[m
[32m+[m[32m    msys=true[m
[32m+[m[32m    ;;[m
[32m+[m[32m  NONSTOP* )[m
[32m+[m[32m    nonstop=true[m
[32m+[m[32m    ;;[m
[32m+[m[32mesac[m
[32m+[m
[32m+[m[32mCLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar[m
[32m+[m
[32m+[m
[32m+[m[32m# Determine the Java command to use to start the JVM.[m
[32m+[m[32mif [ -n "$JAVA_HOME" ] ; then[m
[32m+[m[32m    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then[m
[32m+[m[32m        # IBM's JDK on AIX uses strange locations for the executables[m
[32m+[m[32m        JAVACMD="$JAVA_HOME/jre/sh/java"[m
[32m+[m[32m    else[m
[32m+[m[32m        JAVACMD="$JAVA_HOME/bin/java"[m
[32m+[m[32m    fi[m
[32m+[m[32m    if [ ! -x "$JAVACMD" ] ; then[m
[32m+[m[32m        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME[m
[32m+[m
[32m+[m[32mPlease set the JAVA_HOME variable in your environment to match the[m
[32m+[m[32mlocation of your Java installation."[m
[32m+[m[32m    fi[m
[32m+[m[32melse[m
[32m+[m[32m    JAVACMD="java"[m
[32m+[m[32m    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.[m
[32m+[m
[32m+[m[32mPlease set the JAVA_HOME variable in your environment to match the[m
[32m+[m[32mlocation of your Java installation."[m
[32m+[m[32mfi[m
[32m+[m
[32m+[m[32m# Increase the maximum file descriptors if we can.[m
[32m+[m[32mif [ "$cygwin" = "false" -a "$darwin" = "false" -a "$nonstop" = "false" ] ; then[m
[32m+[m[32m    MAX_FD_LIMIT=`ulimit -H -n`[m
[32m+[m[32m    if [ $? -eq 0 ] ; then[m
[32m+[m[32m        if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "max" ] ; then[m
[32m+[m[32m            MAX_FD="$MAX_FD_LIMIT"[m
[32m+[m[32m        fi[m
[32m+[m[32m        ulimit -n $MAX_FD[m
[32m+[m[32m        if [ $? -ne 0 ] ; then[m
[32m+[m[32m            warn "Could not set maximum file descriptor limit: $MAX_FD"[m
[32m+[m[32m        fi[m
[32m+[m[32m    else[m
[32m+[m[32m        warn "Could not query maximum file descriptor limit: $MAX_FD_LIMIT"[m
[32m+[m[32m    fi[m
[32m+[m[32mfi[m
[32m+[m
[32m+[m[32m# For Darwin, add options to specify how the application appears in the dock[m
[32m+[m[32mif $darwin; then[m
[32m+[m[32m    GRADLE_OPTS="$GRADLE_OPTS \"-Xdock:name=$APP_NAME\" \"-Xdock:icon=$APP_HOME/media/gradle.icns\""[m
[32m+[m[32mfi[m
[32m+[m
[32m+[m[32m# For Cygwin or MSYS, switch paths to Windows format before running java[m
[32m+[m[32mif [ "$cygwin" = "true" -o "$msys" = "true" ] ; then[m
[32m+[m[32m    APP_HOME=`cygpath --path --mixed "$APP_HOME"`[m
[32m+[m[32m    CLASSPATH=`cygpath --path --mixed "$CLASSPATH"`[m
[32m+[m
[32m+[m[32m    JAVACMD=`cygpath --unix "$JAVACMD"`[m
[32m+[m
[32m+[m[32m    # We build the pattern for arguments to be converted via cygpath[m
[32m+[m[32m    ROOTDIRSRAW=`find -L / -maxdepth 1 -mindepth 1 -type d 2>/dev/null`[m
[32m+[m[32m    SEP=""[m
[32m+[m[32m    for dir in $ROOTDIRSRAW ; do[m
[32m+[m[32m        ROOTDIRS="$ROOTDIRS$SEP$dir"[m
[32m+[m[32m        SEP="|"[m
[32m+[m[32m    done[m
[32m+[m[32m    OURCYGPATTERN="(^($ROOTDIRS))"[m
[32m+[m[32m    # Add a user-defined pattern to the cygpath arguments[m
[32m+[m[32m    if [ "$GRADLE_CYGPATTERN" != "" ] ; then[m
[32m+[m[32m        OURCYGPATTERN="$OURCYGPATTERN|($GRADLE_CYGPATTERN)"[m
[32m+[m[32m    fi[m
[32m+[m[32m    # Now convert the arguments - kludge to limit ourselves to /bin/sh[m
[32m+[m[32m    i=0[m
[32m+[m[32m    for arg in "$@" ; do[m
[32m+[m[32m        CHECK=`echo "$arg"|egrep -c "$OURCYGPATTERN" -`[m
[32m+[m[32m        CHECK2=`echo "$arg"|egrep -c "^-"`                                 ### Determine if an option[m
[32m+[m
[32m+[m[32m        if [ $CHECK -ne 0 ] && [ $CHECK2 -eq 0 ] ; then                    ### Added a condition[m
[32m+[m[32m            eval `echo args$i`=`cygpath --path --ignore --mixed "$arg"`[m
[32m+[m[32m        else[m
[32m+[m[32m            eval `echo args$i`="\"$arg\""[m
[32m+[m[32m        fi[m
[32m+[m[32m        i=`expr $i + 1`[m
[32m+[m[32m    done[m
[32m+[m[32m    case $i in[m
[32m+[m[32m        0) set -- ;;[m
[32m+[m[32m        1) set -- "$args0" ;;[m
[32m+[m[32m        2) set -- "$args0" "$args1" ;;[m
[32m+[m[32m        3) set -- "$args0" "$args1" "$args2" ;;[m
[32m+[m[32m        4) set -- "$args0" "$args1" "$args2" "$args3" ;;[m
[32m+[m[32m        5) set -- "$args0" "$args1" "$args2" "$args3" "$args4" ;;[m
[32m+[m[32m        6) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" ;;[m
[32m+[m[32m        7) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" ;;[m
[32m+[m[32m        8) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" "$args7" ;;[m
[32m+[m[32m        9) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" "$args7" "$args8" ;;[m
[32m+[m[32m    esac[m
[32m+[m[32mfi[m
[32m+[m
[32m+[m[32m# Escape application args[m
[32m+[m[32msave () {[m
[32m+[m[32m    for i do printf %s\\n "$i" | sed "s/'/'\\\\''/g;1s/^/'/;\$s/\$/' \\\\/" ; done[m
[32m+[m[32m    echo " "[m
[32m+[m[32m}[m
[32m+[m[32mAPP_ARGS=`save "$@"`[m
[32m+[m
[32m+[m[32m# Collect all arguments for the java command, following the shell quoting and substitution rules[m
[32m+[m[32meval set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS "\"-Dorg.gradle.appname=$APP_BASE_NAME\"" -classpath "\"$CLASSPATH\"" org.gradle.wrapper.GradleWrapperMain "$APP_ARGS"[m
[32m+[m
[32m+[m[32mexec "$JAVACMD" "$@"[m
[1mdiff --git a/gradlew.bat b/gradlew.bat[m
[1mnew file mode 100644[m
[1mindex 0000000..107acd3[m
[1m--- /dev/null[m
[1m+++ b/gradlew.bat[m
[36m@@ -0,0 +1,89 @@[m
[32m+[m[32m@rem[m
[32m+[m[32m@rem Copyright 2015 the original author or authors.[m
[32m+[m[32m@rem[m
[32m+[m[32m@rem Licensed under the Apache License, Version 2.0 (the "License");[m
[32m+[m[32m@rem you may not use this file except in compliance with the License.[m
[32m+[m[32m@rem You may obtain a copy of the License at[m
[32m+[m[32m@rem[m
[32m+[m[32m@rem      https://www.apache.org/licenses/LICENSE-2.0[m
[32m+[m[32m@rem[m
[32m+[m[32m@rem Unless required by applicable law or agreed to in writing, software[m
[32m+[m[32m@rem distributed under the License is distributed on an "AS IS" BASIS,[m
[32m+[m[32m@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.[m
[32m+[m[32m@rem See the License for the specific language governing permissions and[m
[32m+[m[32m@rem limitations under the License.[m
[32m+[m[32m@rem[m
[32m+[m
[32m+[m[32m@if "%DEBUG%" == "" @echo off[m
[32m+[m[32m@rem ##########################################################################[m
[32m+[m[32m@rem[m
[32m+[m[32m@rem  Gradle startup script for Windows[m
[32m+[m[32m@rem[m
[32m+[m[32m@rem ##########################################################################[m
[32m+[m
[32m+[m[32m@rem Set local scope for the variables with windows NT shell[m
[32m+[m[32mif "%OS%"=="Windows_NT" setlocal[m
[32m+[m
[32m+[m[32mset DIRNAME=%~dp0[m
[32m+[m[32mif "%DIRNAME%" == "" set DIRNAME=.[m
[32m+[m[32mset APP_BASE_NAME=%~n0[m
[32m+[m[32mset APP_HOME=%DIRNAME%[m
[32m+[m
[32m+[m[32m@rem Resolve any "." and ".." in APP_HOME to make it shorter.[m
[32m+[m[32mfor %%i in ("%APP_HOME%") do set APP_HOME=%%~fi[m
[32m+[m
[32m+[m[32m@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.[m
[32m+[m[32mset DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"[m
[32m+[m
[32m+[m[32m@rem Find java.exe[m
[32m+[m[32mif defined JAVA_HOME goto findJavaFromJavaHome[m
[32m+[m
[32m+[m[32mset JAVA_EXE=java.exe[m
[32m+[m[32m%JAVA_EXE% -version >NUL 2>&1[m
[32m+[m[32mif "%ERRORLEVEL%" == "0" goto execute[m
[32m+[m
[32m+[m[32mecho.[m
[32m+[m[32mecho ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.[m
[32m+[m[32mecho.[m
[32m+[m[32mecho Please set the JAVA_HOME variable in your environment to match the[m
[32m+[m[32mecho location of your Java installation.[m
[32m+[m
[32m+[m[32mgoto fail[m
[32m+[m
[32m+[m[32m:findJavaFromJavaHome[m
[32m+[m[32mset JAVA_HOME=%JAVA_HOME:"=%[m
[32m+[m[32mset JAVA_EXE=%JAVA_HOME%/bin/java.exe[m
[32m+[m
[32m+[m[32mif exist "%JAVA_EXE%" goto execute[m
[32m+[m
[32m+[m[32mecho.[m
[32m+[m[32mecho ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%[m
[32m+[m[32mecho.[m
[32m+[m[32mecho Please set the JAVA_HOME variable in your environment to match the[m
[32m+[m[32mecho location of your Java installation.[m
[32m+[m
[32m+[m[32mgoto fail[m
[32m+[m
[32m+[m[32m:execute[m
[32m+[m[32m@rem Setup the command line[m
[32m+[m
[32m+[m[32mset CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar[m
[32m+[m
[32m+[m
[32m+[m[32m@rem Execute Gradle[m
[32m+[m[32m"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*[m
[32m+[m
[32m+[m[32m:end[m
[32m+[m[32m@rem End local scope for the variables with windows NT shell[m
[32m+[m[32mif "%ERRORLEVEL%"=="0" goto mainEnd[m
[32m+[m
[32m+[m[32m:fail[m
[32m+[m[32mrem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of[m
[32m+[m[32mrem the _cmd.exe /c_ return code![m
[32m+[m[32mif  not "" == "%GRADLE_EXIT_CONSOLE%" exit 1[m
[32m+[m[32mexit /b 1[m
[32m+[m
[32m+[m[32m:mainEnd[m
[32m+[m[32mif "%OS%"=="Windows_NT" endlocal[m
[32m+[m
[32m+[m[32m:omega[m
[1mdiff --git a/settings.gradle.kts b/settings.gradle.kts[m
[1mnew file mode 100644[m
[1mindex 0000000..c0a8480[m
[1m--- /dev/null[m
[1m+++ b/settings.gradle.kts[m
[36m@@ -0,0 +1,18 @@[m
[32m+[m[32mpluginManagement {[m
[32m+[m[32m    repositories {[m
[32m+[m[32m        google()[m
[32m+[m[32m        mavenCentral()[m
[32m+[m[32m        gradlePluginPortal()[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m[32mdependencyResolutionManagement {[m
[32m+[m[32m    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)[m
[32m+[m[32m    repositories {[m
[32m+[m[32m        google()[m
[32m+[m[32m        mavenCentral()[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32mrootProject.name = "Wallet_App"[m
[32m+[m[32minclude(":app")[m
[32m+[m[41m [m
\ No newline at end of file[m
