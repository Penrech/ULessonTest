package com.enrech.ulessontest.common.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.enrech.ulessontest.common.composition.LocalNavController
import com.enrech.ulessontest.common_domain.entity.ModalFeatureInputData

data class ModalFeatureLauncher(private val originRoute: String?, val key: String? = null) {

    private var data: ModalFeatureInputData<*>? = null

    fun getParams(): Map<String, Any> =
        mutableMapOf(MODAL_DESTINATION_ROUTE to (originRoute ?: ""), MODAL_DESTINATION_KEY to (key ?: "")).let { map ->
            data?.let { map + it.getParams() } ?: (map + ModalFeatureInputData.init.getParams())
        }

    fun addInputData(data: ModalFeatureInputData<*>) {
        this.data = data
    }

    companion object {
        const val MODAL_DESTINATION_ROUTE = "modal_destination_route"
        const val MODAL_DESTINATION_KEY = "modal_destination_key"
        val Init = ModalFeatureLauncher(MODAL_DESTINATION_ROUTE)
    }
}

@Composable
inline fun <reified R : ModalFeatureResultData> rememberModalForResult(
    key: String? = null,
    crossinline onResult: (result: R) -> Unit
): ModalFeatureLauncher {
    val navController = LocalNavController.current
    val currentBackStackEntry = navController.currentBackStackEntry

    val resultData =
        currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<R>(key ?: R::class.java.name)
            ?.observeAsState()

    LaunchedEffect(resultData?.value) {
        resultData?.value?.let(onResult)
        currentBackStackEntry?.savedStateHandle?.set(key ?: R::class.java.name, null)
    }

    return rememberUpdatedState(ModalFeatureLauncher(currentBackStackEntry?.destination?.route, key)).value

}

