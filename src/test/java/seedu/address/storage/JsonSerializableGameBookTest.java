package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.GameBook;
import seedu.address.testutil.TypicalGameEntries;

public class JsonSerializableGameBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableGameBookTest");
    private static final Path TYPICAL_GAME_ENTRIES_FILE = TEST_DATA_FOLDER.resolve("typicalGameEntriesGameBook.json");
    private static final Path INVALID_GAME_ENTRY_FILE = TEST_DATA_FOLDER.resolve("invalidGameEntryGameBook.json");
    private static final Path DUPLICATE_GAME_ENTRY_FILE = TEST_DATA_FOLDER.resolve("duplicateGameEntryGameBook.json");

    @Test
    public void toModelType_typicalGameEntriesFile_success() throws Exception {
        JsonSerializableGameBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_GAME_ENTRIES_FILE,
                JsonSerializableGameBook.class).get();
        GameBook addressBookFromFile = dataFromFile.toModelType();
        GameBook typicalGameEntriesAddressBook = TypicalGameEntries.getTypicalGameBook();
        assertEquals(addressBookFromFile, typicalGameEntriesAddressBook);
    }

    @Test
    public void toModelType_invalidGameEntryFile_throwsIllegalValueException() throws Exception {
        JsonSerializableGameBook dataFromFile = JsonUtil.readJsonFile(INVALID_GAME_ENTRY_FILE,
                JsonSerializableGameBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGameEntries_throwsIllegalValueException() throws Exception {
        JsonSerializableGameBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GAME_ENTRY_FILE,
                JsonSerializableGameBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableGameBook.MESSAGE_DUPLICATE_GAME_ENTRY,
                dataFromFile::toModelType);
    }

}
