package com.palchak.sergey.hiltandroid

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.palchak.sergey.hiltandroid.di.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton


@UninstallModules(AppModule::class)
@HiltAndroidTest
class MainTest {

    @Inject lateinit var someString: String

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun someString() {
        assertThat(someString, containsString("TESTING"))
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object TestAppModule {

        @Singleton
        @Provides
        fun provideSomeString() = "It's a TESTING String!"
    }
}