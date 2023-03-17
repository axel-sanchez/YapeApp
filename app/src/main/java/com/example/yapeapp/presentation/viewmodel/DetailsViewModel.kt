package com.example.yapeapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.yapeapp.domain.usecase.GetRecipeUseCase
import com.example.yapeapp.data.model.Recipe
import kotlinx.coroutines.launch

/**
 * @author Axel Sanchez
 */
class DetailsViewModel(private val getRecipeUseCase: GetRecipeUseCase) : ViewModel() {

    private val recipeLiveData: MutableLiveData<Recipe?> = MutableLiveData<Recipe?>()

    private fun setListData(result: Recipe?) {
        recipeLiveData.postValue(result)
    }

    fun getRecipe(idRecipe: Long) {
        viewModelScope.launch {
            setListData(getRecipeUseCase.call(idRecipe))
        }
    }

    fun getProductLiveData(): LiveData<Recipe?> {
        return recipeLiveData
    }

    class DetailsViewModelFactory(private val getRecipeUseCase: GetRecipeUseCase) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetRecipeUseCase::class.java)
                .newInstance(getRecipeUseCase)
        }
    }
}