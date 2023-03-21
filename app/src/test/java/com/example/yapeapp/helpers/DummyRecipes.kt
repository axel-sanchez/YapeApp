package com.example.yapeapp.helpers

import com.example.yapeapp.data.model.Recipe

object DummyRecipes {
    val recipe4 = Recipe(
        1,
        "Ceviche",
        "El cebiche, ceviche, sebiche o seviche \u200B es un plato consistente en carne marinada ―pescado, mariscos o ambos― en aliños cítricos",
        "-5.177340343212625",
        "-80.65550875477962",
        "https://cdn0.recetasgratis.net/es/posts/7/4/1/ceviche_peruano_18147_orig.jpg"
    )

    val recipe3 = Recipe(
        2,
        "Paella",
        "La paella, \u200B o también arroz a la paella, \u200B \u200B es una receta de cocina con base de arroz, con origen en la actual Comunidad Valenciana, hoy en día muy popular en toda España y servida en restaurantes de todo el mundo",
        "39.471179736560735",
        "-0.3808883347709028",
        "http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcQtbWDBpVNFWZ99Gr1_Lb1hFVY8ZKrOivYyU95f9drycn3WAFxElfsxe39zjgsjeRdz"
    )

    val recipe2 = Recipe(
        3,
        "Pollo al disco",
        "Pollo al disco es un plato argentino abundante que consiste en pollo y varias verduras cocinadas como un guiso en una sartén profunda descubierta sobre un fuego abierto",
        "-34.60283871660018",
        "-58.3914968427616",
        "https://imag.bonviveur.com/pollo-al-disco.jpg"
    )

    val recipe1 = Recipe(
        4,
        "Sushi",
        "es un plato típico de origen japonés basado en arroz aderezado con vinagre de arroz, azúcar y sal y combinado con otros ingredientes como pescados crudos, mariscos, verduras, etc.",
        "36.559435989368374",
        "138.29949348470961",
        "https://www.albal.net/portal/pics/Recetas/Sushi-Albal2_Teaser-738x595.jpg"
    )

    fun getListRecipes(): Either<Constants.ApiError, List<Recipe?>> {
        val listRecipes = arrayListOf<Recipe?>(recipe1, recipe2, recipe3, recipe4)
        return Either.Right(listRecipes.toList())
    }
}