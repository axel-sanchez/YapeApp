package com.example.yapeapp

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.yapeapp.data.model.Recipe
import com.example.yapeapp.presentation.DetailsFragment
import com.example.yapeapp.presentation.RecipesFragment.Companion.ID_RECIPE
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class DetailsFragmentTest{

    private val recipe = Recipe(
        1,
        "Ceviche",
        "El cebiche, ceviche, sebiche o seviche \u200B es un plato consistente en carne marinada ―pescado, mariscos o ambos― en aliños cítricos",
        "-5.177340343212625",
        "-80.65550875477962",
        "https://cdn0.recetasgratis.net/es/posts/7/4/1/ceviche_peruano_18147_orig.jpg"
    )

    @Test
    fun should_show_recipe_title_and_description() {
        val bundle = bundleOf(ID_RECIPE to recipe.id)
        val scenario = launchFragmentInContainer<DetailsFragment>(
            fragmentArgs = bundle
        )

        scenario.withFragment {
            this.updateView(recipe)
        }

        onView(withId(R.id.tvName)).check(matches(withText(recipe.name)))
        onView(withId(R.id.tvDescription)).check(matches(withText(recipe.description)))
    }
}