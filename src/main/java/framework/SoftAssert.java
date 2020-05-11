package framework;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.testng.asserts.Assertion;

import java.util.LinkedList;
import java.util.List;

public class SoftAssert extends Assertion {

    private List<AssertionError> m_errors = new LinkedList<>();
    private Integer counter = 1;
    private boolean shouldCaptureScreen;

    public SoftAssert(){ this(true); }

    public SoftAssert(boolean shouldCaptureScreen){
        this.shouldCaptureScreen = shouldCaptureScreen;
    }

    public <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        if (!matcher.matches(actual)) {
            Description description = new StringDescription();
            description.appendText(reason).appendText("\nExpected: ").appendDescriptionOf(matcher).appendText("\n     but: ");
            matcher.describeMismatch(actual, description);
            AssertionError ae = new AssertionError(description.toString());
            m_errors.add(ae);
            Screenshoter.takeScreenshot(counter.toString() + "_fail(id" +ae.hashCode()+")");
        }
    }

    public void assertAll() {
        if (! m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder("The following asserts failed:");
            boolean first = true;
            int number = 1;
            for (AssertionError ae : m_errors) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                sb.append("\n").append(number++).append("LineNumber").append(ae.getStackTrace()[1].getLineNumber()).append(": ").append(ae.getMessage());
            }
            throw new AssertionError(sb.toString());
        }
    }
}

