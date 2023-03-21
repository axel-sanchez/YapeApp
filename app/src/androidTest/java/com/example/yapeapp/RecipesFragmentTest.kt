package com.example.yapeapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.yapeapp.presentation.MainActivity
import com.example.yapeapp.presentation.adapters.RecipeAdapter
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class RecipesFragmentTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun should_show_recyclerview_and_hide_progress_and_message() {
        onView(withId(R.id.rvRecipes)).check(matches(isDisplayed()))
        onView(withId(R.id.CPILoading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.cvEmptyState)).check(matches(not(isDisplayed())))
    }

    @Test
    fun should_show_product_details_when_click_item() {
        onView(withId(R.id.rvRecipes)).perform(RecyclerViewActions.actionOnItemAtPosition<RecipeAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.tvName)).check(matches(withText("Ceviche")))
    }

    @Test
    fun should_show_recyclerview_when_press_back_from_details_fragment() {
        onView(withId(R.id.rvRecipes)).perform(RecyclerViewActions.actionOnItemAtPosition<RecipeAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.tvName)).check(matches(withText("Ceviche")))
        pressBack()
        onView(withId(R.id.rvRecipes)).check(matches(isDisplayed()))
        onView(withId(R.id.CPILoading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.cvEmptyState)).check(matches(not(isDisplayed())))
    }
}