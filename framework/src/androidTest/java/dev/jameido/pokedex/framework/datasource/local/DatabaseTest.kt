package dev.jameido.pokedex.framework.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Created by Jameido on 08/01/2021.
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var dao: PkmnDao
    private lateinit var db: PkmnDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, PkmnDatabase::class.java).build()
        dao = db.pkmnDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

/*    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val user: User = TestUtil.createUser(3).apply {
            setName("george")
        }
        userDao.insert(user)
        val byName = userDao.findUsersByName("george")
        assertThat(byName.get(0), equalTo(user))
    }*/
}