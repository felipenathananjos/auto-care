package com.github.felipenathananjos.autocare.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.felipenathananjos.autocare.ui.components.state.DialogState

@Composable
fun SimpleDialog(state: DialogState, dismissDialog: () -> Unit) {
    if (state.show) {
        Dialog(onDismissRequest = dismissDialog) {
            DialogContent(state, dismissDialog)
        }
    }
}

@Composable
private fun DialogContent(state: DialogState, dismissDialog: () -> Unit) {
    Column(modifier = Modifier
        .background(color = Color.White, shape = RoundedCornerShape(4.dp))
        .height(120.dp)
        .width(350.dp)
        .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween) {
        Column(Modifier.fillMaxWidth()) {
            Text(state.message, color = Color.Black)
            Spacer(Modifier.height(20.dp))
        }
        TextButton(onClick = { dismissDialog() }) {
            Text("OK", color = Color.Black, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Preview
@Composable
private fun DialogPreview() {
    DialogContent(DialogState(true, "Aviso", "Ocorreu um erro ao tentar abrir o diálogo"), {})
}