package com.marvel.characters.domain.usecases

import com.marvel.characters.data.model.CharacterDetail
import com.marvel.characters.ui.utils.Resource

interface GetCharacterDetailsUseCase {

    suspend operator fun invoke(id:String): Resource<CharacterDetail>

}