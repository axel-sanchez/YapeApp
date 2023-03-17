package com.example.yapeapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.yapeapp.data.model.Recipe
import com.example.yapeapp.domain.usecase.GetAllRecipesUseCase
import com.example.yapeapp.helpers.Constants
import com.example.yapeapp.helpers.Either
import kotlinx.coroutines.launch

/**
 * @author Axel Sanchez
 */
class RecipesViewModel(private val getAllRecipesUseCase: GetAllRecipesUseCase): ViewModel() {

    private val listData: MutableLiveData<Either<Constants.ApiError, List<Recipe?>>> by lazy {
        MutableLiveData<Either<Constants.ApiError, List<Recipe?>>>().also {
            getRecipe()
        }
    }

    private fun setListData(result: Either<Constants.ApiError, List<Recipe?>>) {
        listData.postValue(result)
    }

    private fun getRecipe() {
        viewModelScope.launch {
            setListData(getAllRecipesUseCase.call())
        }
    }

    fun getRecipeLiveData(): LiveData<Either<Constants.ApiError, List<Recipe?>>> {
        return listData
    }

    class RecipeViewModelFactory(private val getAllRecipesUseCase: GetAllRecipesUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetAllRecipesUseCase::class.java).newInstance(getAllRecipesUseCase)
        }
    }
}