package ru.myhw.task1.trading.parser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for format
 * {Name}: {
 * key1={value1}
 * key2={value2}
 * ...
 * }
 */
public class Parser {

    private static final Pattern entryPattern = Pattern.compile("^[\\W]*(\\w*)=\\{(\\S*)}[\\W]*$");
    private static final Pattern startPattern = Pattern.compile("^[\\w]*:[\\s]*\\{[\\n]*$");
    private static final Pattern endPattern = Pattern.compile("^}[\\n]*$");
    private static final String EXCEPTION_MESSAGE = "Invalid format";

    private Parser() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<String, String> parse(InputStream stream) throws ParserException {
        Scanner scanner = new Scanner(stream);
        HashMap<String, String> map = new HashMap<>();

        if (!isValidFirstLine(scanner.nextLine())) {
            throw new ParserException(EXCEPTION_MESSAGE);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            boolean isMatch = processLine(map, line);
            if (!isMatch) {
                if (!isLastLine(line)) throw new ParserException(EXCEPTION_MESSAGE);
                break;
            }
        }
        return map;
    }

    private static boolean processLine(HashMap<String, String> map, String line) {
        Matcher matcher = entryPattern.matcher(line);
        boolean isMatch = matcher.matches();
        if (isMatch) {
            map.put(matcher.group(1), matcher.group(2));
        }
        return isMatch;
    }

    private static boolean isLastLine(String line) {
        return endPattern.matcher(line).matches();
    }

    private static boolean isValidFirstLine(String line) {
        return startPattern.matcher(line).matches();
    }
}
