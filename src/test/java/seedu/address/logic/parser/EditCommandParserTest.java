/*
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserTestUtil.DATE_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.GAMEONE;
import static seedu.address.logic.parser.ParserTestUtil.GAMETWO;
import static seedu.address.logic.parser.ParserTestUtil.DURATION_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.ENDAMOUNT_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.STARTAMOUNT_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.TAG_EMPTY;
import static seedu.address.logic.parser.ParserTestUtil.VALID_DATE_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_DURATION_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_ENDAMOUNT_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_GAMETYPE_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_LOCATION_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_STARTAMOUNT_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_TAG_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_TAG_2;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DURATION;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_START_AMOUNT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_END_AMOUNT;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
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
        assertParseFailure(parser, GAMEONE.GAMETYPE_WITH_PREFIX, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + GAMEONE.GAMETYPE_WITH_PREFIX, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + GAMEONE.GAMETYPE_WITH_PREFIX, MESSAGE_INVALID_FORMAT);

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
        assertParseFailure(parser, 1 + GAMEONE.STARTAMOUNT_WITH_PREFIX + ENDAMOUNT_INVALID_WITH_PREFIX,
                MESSAGE_INVALID_END_AMOUNT);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + STARTAMOUNT_INVALID_WITH_PREFIX + ENDAMOUNT_INVALID_WITH_PREFIX,
                MESSAGE_INVALID_START_AMOUNT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX + GAMETWO.TAG_WITH_PREFIX;

        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withGameType(VALID_GAMETYPE_1)
                .withStartAmount(VALID_STARTAMOUNT_1).withEndAmount(VALID_ENDAMOUNT_1).withDatePlayed(VALID_DATE_1)
                .withDuration(VALID_DURATION_1).withLocation(VALID_LOCATION_1)
                .withTags(VALID_TAG_1, VALID_TAG_2).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMEONE.STARTAMOUNT_WITH_PREFIX + GAMEONE.ENDAMOUNT_WITH_PREFIX;

        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withStartAmount(VALID_STARTAMOUNT_1)
                .withEndAmount(VALID_ENDAMOUNT_1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // gameType
        Index targetIndex = INDEX_THIRD_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMEONE.GAMETYPE_WITH_PREFIX;
        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withGameType(VALID_GAMETYPE_1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // startAmount
        userInput = targetIndex.getOneBased() + GAMEONE.STARTAMOUNT_WITH_PREFIX;
        descriptor = new EditGameEntryDescriptorBuilder().withStartAmount(VALID_STARTAMOUNT_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // endAmount
        userInput = targetIndex.getOneBased() + GAMEONE.ENDAMOUNT_WITH_PREFIX;
        descriptor = new EditGameEntryDescriptorBuilder().withEndAmount(VALID_ENDAMOUNT_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + GAMEONE.DATE_WITH_PREFIX;
        descriptor = new EditGameEntryDescriptorBuilder().withDatePlayed(VALID_DATE_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = targetIndex.getOneBased() + GAMEONE.DURATION_WITH_PREFIX;
        descriptor = new EditGameEntryDescriptorBuilder().withDuration(VALID_DURATION_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + GAMEONE.LOCATION_WITH_PREFIX;
        descriptor = new EditGameEntryDescriptorBuilder().withLocation(VALID_LOCATION_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + GAMEONE.TAG_WITH_PREFIX;
        descriptor = new EditGameEntryDescriptorBuilder().withTags(VALID_TAG_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMETWO.GAMETYPE_WITH_PREFIX + GAMEONE.GAMETYPE_WITH_PREFIX
                + GAMETWO.STARTAMOUNT_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX +GAMETWO.ENDAMOUNT_WITH_PREFIX
                + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMETWO.DATE_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX
                + GAMETWO.DURATION_WITH_PREFIX + GAMETWO.DURATION_WITH_PREFIX + GAMETWO.LOCATION_WITH_PREFIX
                + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX + GAMETWO.TAG_WITH_PREFIX;

        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withGameType(VALID_GAMETYPE_1)
                .withStartAmount(VALID_STARTAMOUNT_1).withEndAmount(VALID_ENDAMOUNT_1)
                .withDatePlayed(VALID_DATE_1).withDuration(VALID_DURATION_1).withLocation(VALID_LOCATION_1)
                .withTags(VALID_TAG_1, VALID_TAG_2)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_GAMEENTRY;
        String userInput = targetIndex.getOneBased() +GAMEONE.STARTAMOUNT_WITH_PREFIX
                + STARTAMOUNT_INVALID_WITH_PREFIX;
        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withStartAmount(VALID_STARTAMOUNT_1)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + GAMEONE.GAMETYPE_WITH_PREFIX + STARTAMOUNT_INVALID_WITH_PREFIX
                + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX;
        descriptor = new EditGameEntryDescriptorBuilder().withStartAmount(VALID_STARTAMOUNT_1)
                .withGameType(VALID_GAMETYPE_1).withEndAmount(VALID_ENDAMOUNT_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
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
*/
