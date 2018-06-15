<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="${packageName}"
    >
    <application
        android:name="me.walten.fastgo.base.application.BaseApplication"
        android:theme="@style/BaseAppTheme">
        <meta-data
            android:name="${packageName}.configuration.GlobalConfiguration"
            android:value="ConfigModule" />

    </application>

</manifest>