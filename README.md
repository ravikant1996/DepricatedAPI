# DepricatedAPI
DepricatedAPI finding

**step 1:-**
**build.gradle(project level):-**

**allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
  }
<!--     //this code passing -->
    gradle.projectsEvaluated {
        tasks.withType(JavaCompile) {
            options.compilerArgs << "-Xlint:deprecation"
        }
    }
}**

**step 2:-**
**settings.gradle:-**

**dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}**

//in this project
ProgressDialog is deprecated.What is the alternate one to use? ProgressBar
[deprecation] CONNECTIVITY_ACTION in ConnectivityManager has been deprecated??
[deprecation] getExternalStoragePublicDirectory(String) in Environment has been deprecated??
[deprecation] NetworkInfo in android.net has been deprecated??
[deprecation] NetworkInfo in android.net has been deprecated??
[deprecation] getActiveNetworkInfo() in ConnectivityManager has been deprecated??
[deprecation] getType() in NetworkInfo has been deprecated??
[deprecation] TYPE_WIFI in ConnectivityManager has been deprecated??
[deprecation] getType() in NetworkInfo has been deprecated??
[deprecation] TYPE_MOBILE in ConnectivityManager has been deprecated??
