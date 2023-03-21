package com.example.yapeapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.yapeapp.helpers.DummyRecipes.getListRecipes
import com.example.yapeapp.helpers.DummyRecipes.recipe1
import com.example.yapeapp.helpers.DummyRecipes.recipe2
import com.example.yapeapp.helpers.DummyRecipes.recipe3
import com.example.yapeapp.helpers.DummyRecipes.recipe4
import com.example.yapeapp.data.repository.RecipeRepositoryImpl
import com.example.yapeapp.data.source.RecipeLocalSource
import com.example.yapeapp.data.source.RecipeRemoteSource
import com.example.yapeapp.domain.repository.RecipeRepository
import com.example.yapeapp.helpers.Either
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RecipeRepositoryImplTest {

    private val recipeRemoteSource: RecipeRemoteSource = mock(RecipeRemoteSource::class.java)
    private val recipeLocalSource: RecipeLocalSource = mock(RecipeLocalSource::class.java)
    private val recipeRepository: RecipeRepository = RecipeRepositoryImpl(recipeRemoteSource, recipeLocalSource)

    @Test
    fun should_return_recipe_list_sorted_by_title() {
        runBlocking {
            given(recipeRepository.getLocalRecipes()).willReturn(listOf())

            val mutableListData = MutableLiveData(getListRecipes())
            given(recipeRemoteSource.getRecipes()).willReturn(mutableListData)

            given(recipeLocalSource.insertRecipes(recipe1)).willReturn(1)
            given(recipeLocalSource.insertRecipes(recipe2)).willReturn(2)
            given(recipeLocalSource.insertRecipes(recipe3)).willReturn(3)
            given(recipeLocalSource.insertRecipes(recipe4)).willReturn(4)

            val result = recipeRepository.getAllRecipes()
            assertThat((result as Either.Right).r, contains(recipe4, recipe3, recipe2, recipe1))
        }
    }

    @Test
        fun should_calls_to_getRemoteRecipes_when_there_are_not_local_recipes(){
        runBlocking {
            val mutableListData = MutableLiveData(getListRecipes())
            given(recipeRemoteSource.getRecipes()).willReturn(mutableListData)

            given(recipeLocalSource.insertRecipes(recipe1)).willReturn(1)
            given(recipeLocalSource.insertRecipes(recipe2)).willReturn(2)
            given(recipeLocalSource.insertRecipes(recipe3)).willReturn(3)
            given(recipeLocalSource.insertRecipes(recipe4)).willReturn(4)

            given(recipeRepository.getLocalRecipes()).willReturn(listOf())

            recipeRepository.getAllRecipes()
            verify(recipeRemoteSource).getRecipes()
        }
    }

    @Test
    fun should_not_calls_to_getRemoteRecipes_when_there_are_local_recipes(){
        runBlocking {
            val mutableListData = MutableLiveData(getListRecipes())
            given(recipeRemoteSource.getRecipes()).willReturn(mutableListData)

            given(recipeLocalSource.insertRecipes(recipe1)).willReturn(1)
            given(recipeLocalSource.insertRecipes(recipe2)).willReturn(2)
            given(recipeLocalSource.insertRecipes(recipe3)).willReturn(3)
            given(recipeLocalSource.insertRecipes(recipe4)).willReturn(4)

            given(recipeRepository.getLocalRecipes()).willReturn(listOf(recipe1))

            recipeRepository.getAllRecipes()
            verify(recipeRemoteSource, never()).getRecipes()
        }
    }
}