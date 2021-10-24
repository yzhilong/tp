
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentTokenizer.MESSAGE_DUPLICATE_FLAGS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserTestUtil.DATE_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.DURATION_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.ENDAMOUNT_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.GAMEONE;
import static seedu.address.logic.parser.ParserTestUtil.GAMETWO;
import static seedu.address.logic.parser.ParserTestUtil.STARTAMOUNT_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.TAG_EMPTY;
import static seedu.address.logic.parser.ParserTestUtil.VALID_DATE_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_DURATION_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_ENDAMOUNT_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_GAMETYPE_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_LOCATION_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_STARTAMOUNT_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_TAG_1;
// import static seedu.address.logic.parser.ParserTestUtil.VALID_TAG_2;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DURATION;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_END_AMOUNT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_START_AMOUNT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GAMEENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GAMEENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_GAMEENTRY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditGameEntryDescriptor;
import seedu.address.testutil.EditGameEntryDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + GAMEONE.gameTypeWithPrefix, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + GAMEONE.gameTypeWithPrefix, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid startAmount
        assertParseFailure(parser, "1" + STARTAMOUNT_INVALID_WITH_PREFIX, MESSAGE_INVALID_START_AMOUNT);
        // invalid endAmount
        assertParseFailure(parser, "1" + ENDAMOUNT_INVALID_WITH_PREFIX, MESSAGE_INVALID_END_AMOUNT);
        // invalid date
        assertParseFailure(parser, "1" + DATE_INVALID_WITH_PREFIX, MESSAGE_INVALID_DATE);
        // invalid duration
        assertParseFailure(parser, "1" + DURATION_INVALID_WITH_PREFIX, MESSAGE_INVALID_DURATION);

        // valid startAmount followed by invalid endAmount
        assertParseFailure(parser, 1 + GAMEONE.startAmountWithPrefix + ENDAMOUNT_INVALID_WITH_PREFIX,
                MESSAGE_INVALID_END_AMOUNT);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + STARTAMOUNT_INVALID_WITH_PREFIX + ENDAMOUNT_INVALID_WITH_PREFIX,
                MESSAGE_INVALID_START_AMOUNT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix + GAMEONE.durationWithPrefix
                + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix;

        EditGameEntryDescriptor descriptor;
        descriptor = new EditGameEntryDescriptorBuilder()
                .withGameType(VALID_GAMETYPE_1.toString())
                .withStartAmount(VALID_STARTAMOUNT_1)
                .withEndAmount(VALID_ENDAMOUNT_1).withDatePlayed(VALID_DATE_1)
                .withDuration(VALID_DURATION_1).withLocation(VALID_LOCATION_1)
                .withTags(VALID_TAG_1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMEONE.startAmountWithPrefix + GAMEONE.endAmountWithPrefix;

        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withStartAmount(VALID_STARTAMOUNT_1)
                .withEndAmount(VALID_ENDAMOUNT_1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // gameType
        Index targetIndex = INDEX_THIRD_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMEONE.gameTypeWithPrefix;
        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder()
            .withGameType(VALID_GAMETYPE_1.toString()).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // startAmount
        userInput = targetIndex.getOneBased() + GAMEONE.startAmountWithPrefix;
        descriptor = new EditGameEntryDescriptorBuilder().withStartAmount(VALID_STARTAMOUNT_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // endAmount
        userInput = targetIndex.getOneBased() + GAMEONE.endAmountWithPrefix;
        descriptor = new EditGameEntryDescriptorBuilder().withEndAmount(VALID_ENDAMOUNT_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + GAMEONE.dateWithPrefix;
        descriptor = new EditGameEntryDescriptorBuilder().withDatePlayed(VALID_DATE_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = targetIndex.getOneBased() + GAMEONE.durationWithPrefix;
        descriptor = new EditGameEntryDescriptorBuilder().withDuration(VALID_DURATION_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + GAMEONE.locationWithPrefix;
        descriptor = new EditGameEntryDescriptorBuilder().withLocation(VALID_LOCATION_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + GAMEONE.tagWithPrefix;
        descriptor = new EditGameEntryDescriptorBuilder().withTags(VALID_TAG_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        Index targetIndex = INDEX_FIRST_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMETWO.gameTypeWithPrefix + GAMEONE.gameTypeWithPrefix
                + GAMETWO.startAmountWithPrefix + GAMEONE.startAmountWithPrefix + GAMETWO.endAmountWithPrefix
                + GAMEONE.endAmountWithPrefix + GAMETWO.dateWithPrefix + GAMEONE.dateWithPrefix
                + GAMETWO.durationWithPrefix + GAMEONE.durationWithPrefix + GAMETWO.locationWithPrefix
                + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix + GAMETWO.tagWithPrefix;

        assertParseFailure(parser, userInput, MESSAGE_DUPLICATE_FLAGS);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + STARTAMOUNT_INVALID_WITH_PREFIX
                + GAMEONE.startAmountWithPrefix;
        assertParseFailure(parser, userInput, MESSAGE_DUPLICATE_FLAGS);

        // other valid values specified
        userInput = targetIndex.getOneBased() + GAMEONE.gameTypeWithPrefix + STARTAMOUNT_INVALID_WITH_PREFIX
                + GAMEONE.endAmountWithPrefix + GAMEONE.startAmountWithPrefix;
        assertParseFailure(parser, userInput, MESSAGE_DUPLICATE_FLAGS);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

