package com.bd.multiverseexplorer.data.mapper

import com.bd.multiverseexplorer.fake.testCharacterDto
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterMapperTest {

    @Test
    fun `character dto maps to domain character correctly`() {

        // When

        val character =
            testCharacterDto.toCharacter()

        // Then

        assertEquals(
            1,
            character.id
        )

        assertEquals(
            "Rick Sanchez",
            character.name
        )

        assertEquals(
            "Alive",
            character.status
        )

        assertEquals(
            "Human",
            character.species
        )

        assertEquals(
            "Male",
            character.gender
        )

        assertEquals(
            "Earth (C-137)",
            character.originName
        )

        assertEquals(
            "Citadel of Ricks",
            character.locationName
        )

        assertEquals(
            "rick.jpg",
            character.imageUrl
        )

        assertEquals(
            listOf(
                "episode-1",
                "episode-2"
            ),
            character.episodeUrls
        )
    }
}