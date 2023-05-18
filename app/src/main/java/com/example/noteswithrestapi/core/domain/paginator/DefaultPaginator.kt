package com.example.noteswithrestapi.core.domain.paginator

import com.example.noteswithrestapi.core.domain.model.AppError
import com.example.noteswithrestapi.core.domain.model.AppResponse

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> AppResponse<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (AppError) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit,
) : Paginator {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextPage() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        when (result) {
            is AppResponse.Success -> {
                val items = result.data
                currentKey = getNextKey(items)
                onSuccess(items, currentKey)
                onLoadUpdated(false)
                return
            }

            is AppResponse.Failed -> {
                onError(result.error)
                onLoadUpdated(false)
                return
            }
        }
    }

    override fun reset() {
        currentKey = initialKey
    }

}