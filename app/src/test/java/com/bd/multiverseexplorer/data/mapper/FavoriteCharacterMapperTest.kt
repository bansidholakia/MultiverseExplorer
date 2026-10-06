package com.bd.multiverseexplorer.data.mapper

import com.bd.multiverseexplorer.fake.testRick
import com.bd.shared.data.mapper.toCharacter
import com.bd.shared.data.mapper.toFavoriteEntity
import junit.framework.TestCase.assertEquals
import org.junit.Test

class FavoriteCharacterMapperTest {

    @Test
    fun `domain character maps to favorite entity`() {

        val entity =
            testRick.toFavoriteEntity()

        assertEquals(
            testRick.id,
            entity.id
        )

        assertEquals(
            testRick.name,
            entity.name
        )

        assertEquals(
            testRick.imageUrl,
            entity.imageUrl
        )
    }

    @Test
    fun `favorite entity maps back to character`() {

        val entity =
            testRick.toFavoriteEntity()

        val character =
            entity.toCharacter()

        assertEquals(
            testRick.id,
            character.id
        )

        assertEquals(
            testRick.name,
            character.name
        )

        assertEquals(
            testRick.originName,
            character.originName
        )
    }
}