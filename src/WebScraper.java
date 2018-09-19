import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/** .
 *
 */
public class WebScraper {
    /** .
     *
     * @param args hi
     */
    public static void main(final String[] args) {
        String webText = urlToString("http://erdani.com/tdpl/hamlet.txt");
        String checkWord = "Hamlet";
        System.out.println(countWords(webText));
        System.out.println(countOneWord(webText, checkWord));

    }

    /**
     * @param webText the web text to search
     * @param thisWord the word to search for
     * @return the number of occurences of the word
     */
    public static int countOneWord(final String webText, final String thisWord) {
        if (thisWord == null || thisWord.equals(' ') || thisWord.isEmpty()) {
            return 0;
        }
        String[] howMany = webText.split(thisWord);
        return howMany.length - 1;

    }
    /**
     *
     * @param webText the text from the web page
     * @return the number of words on the page
     */
    public static int countWords(final String webText) {
        String[] webLines = webText.split("\\n");
        int wordCount = 0;
        for (int count = 0; count < webLines.length; count++) {
            if (webLines[count].isEmpty()) {
                continue;
            }
            boolean canCount = false;
            for (int count2 = 0; count2 < webLines[count].length() - 1; count2++) {
                String thisStr = webLines[count];
                if (canCount && thisStr.charAt(count2) == ' ' && thisStr.charAt(count2 + 1) != ' ') {
                    wordCount++;
                }
                if (thisStr.charAt(count2) != ' ') {
                    canCount = true;
                }
            }
            if (canCount) {
                wordCount++;
            }

        }
        return wordCount;
    }
    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }


}
