/*
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAMETYPE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAMETYPE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTAMOUNT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDAMOUNT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGameEntryAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GAMEENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GAMEENTRY;
import static seedu.address.testutil.TypicalGameEntries.getTypicalGameBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditGameEntryDescriptor;
import seedu.address.model.GameBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.gameentry.GameEntry;

import seedu.address.testutil.EditGameEntryDescriptorBuilder;
import seedu.address.testutil.GameEntryBuilder;


*/
/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 *//*

public class EditCommandTest {

    private Model model = new ModelManager(getTypicalGameBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        GameEntry editedGameEntry = new GameEntryBuilder().build();
        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder(editedGameEntry).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GAMEENTRY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GAME_SUCCESS, editedGameEntry);

        Model expectedModel = new ModelManager(new GameBook(model.getGameBook()), new UserPrefs());
        expectedModel.setGameEntry(model.getFilteredGameEntryList().get(0), editedGameEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastGameEntry = Index.fromOneBased(model.getFilteredGameEntryList().size());
        GameEntry lastGameEntry = model.getFilteredGameEntryList().get(indexLastGameEntry.getZeroBased());

        GameEntryBuilder gameEntryInList = new GameEntryBuilder(lastGameEntry);
        GameEntry editedGameEntry = gameEntryInList.withGameType(VALID_GAMETYPE_1).withStartAmount(VALID_STARTAMOUNT_1)
                .withTags(VALID_ENDAMOUNT_1).build();

        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastGameEntry, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GAME_SUCCESS, editedGameEntry);

        Model expectedModel = new ModelManager(new GameBook(model.getGameBook()), new UserPrefs());
        expectedModel.setGameEntry(lastGameEntry, editedGameEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GAMEENTRY, new EditGameEntryDescriptor());
        GameEntry editedGameEntry = model.getFilteredGameEntryList().get(INDEX_FIRST_GAMEENTRY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GAME_SUCCESS, editedGameEntry);

        Model expectedModel = new ModelManager(new GameBook(model.getGameBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showGameEntryAtIndex(model, INDEX_FIRST_GAMEENTRY);

        GameEntry gameEntryInFilteredList = model.getFilteredGameEntryList().get(INDEX_FIRST_GAMEENTRY.getZeroBased());
        GameEntry editedGameEntry = new GameEntryBuilder(gameEntryInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GAMEENTRY,
                new EditGameEntryDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GAME_SUCCESS, editedGameEntry);

        Model expectedModel = new ModelManager(new GameBook(model.getGameBook()), new UserPrefs());
        expectedModel.setGameEntry(model.getFilteredGameEntryList().get(0), editedGameEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateGameEntryUnfilteredList_failure() {
        GameEntry firstGameEntry = model.getFilteredGameEntryList().get(INDEX_FIRST_GAMEENTRY.getZeroBased());
        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder(firstGameEntry).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_GAMEENTRY, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_GAME);
    }

    @Test
    public void execute_duplicateGameEntryFilteredList_failure() {
        showGameEntryAtIndex(model, INDEX_FIRST_GAMEENTRY);

        // edit game entry in filtered list into a duplicate in address book
        GameEntry gameEntryInList = model.getGameBook().getGameEntryList().get(INDEX_SECOND_GAMEENTRY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GAMEENTRY,
                new EditGameEntryDescriptorBuilder(gameEntryInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_GAME);
    }

    @Test
    public void execute_invalidGameEntryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGameEntryList().size() + 1);
        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_GAMEENTRY_DISPLAYED_INDEX);
    }

    */
/**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     *//*

    @Test
    public void execute_invalidGameEntryIndexFilteredList_failure() {
        showGameEntryAtIndex(model, INDEX_FIRST_GAMEENTRY);
        Index outOfBoundIndex = INDEX_SECOND_GAMEENTRY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getGameBook().getGameEntryList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditGameEntryDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_GAME_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_GAMEENTRY, DESC_AMY);

        // same values -> returns true
        EditGameEntryDescriptor copyDescriptor = new EditGameEntryDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_GAMEENTRY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_GAMEENTRY, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_GAMEENTRY, DESC_BOB)));
    }

}
*/
