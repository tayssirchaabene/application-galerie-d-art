package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

data class Artwork(
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceApp() {
    // Liste des œuvres d'art
    val artworks = listOf(
        Artwork(R.drawable.fleur, "Fleur Mystique", "Artiste A", "2021"),
        Artwork(R.drawable.images1, "Océan Bleu", "Artiste B", "2022"),
        Artwork(R.drawable.image3, "Montagne Majestueuse", "Artiste C", "2023")
    )

    // État pour suivre l'œuvre d'art actuellement affichée
    val currentArtworkIndex = remember { mutableStateOf(0) }

    // Récupérer l'œuvre actuelle
    val currentArtwork = artworks[currentArtworkIndex.value]

    // Structure de l'écran
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Image de l'œuvre
        Image(
            painter = painterResource(currentArtwork.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .border(2.dp, Color.Gray)
                .align(Alignment.CenterHorizontally)
        )

        // 2. Description de l'œuvre (titre, artiste, année)
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = currentArtwork.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = currentArtwork.artist, fontStyle = FontStyle.Italic)
            Text(text = "Année de publication : ${currentArtwork.year}")
        }

        // 3. Contrôles (Boutons Précédent / Suivant)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                // Action pour le bouton "Précédent"
                currentArtworkIndex.value =
                    if (currentArtworkIndex.value > 0) currentArtworkIndex.value - 1
                    else artworks.size - 1 // Revenir à la dernière œuvre si on est à la première
            }) {
                Text("Précédent")
            }

            Button(onClick = {
                // Action pour le bouton "Suivant"
                currentArtworkIndex.value =
                    if (currentArtworkIndex.value < artworks.size - 1) currentArtworkIndex.value + 1
                    else 0 // Revenir à la première œuvre si on est à la dernière
            }) {
                Text("Suivant")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArtSpaceApp() {
    ArtSpaceApp()
}
