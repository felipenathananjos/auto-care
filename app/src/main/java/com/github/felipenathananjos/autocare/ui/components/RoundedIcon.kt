package com.github.felipenathananjos.autocare.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
    fun RoundedIcon(icon: ImageVector) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(20.dp))
                .padding(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Notificações",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }