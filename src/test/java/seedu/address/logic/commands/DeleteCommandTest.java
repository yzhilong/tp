package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGameEntryAtIndex;
import static seedu.address.testutil.TypicalGameEntries.getTypicalGameBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GAMEENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GAMEENTRY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.gameentry.GameEntry;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalGameBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        GameEntry gameEntryToDelete = model.getFilteredGameEntryList().get(INDEX_FIRST_GAMEENTRY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_GAMEENTRY);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_GAMEENTRY_SUCCESS, gameEntryToDelete);

        ModelManager expectedModel = new ModelManager(model.getGameBook(), new UserPrefs());
        expectedModel.deleteGameEntry(gameEntryToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGameEntryList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_GAMEENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showGameEntryAtIndex(model, INDEX_FIRST_GAMEENTRY);

        GameEntry gameEntryToDelete = model.getFilteredGameEntryList().get(INDEX_FIRST_GAMEENTRY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_GAMEENTRY);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_GAMEENTRY_SUCCESS, gameEntryToDelete);

        Model expectedModel = new ModelManager(model.getGameBook(), new UserPrefs());
        expectedModel.deleteGameEntry(gameEntryToDelete);
        showNoGameEntry(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGameEntryAtIndex(model, INDEX_FIRST_GAMEENTRY);

        Index outOfBoundIndex = INDEX_SECOND_GAMEENTRY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getGameBook().getGameEntryList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_GAMEENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_GAMEENTRY);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_GAMEENTRY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_GAMEENTRY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoGameEntry(Model model) {
        model.updateFilteredGameEntryList(p -> false);

        assertTrue(model.getFilteredGameEntryList().isEmpty());
    }
}
