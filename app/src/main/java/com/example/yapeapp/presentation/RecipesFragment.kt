package com.example.yapeapp.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yapeapp.R
import com.example.yapeapp.core.MyApplication
import com.example.yapeapp.data.model.Recipe
import com.example.yapeapp.databinding.FragmentRecipesBinding
import com.example.yapeapp.domain.usecase.GetAllRecipesUseCase
import com.example.yapeapp.helpers.Constants
import com.example.yapeapp.helpers.Either
import com.example.yapeapp.helpers.hide
import com.example.yapeapp.helpers.show
import com.example.yapeapp.presentation.adapters.RecipeAdapter
import com.example.yapeapp.presentation.viewmodel.RecipesViewModel
import java.util.*
import javax.inject.Inject

/**
 * @author Axel Sanchez
 */
class RecipesFragment : Fragment() {
    @Inject
    lateinit var getAllRecipesUseCase: GetAllRecipesUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApplication).component.inject(this)
    }

    private val viewModel: RecipesViewModel by viewModels(
        factoryProducer = { RecipesViewModel.RecipeViewModelFactory(getAllRecipesUseCase) }
    )

    private var fragmentProductBinding: FragmentRecipesBinding? = null
    private val binding get() = fragmentProductBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentProductBinding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentProductBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRecipeLiveData().observe(viewLifecycleOwner) { response ->
            updateView(response)
        }
    }

    private fun updateView(response: Either<Constants.ApiError, List<Recipe?>>?) {
        with(binding) {
            response?.fold(
                left = {
                    cvEmptyState.show()
                    tvErrorText.text = getString(R.string.error_api_products)
                    rvRecipes.hide()
                    etSearch.hide()
                }, right = {
                    if ((response as Either.Right).r.isEmpty()) {
                        rvRecipes.hide()
                        etSearch.hide()
                        tvErrorText.text = getString(R.string.there_is_not_products)
                        cvEmptyState.show()
                    } else {
                        etSearch.show()
                        rvRecipes.show()
                        setAdapter(response.r)
                    }
                }
            )
            cpiLoading.hide()
        }
    }

    private fun setAdapter(recipes: List<Recipe?>) {
        val recipeAdapter = RecipeAdapter(recipes) { itemClick(it) }
        with(binding.rvRecipes) {
            layoutManager = LinearLayoutManager(context)
            adapter = recipeAdapter
        }
        binding.etSearch.addTextChangedListener {
            val filteredList = recipes.filter { recipe ->
                recipe?.name?.lowercase(Locale.getDefault())?.contains(it.toString().lowercase(Locale.getDefault()))?:false
            }
            recipeAdapter.updateRecipes(filteredList)
        }
    }

    private fun itemClick(recipe: Recipe?) {
        val bundle = bundleOf(
            ID_RECIPE to recipe?.id
        )
        findNavController().navigate(R.id.action_recipesFragment_to_detailsFragment, bundle)
    }

    companion object {
        const val ID_RECIPE = "idRecipe"
    }
}