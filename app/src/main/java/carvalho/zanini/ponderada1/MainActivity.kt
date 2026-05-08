package carvalho.zanini.ponderada1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import carvalho.zanini.ponderada1.ui.theme.Ponderada1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LancadorDeDadosApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LancadorDeDadosApp() {
    var dadoSelecionado by remember { mutableStateOf("D6") }
    var resultado by remember { mutableStateOf<Int?>(null) }
    val dados = listOf("D6", "D10", "D20", "D100")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Lançador de Dados",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Escolha o tipo de dado:")

        dados.forEach { dado ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = dadoSelecionado == dado,
                    onClick = { dadoSelecionado = dado }
                )
                Text(text = dado)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (dadoSelecionado == "D6") {
            val num = resultado ?: 0
            if (num in 1..6) FaceD6(num)
        }

        Button(
            onClick = {
                val valorSorteado = when (dadoSelecionado) {
                    "D6"   -> (1..6).random()
                    "D10"  -> (1..10).random()
                    "D20"  -> (1..20).random()
                    "D100" -> (1..100).random()
                    else   -> 0
                }
                resultado = valorSorteado
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Lançar dado")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = if (resultado != null) "Resultado do $dadoSelecionado: $resultado"
            else "Clique no botão para lançar o dado",
            fontSize = 20.sp
        )
    }
}

@Composable
fun FaceD6(valor: Int) {
    val emoji = when (valor) {
        1 -> "⚀"
        2 -> "⚁"
        3 -> "⚂"
        4 -> "⚃"
        5 -> "⚄"
        6 -> "⚅"
        else -> "🎲"
    }
    Text(text = emoji, fontSize = 80.sp)
}
