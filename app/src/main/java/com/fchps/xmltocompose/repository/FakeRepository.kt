package com.fchps.xmltocompose.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

//class FakeRepository : MessageRepository {
//    private val _message = MutableLiveData<String?>("")
//    private val predefinedMessages = listOf(
//        "Profitez de 20% de réduction sur votre prochaine commande !",
//        "Exclusivité : Achetez-en 2 et recevez-en 1 gratuit, seulement cette semaine !",
//        "N'attendez plus : des offres incroyables vous attendent aujourd'hui.",
//        "Livraison gratuite pour toutes les commandes de plus de 50€ !",
//        "Découvrez notre nouvelle collection printemps-été et bénéficiez de remises exclusives.",
//        "Un cadeau surprise pour chaque commande passée avant minuit !",
//        "Recevez 10€ de remise pour votre première commande avec le code BIENVENUE.",
//        "C'est les soldes : jusqu'à 50% de réduction sur une sélection d'articles.",
//        "Chaque minute compte : ces offres ne dureront pas longtemps !",
//        "Inscrivez-vous à notre newsletter et obtenez 15% de réduction immédiate.",
//        "Offre spéciale : économisez jusqu'à 30% sur nos best-sellers.",
//        "Parrainez un ami et recevez chacun 10€ de crédit.",
//        "Les offres de fin de saison sont là : tout doit disparaître !",
//        "Achetez maintenant et payez en 3 fois sans frais.",
//        "Envie de renouveau ? Découvrez nos packs exclusifs à prix cassés."
//    )
//    private var currentIndex = 0
//
//    override val message: LiveData<String?>
//        get() = _message
//
//    override fun updateMessage(newMessage: String?) {
//        _message.value = newMessage
//    }
//
//    fun getNextMessage(): String {
//        val nextMessage = predefinedMessages[currentIndex]
//        currentIndex = (currentIndex + 1) % predefinedMessages.size
//        return nextMessage
//    }
//}