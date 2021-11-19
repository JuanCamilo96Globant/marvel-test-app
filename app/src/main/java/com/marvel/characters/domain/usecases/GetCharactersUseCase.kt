package com.marvel.characters.domain.usecases

import com.marvel.characters.data.model.Character
import com.marvel.characters.ui.utils.Resource

interface GetCharactersUseCase {

    suspend operator fun invoke(): Resource<List<Character>>

}