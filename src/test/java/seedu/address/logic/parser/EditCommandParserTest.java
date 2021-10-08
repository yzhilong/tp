package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DURATION;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_START_AMOUNT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_END_AMOUNT;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.*;


import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditGameEntryDescriptor;
import seedu.address.model.tag.Tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditCommandParserTest {

    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    private static final String VALID_GAMETYPE = "Poker";
    private static final String VALID_STARTAMOUNT = "0.0";
    private static final String VALID_ENDAMOUNT = "100.0";
    private static Date VALID_DATE;

    static {
        try {
            VALID_DATE = new SimpleDateFormat("dd/MM/yy").parse("01/01/21");
        } catch (ParseException e) {
            VALID_DATE = null;
        }
    }

    private static final String VALID_DURATION = "10";
    private static final String VALID_LOCATION = "Sentosa";
    private static final String VALID_TAG_1 = "lucky";
    private static final String VALID_TAG_2 = "drunk";

    private static final String STARTAMOUNT_INVALID_STARTAMOUNT = " " + PREFIX_STARTAMOUNT + " " + "abc";
    private static final String ENDAMOUNT_INVALID_ENDAMOUNT = " " + PREFIX_ENDAMOUNT + " " + "abc";
    private static final String DATE_INVALID_DATE = " " + PREFIX_DATE + " " + "2021/01/01";
    private static final String DURATION_INVALID_DURATION = " " + PREFIX_DURATION + " " + "abc";


    private static final String GAMETYPE_VALID_GAMETYPE_1 = " "+ PREFIX_GAMETYPE+ " " + "Poker";
    private static final String STARTAMOUNT_VALID_STARTAMOUNT_1 = " " + PREFIX_STARTAMOUNT + " " + "0.0";
    private static final String ENDAMOUNT_VALID_ENDAMOUNT_1 = " " + PREFIX_ENDAMOUNT + " " + "100.0";
    private static final String DATE_VALID_DATE_1 = " " + PREFIX_DATE + " " + "01/01/21";
    private static final String DURATION_VALID_DURATION_1 = " " + PREFIX_DURATION + " " + "10";
    private static final String LOCATION_VALID_LOCATION_1 = " " + PREFIX_LOCATION + " " + "Sentosa";
    private static final String TAG_VALID_TAG_1 = " " + PREFIX_TAG + " " + "lucky";


    private static final String GAMETYPE_VALID_GAMETYPE_2 = " "+ PREFIX_GAMETYPE+ " " + "Black Jack";
    private static final String STARTAMOUNT_VALID_STARTAMOUNT_2 = " " + PREFIX_STARTAMOUNT + " " + "10.0";
    private static final String ENDAMOUNT_VALID_ENDAMOUNT_2 = " " + PREFIX_ENDAMOUNT + " " + "200.0";
    private static final String DATE_VALID_DATE_2 = " " + PREFIX_DATE + " " + "10/10/21";
    private static final String DURATION_VALID_DURATION_2 = " " + PREFIX_DURATION + " " + "20";
    private static final String LOCATION_VALID_LOCATION_2 = " " + PREFIX_LOCATION + " " + "Marina Bay";
    private static final String TAG_VALID_TAG_2 = " " + PREFIX_TAG + " " + "drunk";
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, GAMETYPE_VALID_GAMETYPE_1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + GAMETYPE_VALID_GAMETYPE_1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + GAMETYPE_VALID_GAMETYPE_1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid startAmount
        assertParseFailure(parser, "1" + STARTAMOUNT_INVALID_STARTAMOUNT, MESSAGE_INVALID_START_AMOUNT);
        // invalid endAmount
        assertParseFailure(parser, "1" + ENDAMOUNT_INVALID_ENDAMOUNT, MESSAGE_INVALID_END_AMOUNT);
        // invalid date
        assertParseFailure(parser, "1" + DATE_INVALID_DATE, MESSAGE_INVALID_DATE);
        // invalid duration
        assertParseFailure(parser, "1" + DURATION_INVALID_DURATION, MESSAGE_INVALID_DURATION);

        // valid startAmount followed by invalid endAmount
        assertParseFailure(parser, 1 + STARTAMOUNT_VALID_STARTAMOUNT_1 + ENDAMOUNT_INVALID_ENDAMOUNT,
                MESSAGE_INVALID_END_AMOUNT);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + STARTAMOUNT_INVALID_STARTAMOUNT + ENDAMOUNT_INVALID_ENDAMOUNT,
                MESSAGE_INVALID_START_AMOUNT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_1
                + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1 + TAG_VALID_TAG_2;

        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withGameType(VALID_GAMETYPE)
                .withStartAmount(VALID_STARTAMOUNT).withEndAmount(VALID_ENDAMOUNT).withDate(VALID_DATE)
                .withDuration(VALID_DURATION).withLocation(VALID_LOCATION)
                .withTags(VALID_TAG_1, VALID_TAG_2).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + STARTAMOUNT_VALID_STARTAMOUNT_1 + ENDAMOUNT_VALID_ENDAMOUNT_1;

        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withStartAmount(VALID_STARTAMOUNT)
                .withEndAmount(VALID_ENDAMOUNT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // gameType
        Index targetIndex = INDEX_THIRD_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMETYPE_VALID_GAMETYPE_1;
        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withGameType(VALID_GAMETYPE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // startAmount
        userInput = targetIndex.getOneBased() + STARTAMOUNT_VALID_STARTAMOUNT_1;
        descriptor = new EditGameEntryDescriptorBuilder().withStartAmount(VALID_STARTAMOUNT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // endAmount
        userInput = targetIndex.getOneBased() + ENDAMOUNT_VALID_ENDAMOUNT_1;
        descriptor = new EditGameEntryDescriptorBuilder().withEndAmount(VALID_ENDAMOUNT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_VALID_DATE_1;
        descriptor = new EditGameEntryDescriptorBuilder().withDate(VALID_DATE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = targetIndex.getOneBased() + DURATION_VALID_DURATION_1;
        descriptor = new EditGameEntryDescriptorBuilder().withDuration(VALID_DURATION).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_VALID_LOCATION_1;
        descriptor = new EditGameEntryDescriptorBuilder().withLocation(VALID_LOCATION).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_VALID_TAG_1;
        descriptor = new EditGameEntryDescriptorBuilder().withTags(VALID_TAG_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + GAMETYPE_VALID_GAMETYPE_2 + GAMETYPE_VALID_GAMETYPE_1
                + STARTAMOUNT_VALID_STARTAMOUNT_2 + STARTAMOUNT_VALID_STARTAMOUNT_1 + ENDAMOUNT_VALID_ENDAMOUNT_2
                + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_2 + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_2
                + DURATION_VALID_DURATION_1 + LOCATION_VALID_LOCATION_2 + LOCATION_VALID_LOCATION_1
                + TAG_VALID_TAG_1 + TAG_VALID_TAG_2;

        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withGameType(VALID_GAMETYPE)
                .withStartAmount(VALID_STARTAMOUNT).withEndAmount(VALID_ENDAMOUNT)
                .withDate(VALID_DATE).withDuration(VALID_DURATION).withLocation(VALID_LOCATION)
                .withTag(TAG_VALID_TAG_1, TAG_VALID_TAG_2)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_GAMEENTRY;
        String userInput = targetIndex.getOneBased() + STARTAMOUNT_VALID_STARTAMOUNT_1
                + STARTAMOUNT_INVALID_STARTAMOUNT;
        EditGameEntryDescriptor descriptor = new EditGameEntryDescriptorBuilder().withStartAmount(VALID_STARTAMOUNT)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_INVALID_STARTAMOUNT
                + ENDAMOUNT_VALID_ENDAMOUNT_1 + STARTAMOUNT_VALID_STARTAMOUNT_1;
        descriptor = new EditGameEntryDescriptorBuilder().withStartAmount(VALID_STARTAMOUNT).withGameType(VALID_GAMETYPE)
                .withEndAmount(VALID_ENDAMOUNT).build();
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
