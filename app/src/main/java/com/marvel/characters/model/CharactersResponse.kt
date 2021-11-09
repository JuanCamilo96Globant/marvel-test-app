package com.marvel.characters.model

class CharactersResponse(
    data: BaseData<List<Character>>,
    code: Int?, status: String?,
    copyright: String?,
    attributionText: String?, attributionHTML: String?,
    etag: String?
) : BaseResponse<BaseData<List<Character>>>(
    code, status, copyright, attributionText,
    attributionHTML, etag, data
)
