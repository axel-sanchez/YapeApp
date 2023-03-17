package com.example.yapeapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.yapeapp.core.MyApplication
import com.example.yapeapp.data.model.Recipe
import com.example.yapeapp.databinding.FragmentDetailsBinding
import com.example.yapeapp.domain.usecase.GetRecipeUseCase
import com.example.yapeapp.helpers.load
import com.example.yapeapp.presentation.RecipesFragment.Companion.ID_RECIPE
import com.example.yapeapp.presentation.viewmodel.DetailsViewModel
import javax.inject.Inject

/**
 * @author Axel Sanchez
 */
class DetailsFragment : Fragment() {
    @Inject
    lateinit var getRecipeUseCase: GetRecipeUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApplication).component.inject(this)
    }

    private val viewModel: DetailsViewModel by viewModels(
        factoryProducer = { DetailsViewModel.DetailsViewModelFactory(getRecipeUseCase) }
    )

    private var fragmentDetailsBinding: FragmentDetailsBinding? = null
    private val binding get() = fragmentDetailsBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentDetailsBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idProduct = arguments?.getLong(ID_RECIPE)

        idProduct?.let {
            viewModel.getRecipe(idProduct)

            viewModel.getProductLiveData().observe(viewLifecycleOwner) { recipe ->
                updateView(recipe)
            }
        }
    }

    private fun updateView(product: Recipe?) {
        with(binding){
            product?.let {
                name.text = it.name
                description.text = it.description
                imageView.load(it.image)
            }
        }
    }
}