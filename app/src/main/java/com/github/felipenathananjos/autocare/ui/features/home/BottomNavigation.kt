package com.github.felipenathananjos.autocare.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodalog.app.ui.theme.AutoCareTheme

@Composable
fun BottomNavigation(onMenuClick: (Menu) -> Unit, content: @Composable () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background), contentAlignment = Alignment.BottomEnd
    ) {
        Column(Modifier.fillMaxSize()) {
            content()
        }
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onBackground),
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                menuList.forEach {
                    MenuItem(it, onMenuClick)
                }
            }
        }
    }
}

private data class MenuState(
    val menu: Menu,
    var selected: Boolean = false,
)

private val menuList = listOf(
    MenuState(Menu.HOME, true),
    MenuState(Menu.HISTORY),
    MenuState(Menu.VEHICLES),
    MenuState(Menu.MAINTENANCE),
)

@Composable
private fun MenuItem(state: MenuState, onMenuClick: (Menu) -> Unit) {
    val menuColor = if (state.selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable(null, null) {
        onMenuClick(state.menu)
    }) {
        Icon(imageVector = state.menu.icon, contentDescription = state.menu.label, tint = menuColor)
        Text(state.menu.label, style = MaterialTheme.typography.bodyMedium.copy(color = menuColor))
    }
}

@Composable
@Preview(name = "Bottom Navigator")
private fun BottomNavigationPreview() {
    AutoCareTheme {
        BottomNavigation({}) { }
    }
}