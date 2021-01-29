package duke.exception;

/**
 * Enum class to differentiate DukeException types
 */
public enum DukeExceptionType {
    EMPTY_SELECTION, EMPTY_DESCRIPTION, EMPTY_KEYWORD,
    INVALID_INTEGER, UNKNOWN_INPUT, SELECTION_EXCEED_RANGE, INVALID_DATE_FORMAT,
    LOAD_ERROR, SAVE_ERROR, FILE_CREATION_ERROR
}