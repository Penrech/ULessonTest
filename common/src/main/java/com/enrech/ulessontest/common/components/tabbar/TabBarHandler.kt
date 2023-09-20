package com.enrech.ulessontest.common.components.tabbar

import com.enrech.ulessontest.common_domain.extension.Unspecified

@Suppress("UNCHECKED_CAST")
interface TabBarHandler {
    fun getTabList(): List<TabBarData>
    fun <T: TabBarData> getTab(index: Int): T? = (getTabList() as? List<T>)?.getOrNull(index)
    fun <T: TabBarData> getTabIndex(tab: T): Int = when (val index = (getTabList() as? List<T>)?.indexOf(tab)) {
        Int.Unspecified, null -> 0
        else -> index
    }
}