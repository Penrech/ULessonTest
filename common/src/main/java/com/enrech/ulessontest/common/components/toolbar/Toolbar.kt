package com.enrech.ulessontest.common.components.toolbar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.enrech.ulessontest.common_domain.extension.Empty
import com.enrech.ulessontest.common_resources.theme.ULessonTheme
import com.enrech.ulessontest.common_resources.theme.grey100

@Composable
fun ULessonNavigationToolbar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { String.Empty },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = grey100
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick ) {
                Icon(
                    painter = painterResource(id = com.enrech.ulessontest.common_resources.R.drawable.ic_arrow_left),
                    contentDescription = null
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ToolbarPreview() {
    ULessonTheme {
        ULessonNavigationToolbar {}
    }
}