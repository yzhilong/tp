package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGameEntries.getTypicalGameBook;
import static seedu.address.testutil.TypicalGameEntries.POKER1;
import static seedu.address.testutil.TypicalGameEntries.BLACKJACK1;
import static seedu.address.testutil.TypicalGameEntries.DARTS1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.GameBook;
import seedu.address.model.ReadOnlyGameBook;

public class JsonGameBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyGameBook> readAddressBook(String filePath) throws Exception {
        return new JsonGameBookStorage(Paths.get(filePath)).readGameBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        GameBook original = getTypicalGameBook();
        JsonGameBookStorage jsonAddressBookStorage = new JsonGameBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveGameBook(original, filePath);
        ReadOnlyGameBook readBack = jsonAddressBookStorage.readGameBook(filePath).get();
        assertEquals(original, new GameBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGameEntry(BLACKJACK1);
        original.removeGameEntry(POKER1);
        jsonAddressBookStorage.saveGameBook(original, filePath);
        readBack = jsonAddressBookStorage.readGameBook(filePath).get();
        assertEquals(original, new GameBook(readBack));

        // Save and read without specifying file path
        original.addGameEntry(DARTS1);
        jsonAddressBookStorage.saveGameBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readGameBook().get(); // file path not specified
        assertEquals(original, new GameBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyGameBook addressBook, String filePath) {
        try {
            new JsonGameBookStorage(Paths.get(filePath))
                    .saveGameBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new GameBook(), null));
    }
}
